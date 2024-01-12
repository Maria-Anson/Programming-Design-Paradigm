/**
 * XMLValidator uses the AbstractXML class and checks for different conditions to ensure if the
 * document is valid, invalid or incomplete  based on the current document.
 *
 */
public class XMLValidator extends AbstractXML {

  /**
   * This is a constructor that initializes the XML document to be "".
   */
  public XMLValidator() {
    super();
  }

  @Override
  public String output() {
    if (this.xmlDoc.equals("")) {
      return "Status:Empty";
    }
    return this.status;
  }
}
