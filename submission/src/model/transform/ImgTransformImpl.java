package model.transform;

import util.ImgUtil;

/**
 * Implementation of the ImgTransform interface for image transformation.
 */
public class ImgTransformImpl implements ImgTransform {

  @Override
  public int[][][] transformImpl(int[][][] result, double[][] matrix, ImgUtil image) {
    int height = result.length;
    int width = result[0].length;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          int newPixel = (int) Math.round(transformHelper(result[i][j], matrix[k]));
          result[i][j][k] = Math.min(Math.max(0, newPixel), 255);
        }
      }
    }
    return result;
  }

  /**
   * A helper method for transforming a single pixel using a given filter.
   */
  private double transformHelper(int[] pixel, double[] filter) {
    double newPixel = 0;
    for (int i = 0; i < filter.length; i++) {
      newPixel += pixel[i] * filter[i];
    }
    return newPixel;
  }
}
