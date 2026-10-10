package org.hl7.fhir.r5.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Assertions;

/**
 * Reusable RDF/Turtle invariant assertions for generated FHIR Turtle fixtures.
 *
 * <p>This helper intentionally uses a lightweight Turtle parser tuned to the
 * subset emitted by the FHIR generators used in these tests. It is sufficient
 * for structural invariant checks without introducing an external RDF library.
 */
public final class TurtleInvariantAssertions {

  private static final String FHIR_VALUE = "fhir:v";
  private static final String FHIR_TREE_ROOT = "fhir:treeRoot";
  private static final String FHIR_NODE_ROLE = "fhir:nodeRole";
  private static final String FHIR_REFERENCE = "fhir:reference";
  private static final String FHIR_DIV = "fhir:div";

  private static final Pattern YEAR_PATTERN = Pattern.compile("[0-9]{4}");
  private static final Pattern YEAR_MONTH_PATTERN = Pattern.compile("[0-9]{4}-[0-9]{2}");
  private static final Pattern DATE_PATTERN = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
  private static final Pattern DATE_TIME_PATTERN = Pattern.compile(
      "[0-9]{4}-[0-9]{2}-[0-9]{2}T.+");

  private static final Map<String, Set<String>> EXPLICIT_FHIR_TYPE_DATATYPES = new HashMap<>();

  static {
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:Boolean", setOf("xsd:boolean"));
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:Integer", setOf("xsd:integer"));
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:Integer64", setOf("xsd:long"));
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:PositiveInt", setOf("xsd:positiveInteger"));
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:UnsignedInt", setOf("xsd:nonNegativeInteger"));
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:Decimal", setOf("xsd:decimal", "xsd:double"));
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:Base64Binary", setOf("xsd:base64Binary"));
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:Instant", setOf("xsd:dateTime"));
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:Date", setOf("xsd:date", "xsd:gYearMonth", "xsd:gYear"));
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:DateTime", setOf("xsd:dateTime", "xsd:date", "xsd:gYearMonth", "xsd:gYear"));
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:Time", setOf("xsd:time"));
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:Xhtml", setOf("rdf:XMLLiteral"));
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:Uri", setOf("xsd:anyURI"));
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:Url", setOf("xsd:anyURI"));
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:Canonical", setOf("xsd:anyURI"));
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:Uuid", setOf("xsd:anyURI"));
    EXPLICIT_FHIR_TYPE_DATATYPES.put("fhir:Oid", setOf("xsd:anyURI"));
  }

  private TurtleInvariantAssertions() {
    // Utility class
  }

  public enum LinkMode {
    R5("fhir:link"),
    R6("fhir:l");

    private final String predicate;

    LinkMode(String predicate) {
      this.predicate = predicate;
    }

    public String predicate() {
      return predicate;
    }
  }

  public static void assertCoreSpecInvariants(Path turtleFile) throws IOException {
    assertCoreSpecInvariants(Files.readString(turtleFile, StandardCharsets.UTF_8));
  }

  public static void assertCoreSpecInvariants(String turtle) {
    ParsedDocument document = parse(turtle);
    assertExactlyOneTreeRoot(document);
    assertNoEmptyAnonymousNodesOrCollections(document);
    assertOnlyFhirVDirectlyCarriesLiterals(document);
    assertFhirVValuesAreNonEmpty(document);
    assertExplicitPrimitiveTypeDatatypes(document);
    assertUnionTypeDatatypeSelections(document);
    assertNarrativeDivUsesXmlLiteral(document);
  }

  public static void assertGeneratorLinkStyle(String turtle, LinkMode linkMode) {
    ParsedDocument document = parse(turtle);
    String expected = linkMode.predicate();
    String legacy = linkMode == LinkMode.R6 ? LinkMode.R5.predicate() : LinkMode.R6.predicate();
    forEachProperty(document, (context, property) -> {
      if (legacy.equals(property.predicate)) {
        Assertions.fail("Unexpected link predicate " + legacy + " at " + context.path
            + "; expected generator style " + expected);
      }
    });
  }

