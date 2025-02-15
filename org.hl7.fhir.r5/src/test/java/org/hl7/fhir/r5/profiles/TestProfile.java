package org.hl7.fhir.r5.profiles;

/*
 * Licensed under CC0 1.0 Universal (CC0 1.0).
 *
 * The person who associated a work with this deed has dedicated the work to the 
 * public domain by waiving all of his or her rights to the work worldwide under 
 * copyright law, including all related and neighboring rights, to the extent 
 * allowed by law.
 *
 * You can copy, modify, distribute and perform the work, even for commercial 
 * purposes, all without asking permission. See Other Information below.
 *
 */
import java.util.List;
import java.util.ArrayList;
import javax.annotation.Nullable;
import java.util.Date;


import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.model.*;
import org.hl7.fhir.r5.profilemodel.PEBuilder;
import org.hl7.fhir.r5.profilemodel.PEInstance;
import org.hl7.fhir.r5.profilemodel.PEBuilder.PEElementPropertiesPolicy;
import org.hl7.fhir.r5.profilemodel.gen.PEGeneratedBase;


// Generated by the HAPI Java Profile Generator, Sun, Aug 20, 2023 19:01+1000

/**
 * Test Observation Profile.
 *
 */
public class TestProfile extends PEGeneratedBase {

  private static final String CANONICAL_URL = "http://hl7.org/fhir/test/StructureDefinition/pe-profile1|0.1";

  private String id;  // 
  private List<Extension> extensions = new ArrayList<>();  // Extension
  private String simple;  // A simple extension
  private TestComplexExtension complex;  // A complex extension
  private Identifier identifier;  // Business Identifier for observation
  private String status;// @NotNull  // registered | preliminary | final | amended +
  private CodeableConcept code;// @NotNull  // Sexual Orientation
  private Reference subject;// @NotNull  // Who and/or what the observation is about
  private Reference encounter;  // Healthcare event during which this observation is made
  private Date effective;// @NotNull  // Clinically relevant time/time-period for observation
  private List<Reference> performers = new ArrayList<>();  // Who is responsible for the observation
  private TestDatatypeProfile valueCodeableConcept;  // Sexual Orientation

  /**
   * Parameter-less constructor. If you use this, the fixed values won't be filled 
   * out - they'll be missing. They'll be filled in if/when you call build, so they 
   * won't be missing from the resource, only from this particular object model
   *
   */
  public TestProfile() {
    // todo
  }

  /**
   * Construct an instance of the object, and fill out all the fixed values 
   *
   */
  public TestProfile(IWorkerContext context) {
    workerContext = context;
    PEBuilder builder = new PEBuilder(context, PEElementPropertiesPolicy.EXTENSION, true);
    PEInstance src = builder.buildPEInstance(CANONICAL_URL, builder.createResource(CANONICAL_URL, false));
    load(src);
  }

  /**
   * Populate an instance of the object based on this source object 
   *
   */
  public static TestProfile fromSource(IWorkerContext context, Observation source) {
    TestProfile theThing = new TestProfile();
    theThing.workerContext = context;
    PEBuilder builder = new PEBuilder(context, PEElementPropertiesPolicy.EXTENSION, true);
    PEInstance src = builder.buildPEInstance(CANONICAL_URL, source);
    theThing.load(src);
    return theThing;
  }


