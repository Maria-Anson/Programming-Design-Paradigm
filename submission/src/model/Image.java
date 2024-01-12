package model;

/**
 * Interface for representing an image.
 */
public interface Image {

  /**
   * Gets the height of the image.
   *
   * @return the height of the image
   */
  int getHeight();

  /**
   * Gets the width of the image.
   *
   * @return the width of the image
   */
  int getWidth();

  /**
   * Gets the maximum value of the image.
   *
   * @return the maximum value of the image
   */
  int getMaxValue();

  /**
   * Gets the image data as a 3D array of integers.
   *
   * @return the image data as a 3D array of integers
   */
  int[][][] getImage();

}
