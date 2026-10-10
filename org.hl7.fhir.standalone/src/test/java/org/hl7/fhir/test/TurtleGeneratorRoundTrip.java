package org.hl7.fhir.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.hl7.fhir.model.utilities.formats.FhirFormat;
import org.hl7.fhir.services.context.IWorkerContext;
import org.hl7.fhir.services.elementmodel.Element;
import org.hl7.fhir.test.TurtleGeneratorTestUtils.ParserContext;
import org.hl7.fhir.utilities.filesystem.ManagedFileAccess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

public final class TurtleGeneratorRoundTrip {
  private final ParserContext parsers;

  public TurtleGeneratorRoundTrip(IWorkerContext context) {
    this(ParserContext.fromWorkerContext(Objects.requireNonNull(context)));
  }

  public TurtleGeneratorRoundTrip(ParserContext parsers) {
    this.parsers = Objects.requireNonNull(parsers);
  }

  public void testRoundTrip(Path inputDirectory, String fileExtension, RoundTripResource roundTripper) throws IOException {
    List<Path> inputs;
    try (var paths = Files.newDirectoryStream(inputDirectory, fileExtension)) {
      inputs = new java.util.ArrayList<>();
      for (Path path : paths) {
        if (Files.isRegularFile(path)) {
          inputs.add(path);
        }
      }
    }
    inputs.sort(Path::compareTo);
    Assertions.assertFalse(inputs.isEmpty(), "No " + fileExtension + " resources in " + inputDirectory);
    Assertions.assertAll("Turtle roundtrip (" + parsers.getFhirVersion() + ")",
        inputs.stream().map(path -> (Executable) () -> {
          try {
            roundTripper.roundTrip(path);
          } catch (Exception exception) {
            throw new IOException("Roundtrip failed for " + path, exception);
          }
        }));
  }

  public void roundTripJsonResource(Path resourcePath, Path outputJsonDirectory, Path outputTurtleDirectory) throws IOException {
    roundTripResource(resourcePath, FhirFormat.JSON, outputJsonDirectory, outputTurtleDirectory);
  }

  public void roundTripXmlResource(Path resourcePath, Path outputTurtleDirectory) throws IOException {
    roundTripResource(resourcePath, FhirFormat.XML, null, outputTurtleDirectory);
  }

  private void roundTripResource(Path resourcePath, FhirFormat format, Path outputDirectory, Path outputTurtleDirectory) throws IOException {
    Element original;
    byte[] input = Files.readAllBytes(resourcePath);
    try (InputStream stream = new ByteArrayInputStream(input)) {
      original = parsers.parseStrictResource(stream, format);
    }
    String expectedJson = format == FhirFormat.JSON ? new String(input, StandardCharsets.UTF_8)
        : new String(compose(original, FhirFormat.JSON), StandardCharsets.UTF_8);
    byte[] turtle = compose(original, FhirFormat.TURTLE);
    String fileName = resourcePath.getFileName().toString();
    String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
    Files.createDirectories(outputTurtleDirectory);
    Path turtlePath = outputTurtleDirectory.resolve(baseName + ".ttl");
    write(turtlePath, turtle, resourcePath);
    Element reversed;
    try (InputStream stream = new ByteArrayInputStream(turtle)) {
      reversed = parsers.parseStrictResource(stream, FhirFormat.TURTLE);
    }
    byte[] output = compose(reversed, format);
    Path outputPath = null;
    if (outputDirectory != null) {
      Files.createDirectories(outputDirectory);
      outputPath = outputDirectory.resolve(fileName);
      write(outputPath, output, resourcePath);
    }
    Element reparsed;
    try (InputStream stream = new ByteArrayInputStream(output)) {
      reparsed = parsers.parseStrictResource(stream, format);
    }
    String actualJson = format == FhirFormat.JSON ? new String(output, StandardCharsets.UTF_8)
        : new String(compose(reparsed, FhirFormat.JSON), StandardCharsets.UTF_8);
    String difference = describeFirstDifference(JsonParser.parseString(expectedJson), JsonParser.parseString(actualJson), "$");
    Assertions.assertNull(difference, "Roundtrip mismatch for " + resourcePath + "\nFirst difference: " + difference
        + "\nTurtle: " + turtlePath + (outputPath == null ? "" : "\nGenerated JSON: " + outputPath));
  }

  private byte[] compose(Element element, FhirFormat format) throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    parsers.composeResource(element, output, format);
    return output.toByteArray();
  }

  private void write(Path destination, byte[] content, Path source) throws IOException {
    if (destination.toAbsolutePath().normalize().equals(source.toAbsolutePath().normalize())
        || (Files.exists(destination) && Files.isSameFile(destination, source))) {
      throw new IOException("Refusing to overwrite input resource: " + source);
    }
    try (var output = ManagedFileAccess.outStream(destination.toString())) {
      output.write(content);
    }
  }

  static String describeFirstDifference(JsonElement expected, JsonElement actual, String path) {
    if (expected != null && actual != null && expected.isJsonObject() && actual.isJsonObject()) {
      for (String name : expected.getAsJsonObject().keySet()) {
        String childPath = path + "[" + new com.google.gson.JsonPrimitive(name) + "]";
        if (!actual.getAsJsonObject().has(name)) {
          return childPath + ": missing property; expected=" + expected.getAsJsonObject().get(name);
        }
        String difference = describeFirstDifference(expected.getAsJsonObject().get(name), actual.getAsJsonObject().get(name), childPath);
        if (difference != null) {
          return difference;
        }
      }
      for (String name : actual.getAsJsonObject().keySet()) {
        if (!expected.getAsJsonObject().has(name)) {
          return path + "[" + new com.google.gson.JsonPrimitive(name) + "]: unexpected property; actual=" + actual.getAsJsonObject().get(name);
        }
      }
      return null;
    } else if (expected != null && actual != null && expected.isJsonArray() && actual.isJsonArray()) {
      int expectedSize = expected.getAsJsonArray().size();
      int actualSize = actual.getAsJsonArray().size();
      for (int index = 0; index < Math.min(expectedSize, actualSize); index++) {
        String difference = describeFirstDifference(expected.getAsJsonArray().get(index), actual.getAsJsonArray().get(index), path + "[" + index + "]");
        if (difference != null) {
          return difference;
        }
      }
      return expectedSize == actualSize ? null : path + ": expected array length=" + expectedSize + ", actual array length=" + actualSize;
    } else if (expected != null && actual != null && expected.isJsonPrimitive() && actual.isJsonPrimitive()
        && expected.getAsJsonPrimitive().isNumber() && actual.getAsJsonPrimitive().isNumber()) {
      if (expected.getAsBigDecimal().compareTo(actual.getAsBigDecimal()) == 0) {
        return null;
      }
    } else if (Objects.equals(expected, actual)) {
      return null;
    }
    return path + ": expected=" + expected + ", actual=" + actual;
  }

  @FunctionalInterface
  public interface RoundTripResource {
    void roundTrip(Path resourcePath) throws IOException;
  }
}