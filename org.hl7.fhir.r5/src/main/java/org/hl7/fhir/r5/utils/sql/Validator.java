package org.hl7.fhir.r5.utils.sql;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.model.TypeDetails;
import org.hl7.fhir.r5.model.ExpressionNode;
import org.hl7.fhir.r5.model.ExpressionNode.CollectionStatus;
import org.hl7.fhir.r5.utils.FHIRPathEngine;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.json.model.JsonArray;
import org.hl7.fhir.utilities.json.model.JsonBoolean;
import org.hl7.fhir.utilities.json.model.JsonElement;
import org.hl7.fhir.utilities.json.model.JsonNumber;
import org.hl7.fhir.utilities.json.model.JsonObject;
import org.hl7.fhir.utilities.json.model.JsonString;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueType;
import org.hl7.fhir.utilities.validation.ValidationMessage.Source;

public class Validator {
  
  private IWorkerContext context;
  private FHIRPathEngine fpe;
  private List<String> prohibitedNames = new ArrayList<String>();
  private List<ValidationMessage> issues = new ArrayList<ValidationMessage>();
  private boolean arrays;
  private boolean complexTypes;
  private boolean needsName;

  private String resourceName;
  private List<Column> columns = new ArrayList<Column>();
  private String name;

  protected Validator(IWorkerContext context, FHIRPathEngine fpe, List<String> prohibitedNames, boolean arrays, boolean complexTypes, boolean needsName) {
    super();
    this.context = context;
    this.fpe = fpe;
    this.prohibitedNames = prohibitedNames;
    this.arrays = arrays;
    this.complexTypes = complexTypes;
    this.needsName = needsName;
  }

  public String getResourceName() {
    return resourceName;
  }

  public List<Column> getColumns() {
    return columns;
  }

  public void checkViewDefinition(String path, JsonObject viewDefinition) {
    JsonElement nameJ = viewDefinition.get("name");
    if (nameJ == null) {
      if (needsName) {
        error(path, viewDefinition, "No name provided", IssueType.REQUIRED);
      }
    } else if (!(nameJ instanceof JsonString)) {
      error(path, viewDefinition, "name must be a string", IssueType.INVALID);      
    } else {
      name = nameJ.asString();
      if (!isValidName(name)) {      
        error(path+".name", nameJ, "The name '"+name+"' is not valid", IssueType.INVARIANT);
      }
      if (prohibitedNames.contains(name)) {      
        error(path, nameJ, "The name '"+name+"' on the viewDefinition is not allowed in this context", IssueType.BUSINESSRULE);
      }
    }

    JsonElement resourceNameJ = viewDefinition.get("resource");
    if (resourceNameJ == null) {
      error(path, viewDefinition, "No resource provided", IssueType.REQUIRED);      
    } else if (!(resourceNameJ instanceof JsonString)) {
      error(path, viewDefinition, "resource must be a string", IssueType.INVALID);      
    } else {
      resourceName = resourceNameJ.asString();
      if (!context.getResourceNamesAsSet().contains(resourceName)) {      
        error(path+".name", nameJ, "The name '"+resourceName+"' is not a valid resource", IssueType.BUSINESSRULE);
      } else {
        int i = 0;
        if (checkAllObjects(path, viewDefinition, "constant")) {
          for (JsonObject constant : viewDefinition.getJsonObjects("constant")) {
            checkConstant(path+".constant["+i+"]", constant);
            i++;
          }
        }
        i = 0;
        if (checkAllObjects(path, viewDefinition, "where")) {
          for (JsonObject where : viewDefinition.getJsonObjects("where")) {
            checkWhere(path+".where["+i+"]", where);
            i++;
          }
        }
        TypeDetails t = new TypeDetails(CollectionStatus.SINGLETON, resourceName);

        if (viewDefinition.has("forEach")) {
          checkForEach(path, viewDefinition, viewDefinition.get("forEach"), t);
        } else if (viewDefinition.has("forEachOrNull")) {
          checkForEachOrNull(path, viewDefinition, viewDefinition.get("forEachOrNull"), t);
        } else if (viewDefinition.has("union")) {
          checkUnion(path, viewDefinition, viewDefinition.get("union"), t);
        } else {
          i = 0;
          if (checkAllObjects(path, viewDefinition, "select")) {
            for (JsonObject select : viewDefinition.getJsonObjects("select")) {
              checkSelect(path+".select["+i+"]", select, t);
              i++;
            }
            if (i == 0) {
              error(path, viewDefinition, "No select statements found", IssueType.REQUIRED);
            }
          }
        }
      }
    }
  }