  public void load(PEInstance src) {
    clear();
    if (src.hasChild("id")) {
      id = ((IdType) src.child("id").asDataType()).getValue();
    }
    for (PEInstance item : src.children("extension")) {
      extensions.add((Extension) item.asDataType());
    }
    if (src.hasChild("simple")) {
      simple = src.child("simple").asDataType().primitiveValue();
    }
    if (src.hasChild("complex")) {
      complex = TestComplexExtension.fromSource(src.child("complex"));
    }
    if (src.hasChild("identifier")) {
      identifier = (Identifier) src.child("identifier").asDataType();
    }
    if (src.hasChild("status")) {
      status = src.child("status").asDataType().primitiveValue();
    }
    if (src.hasChild("code")) {
      code = (CodeableConcept) src.child("code").asDataType();
    }
    if (src.hasChild("subject")) {
      subject = (Reference) src.child("subject").asDataType();
    }
    if (src.hasChild("encounter")) {
      encounter = (Reference) src.child("encounter").asDataType();
    }
    if (src.hasChild("effective")) {
      effective = ((DateTimeType) src.child("effective").asDataType()).getValue();
    }
    for (PEInstance item : src.children("performer")) {
      performers.add((Reference) item.asDataType());
    }
    if (src.hasChild("valueCodeableConcept")) {
      valueCodeableConcept = TestDatatypeProfile.fromSource(src.child("valueCodeableConcept"));
    }

  }

  /**
   * Build an instance of the object based on this source object 
   *
   */
  public Observation build(IWorkerContext context) {
    workerContext = context;
    Observation theThing = new Observation();
    PEBuilder builder = new PEBuilder(context, PEElementPropertiesPolicy.EXTENSION, true);
    PEInstance tgt = builder.buildPEInstance(CANONICAL_URL, theThing);
    save(tgt, false);
    return theThing;
  }

  /**
   * Save this profile class into an existing resource (overwriting enything that 
   * exists in the profile) 
   *
   */
  public void save(IWorkerContext context, Observation dest, boolean nulls) {
    workerContext = context;
    PEBuilder builder = new PEBuilder(context, PEElementPropertiesPolicy.EXTENSION, true);
    PEInstance tgt = builder.buildPEInstance(CANONICAL_URL, dest);
    save(tgt, nulls);
  }

  public void save(PEInstance tgt, boolean nulls) {
    tgt.clear("id");
    if (id != null) {
      tgt.makeChild("id").data().setProperty("value", new IdType(id));
    }
    tgt.clear("extension");
    for (Extension item : extensions) {
      tgt.addChild("extension", item);
    }
    tgt.clear("simple");
    if (simple != null) {
      tgt.makeChild("simple").data().setProperty("value[x]", new CodeType(simple));
    }
    tgt.clear("complex");
    if (complex != null) {
      complex.save(tgt.makeChild("complex"), nulls);
    }
    tgt.clear("identifier");
    if (identifier != null) {
      tgt.addChild("identifier", identifier);
    }
    tgt.clear("status");
    if (status != null) {
      tgt.makeChild("status").data().setProperty("value", new StringType(status));
    }
    tgt.clear("code");
    if (code != null) {
      tgt.addChild("code", code);
    }
    tgt.clear("subject");
    if (subject != null) {
      tgt.addChild("subject", subject);
    }
    tgt.clear("encounter");
    if (encounter != null) {
      tgt.addChild("encounter", encounter);
    }
    tgt.clear("effective");
    if (effective != null) {
      tgt.addChild("effective", new DateTimeType(effective));
    }
    tgt.clear("performer");
    for (Reference item : performers) {
      tgt.addChild("performer", item);
    }
    tgt.clear("valueCodeableConcept");
    if (valueCodeableConcept != null) {
      valueCodeableConcept.save(tgt.makeChild("valueCodeableConcept"), nulls);
    }

  }

  /**
   * Test Observation Profile.
   *
   */
  public String getId() {
    return id;
  }

  public TestProfile setId(String value) {
    this.id = value;
    return this;
  }

  public boolean hasId() {
    return id != null;
  }

  /**
   * Test Observation Profile.
   *
   */
  public List<Extension> getExtensions() {
    if (extensions == null) { extensions = new ArrayList<>(); }
    return extensions;
  }

  public boolean hasExtensions() {
    return extensions != null && !extensions.isEmpty();
  }

  public Extension addExtension() {
    Extension theThing = new Extension();
    getExtensions().add(theThing);
    return theThing;
  }

