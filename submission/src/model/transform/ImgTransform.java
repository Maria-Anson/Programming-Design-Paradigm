package model.transform;

import util.ImgUtil;

/**
 * Interface for image transformation.
 */
public interface ImgTransform {

  /**
   * Transforms an image using the given matrix.
   *
   * @param result the image to transform
   * @param matrix the transformation matrix
   * @param image  the ImgUtil instance containing the image data
   * @return the transformed image as a 3D array of integers
   */
  int[][][] transformImpl(int[][][] result, double[][] matrix, ImgUtil image);

}