  private void checkSelect(String path, JsonObject select, TypeDetails t) {
    if (select.has("path")) {
      checkSelectPath(path, select, select.get("path"), t);
    } else if (select.has("forEach")) {
      checkForEach(path, select, select.get("forEach"), t);
    } else if (select.has("forEachOrNull")) {
      checkForEachOrNull(path, select, select.get("forEachOrNull"), t);
    } else if (select.has("union")) {
      checkUnion(path, select, select.get("union"), t);
    } else {
      error(path, select, "The select has neither a path, forEach, forEachOrNull, or union statement", IssueType.REQUIRED);
    }
  }

  private void checkSelectPath(String path, JsonObject select, JsonElement expression, TypeDetails t) {
    if (!(expression instanceof JsonString)) {
      error(path+".forEach", expression, "forEach is not a string", IssueType.INVALID);
    } else {
      String expr = expression.asString();

      List<String> warnings = new ArrayList<>();
      TypeDetails td = null;
      ExpressionNode node = null;
      try {
        node = fpe.parse(expr);
        select.setUserData("path", node);
        td = fpe.checkOnTypes(null, resourceName, t, node, warnings);
      } catch (Exception e) {
        error(path, expression, e.getMessage(), IssueType.INVALID);
      }
      if (td != null && node != null) {
        for (String s : warnings) {
          warning(path+".path", expression, s);
        }
        String columnName = null;
        JsonElement aliasJ = select.get("alias");
        if (aliasJ != null) {
          if (aliasJ instanceof JsonString) {
            columnName = aliasJ.asString();
            if (!isValidName(columnName)) {      
              error(path+".name", aliasJ, "The name '"+columnName+"' is not valid", IssueType.VALUE);
            }
          } else {
            error(path+".alias", aliasJ, "alias must be a string", IssueType.INVALID);
          }
        }
        if (columnName == null) {
          List<String> names = node.getDistalNames();
          if (names.size() == 1 && names.get(0) != null) {
            columnName = names.get(0);
            if (!isValidName(columnName)) {      
              error(path+".path", expression, "The name '"+columnName+"' found in the path expression is not a valid column name, so an alias is required", IssueType.INVARIANT);
            }
          } else {
            error(path, select, "The path does not resolve to a name, so an alias is required", IssueType.REQUIRED);
          }
        }
        // ok, name is sorted!
        if (columnName != null) {
          select.setUserData("name", columnName);
          boolean isColl = (td.getCollectionStatus() != CollectionStatus.SINGLETON) || column(columnName) != null;
          if (select.has("collection")) {
            JsonElement collectionJ = select.get("collection");
            if (!(collectionJ instanceof JsonBoolean)) {
              error(path+".collection", collectionJ, "collection is not a boolean", IssueType.INVALID);
            } else {
              boolean collection = collectionJ.asJsonBoolean().asBoolean();
              if (!collection && isColl) {
                isColl = false;
                warning(path, select, "collection is false, but the path statement(s) might return multiple values for the column '"+columnName+"' some inputs");
              }
            }
          }
          if (isColl && !arrays) {
            warning(path, expression, "column appears to be a collection, but this is not allowed in this context");
          }
          // ok collection is sorted
          Set<String> types = new HashSet<>();
          for (String type : td.getTypes()) {
            types.add(simpleType(type));
          }

          JsonElement typeJ = select.get("type");
          if (typeJ != null) {
            if (typeJ instanceof JsonString) {
              String type = typeJ.asString();
              if (!td.hasType(type)) {
                error(path+".type", typeJ, "The path expression does not return a value of the type '"+type, IssueType.VALUE);
              } else {
                types.clear();
                types.add(simpleType(type));
              }
            } else {
              error(path+".type", typeJ, "type must be a string", IssueType.INVALID);
            }
          }
          if (types.size() != 1) {
            error(path, select, "Unable to determine a type (found "+td.describe()+")", IssueType.BUSINESSRULE);
          } else {
            String type = types.iterator().next();
            if (!isSimpleType(type) && !complexTypes) {
              error(path, expression, "column is a complex type but this is not allowed in this context", IssueType.BUSINESSRULE);
            } else {
              Column col = column(columnName);
              if (col != null) {
                if (!col.getType().equals(type)) {
                  error(path, expression, "Duplicate definition for "+columnName+" has different types ("+col.getType()+" vs "+type+")", IssueType.BUSINESSRULE);
                }
                if (col.isColl() != isColl) {
                  error(path, expression, "Duplicate definition for "+columnName+" has different status for collection ("+col.isColl()+" vs "+isColl+")", IssueType.BUSINESSRULE);
                }
              } else {
                columns.add(new Column(columnName, isColl, type, kindForType(type)));

              }
            }
          }
        }
      }
    }
  }

