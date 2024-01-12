package model.filter;

import util.ImgUtil;

import java.util.Map;

/**
 * Implementation of the ImgFilter interface that provides methods
 * for filtering images and applying dithering.
 * to them.
 */
public class ImgFilterImpl implements ImgFilter {
  @Override
  public int[][][] filterApplication(int[][][] result, double[][] filter, Map<String,
          int[][][]> imageMap) {
    int filterSize = filter.length;
    if (filterSize % 2 == 0 || filterSize != filter[0].length) {
      throw new IllegalArgumentException("Error: given filter must have odd dimensions " +
              "and be square");
    }
    int height = result.length;
    int width = result[0].length;
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int[][][] helper = helperFilter(row, col, result, filterSize);
        result[row][col] = pixelNew(helper, filter);
      }
    }
    return join(result);
  }

  @Override
  public int[][][] filterDitherApplication(int[][][] result, ImgUtil image) {
    int width = image.getWidth();
    int height = image.getHeight();

    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        int oldColor = result[row][column][0];
        int newColor = ditherHelper(oldColor);
        int error = oldColor - newColor;
        result[row][column] = new int[]{newColor, newColor, newColor};

        int[][] errorPositions = {
                {0, 1, 7}, {1, -1, 3}, {1, 0, 5}, {1, 1, 1}
        };
        for (int[] errorPos : errorPositions) {
          int rowOffset = errorPos[0];
          int colOffset = errorPos[1];
          double errorFactor = errorPos[2] / 16.0;
          try {
            int[] currPixel = result[row + rowOffset][column + colOffset];
            int newError = (int) Math.round(errorFactor * error);
            int[] newPixel = new int[]{currPixel[0] + newError, currPixel[1] + newError,
                    currPixel[2] + newError};
            result[row + rowOffset][column + colOffset] = newPixel;
          } catch (ArrayIndexOutOfBoundsException ignored) {
          }
        }
      }
    }

    return result;
  }


  /**
   * Applies a filter to a pixel based on a helper array and a filter matrix.
   *
   * @param helper The helper array.
   * @param filter The filter matrix.
   * @return An array of three integers representing the new pixel values.
   */
  private int[] pixelNew(int[][][] helper, double[][] filter) {
    int[] newPixel = {0, 0, 0};
    int helperSize = helper.length;
    for (int i = 0; i < helperSize; i++) {
      for (int j = 0; j < helperSize; j++) {
        newPixel[0] += Math.round(helper[i][j][0] * filter[i][j]);
        newPixel[1] += Math.round(helper[i][j][1] * filter[i][j]);
        newPixel[2] += Math.round(helper[i][j][2] * filter[i][j]);
      }
    }
    return newPixel;
  }

  /**
   * Creates a helper array based on the row, column, image, and size specified.
   *
   * @param row    The row of the pixel being processed.
   * @param column The column of the pixel being processed.
   * @param image  The image being processed.
   * @param size   The size of the helper array.
   * @return A 3D integer array representing the helper array.
   */
  private int[][][] helperFilter(int row, int column, int[][][] image, int size) {
    int[][][] helper = new int[size][size][3];
    int bounds = size / 2;

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        int x = row - bounds + i;
        int y = column - bounds + j;

        if (x >= 0 && x < image.length && y >= 0 && y < image[0].length) {
          helper[i][j] = image[x][y];
        } else {
          helper[i][j] = new int[]{0, 0, 0};
        }
      }
    }
    return helper;
  }

  /**
   * Joins an image by ensuring that all pixel values fall within the range of 0 and 255.
   *
   * @param image The image being processed.
   * @return A 3D integer array representing the processed image.
   */
  private int[][][] join(int[][][] image) {
    int min = 0;
    int max = 255;

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        for (int k = 0; k < 3; k++) {
          image[i][j][k] = Math.max(min, Math.min(max, image[i][j][k]));
        }
      }
    }

    return image;
  }

  /**
   * Helper method for dithering an image. Determines the appropriate value for a pixel
   * based on its color value.
   *
   * @param color The color value of the pixel being processed.
   * @return The new color value for the pixel.
   */
  private int ditherHelper(int color) {
    return (255 - color > color) ? 0 : 255;
  }
}