  public static void assertAnyUriNodesHaveSiblingLinks(String turtle, LinkMode linkMode) {
    ParsedDocument document = parse(turtle);
    forEachBlankNode(document, (context, blankNode) -> {
      LiteralValue value = getSingleFhirLiteral(blankNode);
      if (value == null || !"xsd:anyURI".equals(value.datatype)) {
        return;
      }
      Property linkProperty = blankNode.firstProperty(linkMode.predicate());
      if (linkProperty == null || linkProperty.objects.isEmpty()) {
        Assertions.fail("Missing sibling " + linkMode.predicate() + " for anyURI literal at " + context.path
            + " with value " + value.lexicalForm);
      }
    });
  }

  public static void assertReferenceNodesHaveSiblingLinks(String turtle, LinkMode linkMode) {
    ParsedDocument document = parse(turtle);
    forEachBlankNode(document, (context, blankNode) -> {
      if (!blankNode.hasProperty(FHIR_REFERENCE)) {
        return;
      }
      Property linkProperty = blankNode.firstProperty(linkMode.predicate());
      if (linkProperty == null || linkProperty.objects.isEmpty()) {
        Assertions.fail("Missing sibling " + linkMode.predicate() + " for Reference node at " + context.path);
      }
    });
  }

  public static void assertVersionedAnyUriLinksRewriteBars(String turtle, LinkMode linkMode) {
    ParsedDocument document = parse(turtle);
    forEachBlankNode(document, (context, blankNode) -> {
      LiteralValue value = getSingleFhirLiteral(blankNode);
      if (value == null || !"xsd:anyURI".equals(value.datatype) || !value.lexicalForm.contains("|")) {
        return;
      }
      Property linkProperty = blankNode.firstProperty(linkMode.predicate());
      if (linkProperty == null || linkProperty.objects.size() != 1) {
        Assertions.fail("Expected a single sibling " + linkMode.predicate() + " for versioned anyURI at "
            + context.path);
      }
      Value linkTarget = linkProperty.objects.get(0);
      if (!(linkTarget instanceof IriValue)) {
        Assertions.fail("Expected " + linkMode.predicate() + " to point to an IRI at " + context.path);
      }
      String rewritten = rewriteVersionedCanonical(value.lexicalForm);
      String actual = ((IriValue) linkTarget).iri;
      Assertions.assertEquals(rewritten, actual,
          "Incorrect rewritten versioned link at " + context.path + " for value " + value.lexicalForm);
    });
  }

  public static void assertPredicatesUseCollections(String turtle, Set<String> repeatingPredicates) {
    ParsedDocument document = parse(turtle);
    forEachProperty(document, (context, property) -> {
      if (!repeatingPredicates.contains(property.predicate)) {
        return;
      }
      Assertions.assertEquals(1, property.objects.size(),
          "Expected exactly one RDF collection object for repeatable predicate " + property.predicate
              + " at " + context.path);
      Assertions.assertTrue(property.objects.get(0) instanceof CollectionValue,
          "Repeatable predicate " + property.predicate + " must serialize as an RDF list at " + context.path);
      CollectionValue collection = (CollectionValue) property.objects.get(0);
      Assertions.assertFalse(collection.items.isEmpty(),
          "Repeatable predicate " + property.predicate + " must not use an empty RDF list at " + context.path);
    });
  }

  public static void assertNoTypedChoicePredicates(String turtle, Set<String> disallowedPredicates) {
    ParsedDocument document = parse(turtle);
    forEachProperty(document, (context, property) -> {
      if (disallowedPredicates.contains(property.predicate)) {
        Assertions.fail("Disallowed typed choice predicate " + property.predicate + " found at " + context.path);
      }
    });
  }

  private static void assertExactlyOneTreeRoot(ParsedDocument document) {
    int treeRootCount = 0;
    for (Statement statement : document.statements) {
      for (Property property : statement.properties) {
        if (!FHIR_NODE_ROLE.equals(property.predicate)) {
          continue;
        }
        for (Value value : property.objects) {
          if (value instanceof NamedValue && FHIR_TREE_ROOT.equals(((NamedValue) value).token)) {
            treeRootCount++;
          }
        }
      }
    }
    Assertions.assertEquals(1, treeRootCount,
        "A FHIR Turtle document should contain exactly one fhir:nodeRole fhir:treeRoot statement");
  }

