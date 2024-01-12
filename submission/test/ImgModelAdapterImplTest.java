import model.Image;
import model.ImgModel;
import model.ImgModelAdapterImpl;
import model.ImgModelAdaptive;
import util.ImgUtil;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


import java.io.IOException;
import java.util.Arrays;


import org.junit.Before;
import org.junit.Test;

import exception.ValidCheckedException;

import model.ImgModelImpl;

/**
 * This class contains unit tests for the ImgModelAdapterImpl class.
 */
public class ImgModelAdapterImplTest {
  private final ImgUtil io = new ImgUtil();
  private Image newImage;
  private ImgModelAdaptive img2;


  /**
   * This method is called before each test in the class to initialize objects.
   */
  @Before
  public void setup() {
    ImgModel model = new ImgModelImpl();
    img2 = new ImgModelAdapterImpl(model);
  }

  @Test
  public void validTestBrighten() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    newImage = img2.load("test/res/pixel.ppm", "pixel", newImage);
    Image data2 = img2.applyBrighten(10, "pixel", "pixel-bright");
    int[][][] data = {
            {{235, 160, 14}, {245, 225, 21}, {10, 225, 110}, {255, 210, 17}},
            {{235, 210, 16}, {225, 255, 15}, {200, 16, 15}, {10, 102, 255}},
            {{10, 178, 160}, {10, 156, 200}, {170, 32, 190}, {235, 255, 11}},
            {{121, 89, 170}, {128, 255, 98}, {109, 98, 64}, {10, 70, 200}}
    };
    assertArrayEquals(data, data2.getImage());
  }

  @Test
  public void validTestVFlip() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    newImage = img2.load("test/res/pixel.ppm", "pixel", newImage);
    Image data2 = img2.applyVFlip("pixel", "pixel-vflip");
    int[][][] data = {
            {{111, 79, 160}, {118, 245, 88}, {99, 88, 54}, {0, 60, 190}},
            {{0, 168, 150}, {0, 146, 190}, {160, 22, 180}, {225, 255, 1}},
            {{225, 200, 6}, {215, 255, 5}, {190, 6, 5}, {0, 92, 296}},
            {{225, 150, 4}, {235, 215, 11}, {0, 215, 100}, {245, 200, 7}}
    };
    assertArrayEquals(data, data2.getImage());
  }

  @Test
  public void validTestHFlip() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    newImage = img2.load("test/res/pixel.ppm", "pixel", newImage);
    Image data2 = img2.applyHFlip("pixel", "pixel-hflip");
    int[][][] data = {
            {{245, 200, 7}, {0, 215, 100}, {235, 215, 11}, {225, 150, 4}},
            {{0, 92, 296}, {190, 6, 5}, {215, 255, 5}, {225, 200, 6}},
            {{225, 255, 1}, {160, 22, 180}, {0, 146, 190}, {0, 168, 150}},
            {{0, 60, 190}, {99, 88, 54}, {118, 245, 88}, {111, 79, 160}}
    };
    assertArrayEquals(data, data2.getImage());
  }

  @Test
  public void validTestGreyscale() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    newImage = img2.load("test/res/pixel.ppm", "pixel", newImage);
    Image data2 = img2.applyGreyscaleComponent("luma-component", "pixel",
            "pixel-grey");
    int[][][] data = {
            {{155, 155, 155}, {204, 204, 204}, {160, 160, 160}, {195, 195, 195}},
            {{191, 191, 191}, {228, 228, 228}, {45, 45, 45}, {87, 87, 87}},
            {{130, 130, 130}, {118, 118, 118}, {62, 62, 62}, {230, 230, 230}},
            {{91, 91, 91}, {206, 206, 206}, {87, 87, 87}, {56, 56, 56}}
    };
    assertArrayEquals(data, data2.getImage());
  }

  @Test
  public void validTestSplit() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    newImage = img2.load("test/res/pixel.ppm", "pixel", newImage);
    Image data2 = img2.applyRGBSplit("pixel",
            "pixel-red", "pixel-green", "pixel-blue");
    int[][][] data = {
            {{225, 225, 225}, {235, 235, 235}, {0, 0, 0}, {245, 245, 245}},
            {{225, 225, 225}, {215, 215, 215}, {190, 190, 190}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {160, 160, 160}, {225, 225, 225}},
            {{111, 111, 111}, {118, 118, 118}, {99, 99, 99}, {0, 0, 0}}
    };
    assertArrayEquals(data, data2.getImage());
  }

  @Test
  public void validTestCombine() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    newImage = img2.load("test/res/pixel.ppm", "pixel", newImage);
    img2.applyRGBSplit("pixel",
            "pixel-red", "pixel-green", "pixel-blue");
    Image data2 = img2.applyRGBCombine("pixel",
            "pixel-red", "pixel-green", "pixel-blue");
    String actualData = Arrays.deepToString(data2.getImage());
    String data = "[[[225, 150, 4], [235, 215, 11], [0, 215, 100], [245, 200, 7]], " +
            "[[225, 200, 6], [215, 255, 5], [190, 6, 5], [0, 92, 296]], " +
            "[[0, 168, 150], [0, 146, 190], [160, 22, 180], [225, 255, 1]], " +
            "[[111, 79, 160], [118, 245, 88], [99, 88, 54], [0, 60, 190]]]";
    assertEquals(data, actualData);
  }

  @Test
  public void validTestBlur() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    newImage = img2.load("test/res/pixel.ppm", "pixel", newImage);
    Image data2 = img2.applyBlur("pixel",
            "pixel-blur");
    int[][][] data = {
            {{126, 106, 3}, {128, 139, 17}, {84, 119, 48}, {84, 77, 45}},
            {{107, 134, 35}, {130, 143, 55}, {122, 89, 90}, {69, 84, 105}},
            {{42, 111, 95}, {84, 128, 122}, {125, 109, 106}, {95, 109, 59}},
            {{53, 73, 71}, {71, 111, 66}, {61, 73, 71}, {28, 45, 71}}
    };
    assertArrayEquals(data, data2.getImage());
  }

  @Test
  public void validTestSharpen() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    newImage = img2.load("test/res/pixel.ppm", "pixel", newImage);
    Image data2 = img2.applySharpen("pixel",
            "pixel-sharpen");
    int[][][] data = {
            {{255, 248, 0}, {255, 255, 0}, {144, 255, 101}, {204, 175, 70}},
            {{255, 255, 0}, {255, 255, 13}, {255, 255, 128}, {159, 186, 255}},
            {{77, 255, 220}, {255, 255, 255}, {255, 255, 255}, {255, 241, 225}},
            {{0, 138, 242}, {115, 255, 255}, {185, 214, 255}, {33, 21, 255}}
    };
    assertArrayEquals(data, data2.getImage());
  }

  @Test
  public void validTestSepia() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    newImage = img2.load("test/res/pixel.ppm", "pixel", newImage);
    Image data2 = img2.sepiaTransform("pixel",
            "pixel-sepia");
    int[][][] data = {
            {{205, 175, 150}, {255, 238, 198}, {184, 229, 185}, {251, 226, 190}},
            {{243, 223, 186}, {255, 255, 206}, {80, 33, 40}, {127, 157, 157}},
            {{158, 196, 167}, {148, 184, 163}, {114, 85, 100}, {255, 255, 206}},
            {{135, 128, 126}, {251, 255, 216}, {117, 110, 98}, {82, 102, 102}}
    };
    assertArrayEquals(data, data2.getImage());
  }

  @Test
  public void validTestDither() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    newImage = img2.load("test/res/pixel.ppm", "pixel", newImage);
    Image data2 = img2.ditherTransform("pixel",
            "pixel-dither");
    int[][][] data = {
            {{255, 255, 255}, {255, 255, 255}, {0, 0, 0}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {255, 255, 255}},
            {{0, 0, 0}, {255, 255, 255}, {0, 0, 0}, {0, 0, 0}}};
    assertArrayEquals(data, data2.getImage());
  }
}