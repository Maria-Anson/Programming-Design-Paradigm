import static org.junit.Assert.assertEquals;

import exception.ValidCheckedException;
import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImgModel;

import org.junit.After;
import org.junit.Before;

import view.ImgView;
import view.ImgViewImpl;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * This class contains JUnit tests for the Image Controller functionality related to the
 * ImgModel class. It sets up an environment to mock user input and tests the functionality of the
 * Image Controller and ImgModel.
 */
public class ImgControllerModelMockTest {

  private final ByteArrayOutputStream outputStreamContent = new ByteArrayOutputStream();
  private final PrintStream streamOut = System.out;

  @Before
  public void setUp() {
    System.setOut(new PrintStream(outputStreamContent));
  }

  @After
  public void restore() {
    System.setOut(streamOut);
  }

  @Test
  public void testBrightenMock() throws ValidCheckedException, IOException {
    String inputStream = "brighten 10 pixel pixel-brighter";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Brightened successfully and named as pixel-brighter ",
            mockImageModel.toString());
  }

  @Test
  public void testHFlipMock() throws ValidCheckedException, IOException {
    String inputStream = "horizontal-flip pixel pixel-HFlip";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Flipped Horizontally successfully and named as pixel-HFlip ",
            mockImageModel.toString());
  }

  @Test
  public void testVFlipMock() throws ValidCheckedException, IOException {
    String inputStream = "vertical-flip pixel pixel-VFlip";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Flipped Vertically successfully and named as pixel-VFlip ",
            mockImageModel.toString());
  }

  @Test
  public void testVFlipHFlipMock() throws ValidCheckedException, IOException {
    String inputStream = "vertical-flip pixel pixel-VFlip\nhorizontal-flip pixel-VFlip " +
            "pixel-VFlip-HFlip";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Flipped Vertically successfully and named as pixel-VFlip " +
                    "pixel-VFlip Flipped Horizontally successfully and named as pixel-VFlip-HFlip ",
            mockImageModel.toString());
  }

  @Test
  public void testHFlipVFlipMock() throws ValidCheckedException, IOException {
    String inputStream = "horizontal-flip pixel pixel-HFlip\nvertical-flip pixel-HFlip " +
            "pixel-HFlip-VFlip";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Flipped Horizontally successfully and named as pixel-HFlip " +
                    "pixel-HFlip Flipped Vertically successfully and named as pixel-HFlip-VFlip ",
            mockImageModel.toString());
  }

  @Test
  public void testHFlipVFlipMockWithHash() throws ValidCheckedException, IOException {
    String inputStream = "#do horizontal flip\nhorizontal-flip pixel pixel-HFlip\n#do " +
            "vertical flip\nvertical-flip pixel-HFlip " +
            "pixel-HFlip-VFlip";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Flipped Horizontally successfully and named as pixel-HFlip " +
                    "pixel-HFlip Flipped Vertically successfully and named as pixel-HFlip-VFlip ",
            mockImageModel.toString());
  }

  @Test
  public void testLumaMock() throws ValidCheckedException, IOException {
    String inputStream = "greyscale luma-component pixel pixel-greyscale-luma";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Greyscale successfully done and named as pixel-greyscale-luma ",
            mockImageModel.toString());
  }

  @Test
  public void testValueMock() throws ValidCheckedException, IOException {
    String inputStream = "greyscale value-component pixel pixel-greyscale-value";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Greyscale successfully done and named as pixel-greyscale-value ",
            mockImageModel.toString());
  }

  @Test
  public void testIntensityMock() throws ValidCheckedException, IOException {
    String inputStream = "greyscale intensity-component pixel pixel-greyscale-intensity";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Greyscale successfully done and named as "
            + "pixel-greyscale-intensity ", mockImageModel.toString());
  }

  @Test
  public void testRedGreyScaleMock() throws ValidCheckedException, IOException {
    String inputStream = "greyscale red-component pixel pixel-greyscale-red";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Greyscale successfully done and named as pixel-greyscale-red ",
            mockImageModel.toString());
  }

  @Test
  public void testGreenGreyScaleMock() throws ValidCheckedException, IOException {
    String inputStream = "greyscale green-component pixel pixel-greyscale-green";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Greyscale successfully done and named as pixel-greyscale-green ",
            mockImageModel.toString());
  }

  @Test
  public void testGreenGreyScaleMockWithHash() throws ValidCheckedException, IOException {
    String inputStream = "#do greyscale\ngreyscale green-component pixel pixel-greyscale-green";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Greyscale successfully done and named as pixel-greyscale-green ",
            mockImageModel.toString());
  }

  @Test
  public void testBlueMock() throws ValidCheckedException, IOException {
    String inputStream = "greyscale blue-component pixel pixel-greyscale-blue";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Greyscale successfully done and named as pixel-greyscale-blue ",
            mockImageModel.toString());
  }

  @Test
  public void testSplitMock() throws ValidCheckedException, IOException {
    String inputStream = "rgb-split pixel pixel-red pixel-green pixel-blue";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel RGB Split successfully done ", mockImageModel.toString());
  }

  @Test
  public void testCombineMock() throws ValidCheckedException, IOException {
    String inputStream = "rgb-combine pixel-red-tint pixel-red pixel-green pixel-blue";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("RGB Combined successfully and named as pixel-red-tint ",
            mockImageModel.toString());
  }

  @Test
  public void testCombineMockWithHash() throws ValidCheckedException, IOException {
    String inputStream = "#do rgb combine\nrgb-combine pixel-red-tint pixel-red "
            + "pixel-green pixel-blue";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("RGB Combined successfully and named as pixel-red-tint ",
            mockImageModel.toString());
  }

  @Test
  public void testRunMockBright() throws ValidCheckedException, IOException {
    String inputStream = "run test/testScripts/BrightValid.txt";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Brightened successfully and named as pixel-brighter ",
            mockImageModel.toString());
  }

  @Test
  public void testRunMockBrightWithHash() throws ValidCheckedException, IOException {
    String inputStream = "#run script\nrun test/testScripts/BrightValid.txt";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Brightened successfully and named as pixel-brighter ",
            mockImageModel.toString());
  }

  @Test
  public void testRunMockDarken() throws ValidCheckedException, IOException {
    String inputStream = "run test/testScripts/DarkenValid.txt";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Brightened successfully and named as pixel-d2 ",
            mockImageModel.toString());
  }

  @Test
  public void testCombineInvalidMock() throws IOException, ValidCheckedException {
    String in = "combine pixel pixel";
    System.setIn(new ByteArrayInputStream(in.getBytes()));
    ImgModel mockModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController controller = new ImageControllerImpl(mockModel, view);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(outContent));
    controller.start();
    String out = outContent.toString().trim();
    assertEquals("Error executing command: Error: Not a Valid Command!" +
            " Try again with valid command", out);
    System.setErr(originalErr);
  }

  @Test
  public void testMockBrightenInvalid() throws IOException, ValidCheckedException {
    String in = "brig 10 pixel pixelb";
    System.setIn(new ByteArrayInputStream(in.getBytes()));
    ImgModel mockModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController controller = new ImageControllerImpl(mockModel, view);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(outContent));
    controller.start();
    String out = outContent.toString().trim();
    assertEquals("Error executing command: Error: Not a Valid Command! " +
            "Try again with valid command", out);
    System.setErr(originalErr);
  }

  @Test
  public void testHFlipInvalidMock() throws ValidCheckedException, IOException {
    String in = "hlip pixel pixelh";
    System.setIn(new ByteArrayInputStream(in.getBytes()));
    ImgModel mockModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController controller = new ImageControllerImpl(mockModel, view);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(outContent));
    controller.start();
    String out = outContent.toString().trim();
    assertEquals("Error executing command: Error: Not a Valid Command! T" +
            "ry again with valid command", out);
    System.setErr(originalErr);
  }

  @Test
  public void testVFlipInvalidMock() throws IOException, ValidCheckedException {
    String in = "vflip pixel pixelv";
    System.setIn(new ByteArrayInputStream(in.getBytes()));
    ImgModel mockModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController controller = new ImageControllerImpl(mockModel, view);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(outContent));
    controller.start();
    String out = outContent.toString().trim();
    assertEquals("Error executing command: Error: Not a Valid Command!" +
            " Try again with valid command", out);
    System.setErr(originalErr);
  }

  @Test
  public void testLumaInvalidMock() throws IOException, ValidCheckedException {
    String in = "greyscalw pixel pixel";
    System.setIn(new ByteArrayInputStream(in.getBytes()));
    ImgModel mockModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController controller = new ImageControllerImpl(mockModel, view);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(outContent));
    controller.start();
    String out = outContent.toString().trim();
    assertEquals("Error executing command: Error: Not a Valid Command! " +
            "Try again with valid command", out);
    System.setErr(originalErr);
  }

  @Test
  public void testLumaInvalidMockComponent() throws IOException, ValidCheckedException {
    String in = "greys luma pixel pixell";
    System.setIn(new ByteArrayInputStream(in.getBytes()));
    ImgModel mockModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController controller = new ImageControllerImpl(mockModel, view);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(outContent));
    controller.start();
    String out = outContent.toString().trim();
    assertEquals("Error executing command: Error: Not a Valid Command!" +
            " Try again with valid command", out);
    System.setErr(originalErr);
  }

  @Test
  public void testValueInvalidMock() throws IOException, ValidCheckedException {
    String in = "greyscal value-comp pixel pixelval";
    System.setIn(new ByteArrayInputStream(in.getBytes()));
    ImgModel mockModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController controller = new ImageControllerImpl(mockModel, view);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(outContent));
    controller.start();
    String out = outContent.toString().trim();
    assertEquals("Error executing command: Error: Not a Valid Command! " +
            "Try again with valid command", out);
    System.setErr(originalErr);
  }

  @Test
  public void testIntensityInvalidMock() throws IOException, ValidCheckedException {
    String in = "greyscal intensity-comp pixel pixelin";
    System.setIn(new ByteArrayInputStream(in.getBytes()));
    ImgModel mockModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController controller = new ImageControllerImpl(mockModel, view);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(outContent));
    controller.start();
    String out = outContent.toString().trim();
    assertEquals("Error executing command: Error:" +
            " Not a Valid Command! Try again with valid command", out);
    System.setErr(originalErr);
  }

  @Test
  public void testSplitInvalidMockMock() throws IOException, ValidCheckedException {
    String in = "split pixel sdf dsf sdf";
    System.setIn(new ByteArrayInputStream(in.getBytes()));
    ImgModel mockModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController controller = new ImageControllerImpl(mockModel, view);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(outContent));
    controller.start();
    String out = outContent.toString().trim();
    assertEquals("Error executing command: Error: Not a Valid Command! " +
            "Try again with valid command", out);
    System.setErr(originalErr);
  }

  @Test
  public void testBlurMock() throws ValidCheckedException, IOException {
    String inputStream = "blur pixel pixel-blur";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Blur successfully and named as pixel-blur ",
            mockImageModel.toString());
  }

  @Test
  public void testInvalidBlurMock() throws IOException, ValidCheckedException {
    String in = "blr pixel pixelb";
    System.setIn(new ByteArrayInputStream(in.getBytes()));
    ImgModel mockModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController controller = new ImageControllerImpl(mockModel, view);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(outContent));
    controller.start();
    String out = outContent.toString().trim();
    assertEquals("Error executing command: Error: Not a Valid Command! " +
            "Try again with valid command", out);
    System.setErr(originalErr);
  }

  @Test
  public void testSharpenMock() throws ValidCheckedException, IOException {
    String inputStream = "sharpen pixel pixel-sharp";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Sharpen successfully and named as pixel-sharp ",
            mockImageModel.toString());
  }

  @Test
  public void testInvalidSharpenMock() throws IOException, ValidCheckedException {
    String in = "sharp pixel pixelsh";
    System.setIn(new ByteArrayInputStream(in.getBytes()));
    ImgModel mockModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController controller = new ImageControllerImpl(mockModel, view);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(outContent));
    controller.start();
    String out = outContent.toString().trim();
    assertEquals("Error executing command: Error: Not a Valid Command! " +
            "Try again with valid command", out);
    System.setErr(originalErr);
  }

  @Test
  public void testSepiaMock() throws ValidCheckedException, IOException {
    String inputStream = "sepia pixel pixel-sepia";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Sepia successfully and named as pixel-sepia ",
            mockImageModel.toString());
  }

  @Test
  public void testInvalidSepiaMock() throws IOException, ValidCheckedException {
    String in = "sep pixel pixels";
    System.setIn(new ByteArrayInputStream(in.getBytes()));
    ImgModel mockModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController controller = new ImageControllerImpl(mockModel, view);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(outContent));
    controller.start();
    String out = outContent.toString().trim();
    assertEquals("Error executing command: " +
            "Error: Not a Valid Command! Try again with valid command", out);
    System.setErr(originalErr);
  }

  @Test
  public void testTransformGreyscaleMock() throws ValidCheckedException, IOException {
    String inputStream = "greyscale pixel pixel-grey";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Transformed to Greyscale successfully and named as pixel-grey ",
            mockImageModel.toString());
  }

  @Test
  public void testInvalidTransformGreyscaleMock() throws IOException, ValidCheckedException {
    String in = "greysle pixel pixelg";
    System.setIn(new ByteArrayInputStream(in.getBytes()));
    ImgModel mockModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController controller = new ImageControllerImpl(mockModel, view);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(outContent));
    controller.start();
    String out = outContent.toString().trim();
    assertEquals("Error executing command: Error: Not a Valid Command! " +
            "Try again with valid command", out);
    System.setErr(originalErr);
  }


  @Test
  public void testDitherMock() throws ValidCheckedException, IOException {
    String inputStream = "dither pixel pixel-dither";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel mockImageModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController testController = new ImageControllerImpl(mockImageModel, view);
    testController.start();
    assertEquals("pixel Dither successfully and named as pixel-dither ",
            mockImageModel.toString());
  }

  @Test
  public void testInvalidDitherMock() throws IOException, ValidCheckedException {
    String in = "dith pixel pixeld";
    System.setIn(new ByteArrayInputStream(in.getBytes()));
    ImgModel mockModel = new MockingImgModel();
    ImgView view = new ImgViewImpl();
    ImageController controller = new ImageControllerImpl(mockModel, view);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalErr = System.err;
    System.setErr(new PrintStream(outContent));
    controller.start();
    String out = outContent.toString().trim();
    assertEquals("Error executing command: Error: Not a Valid Command!" +
            " Try again with valid command", out);
    System.setErr(originalErr);
  }

  private String systemOut() {
    return outputStreamContent.toString();
  }
}
