import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * XMLInfoLoggerTest contains all the test cases that is required to check the functionalities of
 * our logger implementation.
 */
public class XMLInfoLoggerTest {

  /**
   * This test case is a valid xml that generates a summary which is given in the assignment.
   */
  @Test
  public void sample1InAsignment() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<html> This is a body</html>";
    try {
      for (int i = 0; i < test.length(); i++) {
        obj.input(test.charAt(i));
      }
      assertEquals(obj.output(), "Started:html\n"
          + "Characters: This is a body\n"
          + "Ended:html\n");
    } catch (InvalidXMLException ex) {
      assertNotEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  /**
   * This test case is a valid xml that generates a summary which is given in the assignment.
   */
  @Test
  public void sample2InAsignment() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<html>_<head> This is a heading</head><p>Paragraph</p></html>";
    try {
      for (int i = 0; i < test.length(); i++) {
        obj.input(test.charAt(i));
      }
      assertEquals(obj.output(), "Started:html\n"
          + "Characters:_\n"
          + "Started:head\n"
          + "Characters: This is a heading\n"
          + "Ended:head\n"
          + "Started:p\n"
          + "Characters:Paragraph\n"
          + "Ended:p\n"
          + "Ended:html\n");
    } catch (InvalidXMLException ex) {
      assertNotEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  /**
   * This test case is checks if the summary returns "" when no input function is used so far.
   */
  @Test
  public void emptyInput() {
    XMLInfoLogger obj = new XMLInfoLogger();
    assertEquals(obj.output(), "");
  }

  @Test
  public void validSample1WithSameStartAndEndTag() {
    String test = "<html></html>";
    XMLInfoLogger obj = new XMLInfoLogger();
    try {
      for (int i = 0; i < test.length(); i++) {
        obj.input(test.charAt(i));
      }
      assertEquals(obj.output(), "Started:html\n"
          + "Ended:html\n");
    } catch (InvalidXMLException ex) {
      assertNotEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  /**
   * This test case is a valid sample given in the assignment that checks for nest condition.
   */
  @Test
  public void validSample1InAssignment() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<root> \n"
        + "        Some data \n"
        + "        <child> Some child data </child> \n"
        + "    </root>";
    try {
      for (int i = 0; i < test.length(); i++) {
        obj.input(test.charAt(i));
      }
      assertEquals(obj.output(), "Started:root\n"
          + "Characters: \n"
          + "        Some data \n"
          + "        \n"
          + "Started:child\n"
          + "Characters: Some child data \n"
          + "Ended:child\n"
          + "Characters: \n"
          + "    \n"
          + "Ended:root\n");
    } catch (InvalidXMLException ex) {
      assertNotEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  /**
   * This test case is a valid sample given in the assignment that checks for valid tag name.
   */
  @Test
  public void validSample2inAssignment() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<html> \n"
        + "            <body0> ... </body0>\n"
        + "            <_xml> ...</_xml>\n"
        + "        </html>";
    try {
      for (int i = 0; i < test.length(); i++) {
        obj.input(test.charAt(i));
      }
      assertEquals(obj.output(), "Started:html\n"
          + "Characters: \n"
          + "            \n"
          + "Started:body0\n"
          + "Characters: ... \n"
          + "Ended:body0\n"
          + "Characters:\n"
          + "            \n"
          + "Started:_xml\n"
          + "Characters: ...\n"
          + "Ended:_xml\n"
          + "Characters:\n"
          + "        \n"
          + "Ended:html\n");
    } catch (InvalidXMLException ex) {
      assertNotEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  /**
   * This test case is a invalid sample given in the assignment that checks for invalid tag name.
   */
  @Test
  public void invalidSample2inAssignment() {
    XMLInfoLogger obj = new XMLInfoLogger();
    int errorIndex = -1;
    String test = "<ht<ml> \n <0body> ... </0body><-xml> ...</-xml> </html>";
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 3);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  /**
   * This test case is a invalid sample given in the assignment that checks for single root
   * condition.
   */
  @Test
  public void invalidSample3inAssignment() {
    XMLInfoLogger obj = new XMLInfoLogger();
    int errorIndex = -1;
    String test = "<html>\n\t<body>This is a body </body>\n</html>\n<html>"
        + "\n\t<body>This is another body </body>\n</html>";
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 44);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  /**
   * This test case is a invalid sample given in the assignment that checks for single root
   * condition.
   */
  @Test
  public void invalidSample1inAssignment() {
    XMLInfoLogger obj = new XMLInfoLogger();
    int errorIndex = -1;
    String test = "<html> This is an HTML page</html> \n <root> <leaf> Leaf </leaf></root>";
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 34);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void validSample2WithSameStartAndEndTag() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<html> <new> This is a sample </new> </html>";
    try {
      for (int i = 0; i < test.length(); i++) {
        obj.input(test.charAt(i));
      }
      assertEquals(obj.output(), "Started:html\n"
          + "Characters: \n"
          + "Started:new\n"
          + "Characters: This is a sample \n"
          + "Ended:new\n"
          + "Characters: \n"
          + "Ended:html\n");
    } catch (InvalidXMLException ex) {
      assertNotEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample1withMissingStartOrEndTag() {
    XMLInfoLogger obj = new XMLInfoLogger();
    int errorIndex = -1;
    String test = "<html> <new> This is a sample </unknown> </html>";
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 32);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample2withMissingStartOrEndTag() {
    XMLInfoLogger obj = new XMLInfoLogger();
    int errorIndex = -1;
    String test = "<html> </new> This is a sample </new> </html>";
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 9);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample1ContentBeforeRootTag() {
    XMLInfoLogger obj = new XMLInfoLogger();
    int errorIndex = -1;
    String test = "you know it's error <html> </new> This is a sample </new> </html>";
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 0);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample1WrongArrowMarks() {
    XMLInfoLogger obj = new XMLInfoLogger();
    int errorIndex = -1;
    String test = "<html> <new> This is a sample </new> </<html>";
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 39);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample2WrongArrowMarks() {
    XMLInfoLogger obj = new XMLInfoLogger();
    int errorIndex = -1;
    String test = ">html> <new> This is a sample </new> </html>";
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 0);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample3WrongArrowMarks() {
    XMLInfoLogger obj = new XMLInfoLogger();
    int errorIndex = -1;
    String test = "<html<new> This is a sample </new> </html>";
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 5);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample4WrongArrowMarks() {
    XMLInfoLogger obj = new XMLInfoLogger();
    int errorIndex = -1;
    String test = "html <new> This is a sample </new> </html>";
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 0);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample5WrongArrowMarks() {
    XMLInfoLogger obj = new XMLInfoLogger();
    int errorIndex = -1;
    String test = "<html> <new> This is a sample </new> /html>";
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 42);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSampleNoCharacterInTag() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<hmtl><>";
    int errorIndex = -1;
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 7);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample1InTagCheck() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<html > <new> This is a sample </new> </html>";
    int errorIndex = -1;
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 5);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample2InTagCheck() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<html> <new> This is a sample </new> </html >";
    int errorIndex = -1;
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 43);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample3InTagCheck() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<html> <new> This is a sample </new> </ html>";
    int errorIndex = -1;
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 39);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample4InTagCheck() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<html> <new> This is a sample </new> < /html>";
    int errorIndex = -1;
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 38);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample5InTagCheck() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<123html> <new> This is a sample </new> </html>";
    int errorIndex = -1;
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 1);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample6InTagCheck() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<html> <1new> This is a sample </new> </html>";
    int errorIndex = -1;
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 8);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample7InTagCheck() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<html> <-new> This is a sample </new> </html>";
    int errorIndex = -1;
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 8);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample8InTagCheck() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<html> <new> This is a sample </new> <9/html>";
    int errorIndex = -1;
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 38);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample9InTagCheck() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<html> <new> This is a sample </ne:w> </html>";
    int errorIndex = -1;
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 34);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample10InTagCheck() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<ht?ml> <new> This is a sample </new> </html>";
    int errorIndex = -1;
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 3);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample1RootCheck() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<html> <new> This is a sample </new> </html><html> This is extra info";
    int errorIndex = -1;
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 44);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample2RootCheck() {
    XMLInfoLogger obj = new XMLInfoLogger();
    String test = "<html> <new> This is a sample </new> </html><hey> This is extra info</hey>";
    int errorIndex = -1;
    try {
      for (int i = 0; i < test.length(); i++) {
        errorIndex = i;
        obj.input(test.charAt(i));
      }
      assertNotEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertEquals(errorIndex, 44);
      assertEquals(ex.getMessage(), "XML file is invalid");
    }
  }

}