  private static void assertNoEmptyAnonymousNodesOrCollections(ParsedDocument document) {
    forEachValue(document, (context, value) -> {
      if (value instanceof BlankNodeValue) {
        BlankNodeValue blankNode = (BlankNodeValue) value;
        Assertions.assertFalse(blankNode.properties.isEmpty(), "Empty blank node at " + context.path);
      }
      if (value instanceof CollectionValue) {
        CollectionValue collection = (CollectionValue) value;
        Assertions.assertFalse(collection.items.isEmpty(), "Empty RDF list at " + context.path);
      }
    });
  }

  private static void assertOnlyFhirVDirectlyCarriesLiterals(ParsedDocument document) {
    forEachProperty(document, (context, property) -> {
      for (Value object : property.objects) {
        if (FHIR_VALUE.equals(property.predicate)) {
          Assertions.assertTrue(object instanceof LiteralValue,
              "fhir:v must point directly to an RDF literal at " + context.path);
        } else if (property.predicate.startsWith("fhir:") && object instanceof LiteralValue) {
          Assertions.fail("Only fhir:v may directly carry an RDF literal; found " + property.predicate
              + " at " + context.path);
        }
      }
    });
  }

  private static void assertFhirVValuesAreNonEmpty(ParsedDocument document) {
    forEachProperty(document, (context, property) -> {
      if (!FHIR_VALUE.equals(property.predicate)) {
        return;
      }
      for (Value object : property.objects) {
        LiteralValue literal = (LiteralValue) object;
        Assertions.assertFalse(literal.lexicalForm.isEmpty(), "Empty fhir:v literal at " + context.path);
      }
    });
  }

  private static void assertExplicitPrimitiveTypeDatatypes(ParsedDocument document) {
    forEachBlankNode(document, (context, blankNode) -> {
      LiteralValue value = getSingleFhirLiteral(blankNode);
      if (value == null) {
        return;
      }
      for (String fhirType : blankNode.fhirTypes()) {
        Set<String> allowedDatatypes = EXPLICIT_FHIR_TYPE_DATATYPES.get(fhirType);
        if (allowedDatatypes == null) {
          continue;
        }
        String effectiveDatatype = value.effectiveDatatype();
        Assertions.assertTrue(allowedDatatypes.contains(effectiveDatatype),
            "Literal datatype " + effectiveDatatype + " is incompatible with explicit type " + fhirType
                + " at " + context.path);
      }
    });
  }

  private static void assertUnionTypeDatatypeSelections(ParsedDocument document) {
    forEachBlankNode(document, (context, blankNode) -> {
      LiteralValue value = getSingleFhirLiteral(blankNode);
      if (value == null) {
        return;
      }
      if (blankNode.hasFhirType("fhir:Decimal")) {
        assertDecimalUnionSelection(value, context.path);
      }
      if (blankNode.hasFhirType("fhir:Date")) {
        assertDateUnionSelection(value, context.path, false);
      }
      if (blankNode.hasFhirType("fhir:DateTime")) {
        assertDateUnionSelection(value, context.path, true);
      }
    });
  }

  private static void assertNarrativeDivUsesXmlLiteral(ParsedDocument document) {
    forEachProperty(document, (context, property) -> {
      if (!FHIR_DIV.equals(property.predicate)) {
        return;
      }
      Assertions.assertEquals(1, property.objects.size(),
          "Expected a single object for narrative div at " + context.path);
      Assertions.assertTrue(property.objects.get(0) instanceof BlankNodeValue,
          "Narrative div should point to a blank node at " + context.path);
      BlankNodeValue blankNode = (BlankNodeValue) property.objects.get(0);
      LiteralValue literal = getSingleFhirLiteral(blankNode);
      Assertions.assertNotNull(literal, "Narrative div missing fhir:v at " + context.path);
      Assertions.assertEquals("rdf:XMLLiteral", literal.effectiveDatatype(),
          "Narrative div must use rdf:XMLLiteral at " + context.path);
    });
  }

  private static void assertDecimalUnionSelection(LiteralValue value, String path) {
    String datatype = value.effectiveDatatype();
    if ("xsd:double".equals(datatype)) {
      Assertions.assertTrue(value.lexicalForm.contains("e") || value.lexicalForm.contains("E"),
          "FHIR decimal should use xsd:double only for scientific notation at " + path);
    }
    if ("xsd:decimal".equals(datatype)) {
      Assertions.assertFalse(value.lexicalForm.contains("e") || value.lexicalForm.contains("E"),
          "FHIR decimal using xsd:decimal must not contain scientific notation at " + path);
    }
  }