  private ColumnKind kindForType(String type) {
    switch (type) {
    case "dateTime": return ColumnKind.DateTime;
    case "boolean": return ColumnKind.Boolean;
    case "integer": return ColumnKind.Integer;
    case "decimal": return ColumnKind.Decimal;
    case "string": return ColumnKind.String;
    case "base64Binary": return ColumnKind.Binary;
    case "time": return ColumnKind.Time;
    default: return ColumnKind.Complex;
    }
  }

  private Column column(String columnName) {
    for (Column t : columns) {
      if (t.getName().equalsIgnoreCase(columnName)) {
        return t;
      }
    }
    return null;
  }

  private boolean isSimpleType(String type) {
    return Utilities.existsInList(type, "dateTime", "boolean", "integer", "decimal", "string", "base64Binary");
  }

  private String simpleType(String type) {
    type = type.replace("http://hl7.org/fhirpath/System.", "").replace("http://hl7.org/fhir/StructureDefinition/", "");
    if (Utilities.existsInList(type, "date", "dateTime", "instant")) {
      return "dateTime";
    }
    if (Utilities.existsInList(type, "Boolean", "boolean")) {
      return "boolean";
    }
    if (Utilities.existsInList(type, "Integer", "integer", "integer64")) {
      return "integer";
    }
    if (Utilities.existsInList(type, "Decimal", "decimal")) {
      return "decimal";
    }
    if (Utilities.existsInList(type, "String", "string", "code")) {
      return "string";
    }
    if (Utilities.existsInList(type, "Time", "time")) {
      return "time";
    }
    if (Utilities.existsInList(type, "base64Binary")) {
      return "base64Binary";
    }
    return type;
  }

  private void checkForEach(String path, JsonObject focus, JsonElement expression, TypeDetails t) {
    if (!(expression instanceof JsonString)) {
      error(path+".forEach", expression, "forEach is not a string", IssueType.INVALID);
    } else {
      String expr = expression.asString();

      List<String> warnings = new ArrayList<>();
      TypeDetails td = null;
      try {
        ExpressionNode n = fpe.parse(expr);
        focus.setUserData("forEach", n);
        td = fpe.checkOnTypes(null, resourceName, t, n, warnings);
      } catch (Exception e) {
        error(path, expression, e.getMessage(), IssueType.INVALID);
      }
      if (td != null) {
        for (String s : warnings) {
          warning(path+".path", expression, s);
        }
        int i = 0;
        if (checkAllObjects(path, focus, "select")) {
          for (JsonObject select : focus.getJsonObjects("select")) {
            checkSelect(path+".select["+i+"]", select, td);
            i++;
          }
          if (i == 0) {
            error(path, focus, "No select statements found", IssueType.REQUIRED);
          }
        }
      }
    }
  }

  private void checkForEachOrNull(String path, JsonObject focus, JsonElement expression, TypeDetails t) {
    error(path+".forEachOrNull", expression, "forEachOrNull is not supported", IssueType.BUSINESSRULE);    
  }

  private void checkUnion(String path, JsonObject focus, JsonElement expression,  TypeDetails t) {
    error(path+".union", focus.get("union"), "union is not supported", IssueType.BUSINESSRULE);        
  }

  private void checkConstant(String path, JsonObject constant) {
    JsonElement nameJ = constant.get("name");
    if (nameJ == null) {
      error(path, constant, "No name provided", IssueType.REQUIRED);      
    } else if (!(nameJ instanceof JsonString)) {
      error(path, constant, "Name must be a string", IssueType.INVALID);      
    } else {
      String name = constant.asString("name");
      if (!isValidName(name)) {      
        error(path+".name", nameJ, "The name '"+name+"' is not valid", IssueType.INVARIANT);
      }
    }
    if (constant.has("valueBase64Binary")) {
      checkIsString(path, constant, "valueBase64Binary");
    } else if (constant.has("valueBoolean")) {
      checkIsBoolean(path, constant, "valueBoolean");
    } else if (constant.has("valueCanonical")) { 
      checkIsString(path, constant, "valueCanonical");
    } else if (constant.has("valueCode")) {
      checkIsString(path, constant, "valueCode");
    } else if (constant.has("valueDate")) {
      checkIsString(path, constant, "valueDate");
    } else if (constant.has("valueDateTime")) {
      checkIsString(path, constant, "valueDateTime");
    } else if (constant.has("valueDecimal")) {
      checkIsNumber(path, constant, "valueDecimal");
    } else if (constant.has("valueId")) {
      checkIsString(path, constant, "valueId");
    } else if (constant.has("valueInstant")) {
      checkIsString(path, constant, "valueInstant");
    } else if (constant.has("valueInteger")) {
      checkIsNumber(path, constant, "valueInteger");
    } else if (constant.has("valueInteger64")) {
      checkIsNumber(path, constant, "valueInteger64");
    } else if (constant.has("valueOid")) {
      checkIsString(path, constant, "valueOid");
    } else if (constant.has("valueString")) {
      checkIsString(path, constant, "valueString");
    } else if (constant.has("valuePositiveInt")) {
      checkIsNumber(path, constant, "valuePositiveInt");
    } else if (constant.has("valueTime")) {
      checkIsString(path, constant, "valueTime");
    } else if (constant.has("valueUnsignedInt")) {
      checkIsNumber(path, constant, "valueUnsignedInt");
    } else if (constant.has("valueUri")) {
      checkIsString(path, constant, "valueUri");
    } else if (constant.has("valueUrl")) {
      checkIsString(path, constant, "valueUrl");
    } else if (constant.has("valueUuid")) {
      checkIsString(path, constant, "valueUuid");
    } else {
      error(path, constant, "No value found", IssueType.REQUIRED);
    }
  }

