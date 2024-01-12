import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * XMLValidatorTest class contains all the test cases required to check the functioning of the xml
 * validator implemented.
 */
public class XMLValidatorTest {

  @Test
  public void validSample1WithSameStartAndEndTag() {
    String test = "<html></html>";
    XMLValidator obj = new XMLValidator();
    try {
      for (int i = 0; i < test.length(); i++) {
        obj.input(test.charAt(i));
      }
      assertEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertNotEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  /**
   * This test case is a valid sample given in the assignment that checks for nest condition.
   */
  @Test
  public void validSample1InAssignment() {
    XMLValidator obj = new XMLValidator();
    String test = "<root> \n"
        + "        Some data \n"
        + "        <child> Some child data </child> \n"
        + "    </root>";
    try {
      for (int i = 0; i < test.length(); i++) {
        obj.input(test.charAt(i));
      }
      assertEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertNotEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  /**
   * This test case is a valid sample given in the assignment that checks for valid tag name.
   */
  @Test
  public void validSample2inAssignment() {
    XMLValidator obj = new XMLValidator();
    String test = "<html> \n"
        + "            <body0> ... </body0>\n"
        + "            <_xml> ...</_xml>\n"
        + "        </html>";
    try {
      for (int i = 0; i < test.length(); i++) {
        obj.input(test.charAt(i));
      }
      assertEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertNotEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  /**
   * This test case is a invalid sample given in the assignment that checks for invalid tag name.
   */
  @Test
  public void invalidSample2inAssignment() {
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
    String test = "<html> <new> This is a sample </new> </html>";
    try {
      for (int i = 0; i < test.length(); i++) {
        obj.input(test.charAt(i));
      }
      assertEquals(obj.output(), "Status:Valid");
    } catch (InvalidXMLException ex) {
      assertNotEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSample1withMissingStartOrEndTag() {
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
  public void incompleteSampleMissingArrowMarks() {
    XMLValidator obj = new XMLValidator();
    String test = "<html> <new> This is a sample </new> /html";
    try {
      for (int i = 0; i < test.length(); i++) {
        obj.input(test.charAt(i));
      }
      assertEquals(obj.output(), "Status:Incomplete");
    } catch (InvalidXMLException ex) {
      assertNotEquals(ex.getMessage(), "XML file is invalid");
    }
  }

  @Test
  public void invalidSampleNoCharacterInTag() {
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
    XMLValidator obj = new XMLValidator();
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