  private static void assertDateUnionSelection(LiteralValue value, String path, boolean allowDateTime) {
    String lexicalForm = value.lexicalForm;
    String expectedDatatype;
    if (YEAR_PATTERN.matcher(lexicalForm).matches()) {
      expectedDatatype = "xsd:gYear";
    } else if (YEAR_MONTH_PATTERN.matcher(lexicalForm).matches()) {
      expectedDatatype = "xsd:gYearMonth";
    } else if (DATE_PATTERN.matcher(lexicalForm).matches()) {
      expectedDatatype = "xsd:date";
    } else if (allowDateTime && DATE_TIME_PATTERN.matcher(lexicalForm).matches()) {
      expectedDatatype = "xsd:dateTime";
    } else {
      return;
    }
    Assertions.assertEquals(expectedDatatype, value.effectiveDatatype(),
        "Incorrect union datatype selection for literal " + lexicalForm + " at " + path);
  }

  private static LiteralValue getSingleFhirLiteral(BlankNodeValue blankNode) {
    Property property = blankNode.firstProperty(FHIR_VALUE);
    if (property == null || property.objects.size() != 1 || !(property.objects.get(0) instanceof LiteralValue)) {
      return null;
    }
    return (LiteralValue) property.objects.get(0);
  }

  private static String rewriteVersionedCanonical(String rawUri) {
    int barIndex = rawUri.lastIndexOf('|');
    if (barIndex < 0) {
      return rawUri;
    }
    String base = rawUri.substring(0, barIndex);
    String version = rawUri.substring(barIndex + 1);
    return base + "?version=" + version;
  }

  private static ParsedDocument parse(String turtle) {
    return new Parser(new Tokenizer(turtle)).parseDocument();
  }

  private interface PropertyVisitor {
    void accept(Context context, Property property);
  }

  private interface BlankNodeVisitor {
    void accept(Context context, BlankNodeValue blankNode);
  }

  private interface ValueVisitor {
    void accept(Context context, Value value);
  }

  private static void forEachProperty(ParsedDocument document, PropertyVisitor visitor) {
    for (Statement statement : document.statements) {
      Context root = new Context(statement.subject.render());
      traverseProperties(root, statement.properties, visitor);
    }
  }

  private static void forEachBlankNode(ParsedDocument document, BlankNodeVisitor visitor) {
    forEachValue(document, (context, value) -> {
      if (value instanceof BlankNodeValue) {
        visitor.accept(context, (BlankNodeValue) value);
      }
    });
  }

  private static void forEachValue(ParsedDocument document, ValueVisitor visitor) {
    for (Statement statement : document.statements) {
      Context root = new Context(statement.subject.render());
      for (Property property : statement.properties) {
        Context propertyContext = root.child(property.predicate);
        for (int i = 0; i < property.objects.size(); i++) {
          Value value = property.objects.get(i);
          traverseValue(propertyContext.indexed(i), value, visitor);
        }
      }
    }
  }

  private static void traverseProperties(Context context, List<Property> properties, PropertyVisitor visitor) {
    for (Property property : properties) {
      Context propertyContext = context.child(property.predicate);
      visitor.accept(propertyContext, property);
      for (int i = 0; i < property.objects.size(); i++) {
        Value value = property.objects.get(i);
        traverseNested(propertyContext.indexed(i), value, visitor);
      }
    }
  }

  private static void traverseNested(Context context, Value value, PropertyVisitor visitor) {
    if (value instanceof BlankNodeValue) {
      traverseProperties(context, ((BlankNodeValue) value).properties, visitor);
    }
    if (value instanceof CollectionValue) {
      List<Value> items = ((CollectionValue) value).items;
      for (int i = 0; i < items.size(); i++) {
        traverseNested(context.child("list").indexed(i), items.get(i), visitor);
      }
    }
  }

