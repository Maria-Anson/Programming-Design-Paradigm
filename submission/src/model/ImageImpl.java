package model;

/**
 * Implementation of the Image interface for representing an image.
 */
public class ImageImpl implements Image {
  private final int height;
  private final int width;
  private final int maxValue;
  private final int[][][] image;

  /**
   * Constructs a new ImageImpl instance with the given properties.
   *
   * @param height   the height of the image
   * @param width    the width of the image
   * @param maxValue the maximum value of the image
   * @param image    the image data as a 3D array of integers
   */
  public ImageImpl(int height, int width, int maxValue, int[][][] image) {
    this.height = height;
    this.width = width;
    this.maxValue = maxValue;
    this.image = image;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getMaxValue() {
    return maxValue;
  }

  @Override
  public int[][][] getImage() {
    return image;
  }
}