  public boolean hasExtension(Extension item) {
    return hasExtensions() && extensions.contains(item);
  }

  public void removeExtension(Extension item) {
    if (hasExtension(item)) {
      extensions.remove(item);
    }
  }


  /**
   * Test Observation Profile.
   *
   */
  public String getSimple() {
    return simple;
  }

  public TestProfile setSimple(String value) {
    this.simple = value;
    return this;
  }

  public boolean hasSimple() {
    return simple != null;
  }

  /**
   * Test Observation Profile.
   *
   */
  public TestComplexExtension getComplex() {
    if (complex == null) { complex = new TestComplexExtension(); }
    return complex;
  }

  public TestProfile setComplex(TestComplexExtension value) {
    this.complex = value;
    return this;
  }
  public boolean hasComplex() {
    return complex != null;
  }

  /**
   * Test Observation Profile.
   *
   */
  public Identifier getIdentifier() {
    if (identifier == null) { identifier = new Identifier(); }
    return identifier;
  }

  public TestProfile setIdentifier(Identifier value) {
    this.identifier = value;
    return this;
  }
  public boolean hasIdentifier() {
    return identifier != null;
  }

  /**
   * Test Observation Profile.
   *
   */
  public String getStatus() {
    return status;
  }

  public TestProfile setStatus(String value) {
    this.status = value;
    return this;
  }

  public boolean hasStatus() {
    return status != null;
  }

  /**
   * Test Observation Profile.
   *
   */
  public CodeableConcept getCode() {
    if (code == null) { code = new CodeableConcept(); }
    return code;
  }

  public boolean hasCode() {
    return code != null;
  }

  /**
   * Test Observation Profile.
   *
   */
  public Reference getSubject() {
    if (subject == null) { subject = new Reference(); }
    return subject;
  }

  public TestProfile setSubject(Reference value) {
    this.subject = value;
    return this;
  }
  public boolean hasSubject() {
    return subject != null;
  }

  /**
   * Test Observation Profile.
   *
   */
  public Reference getEncounter() {
    if (encounter == null) { encounter = new Reference(); }
    return encounter;
  }

  public TestProfile setEncounter(Reference value) {
    this.encounter = value;
    return this;
  }
  public boolean hasEncounter() {
    return encounter != null;
  }

  /**
   * Test Observation Profile.
   *
   */
  public Date getEffective() {
    return effective;
  }

  public TestProfile setEffective(Date value) {
    this.effective = value;
    return this;
  }

  public boolean hasEffective() {
    return effective != null;
  }

  /**
   * Test Observation Profile.
   *
   */
  public List<Reference> getPerformers() {
    if (performers == null) { performers = new ArrayList<>(); }
    return performers;
  }

  public boolean hasPerformers() {
    return performers != null && !performers.isEmpty();
  }

  public Reference addPerformer() {
    Reference theThing = new Reference();
    getPerformers().add(theThing);
    return theThing;
  }

  public boolean hasPerformer(Reference item) {
    return hasPerformers() && performers.contains(item);
  }

  public void removePerformer(Reference item) {
    if (hasPerformer(item)) {
      performers.remove(item);
    }
  }


  /**
   * Test Observation Profile.
   *
   */
  public TestDatatypeProfile getValueCodeableConcept() {
    if (valueCodeableConcept == null) { valueCodeableConcept = new TestDatatypeProfile(); }
    return valueCodeableConcept;
  }

  public TestProfile setValueCodeableConcept(TestDatatypeProfile value) {
    this.valueCodeableConcept = value;
    return this;
  }
  public boolean hasValueCodeableConcept() {
    return valueCodeableConcept != null;
  }



  public void clear() {
    id = null;
    extensions.clear();
    simple = null;
    complex = null;
    identifier = null;
    status = null;
    code = null;
    subject = null;
    encounter = null;
    effective = null;
    performers.clear();
    valueCodeableConcept = null;

  }

}
