package model.filter;

import util.ImgUtil;

import java.util.Map;

/**
 * The ImgFilter interface provides a contract for classes that represent an image filter.
 * It includes methods for applying various filters to a given image, represented
 * by an array of integer values.
 */
public interface ImgFilter {

  /**
   * Applies filter to a given image by transforming the provided pixels using the
   * given filter. This method creates a new image from the operation performed.
   *
   * @param result   the image that the filter will be applied to, represented by array of integer.
   * @param filter   the filter that is applied to the image, represented by a 2D array of values.
   * @param imageMap a map that contains image data represented by an array of integer values.
   * @return a new image object that includes an array of pixels that represent image.
   */
  int[][][] filterApplication(int[][][] result, double[][] filter,
                              Map<String, int[][][]> imageMap);

  /**
   * Applies a dither filter to a given image by transforming the provided pixels using
   * the dither matrix. This method creates a new image from the operation performed.
   *
   * @param result the image that the filter will be applied to, represented by an array of values.
   * @param image  an instance of ImgUtil that contains image data represented by array of values.
   * @return a new image object that includes an array of pixels that represent the dithered image.
   */
  int[][][] filterDitherApplication(int[][][] result, ImgUtil image);

}