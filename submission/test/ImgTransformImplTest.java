import model.transform.ImgTransformImpl;

import org.junit.Test;
import org.junit.Before;

import util.ImgUtil;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


/**
 * This class provides test cases for the ImgTransformImpl class.
 * It tests the image transformation functionalities provided by the ImgTransformImpl class.
 */
public class ImgTransformImplTest {
  private ImgTransformImpl imgTransform;
  private int[][][] inputImage;
  private double[][] testMatrix;

  /**
   * Sets up the testing environment by initializing the objects and variables needed for testing.
   */
  @Before
  public void setUp() {
    imgTransform = new ImgTransformImpl();
    inputImage = new int[][][]{{{100, 50, 200}, {75, 225, 125}}, {{25, 175, 75}, {150, 100, 225}}};
    testMatrix = new double[][]{{0.5, 0.5, 0}, {0, 0.5, 0.5}, {0.5, 0, 0.5}};
  }

  @Test
  public void testTransformImpl() {
    int[][][] expectedResult = new int[][][]{{{75, 125, 138}, {150, 175, 138}}, {{100, 125, 88},
            {125, 163, 175}}};
    int[][][] result = imgTransform.transformImpl(inputImage, testMatrix, new ImgUtil());
    assertArrayEquals(expectedResult, result);
  }

  @Test
  public void testTransformHelper() {
    int[] pixel = new int[]{100, 50, 200};
    double[] filter = new double[]{0.5, 0.5, 0};
    double expectedResult = 75;
    double result = transformHelper(pixel, filter);
    assertEquals(expectedResult, result, 0);
  }

  @Test
  public void testTransformHelperWithNegativePixel() {
    int[] pixel = new int[]{-50, 200, 75};
    double[] filter = new double[]{0.5, 0.5, 0};
    double expectedResult = 75.0;
    double result = transformHelper(pixel, filter);
    assertEquals(expectedResult, result, 0);
  }

  @Test
  public void testTransformHelperWithFilterGreaterThanOne() {
    int[] pixel = new int[]{100, 50, 200};
    double[] filter = new double[]{1.5, 0.5, 0};
    double expectedResult = 175.0;
    double result = transformHelper(pixel, filter);
    assertEquals(expectedResult, result, 0);
  }

  @Test
  public void testTransformHelperWithFilterLessThanZero() {
    int[] pixel = new int[]{100, 50, 200};
    double[] filter = new double[]{-0.5, 0.5, 0};
    double expectedResult = -25;
    double result = transformHelper(pixel, filter);
    assertEquals(expectedResult, result, 0);
  }

  private double transformHelper(int[] pixel, double[] filter) {
    double newPixel = 0;
    for (int i = 0; i < filter.length; i++) {
      newPixel += pixel[i] * filter[i];
    }
    return newPixel;
  }
}