  private static void traverseValue(Context context, Value value, ValueVisitor visitor) {
    visitor.accept(context, value);
    if (value instanceof BlankNodeValue) {
      BlankNodeValue blankNode = (BlankNodeValue) value;
      for (Property property : blankNode.properties) {
        Context propertyContext = context.child(property.predicate);
        for (int i = 0; i < property.objects.size(); i++) {
          traverseValue(propertyContext.indexed(i), property.objects.get(i), visitor);
        }
      }
    }
    if (value instanceof CollectionValue) {
      CollectionValue collection = (CollectionValue) value;
      for (int i = 0; i < collection.items.size(); i++) {
        traverseValue(context.child("list").indexed(i), collection.items.get(i), visitor);
      }
    }
  }

  private static Set<String> setOf(String... values) {
    return new HashSet<>(Arrays.asList(values));
  }

  private static final class Context {
    private final String path;

    private Context(String path) {
      this.path = path;
    }

    private Context child(String child) {
      return new Context(path + " -> " + child);
    }

    private Context indexed(int index) {
      return new Context(path + "[" + index + "]");
    }
  }

  private static final class ParsedDocument {
    private final List<Statement> statements = new ArrayList<>();
  }

  private static final class Statement {
    private final Value subject;
    private final List<Property> properties;

    private Statement(Value subject, List<Property> properties) {
      this.subject = subject;
      this.properties = properties;
    }
  }

  private static final class Property {
    private final String predicate;
    private final List<Value> objects;

    private Property(String predicate, List<Value> objects) {
      this.predicate = predicate;
      this.objects = objects;
    }
  }

  private abstract static class Value {
    protected abstract String render();
  }

  private static final class NamedValue extends Value {
    private final String token;

    private NamedValue(String token) {
      this.token = token;
    }

    @Override
    protected String render() {
      return token;
    }
  }

  private static final class IriValue extends Value {
    private final String iri;

    private IriValue(String iri) {
      this.iri = iri;
    }

    @Override
    protected String render() {
      return "<" + iri + ">";
    }
  }

  private static final class LiteralValue extends Value {
    private final String lexicalForm;
    private final String datatype;
    private final String language;

    private LiteralValue(String lexicalForm, String datatype, String language) {
      this.lexicalForm = lexicalForm;
      this.datatype = datatype;
      this.language = language;
    }

    private String effectiveDatatype() {
      if (datatype != null) {
        return datatype;
      }
      if (language != null) {
        return "rdf:langString";
      }
      return "xsd:string";
    }

    @Override
    protected String render() {
      return lexicalForm;
    }
  }

  private static final class BlankNodeValue extends Value {
    private final List<Property> properties;

    private BlankNodeValue(List<Property> properties) {
      this.properties = properties;
    }

    private boolean hasProperty(String predicate) {
      return firstProperty(predicate) != null;
    }

    private Property firstProperty(String predicate) {
      for (Property property : properties) {
        if (predicate.equals(property.predicate)) {
          return property;
        }
      }
      return null;
    }

    private boolean hasFhirType(String fhirType) {
      return fhirTypes().contains(fhirType);
    }

    private Set<String> fhirTypes() {
      Set<String> types = new HashSet<>();
      for (Property property : properties) {
        if (!"a".equals(property.predicate) && !"rdf:type".equals(property.predicate)) {
          continue;
        }
        for (Value object : property.objects) {
          if (object instanceof NamedValue) {
            String token = ((NamedValue) object).token;
            if (token.startsWith("fhir:")) {
              types.add(token);
            }
          }
        }
      }
      return types;
    }

    @Override
    protected String render() {
      return "[]";
    }
  }

  private static final class CollectionValue extends Value {
    private final List<Value> items;

    private CollectionValue(List<Value> items) {
      this.items = items;
    }

    @Override
    protected String render() {
      return "()";
    }
  }

  private enum TokenType {
    WORD,
    IRI,
    STRING,
    LANG,
    TYPE,
    LBRACKET,
    RBRACKET,
    LPAREN,
    RPAREN,
    SEMI,
    COMMA,
    DOT,
    EOF
  }

  private static final class Token {
    private final TokenType type;
    private final String text;

    private Token(TokenType type, String text) {
      this.type = type;
      this.text = text;
    }
  }

  private static final class Tokenizer {
    private final String source;
    private int cursor;

    private Tokenizer(String source) {
      this.source = source;
    }

