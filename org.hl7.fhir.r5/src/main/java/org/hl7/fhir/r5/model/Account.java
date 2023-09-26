﻿package org.hl7.fhir.r5.model;


/*
  Copyright (c) 2011+, HL7, Inc.
  All rights reserved.
  
  Redistribution and use in source and binary forms, with or without modification, \
  are permitted provided that the following conditions are met:
  
   * Redistributions of source code must retain the above copyright notice, this \
     list of conditions and the following disclaimer.
   * Redistributions in binary form must reproduce the above copyright notice, \
     this list of conditions and the following disclaimer in the documentation \
     and/or other materials provided with the distribution.
   * Neither the name of HL7 nor the names of its contributors may be used to 
     endorse or promote products derived from this software without specific 
     prior written permission.
  
  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\" AND \
  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED \
  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. \
  IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, \
  INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT \
  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR \
  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, \
  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) \
  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE \
  POSSIBILITY OF SUCH DAMAGE.
  */

// Generated on Thu, Mar 23, 2023 19:59+1100 for FHIR v5.0.0

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.r5.model.Enumerations.*;
import org.hl7.fhir.instance.model.api.IBaseBackboneElement;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.instance.model.api.ICompositeType;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.api.annotation.SearchParamDefinition;
import org.hl7.fhir.instance.model.api.IBaseBackboneElement;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.ChildOrder;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Block;

/**
 * A financial tool for tracking value accrued for a particular purpose.  In the healthcare field, used to track charges for a patient, cost centers, etc.
 */
@ResourceDef(name="Account", profile="http://hl7.org/fhir/StructureDefinition/Account")
public class Account extends DomainResource {

    public enum AccountStatus {
        /**
         * This account is active and may be used.
         */
        ACTIVE, 
        /**
         * This account is inactive and should not be used to track financial information.
         */
        INACTIVE, 
        /**
         * This instance should not have been part of this patient's medical record.
         */
        ENTEREDINERROR, 
        /**
         * This account is on hold.
         */
        ONHOLD, 
        /**
         * The account status is unknown.
         */
        UNKNOWN, 
        /**
         * added to help the parsers with the generic types
         */
        NULL;
        public static AccountStatus fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return ACTIVE;
        if ("inactive".equals(codeString))
          return INACTIVE;
        if ("entered-in-error".equals(codeString))
          return ENTEREDINERROR;
        if ("on-hold".equals(codeString))
          return ONHOLD;
        if ("unknown".equals(codeString))
          return UNKNOWN;
        if (Configuration.isAcceptInvalidEnums())
          return null;
        else
          throw new FHIRException("Unknown AccountStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ACTIVE: return "active";
            case INACTIVE: return "inactive";
            case ENTEREDINERROR: return "entered-in-error";
            case ONHOLD: return "on-hold";
            case UNKNOWN: return "unknown";
            case NULL: return null;
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case ACTIVE: return "http://hl7.org/fhir/account-status";
            case INACTIVE: return "http://hl7.org/fhir/account-status";
            case ENTEREDINERROR: return "http://hl7.org/fhir/account-status";
            case ONHOLD: return "http://hl7.org/fhir/account-status";
            case UNKNOWN: return "http://hl7.org/fhir/account-status";
            case NULL: return null;
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case ACTIVE: return "This account is active and may be used.";
            case INACTIVE: return "This account is inactive and should not be used to track financial information.";
            case ENTEREDINERROR: return "This instance should not have been part of this patient's medical record.";
            case ONHOLD: return "This account is on hold.";
            case UNKNOWN: return "The account status is unknown.";
            case NULL: return null;
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case ACTIVE: return "Active";
            case INACTIVE: return "Inactive";
            case ENTEREDINERROR: return "Entered in error";
            case ONHOLD: return "On Hold";
            case UNKNOWN: return "Unknown";
            case NULL: return null;
            default: return "?";
          }
        }
    }

  public static class AccountStatusEnumFactory implements EnumFactory<AccountStatus> {
    public AccountStatus fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return AccountStatus.ACTIVE;
        if ("inactive".equals(codeString))
          return AccountStatus.INACTIVE;
        if ("entered-in-error".equals(codeString))
          return AccountStatus.ENTEREDINERROR;
        if ("on-hold".equals(codeString))
          return AccountStatus.ONHOLD;
        if ("unknown".equals(codeString))
          return AccountStatus.UNKNOWN;
        throw new IllegalArgumentException("Unknown AccountStatus code '"+codeString+"'");
        }
        public Enumeration<AccountStatus> fromType(PrimitiveType<?> code) throws FHIRException {
          if (code == null)
            return null;
          if (code.isEmpty())
            return new Enumeration<AccountStatus>(this, AccountStatus.NULL, code);
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return new Enumeration<AccountStatus>(this, AccountStatus.NULL, code);
        if ("active".equals(codeString))
          return new Enumeration<AccountStatus>(this, AccountStatus.ACTIVE, code);
        if ("inactive".equals(codeString))
          return new Enumeration<AccountStatus>(this, AccountStatus.INACTIVE, code);
        if ("entered-in-error".equals(codeString))
          return new Enumeration<AccountStatus>(this, AccountStatus.ENTEREDINERROR, code);
        if ("on-hold".equals(codeString))
          return new Enumeration<AccountStatus>(this, AccountStatus.ONHOLD, code);
        if ("unknown".equals(codeString))
          return new Enumeration<AccountStatus>(this, AccountStatus.UNKNOWN, code);
        throw new FHIRException("Unknown AccountStatus code '"+codeString+"'");
        }
    public String toCode(AccountStatus code) {
      if (code == AccountStatus.ACTIVE)
        return "active";
      if (code == AccountStatus.INACTIVE)
        return "inactive";
      if (code == AccountStatus.ENTEREDINERROR)
        return "entered-in-error";
      if (code == AccountStatus.ONHOLD)
        return "on-hold";
      if (code == AccountStatus.UNKNOWN)
        return "unknown";
      return "?";
      }
    public String toSystem(AccountStatus code) {
      return code.getSystem();
      }
    }

    @Block()
    public static class CoverageComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * The party(s) that contribute to payment (or part of) of the charges applied to this account (including self-pay).

