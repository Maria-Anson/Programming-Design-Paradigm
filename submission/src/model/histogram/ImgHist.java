package model.histogram;

import model.Image;

/**
 * The ImgHist interface defines the method signature for calculating a histogram of an Image.
 */
public interface ImgHist {

  /**
   * Calculates the histogram of the specified Image.
   *
   * @param image the Image for which to calculate the histogram
   * @return 2D array representing histogram data, where first dimension represents color channel
   */
  int[][] calculateHistogram(Image image);
}
