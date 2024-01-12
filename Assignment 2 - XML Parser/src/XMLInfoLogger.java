
/**
 * XMLInfoLogger is a class that takes an XML document and outputs a string that describes the
 * structure of the XML document.
 */
public class XMLInfoLogger extends AbstractXML {

  /**
   * This is a constructor that initializes the XML document to be "".
   */
  public XMLInfoLogger() {
    super();
  }

  @Override
  public String output() {
    if (this.xmlDoc.equals("")) {
      return "";
    }
    return generateOutput();
  }

  /**
   * generateOutput is a function that creates the descriptive log of the xml document.
   *
   * @return String log document
   */
  private String generateOutput() {
    String doc = this.xmlDoc;
    StringBuilder accumulator = new StringBuilder();
    // Looping through the XML doc
    for (int i = 0; i < doc.length(); ) {
      char c = doc.charAt(i);
      if (c == '<') {
        int start_index = i + 1;
        int end_index = this.findIndex('>', i + 1);
        String tag = doc.substring(start_index, end_index + 1);
        // Logging end tag if / is found after <
        if (tag.charAt(0) == '/') {
          accumulator.append("Ended:").append(tag.substring(1)).append("\n");
        } else {
          // Logging start tag
          accumulator.append("Started:").append(tag).append("\n");
        }
        i = end_index + 2;
      } else {
        int stop_index = i;
        for (int j = i; j < doc.length(); j++) {
          if (doc.charAt(j) == '<') {
            stop_index = j;
            break;
          }
        }
        // Logging contents
        accumulator.append("Characters:").append(doc, i, stop_index).append("\n");
        i = stop_index;
      }
    }
    return accumulator.toString();
  }
}