    private Token next() {
      skipWhitespaceAndComments();
      if (cursor >= source.length()) {
        return new Token(TokenType.EOF, "");
      }
      if (source.startsWith("^^", cursor)) {
        cursor += 2;
        return new Token(TokenType.TYPE, "^^");
      }
      char current = source.charAt(cursor);
      if (current == '[') {
        cursor++;
        return new Token(TokenType.LBRACKET, "[");
      }
      if (current == ']') {
        cursor++;
        return new Token(TokenType.RBRACKET, "]");
      }
      if (current == '(') {
        cursor++;
        return new Token(TokenType.LPAREN, "(");
      }
      if (current == ')') {
        cursor++;
        return new Token(TokenType.RPAREN, ")");
      }
      if (current == ';') {
        cursor++;
        return new Token(TokenType.SEMI, ";");
      }
      if (current == ',') {
        cursor++;
        return new Token(TokenType.COMMA, ",");
      }
      if (current == '.') {
        cursor++;
        return new Token(TokenType.DOT, ".");
      }
      if (current == '<') {
        return readIri();
      }
      if (current == '"') {
        return readString();
      }
      if (current == '@') {
        return readLanguageTag();
      }
      return readWord();
    }

    private Token readIri() {
      int start = ++cursor;
      while (cursor < source.length() && source.charAt(cursor) != '>') {
        cursor++;
      }
      if (cursor >= source.length()) {
        throw new IllegalArgumentException("Unterminated IRI");
      }
      String iri = source.substring(start, cursor);
      cursor++;
      return new Token(TokenType.IRI, iri);
    }

    private Token readString() {
      StringBuilder builder = new StringBuilder();
      cursor++;
      while (cursor < source.length()) {
        char current = source.charAt(cursor++);
        if (current == '\\' && cursor < source.length()) {
          char escaped = source.charAt(cursor++);
          switch (escaped) {
          case 'n':
            builder.append('\n');
            break;
          case 'r':
            builder.append('\r');
            break;
          case 't':
            builder.append('\t');
            break;
          default:
            builder.append(escaped);
            break;
          }
          continue;
        }
        if (current == '"') {
          return new Token(TokenType.STRING, builder.toString());
        }
        builder.append(current);
      }
      throw new IllegalArgumentException("Unterminated string literal");
    }

    private Token readLanguageTag() {
      int start = cursor;
      cursor++;
      while (cursor < source.length() && isWordCharacter(source.charAt(cursor))) {
        cursor++;
      }
      return new Token(TokenType.LANG, source.substring(start, cursor));
    }

    private Token readWord() {
      int start = cursor;
      while (cursor < source.length() && isWordCharacter(source.charAt(cursor))) {
        cursor++;
      }
      return new Token(TokenType.WORD, source.substring(start, cursor));
    }

    private void skipWhitespaceAndComments() {
      while (cursor < source.length()) {
        char current = source.charAt(cursor);
        if (Character.isWhitespace(current)) {
          cursor++;
          continue;
        }
        if (current == '#') {
          while (cursor < source.length() && source.charAt(cursor) != '\n') {
            cursor++;
          }
          continue;
        }
        return;
      }
    }

    private boolean isWordCharacter(char current) {
      return !Character.isWhitespace(current)
          && current != '['
          && current != ']'
          && current != '('
          && current != ')'
          && current != ';'
          && current != ','
          && current != '.'
          && current != '#';
    }
  }

  private static final class Parser {
    private final Tokenizer tokenizer;
    private final Deque<Token> buffered = new ArrayDeque<>();

    private Parser(Tokenizer tokenizer) {
      this.tokenizer = tokenizer;
    }

    private ParsedDocument parseDocument() {
      ParsedDocument document = new ParsedDocument();
      while (true) {
        Token next = peek();
        if (next.type == TokenType.EOF) {
          return document;
        }
        if (next.type == TokenType.WORD && "@prefix".equals(next.text)) {
          parsePrefix();
          continue;
        }
        document.statements.add(parseStatement());
      }
    }

    private void parsePrefix() {
      consume(TokenType.WORD);
      consume(TokenType.WORD);
      consume(TokenType.IRI);
      consume(TokenType.DOT);
    }

    private Statement parseStatement() {
      Value subject = parseValue(false);
      List<Property> properties = parsePropertyList(TokenType.DOT);
      consume(TokenType.DOT);
      return new Statement(subject, properties);
    }

