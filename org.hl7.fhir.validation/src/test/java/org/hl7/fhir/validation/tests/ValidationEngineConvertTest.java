package org.hl7.fhir.validation.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

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

  static Stream<Arguments> turtleVersions() {
    return Stream.of(
      Arguments.of("hl7.fhir.r4.core#4.0.1", "4.0.1", new String[] {"fhir:Observation.status", "fhir:value"}, new String[] {"fhir:v "}),
      Arguments.of("hl7.fhir.r5.core#5.0.0", "5.0.0", new String[] {"fhir:status", "fhir:v "}, new String[] {"fhir:Observation.status"}),
      Arguments.of("hl7.fhir.r6.core#6.0.0-ballot3", "6.0.0-ballot3", new String[] {"fhir:status", "fhir:v "}, new String[] {"fhir:Observation.status"})
    );
  }

  @DisplayName("Convert writes version-specific Turtle for a .ttl output")
  @ParameterizedTest(name = "{1}")
  @MethodSource("turtleVersions")
  void convertJsonToTurtle(String core, String version, String[] expected, String[] unexpected, @TempDir Path dir) throws Exception {
    Path source = dir.resolve("observation.json");
    Files.writeString(source, OBSERVATION_JSON);
    Path output = dir.resolve("observation.ttl");

    ValidationEngine engine = TestUtilities.getValidationEngineNoTxServer(core, version);
    engine.convert(source.toString(), output.toString());

    String ttl = Files.readString(output, StandardCharsets.UTF_8);
    assertTrue(ttl.contains("@prefix fhir:"), ttl);
    for (String s : expected) {
      assertTrue(ttl.contains(s), "missing '" + s + "' in:\n" + ttl);
    }
    for (String s : unexpected) {
      assertFalse(ttl.contains(s), "unexpected '" + s + "' in:\n" + ttl);
    }
  }
}
