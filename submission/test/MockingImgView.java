import view.ImgView;

/**
 * This class represents a MockingImgView, which is an implementation of the ImgView interface.
 * It logs any operation performed on the image using a StringBuilder.
 */
public class MockingImgView implements ImgView {

  private final StringBuilder logger;

  /**
   * Creates a new instance of MockingImgView with an empty StringBuilder for logging operations.
   */
  public MockingImgView() {
    this.logger = new StringBuilder();
  }

  @Override
  public void displayStatus(String image, String operation) {
    logger.append("Successful completion of the operation\n");
  }

  @Override
  public String toString() {
    return "" + logger;
  }
}
