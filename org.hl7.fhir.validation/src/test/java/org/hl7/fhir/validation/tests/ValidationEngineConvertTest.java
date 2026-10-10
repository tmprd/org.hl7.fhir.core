package org.hl7.fhir.validation.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.google.gson.JsonParser;
import org.hl7.fhir.model.utilities.formats.FhirFormat;
import org.hl7.fhir.validation.ValidationEngine;
import org.hl7.fhir.validation.tests.utilities.TestUtilities;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class ValidationEngineConvertTest {

  static final String OBSERVATION_JSON = "{\"resourceType\":\"Observation\",\"id\":\"example\",\"status\":\"final\",\"code\":{\"text\":\"blood pressure\"}}";
  static final String OBSERVATION_XML = "<Observation xmlns=\"http://hl7.org/fhir\"><id value=\"example\"/><status value=\"final\"/><code><text value=\"blood pressure\"/></code></Observation>";

  // Preserve existing case-sensitive matching behavior and XML fallback
  @DisplayName("Convert output format is chosen from the output file extension")
  @ParameterizedTest(name = "{0} -> {1}")
  @CsvSource({
    "out.json, JSON",
    ".json, JSON",
    "out.JSON, XML",
    "out.Json, XML",
    "out.ttl, TURTLE",
    "dir.d/out.ttl, TURTLE",
    "out.TTL, XML",
    "out.xml, XML",
    "out.txt, XML",
    "out.fml, XML",
    "out.ndjson, XML",
    "out.turtle, XML",
    "'', XML",
    "out, XML",
    "out., XML",
    "out.json.bak, XML",
    "dir.json/out, XML"
  })
  void outputFormatFollowsExtension(String output, FhirFormat expectedFormat) {
    assertEquals(expectedFormat, ValidationEngine.convertOutputFormat(output));
  }

  static Stream<Arguments> conversionVersions() {
    return Stream.of(
      Arguments.of("hl7.fhir.r4.core#4.0.1", "4.0.1", new String[] {"fhir:Observation.status", "fhir:value"}, new String[] {"fhir:v "}),
      Arguments.of("hl7.fhir.r5.core#5.0.0", "5.0.0", new String[] {"fhir:status", "fhir:v "}, new String[] {"fhir:Observation.status"}),
      Arguments.of("hl7.fhir.r6.core#6.0.0-ballot3", "6.0.0-ballot3", new String[] {"fhir:status", "fhir:v "}, new String[] {"fhir:Observation.status"})
    );
  }

  @DisplayName("Convert writes version-specific Turtle and reads it back to JSON and XML")
  @ParameterizedTest(name = "{1}")
  @MethodSource("conversionVersions")
  void convertJsonAndXmlThroughTurtle(String core, String version, String[] expected, String[] unexpected, @TempDir Path dir) throws Exception {
    ValidationEngine engine = TestUtilities.getValidationEngineNoTxServer(core, version);
    for (String inputFormat : new String[] {"json", "xml"}) {
      Path source = dir.resolve("observation." + inputFormat);
      Files.writeString(source, "json".equals(inputFormat) ? OBSERVATION_JSON : OBSERVATION_XML);
      Path output = dir.resolve(inputFormat + ".ttl");
      engine.convert(source.toString(), output.toString());

      String ttl = Files.readString(output, StandardCharsets.UTF_8);
      assertTrue(ttl.contains("@prefix fhir:"), ttl);
      for (String fragment : expected) {
        assertTrue(ttl.contains(fragment), "missing '" + fragment + "' in:\n" + ttl);
      }
      for (String fragment : unexpected) {
        assertFalse(ttl.contains(fragment), "unexpected '" + fragment + "' in:\n" + ttl);
      }

      Path json = dir.resolve(inputFormat + "-roundtrip.json");
      engine.convert(output.toString(), json.toString());
      assertSameJson(json);

      Path xml = dir.resolve(inputFormat + "-roundtrip.xml");
      engine.convert(output.toString(), xml.toString());
      Path xmlAsJson = dir.resolve(inputFormat + "-roundtrip-xml.json");
      engine.convert(xml.toString(), xmlAsJson.toString());
      assertSameJson(xmlAsJson);
    }
  }

  private static void assertSameJson(Path actual) throws Exception {
    assertEquals(JsonParser.parseString(OBSERVATION_JSON),
      JsonParser.parseString(Files.readString(actual, StandardCharsets.UTF_8)), actual.toString());
  }

  @DisplayName("Convert preserves resource content from XML to JSON")
  @ParameterizedTest(name = "{1}")
  @CsvSource({
    "hl7.fhir.r4.core#4.0.1, 4.0.1",
    "hl7.fhir.r5.core#5.0.0, 5.0.0",
    "hl7.fhir.r6.core#6.0.0-ballot3, 6.0.0-ballot3"
  })
  void convertXmlToJson(String core, String version, @TempDir Path dir) throws Exception {
    Path source = dir.resolve("observation.xml");
    Files.writeString(source, OBSERVATION_XML);
    Path output = dir.resolve("observation.json");

    ValidationEngine engine = TestUtilities.getValidationEngineNoTxServer(core, version);
    engine.convert(source.toString(), output.toString());

    assertEquals(JsonParser.parseString(OBSERVATION_JSON),
      JsonParser.parseString(Files.readString(output, StandardCharsets.UTF_8)));
  }
}
