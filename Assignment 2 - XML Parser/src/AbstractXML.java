import java.util.Stack;

/**
 * AbstractXML is an abstract class that implements the XMLParser interface and contains all the
 * common functions that is required by both the XMLParser class and XMLInfoLogger class.
 */
public abstract class AbstractXML implements XMLParser {

  protected String xmlDoc;
  protected String status;

  public AbstractXML() {
    this.xmlDoc = "";
  }

  /**
   * findIndex is a function used to get the end index of the tag. The function returns -1 if there
   * is no symbol (< or >) found in the tag.
   *
   * @param symbol     the end index we are searching
   * @param startIndex the start index of the tag
   * @return endIndex the end index of the tag if found
   */

  protected int findIndex(char symbol, int startIndex) {
    int endIndex = -1;
    // Looping through the xmlDoc string and checking for the symbol to get the end index.
    for (int j = startIndex; j < this.xmlDoc.length(); j++) {
      char c = this.xmlDoc.charAt(j);
      if (c == symbol) {
        endIndex = j - 1;
        break;
      } else if (c == '<') {
        endIndex = -5;
      }
    }
    return endIndex;
  }

  @Override
  public XMLParser input(char c) throws InvalidXMLException {
    // This is a method that is used to add a character to the xmlDoc and then validate the xmlDoc.
    this.xmlDoc = xmlDoc + c;
    this.status = validateXML();
    return this;
  }

  /**
   * validateXML function checks for the validity of the XML document by checking for the following.
   * 1. If the document has invalid name tags. 2. If the document has proper nested tags. 3. If the
   * document has only one root tag set. At any point if an invalid document is found, the function
   * throws an InvalidXMLException. At any point if the document can be valid if more characters are
   * entered, the function returns "Status:Incomplete". At any point if the XML document is valid,
   * it returns "Status:Valid".
   *
   * @return String : Status of the XML
   * @throws InvalidXMLException when document is invalid
   */
  protected String validateXML() throws InvalidXMLException {
    Stack<String> tagStack = new Stack<>();
    String doc = this.xmlDoc;

    // An int flag variable to check if the root have been visited before
    int rootFinder = 0;
    String root = "";
    int end_index;
    // A boolean to check if the rootend have been reached
    boolean rootEnd = false;

    // Looping through each character in the xml document
    for (int i = 0; i < doc.length(); i++) {
      char c = doc.charAt(i);
      // Throwing an error if the first character is not <
      if (i == 0 && c != '<') {
        throw new InvalidXMLException("XML file is invalid");
      }
      // Throwing an exception if the root start and end tag have been visited and further
      // characters are seen
      if (rootEnd) {
        throw new InvalidXMLException("XML file is invalid");
      }
      if (c == '<') {
        // Trying to get the end index of the tag > if found
        int start_index = i + 1;
        end_index = this.findIndex('>', i + 1);
        // If there is no end index check for the incomplete tag if it's an end tag
        // with the previous tag so far
        if (end_index == -1) {
          String incompleteTag = doc.substring(start_index);
          // Checking if the incomplete has a character entered so far
          if (incompleteTag.length() > 0) {
            if (incompleteTag.charAt(0) == '/') {
              if (incompleteTag.length() > 1) {
                incompleteTag = incompleteTag.substring(1, doc.length() - start_index);
              } else {
                return "Status:Incomplete";
              }
            }
          }
          // This is checking if the incomplete tag is valid or not.
          if (!formatValidator(incompleteTag)) {
            throw new InvalidXMLException("XML file is invalid");
          }
          // Checking for nesting condition with incomplete tag so far
          if (!tagStack.empty()) {
            String poppedTag = tagStack.pop();
            String soFar = doc.substring(start_index);
            if (soFar.length() > 1) {
              if (soFar.charAt(0) == '/') {
                String left = poppedTag.substring(0, soFar.length() - 1);
                String right = soFar.substring(1);
                if (!left.equals(right)) {
                  throw new InvalidXMLException("XML file is invalid");
                }
              }
            }
          }
          return "Status:Incomplete";
        } else if (end_index == -5) {
          // Throwing an exception when an < is found inside the tag
          throw new InvalidXMLException("XML file is invalid");
        }

        // Checking if it's an end tag
        if (doc.charAt(i + 1) == '/') {
          start_index += 1;
        }
        String tag = doc.substring(i + 1, end_index + 1);
        String filtered_tag = doc.substring(start_index, end_index + 1);

        // Validating the format of the end tag
        if (!formatValidator(filtered_tag)) {
          throw new InvalidXMLException("XML file is invalid");
        }
        i = end_index + 1;

        // Fixing the root found
        if (rootFinder == 0) {
          root = tag;
        }
        // Incrementing the root if new root is encountered
        if (tag.equals(root) || filtered_tag.equals(root)) {
          rootFinder += 1;
          if (tag.equals('/' + root)) {
            rootEnd = true;
          }
          // Throwing error if more than 2 root tags is in XML
          if (rootFinder >= 3) {
            throw new InvalidXMLException("XML file is invalid");
          }

        }
        // Checking for nested condition with complete tags
        if (tagStack.isEmpty()) {
          tagStack.push(tag);
        } else {
          String popped_tag = tagStack.pop();
          if (tag.length() == 0) {
            throw new InvalidXMLException("XML file is invalid");
          }
          if (popped_tag.charAt(0) == '/') {
            if (popped_tag.substring(1).equals(tagStack.pop())) {
              continue;
            } else {
              throw new InvalidXMLException("XML file is invalid");
            }
          } else if (tag.charAt(0) == '/') {
            if (tag.equals('/' + popped_tag)) {
              continue;
            } else {
              throw new InvalidXMLException("XML file is invalid");
            }

          } else {
            tagStack.push(popped_tag);
            tagStack.push(tag);
          }
        }
      } else if (c == '>') {
        throw new InvalidXMLException("XML file is invalid");
      }

    }
    if (tagStack.isEmpty()) {
      return "Status:Valid";
    } else {
      return "Status:Incomplete";
    }
  }

  /**
   * formatValidator checks for the tag name valid conditions.
   *
   * @param tag the tag to be validated.
   * @return boolean true/false.
   */
  private Boolean formatValidator(String tag) {
    boolean flag = true;
    for (int i = 0; i < tag.length(); i++) {
      char c = tag.charAt(i);
      boolean numeric = (c >= '0' && c <= '9');
      boolean alpha = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
      boolean special_characters = (c == ':' || c == '_' || c == '-');

      if (i == 0) {
        flag = flag && ((alpha || special_characters) && (c != '-'));
      } else {
        flag = flag && (alpha || special_characters || numeric);
      }
      if (!flag) {
        return false;
      }
    }
    return true;
  }
}