A coverage may only be responsible for specific types of charges, and the sequence of the coverages in the account could be important when processing billing.
         */
        @Child(name = "coverage", type = {Coverage.class}, order=1, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The party(s), such as insurances, that may contribute to the payment of this account", formalDefinition="The party(s) that contribute to payment (or part of) of the charges applied to this account (including self-pay).\n\nA coverage may only be responsible for specific types of charges, and the sequence of the coverages in the account could be important when processing billing." )
        protected Reference coverage;

        /**
         * The priority of the coverage in the context of this account.
         */
        @Child(name = "priority", type = {PositiveIntType.class}, order=2, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The priority of the coverage in the context of this account", formalDefinition="The priority of the coverage in the context of this account." )
        protected PositiveIntType priority;

        private static final long serialVersionUID = 1695665065L;

    /**
     * Constructor
     */
      public CoverageComponent() {
        super();
      }

    /**
     * Constructor
     */
      public CoverageComponent(Reference coverage) {
        super();
        this.setCoverage(coverage);
      }

        /**
         * @return {@link #coverage} (The party(s) that contribute to payment (or part of) of the charges applied to this account (including self-pay).

A coverage may only be responsible for specific types of charges, and the sequence of the coverages in the account could be important when processing billing.)
         */
        public Reference getCoverage() { 
          if (this.coverage == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CoverageComponent.coverage");
            else if (Configuration.doAutoCreate())
              this.coverage = new Reference(); // cc
          return this.coverage;
        }

        public boolean hasCoverage() { 
          return this.coverage != null && !this.coverage.isEmpty();
        }

        /**
         * @param value {@link #coverage} (The party(s) that contribute to payment (or part of) of the charges applied to this account (including self-pay).

A coverage may only be responsible for specific types of charges, and the sequence of the coverages in the account could be important when processing billing.)
         */
        public CoverageComponent setCoverage(Reference value) { 
          this.coverage = value;
          return this;
        }

        /**
         * @return {@link #priority} (The priority of the coverage in the context of this account.). This is the underlying object with id, value and extensions. The accessor "getPriority" gives direct access to the value
         */
        public PositiveIntType getPriorityElement() { 
          if (this.priority == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create CoverageComponent.priority");
            else if (Configuration.doAutoCreate())
              this.priority = new PositiveIntType(); // bb
          return this.priority;
        }

        public boolean hasPriorityElement() { 
          return this.priority != null && !this.priority.isEmpty();
        }

        public boolean hasPriority() { 
          return this.priority != null && !this.priority.isEmpty();
        }

        /**
         * @param value {@link #priority} (The priority of the coverage in the context of this account.). This is the underlying object with id, value and extensions. The accessor "getPriority" gives direct access to the value
         */
        public CoverageComponent setPriorityElement(PositiveIntType value) { 
          this.priority = value;
          return this;
        }

        /**
         * @return The priority of the coverage in the context of this account.
         */
        public int getPriority() { 
          return this.priority == null || this.priority.isEmpty() ? 0 : this.priority.getValue();
        }

        /**
         * @param value The priority of the coverage in the context of this account.
         */
        public CoverageComponent setPriority(int value) { 
            if (this.priority == null)
              this.priority = new PositiveIntType();
            this.priority.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("coverage", "Reference(Coverage)", "The party(s) that contribute to payment (or part of) of the charges applied to this account (including self-pay).\n\nA coverage may only be responsible for specific types of charges, and the sequence of the coverages in the account could be important when processing billing.", 0, 1, coverage));
          children.add(new Property("priority", "positiveInt", "The priority of the coverage in the context of this account.", 0, 1, priority));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case -351767064: /*coverage*/  return new Property("coverage", "Reference(Coverage)", "The party(s) that contribute to payment (or part of) of the charges applied to this account (including self-pay).\n\nA coverage may only be responsible for specific types of charges, and the sequence of the coverages in the account could be important when processing billing.", 0, 1, coverage);
          case -1165461084: /*priority*/  return new Property("priority", "positiveInt", "The priority of the coverage in the context of this account.", 0, 1, priority);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -351767064: /*coverage*/ return this.coverage == null ? new Base[0] : new Base[] {this.coverage}; // Reference
        case -1165461084: /*priority*/ return this.priority == null ? new Base[0] : new Base[] {this.priority}; // PositiveIntType
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -351767064: // coverage
          this.coverage = TypeConvertor.castToReference(value); // Reference
          return value;
        case -1165461084: // priority
          this.priority = TypeConvertor.castToPositiveInt(value); // PositiveIntType
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("coverage")) {
          this.coverage = TypeConvertor.castToReference(value); // Reference
        } else if (name.equals("priority")) {
          this.priority = TypeConvertor.castToPositiveInt(value); // PositiveIntType
        } else
          return super.setProperty(name, value);
        return value;
      }

  @Override
  public void removeChild(String name, Base value) throws FHIRException {
        if (name.equals("coverage")) {
          this.coverage = null;
        } else if (name.equals("priority")) {
          this.priority = null;
        } else
          super.removeChild(name, value);
        
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -351767064:  return getCoverage();
        case -1165461084:  return getPriorityElement();
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -351767064: /*coverage*/ return new String[] {"Reference"};
        case -1165461084: /*priority*/ return new String[] {"positiveInt"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("coverage")) {
          this.coverage = new Reference();
          return this.coverage;
        }
        else if (name.equals("priority")) {
          throw new FHIRException("Cannot call addChild on a singleton property Account.coverage.priority");
        }
        else
          return super.addChild(name);
      }

      public CoverageComponent copy() {
        CoverageComponent dst = new CoverageComponent();
        copyValues(dst);
        return dst;
      }

      public void copyValues(CoverageComponent dst) {
        super.copyValues(dst);
        dst.coverage = coverage == null ? null : coverage.copy();
        dst.priority = priority == null ? null : priority.copy();
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof CoverageComponent))
          return false;
        CoverageComponent o = (CoverageComponent) other_;
        return compareDeep(coverage, o.coverage, true) && compareDeep(priority, o.priority, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof CoverageComponent))
          return false;
        CoverageComponent o = (CoverageComponent) other_;
        return compareValues(priority, o.priority, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(coverage, priority);
      }

  public String fhirType() {
    return "Account.coverage";

  }

  }

    @Block()
    public static class GuarantorComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * The entity who is responsible.
         */
        @Child(name = "party", type = {Patient.class, RelatedPerson.class, Organization.class}, order=1, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Responsible entity", formalDefinition="The entity who is responsible." )
        protected Reference party;

        /**
         * A guarantor may be placed on credit hold or otherwise have their role temporarily suspended.
         */
        @Child(name = "onHold", type = {BooleanType.class}, order=2, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Credit or other hold applied", formalDefinition="A guarantor may be placed on credit hold or otherwise have their role temporarily suspended." )
        protected BooleanType onHold;

        /**
         * The timeframe during which the guarantor accepts responsibility for the account.
         */
        @Child(name = "period", type = {Period.class}, order=3, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Guarantee account during", formalDefinition="The timeframe during which the guarantor accepts responsibility for the account." )
        protected Period period;

        private static final long serialVersionUID = -523056773L;

    /**
     * Constructor
     */
      public GuarantorComponent() {
        super();
      }

    /**
     * Constructor
     */
      public GuarantorComponent(Reference party) {
        super();
        this.setParty(party);
      }

        /**
         * @return {@link #party} (The entity who is responsible.)
         */
        public Reference getParty() { 
          if (this.party == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create GuarantorComponent.party");
            else if (Configuration.doAutoCreate())
              this.party = new Reference(); // cc
          return this.party;
        }

        public boolean hasParty() { 
          return this.party != null && !this.party.isEmpty();
        }

        /**
         * @param value {@link #party} (The entity who is responsible.)
         */
        public GuarantorComponent setParty(Reference value) { 
          this.party = value;
          return this;
        }

        /**
         * @return {@link #onHold} (A guarantor may be placed on credit hold or otherwise have their role temporarily suspended.). This is the underlying object with id, value and extensions. The accessor "getOnHold" gives direct access to the value
         */
        public BooleanType getOnHoldElement() { 
          if (this.onHold == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create GuarantorComponent.onHold");
            else if (Configuration.doAutoCreate())
              this.onHold = new BooleanType(); // bb
          return this.onHold;
        }

        public boolean hasOnHoldElement() { 
          return this.onHold != null && !this.onHold.isEmpty();
        }

        public boolean hasOnHold() { 
          return this.onHold != null && !this.onHold.isEmpty();
        }

        /**
         * @param value {@link #onHold} (A guarantor may be placed on credit hold or otherwise have their role temporarily suspended.). This is the underlying object with id, value and extensions. The accessor "getOnHold" gives direct access to the value
         */
        public GuarantorComponent setOnHoldElement(BooleanType value) { 
          this.onHold = value;
          return this;
        }

        /**
         * @return A guarantor may be placed on credit hold or otherwise have their role temporarily suspended.
         */
        public boolean getOnHold() { 
          return this.onHold == null || this.onHold.isEmpty() ? false : this.onHold.getValue();
        }

        /**
         * @param value A guarantor may be placed on credit hold or otherwise have their role temporarily suspended.
         */
        public GuarantorComponent setOnHold(boolean value) { 
            if (this.onHold == null)
              this.onHold = new BooleanType();
            this.onHold.setValue(value);
          return this;
        }

        /**
         * @return {@link #period} (The timeframe during which the guarantor accepts responsibility for the account.)
         */
        public Period getPeriod() { 
          if (this.period == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create GuarantorComponent.period");
            else if (Configuration.doAutoCreate())
              this.period = new Period(); // cc
          return this.period;
        }

        public boolean hasPeriod() { 
          return this.period != null && !this.period.isEmpty();
        }

        /**
         * @param value {@link #period} (The timeframe during which the guarantor accepts responsibility for the account.)
         */
        public GuarantorComponent setPeriod(Period value) { 
          this.period = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("party", "Reference(Patient|RelatedPerson|Organization)", "The entity who is responsible.", 0, 1, party));
          children.add(new Property("onHold", "boolean", "A guarantor may be placed on credit hold or otherwise have their role temporarily suspended.", 0, 1, onHold));
          children.add(new Property("period", "Period", "The timeframe during which the guarantor accepts responsibility for the account.", 0, 1, period));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 106437350: /*party*/  return new Property("party", "Reference(Patient|RelatedPerson|Organization)", "The entity who is responsible.", 0, 1, party);
          case -1013289154: /*onHold*/  return new Property("onHold", "boolean", "A guarantor may be placed on credit hold or otherwise have their role temporarily suspended.", 0, 1, onHold);
          case -991726143: /*period*/  return new Property("period", "Period", "The timeframe during which the guarantor accepts responsibility for the account.", 0, 1, period);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 106437350: /*party*/ return this.party == null ? new Base[0] : new Base[] {this.party}; // Reference
        case -1013289154: /*onHold*/ return this.onHold == null ? new Base[0] : new Base[] {this.onHold}; // BooleanType
        case -991726143: /*period*/ return this.period == null ? new Base[0] : new Base[] {this.period}; // Period
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 106437350: // party
          this.party = TypeConvertor.castToReference(value); // Reference
          return value;
        case -1013289154: // onHold
          this.onHold = TypeConvertor.castToBoolean(value); // BooleanType
          return value;
        case -991726143: // period
          this.period = TypeConvertor.castToPeriod(value); // Period
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("party")) {
          this.party = TypeConvertor.castToReference(value); // Reference
        } else if (name.equals("onHold")) {
          this.onHold = TypeConvertor.castToBoolean(value); // BooleanType
        } else if (name.equals("period")) {
          this.period = TypeConvertor.castToPeriod(value); // Period
        } else
          return super.setProperty(name, value);
        return value;
      }

  @Override
  public void removeChild(String name, Base value) throws FHIRException {
        if (name.equals("party")) {
          this.party = null;
        } else if (name.equals("onHold")) {
          this.onHold = null;
        } else if (name.equals("period")) {
          this.period = null;
        } else
          super.removeChild(name, value);
        
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 106437350:  return getParty();
        case -1013289154:  return getOnHoldElement();
        case -991726143:  return getPeriod();
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 106437350: /*party*/ return new String[] {"Reference"};
        case -1013289154: /*onHold*/ return new String[] {"boolean"};
        case -991726143: /*period*/ return new String[] {"Period"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("party")) {
          this.party = new Reference();
          return this.party;
        }
        else if (name.equals("onHold")) {
          throw new FHIRException("Cannot call addChild on a singleton property Account.guarantor.onHold");
        }
        else if (name.equals("period")) {
          this.period = new Period();
          return this.period;
        }
        else
          return super.addChild(name);
      }

      public GuarantorComponent copy() {
        GuarantorComponent dst = new GuarantorComponent();
        copyValues(dst);
        return dst;
      }

      public void copyValues(GuarantorComponent dst) {
        super.copyValues(dst);
        dst.party = party == null ? null : party.copy();
        dst.onHold = onHold == null ? null : onHold.copy();
        dst.period = period == null ? null : period.copy();
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof GuarantorComponent))
          return false;
        GuarantorComponent o = (GuarantorComponent) other_;
        return compareDeep(party, o.party, true) && compareDeep(onHold, o.onHold, true) && compareDeep(period, o.period, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof GuarantorComponent))
          return false;
        GuarantorComponent o = (GuarantorComponent) other_;
        return compareValues(onHold, o.onHold, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(party, onHold, period);
      }

  public String fhirType() {
    return "Account.guarantor";

  }

  }

    @Block()
    public static class AccountDiagnosisComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Ranking of the diagnosis (for each type).
         */
        @Child(name = "sequence", type = {PositiveIntType.class}, order=1, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Ranking of the diagnosis (for each type)", formalDefinition="Ranking of the diagnosis (for each type)." )
        protected PositiveIntType sequence;

        /**
         * The diagnosis relevant to the account.
         */
        @Child(name = "condition", type = {CodeableReference.class}, order=2, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The diagnosis relevant to the account", formalDefinition="The diagnosis relevant to the account." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/condition-code")
        protected CodeableReference condition;

        /**
         * Ranking of the diagnosis (for each type).
         */
        @Child(name = "dateOfDiagnosis", type = {DateTimeType.class}, order=3, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Date of the diagnosis (when coded diagnosis)", formalDefinition="Ranking of the diagnosis (for each type)." )
        protected DateTimeType dateOfDiagnosis;

        /**
         * Type that this diagnosis has relevant to the account (e.g. admission, billing, discharge …).
         */
        @Child(name = "type", type = {CodeableConcept.class}, order=4, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
        @Description(shortDefinition="Type that this diagnosis has relevant to the account (e.g. admission, billing, discharge …)", formalDefinition="Type that this diagnosis has relevant to the account (e.g. admission, billing, discharge …)." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/encounter-diagnosis-use")
        protected List<CodeableConcept> type;

        /**
         * Was the Diagnosis present on Admission in the related Encounter.
         */
        @Child(name = "onAdmission", type = {BooleanType.class}, order=5, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Diagnosis present on Admission", formalDefinition="Was the Diagnosis present on Admission in the related Encounter." )
        protected BooleanType onAdmission;

        /**
         * The package code can be used to group diagnoses that may be priced or delivered as a single product. Such as DRGs.
         */
        @Child(name = "packageCode", type = {CodeableConcept.class}, order=6, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
        @Description(shortDefinition="Package Code specific for billing", formalDefinition="The package code can be used to group diagnoses that may be priced or delivered as a single product. Such as DRGs." )
        protected List<CodeableConcept> packageCode;

        private static final long serialVersionUID = 57125500L;

    /**
     * Constructor
     */
      public AccountDiagnosisComponent() {
        super();
      }

    /**
     * Constructor
     */
      public AccountDiagnosisComponent(CodeableReference condition) {
        super();
        this.setCondition(condition);
      }

        /**
         * @return {@link #sequence} (Ranking of the diagnosis (for each type).). This is the underlying object with id, value and extensions. The accessor "getSequence" gives direct access to the value
         */
        public PositiveIntType getSequenceElement() { 
          if (this.sequence == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create AccountDiagnosisComponent.sequence");
            else if (Configuration.doAutoCreate())
              this.sequence = new PositiveIntType(); // bb
          return this.sequence;
        }

        public boolean hasSequenceElement() { 
          return this.sequence != null && !this.sequence.isEmpty();
        }

        public boolean hasSequence() { 
          return this.sequence != null && !this.sequence.isEmpty();
        }

        /**
         * @param value {@link #sequence} (Ranking of the diagnosis (for each type).). This is the underlying object with id, value and extensions. The accessor "getSequence" gives direct access to the value
         */
        public AccountDiagnosisComponent setSequenceElement(PositiveIntType value) { 
          this.sequence = value;
          return this;
        }

        /**
         * @return Ranking of the diagnosis (for each type).
         */
        public int getSequence() { 
          return this.sequence == null || this.sequence.isEmpty() ? 0 : this.sequence.getValue();
        }

        /**
         * @param value Ranking of the diagnosis (for each type).
         */
        public AccountDiagnosisComponent setSequence(int value) { 
            if (this.sequence == null)
              this.sequence = new PositiveIntType();
            this.sequence.setValue(value);
          return this;
        }

        /**
         * @return {@link #condition} (The diagnosis relevant to the account.)
         */
        public CodeableReference getCondition() { 
          if (this.condition == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create AccountDiagnosisComponent.condition");
            else if (Configuration.doAutoCreate())
              this.condition = new CodeableReference(); // cc
          return this.condition;
        }

        public boolean hasCondition() { 
          return this.condition != null && !this.condition.isEmpty();
        }

        /**
         * @param value {@link #condition} (The diagnosis relevant to the account.)
         */
        public AccountDiagnosisComponent setCondition(CodeableReference value) { 
          this.condition = value;
          return this;
        }

        /**
         * @return {@link #dateOfDiagnosis} (Ranking of the diagnosis (for each type).). This is the underlying object with id, value and extensions. The accessor "getDateOfDiagnosis" gives direct access to the value
         */
        public DateTimeType getDateOfDiagnosisElement() { 
          if (this.dateOfDiagnosis == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create AccountDiagnosisComponent.dateOfDiagnosis");
            else if (Configuration.doAutoCreate())
              this.dateOfDiagnosis = new DateTimeType(); // bb
          return this.dateOfDiagnosis;
        }

        public boolean hasDateOfDiagnosisElement() { 
          return this.dateOfDiagnosis != null && !this.dateOfDiagnosis.isEmpty();
        }

        public boolean hasDateOfDiagnosis() { 
          return this.dateOfDiagnosis != null && !this.dateOfDiagnosis.isEmpty();
        }

        /**
         * @param value {@link #dateOfDiagnosis} (Ranking of the diagnosis (for each type).). This is the underlying object with id, value and extensions. The accessor "getDateOfDiagnosis" gives direct access to the value
         */
        public AccountDiagnosisComponent setDateOfDiagnosisElement(DateTimeType value) { 
          this.dateOfDiagnosis = value;
          return this;
        }

        /**
         * @return Ranking of the diagnosis (for each type).
         */
        public Date getDateOfDiagnosis() { 
          return this.dateOfDiagnosis == null ? null : this.dateOfDiagnosis.getValue();
        }

        /**
         * @param value Ranking of the diagnosis (for each type).
         */
        public AccountDiagnosisComponent setDateOfDiagnosis(Date value) { 
          if (value == null)
            this.dateOfDiagnosis = null;
          else {
            if (this.dateOfDiagnosis == null)
              this.dateOfDiagnosis = new DateTimeType();
            this.dateOfDiagnosis.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #type} (Type that this diagnosis has relevant to the account (e.g. admission, billing, discharge …).)
         */
        public List<CodeableConcept> getType() { 
          if (this.type == null)
            this.type = new ArrayList<CodeableConcept>();
          return this.type;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public AccountDiagnosisComponent setType(List<CodeableConcept> theType) { 
          this.type = theType;
          return this;
        }

        public boolean hasType() { 
          if (this.type == null)
            return false;
          for (CodeableConcept item : this.type)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public CodeableConcept addType() { //3
          CodeableConcept t = new CodeableConcept();
          if (this.type == null)
            this.type = new ArrayList<CodeableConcept>();
          this.type.add(t);
          return t;
        }

        public AccountDiagnosisComponent addType(CodeableConcept t) { //3
          if (t == null)
            return this;
          if (this.type == null)
            this.type = new ArrayList<CodeableConcept>();
          this.type.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #type}, creating it if it does not already exist {3}
         */
        public CodeableConcept getTypeFirstRep() { 
          if (getType().isEmpty()) {
            addType();
          }
          return getType().get(0);
        }

        /**
         * @return {@link #onAdmission} (Was the Diagnosis present on Admission in the related Encounter.). This is the underlying object with id, value and extensions. The accessor "getOnAdmission" gives direct access to the value
         */
        public BooleanType getOnAdmissionElement() { 
          if (this.onAdmission == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create AccountDiagnosisComponent.onAdmission");
            else if (Configuration.doAutoCreate())
              this.onAdmission = new BooleanType(); // bb
          return this.onAdmission;
        }

        public boolean hasOnAdmissionElement() { 
          return this.onAdmission != null && !this.onAdmission.isEmpty();
        }

        public boolean hasOnAdmission() { 
          return this.onAdmission != null && !this.onAdmission.isEmpty();
        }

        /**
         * @param value {@link #onAdmission} (Was the Diagnosis present on Admission in the related Encounter.). This is the underlying object with id, value and extensions. The accessor "getOnAdmission" gives direct access to the value
         */
        public AccountDiagnosisComponent setOnAdmissionElement(BooleanType value) { 
          this.onAdmission = value;
          return this;
        }

        /**
         * @return Was the Diagnosis present on Admission in the related Encounter.
         */
        public boolean getOnAdmission() { 
          return this.onAdmission == null || this.onAdmission.isEmpty() ? false : this.onAdmission.getValue();
        }

        /**
         * @param value Was the Diagnosis present on Admission in the related Encounter.
         */
        public AccountDiagnosisComponent setOnAdmission(boolean value) { 
            if (this.onAdmission == null)
              this.onAdmission = new BooleanType();
            this.onAdmission.setValue(value);
          return this;
        }

        /**
         * @return {@link #packageCode} (The package code can be used to group diagnoses that may be priced or delivered as a single product. Such as DRGs.)
         */
        public List<CodeableConcept> getPackageCode() { 
          if (this.packageCode == null)
            this.packageCode = new ArrayList<CodeableConcept>();
          return this.packageCode;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public AccountDiagnosisComponent setPackageCode(List<CodeableConcept> thePackageCode) { 
          this.packageCode = thePackageCode;
          return this;
        }

        public boolean hasPackageCode() { 
          if (this.packageCode == null)
            return false;
          for (CodeableConcept item : this.packageCode)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public CodeableConcept addPackageCode() { //3
          CodeableConcept t = new CodeableConcept();
          if (this.packageCode == null)
            this.packageCode = new ArrayList<CodeableConcept>();
          this.packageCode.add(t);
          return t;
        }

        public AccountDiagnosisComponent addPackageCode(CodeableConcept t) { //3
          if (t == null)
            return this;
          if (this.packageCode == null)
            this.packageCode = new ArrayList<CodeableConcept>();
          this.packageCode.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #packageCode}, creating it if it does not already exist {3}
         */
        public CodeableConcept getPackageCodeFirstRep() { 
          if (getPackageCode().isEmpty()) {
            addPackageCode();
          }
          return getPackageCode().get(0);
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("sequence", "positiveInt", "Ranking of the diagnosis (for each type).", 0, 1, sequence));
          children.add(new Property("condition", "CodeableReference(Condition)", "The diagnosis relevant to the account.", 0, 1, condition));
          children.add(new Property("dateOfDiagnosis", "dateTime", "Ranking of the diagnosis (for each type).", 0, 1, dateOfDiagnosis));
          children.add(new Property("type", "CodeableConcept", "Type that this diagnosis has relevant to the account (e.g. admission, billing, discharge …).", 0, java.lang.Integer.MAX_VALUE, type));
          children.add(new Property("onAdmission", "boolean", "Was the Diagnosis present on Admission in the related Encounter.", 0, 1, onAdmission));
          children.add(new Property("packageCode", "CodeableConcept", "The package code can be used to group diagnoses that may be priced or delivered as a single product. Such as DRGs.", 0, java.lang.Integer.MAX_VALUE, packageCode));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 1349547969: /*sequence*/  return new Property("sequence", "positiveInt", "Ranking of the diagnosis (for each type).", 0, 1, sequence);
          case -861311717: /*condition*/  return new Property("condition", "CodeableReference(Condition)", "The diagnosis relevant to the account.", 0, 1, condition);
          case -774562228: /*dateOfDiagnosis*/  return new Property("dateOfDiagnosis", "dateTime", "Ranking of the diagnosis (for each type).", 0, 1, dateOfDiagnosis);
          case 3575610: /*type*/  return new Property("type", "CodeableConcept", "Type that this diagnosis has relevant to the account (e.g. admission, billing, discharge …).", 0, java.lang.Integer.MAX_VALUE, type);
          case -3386134: /*onAdmission*/  return new Property("onAdmission", "boolean", "Was the Diagnosis present on Admission in the related Encounter.", 0, 1, onAdmission);
          case 908444499: /*packageCode*/  return new Property("packageCode", "CodeableConcept", "The package code can be used to group diagnoses that may be priced or delivered as a single product. Such as DRGs.", 0, java.lang.Integer.MAX_VALUE, packageCode);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 1349547969: /*sequence*/ return this.sequence == null ? new Base[0] : new Base[] {this.sequence}; // PositiveIntType
        case -861311717: /*condition*/ return this.condition == null ? new Base[0] : new Base[] {this.condition}; // CodeableReference
        case -774562228: /*dateOfDiagnosis*/ return this.dateOfDiagnosis == null ? new Base[0] : new Base[] {this.dateOfDiagnosis}; // DateTimeType
        case 3575610: /*type*/ return this.type == null ? new Base[0] : this.type.toArray(new Base[this.type.size()]); // CodeableConcept
        case -3386134: /*onAdmission*/ return this.onAdmission == null ? new Base[0] : new Base[] {this.onAdmission}; // BooleanType
        case 908444499: /*packageCode*/ return this.packageCode == null ? new Base[0] : this.packageCode.toArray(new Base[this.packageCode.size()]); // CodeableConcept
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 1349547969: // sequence
          this.sequence = TypeConvertor.castToPositiveInt(value); // PositiveIntType
          return value;
        case -861311717: // condition
          this.condition = TypeConvertor.castToCodeableReference(value); // CodeableReference
          return value;
        case -774562228: // dateOfDiagnosis
          this.dateOfDiagnosis = TypeConvertor.castToDateTime(value); // DateTimeType
          return value;
        case 3575610: // type
          this.getType().add(TypeConvertor.castToCodeableConcept(value)); // CodeableConcept
          return value;
        case -3386134: // onAdmission
          this.onAdmission = TypeConvertor.castToBoolean(value); // BooleanType
          return value;
        case 908444499: // packageCode
          this.getPackageCode().add(TypeConvertor.castToCodeableConcept(value)); // CodeableConcept
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("sequence")) {
          this.sequence = TypeConvertor.castToPositiveInt(value); // PositiveIntType
        } else if (name.equals("condition")) {
          this.condition = TypeConvertor.castToCodeableReference(value); // CodeableReference
        } else if (name.equals("dateOfDiagnosis")) {
          this.dateOfDiagnosis = TypeConvertor.castToDateTime(value); // DateTimeType
        } else if (name.equals("type")) {
          this.getType().add(TypeConvertor.castToCodeableConcept(value));
        } else if (name.equals("onAdmission")) {
          this.onAdmission = TypeConvertor.castToBoolean(value); // BooleanType
        } else if (name.equals("packageCode")) {
          this.getPackageCode().add(TypeConvertor.castToCodeableConcept(value));
        } else
          return super.setProperty(name, value);
        return value;
      }

  @Override
  public void removeChild(String name, Base value) throws FHIRException {
        if (name.equals("sequence")) {
          this.sequence = null;
        } else if (name.equals("condition")) {
          this.condition = null;
        } else if (name.equals("dateOfDiagnosis")) {
          this.dateOfDiagnosis = null;
        } else if (name.equals("type")) {
          this.getType().remove(value);
        } else if (name.equals("onAdmission")) {
          this.onAdmission = null;
        } else if (name.equals("packageCode")) {
          this.getPackageCode().remove(value);
        } else
          super.removeChild(name, value);
        
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 1349547969:  return getSequenceElement();
        case -861311717:  return getCondition();
        case -774562228:  return getDateOfDiagnosisElement();
        case 3575610:  return addType(); 
        case -3386134:  return getOnAdmissionElement();
        case 908444499:  return addPackageCode(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 1349547969: /*sequence*/ return new String[] {"positiveInt"};
        case -861311717: /*condition*/ return new String[] {"CodeableReference"};
        case -774562228: /*dateOfDiagnosis*/ return new String[] {"dateTime"};
        case 3575610: /*type*/ return new String[] {"CodeableConcept"};
        case -3386134: /*onAdmission*/ return new String[] {"boolean"};
        case 908444499: /*packageCode*/ return new String[] {"CodeableConcept"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("sequence")) {
          throw new FHIRException("Cannot call addChild on a singleton property Account.diagnosis.sequence");
        }
        else if (name.equals("condition")) {
          this.condition = new CodeableReference();
          return this.condition;
        }
        else if (name.equals("dateOfDiagnosis")) {
          throw new FHIRException("Cannot call addChild on a singleton property Account.diagnosis.dateOfDiagnosis");
        }
        else if (name.equals("type")) {
          return addType();
        }
        else if (name.equals("onAdmission")) {
          throw new FHIRException("Cannot call addChild on a singleton property Account.diagnosis.onAdmission");
        }
        else if (name.equals("packageCode")) {
          return addPackageCode();
        }
        else
          return super.addChild(name);
      }

      public AccountDiagnosisComponent copy() {
        AccountDiagnosisComponent dst = new AccountDiagnosisComponent();
        copyValues(dst);
        return dst;
      }

      public void copyValues(AccountDiagnosisComponent dst) {
        super.copyValues(dst);
        dst.sequence = sequence == null ? null : sequence.copy();
        dst.condition = condition == null ? null : condition.copy();
        dst.dateOfDiagnosis = dateOfDiagnosis == null ? null : dateOfDiagnosis.copy();
        if (type != null) {
          dst.type = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : type)
            dst.type.add(i.copy());
        };
        dst.onAdmission = onAdmission == null ? null : onAdmission.copy();
        if (packageCode != null) {
          dst.packageCode = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : packageCode)
            dst.packageCode.add(i.copy());
        };
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof AccountDiagnosisComponent))
          return false;
        AccountDiagnosisComponent o = (AccountDiagnosisComponent) other_;
        return compareDeep(sequence, o.sequence, true) && compareDeep(condition, o.condition, true) && compareDeep(dateOfDiagnosis, o.dateOfDiagnosis, true)
           && compareDeep(type, o.type, true) && compareDeep(onAdmission, o.onAdmission, true) && compareDeep(packageCode, o.packageCode, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof AccountDiagnosisComponent))
          return false;
        AccountDiagnosisComponent o = (AccountDiagnosisComponent) other_;
        return compareValues(sequence, o.sequence, true) && compareValues(dateOfDiagnosis, o.dateOfDiagnosis, true)
           && compareValues(onAdmission, o.onAdmission, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(sequence, condition, dateOfDiagnosis
          , type, onAdmission, packageCode);
      }

  public String fhirType() {
    return "Account.diagnosis";

  }

  }

    @Block()
    public static class AccountProcedureComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Ranking of the procedure (for each type).
         */
        @Child(name = "sequence", type = {PositiveIntType.class}, order=1, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Ranking of the procedure (for each type)", formalDefinition="Ranking of the procedure (for each type)." )
        protected PositiveIntType sequence;

        /**
         * The procedure relevant to the account.
         */
        @Child(name = "code", type = {CodeableReference.class}, order=2, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The procedure relevant to the account", formalDefinition="The procedure relevant to the account." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/procedure-code")
        protected CodeableReference code;

        /**
         * Date of the procedure when using a coded procedure. If using a reference to a procedure, then the date on the procedure should be used.
         */
        @Child(name = "dateOfService", type = {DateTimeType.class}, order=3, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Date of the procedure (when coded procedure)", formalDefinition="Date of the procedure when using a coded procedure. If using a reference to a procedure, then the date on the procedure should be used." )
        protected DateTimeType dateOfService;

        /**
         * How this procedure value should be used in charging the account.
         */
        @Child(name = "type", type = {CodeableConcept.class}, order=4, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
        @Description(shortDefinition="How this procedure value should be used in charging the account", formalDefinition="How this procedure value should be used in charging the account." )
        protected List<CodeableConcept> type;

        /**
         * The package code can be used to group procedures that may be priced or delivered as a single product. Such as DRGs.
         */
        @Child(name = "packageCode", type = {CodeableConcept.class}, order=5, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
        @Description(shortDefinition="Package Code specific for billing", formalDefinition="The package code can be used to group procedures that may be priced or delivered as a single product. Such as DRGs." )
        protected List<CodeableConcept> packageCode;

        /**
         * Any devices that were associated with the procedure relevant to the account.
         */
        @Child(name = "device", type = {Device.class}, order=6, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
        @Description(shortDefinition="Any devices that were associated with the procedure", formalDefinition="Any devices that were associated with the procedure relevant to the account." )
        protected List<Reference> device;

        private static final long serialVersionUID = -797201673L;

    /**
     * Constructor
     */
      public AccountProcedureComponent() {
        super();
      }

    /**
     * Constructor
     */
      public AccountProcedureComponent(CodeableReference code) {
        super();
        this.setCode(code);
      }

        /**
         * @return {@link #sequence} (Ranking of the procedure (for each type).). This is the underlying object with id, value and extensions. The accessor "getSequence" gives direct access to the value
         */
        public PositiveIntType getSequenceElement() { 
          if (this.sequence == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create AccountProcedureComponent.sequence");
            else if (Configuration.doAutoCreate())
              this.sequence = new PositiveIntType(); // bb
          return this.sequence;
        }

        public boolean hasSequenceElement() { 
          return this.sequence != null && !this.sequence.isEmpty();
        }

        public boolean hasSequence() { 
          return this.sequence != null && !this.sequence.isEmpty();
        }

        /**
         * @param value {@link #sequence} (Ranking of the procedure (for each type).). This is the underlying object with id, value and extensions. The accessor "getSequence" gives direct access to the value
         */
        public AccountProcedureComponent setSequenceElement(PositiveIntType value) { 
          this.sequence = value;
          return this;
        }

        /**
         * @return Ranking of the procedure (for each type).
         */
        public int getSequence() { 
          return this.sequence == null || this.sequence.isEmpty() ? 0 : this.sequence.getValue();
        }

        /**
         * @param value Ranking of the procedure (for each type).
         */
        public AccountProcedureComponent setSequence(int value) { 
            if (this.sequence == null)
              this.sequence = new PositiveIntType();
            this.sequence.setValue(value);
          return this;
        }

        /**
         * @return {@link #code} (The procedure relevant to the account.)
         */
        public CodeableReference getCode() { 
          if (this.code == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create AccountProcedureComponent.code");
            else if (Configuration.doAutoCreate())
              this.code = new CodeableReference(); // cc
          return this.code;
        }

        public boolean hasCode() { 
          return this.code != null && !this.code.isEmpty();
        }

        /**
         * @param value {@link #code} (The procedure relevant to the account.)
         */
        public AccountProcedureComponent setCode(CodeableReference value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #dateOfService} (Date of the procedure when using a coded procedure. If using a reference to a procedure, then the date on the procedure should be used.). This is the underlying object with id, value and extensions. The accessor "getDateOfService" gives direct access to the value
         */
        public DateTimeType getDateOfServiceElement() { 
          if (this.dateOfService == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create AccountProcedureComponent.dateOfService");
            else if (Configuration.doAutoCreate())
              this.dateOfService = new DateTimeType(); // bb
          return this.dateOfService;
        }

        public boolean hasDateOfServiceElement() { 
          return this.dateOfService != null && !this.dateOfService.isEmpty();
        }

        public boolean hasDateOfService() { 
          return this.dateOfService != null && !this.dateOfService.isEmpty();
        }

        /**
         * @param value {@link #dateOfService} (Date of the procedure when using a coded procedure. If using a reference to a procedure, then the date on the procedure should be used.). This is the underlying object with id, value and extensions. The accessor "getDateOfService" gives direct access to the value
         */
        public AccountProcedureComponent setDateOfServiceElement(DateTimeType value) { 
          this.dateOfService = value;
          return this;
        }

        /**
         * @return Date of the procedure when using a coded procedure. If using a reference to a procedure, then the date on the procedure should be used.
         */
        public Date getDateOfService() { 
          return this.dateOfService == null ? null : this.dateOfService.getValue();
        }

        /**
         * @param value Date of the procedure when using a coded procedure. If using a reference to a procedure, then the date on the procedure should be used.
         */
        public AccountProcedureComponent setDateOfService(Date value) { 
          if (value == null)
            this.dateOfService = null;
          else {
            if (this.dateOfService == null)
              this.dateOfService = new DateTimeType();
            this.dateOfService.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #type} (How this procedure value should be used in charging the account.)
         */
        public List<CodeableConcept> getType() { 
          if (this.type == null)
            this.type = new ArrayList<CodeableConcept>();
          return this.type;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public AccountProcedureComponent setType(List<CodeableConcept> theType) { 
          this.type = theType;
          return this;
        }

        public boolean hasType() { 
          if (this.type == null)
            return false;
          for (CodeableConcept item : this.type)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public CodeableConcept addType() { //3
          CodeableConcept t = new CodeableConcept();
          if (this.type == null)
            this.type = new ArrayList<CodeableConcept>();
          this.type.add(t);
          return t;
        }

        public AccountProcedureComponent addType(CodeableConcept t) { //3
          if (t == null)
            return this;
          if (this.type == null)
            this.type = new ArrayList<CodeableConcept>();
          this.type.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #type}, creating it if it does not already exist {3}
         */
        public CodeableConcept getTypeFirstRep() { 
          if (getType().isEmpty()) {
            addType();
          }
          return getType().get(0);
        }

        /**
         * @return {@link #packageCode} (The package code can be used to group procedures that may be priced or delivered as a single product. Such as DRGs.)
         */
        public List<CodeableConcept> getPackageCode() { 
          if (this.packageCode == null)
            this.packageCode = new ArrayList<CodeableConcept>();
          return this.packageCode;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public AccountProcedureComponent setPackageCode(List<CodeableConcept> thePackageCode) { 
          this.packageCode = thePackageCode;
          return this;
        }

        public boolean hasPackageCode() { 
          if (this.packageCode == null)
            return false;
          for (CodeableConcept item : this.packageCode)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public CodeableConcept addPackageCode() { //3
          CodeableConcept t = new CodeableConcept();
          if (this.packageCode == null)
            this.packageCode = new ArrayList<CodeableConcept>();
          this.packageCode.add(t);
          return t;
        }

        public AccountProcedureComponent addPackageCode(CodeableConcept t) { //3
          if (t == null)
            return this;
          if (this.packageCode == null)
            this.packageCode = new ArrayList<CodeableConcept>();
          this.packageCode.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #packageCode}, creating it if it does not already exist {3}
         */
        public CodeableConcept getPackageCodeFirstRep() { 
          if (getPackageCode().isEmpty()) {
            addPackageCode();
          }
          return getPackageCode().get(0);
        }

        /**
         * @return {@link #device} (Any devices that were associated with the procedure relevant to the account.)
         */
        public List<Reference> getDevice() { 
          if (this.device == null)
            this.device = new ArrayList<Reference>();
          return this.device;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public AccountProcedureComponent setDevice(List<Reference> theDevice) { 
          this.device = theDevice;
          return this;
        }

        public boolean hasDevice() { 
          if (this.device == null)
            return false;
          for (Reference item : this.device)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public Reference addDevice() { //3
          Reference t = new Reference();
          if (this.device == null)
            this.device = new ArrayList<Reference>();
          this.device.add(t);
          return t;
        }

        public AccountProcedureComponent addDevice(Reference t) { //3
          if (t == null)
            return this;
          if (this.device == null)
            this.device = new ArrayList<Reference>();
          this.device.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #device}, creating it if it does not already exist {3}
         */
        public Reference getDeviceFirstRep() { 
          if (getDevice().isEmpty()) {
            addDevice();
          }
          return getDevice().get(0);
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("sequence", "positiveInt", "Ranking of the procedure (for each type).", 0, 1, sequence));
          children.add(new Property("code", "CodeableReference(Procedure)", "The procedure relevant to the account.", 0, 1, code));
          children.add(new Property("dateOfService", "dateTime", "Date of the procedure when using a coded procedure. If using a reference to a procedure, then the date on the procedure should be used.", 0, 1, dateOfService));
          children.add(new Property("type", "CodeableConcept", "How this procedure value should be used in charging the account.", 0, java.lang.Integer.MAX_VALUE, type));
          children.add(new Property("packageCode", "CodeableConcept", "The package code can be used to group procedures that may be priced or delivered as a single product. Such as DRGs.", 0, java.lang.Integer.MAX_VALUE, packageCode));
          children.add(new Property("device", "Reference(Device)", "Any devices that were associated with the procedure relevant to the account.", 0, java.lang.Integer.MAX_VALUE, device));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 1349547969: /*sequence*/  return new Property("sequence", "positiveInt", "Ranking of the procedure (for each type).", 0, 1, sequence);
          case 3059181: /*code*/  return new Property("code", "CodeableReference(Procedure)", "The procedure relevant to the account.", 0, 1, code);
          case -328510256: /*dateOfService*/  return new Property("dateOfService", "dateTime", "Date of the procedure when using a coded procedure. If using a reference to a procedure, then the date on the procedure should be used.", 0, 1, dateOfService);
          case 3575610: /*type*/  return new Property("type", "CodeableConcept", "How this procedure value should be used in charging the account.", 0, java.lang.Integer.MAX_VALUE, type);
          case 908444499: /*packageCode*/  return new Property("packageCode", "CodeableConcept", "The package code can be used to group procedures that may be priced or delivered as a single product. Such as DRGs.", 0, java.lang.Integer.MAX_VALUE, packageCode);
          case -1335157162: /*device*/  return new Property("device", "Reference(Device)", "Any devices that were associated with the procedure relevant to the account.", 0, java.lang.Integer.MAX_VALUE, device);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 1349547969: /*sequence*/ return this.sequence == null ? new Base[0] : new Base[] {this.sequence}; // PositiveIntType
        case 3059181: /*code*/ return this.code == null ? new Base[0] : new Base[] {this.code}; // CodeableReference
        case -328510256: /*dateOfService*/ return this.dateOfService == null ? new Base[0] : new Base[] {this.dateOfService}; // DateTimeType
        case 3575610: /*type*/ return this.type == null ? new Base[0] : this.type.toArray(new Base[this.type.size()]); // CodeableConcept
        case 908444499: /*packageCode*/ return this.packageCode == null ? new Base[0] : this.packageCode.toArray(new Base[this.packageCode.size()]); // CodeableConcept
        case -1335157162: /*device*/ return this.device == null ? new Base[0] : this.device.toArray(new Base[this.device.size()]); // Reference
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 1349547969: // sequence
          this.sequence = TypeConvertor.castToPositiveInt(value); // PositiveIntType
          return value;
        case 3059181: // code
          this.code = TypeConvertor.castToCodeableReference(value); // CodeableReference
          return value;
        case -328510256: // dateOfService
          this.dateOfService = TypeConvertor.castToDateTime(value); // DateTimeType
          return value;
        case 3575610: // type
          this.getType().add(TypeConvertor.castToCodeableConcept(value)); // CodeableConcept
          return value;
        case 908444499: // packageCode
          this.getPackageCode().add(TypeConvertor.castToCodeableConcept(value)); // CodeableConcept
          return value;
        case -1335157162: // device
          this.getDevice().add(TypeConvertor.castToReference(value)); // Reference
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("sequence")) {
          this.sequence = TypeConvertor.castToPositiveInt(value); // PositiveIntType
        } else if (name.equals("code")) {
          this.code = TypeConvertor.castToCodeableReference(value); // CodeableReference
        } else if (name.equals("dateOfService")) {
          this.dateOfService = TypeConvertor.castToDateTime(value); // DateTimeType
        } else if (name.equals("type")) {
          this.getType().add(TypeConvertor.castToCodeableConcept(value));
        } else if (name.equals("packageCode")) {
          this.getPackageCode().add(TypeConvertor.castToCodeableConcept(value));
        } else if (name.equals("device")) {
          this.getDevice().add(TypeConvertor.castToReference(value));
        } else
          return super.setProperty(name, value);
        return value;
      }

  @Override
  public void removeChild(String name, Base value) throws FHIRException {
        if (name.equals("sequence")) {
          this.sequence = null;
        } else if (name.equals("code")) {
          this.code = null;
        } else if (name.equals("dateOfService")) {
          this.dateOfService = null;
        } else if (name.equals("type")) {
          this.getType().remove(value);
        } else if (name.equals("packageCode")) {
          this.getPackageCode().remove(value);
        } else if (name.equals("device")) {
          this.getDevice().remove(value);
        } else
          super.removeChild(name, value);
        
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 1349547969:  return getSequenceElement();
        case 3059181:  return getCode();
        case -328510256:  return getDateOfServiceElement();
        case 3575610:  return addType(); 
        case 908444499:  return addPackageCode(); 
        case -1335157162:  return addDevice(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 1349547969: /*sequence*/ return new String[] {"positiveInt"};
        case 3059181: /*code*/ return new String[] {"CodeableReference"};
        case -328510256: /*dateOfService*/ return new String[] {"dateTime"};
        case 3575610: /*type*/ return new String[] {"CodeableConcept"};
        case 908444499: /*packageCode*/ return new String[] {"CodeableConcept"};
        case -1335157162: /*device*/ return new String[] {"Reference"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("sequence")) {
          throw new FHIRException("Cannot call addChild on a singleton property Account.procedure.sequence");
        }
        else if (name.equals("code")) {
          this.code = new CodeableReference();
          return this.code;
        }
        else if (name.equals("dateOfService")) {
          throw new FHIRException("Cannot call addChild on a singleton property Account.procedure.dateOfService");
        }
        else if (name.equals("type")) {
          return addType();
        }
        else if (name.equals("packageCode")) {
          return addPackageCode();
        }
        else if (name.equals("device")) {
          return addDevice();
        }
        else
          return super.addChild(name);
      }

      public AccountProcedureComponent copy() {
        AccountProcedureComponent dst = new AccountProcedureComponent();
        copyValues(dst);
        return dst;
      }

      public void copyValues(AccountProcedureComponent dst) {
        super.copyValues(dst);
        dst.sequence = sequence == null ? null : sequence.copy();
        dst.code = code == null ? null : code.copy();
        dst.dateOfService = dateOfService == null ? null : dateOfService.copy();
        if (type != null) {
          dst.type = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : type)
            dst.type.add(i.copy());
        };
        if (packageCode != null) {
          dst.packageCode = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : packageCode)
            dst.packageCode.add(i.copy());
        };
        if (device != null) {
          dst.device = new ArrayList<Reference>();
          for (Reference i : device)
            dst.device.add(i.copy());
        };
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof AccountProcedureComponent))
          return false;
        AccountProcedureComponent o = (AccountProcedureComponent) other_;
        return compareDeep(sequence, o.sequence, true) && compareDeep(code, o.code, true) && compareDeep(dateOfService, o.dateOfService, true)
           && compareDeep(type, o.type, true) && compareDeep(packageCode, o.packageCode, true) && compareDeep(device, o.device, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof AccountProcedureComponent))
          return false;
        AccountProcedureComponent o = (AccountProcedureComponent) other_;
        return compareValues(sequence, o.sequence, true) && compareValues(dateOfService, o.dateOfService, true)
          ;
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(sequence, code, dateOfService
          , type, packageCode, device);
      }

  public String fhirType() {
    return "Account.procedure";

  }

  }

    @Block()
    public static class AccountRelatedAccountComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Relationship of the associated Account.
         */
        @Child(name = "relationship", type = {CodeableConcept.class}, order=1, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Relationship of the associated Account", formalDefinition="Relationship of the associated Account." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/account-relationship")
        protected CodeableConcept relationship;

        /**
         * Reference to an associated Account.
         */
        @Child(name = "account", type = {Account.class}, order=2, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Reference to an associated Account", formalDefinition="Reference to an associated Account." )
        protected Reference account;

        private static final long serialVersionUID = 1586291361L;

    /**
     * Constructor
     */
      public AccountRelatedAccountComponent() {
        super();
      }

    /**
     * Constructor
     */
      public AccountRelatedAccountComponent(Reference account) {
        super();
        this.setAccount(account);
      }

        /**
         * @return {@link #relationship} (Relationship of the associated Account.)
         */
        public CodeableConcept getRelationship() { 
          if (this.relationship == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create AccountRelatedAccountComponent.relationship");
            else if (Configuration.doAutoCreate())
              this.relationship = new CodeableConcept(); // cc
          return this.relationship;
        }

        public boolean hasRelationship() { 
          return this.relationship != null && !this.relationship.isEmpty();
        }

        /**
         * @param value {@link #relationship} (Relationship of the associated Account.)
         */
        public AccountRelatedAccountComponent setRelationship(CodeableConcept value) { 
          this.relationship = value;
          return this;
        }

        /**
         * @return {@link #account} (Reference to an associated Account.)
         */
        public Reference getAccount() { 
          if (this.account == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create AccountRelatedAccountComponent.account");
            else if (Configuration.doAutoCreate())
              this.account = new Reference(); // cc
          return this.account;
        }

        public boolean hasAccount() { 
          return this.account != null && !this.account.isEmpty();
        }

        /**
         * @param value {@link #account} (Reference to an associated Account.)
         */
        public AccountRelatedAccountComponent setAccount(Reference value) { 
          this.account = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("relationship", "CodeableConcept", "Relationship of the associated Account.", 0, 1, relationship));
          children.add(new Property("account", "Reference(Account)", "Reference to an associated Account.", 0, 1, account));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case -261851592: /*relationship*/  return new Property("relationship", "CodeableConcept", "Relationship of the associated Account.", 0, 1, relationship);
          case -1177318867: /*account*/  return new Property("account", "Reference(Account)", "Reference to an associated Account.", 0, 1, account);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -261851592: /*relationship*/ return this.relationship == null ? new Base[0] : new Base[] {this.relationship}; // CodeableConcept
        case -1177318867: /*account*/ return this.account == null ? new Base[0] : new Base[] {this.account}; // Reference
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -261851592: // relationship
          this.relationship = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
          return value;
        case -1177318867: // account
          this.account = TypeConvertor.castToReference(value); // Reference
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("relationship")) {
          this.relationship = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("account")) {
          this.account = TypeConvertor.castToReference(value); // Reference
        } else
          return super.setProperty(name, value);
        return value;
      }

  @Override
  public void removeChild(String name, Base value) throws FHIRException {
        if (name.equals("relationship")) {
          this.relationship = null;
        } else if (name.equals("account")) {
          this.account = null;
        } else
          super.removeChild(name, value);
        
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -261851592:  return getRelationship();
        case -1177318867:  return getAccount();
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -261851592: /*relationship*/ return new String[] {"CodeableConcept"};
        case -1177318867: /*account*/ return new String[] {"Reference"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("relationship")) {
          this.relationship = new CodeableConcept();
          return this.relationship;
        }
        else if (name.equals("account")) {
          this.account = new Reference();
          return this.account;
        }
        else
          return super.addChild(name);
      }

      public AccountRelatedAccountComponent copy() {
        AccountRelatedAccountComponent dst = new AccountRelatedAccountComponent();
        copyValues(dst);
        return dst;
      }

      public void copyValues(AccountRelatedAccountComponent dst) {
        super.copyValues(dst);
        dst.relationship = relationship == null ? null : relationship.copy();
        dst.account = account == null ? null : account.copy();
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof AccountRelatedAccountComponent))
          return false;
        AccountRelatedAccountComponent o = (AccountRelatedAccountComponent) other_;
        return compareDeep(relationship, o.relationship, true) && compareDeep(account, o.account, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof AccountRelatedAccountComponent))
          return false;
        AccountRelatedAccountComponent o = (AccountRelatedAccountComponent) other_;
        return true;
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(relationship, account);
      }

  public String fhirType() {
    return "Account.relatedAccount";

  }

  }

    @Block()
    public static class AccountBalanceComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * Who is expected to pay this part of the balance.
         */
        @Child(name = "aggregate", type = {CodeableConcept.class}, order=1, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Who is expected to pay this part of the balance", formalDefinition="Who is expected to pay this part of the balance." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/account-aggregate")
        protected CodeableConcept aggregate;

        /**
         * The term of the account balances - The balance value is the amount that was outstanding for this age.
         */
        @Child(name = "term", type = {CodeableConcept.class}, order=2, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="current | 30 | 60 | 90 | 120", formalDefinition="The term of the account balances - The balance value is the amount that was outstanding for this age." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/account-balance-term")
        protected CodeableConcept term;

        /**
         * The amount is only an estimated value - this is likely common for `current` term balances, but not with known terms (that were generated by a backend process).
         */
        @Child(name = "estimate", type = {BooleanType.class}, order=3, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Estimated balance", formalDefinition="The amount is only an estimated value - this is likely common for `current` term balances, but not with known terms (that were generated by a backend process)." )
        protected BooleanType estimate;

        /**
         * The actual balance value calculated for the age defined in the term property.
         */
        @Child(name = "amount", type = {Money.class}, order=4, min=1, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Calculated amount", formalDefinition="The actual balance value calculated for the age defined in the term property." )
        protected Money amount;

        private static final long serialVersionUID = -338990145L;

    /**
     * Constructor
     */
      public AccountBalanceComponent() {
        super();
      }

    /**
     * Constructor
     */
      public AccountBalanceComponent(Money amount) {
        super();
        this.setAmount(amount);
      }

        /**
         * @return {@link #aggregate} (Who is expected to pay this part of the balance.)
         */
        public CodeableConcept getAggregate() { 
          if (this.aggregate == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create AccountBalanceComponent.aggregate");
            else if (Configuration.doAutoCreate())
              this.aggregate = new CodeableConcept(); // cc
          return this.aggregate;
        }

        public boolean hasAggregate() { 
          return this.aggregate != null && !this.aggregate.isEmpty();
        }

        /**
         * @param value {@link #aggregate} (Who is expected to pay this part of the balance.)
         */
        public AccountBalanceComponent setAggregate(CodeableConcept value) { 
          this.aggregate = value;
          return this;
        }

        /**
         * @return {@link #term} (The term of the account balances - The balance value is the amount that was outstanding for this age.)
         */
        public CodeableConcept getTerm() { 
          if (this.term == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create AccountBalanceComponent.term");
            else if (Configuration.doAutoCreate())
              this.term = new CodeableConcept(); // cc
          return this.term;
        }

        public boolean hasTerm() { 
          return this.term != null && !this.term.isEmpty();
        }

        /**
         * @param value {@link #term} (The term of the account balances - The balance value is the amount that was outstanding for this age.)
         */
        public AccountBalanceComponent setTerm(CodeableConcept value) { 
          this.term = value;
          return this;
        }

        /**
         * @return {@link #estimate} (The amount is only an estimated value - this is likely common for `current` term balances, but not with known terms (that were generated by a backend process).). This is the underlying object with id, value and extensions. The accessor "getEstimate" gives direct access to the value
         */
        public BooleanType getEstimateElement() { 
          if (this.estimate == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create AccountBalanceComponent.estimate");
            else if (Configuration.doAutoCreate())
              this.estimate = new BooleanType(); // bb
          return this.estimate;
        }

        public boolean hasEstimateElement() { 
          return this.estimate != null && !this.estimate.isEmpty();
        }

        public boolean hasEstimate() { 
          return this.estimate != null && !this.estimate.isEmpty();
        }

        /**
         * @param value {@link #estimate} (The amount is only an estimated value - this is likely common for `current` term balances, but not with known terms (that were generated by a backend process).). This is the underlying object with id, value and extensions. The accessor "getEstimate" gives direct access to the value
         */
        public AccountBalanceComponent setEstimateElement(BooleanType value) { 
          this.estimate = value;
          return this;
        }

        /**
         * @return The amount is only an estimated value - this is likely common for `current` term balances, but not with known terms (that were generated by a backend process).
         */
        public boolean getEstimate() { 
          return this.estimate == null || this.estimate.isEmpty() ? false : this.estimate.getValue();
        }

        /**
         * @param value The amount is only an estimated value - this is likely common for `current` term balances, but not with known terms (that were generated by a backend process).
         */
        public AccountBalanceComponent setEstimate(boolean value) { 
            if (this.estimate == null)
              this.estimate = new BooleanType();
            this.estimate.setValue(value);
          return this;
        }

        /**
         * @return {@link #amount} (The actual balance value calculated for the age defined in the term property.)
         */
        public Money getAmount() { 
          if (this.amount == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create AccountBalanceComponent.amount");
            else if (Configuration.doAutoCreate())
              this.amount = new Money(); // cc
          return this.amount;
        }

        public boolean hasAmount() { 
          return this.amount != null && !this.amount.isEmpty();
        }

        /**
         * @param value {@link #amount} (The actual balance value calculated for the age defined in the term property.)
         */
        public AccountBalanceComponent setAmount(Money value) { 
          this.amount = value;
          return this;
        }

        protected void listChildren(List<Property> children) {
          super.listChildren(children);
          children.add(new Property("aggregate", "CodeableConcept", "Who is expected to pay this part of the balance.", 0, 1, aggregate));
          children.add(new Property("term", "CodeableConcept", "The term of the account balances - The balance value is the amount that was outstanding for this age.", 0, 1, term));
          children.add(new Property("estimate", "boolean", "The amount is only an estimated value - this is likely common for `current` term balances, but not with known terms (that were generated by a backend process).", 0, 1, estimate));
          children.add(new Property("amount", "Money", "The actual balance value calculated for the age defined in the term property.", 0, 1, amount));
        }

        @Override
        public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
          switch (_hash) {
          case 175177151: /*aggregate*/  return new Property("aggregate", "CodeableConcept", "Who is expected to pay this part of the balance.", 0, 1, aggregate);
          case 3556460: /*term*/  return new Property("term", "CodeableConcept", "The term of the account balances - The balance value is the amount that was outstanding for this age.", 0, 1, term);
          case -1959779032: /*estimate*/  return new Property("estimate", "boolean", "The amount is only an estimated value - this is likely common for `current` term balances, but not with known terms (that were generated by a backend process).", 0, 1, estimate);
          case -1413853096: /*amount*/  return new Property("amount", "Money", "The actual balance value calculated for the age defined in the term property.", 0, 1, amount);
          default: return super.getNamedProperty(_hash, _name, _checkValid);
          }

        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 175177151: /*aggregate*/ return this.aggregate == null ? new Base[0] : new Base[] {this.aggregate}; // CodeableConcept
        case 3556460: /*term*/ return this.term == null ? new Base[0] : new Base[] {this.term}; // CodeableConcept
        case -1959779032: /*estimate*/ return this.estimate == null ? new Base[0] : new Base[] {this.estimate}; // BooleanType
        case -1413853096: /*amount*/ return this.amount == null ? new Base[0] : new Base[] {this.amount}; // Money
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 175177151: // aggregate
          this.aggregate = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
          return value;
        case 3556460: // term
          this.term = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
          return value;
        case -1959779032: // estimate
          this.estimate = TypeConvertor.castToBoolean(value); // BooleanType
          return value;
        case -1413853096: // amount
          this.amount = TypeConvertor.castToMoney(value); // Money
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("aggregate")) {
          this.aggregate = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("term")) {
          this.term = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("estimate")) {
          this.estimate = TypeConvertor.castToBoolean(value); // BooleanType
        } else if (name.equals("amount")) {
          this.amount = TypeConvertor.castToMoney(value); // Money
        } else
          return super.setProperty(name, value);
        return value;
      }

  @Override
  public void removeChild(String name, Base value) throws FHIRException {
        if (name.equals("aggregate")) {
          this.aggregate = null;
        } else if (name.equals("term")) {
          this.term = null;
        } else if (name.equals("estimate")) {
          this.estimate = null;
        } else if (name.equals("amount")) {
          this.amount = null;
        } else
          super.removeChild(name, value);
        
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 175177151:  return getAggregate();
        case 3556460:  return getTerm();
        case -1959779032:  return getEstimateElement();
        case -1413853096:  return getAmount();
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 175177151: /*aggregate*/ return new String[] {"CodeableConcept"};
        case 3556460: /*term*/ return new String[] {"CodeableConcept"};
        case -1959779032: /*estimate*/ return new String[] {"boolean"};
        case -1413853096: /*amount*/ return new String[] {"Money"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("aggregate")) {
          this.aggregate = new CodeableConcept();
          return this.aggregate;
        }
        else if (name.equals("term")) {
          this.term = new CodeableConcept();
          return this.term;
        }
        else if (name.equals("estimate")) {
          throw new FHIRException("Cannot call addChild on a singleton property Account.balance.estimate");
        }
        else if (name.equals("amount")) {
          this.amount = new Money();
          return this.amount;
        }
        else
          return super.addChild(name);
      }

      public AccountBalanceComponent copy() {
        AccountBalanceComponent dst = new AccountBalanceComponent();
        copyValues(dst);
        return dst;
      }

      public void copyValues(AccountBalanceComponent dst) {
        super.copyValues(dst);
        dst.aggregate = aggregate == null ? null : aggregate.copy();
        dst.term = term == null ? null : term.copy();
        dst.estimate = estimate == null ? null : estimate.copy();
        dst.amount = amount == null ? null : amount.copy();
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof AccountBalanceComponent))
          return false;
        AccountBalanceComponent o = (AccountBalanceComponent) other_;
        return compareDeep(aggregate, o.aggregate, true) && compareDeep(term, o.term, true) && compareDeep(estimate, o.estimate, true)
           && compareDeep(amount, o.amount, true);
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof AccountBalanceComponent))
          return false;
        AccountBalanceComponent o = (AccountBalanceComponent) other_;
        return compareValues(estimate, o.estimate, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(aggregate, term, estimate
          , amount);
      }

  public String fhirType() {
    return "Account.balance";

  }

  }

    /**
     * Unique identifier used to reference the account.  Might or might not be intended for human use (e.g. credit card number).
     */
    @Child(name = "identifier", type = {Identifier.class}, order=0, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Account number", formalDefinition="Unique identifier used to reference the account.  Might or might not be intended for human use (e.g. credit card number)." )
    protected List<Identifier> identifier;

    /**
     * Indicates whether the account is presently used/usable or not.
     */
    @Child(name = "status", type = {CodeType.class}, order=1, min=1, max=1, modifier=true, summary=true)
    @Description(shortDefinition="active | inactive | entered-in-error | on-hold | unknown", formalDefinition="Indicates whether the account is presently used/usable or not." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/account-status")
    protected Enumeration<AccountStatus> status;

    /**
     * The BillingStatus tracks the lifecycle of the account through the billing process. It indicates how transactions are treated when they are allocated to the account.
     */
    @Child(name = "billingStatus", type = {CodeableConcept.class}, order=2, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Tracks the lifecycle of the account through the billing process", formalDefinition="The BillingStatus tracks the lifecycle of the account through the billing process. It indicates how transactions are treated when they are allocated to the account." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/account-billing-status")
    protected CodeableConcept billingStatus;

    /**
     * Categorizes the account for reporting and searching purposes.
     */
    @Child(name = "type", type = {CodeableConcept.class}, order=3, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="E.g. patient, expense, depreciation", formalDefinition="Categorizes the account for reporting and searching purposes." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/account-type")
    protected CodeableConcept type;

    /**
     * Name used for the account when displaying it to humans in reports, etc.
     */
    @Child(name = "name", type = {StringType.class}, order=4, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Human-readable label", formalDefinition="Name used for the account when displaying it to humans in reports, etc." )
    protected StringType name;

    /**
     * Identifies the entity which incurs the expenses. While the immediate recipients of services or goods might be entities related to the subject, the expenses were ultimately incurred by the subject of the Account.
     */
    @Child(name = "subject", type = {Patient.class, Device.class, Practitioner.class, PractitionerRole.class, Location.class, HealthcareService.class, Organization.class}, order=5, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="The entity that caused the expenses", formalDefinition="Identifies the entity which incurs the expenses. While the immediate recipients of services or goods might be entities related to the subject, the expenses were ultimately incurred by the subject of the Account." )
    protected List<Reference> subject;

    /**
     * The date range of services associated with this account.
     */
    @Child(name = "servicePeriod", type = {Period.class}, order=6, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Transaction window", formalDefinition="The date range of services associated with this account." )
    protected Period servicePeriod;

    /**
     * The party(s) that are responsible for covering the payment of this account, and what order should they be applied to the account.
     */
    @Child(name = "coverage", type = {}, order=7, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="The party(s) that are responsible for covering the payment of this account, and what order should they be applied to the account", formalDefinition="The party(s) that are responsible for covering the payment of this account, and what order should they be applied to the account." )
    protected List<CoverageComponent> coverage;

    /**
     * Indicates the service area, hospital, department, etc. with responsibility for managing the Account.
     */
    @Child(name = "owner", type = {Organization.class}, order=8, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Entity managing the Account", formalDefinition="Indicates the service area, hospital, department, etc. with responsibility for managing the Account." )
    protected Reference owner;

    /**
     * Provides additional information about what the account tracks and how it is used.
     */
    @Child(name = "description", type = {MarkdownType.class}, order=9, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Explanation of purpose/use", formalDefinition="Provides additional information about what the account tracks and how it is used." )
    protected MarkdownType description;

    /**
     * The parties responsible for balancing the account if other payment options fall short.
     */
    @Child(name = "guarantor", type = {}, order=10, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="The parties ultimately responsible for balancing the Account", formalDefinition="The parties responsible for balancing the account if other payment options fall short." )
    protected List<GuarantorComponent> guarantor;

    /**
     * When using an account for billing a specific Encounter the set of diagnoses that are relevant for billing are stored here on the account where they are able to be sequenced appropriately prior to processing to produce claim(s).
     */
    @Child(name = "diagnosis", type = {}, order=11, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="The list of diagnoses relevant to this account", formalDefinition="When using an account for billing a specific Encounter the set of diagnoses that are relevant for billing are stored here on the account where they are able to be sequenced appropriately prior to processing to produce claim(s)." )
    protected List<AccountDiagnosisComponent> diagnosis;

    /**
     * When using an account for billing a specific Encounter the set of procedures that are relevant for billing are stored here on the account where they are able to be sequenced appropriately prior to processing to produce claim(s).
     */
    @Child(name = "procedure", type = {}, order=12, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="The list of procedures relevant to this account", formalDefinition="When using an account for billing a specific Encounter the set of procedures that are relevant for billing are stored here on the account where they are able to be sequenced appropriately prior to processing to produce claim(s)." )
    protected List<AccountProcedureComponent> procedure;

    /**
     * Other associated accounts related to this account.
     */
    @Child(name = "relatedAccount", type = {}, order=13, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Other associated accounts related to this account", formalDefinition="Other associated accounts related to this account." )
    protected List<AccountRelatedAccountComponent> relatedAccount;

    /**
     * The default currency for the account.
     */
    @Child(name = "currency", type = {CodeableConcept.class}, order=14, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="The base or default currency", formalDefinition="The default currency for the account." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/currencies")
    protected CodeableConcept currency;

    /**
     * The calculated account balances - these are calculated and processed by the finance system.The balances with a `term` that is not current are usually generated/updated by an invoicing or similar process.
     */
    @Child(name = "balance", type = {}, order=15, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Calculated account balance(s)", formalDefinition="The calculated account balances - these are calculated and processed by the finance system.\r\rThe balances with a `term` that is not current are usually generated/updated by an invoicing or similar process." )
    protected List<AccountBalanceComponent> balance;

    /**
     * Time the balance amount was calculated.
     */
    @Child(name = "calculatedAt", type = {InstantType.class}, order=16, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="Time the balance amount was calculated", formalDefinition="Time the balance amount was calculated." )
    protected InstantType calculatedAt;

    private static final long serialVersionUID = -924752626L;

  /**
   * Constructor
   */
    public Account() {
      super();
    }

  /**
   * Constructor
   */
    public Account(AccountStatus status) {
      super();
      this.setStatus(status);
    }

    /**
     * @return {@link #identifier} (Unique identifier used to reference the account.  Might or might not be intended for human use (e.g. credit card number).)
     */
    public List<Identifier> getIdentifier() { 
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      return this.identifier;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Account setIdentifier(List<Identifier> theIdentifier) { 
      this.identifier = theIdentifier;
      return this;
    }

    public boolean hasIdentifier() { 
      if (this.identifier == null)
        return false;
      for (Identifier item : this.identifier)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      this.identifier.add(t);
      return t;
    }

    public Account addIdentifier(Identifier t) { //3
      if (t == null)
        return this;
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      this.identifier.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #identifier}, creating it if it does not already exist {3}
     */
    public Identifier getIdentifierFirstRep() { 
      if (getIdentifier().isEmpty()) {
        addIdentifier();
      }
      return getIdentifier().get(0);
    }

    /**
     * @return {@link #status} (Indicates whether the account is presently used/usable or not.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<AccountStatus> getStatusElement() { 
      if (this.status == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Account.status");
        else if (Configuration.doAutoCreate())
          this.status = new Enumeration<AccountStatus>(new AccountStatusEnumFactory()); // bb
      return this.status;
    }

    public boolean hasStatusElement() { 
      return this.status != null && !this.status.isEmpty();
    }

    public boolean hasStatus() { 
      return this.status != null && !this.status.isEmpty();
    }

    /**
     * @param value {@link #status} (Indicates whether the account is presently used/usable or not.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Account setStatusElement(Enumeration<AccountStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Indicates whether the account is presently used/usable or not.
     */
    public AccountStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Indicates whether the account is presently used/usable or not.
     */
    public Account setStatus(AccountStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<AccountStatus>(new AccountStatusEnumFactory());
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #billingStatus} (The BillingStatus tracks the lifecycle of the account through the billing process. It indicates how transactions are treated when they are allocated to the account.)
     */
    public CodeableConcept getBillingStatus() { 
      if (this.billingStatus == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Account.billingStatus");
        else if (Configuration.doAutoCreate())
          this.billingStatus = new CodeableConcept(); // cc
      return this.billingStatus;
    }

    public boolean hasBillingStatus() { 
      return this.billingStatus != null && !this.billingStatus.isEmpty();
    }

    /**
     * @param value {@link #billingStatus} (The BillingStatus tracks the lifecycle of the account through the billing process. It indicates how transactions are treated when they are allocated to the account.)
     */
    public Account setBillingStatus(CodeableConcept value) { 
      this.billingStatus = value;
      return this;
    }

    /**
     * @return {@link #type} (Categorizes the account for reporting and searching purposes.)
     */
    public CodeableConcept getType() { 
      if (this.type == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Account.type");
        else if (Configuration.doAutoCreate())
          this.type = new CodeableConcept(); // cc
      return this.type;
    }

    public boolean hasType() { 
      return this.type != null && !this.type.isEmpty();
    }

    /**
     * @param value {@link #type} (Categorizes the account for reporting and searching purposes.)
     */
    public Account setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #name} (Name used for the account when displaying it to humans in reports, etc.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public StringType getNameElement() { 
      if (this.name == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Account.name");
        else if (Configuration.doAutoCreate())
          this.name = new StringType(); // bb
      return this.name;
    }

    public boolean hasNameElement() { 
      return this.name != null && !this.name.isEmpty();
    }

    public boolean hasName() { 
      return this.name != null && !this.name.isEmpty();
    }

    /**
     * @param value {@link #name} (Name used for the account when displaying it to humans in reports, etc.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public Account setNameElement(StringType value) { 
      this.name = value;
      return this;
    }

    /**
     * @return Name used for the account when displaying it to humans in reports, etc.
     */
    public String getName() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value Name used for the account when displaying it to humans in reports, etc.
     */
    public Account setName(String value) { 
      if (Utilities.noString(value))
        this.name = null;
      else {
        if (this.name == null)
          this.name = new StringType();
        this.name.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #subject} (Identifies the entity which incurs the expenses. While the immediate recipients of services or goods might be entities related to the subject, the expenses were ultimately incurred by the subject of the Account.)
     */
    public List<Reference> getSubject() { 
      if (this.subject == null)
        this.subject = new ArrayList<Reference>();
      return this.subject;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Account setSubject(List<Reference> theSubject) { 
      this.subject = theSubject;
      return this;
    }

    public boolean hasSubject() { 
      if (this.subject == null)
        return false;
      for (Reference item : this.subject)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Reference addSubject() { //3
      Reference t = new Reference();
      if (this.subject == null)
        this.subject = new ArrayList<Reference>();
      this.subject.add(t);
      return t;
    }

    public Account addSubject(Reference t) { //3
      if (t == null)
        return this;
      if (this.subject == null)
        this.subject = new ArrayList<Reference>();
      this.subject.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #subject}, creating it if it does not already exist {3}
     */
    public Reference getSubjectFirstRep() { 
      if (getSubject().isEmpty()) {
        addSubject();
      }
      return getSubject().get(0);
    }

    /**
     * @return {@link #servicePeriod} (The date range of services associated with this account.)
     */
    public Period getServicePeriod() { 
      if (this.servicePeriod == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Account.servicePeriod");
        else if (Configuration.doAutoCreate())
          this.servicePeriod = new Period(); // cc
      return this.servicePeriod;
    }

    public boolean hasServicePeriod() { 
      return this.servicePeriod != null && !this.servicePeriod.isEmpty();
    }

    /**
     * @param value {@link #servicePeriod} (The date range of services associated with this account.)
     */
    public Account setServicePeriod(Period value) { 
      this.servicePeriod = value;
      return this;
    }

    /**
     * @return {@link #coverage} (The party(s) that are responsible for covering the payment of this account, and what order should they be applied to the account.)
     */
    public List<CoverageComponent> getCoverage() { 
      if (this.coverage == null)
        this.coverage = new ArrayList<CoverageComponent>();
      return this.coverage;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Account setCoverage(List<CoverageComponent> theCoverage) { 
      this.coverage = theCoverage;
      return this;
    }

    public boolean hasCoverage() { 
      if (this.coverage == null)
        return false;
      for (CoverageComponent item : this.coverage)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public CoverageComponent addCoverage() { //3
      CoverageComponent t = new CoverageComponent();
      if (this.coverage == null)
        this.coverage = new ArrayList<CoverageComponent>();
      this.coverage.add(t);
      return t;
    }

    public Account addCoverage(CoverageComponent t) { //3
      if (t == null)
        return this;
      if (this.coverage == null)
        this.coverage = new ArrayList<CoverageComponent>();
      this.coverage.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #coverage}, creating it if it does not already exist {3}
     */
    public CoverageComponent getCoverageFirstRep() { 
      if (getCoverage().isEmpty()) {
        addCoverage();
      }
      return getCoverage().get(0);
    }

    /**
     * @return {@link #owner} (Indicates the service area, hospital, department, etc. with responsibility for managing the Account.)
     */
    public Reference getOwner() { 
      if (this.owner == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Account.owner");
        else if (Configuration.doAutoCreate())
          this.owner = new Reference(); // cc
      return this.owner;
    }

    public boolean hasOwner() { 
      return this.owner != null && !this.owner.isEmpty();
    }

    /**
     * @param value {@link #owner} (Indicates the service area, hospital, department, etc. with responsibility for managing the Account.)
     */
    public Account setOwner(Reference value) { 
      this.owner = value;
      return this;
    }

    /**
     * @return {@link #description} (Provides additional information about what the account tracks and how it is used.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public MarkdownType getDescriptionElement() { 
      if (this.description == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Account.description");
        else if (Configuration.doAutoCreate())
          this.description = new MarkdownType(); // bb
      return this.description;
    }

    public boolean hasDescriptionElement() { 
      return this.description != null && !this.description.isEmpty();
    }

    public boolean hasDescription() { 
      return this.description != null && !this.description.isEmpty();
    }

    /**
     * @param value {@link #description} (Provides additional information about what the account tracks and how it is used.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public Account setDescriptionElement(MarkdownType value) { 
      this.description = value;
      return this;
    }

    /**
     * @return Provides additional information about what the account tracks and how it is used.
     */
    public String getDescription() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value Provides additional information about what the account tracks and how it is used.
     */
    public Account setDescription(String value) { 
      if (Utilities.noString(value))
        this.description = null;
      else {
        if (this.description == null)
          this.description = new MarkdownType();
        this.description.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #guarantor} (The parties responsible for balancing the account if other payment options fall short.)
     */
    public List<GuarantorComponent> getGuarantor() { 
      if (this.guarantor == null)
        this.guarantor = new ArrayList<GuarantorComponent>();
      return this.guarantor;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Account setGuarantor(List<GuarantorComponent> theGuarantor) { 
      this.guarantor = theGuarantor;
      return this;
    }

    public boolean hasGuarantor() { 
      if (this.guarantor == null)
        return false;
      for (GuarantorComponent item : this.guarantor)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public GuarantorComponent addGuarantor() { //3
      GuarantorComponent t = new GuarantorComponent();
      if (this.guarantor == null)
        this.guarantor = new ArrayList<GuarantorComponent>();
      this.guarantor.add(t);
      return t;
    }

    public Account addGuarantor(GuarantorComponent t) { //3
      if (t == null)
        return this;
      if (this.guarantor == null)
        this.guarantor = new ArrayList<GuarantorComponent>();
      this.guarantor.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #guarantor}, creating it if it does not already exist {3}
     */
    public GuarantorComponent getGuarantorFirstRep() { 
      if (getGuarantor().isEmpty()) {
        addGuarantor();
      }
      return getGuarantor().get(0);
    }

    /**
     * @return {@link #diagnosis} (When using an account for billing a specific Encounter the set of diagnoses that are relevant for billing are stored here on the account where they are able to be sequenced appropriately prior to processing to produce claim(s).)
     */
    public List<AccountDiagnosisComponent> getDiagnosis() { 
      if (this.diagnosis == null)
        this.diagnosis = new ArrayList<AccountDiagnosisComponent>();
      return this.diagnosis;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Account setDiagnosis(List<AccountDiagnosisComponent> theDiagnosis) { 
      this.diagnosis = theDiagnosis;
      return this;
    }

    public boolean hasDiagnosis() { 
      if (this.diagnosis == null)
        return false;
      for (AccountDiagnosisComponent item : this.diagnosis)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public AccountDiagnosisComponent addDiagnosis() { //3
      AccountDiagnosisComponent t = new AccountDiagnosisComponent();
      if (this.diagnosis == null)
        this.diagnosis = new ArrayList<AccountDiagnosisComponent>();
      this.diagnosis.add(t);
      return t;
    }

    public Account addDiagnosis(AccountDiagnosisComponent t) { //3
      if (t == null)
        return this;
      if (this.diagnosis == null)
        this.diagnosis = new ArrayList<AccountDiagnosisComponent>();
      this.diagnosis.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #diagnosis}, creating it if it does not already exist {3}
     */
    public AccountDiagnosisComponent getDiagnosisFirstRep() { 
      if (getDiagnosis().isEmpty()) {
        addDiagnosis();
      }
      return getDiagnosis().get(0);
    }

    /**
     * @return {@link #procedure} (When using an account for billing a specific Encounter the set of procedures that are relevant for billing are stored here on the account where they are able to be sequenced appropriately prior to processing to produce claim(s).)
     */
    public List<AccountProcedureComponent> getProcedure() { 
      if (this.procedure == null)
        this.procedure = new ArrayList<AccountProcedureComponent>();
      return this.procedure;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Account setProcedure(List<AccountProcedureComponent> theProcedure) { 
      this.procedure = theProcedure;
      return this;
    }

    public boolean hasProcedure() { 
      if (this.procedure == null)
        return false;
      for (AccountProcedureComponent item : this.procedure)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public AccountProcedureComponent addProcedure() { //3
      AccountProcedureComponent t = new AccountProcedureComponent();
      if (this.procedure == null)
        this.procedure = new ArrayList<AccountProcedureComponent>();
      this.procedure.add(t);
      return t;
    }

    public Account addProcedure(AccountProcedureComponent t) { //3
      if (t == null)
        return this;
      if (this.procedure == null)
        this.procedure = new ArrayList<AccountProcedureComponent>();
      this.procedure.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #procedure}, creating it if it does not already exist {3}
     */
    public AccountProcedureComponent getProcedureFirstRep() { 
      if (getProcedure().isEmpty()) {
        addProcedure();
      }
      return getProcedure().get(0);
    }

    /**
     * @return {@link #relatedAccount} (Other associated accounts related to this account.)
     */
    public List<AccountRelatedAccountComponent> getRelatedAccount() { 
      if (this.relatedAccount == null)
        this.relatedAccount = new ArrayList<AccountRelatedAccountComponent>();
      return this.relatedAccount;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Account setRelatedAccount(List<AccountRelatedAccountComponent> theRelatedAccount) { 
      this.relatedAccount = theRelatedAccount;
      return this;
    }

    public boolean hasRelatedAccount() { 
      if (this.relatedAccount == null)
        return false;
      for (AccountRelatedAccountComponent item : this.relatedAccount)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public AccountRelatedAccountComponent addRelatedAccount() { //3
      AccountRelatedAccountComponent t = new AccountRelatedAccountComponent();
      if (this.relatedAccount == null)
        this.relatedAccount = new ArrayList<AccountRelatedAccountComponent>();
      this.relatedAccount.add(t);
      return t;
    }

    public Account addRelatedAccount(AccountRelatedAccountComponent t) { //3
      if (t == null)
        return this;
      if (this.relatedAccount == null)
        this.relatedAccount = new ArrayList<AccountRelatedAccountComponent>();
      this.relatedAccount.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #relatedAccount}, creating it if it does not already exist {3}
     */
    public AccountRelatedAccountComponent getRelatedAccountFirstRep() { 
      if (getRelatedAccount().isEmpty()) {
        addRelatedAccount();
      }
      return getRelatedAccount().get(0);
    }

    /**
     * @return {@link #currency} (The default currency for the account.)
     */
    public CodeableConcept getCurrency() { 
      if (this.currency == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Account.currency");
        else if (Configuration.doAutoCreate())
          this.currency = new CodeableConcept(); // cc
      return this.currency;
    }

    public boolean hasCurrency() { 
      return this.currency != null && !this.currency.isEmpty();
    }

    /**
     * @param value {@link #currency} (The default currency for the account.)
     */
    public Account setCurrency(CodeableConcept value) { 
      this.currency = value;
      return this;
    }

    /**
     * @return {@link #balance} (The calculated account balances - these are calculated and processed by the finance system.The balances with a `term` that is not current are usually generated/updated by an invoicing or similar process.)
     */
    public List<AccountBalanceComponent> getBalance() { 
      if (this.balance == null)
        this.balance = new ArrayList<AccountBalanceComponent>();
      return this.balance;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public Account setBalance(List<AccountBalanceComponent> theBalance) { 
      this.balance = theBalance;
      return this;
    }

    public boolean hasBalance() { 
      if (this.balance == null)
        return false;
      for (AccountBalanceComponent item : this.balance)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public AccountBalanceComponent addBalance() { //3
      AccountBalanceComponent t = new AccountBalanceComponent();
      if (this.balance == null)
        this.balance = new ArrayList<AccountBalanceComponent>();
      this.balance.add(t);
      return t;
    }

    public Account addBalance(AccountBalanceComponent t) { //3
      if (t == null)
        return this;
      if (this.balance == null)
        this.balance = new ArrayList<AccountBalanceComponent>();
      this.balance.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #balance}, creating it if it does not already exist {3}
     */
    public AccountBalanceComponent getBalanceFirstRep() { 
      if (getBalance().isEmpty()) {
        addBalance();
      }
      return getBalance().get(0);
    }

    /**
     * @return {@link #calculatedAt} (Time the balance amount was calculated.). This is the underlying object with id, value and extensions. The accessor "getCalculatedAt" gives direct access to the value
     */
    public InstantType getCalculatedAtElement() { 
      if (this.calculatedAt == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create Account.calculatedAt");
        else if (Configuration.doAutoCreate())
          this.calculatedAt = new InstantType(); // bb
      return this.calculatedAt;
    }

    public boolean hasCalculatedAtElement() { 
      return this.calculatedAt != null && !this.calculatedAt.isEmpty();
    }

    public boolean hasCalculatedAt() { 
      return this.calculatedAt != null && !this.calculatedAt.isEmpty();
    }

    /**
     * @param value {@link #calculatedAt} (Time the balance amount was calculated.). This is the underlying object with id, value and extensions. The accessor "getCalculatedAt" gives direct access to the value
     */
    public Account setCalculatedAtElement(InstantType value) { 
      this.calculatedAt = value;
      return this;
    }

    /**
     * @return Time the balance amount was calculated.
     */
    public Date getCalculatedAt() { 
      return this.calculatedAt == null ? null : this.calculatedAt.getValue();
    }

    /**
     * @param value Time the balance amount was calculated.
     */
    public Account setCalculatedAt(Date value) { 
      if (value == null)
        this.calculatedAt = null;
      else {
        if (this.calculatedAt == null)
          this.calculatedAt = new InstantType();
        this.calculatedAt.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> children) {
        super.listChildren(children);
        children.add(new Property("identifier", "Identifier", "Unique identifier used to reference the account.  Might or might not be intended for human use (e.g. credit card number).", 0, java.lang.Integer.MAX_VALUE, identifier));
        children.add(new Property("status", "code", "Indicates whether the account is presently used/usable or not.", 0, 1, status));
        children.add(new Property("billingStatus", "CodeableConcept", "The BillingStatus tracks the lifecycle of the account through the billing process. It indicates how transactions are treated when they are allocated to the account.", 0, 1, billingStatus));
        children.add(new Property("type", "CodeableConcept", "Categorizes the account for reporting and searching purposes.", 0, 1, type));
        children.add(new Property("name", "string", "Name used for the account when displaying it to humans in reports, etc.", 0, 1, name));
        children.add(new Property("subject", "Reference(Patient|Device|Practitioner|PractitionerRole|Location|HealthcareService|Organization)", "Identifies the entity which incurs the expenses. While the immediate recipients of services or goods might be entities related to the subject, the expenses were ultimately incurred by the subject of the Account.", 0, java.lang.Integer.MAX_VALUE, subject));
        children.add(new Property("servicePeriod", "Period", "The date range of services associated with this account.", 0, 1, servicePeriod));
        children.add(new Property("coverage", "", "The party(s) that are responsible for covering the payment of this account, and what order should they be applied to the account.", 0, java.lang.Integer.MAX_VALUE, coverage));
        children.add(new Property("owner", "Reference(Organization)", "Indicates the service area, hospital, department, etc. with responsibility for managing the Account.", 0, 1, owner));
        children.add(new Property("description", "markdown", "Provides additional information about what the account tracks and how it is used.", 0, 1, description));
        children.add(new Property("guarantor", "", "The parties responsible for balancing the account if other payment options fall short.", 0, java.lang.Integer.MAX_VALUE, guarantor));
        children.add(new Property("diagnosis", "", "When using an account for billing a specific Encounter the set of diagnoses that are relevant for billing are stored here on the account where they are able to be sequenced appropriately prior to processing to produce claim(s).", 0, java.lang.Integer.MAX_VALUE, diagnosis));
        children.add(new Property("procedure", "", "When using an account for billing a specific Encounter the set of procedures that are relevant for billing are stored here on the account where they are able to be sequenced appropriately prior to processing to produce claim(s).", 0, java.lang.Integer.MAX_VALUE, procedure));
        children.add(new Property("relatedAccount", "", "Other associated accounts related to this account.", 0, java.lang.Integer.MAX_VALUE, relatedAccount));
        children.add(new Property("currency", "CodeableConcept", "The default currency for the account.", 0, 1, currency));
        children.add(new Property("balance", "", "The calculated account balances - these are calculated and processed by the finance system.\r\rThe balances with a `term` that is not current are usually generated/updated by an invoicing or similar process.", 0, java.lang.Integer.MAX_VALUE, balance));
        children.add(new Property("calculatedAt", "instant", "Time the balance amount was calculated.", 0, 1, calculatedAt));
      }

      @Override
      public Property getNamedProperty(int _hash, String _name, boolean _checkValid) throws FHIRException {
        switch (_hash) {
        case -1618432855: /*identifier*/  return new Property("identifier", "Identifier", "Unique identifier used to reference the account.  Might or might not be intended for human use (e.g. credit card number).", 0, java.lang.Integer.MAX_VALUE, identifier);
        case -892481550: /*status*/  return new Property("status", "code", "Indicates whether the account is presently used/usable or not.", 0, 1, status);
        case -1524378035: /*billingStatus*/  return new Property("billingStatus", "CodeableConcept", "The BillingStatus tracks the lifecycle of the account through the billing process. It indicates how transactions are treated when they are allocated to the account.", 0, 1, billingStatus);
        case 3575610: /*type*/  return new Property("type", "CodeableConcept", "Categorizes the account for reporting and searching purposes.", 0, 1, type);
        case 3373707: /*name*/  return new Property("name", "string", "Name used for the account when displaying it to humans in reports, etc.", 0, 1, name);
        case -1867885268: /*subject*/  return new Property("subject", "Reference(Patient|Device|Practitioner|PractitionerRole|Location|HealthcareService|Organization)", "Identifies the entity which incurs the expenses. While the immediate recipients of services or goods might be entities related to the subject, the expenses were ultimately incurred by the subject of the Account.", 0, java.lang.Integer.MAX_VALUE, subject);
        case 2129104086: /*servicePeriod*/  return new Property("servicePeriod", "Period", "The date range of services associated with this account.", 0, 1, servicePeriod);
        case -351767064: /*coverage*/  return new Property("coverage", "", "The party(s) that are responsible for covering the payment of this account, and what order should they be applied to the account.", 0, java.lang.Integer.MAX_VALUE, coverage);
        case 106164915: /*owner*/  return new Property("owner", "Reference(Organization)", "Indicates the service area, hospital, department, etc. with responsibility for managing the Account.", 0, 1, owner);
        case -1724546052: /*description*/  return new Property("description", "markdown", "Provides additional information about what the account tracks and how it is used.", 0, 1, description);
        case -188629045: /*guarantor*/  return new Property("guarantor", "", "The parties responsible for balancing the account if other payment options fall short.", 0, java.lang.Integer.MAX_VALUE, guarantor);
        case 1196993265: /*diagnosis*/  return new Property("diagnosis", "", "When using an account for billing a specific Encounter the set of diagnoses that are relevant for billing are stored here on the account where they are able to be sequenced appropriately prior to processing to produce claim(s).", 0, java.lang.Integer.MAX_VALUE, diagnosis);
        case -1095204141: /*procedure*/  return new Property("procedure", "", "When using an account for billing a specific Encounter the set of procedures that are relevant for billing are stored here on the account where they are able to be sequenced appropriately prior to processing to produce claim(s).", 0, java.lang.Integer.MAX_VALUE, procedure);
        case 962039682: /*relatedAccount*/  return new Property("relatedAccount", "", "Other associated accounts related to this account.", 0, java.lang.Integer.MAX_VALUE, relatedAccount);
        case 575402001: /*currency*/  return new Property("currency", "CodeableConcept", "The default currency for the account.", 0, 1, currency);
        case -339185956: /*balance*/  return new Property("balance", "", "The calculated account balances - these are calculated and processed by the finance system.\r\rThe balances with a `term` that is not current are usually generated/updated by an invoicing or similar process.", 0, java.lang.Integer.MAX_VALUE, balance);
        case 1089469073: /*calculatedAt*/  return new Property("calculatedAt", "instant", "Time the balance amount was calculated.", 0, 1, calculatedAt);
        default: return super.getNamedProperty(_hash, _name, _checkValid);
        }

      }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return this.identifier == null ? new Base[0] : this.identifier.toArray(new Base[this.identifier.size()]); // Identifier
        case -892481550: /*status*/ return this.status == null ? new Base[0] : new Base[] {this.status}; // Enumeration<AccountStatus>
        case -1524378035: /*billingStatus*/ return this.billingStatus == null ? new Base[0] : new Base[] {this.billingStatus}; // CodeableConcept
        case 3575610: /*type*/ return this.type == null ? new Base[0] : new Base[] {this.type}; // CodeableConcept
        case 3373707: /*name*/ return this.name == null ? new Base[0] : new Base[] {this.name}; // StringType
        case -1867885268: /*subject*/ return this.subject == null ? new Base[0] : this.subject.toArray(new Base[this.subject.size()]); // Reference
        case 2129104086: /*servicePeriod*/ return this.servicePeriod == null ? new Base[0] : new Base[] {this.servicePeriod}; // Period
        case -351767064: /*coverage*/ return this.coverage == null ? new Base[0] : this.coverage.toArray(new Base[this.coverage.size()]); // CoverageComponent
        case 106164915: /*owner*/ return this.owner == null ? new Base[0] : new Base[] {this.owner}; // Reference
        case -1724546052: /*description*/ return this.description == null ? new Base[0] : new Base[] {this.description}; // MarkdownType
        case -188629045: /*guarantor*/ return this.guarantor == null ? new Base[0] : this.guarantor.toArray(new Base[this.guarantor.size()]); // GuarantorComponent
        case 1196993265: /*diagnosis*/ return this.diagnosis == null ? new Base[0] : this.diagnosis.toArray(new Base[this.diagnosis.size()]); // AccountDiagnosisComponent
        case -1095204141: /*procedure*/ return this.procedure == null ? new Base[0] : this.procedure.toArray(new Base[this.procedure.size()]); // AccountProcedureComponent
        case 962039682: /*relatedAccount*/ return this.relatedAccount == null ? new Base[0] : this.relatedAccount.toArray(new Base[this.relatedAccount.size()]); // AccountRelatedAccountComponent
        case 575402001: /*currency*/ return this.currency == null ? new Base[0] : new Base[] {this.currency}; // CodeableConcept
        case -339185956: /*balance*/ return this.balance == null ? new Base[0] : this.balance.toArray(new Base[this.balance.size()]); // AccountBalanceComponent
        case 1089469073: /*calculatedAt*/ return this.calculatedAt == null ? new Base[0] : new Base[] {this.calculatedAt}; // InstantType
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -1618432855: // identifier
          this.getIdentifier().add(TypeConvertor.castToIdentifier(value)); // Identifier
          return value;
        case -892481550: // status
          value = new AccountStatusEnumFactory().fromType(TypeConvertor.castToCode(value));
          this.status = (Enumeration) value; // Enumeration<AccountStatus>
          return value;
        case -1524378035: // billingStatus
          this.billingStatus = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
          return value;
        case 3575610: // type
          this.type = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
          return value;
        case 3373707: // name
          this.name = TypeConvertor.castToString(value); // StringType
          return value;
        case -1867885268: // subject
          this.getSubject().add(TypeConvertor.castToReference(value)); // Reference
          return value;
        case 2129104086: // servicePeriod
          this.servicePeriod = TypeConvertor.castToPeriod(value); // Period
          return value;
        case -351767064: // coverage
          this.getCoverage().add((CoverageComponent) value); // CoverageComponent
          return value;
        case 106164915: // owner
          this.owner = TypeConvertor.castToReference(value); // Reference
          return value;
        case -1724546052: // description
          this.description = TypeConvertor.castToMarkdown(value); // MarkdownType
          return value;
        case -188629045: // guarantor
          this.getGuarantor().add((GuarantorComponent) value); // GuarantorComponent
          return value;
        case 1196993265: // diagnosis
          this.getDiagnosis().add((AccountDiagnosisComponent) value); // AccountDiagnosisComponent
          return value;
        case -1095204141: // procedure
          this.getProcedure().add((AccountProcedureComponent) value); // AccountProcedureComponent
          return value;
        case 962039682: // relatedAccount
          this.getRelatedAccount().add((AccountRelatedAccountComponent) value); // AccountRelatedAccountComponent
          return value;
        case 575402001: // currency
          this.currency = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
          return value;
        case -339185956: // balance
          this.getBalance().add((AccountBalanceComponent) value); // AccountBalanceComponent
          return value;
        case 1089469073: // calculatedAt
          this.calculatedAt = TypeConvertor.castToInstant(value); // InstantType
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("identifier")) {
          this.getIdentifier().add(TypeConvertor.castToIdentifier(value));
        } else if (name.equals("status")) {
          value = new AccountStatusEnumFactory().fromType(TypeConvertor.castToCode(value));
          this.status = (Enumeration) value; // Enumeration<AccountStatus>
        } else if (name.equals("billingStatus")) {
          this.billingStatus = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("type")) {
          this.type = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("name")) {
          this.name = TypeConvertor.castToString(value); // StringType
        } else if (name.equals("subject")) {
          this.getSubject().add(TypeConvertor.castToReference(value));
        } else if (name.equals("servicePeriod")) {
          this.servicePeriod = TypeConvertor.castToPeriod(value); // Period
        } else if (name.equals("coverage")) {
          this.getCoverage().add((CoverageComponent) value);
        } else if (name.equals("owner")) {
          this.owner = TypeConvertor.castToReference(value); // Reference
        } else if (name.equals("description")) {
          this.description = TypeConvertor.castToMarkdown(value); // MarkdownType
        } else if (name.equals("guarantor")) {
          this.getGuarantor().add((GuarantorComponent) value);
        } else if (name.equals("diagnosis")) {
          this.getDiagnosis().add((AccountDiagnosisComponent) value);
        } else if (name.equals("procedure")) {
          this.getProcedure().add((AccountProcedureComponent) value);
        } else if (name.equals("relatedAccount")) {
          this.getRelatedAccount().add((AccountRelatedAccountComponent) value);
        } else if (name.equals("currency")) {
          this.currency = TypeConvertor.castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("balance")) {
          this.getBalance().add((AccountBalanceComponent) value);
        } else if (name.equals("calculatedAt")) {
          this.calculatedAt = TypeConvertor.castToInstant(value); // InstantType
        } else
          return super.setProperty(name, value);
        return value;
      }

  @Override
  public void removeChild(String name, Base value) throws FHIRException {
        if (name.equals("identifier")) {
          this.getIdentifier().remove(value);
        } else if (name.equals("status")) {
          value = new AccountStatusEnumFactory().fromType(TypeConvertor.castToCode(value));
          this.status = (Enumeration) value; // Enumeration<AccountStatus>
        } else if (name.equals("billingStatus")) {
          this.billingStatus = null;
        } else if (name.equals("type")) {
          this.type = null;
        } else if (name.equals("name")) {
          this.name = null;
        } else if (name.equals("subject")) {
          this.getSubject().remove(value);
        } else if (name.equals("servicePeriod")) {
          this.servicePeriod = null;
        } else if (name.equals("coverage")) {
          this.getCoverage().add((CoverageComponent) value);
        } else if (name.equals("owner")) {
          this.owner = null;
        } else if (name.equals("description")) {
          this.description = null;
        } else if (name.equals("guarantor")) {
          this.getGuarantor().add((GuarantorComponent) value);
        } else if (name.equals("diagnosis")) {
          this.getDiagnosis().add((AccountDiagnosisComponent) value);
        } else if (name.equals("procedure")) {
          this.getProcedure().add((AccountProcedureComponent) value);
        } else if (name.equals("relatedAccount")) {
          this.getRelatedAccount().add((AccountRelatedAccountComponent) value);
        } else if (name.equals("currency")) {
          this.currency = null;
        } else if (name.equals("balance")) {
          this.getBalance().add((AccountBalanceComponent) value);
        } else if (name.equals("calculatedAt")) {
          this.calculatedAt = null;
        } else
          super.removeChild(name, value);
        
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855:  return addIdentifier(); 
        case -892481550:  return getStatusElement();
        case -1524378035:  return getBillingStatus();
        case 3575610:  return getType();
        case 3373707:  return getNameElement();
        case -1867885268:  return addSubject(); 
        case 2129104086:  return getServicePeriod();
        case -351767064:  return addCoverage(); 
        case 106164915:  return getOwner();
        case -1724546052:  return getDescriptionElement();
        case -188629045:  return addGuarantor(); 
        case 1196993265:  return addDiagnosis(); 
        case -1095204141:  return addProcedure(); 
        case 962039682:  return addRelatedAccount(); 
        case 575402001:  return getCurrency();
        case -339185956:  return addBalance(); 
        case 1089469073:  return getCalculatedAtElement();
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return new String[] {"Identifier"};
        case -892481550: /*status*/ return new String[] {"code"};
        case -1524378035: /*billingStatus*/ return new String[] {"CodeableConcept"};
        case 3575610: /*type*/ return new String[] {"CodeableConcept"};
        case 3373707: /*name*/ return new String[] {"string"};
        case -1867885268: /*subject*/ return new String[] {"Reference"};
        case 2129104086: /*servicePeriod*/ return new String[] {"Period"};
        case -351767064: /*coverage*/ return new String[] {};
        case 106164915: /*owner*/ return new String[] {"Reference"};
        case -1724546052: /*description*/ return new String[] {"markdown"};
        case -188629045: /*guarantor*/ return new String[] {};
        case 1196993265: /*diagnosis*/ return new String[] {};
        case -1095204141: /*procedure*/ return new String[] {};
        case 962039682: /*relatedAccount*/ return new String[] {};
        case 575402001: /*currency*/ return new String[] {"CodeableConcept"};
        case -339185956: /*balance*/ return new String[] {};
        case 1089469073: /*calculatedAt*/ return new String[] {"instant"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("identifier")) {
          return addIdentifier();
        }
        else if (name.equals("status")) {
          throw new FHIRException("Cannot call addChild on a singleton property Account.status");
        }
        else if (name.equals("billingStatus")) {
          this.billingStatus = new CodeableConcept();
          return this.billingStatus;
        }
        else if (name.equals("type")) {
          this.type = new CodeableConcept();
          return this.type;
        }
        else if (name.equals("name")) {
          throw new FHIRException("Cannot call addChild on a singleton property Account.name");
        }
        else if (name.equals("subject")) {
          return addSubject();
        }
        else if (name.equals("servicePeriod")) {
          this.servicePeriod = new Period();
          return this.servicePeriod;
        }
        else if (name.equals("coverage")) {
          return addCoverage();
        }
        else if (name.equals("owner")) {
          this.owner = new Reference();
          return this.owner;
        }
        else if (name.equals("description")) {
          throw new FHIRException("Cannot call addChild on a singleton property Account.description");
        }
        else if (name.equals("guarantor")) {
          return addGuarantor();
        }
        else if (name.equals("diagnosis")) {
          return addDiagnosis();
        }
        else if (name.equals("procedure")) {
          return addProcedure();
        }
        else if (name.equals("relatedAccount")) {
          return addRelatedAccount();
        }
        else if (name.equals("currency")) {
          this.currency = new CodeableConcept();
          return this.currency;
        }
        else if (name.equals("balance")) {
          return addBalance();
        }
        else if (name.equals("calculatedAt")) {
          throw new FHIRException("Cannot call addChild on a singleton property Account.calculatedAt");
        }
        else
          return super.addChild(name);
      }

  public String fhirType() {
    return "Account";

  }

      public Account copy() {
        Account dst = new Account();
        copyValues(dst);
        return dst;
      }

      public void copyValues(Account dst) {
        super.copyValues(dst);
        if (identifier != null) {
          dst.identifier = new ArrayList<Identifier>();
          for (Identifier i : identifier)
            dst.identifier.add(i.copy());
        };
        dst.status = status == null ? null : status.copy();
        dst.billingStatus = billingStatus == null ? null : billingStatus.copy();
        dst.type = type == null ? null : type.copy();
        dst.name = name == null ? null : name.copy();
        if (subject != null) {
          dst.subject = new ArrayList<Reference>();
          for (Reference i : subject)
            dst.subject.add(i.copy());
        };
        dst.servicePeriod = servicePeriod == null ? null : servicePeriod.copy();
        if (coverage != null) {
          dst.coverage = new ArrayList<CoverageComponent>();
          for (CoverageComponent i : coverage)
            dst.coverage.add(i.copy());
        };
        dst.owner = owner == null ? null : owner.copy();
        dst.description = description == null ? null : description.copy();
        if (guarantor != null) {
          dst.guarantor = new ArrayList<GuarantorComponent>();
          for (GuarantorComponent i : guarantor)
            dst.guarantor.add(i.copy());
        };
        if (diagnosis != null) {
          dst.diagnosis = new ArrayList<AccountDiagnosisComponent>();
          for (AccountDiagnosisComponent i : diagnosis)
            dst.diagnosis.add(i.copy());
        };
        if (procedure != null) {
          dst.procedure = new ArrayList<AccountProcedureComponent>();
          for (AccountProcedureComponent i : procedure)
            dst.procedure.add(i.copy());
        };
        if (relatedAccount != null) {
          dst.relatedAccount = new ArrayList<AccountRelatedAccountComponent>();
          for (AccountRelatedAccountComponent i : relatedAccount)
            dst.relatedAccount.add(i.copy());
        };
        dst.currency = currency == null ? null : currency.copy();
        if (balance != null) {
          dst.balance = new ArrayList<AccountBalanceComponent>();
          for (AccountBalanceComponent i : balance)
            dst.balance.add(i.copy());
        };
        dst.calculatedAt = calculatedAt == null ? null : calculatedAt.copy();
      }

      protected Account typedCopy() {
        return copy();
      }

      @Override
      public boolean equalsDeep(Base other_) {
        if (!super.equalsDeep(other_))
          return false;
        if (!(other_ instanceof Account))
          return false;
        Account o = (Account) other_;
        return compareDeep(identifier, o.identifier, true) && compareDeep(status, o.status, true) && compareDeep(billingStatus, o.billingStatus, true)
           && compareDeep(type, o.type, true) && compareDeep(name, o.name, true) && compareDeep(subject, o.subject, true)
           && compareDeep(servicePeriod, o.servicePeriod, true) && compareDeep(coverage, o.coverage, true)
           && compareDeep(owner, o.owner, true) && compareDeep(description, o.description, true) && compareDeep(guarantor, o.guarantor, true)
           && compareDeep(diagnosis, o.diagnosis, true) && compareDeep(procedure, o.procedure, true) && compareDeep(relatedAccount, o.relatedAccount, true)
           && compareDeep(currency, o.currency, true) && compareDeep(balance, o.balance, true) && compareDeep(calculatedAt, o.calculatedAt, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other_) {
        if (!super.equalsShallow(other_))
          return false;
        if (!(other_ instanceof Account))
          return false;
        Account o = (Account) other_;
        return compareValues(status, o.status, true) && compareValues(name, o.name, true) && compareValues(description, o.description, true)
           && compareValues(calculatedAt, o.calculatedAt, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(identifier, status, billingStatus
          , type, name, subject, servicePeriod, coverage, owner, description, guarantor
          , diagnosis, procedure, relatedAccount, currency, balance, calculatedAt);
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Account;
   }

 /**
   * Search parameter: <b>guarantor</b>
   * <p>
   * Description: <b>The parties ultimately responsible for balancing the Account</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Account.guarantor.party</b><br>
   * </p>
   */
  @SearchParamDefinition(name="guarantor", path="Account.guarantor.party", description="The parties ultimately responsible for balancing the Account", type="reference", target={Organization.class, Patient.class, RelatedPerson.class } )
  public static final String SP_GUARANTOR = "guarantor";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>guarantor</b>
   * <p>
   * Description: <b>The parties ultimately responsible for balancing the Account</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Account.guarantor.party</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam GUARANTOR = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_GUARANTOR);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Account:guarantor</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_GUARANTOR = new ca.uhn.fhir.model.api.Include("Account:guarantor").toLocked();

 /**
   * Search parameter: <b>name</b>
   * <p>
   * Description: <b>Human-readable label</b><br>
   * Type: <b>string</b><br>
   * Path: <b>Account.name</b><br>
   * </p>
   */
  @SearchParamDefinition(name="name", path="Account.name", description="Human-readable label", type="string" )
  public static final String SP_NAME = "name";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>name</b>
   * <p>
   * Description: <b>Human-readable label</b><br>
   * Type: <b>string</b><br>
   * Path: <b>Account.name</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.StringClientParam NAME = new ca.uhn.fhir.rest.gclient.StringClientParam(SP_NAME);

 /**
   * Search parameter: <b>owner</b>
   * <p>
   * Description: <b>Entity managing the Account</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Account.owner</b><br>
   * </p>
   */
  @SearchParamDefinition(name="owner", path="Account.owner", description="Entity managing the Account", type="reference", target={Organization.class } )
  public static final String SP_OWNER = "owner";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>owner</b>
   * <p>
   * Description: <b>Entity managing the Account</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Account.owner</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam OWNER = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_OWNER);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Account:owner</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_OWNER = new ca.uhn.fhir.model.api.Include("Account:owner").toLocked();

 /**
   * Search parameter: <b>period</b>
   * <p>
   * Description: <b>Transaction window</b><br>
   * Type: <b>date</b><br>
   * Path: <b>Account.servicePeriod</b><br>
   * </p>
   */
  @SearchParamDefinition(name="period", path="Account.servicePeriod", description="Transaction window", type="date" )
  public static final String SP_PERIOD = "period";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>period</b>
   * <p>
   * Description: <b>Transaction window</b><br>
   * Type: <b>date</b><br>
   * Path: <b>Account.servicePeriod</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.DateClientParam PERIOD = new ca.uhn.fhir.rest.gclient.DateClientParam(SP_PERIOD);

 /**
   * Search parameter: <b>relatedaccount</b>
   * <p>
   * Description: <b>Parent and other related accounts</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Account.relatedAccount.account</b><br>
   * </p>
   */
  @SearchParamDefinition(name="relatedaccount", path="Account.relatedAccount.account", description="Parent and other related accounts", type="reference", target={Account.class } )
  public static final String SP_RELATEDACCOUNT = "relatedaccount";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>relatedaccount</b>
   * <p>
   * Description: <b>Parent and other related accounts</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Account.relatedAccount.account</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam RELATEDACCOUNT = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_RELATEDACCOUNT);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Account:relatedaccount</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_RELATEDACCOUNT = new ca.uhn.fhir.model.api.Include("Account:relatedaccount").toLocked();

 /**
   * Search parameter: <b>status</b>
   * <p>
   * Description: <b>active | inactive | entered-in-error | on-hold | unknown</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Account.status</b><br>
   * </p>
   */
  @SearchParamDefinition(name="status", path="Account.status", description="active | inactive | entered-in-error | on-hold | unknown", type="token" )
  public static final String SP_STATUS = "status";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>status</b>
   * <p>
   * Description: <b>active | inactive | entered-in-error | on-hold | unknown</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Account.status</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam STATUS = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_STATUS);

 /**
   * Search parameter: <b>subject</b>
   * <p>
   * Description: <b>The entity that caused the expenses</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Account.subject</b><br>
   * </p>
   */
  @SearchParamDefinition(name="subject", path="Account.subject", description="The entity that caused the expenses", type="reference", providesMembershipIn={ @ca.uhn.fhir.model.api.annotation.Compartment(name="Base FHIR compartment definition for Device"), @ca.uhn.fhir.model.api.annotation.Compartment(name="Base FHIR compartment definition for Patient"), @ca.uhn.fhir.model.api.annotation.Compartment(name="Base FHIR compartment definition for Practitioner") }, target={Device.class, HealthcareService.class, Location.class, Organization.class, Patient.class, Practitioner.class, PractitionerRole.class } )
  public static final String SP_SUBJECT = "subject";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>subject</b>
   * <p>
   * Description: <b>The entity that caused the expenses</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Account.subject</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam SUBJECT = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_SUBJECT);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Account:subject</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_SUBJECT = new ca.uhn.fhir.model.api.Include("Account:subject").toLocked();

 /**
   * Search parameter: <b>identifier</b>
   * <p>
   * Description: <b>Multiple Resources: 

* [Account](account.html): Account number
* [AdverseEvent](adverseevent.html): Business identifier for the event
* [AllergyIntolerance](allergyintolerance.html): External ids for this item
* [Appointment](appointment.html): An Identifier of the Appointment
* [AppointmentResponse](appointmentresponse.html): An Identifier in this appointment response
* [Basic](basic.html): Business identifier
* [BodyStructure](bodystructure.html): Bodystructure identifier
* [CarePlan](careplan.html): External Ids for this plan
* [CareTeam](careteam.html): External Ids for this team
* [ChargeItem](chargeitem.html): Business Identifier for item
* [Claim](claim.html): The primary identifier of the financial resource
* [ClaimResponse](claimresponse.html): The identity of the ClaimResponse
* [ClinicalImpression](clinicalimpression.html): Business identifier
* [Communication](communication.html): Unique identifier
* [CommunicationRequest](communicationrequest.html): Unique identifier
* [Composition](composition.html): Version-independent identifier for the Composition
* [Condition](condition.html): A unique identifier of the condition record
* [Consent](consent.html): Identifier for this record (external references)
* [Contract](contract.html): The identity of the contract
* [Coverage](coverage.html): The primary identifier of the insured and the coverage
* [CoverageEligibilityRequest](coverageeligibilityrequest.html): The business identifier of the Eligibility
* [CoverageEligibilityResponse](coverageeligibilityresponse.html): The business identifier
* [DetectedIssue](detectedissue.html): Unique id for the detected issue
* [DeviceRequest](devicerequest.html): Business identifier for request/order
* [DeviceUsage](deviceusage.html): Search by identifier
* [DiagnosticReport](diagnosticreport.html): An identifier for the report
* [DocumentReference](documentreference.html): Identifier of the attachment binary
* [Encounter](encounter.html): Identifier(s) by which this encounter is known
* [EnrollmentRequest](enrollmentrequest.html): The business identifier of the Enrollment
* [EpisodeOfCare](episodeofcare.html): Business Identifier(s) relevant for this EpisodeOfCare
* [ExplanationOfBenefit](explanationofbenefit.html): The business identifier of the Explanation of Benefit
* [FamilyMemberHistory](familymemberhistory.html): A search by a record identifier
* [Flag](flag.html): Business identifier
* [Goal](goal.html): External Ids for this goal
* [GuidanceResponse](guidanceresponse.html): The identifier of the guidance response
* [ImagingSelection](imagingselection.html): Identifiers for the imaging selection
* [ImagingStudy](imagingstudy.html): Identifiers for the Study, such as DICOM Study Instance UID
* [Immunization](immunization.html): Business identifier
* [ImmunizationEvaluation](immunizationevaluation.html): ID of the evaluation
* [ImmunizationRecommendation](immunizationrecommendation.html): Business identifier
* [Invoice](invoice.html): Business Identifier for item
* [List](list.html): Business identifier
* [MeasureReport](measurereport.html): External identifier of the measure report to be returned
* [Medication](medication.html): Returns medications with this external identifier
* [MedicationAdministration](medicationadministration.html): Return administrations with this external identifier
* [MedicationDispense](medicationdispense.html): Returns dispenses with this external identifier
* [MedicationRequest](medicationrequest.html): Return prescriptions with this external identifier
* [MedicationStatement](medicationstatement.html): Return statements with this external identifier
* [MolecularSequence](molecularsequence.html): The unique identity for a particular sequence
* [NutritionIntake](nutritionintake.html): Return statements with this external identifier
* [NutritionOrder](nutritionorder.html): Return nutrition orders with this external identifier
* [Observation](observation.html): The unique id for a particular observation
* [Person](person.html): A person Identifier
* [Procedure](procedure.html): A unique identifier for a procedure
* [QuestionnaireResponse](questionnaireresponse.html): The unique identifier for the questionnaire response
* [RelatedPerson](relatedperson.html): An Identifier of the RelatedPerson
* [RequestOrchestration](requestorchestration.html): External identifiers for the request orchestration
* [ResearchSubject](researchsubject.html): Business Identifier for research subject in a study
* [RiskAssessment](riskassessment.html): Unique identifier for the assessment
* [ServiceRequest](servicerequest.html): Identifiers assigned to this order
* [Specimen](specimen.html): The unique identifier associated with the specimen
* [SupplyDelivery](supplydelivery.html): External identifier
* [SupplyRequest](supplyrequest.html): Business Identifier for SupplyRequest
* [Task](task.html): Search for a task instance by its business identifier
* [VisionPrescription](visionprescription.html): Return prescriptions with this external identifier
</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Account.identifier | AdverseEvent.identifier | AllergyIntolerance.identifier | Appointment.identifier | AppointmentResponse.identifier | Basic.identifier | BodyStructure.identifier | CarePlan.identifier | CareTeam.identifier | ChargeItem.identifier | Claim.identifier | ClaimResponse.identifier | ClinicalImpression.identifier | Communication.identifier | CommunicationRequest.identifier | Composition.identifier | Condition.identifier | Consent.identifier | Contract.identifier | Coverage.identifier | CoverageEligibilityRequest.identifier | CoverageEligibilityResponse.identifier | DetectedIssue.identifier | DeviceRequest.identifier | DeviceUsage.identifier | DiagnosticReport.identifier | DocumentReference.identifier | Encounter.identifier | EnrollmentRequest.identifier | EpisodeOfCare.identifier | ExplanationOfBenefit.identifier | FamilyMemberHistory.identifier | Flag.identifier | Goal.identifier | GuidanceResponse.identifier | ImagingSelection.identifier | ImagingStudy.identifier | Immunization.identifier | ImmunizationEvaluation.identifier | ImmunizationRecommendation.identifier | Invoice.identifier | List.identifier | MeasureReport.identifier | Medication.identifier | MedicationAdministration.identifier | MedicationDispense.identifier | MedicationRequest.identifier | MedicationStatement.identifier | MolecularSequence.identifier | NutritionIntake.identifier | NutritionOrder.identifier | Observation.identifier | Person.identifier | Procedure.identifier | QuestionnaireResponse.identifier | RelatedPerson.identifier | RequestOrchestration.identifier | ResearchSubject.identifier | RiskAssessment.identifier | ServiceRequest.identifier | Specimen.identifier | SupplyDelivery.identifier | SupplyRequest.identifier | Task.identifier | VisionPrescription.identifier</b><br>
   * </p>
   */
  @SearchParamDefinition(name="identifier", path="Account.identifier | AdverseEvent.identifier | AllergyIntolerance.identifier | Appointment.identifier | AppointmentResponse.identifier | Basic.identifier | BodyStructure.identifier | CarePlan.identifier | CareTeam.identifier | ChargeItem.identifier | Claim.identifier | ClaimResponse.identifier | ClinicalImpression.identifier | Communication.identifier | CommunicationRequest.identifier | Composition.identifier | Condition.identifier | Consent.identifier | Contract.identifier | Coverage.identifier | CoverageEligibilityRequest.identifier | CoverageEligibilityResponse.identifier | DetectedIssue.identifier | DeviceRequest.identifier | DeviceUsage.identifier | DiagnosticReport.identifier | DocumentReference.identifier | Encounter.identifier | EnrollmentRequest.identifier | EpisodeOfCare.identifier | ExplanationOfBenefit.identifier | FamilyMemberHistory.identifier | Flag.identifier | Goal.identifier | GuidanceResponse.identifier | ImagingSelection.identifier | ImagingStudy.identifier | Immunization.identifier | ImmunizationEvaluation.identifier | ImmunizationRecommendation.identifier | Invoice.identifier | List.identifier | MeasureReport.identifier | Medication.identifier | MedicationAdministration.identifier | MedicationDispense.identifier | MedicationRequest.identifier | MedicationStatement.identifier | MolecularSequence.identifier | NutritionIntake.identifier | NutritionOrder.identifier | Observation.identifier | Person.identifier | Procedure.identifier | QuestionnaireResponse.identifier | RelatedPerson.identifier | RequestOrchestration.identifier | ResearchSubject.identifier | RiskAssessment.identifier | ServiceRequest.identifier | Specimen.identifier | SupplyDelivery.identifier | SupplyRequest.identifier | Task.identifier | VisionPrescription.identifier", description="Multiple Resources: \r\n\r\n* [Account](account.html): Account number\r\n* [AdverseEvent](adverseevent.html): Business identifier for the event\r\n* [AllergyIntolerance](allergyintolerance.html): External ids for this item\r\n* [Appointment](appointment.html): An Identifier of the Appointment\r\n* [AppointmentResponse](appointmentresponse.html): An Identifier in this appointment response\r\n* [Basic](basic.html): Business identifier\r\n* [BodyStructure](bodystructure.html): Bodystructure identifier\r\n* [CarePlan](careplan.html): External Ids for this plan\r\n* [CareTeam](careteam.html): External Ids for this team\r\n* [ChargeItem](chargeitem.html): Business Identifier for item\r\n* [Claim](claim.html): The primary identifier of the financial resource\r\n* [ClaimResponse](claimresponse.html): The identity of the ClaimResponse\r\n* [ClinicalImpression](clinicalimpression.html): Business identifier\r\n* [Communication](communication.html): Unique identifier\r\n* [CommunicationRequest](communicationrequest.html): Unique identifier\r\n* [Composition](composition.html): Version-independent identifier for the Composition\r\n* [Condition](condition.html): A unique identifier of the condition record\r\n* [Consent](consent.html): Identifier for this record (external references)\r\n* [Contract](contract.html): The identity of the contract\r\n* [Coverage](coverage.html): The primary identifier of the insured and the coverage\r\n* [CoverageEligibilityRequest](coverageeligibilityrequest.html): The business identifier of the Eligibility\r\n* [CoverageEligibilityResponse](coverageeligibilityresponse.html): The business identifier\r\n* [DetectedIssue](detectedissue.html): Unique id for the detected issue\r\n* [DeviceRequest](devicerequest.html): Business identifier for request/order\r\n* [DeviceUsage](deviceusage.html): Search by identifier\r\n* [DiagnosticReport](diagnosticreport.html): An identifier for the report\r\n* [DocumentReference](documentreference.html): Identifier of the attachment binary\r\n* [Encounter](encounter.html): Identifier(s) by which this encounter is known\r\n* [EnrollmentRequest](enrollmentrequest.html): The business identifier of the Enrollment\r\n* [EpisodeOfCare](episodeofcare.html): Business Identifier(s) relevant for this EpisodeOfCare\r\n* [ExplanationOfBenefit](explanationofbenefit.html): The business identifier of the Explanation of Benefit\r\n* [FamilyMemberHistory](familymemberhistory.html): A search by a record identifier\r\n* [Flag](flag.html): Business identifier\r\n* [Goal](goal.html): External Ids for this goal\r\n* [GuidanceResponse](guidanceresponse.html): The identifier of the guidance response\r\n* [ImagingSelection](imagingselection.html): Identifiers for the imaging selection\r\n* [ImagingStudy](imagingstudy.html): Identifiers for the Study, such as DICOM Study Instance UID\r\n* [Immunization](immunization.html): Business identifier\r\n* [ImmunizationEvaluation](immunizationevaluation.html): ID of the evaluation\r\n* [ImmunizationRecommendation](immunizationrecommendation.html): Business identifier\r\n* [Invoice](invoice.html): Business Identifier for item\r\n* [List](list.html): Business identifier\r\n* [MeasureReport](measurereport.html): External identifier of the measure report to be returned\r\n* [Medication](medication.html): Returns medications with this external identifier\r\n* [MedicationAdministration](medicationadministration.html): Return administrations with this external identifier\r\n* [MedicationDispense](medicationdispense.html): Returns dispenses with this external identifier\r\n* [MedicationRequest](medicationrequest.html): Return prescriptions with this external identifier\r\n* [MedicationStatement](medicationstatement.html): Return statements with this external identifier\r\n* [MolecularSequence](molecularsequence.html): The unique identity for a particular sequence\r\n* [NutritionIntake](nutritionintake.html): Return statements with this external identifier\r\n* [NutritionOrder](nutritionorder.html): Return nutrition orders with this external identifier\r\n* [Observation](observation.html): The unique id for a particular observation\r\n* [Person](person.html): A person Identifier\r\n* [Procedure](procedure.html): A unique identifier for a procedure\r\n* [QuestionnaireResponse](questionnaireresponse.html): The unique identifier for the questionnaire response\r\n* [RelatedPerson](relatedperson.html): An Identifier of the RelatedPerson\r\n* [RequestOrchestration](requestorchestration.html): External identifiers for the request orchestration\r\n* [ResearchSubject](researchsubject.html): Business Identifier for research subject in a study\r\n* [RiskAssessment](riskassessment.html): Unique identifier for the assessment\r\n* [ServiceRequest](servicerequest.html): Identifiers assigned to this order\r\n* [Specimen](specimen.html): The unique identifier associated with the specimen\r\n* [SupplyDelivery](supplydelivery.html): External identifier\r\n* [SupplyRequest](supplyrequest.html): Business Identifier for SupplyRequest\r\n* [Task](task.html): Search for a task instance by its business identifier\r\n* [VisionPrescription](visionprescription.html): Return prescriptions with this external identifier\r\n", type="token" )
  public static final String SP_IDENTIFIER = "identifier";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>identifier</b>
   * <p>
   * Description: <b>Multiple Resources: 

* [Account](account.html): Account number
* [AdverseEvent](adverseevent.html): Business identifier for the event
* [AllergyIntolerance](allergyintolerance.html): External ids for this item
* [Appointment](appointment.html): An Identifier of the Appointment
* [AppointmentResponse](appointmentresponse.html): An Identifier in this appointment response
* [Basic](basic.html): Business identifier
* [BodyStructure](bodystructure.html): Bodystructure identifier
* [CarePlan](careplan.html): External Ids for this plan
* [CareTeam](careteam.html): External Ids for this team
* [ChargeItem](chargeitem.html): Business Identifier for item
* [Claim](claim.html): The primary identifier of the financial resource
* [ClaimResponse](claimresponse.html): The identity of the ClaimResponse
* [ClinicalImpression](clinicalimpression.html): Business identifier
* [Communication](communication.html): Unique identifier
* [CommunicationRequest](communicationrequest.html): Unique identifier
* [Composition](composition.html): Version-independent identifier for the Composition
* [Condition](condition.html): A unique identifier of the condition record
* [Consent](consent.html): Identifier for this record (external references)
* [Contract](contract.html): The identity of the contract
* [Coverage](coverage.html): The primary identifier of the insured and the coverage
* [CoverageEligibilityRequest](coverageeligibilityrequest.html): The business identifier of the Eligibility
* [CoverageEligibilityResponse](coverageeligibilityresponse.html): The business identifier
* [DetectedIssue](detectedissue.html): Unique id for the detected issue
* [DeviceRequest](devicerequest.html): Business identifier for request/order
* [DeviceUsage](deviceusage.html): Search by identifier
* [DiagnosticReport](diagnosticreport.html): An identifier for the report
* [DocumentReference](documentreference.html): Identifier of the attachment binary
* [Encounter](encounter.html): Identifier(s) by which this encounter is known
* [EnrollmentRequest](enrollmentrequest.html): The business identifier of the Enrollment
* [EpisodeOfCare](episodeofcare.html): Business Identifier(s) relevant for this EpisodeOfCare
* [ExplanationOfBenefit](explanationofbenefit.html): The business identifier of the Explanation of Benefit
* [FamilyMemberHistory](familymemberhistory.html): A search by a record identifier
* [Flag](flag.html): Business identifier
* [Goal](goal.html): External Ids for this goal
* [GuidanceResponse](guidanceresponse.html): The identifier of the guidance response
* [ImagingSelection](imagingselection.html): Identifiers for the imaging selection
* [ImagingStudy](imagingstudy.html): Identifiers for the Study, such as DICOM Study Instance UID
* [Immunization](immunization.html): Business identifier
* [ImmunizationEvaluation](immunizationevaluation.html): ID of the evaluation
* [ImmunizationRecommendation](immunizationrecommendation.html): Business identifier
* [Invoice](invoice.html): Business Identifier for item
* [List](list.html): Business identifier
* [MeasureReport](measurereport.html): External identifier of the measure report to be returned
* [Medication](medication.html): Returns medications with this external identifier
* [MedicationAdministration](medicationadministration.html): Return administrations with this external identifier
* [MedicationDispense](medicationdispense.html): Returns dispenses with this external identifier
* [MedicationRequest](medicationrequest.html): Return prescriptions with this external identifier
* [MedicationStatement](medicationstatement.html): Return statements with this external identifier
* [MolecularSequence](molecularsequence.html): The unique identity for a particular sequence
* [NutritionIntake](nutritionintake.html): Return statements with this external identifier
* [NutritionOrder](nutritionorder.html): Return nutrition orders with this external identifier
* [Observation](observation.html): The unique id for a particular observation
* [Person](person.html): A person Identifier
* [Procedure](procedure.html): A unique identifier for a procedure
* [QuestionnaireResponse](questionnaireresponse.html): The unique identifier for the questionnaire response
* [RelatedPerson](relatedperson.html): An Identifier of the RelatedPerson
* [RequestOrchestration](requestorchestration.html): External identifiers for the request orchestration
* [ResearchSubject](researchsubject.html): Business Identifier for research subject in a study
* [RiskAssessment](riskassessment.html): Unique identifier for the assessment
* [ServiceRequest](servicerequest.html): Identifiers assigned to this order
* [Specimen](specimen.html): The unique identifier associated with the specimen
* [SupplyDelivery](supplydelivery.html): External identifier
* [SupplyRequest](supplyrequest.html): Business Identifier for SupplyRequest
* [Task](task.html): Search for a task instance by its business identifier
* [VisionPrescription](visionprescription.html): Return prescriptions with this external identifier
</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Account.identifier | AdverseEvent.identifier | AllergyIntolerance.identifier | Appointment.identifier | AppointmentResponse.identifier | Basic.identifier | BodyStructure.identifier | CarePlan.identifier | CareTeam.identifier | ChargeItem.identifier | Claim.identifier | ClaimResponse.identifier | ClinicalImpression.identifier | Communication.identifier | CommunicationRequest.identifier | Composition.identifier | Condition.identifier | Consent.identifier | Contract.identifier | Coverage.identifier | CoverageEligibilityRequest.identifier | CoverageEligibilityResponse.identifier | DetectedIssue.identifier | DeviceRequest.identifier | DeviceUsage.identifier | DiagnosticReport.identifier | DocumentReference.identifier | Encounter.identifier | EnrollmentRequest.identifier | EpisodeOfCare.identifier | ExplanationOfBenefit.identifier | FamilyMemberHistory.identifier | Flag.identifier | Goal.identifier | GuidanceResponse.identifier | ImagingSelection.identifier | ImagingStudy.identifier | Immunization.identifier | ImmunizationEvaluation.identifier | ImmunizationRecommendation.identifier | Invoice.identifier | List.identifier | MeasureReport.identifier | Medication.identifier | MedicationAdministration.identifier | MedicationDispense.identifier | MedicationRequest.identifier | MedicationStatement.identifier | MolecularSequence.identifier | NutritionIntake.identifier | NutritionOrder.identifier | Observation.identifier | Person.identifier | Procedure.identifier | QuestionnaireResponse.identifier | RelatedPerson.identifier | RequestOrchestration.identifier | ResearchSubject.identifier | RiskAssessment.identifier | ServiceRequest.identifier | Specimen.identifier | SupplyDelivery.identifier | SupplyRequest.identifier | Task.identifier | VisionPrescription.identifier</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam IDENTIFIER = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_IDENTIFIER);

 /**
   * Search parameter: <b>patient</b>
   * <p>
   * Description: <b>Multiple Resources: 

* [Account](account.html): The entity that caused the expenses
* [AdverseEvent](adverseevent.html): Subject impacted by event
* [AllergyIntolerance](allergyintolerance.html): Who the sensitivity is for
* [Appointment](appointment.html): One of the individuals of the appointment is this patient
* [AppointmentResponse](appointmentresponse.html): This Response is for this Patient
* [AuditEvent](auditevent.html): Where the activity involved patient data
* [Basic](basic.html): Identifies the focus of this resource
* [BodyStructure](bodystructure.html): Who this is about
* [CarePlan](careplan.html): Who the care plan is for
* [CareTeam](careteam.html): Who care team is for
* [ChargeItem](chargeitem.html): Individual service was done for/to
* [Claim](claim.html): Patient receiving the products or services
* [ClaimResponse](claimresponse.html): The subject of care
* [ClinicalImpression](clinicalimpression.html): Patient assessed
* [Communication](communication.html): Focus of message
* [CommunicationRequest](communicationrequest.html): Focus of message
* [Composition](composition.html): Who and/or what the composition is about
* [Condition](condition.html): Who has the condition?
* [Consent](consent.html): Who the consent applies to
* [Contract](contract.html): The identity of the subject of the contract (if a patient)
* [Coverage](coverage.html): Retrieve coverages for a patient
* [CoverageEligibilityRequest](coverageeligibilityrequest.html): The reference to the patient
* [CoverageEligibilityResponse](coverageeligibilityresponse.html): The reference to the patient
* [DetectedIssue](detectedissue.html): Associated patient
* [DeviceRequest](devicerequest.html): Individual the service is ordered for
* [DeviceUsage](deviceusage.html): Search by patient who used / uses the device
* [DiagnosticReport](diagnosticreport.html): The subject of the report if a patient
* [DocumentReference](documentreference.html): Who/what is the subject of the document
* [Encounter](encounter.html): The patient present at the encounter
* [EnrollmentRequest](enrollmentrequest.html): The party to be enrolled
* [EpisodeOfCare](episodeofcare.html): The patient who is the focus of this episode of care
* [ExplanationOfBenefit](explanationofbenefit.html): The reference to the patient
* [FamilyMemberHistory](familymemberhistory.html): The identity of a subject to list family member history items for
* [Flag](flag.html): The identity of a subject to list flags for
* [Goal](goal.html): Who this goal is intended for
* [GuidanceResponse](guidanceresponse.html): The identity of a patient to search for guidance response results
* [ImagingSelection](imagingselection.html): Who the study is about
* [ImagingStudy](imagingstudy.html): Who the study is about
* [Immunization](immunization.html): The patient for the vaccination record
* [ImmunizationEvaluation](immunizationevaluation.html): The patient being evaluated
* [ImmunizationRecommendation](immunizationrecommendation.html): Who this profile is for
* [Invoice](invoice.html): Recipient(s) of goods and services
* [List](list.html): If all resources have the same subject
* [MeasureReport](measurereport.html): The identity of a patient to search for individual measure report results for
* [MedicationAdministration](medicationadministration.html): The identity of a patient to list administrations  for
* [MedicationDispense](medicationdispense.html): The identity of a patient to list dispenses  for
* [MedicationRequest](medicationrequest.html): Returns prescriptions for a specific patient
* [MedicationStatement](medicationstatement.html): Returns statements for a specific patient.
* [MolecularSequence](molecularsequence.html): The subject that the sequence is about
* [NutritionIntake](nutritionintake.html): Returns statements for a specific patient.
* [NutritionOrder](nutritionorder.html): The identity of the individual or set of individuals who requires the diet, formula or nutritional supplement
* [Observation](observation.html): The subject that the observation is about (if patient)
* [Person](person.html): The Person links to this Patient
* [Procedure](procedure.html): Search by subject - a patient
* [Provenance](provenance.html): Where the activity involved patient data
* [QuestionnaireResponse](questionnaireresponse.html): The patient that is the subject of the questionnaire response
* [RelatedPerson](relatedperson.html): The patient this related person is related to
* [RequestOrchestration](requestorchestration.html): The identity of a patient to search for request orchestrations
* [ResearchSubject](researchsubject.html): Who or what is part of study
* [RiskAssessment](riskassessment.html): Who/what does assessment apply to?
* [ServiceRequest](servicerequest.html): Search by subject - a patient
* [Specimen](specimen.html): The patient the specimen comes from
* [SupplyDelivery](supplydelivery.html): Patient for whom the item is supplied
* [SupplyRequest](supplyrequest.html): The patient or subject for whom the supply is destined
* [Task](task.html): Search by patient
* [VisionPrescription](visionprescription.html): The identity of a patient to list dispenses for
</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Account.subject.where(resolve() is Patient) | AdverseEvent.subject.where(resolve() is Patient) | AllergyIntolerance.patient | Appointment.participant.actor.where(resolve() is Patient) | Appointment.subject.where(resolve() is Patient) | AppointmentResponse.actor.where(resolve() is Patient) | AuditEvent.patient | Basic.subject.where(resolve() is Patient) | BodyStructure.patient | CarePlan.subject.where(resolve() is Patient) | CareTeam.subject.where(resolve() is Patient) | ChargeItem.subject.where(resolve() is Patient) | Claim.patient | ClaimResponse.patient | ClinicalImpression.subject.where(resolve() is Patient) | Communication.subject.where(resolve() is Patient) | CommunicationRequest.subject.where(resolve() is Patient) | Composition.subject.where(resolve() is Patient) | Condition.subject.where(resolve() is Patient) | Consent.subject.where(resolve() is Patient) | Contract.subject.where(resolve() is Patient) | Coverage.beneficiary | CoverageEligibilityRequest.patient | CoverageEligibilityResponse.patient | DetectedIssue.subject.where(resolve() is Patient) | DeviceRequest.subject.where(resolve() is Patient) | DeviceUsage.patient | DiagnosticReport.subject.where(resolve() is Patient) | DocumentReference.subject.where(resolve() is Patient) | Encounter.subject.where(resolve() is Patient) | EnrollmentRequest.candidate | EpisodeOfCare.patient | ExplanationOfBenefit.patient | FamilyMemberHistory.patient | Flag.subject.where(resolve() is Patient) | Goal.subject.where(resolve() is Patient) | GuidanceResponse.subject.where(resolve() is Patient) | ImagingSelection.subject.where(resolve() is Patient) | ImagingStudy.subject.where(resolve() is Patient) | Immunization.patient | ImmunizationEvaluation.patient | ImmunizationRecommendation.patient | Invoice.subject.where(resolve() is Patient) | List.subject.where(resolve() is Patient) | MeasureReport.subject.where(resolve() is Patient) | MedicationAdministration.subject.where(resolve() is Patient) | MedicationDispense.subject.where(resolve() is Patient) | MedicationRequest.subject.where(resolve() is Patient) | MedicationStatement.subject.where(resolve() is Patient) | MolecularSequence.subject.where(resolve() is Patient) | NutritionIntake.subject.where(resolve() is Patient) | NutritionOrder.subject.where(resolve() is Patient) | Observation.subject.where(resolve() is Patient) | Person.link.target.where(resolve() is Patient) | Procedure.subject.where(resolve() is Patient) | Provenance.patient | QuestionnaireResponse.subject.where(resolve() is Patient) | RelatedPerson.patient | RequestOrchestration.subject.where(resolve() is Patient) | ResearchSubject.subject.where(resolve() is Patient) | RiskAssessment.subject.where(resolve() is Patient) | ServiceRequest.subject.where(resolve() is Patient) | Specimen.subject.where(resolve() is Patient) | SupplyDelivery.patient | SupplyRequest.deliverFor | Task.for.where(resolve() is Patient) | VisionPrescription.patient</b><br>
   * </p>
   */
  @SearchParamDefinition(name="patient", path="Account.subject.where(resolve() is Patient) | AdverseEvent.subject.where(resolve() is Patient) | AllergyIntolerance.patient | Appointment.participant.actor.where(resolve() is Patient) | Appointment.subject.where(resolve() is Patient) | AppointmentResponse.actor.where(resolve() is Patient) | AuditEvent.patient | Basic.subject.where(resolve() is Patient) | BodyStructure.patient | CarePlan.subject.where(resolve() is Patient) | CareTeam.subject.where(resolve() is Patient) | ChargeItem.subject.where(resolve() is Patient) | Claim.patient | ClaimResponse.patient | ClinicalImpression.subject.where(resolve() is Patient) | Communication.subject.where(resolve() is Patient) | CommunicationRequest.subject.where(resolve() is Patient) | Composition.subject.where(resolve() is Patient) | Condition.subject.where(resolve() is Patient) | Consent.subject.where(resolve() is Patient) | Contract.subject.where(resolve() is Patient) | Coverage.beneficiary | CoverageEligibilityRequest.patient | CoverageEligibilityResponse.patient | DetectedIssue.subject.where(resolve() is Patient) | DeviceRequest.subject.where(resolve() is Patient) | DeviceUsage.patient | DiagnosticReport.subject.where(resolve() is Patient) | DocumentReference.subject.where(resolve() is Patient) | Encounter.subject.where(resolve() is Patient) | EnrollmentRequest.candidate | EpisodeOfCare.patient | ExplanationOfBenefit.patient | FamilyMemberHistory.patient | Flag.subject.where(resolve() is Patient) | Goal.subject.where(resolve() is Patient) | GuidanceResponse.subject.where(resolve() is Patient) | ImagingSelection.subject.where(resolve() is Patient) | ImagingStudy.subject.where(resolve() is Patient) | Immunization.patient | ImmunizationEvaluation.patient | ImmunizationRecommendation.patient | Invoice.subject.where(resolve() is Patient) | List.subject.where(resolve() is Patient) | MeasureReport.subject.where(resolve() is Patient) | MedicationAdministration.subject.where(resolve() is Patient) | MedicationDispense.subject.where(resolve() is Patient) | MedicationRequest.subject.where(resolve() is Patient) | MedicationStatement.subject.where(resolve() is Patient) | MolecularSequence.subject.where(resolve() is Patient) | NutritionIntake.subject.where(resolve() is Patient) | NutritionOrder.subject.where(resolve() is Patient) | Observation.subject.where(resolve() is Patient) | Person.link.target.where(resolve() is Patient) | Procedure.subject.where(resolve() is Patient) | Provenance.patient | QuestionnaireResponse.subject.where(resolve() is Patient) | RelatedPerson.patient | RequestOrchestration.subject.where(resolve() is Patient) | ResearchSubject.subject.where(resolve() is Patient) | RiskAssessment.subject.where(resolve() is Patient) | ServiceRequest.subject.where(resolve() is Patient) | Specimen.subject.where(resolve() is Patient) | SupplyDelivery.patient | SupplyRequest.deliverFor | Task.for.where(resolve() is Patient) | VisionPrescription.patient", description="Multiple Resources: \r\n\r\n* [Account](account.html): The entity that caused the expenses\r\n* [AdverseEvent](adverseevent.html): Subject impacted by event\r\n* [AllergyIntolerance](allergyintolerance.html): Who the sensitivity is for\r\n* [Appointment](appointment.html): One of the individuals of the appointment is this patient\r\n* [AppointmentResponse](appointmentresponse.html): This Response is for this Patient\r\n* [AuditEvent](auditevent.html): Where the activity involved patient data\r\n* [Basic](basic.html): Identifies the focus of this resource\r\n* [BodyStructure](bodystructure.html): Who this is about\r\n* [CarePlan](careplan.html): Who the care plan is for\r\n* [CareTeam](careteam.html): Who care team is for\r\n* [ChargeItem](chargeitem.html): Individual service was done for/to\r\n* [Claim](claim.html): Patient receiving the products or services\r\n* [ClaimResponse](claimresponse.html): The subject of care\r\n* [ClinicalImpression](clinicalimpression.html): Patient assessed\r\n* [Communication](communication.html): Focus of message\r\n* [CommunicationRequest](communicationrequest.html): Focus of message\r\n* [Composition](composition.html): Who and/or what the composition is about\r\n* [Condition](condition.html): Who has the condition?\r\n* [Consent](consent.html): Who the consent applies to\r\n* [Contract](contract.html): The identity of the subject of the contract (if a patient)\r\n* [Coverage](coverage.html): Retrieve coverages for a patient\r\n* [CoverageEligibilityRequest](coverageeligibilityrequest.html): The reference to the patient\r\n* [CoverageEligibilityResponse](coverageeligibilityresponse.html): The reference to the patient\r\n* [DetectedIssue](detectedissue.html): Associated patient\r\n* [DeviceRequest](devicerequest.html): Individual the service is ordered for\r\n* [DeviceUsage](deviceusage.html): Search by patient who used / uses the device\r\n* [DiagnosticReport](diagnosticreport.html): The subject of the report if a patient\r\n* [DocumentReference](documentreference.html): Who/what is the subject of the document\r\n* [Encounter](encounter.html): The patient present at the encounter\r\n* [EnrollmentRequest](enrollmentrequest.html): The party to be enrolled\r\n* [EpisodeOfCare](episodeofcare.html): The patient who is the focus of this episode of care\r\n* [ExplanationOfBenefit](explanationofbenefit.html): The reference to the patient\r\n* [FamilyMemberHistory](familymemberhistory.html): The identity of a subject to list family member history items for\r\n* [Flag](flag.html): The identity of a subject to list flags for\r\n* [Goal](goal.html): Who this goal is intended for\r\n* [GuidanceResponse](guidanceresponse.html): The identity of a patient to search for guidance response results\r\n* [ImagingSelection](imagingselection.html): Who the study is about\r\n* [ImagingStudy](imagingstudy.html): Who the study is about\r\n* [Immunization](immunization.html): The patient for the vaccination record\r\n* [ImmunizationEvaluation](immunizationevaluation.html): The patient being evaluated\r\n* [ImmunizationRecommendation](immunizationrecommendation.html): Who this profile is for\r\n* [Invoice](invoice.html): Recipient(s) of goods and services\r\n* [List](list.html): If all resources have the same subject\r\n* [MeasureReport](measurereport.html): The identity of a patient to search for individual measure report results for\r\n* [MedicationAdministration](medicationadministration.html): The identity of a patient to list administrations  for\r\n* [MedicationDispense](medicationdispense.html): The identity of a patient to list dispenses  for\r\n* [MedicationRequest](medicationrequest.html): Returns prescriptions for a specific patient\r\n* [MedicationStatement](medicationstatement.html): Returns statements for a specific patient.\r\n* [MolecularSequence](molecularsequence.html): The subject that the sequence is about\r\n* [NutritionIntake](nutritionintake.html): Returns statements for a specific patient.\r\n* [NutritionOrder](nutritionorder.html): The identity of the individual or set of individuals who requires the diet, formula or nutritional supplement\r\n* [Observation](observation.html): The subject that the observation is about (if patient)\r\n* [Person](person.html): The Person links to this Patient\r\n* [Procedure](procedure.html): Search by subject - a patient\r\n* [Provenance](provenance.html): Where the activity involved patient data\r\n* [QuestionnaireResponse](questionnaireresponse.html): The patient that is the subject of the questionnaire response\r\n* [RelatedPerson](relatedperson.html): The patient this related person is related to\r\n* [RequestOrchestration](requestorchestration.html): The identity of a patient to search for request orchestrations\r\n* [ResearchSubject](researchsubject.html): Who or what is part of study\r\n* [RiskAssessment](riskassessment.html): Who/what does assessment apply to?\r\n* [ServiceRequest](servicerequest.html): Search by subject - a patient\r\n* [Specimen](specimen.html): The patient the specimen comes from\r\n* [SupplyDelivery](supplydelivery.html): Patient for whom the item is supplied\r\n* [SupplyRequest](supplyrequest.html): The patient or subject for whom the supply is destined\r\n* [Task](task.html): Search by patient\r\n* [VisionPrescription](visionprescription.html): The identity of a patient to list dispenses for\r\n", type="reference", target={Patient.class } )
  public static final String SP_PATIENT = "patient";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>patient</b>
   * <p>
   * Description: <b>Multiple Resources: 

* [Account](account.html): The entity that caused the expenses
* [AdverseEvent](adverseevent.html): Subject impacted by event
* [AllergyIntolerance](allergyintolerance.html): Who the sensitivity is for
* [Appointment](appointment.html): One of the individuals of the appointment is this patient
* [AppointmentResponse](appointmentresponse.html): This Response is for this Patient
* [AuditEvent](auditevent.html): Where the activity involved patient data
* [Basic](basic.html): Identifies the focus of this resource
* [BodyStructure](bodystructure.html): Who this is about
* [CarePlan](careplan.html): Who the care plan is for
* [CareTeam](careteam.html): Who care team is for
* [ChargeItem](chargeitem.html): Individual service was done for/to
* [Claim](claim.html): Patient receiving the products or services
* [ClaimResponse](claimresponse.html): The subject of care
* [ClinicalImpression](clinicalimpression.html): Patient assessed
* [Communication](communication.html): Focus of message
* [CommunicationRequest](communicationrequest.html): Focus of message
* [Composition](composition.html): Who and/or what the composition is about
* [Condition](condition.html): Who has the condition?
* [Consent](consent.html): Who the consent applies to
* [Contract](contract.html): The identity of the subject of the contract (if a patient)
* [Coverage](coverage.html): Retrieve coverages for a patient
* [CoverageEligibilityRequest](coverageeligibilityrequest.html): The reference to the patient
* [CoverageEligibilityResponse](coverageeligibilityresponse.html): The reference to the patient
* [DetectedIssue](detectedissue.html): Associated patient
* [DeviceRequest](devicerequest.html): Individual the service is ordered for
* [DeviceUsage](deviceusage.html): Search by patient who used / uses the device
* [DiagnosticReport](diagnosticreport.html): The subject of the report if a patient
* [DocumentReference](documentreference.html): Who/what is the subject of the document
* [Encounter](encounter.html): The patient present at the encounter
* [EnrollmentRequest](enrollmentrequest.html): The party to be enrolled
* [EpisodeOfCare](episodeofcare.html): The patient who is the focus of this episode of care
* [ExplanationOfBenefit](explanationofbenefit.html): The reference to the patient
* [FamilyMemberHistory](familymemberhistory.html): The identity of a subject to list family member history items for
* [Flag](flag.html): The identity of a subject to list flags for
* [Goal](goal.html): Who this goal is intended for
* [GuidanceResponse](guidanceresponse.html): The identity of a patient to search for guidance response results
* [ImagingSelection](imagingselection.html): Who the study is about
* [ImagingStudy](imagingstudy.html): Who the study is about
* [Immunization](immunization.html): The patient for the vaccination record
* [ImmunizationEvaluation](immunizationevaluation.html): The patient being evaluated
* [ImmunizationRecommendation](immunizationrecommendation.html): Who this profile is for
* [Invoice](invoice.html): Recipient(s) of goods and services
* [List](list.html): If all resources have the same subject
* [MeasureReport](measurereport.html): The identity of a patient to search for individual measure report results for
* [MedicationAdministration](medicationadministration.html): The identity of a patient to list administrations  for
* [MedicationDispense](medicationdispense.html): The identity of a patient to list dispenses  for
* [MedicationRequest](medicationrequest.html): Returns prescriptions for a specific patient
* [MedicationStatement](medicationstatement.html): Returns statements for a specific patient.
* [MolecularSequence](molecularsequence.html): The subject that the sequence is about
* [NutritionIntake](nutritionintake.html): Returns statements for a specific patient.
* [NutritionOrder](nutritionorder.html): The identity of the individual or set of individuals who requires the diet, formula or nutritional supplement
* [Observation](observation.html): The subject that the observation is about (if patient)
* [Person](person.html): The Person links to this Patient
* [Procedure](procedure.html): Search by subject - a patient
* [Provenance](provenance.html): Where the activity involved patient data
* [QuestionnaireResponse](questionnaireresponse.html): The patient that is the subject of the questionnaire response
* [RelatedPerson](relatedperson.html): The patient this related person is related to
* [RequestOrchestration](requestorchestration.html): The identity of a patient to search for request orchestrations
* [ResearchSubject](researchsubject.html): Who or what is part of study
* [RiskAssessment](riskassessment.html): Who/what does assessment apply to?
* [ServiceRequest](servicerequest.html): Search by subject - a patient
* [Specimen](specimen.html): The patient the specimen comes from
* [SupplyDelivery](supplydelivery.html): Patient for whom the item is supplied
* [SupplyRequest](supplyrequest.html): The patient or subject for whom the supply is destined
* [Task](task.html): Search by patient
* [VisionPrescription](visionprescription.html): The identity of a patient to list dispenses for
</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>Account.subject.where(resolve() is Patient) | AdverseEvent.subject.where(resolve() is Patient) | AllergyIntolerance.patient | Appointment.participant.actor.where(resolve() is Patient) | Appointment.subject.where(resolve() is Patient) | AppointmentResponse.actor.where(resolve() is Patient) | AuditEvent.patient | Basic.subject.where(resolve() is Patient) | BodyStructure.patient | CarePlan.subject.where(resolve() is Patient) | CareTeam.subject.where(resolve() is Patient) | ChargeItem.subject.where(resolve() is Patient) | Claim.patient | ClaimResponse.patient | ClinicalImpression.subject.where(resolve() is Patient) | Communication.subject.where(resolve() is Patient) | CommunicationRequest.subject.where(resolve() is Patient) | Composition.subject.where(resolve() is Patient) | Condition.subject.where(resolve() is Patient) | Consent.subject.where(resolve() is Patient) | Contract.subject.where(resolve() is Patient) | Coverage.beneficiary | CoverageEligibilityRequest.patient | CoverageEligibilityResponse.patient | DetectedIssue.subject.where(resolve() is Patient) | DeviceRequest.subject.where(resolve() is Patient) | DeviceUsage.patient | DiagnosticReport.subject.where(resolve() is Patient) | DocumentReference.subject.where(resolve() is Patient) | Encounter.subject.where(resolve() is Patient) | EnrollmentRequest.candidate | EpisodeOfCare.patient | ExplanationOfBenefit.patient | FamilyMemberHistory.patient | Flag.subject.where(resolve() is Patient) | Goal.subject.where(resolve() is Patient) | GuidanceResponse.subject.where(resolve() is Patient) | ImagingSelection.subject.where(resolve() is Patient) | ImagingStudy.subject.where(resolve() is Patient) | Immunization.patient | ImmunizationEvaluation.patient | ImmunizationRecommendation.patient | Invoice.subject.where(resolve() is Patient) | List.subject.where(resolve() is Patient) | MeasureReport.subject.where(resolve() is Patient) | MedicationAdministration.subject.where(resolve() is Patient) | MedicationDispense.subject.where(resolve() is Patient) | MedicationRequest.subject.where(resolve() is Patient) | MedicationStatement.subject.where(resolve() is Patient) | MolecularSequence.subject.where(resolve() is Patient) | NutritionIntake.subject.where(resolve() is Patient) | NutritionOrder.subject.where(resolve() is Patient) | Observation.subject.where(resolve() is Patient) | Person.link.target.where(resolve() is Patient) | Procedure.subject.where(resolve() is Patient) | Provenance.patient | QuestionnaireResponse.subject.where(resolve() is Patient) | RelatedPerson.patient | RequestOrchestration.subject.where(resolve() is Patient) | ResearchSubject.subject.where(resolve() is Patient) | RiskAssessment.subject.where(resolve() is Patient) | ServiceRequest.subject.where(resolve() is Patient) | Specimen.subject.where(resolve() is Patient) | SupplyDelivery.patient | SupplyRequest.deliverFor | Task.for.where(resolve() is Patient) | VisionPrescription.patient</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam PATIENT = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_PATIENT);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>Account:patient</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_PATIENT = new ca.uhn.fhir.model.api.Include("Account:patient").toLocked();

 /**
   * Search parameter: <b>type</b>
   * <p>
   * Description: <b>Multiple Resources: 

* [Account](account.html): E.g. patient, expense, depreciation
* [AllergyIntolerance](allergyintolerance.html): allergy | intolerance - Underlying mechanism (if known)
* [Composition](composition.html): Kind of composition (LOINC if possible)
* [Coverage](coverage.html): The kind of coverage (health plan, auto, Workers Compensation)
* [DocumentReference](documentreference.html): Kind of document (LOINC if possible)
* [Encounter](encounter.html): Specific type of encounter
* [EpisodeOfCare](episodeofcare.html): Type/class  - e.g. specialist referral, disease management
* [Invoice](invoice.html): Type of Invoice
* [MedicationDispense](medicationdispense.html): Returns dispenses of a specific type
* [MolecularSequence](molecularsequence.html): Amino Acid Sequence/ DNA Sequence / RNA Sequence
* [Specimen](specimen.html): The specimen type
</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Account.type | AllergyIntolerance.type | Composition.type | Coverage.type | DocumentReference.type | Encounter.type | EpisodeOfCare.type | Invoice.type | MedicationDispense.type | MolecularSequence.type | Specimen.type</b><br>
   * </p>
   */
  @SearchParamDefinition(name="type", path="Account.type | AllergyIntolerance.type | Composition.type | Coverage.type | DocumentReference.type | Encounter.type | EpisodeOfCare.type | Invoice.type | MedicationDispense.type | MolecularSequence.type | Specimen.type", description="Multiple Resources: \r\n\r\n* [Account](account.html): E.g. patient, expense, depreciation\r\n* [AllergyIntolerance](allergyintolerance.html): allergy | intolerance - Underlying mechanism (if known)\r\n* [Composition](composition.html): Kind of composition (LOINC if possible)\r\n* [Coverage](coverage.html): The kind of coverage (health plan, auto, Workers Compensation)\r\n* [DocumentReference](documentreference.html): Kind of document (LOINC if possible)\r\n* [Encounter](encounter.html): Specific type of encounter\r\n* [EpisodeOfCare](episodeofcare.html): Type/class  - e.g. specialist referral, disease management\r\n* [Invoice](invoice.html): Type of Invoice\r\n* [MedicationDispense](medicationdispense.html): Returns dispenses of a specific type\r\n* [MolecularSequence](molecularsequence.html): Amino Acid Sequence/ DNA Sequence / RNA Sequence\r\n* [Specimen](specimen.html): The specimen type\r\n", type="token" )
  public static final String SP_TYPE = "type";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>type</b>
   * <p>
   * Description: <b>Multiple Resources: 

* [Account](account.html): E.g. patient, expense, depreciation
* [AllergyIntolerance](allergyintolerance.html): allergy | intolerance - Underlying mechanism (if known)
* [Composition](composition.html): Kind of composition (LOINC if possible)
* [Coverage](coverage.html): The kind of coverage (health plan, auto, Workers Compensation)
* [DocumentReference](documentreference.html): Kind of document (LOINC if possible)
* [Encounter](encounter.html): Specific type of encounter
* [EpisodeOfCare](episodeofcare.html): Type/class  - e.g. specialist referral, disease management
* [Invoice](invoice.html): Type of Invoice
* [MedicationDispense](medicationdispense.html): Returns dispenses of a specific type
* [MolecularSequence](molecularsequence.html): Amino Acid Sequence/ DNA Sequence / RNA Sequence
* [Specimen](specimen.html): The specimen type
</b><br>
   * Type: <b>token</b><br>
   * Path: <b>Account.type | AllergyIntolerance.type | Composition.type | Coverage.type | DocumentReference.type | Encounter.type | EpisodeOfCare.type | Invoice.type | MedicationDispense.type | MolecularSequence.type | Specimen.type</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam TYPE = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_TYPE);


}

