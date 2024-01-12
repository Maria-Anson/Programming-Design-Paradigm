import model.Image;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import exception.ValidCheckedException;
import model.ImgModel;
import model.ImgModelImpl;
import util.ImgUtil;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * This class contains JUnit tests for the ImgIOImpl class.
 */
public class ImgUtilTest {
  ImgUtil io = new ImgUtil();
  private Image newImage;
  private Image renewImage;

  /**
   * Sets up the environment for the test by initializing an ImgModelImpl object,
   * loading an image from the given file path using the io.readPPM method,
   * and calling the load method of the ImgModelImpl object to load the image data.
   *
   * @throws IOException           if there is an error while reading the image file.
   * @throws ValidCheckedException if the loaded image is not valid.
   */
  @Before
  public void setup() throws IOException, ValidCheckedException {
    ImgModel img1;
    img1 = new ImgModelImpl();
    newImage = io.readPPM("test/res/pixel.ppm");
    img1.load("test/res/pixel.ppm", "pixel", newImage);
  }

  @Test
  public void validTestReadPPM() throws IOException {
    newImage = io.readPPM("test/res/pixel.ppm");
    int[][][] data = {
            {{225, 150, 4}, {235, 215, 11}, {0, 215, 100}, {245, 200, 7}},
            {{225, 200, 6}, {215, 255, 5}, {190, 6, 5}, {0, 92, 296}},
            {{0, 168, 150}, {0, 146, 190}, {160, 22, 180}, {225, 255, 1}},
            {{111, 79, 160}, {118, 245, 88}, {99, 88, 54}, {0, 60, 190}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test(expected = FileNotFoundException.class)
  public void invalidTestReadPPM() throws IOException {
    newImage = io.readPPM("test/es/pixelll.ppm");
  }

  @Test
  public void validTestReadImageBMP() throws IOException {
    newImage = io.readImage("test/testImages/photo.bmp");
    int[][][] data = {
            {{235, 204, 17}, {0, 212, 99}, {242, 214, 17}, {215, 155, 7}},
            {{0, 97, 250}, {198, 5, 8}, {216, 255, 0}, {219, 198, 9}},
            {{227, 251, 9}, {157, 26, 180}, {1, 142, 187}, {6, 173, 147}},
            {{0, 57, 187}, {101, 81, 48}, {116, 248, 103}, {112, 72, 158}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestReadImageJPG() throws IOException {
    newImage = io.readImage("test/testImages/discover.jpg");
    int expectedWidth = 4;
    int expectedHeight = 4;
    assertEquals(expectedWidth, io.getWidth());
    assertEquals(expectedHeight, io.getHeight());
    int[][][] data = {
            {{235, 204, 17}, {0, 212, 99}, {242, 214, 17}, {215, 155, 7}},
            {{0, 97, 250}, {198, 5, 8}, {216, 255, 0}, {219, 198, 9}},
            {{227, 251, 9}, {157, 26, 180}, {1, 142, 187}, {6, 173, 147}},
            {{0, 57, 187}, {101, 81, 48}, {116, 248, 103}, {112, 72, 158}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test
  public void validTestReadImagePNG() throws IOException {
    newImage = io.readImage("test/testImages/chase.png");
    int expectedWidth = 4;
    int expectedHeight = 4;
    assertEquals(expectedWidth, io.getWidth());
    assertEquals(expectedHeight, io.getHeight());
    int[][][] data = {
            {{245, 200, 7}, {0, 215, 100}, {235, 215, 11}, {225, 150, 4}},
            {{0, 92, 255}, {190, 6, 5}, {220, 255, 3}, {225, 200, 6}},
            {{220, 255, 3}, {160, 22, 180}, {0, 146, 190}, {0, 168, 150}},
            {{0, 60, 190}, {99, 88, 54}, {118, 245, 88}, {111, 79, 160}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test(expected = FileNotFoundException.class)
  public void invalidTestReadImage() throws IOException {
    newImage = io.readImage("test/es/chaseee.png");
  }

  @Test
  public void validTestWritePPM() throws IOException {
    newImage = io.readPPM("test/res/pixel.ppm");
    Map<String, int[][][]> hm = new HashMap<>();
    hm.put("pixel-write", newImage.getImage());
    io.writePPM("test/res/pixel-write.ppm", newImage);
    Image newImg = io.readPPM("test/res/pixel-write.ppm");
    assertArrayEquals(newImg.getImage(), newImage.getImage());
  }

  @Test(expected = FileNotFoundException.class)
  public void invalidTestWritePPM() throws IOException {
    newImage = io.readPPM("test/re/pixel.ppm");
    Map<String, int[][][]> hm = new HashMap<>();
    hm.put("pixel-write", newImage.getImage());
    io.writePPM("test/res/pixel-write.ppm", newImage);
    Image newImg = io.readPPM("test/res/pixel-write.ppm");
    assertArrayEquals(newImg.getImage(), newImage.getImage());
  }

  @Test
  public void validTestWriteImagePNG() throws IOException {
    newImage = io.readImage("test/testImages/chase.png");
    Map<String, int[][][]> hm = new HashMap<>();
    hm.put("chase-write", newImage.getImage());
    io.writeImage("test/testImages/chase-write.png", newImage);
    Image newImg = io.readImage("test/testImages/chase-write.png");
    assertArrayEquals(newImg.getImage(), newImage.getImage());
  }

  @Test
  public void validTestWriteImageBMP() throws IOException {
    newImage = io.readImage("test/testImages/photo.bmp");
    Map<String, int[][][]> hm = new HashMap<>();
    hm.put("photo-write", newImage.getImage());
    io.writeImage("test/testImages/photo-write.bmp", newImage);
    Image newImg = io.readImage("test/testImages/photo-write.bmp");
    assertArrayEquals(newImg.getImage(), newImage.getImage());
  }

  @Test
  public void validTestWriteImageJPG() throws IOException {
    newImage = io.readImage("test/testImages/discover.jpg");
    Map<String, int[][][]> hm = new HashMap<>();
    hm.put("discover-write", newImage.getImage());
    io.writeImage("test/testImages/discover-write.jpg", newImage);
    int[][][] data = {
            {{235, 204, 17}, {0, 212, 99}, {242, 214, 17}, {215, 155, 7}},
            {{0, 97, 250}, {198, 5, 8}, {216, 255, 0}, {219, 198, 9}},
            {{227, 251, 9}, {157, 26, 180}, {1, 142, 187}, {6, 173, 147}},
            {{0, 57, 187}, {101, 81, 48}, {116, 248, 103}, {112, 72, 158}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test(expected = FileNotFoundException.class)
  public void invalidTestWriteImageJPG() throws IOException {
    newImage = io.readImage("test/testIm/discover.jpg");
    Map<String, int[][][]> hm = new HashMap<>();
    hm.put("discover-write", newImage.getImage());
    io.writeImage("test/tes/discover-write.jpg", newImage);
    int[][][] data = {
            {{235, 204, 17}, {0, 212, 99}, {242, 214, 17}, {215, 155, 7}},
            {{0, 97, 250}, {198, 5, 8}, {216, 255, 0}, {219, 198, 9}},
            {{227, 251, 9}, {157, 26, 180}, {1, 142, 187}, {6, 173, 147}},
            {{0, 57, 187}, {101, 81, 48}, {116, 248, 103}, {112, 72, 158}}
    };
    assertArrayEquals(data, newImage.getImage());
  }

  @Test(expected = FileNotFoundException.class)
  public void invalidTestWriteImagePNG() throws IOException {
    newImage = io.readImage("test/testImages/cse.png");
    Map<String, int[][][]> hm = new HashMap<>();
    hm.put("chase-write", newImage.getImage());
    io.writeImage("test/testImages/chase-write.png", newImage);
    Image newImg = io.readImage("test/testImages/chase-write.png");
    assertArrayEquals(newImg.getImage(), newImage.getImage());
  }

  @Test(expected = FileNotFoundException.class)
  public void invalidTestWriteImageBMP() throws IOException {
    newImage = io.readImage("test/testIma/photo.bmp");
    Map<String, int[][][]> hm = new HashMap<>();
    hm.put("photo-write", newImage.getImage());
    io.writeImage("test/testImages/photo-write.bmp", newImage);
    Image newImg = io.readImage("test/testImages/photo-write.bmp");
    assertArrayEquals(newImg.getImage(), newImage.getImage());
  }

  @Test(expected = FileNotFoundException.class)
  public void invalidTestWriteImage() throws IOException {
    newImage = io.readImage("test/re/pixel.png");
    Map<String, int[][][]> hm = new HashMap<>();
    hm.put("pixel-write", newImage.getImage());
    io.writeImage("test/res/pixel-write.ppm", newImage);
    Image newImg = io.readImage("test/res/pixel-write.ppm");
    assertArrayEquals(newImg.getImage(), newImage.getImage());
  }

  @Test
  public void validTestWriteImagePPMtoPNG() throws IOException {
    newImage = io.readPPM("test/testImages/pixel.ppm");
    io.writeImage("test/testImages/chase-write.png", newImage);
    renewImage = io.readImage("test/testImages/chase-write.png");
    int[][][] data = {
            {{225, 150, 4}, {235, 215, 11}, {0, 215, 100}, {245, 200, 7}},
            {{225, 200, 6}, {215, 255, 5}, {190, 6, 5}, {0, 93, 40}},
            {{0, 168, 150}, {0, 146, 190}, {160, 22, 180}, {225, 255, 1}},
            {{111, 79, 160}, {118, 245, 88}, {99, 88, 54}, {0, 60, 190}}
    };
    assertArrayEquals(data, renewImage.getImage());
  }

  @Test
  public void validTestWriteImagePPMtoJPG() throws IOException {
    newImage = io.readPPM("test/testImages/pixel.ppm");
    io.writeImage("test/testImages/chase-write.jpg", newImage);
    renewImage = io.readImage("test/testImages/chase-write.jpg");
    String expectedData = "[[[129, 203, 48], [159, 233, 78], [181, 142, 21], [233, 194, 73]], " +
            "[[146, 220, 65], [190, 255, 109], [111, 72, 0], [80, 41, 0]], " +
            "[[76, 151, 110], [50, 125, 84], [114, 77, 61], [254, 217, 201]], " +
            "[[38, 113, 72], [151, 226, 185], [117, 80, 64], [76, 39, 23]]]";
    String actualData = Arrays.deepToString(renewImage.getImage());
    assertEquals(expectedData, actualData);
  }

  @Test
  public void validTestWriteImagePPMtoBMP() throws IOException {
    newImage = io.readPPM("test/testImages/pixel.ppm");
    io.writeImage("test/testImages/chase-write.bmp", newImage);
    renewImage = io.readImage("test/testImages/chase-write.bmp");
    int[][][] data = {
            {{225, 150, 4}, {235, 215, 11}, {0, 215, 100}, {245, 200, 7}},
            {{225, 200, 6}, {215, 255, 5}, {190, 6, 5}, {0, 93, 40}},
            {{0, 168, 150}, {0, 146, 190}, {160, 22, 180}, {225, 255, 1}},
            {{111, 79, 160}, {118, 245, 88}, {99, 88, 54}, {0, 60, 190}}
    };
    assertArrayEquals(data, renewImage.getImage());
  }

  @Test
  public void validTestWriteImagePNGtoPPM() throws IOException {
    newImage = io.readImage("test/testImages/chase.png");
    io.writePPM("test/testImages/chase-write.ppm", newImage);
    renewImage = io.readPPM("test/testImages/chase-write.ppm");
    String expectedData = "[[[245, 200, 7], [0, 215, 100], [235, 215, 11], [225, 150, 4]], " +
            "[[0, 92, 255], [190, 6, 5], [220, 255, 3], [225, 200, 6]], " +
            "[[220, 255, 3], [160, 22, 180], [0, 146, 190], [0, 168, 150]], " +
            "[[0, 60, 190], [99, 88, 54], [118, 245, 88], [111, 79, 160]]]";
    String actualData = Arrays.deepToString(renewImage.getImage());
    assertEquals(expectedData, actualData);
  }

  @Test
  public void validTestWriteImagePNGtoBMP() throws IOException {
    newImage = io.readImage("test/testImages/chase.png");
    io.writeImage("test/testImages/chase-write.bmp", newImage);
    renewImage = io.readImage("test/testImages/chase-write.bmp");
    String expectedData = "[[[245, 200, 7], [0, 215, 100], [235, 215, 11], [225, 150, 4]], " +
            "[[0, 92, 255], [190, 6, 5], [220, 255, 3], [225, 200, 6]], " +
            "[[220, 255, 3], [160, 22, 180], [0, 146, 190], [0, 168, 150]], " +
            "[[0, 60, 190], [99, 88, 54], [118, 245, 88], [111, 79, 160]]]";
    String actualData = Arrays.deepToString(renewImage.getImage());
    assertEquals(expectedData, actualData);
  }

  @Test
  public void validTestWriteImagePNGtoJPG() throws IOException {
    newImage = io.readImage("test/testImages/chase.png");
    io.writeImage("test/testImages/chase-write.jpg", newImage);
    renewImage = io.readImage("test/testImages/chase-write.jpg");
    String expectedData = "[[[233, 202, 86], [164, 133, 17], [189, 229, 70], [147, 187, 28]], " +
            "[[106, 75, 0], [113, 82, 0], [201, 241, 82], [173, 213, 54]], " +
            "[[196, 224, 255], [57, 85, 133], [31, 142, 153], [20, 131, 142]], " +
            "[[24, 52, 100], [64, 92, 140], [117, 228, 239], [22, 133, 144]]]";
    String actualData = Arrays.deepToString(renewImage.getImage());
    assertEquals(expectedData, actualData);
  }

  @Test
  public void validTestWriteBMPtoPPM() throws IOException {
    newImage = io.readImage("test/testImages/photo.bmp");
    io.writePPM("test/testImages/chase-write.ppm", newImage);
    renewImage = io.readPPM("test/testImages/chase-write.ppm");
    String expectedData = "[[[235, 204, 17], [0, 212, 99], [242, 214, 17], [215, 155, 7]], " +
            "[[0, 97, 250], [198, 5, 8], [216, 255, 0], [219, 198, 9]], [[227, 251, 9], " +
            "[157, 26, 180], [1, 142, 187], [6, 173, 147]], [[0, 57, 187], [101, 81, 48], " +
            "[116, 248, 103], [112, 72, 158]]]";
    String actualData = Arrays.deepToString(renewImage.getImage());
    assertEquals(expectedData, actualData);
  }

  @Test
  public void validTestWriteImageBMPtoPNG() throws IOException {
    newImage = io.readImage("test/testImages/photo.bmp");
    io.writeImage("test/testImages/chase-write.png", newImage);
    renewImage = io.readImage("test/testImages/chase-write.png");
    String expectedData = "[[[235, 204, 17], [0, 212, 99], [242, 214, 17], [215, 155, 7]], " +
            "[[0, 97, 250], [198, 5, 8], [216, 255, 0], [219, 198, 9]], [[227, 251, 9], " +
            "[157, 26, 180], [1, 142, 187], [6, 173, 147]], [[0, 57, 187], [101, 81, 48], " +
            "[116, 248, 103], [112, 72, 158]]]";
    String actualData = Arrays.deepToString(renewImage.getImage());
    assertEquals(expectedData, actualData);
  }

  @Test
  public void validTestWriteImageBMPtoJPG() throws IOException {
    newImage = io.readImage("test/testImages/photo.bmp");
    io.writeImage("test/testImages/chase-write.jpg", newImage);
    renewImage = io.readImage("test/testImages/chase-write.jpg");
    String expectedData = "[[[251, 196, 93], [186, 131, 28], [195, 219, 69], [165, 189, 39]], " +
            "[[117, 62, 0], [127, 72, 0], [202, 226, 76], [185, 209, 59]], [[200, 226, 255], " +
            "[64, 90, 141], [32, 142, 155], [29, 139, 152]], [[22, 48, 99], [64, 90, 141], " +
            "[114, 224, 237], [22, 132, 145]]]";
    String actualData = Arrays.deepToString(renewImage.getImage());
    assertEquals(expectedData, actualData);
  }

  @Test
  public void validTestWriteJPGtoPPM() throws IOException {
    newImage = io.readImage("test/testImages/discover.jpg");
    io.writePPM("test/testImages/chase-write.ppm", newImage);
    renewImage = io.readPPM("test/testImages/chase-write.ppm");
    String expectedData = "[[[235, 204, 17], [0, 212, 99], [242, 214, 17], [215, 155, 7]], " +
            "[[0, 97, 250], [198, 5, 8], [216, 255, 0], [219, 198, 9]], [[227, 251, 9], " +
            "[157, 26, 180], [1, 142, 187], [6, 173, 147]], [[0, 57, 187], [101, 81, 48], " +
            "[116, 248, 103], [112, 72, 158]]]";
    String actualData = Arrays.deepToString(renewImage.getImage());
    assertEquals(expectedData, actualData);
  }

  @Test
  public void validTestWriteImageJPGtoPNG() throws IOException {
    newImage = io.readImage("test/testImages/discover.jpg");
    io.writeImage("test/testImages/chase-write.png", newImage);
    renewImage = io.readImage("test/testImages/chase-write.png");
    String expectedData = "[[[235, 204, 17], [0, 212, 99], [242, 214, 17], [215, 155, 7]], " +
            "[[0, 97, 250], [198, 5, 8], [216, 255, 0], [219, 198, 9]], [[227, 251, 9], " +
            "[157, 26, 180], [1, 142, 187], [6, 173, 147]], [[0, 57, 187], [101, 81, 48], " +
            "[116, 248, 103], [112, 72, 158]]]";
    String actualData = Arrays.deepToString(renewImage.getImage());
    assertEquals(expectedData, actualData);
  }

  @Test
  public void validTestWriteImageJPGtoBMP() throws IOException {
    newImage = io.readImage("test/testImages/discover.jpg");
    io.writeImage("test/testImages/chase-write.bmp", newImage);
    renewImage = io.readImage("test/testImages/chase-write.bmp");
    String expectedData = "[[[235, 204, 17], [0, 212, 99], [242, 214, 17], [215, 155, 7]], " +
            "[[0, 97, 250], [198, 5, 8], [216, 255, 0], [219, 198, 9]], [[227, 251, 9], " +
            "[157, 26, 180], [1, 142, 187], [6, 173, 147]], [[0, 57, 187], [101, 81, 48], " +
            "[116, 248, 103], [112, 72, 158]]]";
    String actualData = Arrays.deepToString(renewImage.getImage());
    assertEquals(expectedData, actualData);
  }

}