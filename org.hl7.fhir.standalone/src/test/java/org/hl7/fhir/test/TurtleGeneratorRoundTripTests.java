package org.hl7.fhir.test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.google.gson.JsonParser;
import org.hl7.fhir.standalone.testing.TestingUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TurtleGeneratorRoundTripTests {
  @TempDir
  Path temporaryDirectory;

  static Stream<Arguments> patientVersionsAndFormats() {
    return Stream.of("4.0.1", "5.0.0", "6.0.0-snapshot1")
        .flatMap(version -> Stream.of("json", "xml").map(format -> Arguments.of(version, format)));
  }

  static Stream<Arguments> differences() {
    return Stream.of(
        Arguments.of("{\"id\":1,\"active\":true}", "{\"active\":true,\"id\":1}", null),
        Arguments.of("{\"id\":1,\"active\":true}", "{\"id\":1,\"active\":false}", "$[\"active\"]: expected=true, actual=false"),
        Arguments.of("{\"text\":{\"div\":\"narrative\"}}", "{\"text\":{}}", "$[\"text\"][\"div\"]: missing property; expected=\"narrative\""),
        Arguments.of("{}", "{\"id\":null}", "$[\"id\"]: unexpected property; actual=null"),
        Arguments.of("[1,2]", "[2,1]", "$[0]: expected=1, actual=2"),
        Arguments.of("[1]", "[1,2]", "$: expected array length=1, actual array length=2"),
        Arguments.of("true", "\"true\"", "$: expected=true, actual=\"true\""),
        Arguments.of("{\"value\":9007199254740992}", "{\"value\":9007199254740993}",
          "$[\"value\"]: expected=9007199254740992, actual=9007199254740993"),
        Arguments.of("[1.00000000000000001]", "[1.00000000000000002]",
          "$[0]: expected=1.00000000000000001, actual=1.00000000000000002"),
        Arguments.of("1.0", "1.00", null));
  }

  @ParameterizedTest
  @MethodSource("differences")
  void reportsFirstStructuralDifference(String expected, String actual, String difference) {
    Assertions.assertEquals(difference, TurtleGeneratorRoundTrip.describeFirstDifference(
        JsonParser.parseString(expected), JsonParser.parseString(actual), "$"));
  }

  @Test
  void roundTripsResourceShellAndWritesJson() throws Exception {
    Path inputDirectory = Files.createDirectory(temporaryDirectory.resolve("input"));
    Path source = inputDirectory.resolve("patient.json");
    Files.writeString(source, "{\"resourceType\":\"Patient\"}");
    Path jsonDirectory = temporaryDirectory.resolve("json");
    Path turtleDirectory = temporaryDirectory.resolve("ttl");
    TurtleGeneratorRoundTrip runner = new TurtleGeneratorRoundTrip(TestingUtilities.getSharedWorkerContext());
    runner.testRoundTrip(inputDirectory, "*.json", path -> runner.roundTripJsonResource(path, jsonDirectory, turtleDirectory));
    Assertions.assertEquals(JsonParser.parseString(Files.readString(source)),
        JsonParser.parseString(Files.readString(jsonDirectory.resolve("patient.json"))));
    Assertions.assertTrue(Files.size(turtleDirectory.resolve("patient.ttl")) > 0);
    Assertions.assertThrows(java.io.IOException.class,
        () -> runner.roundTripJsonResource(source, inputDirectory, turtleDirectory));
    Assertions.assertEquals("{\"resourceType\":\"Patient\"}", Files.readString(source));
    Path emptyDirectory = Files.createDirectory(temporaryDirectory.resolve("empty"));
    Assertions.assertThrows(AssertionError.class,
        () -> runner.testRoundTrip(emptyDirectory, "*.json", path -> Assertions.fail("Unexpected input")));
    Files.writeString(inputDirectory.resolve("second.json"), "{}");
    var visited = new java.util.ArrayList<Path>();
    var failures = Assertions.assertThrows(org.opentest4j.MultipleFailuresError.class,
        () -> runner.testRoundTrip(inputDirectory, "*.json", path -> {
          visited.add(path);
          Assertions.fail("Mismatch for " + path);
        }));
    Assertions.assertEquals(2, failures.getFailures().size());
    Assertions.assertEquals(2, visited.size());
  }

  @ParameterizedTest
  @MethodSource("patientVersionsAndFormats")
  void roundTripsPopulatedPatient(String version, String format) throws Exception {
    Path source = temporaryDirectory.resolve("patient." + format);
    String narrative = "<div xmlns=\"http://www.w3.org/1999/xhtml\"><p>Before <b>bold</b> &amp; after.</p><p>Second paragraph.</p></div>";
    String json = "{\"resourceType\":\"Patient\",\"id\":\"example\",\"active\":true,"
      + "\"text\":{\"status\":\"generated\",\"div\":" + new com.google.gson.JsonPrimitive(narrative) + "},"
      + "\"name\":[{\"family\":\"Example\",\"given\":[\"First\",\"Second\"]}]}";
    String xml = "<Patient xmlns=\"http://hl7.org/fhir\"><id value=\"example\"/>"
      + "<text><status value=\"generated\"/>" + narrative + "</text><active value=\"true\"/>"
        + "<name><family value=\"Example\"/><given value=\"First\"/><given value=\"Second\"/></name></Patient>";
    Files.writeString(source, format.equals("json") ? json : xml);
    var parsers = TurtleGeneratorTestUtils.ParserContext.fromWorkerContext(TestingUtilities.getSharedWorkerContext(version));
    TurtleGeneratorRoundTrip runner = new TurtleGeneratorRoundTrip(parsers);
    Path generatedDirectory = Files.createDirectory(temporaryDirectory.resolve("generated"));
    String generatedPath;
    if (format.equals("json")) {
      runner.roundTripJsonResource(source, null, temporaryDirectory.resolve("ttl"));
      generatedPath = parsers.generateTurtleFromJsonResourcePath(source, generatedDirectory);
    } else {
      runner.roundTripXmlResource(source, temporaryDirectory.resolve("ttl"));
      generatedPath = parsers.generateTurtleFromXmlResourcePath(source, generatedDirectory);
    }
    Assertions.assertEquals(parsers.parseGeneratedTurtle(generatedPath),
        parsers.parseGeneratedTurtle(temporaryDirectory.resolve("ttl/patient.ttl").toString()));
  }

  @Test
  void restoresR4RepetitionsByIndexRatherThanTripleOrder() throws Exception {
    String turtle = """
        @prefix fhir: <http://hl7.org/fhir/> .
        @prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
        <http://example.org/patient> rdf:type fhir:Patient;
          fhir:nodeRole fhir:treeRoot;
          fhir:Patient.name [
            fhir:index 1;
            fhir:HumanName.family [ fhir:value "Second family" ]
          ], [
            fhir:index 0;
            fhir:HumanName.family [ fhir:value "First family" ];
            fhir:HumanName.given [ fhir:index 2; fhir:value "Third" ],
              [ fhir:index 0; fhir:value "First" ],
              [ fhir:index 1; fhir:value "Second" ]
          ];
          fhir:DomainResource.contained [
            rdf:type fhir:Organization; fhir:index 1;
            fhir:Resource.id [ fhir:value "second" ]
          ], [
            rdf:type fhir:Organization; fhir:index 0;
            fhir:Resource.id [ fhir:value "first" ]
          ].
        """;
    var parsers = TurtleGeneratorTestUtils.ParserContext.fromWorkerContext(TestingUtilities.getSharedWorkerContext("4.0.1"));
    var resource = parsers.parseResource(new java.io.ByteArrayInputStream(turtle.getBytes(java.nio.charset.StandardCharsets.UTF_8)),
        org.hl7.fhir.model.utilities.formats.FhirFormat.TURTLE);
    var output = new java.io.ByteArrayOutputStream();
    parsers.composeResource(resource, output, org.hl7.fhir.model.utilities.formats.FhirFormat.JSON);
    String expected = """
        {"resourceType":"Patient","name":[
          {"family":"First family","given":["First","Second","Third"]},
          {"family":"Second family"}],
          "contained":[{"resourceType":"Organization","id":"first"},
                       {"resourceType":"Organization","id":"second"}]}
        """;
    Assertions.assertNull(TurtleGeneratorRoundTrip.describeFirstDifference(JsonParser.parseString(expected),
        JsonParser.parseString(output.toString(java.nio.charset.StandardCharsets.UTF_8)), "$"));
  }

  @Test
  void roundTripConfiguredJsonDirectory() throws Exception {
    var properties = TurtleGeneratorTestUtils.loadLocalProperties();
    Path inputDirectory = TurtleGeneratorTestUtils.getConfiguredDirectory(properties, "inputJsonDirectory", null);
    Assumptions.assumeTrue(inputDirectory != null, "Set inputJsonDirectory to run external JSON fixtures");
    Path jsonDirectory = TurtleGeneratorTestUtils.getConfiguredDirectory(properties, "outputJsonDirectory", null);
    Path turtleDirectory = TurtleGeneratorTestUtils.getConfiguredDirectory(properties, "outputTtlDirectory", temporaryDirectory.resolve("ttl"));
    String version = System.getProperty("roundTripVersion", properties.getProperty("roundTripVersion", "5.0.0"));
    TurtleGeneratorRoundTrip runner = new TurtleGeneratorRoundTrip(TestingUtilities.getSharedWorkerContext(version));
    runner.testRoundTrip(inputDirectory, "*.json", path -> runner.roundTripJsonResource(path, jsonDirectory, turtleDirectory));
  }
}