    private List<Property> parsePropertyList(TokenType terminator) {
      List<Property> properties = new ArrayList<>();
      while (peek().type != terminator) {
        String predicate = parsePredicate();
        List<Value> objects = parseObjectList();
        properties.add(new Property(predicate, objects));
        if (peek().type == TokenType.SEMI) {
          consume(TokenType.SEMI);
          while (peek().type == TokenType.SEMI) {
            consume(TokenType.SEMI);
          }
          if (peek().type == terminator) {
            break;
          }
          continue;
        }
        break;
      }
      return properties;
    }

    private String parsePredicate() {
      Token token = consume(TokenType.WORD);
      return token.text;
    }

    private List<Value> parseObjectList() {
      List<Value> values = new ArrayList<>();
      values.add(parseValue(true));
      while (peek().type == TokenType.COMMA) {
        consume(TokenType.COMMA);
        values.add(parseValue(true));
      }
      return values;
    }

    private Value parseValue(boolean allowLiteralShorthand) {
      Token token = peek();
      switch (token.type) {
      case LBRACKET:
        return parseBlankNode();
      case LPAREN:
        return parseCollection();
      case IRI:
        return new IriValue(consume(TokenType.IRI).text);
      case STRING:
        return parseStringLiteral();
      case WORD:
        return parseWordValue(allowLiteralShorthand);
      default:
        throw new IllegalArgumentException("Unexpected token " + token.type + " with text " + token.text);
      }
    }

    private Value parseBlankNode() {
      consume(TokenType.LBRACKET);
      if (peek().type == TokenType.RBRACKET) {
        consume(TokenType.RBRACKET);
        return new BlankNodeValue(Collections.emptyList());
      }
      List<Property> properties = parsePropertyList(TokenType.RBRACKET);
      consume(TokenType.RBRACKET);
      return new BlankNodeValue(properties);
    }

    private Value parseCollection() {
      consume(TokenType.LPAREN);
      List<Value> items = new ArrayList<>();
      while (peek().type != TokenType.RPAREN) {
        items.add(parseValue(true));
      }
      consume(TokenType.RPAREN);
      return new CollectionValue(items);
    }

    private Value parseStringLiteral() {
      String lexicalForm = consume(TokenType.STRING).text;
      String datatype = null;
      String language = null;
      if (peek().type == TokenType.TYPE) {
        consume(TokenType.TYPE);
        Token datatypeToken = next();
        if (datatypeToken.type != TokenType.WORD && datatypeToken.type != TokenType.IRI) {
          throw new IllegalArgumentException("Expected datatype after ^^ but found " + datatypeToken.type);
        }
        datatype = datatypeToken.text;
      } else if (peek().type == TokenType.LANG) {
        language = consume(TokenType.LANG).text;
      }
      return new LiteralValue(lexicalForm, datatype, language);
    }

    private Value parseWordValue(boolean allowLiteralShorthand) {
      String token = consume(TokenType.WORD).text;
      if (allowLiteralShorthand) {
        if ("true".equals(token) || "false".equals(token)) {
          return new LiteralValue(token, "xsd:boolean", null);
        }
        if (token.matches("[+-]?[0-9]+")) {
          return new LiteralValue(token, "xsd:integer", null);
        }
        if (token.matches("[+-]?[0-9]+\\.[0-9]+") || token.matches("[+-]?[0-9]+[eE][+-]?[0-9]+")
            || token.matches("[+-]?[0-9]+\\.[0-9]+[eE][+-]?[0-9]+")) {
          String datatype = token.contains("e") || token.contains("E") ? "xsd:double" : "xsd:decimal";
          return new LiteralValue(token, datatype, null);
        }
      }
      return new NamedValue(token);
    }

    private Token peek() {
      if (buffered.isEmpty()) {
        buffered.push(tokenizer.next());
      }
      return buffered.peek();
    }

    private Token next() {
      if (!buffered.isEmpty()) {
        return buffered.pop();
      }
      return tokenizer.next();
    }

    private Token consume(TokenType expected) {
      Token token = next();
      if (token.type != expected) {
        throw new IllegalArgumentException("Expected " + expected + " but found " + token.type + " (" + token.text + ")");
      }
      return token;
    }
  }
}