  private void checkIsString(String path, JsonObject constant, String name) {
    JsonElement j = constant.get(name);
    if (!(j instanceof JsonString)) {
      error(path+"."+name, j, name+" must be a string", IssueType.INVALID);
    }
  }

  private void checkIsBoolean(String path, JsonObject constant, String name) {
    JsonElement j = constant.get(name);
    if (!(j instanceof JsonBoolean)) {
      error(path+"."+name, j, name+" must be a boolean", IssueType.INVALID);
    }
  }

  private void checkIsNumber(String path, JsonObject constant, String name) {
    JsonElement j = constant.get(name);
    if (!(j instanceof JsonNumber)) {
      error(path+"."+name, j, name+" must be a number", IssueType.INVALID);
    }
  }
  private void checkWhere(String path, JsonObject where) {
    String expr = where.asString("path");
    if (expr == null) {
      error(path, where, "No path provided", IssueType.REQUIRED);
    }
    List<String> types = new ArrayList<>();
    List<String> warnings = new ArrayList<>();
    types.add(resourceName);
    TypeDetails td = null;
    try {
      ExpressionNode n = fpe.parse(expr);
      where.setUserData("path", n);
      td = fpe.checkOnTypes(null, resourceName, types, n, warnings);
    } catch (Exception e) {
      error(path, where.get("path"), e.getMessage(), IssueType.INVALID);
    }
    if (td != null) {
      if (td.getCollectionStatus() != CollectionStatus.SINGLETON || td.getTypes().size() != 1 || !td.hasType("boolean")) {
        error(path+".path", where.get("path"), "A where path must return a boolean, but the expression "+expr+" returns a "+td.describe(), IssueType.BUSINESSRULE);
      } else {
        for (String s : warnings) {
          warning(path+".path", where.get("path"), s);
        }
      }
    }
  }

  private boolean isValidName(String name) {
    boolean first = true;
    for (char c : name.toCharArray()) {
      if (!(Character.isAlphabetic(c) || Character.isDigit(c) || (!first && c == '_'))) {
        return false;
      }
      first = false;
    }
    return true;
  }


  private boolean checkAllObjects(String path, JsonObject focus, String name) {
    if (!focus.has(name)) {
      return true;
    } else if (!(focus.get(name) instanceof JsonArray)) {
      error(path+"."+name, focus.get(name), name+" must be an array", IssueType.INVALID);
      return false;
    } else {
      JsonArray arr = focus.getJsonArray(name);
      int i = 0;
      boolean ok = true;
      for (JsonElement e : arr) {
        if (!(e instanceof JsonObject)) {
          error(path+"."+name+"["+i+"]", e, name+"["+i+"] must be an object", IssueType.INVALID);
          ok = false;
        }
      }
      return ok;
    }
  }

  private void error(String path, JsonElement e, String issue, IssueType type) {
    ValidationMessage vm = new ValidationMessage(Source.InstanceValidator, type, e.getStart().getLine(), e.getStart().getCol(), path, issue, IssueSeverity.ERROR);
    issues.add(vm);

  }

  private void warning(String path, JsonElement e, String issue) {
    ValidationMessage vm = new ValidationMessage(Source.InstanceValidator, IssueType.BUSINESSRULE, e.getStart().getLine(), e.getStart().getCol(), path, issue, IssueSeverity.WARNING);
    issues.add(vm);
  }

  public void dump() {
   for (ValidationMessage vm : issues) {
     System.out.println(vm.summary());
   }
    
  }

  public void check() {
    boolean ok = true;
    for (ValidationMessage vm : issues) {
      if (vm.isError()) {
        ok = false;
      }
    }
    if (!ok) {
      throw new FHIRException("View Definition is not valid");
    }
     
  }

  public String getName() {
    return name;
  }
}
