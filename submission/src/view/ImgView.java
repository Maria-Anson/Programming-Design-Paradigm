package view;

/**
 * This interface represents an image view that can display the status of a specific
 * image operation.
 */
public interface ImgView {

  /**
   * Displays the status of an image operation on the specified image.
   *
   * @param image     the name of the image being operated on
   * @param operation the name of the operation being performed on the image
   */
  void displayStatus(String image, String operation);
}
