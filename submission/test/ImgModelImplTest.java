import static org.junit.Assert.assertArrayEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import model.Image;

import org.junit.Before;
import org.junit.Test;

import exception.ValidCheckedException;
import model.ImgModel;
import model.ImgModelImpl;
import util.ImgUtil;

/**
 * This class contains JUnit tests for the ImgModelImpl class.
 */
public class ImgModelImplTest {

  private final ImgUtil io = new ImgUtil();
  private ImgModel img1;
  private Image newImage;

  /**
   * This method is called before each test in the class to initialize ImgModelImpl objects.
   */
  @Before
  public void setup() {
    img1 = new ImgModelImpl();
  }

  @Test
  public void validTestBrighten() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyBrighten(10, "pixel", "pixel-bright");
    newImage = img1.savePPM("test/res/pixel-bright.ppm", "pixel-bright");
    io.writePPM("test/res/pixel-bright.ppm", newImage);
    int[][][] data = {
            {{235, 160, 14}, {245, 225, 21}, {10, 225, 110}, {255, 210, 17}},
            {{235, 210, 16}, {225, 255, 15}, {200, 16, 15}, {10, 102, 255}},
            {{10, 178, 160}, {10, 156, 200}, {170, 32, 190}, {235, 255, 11}},
            {{121, 89, 170}, {128, 255, 98}, {109, 98, 64}, {10, 70, 200}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestBrightenMax() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyBrighten(350, "pixel", "pixel-brightMax");
    newImage = img1.savePPM("test/res/pixel-brightMax.ppm", "pixel-brightMax");
    io.writePPM("test/res/pixel-brightMax.ppm", newImage);
    int[][][] data = {
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}, {255, 255, 255}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestDarken() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyBrighten(-20, "pixel", "pixel-darken");
    newImage = img1.savePPM("test/res/pixel-darken.ppm", "pixel-darken");
    io.writePPM("test/res/pixel-darken.ppm", newImage);
    int[][][] data = {
            {{205, 130, 0}, {215, 195, 0}, {0, 195, 80}, {225, 180, 0}},
            {{205, 180, 0}, {195, 235, 0}, {170, 0, 0}, {0, 72, 255}},
            {{0, 148, 130}, {0, 126, 170}, {140, 2, 160}, {205, 235, 0}},
            {{91, 59, 140}, {98, 225, 68}, {79, 68, 34}, {0, 40, 170}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestDarkenMax() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyBrighten(-350, "pixel", "pixel-darkenMax");
    newImage = img1.savePPM("test/res/pixel-darkenMax.ppm", "pixel-darkenMax");
    io.writePPM("test/res/pixel-darkenMax.ppm", newImage);
    int[][][] data = {
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestDarkenBrighten() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyBrighten(-20, "pixel", "pixel-darken");
    img1.savePPM("test/res/pixel-darken.ppm", "pixel-darken");
    img1.applyBrighten(70, "pixel-darken", "pixel-darken-brighten");
    newImage = img1.savePPM("test/res/pixel-darken-brighten.ppm",
            "pixel-darken-brighten");
    io.writePPM("test/res/pixel-darken-brighten.ppm", newImage);
    int[][][] data = {
            {{255, 200, 70}, {255, 255, 70}, {70, 255, 150}, {255, 250, 70}},
            {{255, 250, 70}, {255, 255, 70}, {240, 70, 70}, {70, 142, 255}},
            {{70, 218, 200}, {70, 196, 240}, {210, 72, 230}, {255, 255, 70}},
            {{161, 129, 210}, {168, 255, 138}, {149, 138, 104}, {70, 110, 240}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test(expected = ValidCheckedException.class)
  public void testInvalidImagePassedToBrightenFileName() throws ValidCheckedException,
          IOException, NullPointerException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyBrighten(50, "cpix11123", "colorMatrix-bright");
  }

  @Test(expected = ValidCheckedException.class)
  public void testInvalidBrightenArguments() throws ValidCheckedException, IOException {
    newImage = io.readPPM("test/res/dot.ppm");
    img1.load("test/res/dot.ppm", "bdot", newImage);
    img1.applyBrighten(89, "dot-bright", "bdot");
  }

  @Test(expected = FileNotFoundException.class)
  public void testInvalidLoadWrongFileFormat() throws ValidCheckedException, IOException {
    newImage = io.readPPM("test/res/dot.xxx");
    img1.load("test/res/dot.xxx", "bdot", newImage);
    img1.applyBrighten(89, "dot-bright", "bdot");
  }

  @Test
  public void testLoadingOfFile() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/dot.ppm");
    img1.load("test/res/dot.ppm", "dot", newImage);
    int[][][] modelArrayImage = img1.deepCopy("dot");
    int[][][] data = {
            {{245, 200, 7}, {0, 215, 100}, {235, 215, 11}, {225, 150, 4}},
            {{0, 92, 296}, {190, 6, 5}, {215, 255, 5}, {225, 200, 6}},
            {{225, 255, 1}, {160, 22, 180}, {0, 146, 190}, {0, 168, 150}},
            {{0, 60, 190}, {99, 88, 54}, {118, 245, 88}, {111, 79, 160}}
    };
    assertArrayEquals(data, modelArrayImage);
  }

  @Test(expected = FileNotFoundException.class)
  public void testLoadingOfFileNotExist() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/ram.ppm");
    img1.load("test/res/ram.ppm", "dot", newImage);
    int[][][] modelArrayImage = img1.deepCopy("dot");
    int[][][] data = {
            {{245, 200, 7}, {0, 215, 100}, {235, 215, 11}, {225, 150, 4}},
            {{0, 92, 296}, {190, 6, 5}, {215, 255, 5}, {225, 200, 6}},
            {{225, 255, 1}, {160, 22, 180}, {0, 146, 190}, {0, 168, 150}},
            {{0, 60, 190}, {99, 88, 54}, {118, 245, 88}, {111, 79, 160}}
    };
    assertArrayEquals(data, modelArrayImage);
  }

  @Test
  public void validTestHFlip() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyHFlip("pixel", "pixel-hflip");
    newImage = img1.savePPM("test/res/pixel-hflip.ppm", "pixel-hflip");
    io.writePPM("test/res/pixel-hflip.ppm", newImage);
    int[][][] data = {
            {{245, 200, 7}, {0, 215, 100}, {235, 215, 11}, {225, 150, 4}},
            {{0, 92, 296}, {190, 6, 5}, {215, 255, 5}, {225, 200, 6}},
            {{225, 255, 1}, {160, 22, 180}, {0, 146, 190}, {0, 168, 150}},
            {{0, 60, 190}, {99, 88, 54}, {118, 245, 88}, {111, 79, 160}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestVFlip() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyVFlip("pixel", "pixel-vflip");
    newImage = img1.savePPM("test/res/pixel-vflip.ppm", "pixel-vflip");
    io.writePPM("test/res/pixel-vflip.ppm", newImage);
    int[][][] data = {
            {{111, 79, 160}, {118, 245, 88}, {99, 88, 54}, {0, 60, 190}},
            {{0, 168, 150}, {0, 146, 190}, {160, 22, 180}, {225, 255, 1}},
            {{225, 200, 6}, {215, 255, 5}, {190, 6, 5}, {0, 92, 296}},
            {{225, 150, 4}, {235, 215, 11}, {0, 215, 100}, {245, 200, 7}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestHFlipVFlip() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyHFlip("pixel", "pixel-hflip");
    img1.applyVFlip("pixel-hflip", "pixel-hflip-vflip");
    newImage = img1.savePPM("test/res/pixel-hflip-vflip.ppm", "pixel-hflip-vflip");
    io.writePPM("test/res/pixel-hflip-vflip.ppm", newImage);
    int[][][] data = {
            {{0, 60, 190}, {99, 88, 54}, {118, 245, 88}, {111, 79, 160}},
            {{225, 255, 1}, {160, 22, 180}, {0, 146, 190}, {0, 168, 150}},
            {{0, 92, 296}, {190, 6, 5}, {215, 255, 5}, {225, 200, 6}},
            {{245, 200, 7}, {0, 215, 100}, {235, 215, 11}, {225, 150, 4}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestVFlipHFlip() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyVFlip("pixel", "pixel-vflip");
    img1.applyHFlip("pixel-vflip", "pixel-vflip-hflip");
    newImage = img1.savePPM("test/res/pixel-vflip-hflip.ppm", "pixel-vflip-hflip");
    io.writePPM("test/res/pixel-vflip-hflip.ppm", newImage);
    int[][][] data = {
            {{0, 60, 190}, {99, 88, 54}, {118, 245, 88}, {111, 79, 160}},
            {{225, 255, 1}, {160, 22, 180}, {0, 146, 190}, {0, 168, 150}},
            {{0, 92, 296}, {190, 6, 5}, {215, 255, 5}, {225, 200, 6}},
            {{245, 200, 7}, {0, 215, 100}, {235, 215, 11}, {225, 150, 4}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test(expected = ValidCheckedException.class)
  public void TestVFlipHFlipInvalidWithWrongFilename() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyVFlip("pixel", "pixel-vflip");
    img1.applyHFlip("pixel-flipper", "pixel-vflip-hflip");
    newImage = img1.savePPM("test/res/pixel-vflip-hflip.ppm", "pixel-vflip-hflip");
    int[][][] data = {
            {{0, 60, 190}, {99, 88, 54}, {118, 245, 88}, {111, 79, 160}},
            {{225, 255, 1}, {160, 22, 180}, {0, 146, 190}, {0, 168, 150}},
            {{0, 92, 296}, {190, 6, 5}, {215, 255, 5}, {225, 200, 6}},
            {{245, 200, 7}, {0, 215, 100}, {235, 215, 11}, {225, 150, 4}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test(expected = ValidCheckedException.class)
  public void testInvalidVFlip() throws ValidCheckedException {
    img1.applyVFlip("dot-xyz", "dotty-vertical");
  }

  @Test(expected = ValidCheckedException.class)
  public void testInvalidHFlip() throws ValidCheckedException {
    img1.applyVFlip("dot-xyz",
            "dotty-horizontal");
  }

  @Test
  public void validTestGreyscaleLuma() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyGreyscaleComponent("luma-component",
            "pixel", "pixel-luma");
    newImage = img1.savePPM("test/res/pixel-luma.ppm", "pixel-luma");
    io.writePPM("test/res/pixel-luma.ppm", newImage);
    int[][][] data = {
            {{155, 155, 155}, {204, 204, 204}, {160, 160, 160}, {195, 195, 195}},
            {{191, 191, 191}, {228, 228, 228}, {45, 45, 45}, {87, 87, 87}},
            {{130, 130, 130}, {118, 118, 118}, {62, 62, 62}, {230, 230, 230}},
            {{91, 91, 91}, {206, 206, 206}, {87, 87, 87}, {56, 56, 56}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestGreyscaleIntensity() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyGreyscaleComponent("intensity-component", "pixel",
            "pixel-intensity");
    newImage = img1.savePPM("test/res/pixel-intensity.ppm", "pixel-intensity");
    io.writePPM("test/res/pixel-intensity.ppm", newImage);
    int[][][] data = {
            {{126, 126, 126}, {153, 153, 153}, {105, 105, 105}, {150, 150, 150}},
            {{143, 143, 143}, {158, 158, 158}, {67, 67, 67}, {129, 129, 129}},
            {{106, 106, 106}, {112, 112, 112}, {120, 120, 120}, {160, 160, 160}},
            {{116, 116, 116}, {150, 150, 150}, {80, 80, 80}, {83, 83, 83}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestGreyscaleValue() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyGreyscaleComponent("value-component", "pixel",
            "pixel-value");
    newImage = img1.savePPM("test/res/pixel-value.ppm", "pixel-value");
    io.writePPM("test/res/pixel-value.ppm", newImage);
    int[][][] data = {
            {{225, 225, 225}, {235, 235, 235}, {215, 215, 215}, {245, 245, 245}},
            {{225, 225, 225}, {255, 255, 255}, {190, 190, 190}, {296, 296, 296}},
            {{168, 168, 168}, {190, 190, 190}, {180, 180, 180}, {255, 255, 255}},
            {{160, 160, 160}, {245, 245, 245}, {99, 99, 99}, {190, 190, 190}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestGreyscaleRed() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyGreyscaleComponent("red-component", "pixel",
            "pixel-red");
    newImage = img1.savePPM("test/res/pixel-red.ppm", "pixel-red");
    io.writePPM("test/res/pixel-red.ppm", newImage);
    int[][][] data = {
            {{225, 225, 225}, {235, 235, 235}, {0, 0, 0}, {245, 245, 245}},
            {{225, 225, 225}, {215, 215, 215}, {190, 190, 190}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {160, 160, 160}, {225, 225, 225}},
            {{111, 111, 111}, {118, 118, 118}, {99, 99, 99}, {0, 0, 0}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestGreyscaleBlue() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyGreyscaleComponent("blue-component", "pixel",
            "pixel-blue");
    newImage = img1.savePPM("test/res/pixel-blue.ppm", "pixel-blue");
    io.writePPM("test/res/pixel-blue.ppm", newImage);
    int[][][] data = {
            {{4, 4, 4}, {11, 11, 11}, {100, 100, 100}, {7, 7, 7}},
            {{6, 6, 6}, {5, 5, 5}, {5, 5, 5}, {296, 296, 296}},
            {{150, 150, 150}, {190, 190, 190}, {180, 180, 180}, {1, 1, 1}},
            {{160, 160, 160}, {88, 88, 88}, {54, 54, 54}, {190, 190, 190}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestGreyscaleGreen() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyGreyscaleComponent("green-component", "pixel",
            "pixel-green");
    newImage = img1.savePPM("test/res/pixel-green.ppm", "pixel-green");
    io.writePPM("test/res/pixel-green.ppm", newImage);
    int[][][] data = {
            {{150, 150, 150}, {215, 215, 215}, {215, 215, 215}, {200, 200, 200}},
            {{200, 200, 200}, {255, 255, 255}, {6, 6, 6}, {92, 92, 92}},
            {{168, 168, 168}, {146, 146, 146}, {22, 22, 22}, {255, 255, 255}},
            {{79, 79, 79}, {245, 245, 245}, {88, 88, 88}, {60, 60, 60}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void TestAllMethodsForGreyScale() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyGreyscaleComponent("blue-component", "pixel",
            "pixel-blue");
    img1.applyGreyscaleComponent("green-component", "pixel-blue",
            "pixel-blue-green");
    img1.applyGreyscaleComponent("red-component", "pixel-blue-green",
            "pixel-blue-green-red");
    img1.applyGreyscaleComponent("luma-component", "pixel-blue-green-red",
            "pixel-blue-green-red-luma");
    img1.applyGreyscaleComponent("intensity-component",
            "pixel-blue-green-red-luma",
            "pixel-blue-green-red-luma-intensity");
    img1.applyGreyscaleComponent("value-component",
            "pixel-blue-green-red-luma-intensity",
            "pixel-blue-green-red-luma-intensity-value");
    newImage = img1.savePPM("test/res/pixel-blue-green-red-luma-intensity-value.ppm",
            "pixel-blue-green-red-luma-intensity-value");
    io.writePPM("test/res/pixel-blue-green-red-luma-intensity-value.ppm", newImage);
    int[][][] data = {
            {{4, 4, 4}, {11, 11, 11}, {100, 100, 100}, {6, 6, 6}},
            {{6, 6, 6}, {4, 4, 4}, {4, 4, 4}, {296, 296, 296}},
            {{150, 150, 150}, {189, 189, 189}, {180, 180, 180}, {1, 1, 1}},
            {{159, 159, 159}, {88, 88, 88}, {54, 54, 54}, {189, 189, 189}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test(expected = ValidCheckedException.class)
  public void testInvalidGreyscaleInvalidFilenameValue() throws ValidCheckedException {
    img1.applyGreyscaleComponent("value-component", "dot-xyz",
            "dot-greyscale");
  }

  @Test(expected = ValidCheckedException.class)
  public void testInvalidGreyscaleInvalidFilenameIntensity() throws ValidCheckedException {
    img1.applyGreyscaleComponent("intensity-component", "dot-xyz",
            "dot-greyscale");
  }

  @Test(expected = ValidCheckedException.class)
  public void testInvalidGreyscaleInvalidFilenameLuma() throws ValidCheckedException {
    img1.applyGreyscaleComponent("luma-component",
            "dot-xyz", "dot-greyscale");
  }

  @Test(expected = ValidCheckedException.class)
  public void testInvalidGreyscaleInvalidFilenameRed() throws ValidCheckedException {
    img1.applyGreyscaleComponent("red-component",
            "dot-xyz", "dot-greyscale");
  }

  @Test(expected = ValidCheckedException.class)
  public void testInvalidGreyscaleInvalidFilenameGreen() throws ValidCheckedException {
    img1.applyGreyscaleComponent("green-component",
            "dot-xyz", "dot-greyscale");
  }

  @Test(expected = ValidCheckedException.class)
  public void testInvalidGreyscaleInvalidFilenameBlue() throws ValidCheckedException {
    img1.applyGreyscaleComponent("blue-component",
            "dot-xyz", "dot-greyscale");
  }

  @Test(expected = ValidCheckedException.class)
  public void testInvalidGreyscaleInvalidComponent() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/dot.ppm");
    img1.load("test/res/dot.ppm", "dot", newImage);
    img1.applyGreyscaleComponent("pp-component",
            "dot", "dot-greyscale");
  }

  @Test
  public void validTestRGBSplit() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyRGBSplit("pixel", "pixel-splitRed", "pixel-splitGreen",
            "pixel-splitBlue");
    newImage = img1.savePPM("test/res/pixel-splitRed.ppm", "pixel-splitRed");
    io.writePPM("test/res/pixel-splitRed.ppm", newImage);
    int[][][] dataRed = {
            {{225, 225, 225}, {235, 235, 235}, {0, 0, 0}, {245, 245, 245}},
            {{225, 225, 225}, {215, 215, 215}, {190, 190, 190}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {160, 160, 160}, {225, 225, 225}},
            {{111, 111, 111}, {118, 118, 118}, {99, 99, 99}, {0, 0, 0}}
    };
    assertArrayEquals(dataRed, newImage.getImage());
    newImage = img1.savePPM("test/res/pixel-splitGreen.ppm", "pixel-splitGreen");
    io.writePPM("test/res/pixel-splitGreen.ppm", newImage);
    newImage = io.readPPM("test/res/pixel-splitGreen.ppm");
    int[][][] dataGreen = {
            {{150, 150, 150}, {215, 215, 215}, {215, 215, 215}, {200, 200, 200}},
            {{200, 200, 200}, {255, 255, 255}, {6, 6, 6}, {92, 92, 92}},
            {{168, 168, 168}, {146, 146, 146}, {22, 22, 22}, {255, 255, 255}},
            {{79, 79, 79}, {245, 245, 245}, {88, 88, 88}, {60, 60, 60}}
    };
    assertArrayEquals(dataGreen, newImage.getImage());
    newImage = img1.savePPM("test/res/pixel-splitBlue.ppm", "pixel-splitBlue");
    io.writePPM("test/res/pixel-splitBlue.ppm", newImage);
    newImage = io.readPPM("test/res/pixel-splitBlue.ppm");
    int[][][] dataBlue = {
            {{4, 4, 4}, {11, 11, 11}, {100, 100, 100}, {7, 7, 7}},
            {{6, 6, 6}, {5, 5, 5}, {5, 5, 5}, {296, 296, 296}},
            {{150, 150, 150}, {190, 190, 190}, {180, 180, 180}, {1, 1, 1}},
            {{160, 160, 160}, {88, 88, 88}, {54, 54, 54}, {190, 190, 190}}
    };
    assertArrayEquals(dataBlue, newImage.getImage());
  }

  @Test(expected = NullPointerException.class)
  public void testInvalidSplitInvalidFile() throws IOException, ValidCheckedException {
    img1.load("test/res/dot.ppm", "dot", newImage);
    img1.applyRGBSplit("dot-split", "red.ppm",
            "green,ppm", "blue.ppm");
  }

  @Test
  public void validTestSplitRBGCombine() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyRGBSplit("pixel", "pixel-splitRed", "pixel-splitGreen",
            "pixel-splitBlue");
    img1.savePPM("test/res/pixel-splitRed.ppm", "pixel-splitRed");
    img1.savePPM("test/res/pixel-splitGreen.ppm", "pixel-splitGreen");
    img1.savePPM("test/res/pixel-splitBlue.ppm", "pixel-splitBlue");
    img1.applyRGBCombine("pixel-combine", "pixel-splitRed",
            "pixel-splitGreen", "pixel-splitBlue");
    newImage = img1.savePPM("test/res/pixel-combine.ppm", "pixel-combine");
    io.writePPM("test/res/pixel-combine.ppm", newImage);
    int[][][] data = {
            {{225, 150, 4}, {235, 215, 11}, {0, 215, 100}, {245, 200, 7}},
            {{225, 200, 6}, {215, 255, 5}, {190, 6, 5}, {0, 92, 296}},
            {{0, 168, 150}, {0, 146, 190}, {160, 22, 180}, {225, 255, 1}},
            {{111, 79, 160}, {118, 245, 88}, {99, 88, 54}, {0, 60, 190}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestRBGCombineWithTwoImages() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    newImage = io.readPPM("test/res/dot.ppm");
    img1.load("test/res/dot.ppm", "dot", newImage);
    img1.applyRGBSplit("pixel", "pixel-splitRed", "pixel-splitGreen",
            "pixel-splitBlue");
    img1.applyRGBSplit("dot", "dot-splitRed", "dot-splitGreen",
            "dot-splitBlue");
    img1.applyRGBCombine("pixel-combine", "dot-splitRed",
            "pixel-splitGreen", "pixel-splitBlue");
    newImage = img1.savePPM("test/res/pixel-combine.ppm", "pixel-combine");
    io.writePPM("test/res/pixel-combine.ppm", newImage);
    int[][][] data = {
            {{245, 150, 4}, {0, 215, 11}, {235, 215, 100}, {225, 200, 7}},
            {{0, 200, 6}, {190, 255, 5}, {215, 6, 5}, {225, 92, 296}},
            {{225, 168, 150}, {160, 146, 190}, {0, 22, 180}, {0, 255, 1}},
            {{0, 79, 160}, {99, 245, 88}, {118, 88, 54}, {111, 60, 190}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test(expected = NullPointerException.class)
  public void invalidTestRBGCombine() throws IOException, ValidCheckedException {
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyRGBSplit("pixel", "pixel-splitRed", "pixel-splitGreen",
            "pixel-splitBlue");
    img1.savePPM("test/res/pixel-splitRed.ppm", "pixel-splitRed");
    img1.savePPM("test/res/pixel-splitGreen.ppm", "pixel-splitGreen");
    img1.savePPM("test/res/pixel-p.ppm", "pixel-splitBlue");
    img1.applyRGBCombine("pixel-combine", "3pixel-splitRed",
            "pixel2-splitGreen", "pixel-s5plitBlue");
    img1.savePPM("test/res/pixel-combine.ppm", "pixel-combine");
    io.writePPM("test/res/pixel-combine.ppm", newImage);
  }

  @Test(expected = NullPointerException.class)
  public void testInvalidCombineInvalidFile() throws IOException, ValidCheckedException {
    img1.load("test/res/dot.ppm", "dot", newImage);
    img1.applyRGBCombine("dot-combine", "red.ppm",
            "green,ppm", "blue.ppm");
  }


  @Test
  public void TestAllMethodsForSingleImage() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyBrighten(50, "pixel", "pixel-bright");
    img1.applyVFlip("pixel-bright", "pixel-bright-vertical");
    img1.applyHFlip("pixel-bright-vertical",
            "pixel-bright-vertical-horizontal");
    img1.applyGreyscaleComponent("blue-component",
            "pixel-bright-vertical-horizontal",
            "pixel-bright-vertical-horizontal-greyscale");
    img1.applyRGBSplit("pixel-bright-vertical-horizontal",
            "pixel-bright-vertical-horizontal-red-split",
            "pixel-bright-vertical-horizontal-green-split",
            "pixel-bright-vertical-horizontal-blue-split");
    img1.applyRGBCombine("pixel-bright-vertical-horizontal-combine",
            "pixel-bright-vertical-horizontal-red-split",
            "pixel-bright-vertical-horizontal-green-split",
            "pixel-bright-vertical-horizontal-blue-split");
    newImage = img1.savePPM("test/res/pixel-bright-vertical-horizontal-combine.ppm",
            "pixel-bright-vertical-horizontal-combine");
    io.writePPM("test/res/pixel-bright-vertical-horizontal-combine.ppm", newImage);
    int[][][] data = {
            {{50, 110, 240}, {149, 138, 104}, {168, 255, 138}, {161, 129, 210}},
            {{255, 255, 51}, {210, 72, 230}, {50, 196, 240}, {50, 218, 200}},
            {{50, 142, 255}, {240, 56, 55}, {255, 255, 55}, {255, 250, 56}},
            {{255, 250, 57}, {50, 255, 150}, {255, 255, 61}, {255, 200, 54}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void testOverwriteExistingImage() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "dot", newImage);
    newImage = io.readPPM("test/res/dot.ppm");
    img1.load("test/res/dot.ppm", "dot", newImage);
    newImage = img1.savePPM("test/res/dot2.ppm", "dot");
    io.writePPM("test/res/dot2.ppm", newImage);
    int[][][] data = {
            {{245, 200, 7}, {0, 215, 100}, {235, 215, 11}, {225, 150, 4}},
            {{0, 92, 296}, {190, 6, 5}, {215, 255, 5}, {225, 200, 6}},
            {{225, 255, 1}, {160, 22, 180}, {0, 146, 190}, {0, 168, 150}},
            {{0, 60, 190}, {99, 88, 54}, {118, 245, 88}, {111, 79, 160}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void testSavingOfFile() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/dot.ppm");
    img1.load("test/res/dot.ppm", "dot", newImage);
    newImage = img1.savePPM("test/res/dotSaved.ppm", "dot");
    io.writePPM("test/res/dotSaved.ppm", newImage);
    int[][][] data = {
            {{245, 200, 7}, {0, 215, 100}, {235, 215, 11}, {225, 150, 4}},
            {{0, 92, 296}, {190, 6, 5}, {215, 255, 5}, {225, 200, 6}},
            {{225, 255, 1}, {160, 22, 180}, {0, 146, 190}, {0, 168, 150}},
            {{0, 60, 190}, {99, 88, 54}, {118, 245, 88}, {111, 79, 160}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestBlur() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyBlur("pixel",
            "pixel-blur");
    newImage = img1.savePPM("test/res/pixel-blur.ppm", "pixel-blur");
    io.writePPM("test/res/pixel-blur.ppm", newImage);
    int[][][] data = {
            {{126, 106, 3}, {128, 139, 17}, {84, 119, 48}, {84, 77, 45}},
            {{107, 134, 35}, {130, 143, 55}, {122, 89, 90}, {69, 84, 105}},
            {{42, 111, 95}, {84, 128, 122}, {125, 109, 106}, {95, 109, 59}},
            {{53, 73, 71}, {71, 111, 66}, {61, 73, 71}, {28, 45, 71}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test(expected = ValidCheckedException.class)
  public void testInvalidBlur() throws ValidCheckedException {
    img1.applyBlur("dot-xyz", "dotty-blur");
  }

  @Test
  public void validTestSharpen() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applySharpen("pixel",
            "pixel-sharpen");
    newImage = img1.savePPM("test/res/pixel-sharpen.ppm", "pixel-sharpen");
    io.writePPM("test/res/pixel-sharpen.ppm", newImage);
    int[][][] data = {
            {{255, 248, 0}, {255, 255, 0}, {144, 255, 101}, {204, 175, 70}},
            {{255, 255, 0}, {255, 255, 13}, {255, 255, 128}, {159, 186, 255}},
            {{77, 255, 220}, {255, 255, 255}, {255, 255, 255}, {255, 241, 225}},
            {{0, 138, 242}, {115, 255, 255}, {185, 214, 255}, {33, 21, 255}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test(expected = ValidCheckedException.class)
  public void testInvalidSharpen() throws ValidCheckedException {
    img1.applySharpen("dot-xyz", "dotty-sharp");
  }

  @Test
  public void validTestSepia() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.sepiaTransform("pixel",
            "pixel-sepia");
    newImage = img1.savePPM("test/res/pixel-sepia.ppm", "pixel-sepia");
    io.writePPM("test/res/pixel-sepia.ppm", newImage);
    int[][][] data = {
            {{205, 175, 150}, {255, 238, 198}, {184, 229, 185}, {251, 226, 190}},
            {{243, 223, 186}, {255, 255, 206}, {80, 33, 40}, {127, 157, 157}},
            {{158, 196, 167}, {148, 184, 163}, {114, 85, 100}, {255, 255, 206}},
            {{135, 128, 126}, {251, 255, 216}, {117, 110, 98}, {82, 102, 102}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test(expected = ValidCheckedException.class)
  public void testInvalidSepia() throws ValidCheckedException {
    img1.sepiaTransform("dot-xyz", "dotty-sepia");
  }

  @Test
  public void validTestTransformGreyscale() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.transformGreyscale("pixel",
            "pixel-grey");
    newImage = img1.savePPM("test/res/pixel-grey.ppm", "pixel-grey");
    io.writePPM("test/res/pixel-grey.ppm", newImage);
    int[][][] data = {
            {{155, 155, 155}, {204, 204, 204}, {160, 160, 160}, {195, 195, 195}},
            {{191, 191, 191}, {228, 228, 228}, {45, 45, 45}, {87, 87, 87}},
            {{130, 130, 130}, {118, 118, 118}, {62, 62, 62}, {230, 230, 230}},
            {{91, 91, 91}, {206, 206, 206}, {87, 87, 87}, {56, 56, 56}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test(expected = ValidCheckedException.class)
  public void testInvalidGreyScale() throws ValidCheckedException {
    img1.transformGreyscale("dot-xyz", "dotty-grey");
  }

  @Test
  public void validTestDither() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.ditherTransform("pixel",
            "pixel-dither");
    newImage = img1.savePPM("test/res/pixel-dither.ppm", "pixel-dither");
    io.writePPM("test/res/pixel-dither.ppm", newImage);
    int[][][] data = {
            {{255, 255, 255}, {255, 255, 255}, {0, 0, 0}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}, {0, 0, 0}},
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {255, 255, 255}},
            {{0, 0, 0}, {255, 255, 255}, {0, 0, 0}, {0, 0, 0}}};
    assertArrayEquals(data, newImage.getImage());
  }

  @Test(expected = ValidCheckedException.class)
  public void testInvalidDither() throws ValidCheckedException {
    img1.ditherTransform("dot-xyz", "dotty-dither");
  }

  @Test
  public void validTestBlurSharpen() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyBlur("pixel",
            "pixel-blur");
    img1.applySharpen("pixel-blur",
            "pixel-blur-sharpen");
    newImage = img1.savePPM("test/res/pixel-blur-sharpen.ppm",
            "pixel-blur-sharpen");
    io.writePPM("test/res/pixel-blur-sharpen.ppm", newImage);
    int[][][] data = {
            {{162, 141, 0}, {219, 218, 2}, {165, 179, 75}, {92, 76, 71}},
            {{192, 241, 39}, {255, 255, 119}, {255, 255, 208}, {174, 160, 180}},
            {{95, 214, 147}, {255, 255, 255}, {255, 255, 255}, {190, 178, 197}},
            {{4, 82, 100}, {114, 227, 195}, {163, 200, 240}, {48, 48, 139}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestSepiaGreyscale() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.sepiaTransform("pixel",
            "pixel-sepia");
    img1.transformGreyscale("pixel-sepia",
            "pixel-sepia-grey");
    newImage = img1.savePPM("test/res/pixel-sepia-grey.ppm",
            "pixel-sepia-grey");
    io.writePPM("test/res/pixel-sepia-grey.ppm", newImage);
    int[][][] data = {
            {{179, 179, 179}, {238, 238, 238}, {216, 216, 216}, {228, 228, 228}},
            {{224, 224, 224}, {251, 251, 251}, {43, 43, 43}, {150, 150, 150}},
            {{185, 185, 185}, {174, 174, 174}, {92, 92, 92}, {251, 251, 251}},
            {{129, 129, 129}, {251, 251, 251}, {110, 110, 110}, {97, 97, 97}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestAllTransforms() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.ditherTransform("pixel",
            "pixel-dither");
    img1.applyBlur("pixel-dither",
            "pixel-dither-blur");
    img1.applySharpen("pixel-dither-blur",
            "pixel-dither-blur-sharpen");
    img1.sepiaTransform("pixel-dither-blur-sharpen",
            "pixel-dither-blur-sharpen-sepia");
    img1.transformGreyscale("pixel-dither-blur-sharpen-sepia",
            "pixel-dither-blur-sharpen-sepia-grey");
    newImage = img1.savePPM("test/res/pixel-dither-blur-sharpen-sepia-grey.ppm",
            "pixel-dither-blur-sharpen-sepia-grey");
    io.writePPM("test/res/pixel-dither-blur-sharpen-sepia-grey.ppm", newImage);
    int[][][] data = {
            {{253, 253, 253}, {253, 253, 253}, {253, 253, 253}, {141, 141, 141}},
            {{253, 253, 253}, {253, 253, 253}, {253, 253, 253}, {224, 224, 224}},
            {{137, 137, 137}, {253, 253, 253}, {253, 253, 253}, {196, 196, 196}},
            {{0, 0, 0}, {129, 129, 129}, {116, 116, 116}, {0, 0, 0}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestSepiaPPMToPNG() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.sepiaTransform("pixel",
            "pixel-sepia");
    newImage = img1.savePPM("test/res/pixel-png-sepia.png", "pixel-sepia");
    io.writePPM("test/res/pixel-png-sepia.png", newImage);
    int[][][] data = {
            {{205, 175, 150}, {255, 238, 198}, {184, 229, 185}, {251, 226, 190}},
            {{243, 223, 186}, {255, 255, 206}, {80, 33, 40}, {127, 157, 157}},
            {{158, 196, 167}, {148, 184, 163}, {114, 85, 100}, {255, 255, 206}},
            {{135, 128, 126}, {251, 255, 216}, {117, 110, 98}, {82, 102, 102}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestBlurPPMtoJPG() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applyBlur("pixel",
            "pixel-blur");
    newImage = img1.savePPM("test/res/pixel-jpg-blur.jpg", "pixel-blur");
    io.writePPM("test/res/pixel-jpg-blur.jpg", newImage);
    int[][][] data = {
            {{126, 106, 3}, {128, 139, 17}, {84, 119, 48}, {84, 77, 45}},
            {{107, 134, 35}, {130, 143, 55}, {122, 89, 90}, {69, 84, 105}},
            {{42, 111, 95}, {84, 128, 122}, {125, 109, 106}, {95, 109, 59}},
            {{53, 73, 71}, {71, 111, 66}, {61, 73, 71}, {28, 45, 71}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestSharpenPPMtoBMP() throws IOException, ValidCheckedException {
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applySharpen("pixel",
            "pixel-sharpen");
    newImage = img1.savePPM("test/res/pixel-bmp-sharpen.bmp", "pixel-sharpen");
    io.writePPM("test/res/pixel-bmp-sharpen.bmp", newImage);
    int[][][] data = {
            {{255, 248, 0}, {255, 255, 0}, {144, 255, 101}, {204, 175, 70}},
            {{255, 255, 0}, {255, 255, 13}, {255, 255, 128}, {159, 186, 255}},
            {{77, 255, 220}, {255, 255, 255}, {255, 255, 255}, {255, 241, 225}},
            {{0, 138, 242}, {115, 255, 255}, {185, 214, 255}, {33, 21, 255}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestSharpenPPMtoJPEG() throws IOException, ValidCheckedException {
    Image newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
    img1.applySharpen("pixel",
            "pixel-sharpen");
    newImage = img1.savePPM("test/res/pixel-jpeg-sharpen.jpeg", "pixel-sharpen");
    io.writePPM("test/res/pixel-jpeg-sharpen.jpeg", newImage);
    int[][][] data = {
            {{255, 248, 0}, {255, 255, 0}, {144, 255, 101}, {204, 175, 70}},
            {{255, 255, 0}, {255, 255, 13}, {255, 255, 128}, {159, 186, 255}},
            {{77, 255, 220}, {255, 255, 255}, {255, 255, 255}, {255, 241, 225}},
            {{0, 138, 242}, {115, 255, 255}, {185, 214, 255}, {33, 21, 255}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestSepiaPNGToPPM() throws IOException, ValidCheckedException {
    newImage = io.readImage("test/testImages/chase.png");
    img1.load("test/testImages/chase.png", "chase", newImage);
    img1.sepiaTransform("chase",
            "chase-sepia");
    newImage = img1.savePPM("test/res/chase-ppm-sepia.ppm", "chase-sepia");
    io.writePPM("test/res/chase-ppm-sepia.ppm", newImage);
    int[][][] data = {
            {{251, 226, 190}, {184, 229, 185}, {255, 238, 198}, {205, 175, 150}},
            {{119, 147, 144}, {80, 33, 40}, {255, 255, 206}, {243, 223, 186}},
            {{255, 255, 206}, {114, 85, 100}, {148, 184, 163}, {158, 196, 167}},
            {{82, 102, 102}, {117, 110, 98}, {251, 255, 216}, {135, 128, 126}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestSepiaPNGToJPG() throws IOException, ValidCheckedException {
    newImage = io.readImage("test/testImages/chase.png");
    img1.load("test/testImages/chase.png", "chase", newImage);
    img1.sepiaTransform("chase",
            "chase-sepia");
    newImage = img1.savePPM("test/res/chase-jpg-sepia.jpg", "chase-sepia");
    io.writePPM("test/res/chase-jpg-sepia.jpg", newImage);
    int[][][] data = {
            {{251, 226, 190}, {184, 229, 185}, {255, 238, 198}, {205, 175, 150}},
            {{119, 147, 144}, {80, 33, 40}, {255, 255, 206}, {243, 223, 186}},
            {{255, 255, 206}, {114, 85, 100}, {148, 184, 163}, {158, 196, 167}},
            {{82, 102, 102}, {117, 110, 98}, {251, 255, 216}, {135, 128, 126}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestSepiaPNGToBMP() throws IOException, ValidCheckedException {
    newImage = io.readImage("test/testImages/chase.png");
    img1.load("test/testImages/chase.png", "chase", newImage);
    img1.sepiaTransform("chase",
            "chase-sepia");
    newImage = img1.savePPM("test/res/chase-bmp-sepia.bmp", "chase-sepia");
    io.writePPM("test/res/chase-bmp-sepia.bmp", newImage);
    int[][][] data = {
            {{251, 226, 190}, {184, 229, 185}, {255, 238, 198}, {205, 175, 150}},
            {{119, 147, 144}, {80, 33, 40}, {255, 255, 206}, {243, 223, 186}},
            {{255, 255, 206}, {114, 85, 100}, {148, 184, 163}, {158, 196, 167}},
            {{82, 102, 102}, {117, 110, 98}, {251, 255, 216}, {135, 128, 126}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestSepiaPNGToJPEG() throws IOException, ValidCheckedException {
    newImage = io.readImage("test/testImages/chase.png");
    img1.load("test/testImages/chase.png", "chase", newImage);
    img1.sepiaTransform("chase",
            "chase-sepia");
    newImage = img1.savePPM("test/res/chase-jpeg-sepia.jpeg", "chase-sepia");
    io.writePPM("test/res/chase-jpeg-sepia.jpeg", newImage);
    int[][][] data = {
            {{251, 226, 190}, {184, 229, 185}, {255, 238, 198}, {205, 175, 150}},
            {{119, 147, 144}, {80, 33, 40}, {255, 255, 206}, {243, 223, 186}},
            {{255, 255, 206}, {114, 85, 100}, {148, 184, 163}, {158, 196, 167}},
            {{82, 102, 102}, {117, 110, 98}, {251, 255, 216}, {135, 128, 126}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestSepiaJPGToPPM() throws IOException, ValidCheckedException {
    newImage = io.readImage("test/testImages/discover.jpg");
    img1.load("test/testImages/discover.jpg", "discover", newImage);
    img1.sepiaTransform("discover",
            "discover-sepia");
    newImage = img1.savePPM("test/res/discover-ppm-sepia.ppm", "discover-sepia");
    io.writePPM("test/res/discover-ppm-sepia.ppm", newImage);
    int[][][] data = {
            {{252, 231, 194}, {182, 226, 183}, {255, 239, 199}, {205, 179, 152}},
            {{122, 151, 147}, {83, 34, 42}, {255, 255, 206}, {240, 221, 184}},
            {{255, 255, 207}, {116, 89, 103}, {145, 179, 160}, {163, 200, 170}},
            {{79, 98, 98}, {111, 102, 91}, {255, 255, 219}, {129, 121, 120}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestSepiaJPGToPNG() throws IOException, ValidCheckedException {
    newImage = io.readImage("test/testImages/discover.jpg");
    img1.load("test/testImages/discover.jpg", "discover", newImage);
    img1.sepiaTransform("discover",
            "discover-sepia");
    newImage = img1.savePPM("test/res/discover-png-sepia.png", "discover-sepia");
    io.writePPM("test/res/discover-png-sepia.png", newImage);
    int[][][] data = {
            {{252, 231, 194}, {182, 226, 183}, {255, 239, 199}, {205, 179, 152}},
            {{122, 151, 147}, {83, 34, 42}, {255, 255, 206}, {240, 221, 184}},
            {{255, 255, 207}, {116, 89, 103}, {145, 179, 160}, {163, 200, 170}},
            {{79, 98, 98}, {111, 102, 91}, {255, 255, 219}, {129, 121, 120}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestSepiaJPGToBMP() throws IOException, ValidCheckedException {
    newImage = io.readImage("test/testImages/discover.jpg");
    img1.load("test/testImages/discover.jpg", "discover", newImage);
    img1.sepiaTransform("discover",
            "discover-sepia");
    newImage = img1.savePPM("test/res/discover-bmp-sepia.bmp", "discover-sepia");
    io.writePPM("test/res/discover-bmp-sepia.bmp", newImage);
    int[][][] data = {
            {{252, 231, 194}, {182, 226, 183}, {255, 239, 199}, {205, 179, 152}},
            {{122, 151, 147}, {83, 34, 42}, {255, 255, 206}, {240, 221, 184}},
            {{255, 255, 207}, {116, 89, 103}, {145, 179, 160}, {163, 200, 170}},
            {{79, 98, 98}, {111, 102, 91}, {255, 255, 219}, {129, 121, 120}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestSepiaJPGToJPEG() throws IOException, ValidCheckedException {
    newImage = io.readImage("test/testImages/discover.jpg");
    img1.load("test/testImages/discover.jpg", "discover", newImage);
    img1.sepiaTransform("discover",
            "discover-sepia");
    newImage = img1.savePPM("test/res/discover-jpeg-sepia.jpeg", "discover-sepia");
    io.writePPM("test/res/discover-jpeg-sepia.jpeg", newImage);
    int[][][] data = {
            {{252, 231, 194}, {182, 226, 183}, {255, 239, 199}, {205, 179, 152}},
            {{122, 151, 147}, {83, 34, 42}, {255, 255, 206}, {240, 221, 184}},
            {{255, 255, 207}, {116, 89, 103}, {145, 179, 160}, {163, 200, 170}},
            {{79, 98, 98}, {111, 102, 91}, {255, 255, 219}, {129, 121, 120}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestSepiaBMPToPPM() throws IOException, ValidCheckedException {
    newImage = io.readImage("test/testImages/photo.bmp");
    img1.load("test/testImages/photo.bmp", "photo", newImage);
    img1.sepiaTransform("photo",
            "photo-sepia");
    newImage = img1.savePPM("test/res/photo-ppm-sepia.ppm", "photo-sepia");
    io.writePPM("test/res/photo-ppm-sepia.ppm", newImage);
    int[][][] data = {
            {{252, 231, 194}, {182, 226, 183}, {255, 239, 199}, {205, 179, 152}},
            {{122, 151, 147}, {83, 34, 42}, {255, 255, 206}, {240, 221, 184}},
            {{255, 255, 207}, {116, 89, 103}, {145, 179, 160}, {163, 200, 170}},
            {{79, 98, 98}, {111, 102, 91}, {255, 255, 219}, {129, 121, 120}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestSepiaBMPToPNG() throws IOException, ValidCheckedException {
    newImage = io.readImage("test/testImages/photo.bmp");
    img1.load("test/testImages/photo.bmp", "photo", newImage);
    img1.sepiaTransform("photo",
            "photo-sepia");
    newImage = img1.savePPM("test/res/photo-png-sepia.png", "photo-sepia");
    io.writePPM("test/res/photo-png-sepia.png", newImage);
    int[][][] data = {
            {{252, 231, 194}, {182, 226, 183}, {255, 239, 199}, {205, 179, 152}},
            {{122, 151, 147}, {83, 34, 42}, {255, 255, 206}, {240, 221, 184}},
            {{255, 255, 207}, {116, 89, 103}, {145, 179, 160}, {163, 200, 170}},
            {{79, 98, 98}, {111, 102, 91}, {255, 255, 219}, {129, 121, 120}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestSepiaBMPToJPG() throws IOException, ValidCheckedException {
    newImage = io.readImage("test/testImages/photo.bmp");
    img1.load("test/testImages/photo.bmp", "photo", newImage);
    img1.sepiaTransform("photo",
            "photo-sepia");
    newImage = img1.savePPM("test/res/photo-jpg-sepia.jpg", "photo-sepia");
    io.writePPM("test/res/photo-jpg-sepia.jpg", newImage);
    int[][][] data = {
            {{252, 231, 194}, {182, 226, 183}, {255, 239, 199}, {205, 179, 152}},
            {{122, 151, 147}, {83, 34, 42}, {255, 255, 206}, {240, 221, 184}},
            {{255, 255, 207}, {116, 89, 103}, {145, 179, 160}, {163, 200, 170}},
            {{79, 98, 98}, {111, 102, 91}, {255, 255, 219}, {129, 121, 120}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestSepiaBMPToJPEG() throws IOException, ValidCheckedException {
    newImage = io.readImage("test/testImages/photo.bmp");
    img1.load("test/testImages/photo.bmp", "photo", newImage);
    img1.sepiaTransform("photo",
            "photo-sepia");
    newImage = img1.savePPM("test/res/photo-jpeg-sepia.jpeg", "photo-sepia");
    io.writePPM("test/res/photo-jpeg-sepia.jpeg", newImage);
    int[][][] data = {
            {{252, 231, 194}, {182, 226, 183}, {255, 239, 199}, {205, 179, 152}},
            {{122, 151, 147}, {83, 34, 42}, {255, 255, 206}, {240, 221, 184}},
            {{255, 255, 207}, {116, 89, 103}, {145, 179, 160}, {163, 200, 170}},
            {{79, 98, 98}, {111, 102, 91}, {255, 255, 219}, {129, 121, 120}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestHistogram() throws IOException, ValidCheckedException {
    Map<String, int[][]> histMap;
    newImage = io.readImage("test/testImages/photo.bmp");
    img1.load("test/testImages/photo.bmp", "photo", newImage);
    img1.createHistogram("photo");
    histMap = img1.getHistImage();
    int[][] data1 = histMap.get("photo");
    int[] flattenedData1 = Arrays.stream(data1)
            .flatMapToInt(Arrays::stream)
            .toArray();
    int[] top20 = Arrays.copyOfRange(flattenedData1, flattenedData1.length - 20,
            flattenedData1.length);
    int[] expectedTop20 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    assertArrayEquals(expectedTop20, top20);
  }

  @Test(expected = ValidCheckedException.class)
  public void invalidTestHistogram() throws IOException, ValidCheckedException {
    newImage = io.readImage("test/testImages/photo.bmp");
    img1.load("test/testImages/photo.bmp", "photo", newImage);
    img1.createHistogram("photo1");
  }
}

