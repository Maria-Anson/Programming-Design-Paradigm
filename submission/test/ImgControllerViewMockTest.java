import static org.junit.Assert.assertEquals;

import exception.ValidCheckedException;
import controller.ImageController;
import controller.ImageControllerImpl;
import model.ImgModel;
import model.ImgModelImpl;
import view.ImgView;
import view.ImgViewImpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains JUnit tests for the Image Controller Script file.
 * It sets up an environment to mock the view and tests the Image Controller functionality.
 */
public class ImgControllerViewMockTest {

  private final ByteArrayOutputStream outStreamContent = new ByteArrayOutputStream();
  private final PrintStream streamOut = System.out;

  /**
   * Sets up the output stream to be redirected to a new PrintStream instance
   * that captures the content written to System.out.
   */
  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outStreamContent));
  }

  /**
   * Restores the original output stream after the test has completed.
   */
  @After
  public void restoreStreams() {
    System.setOut(streamOut);
  }

  @Test
  public void testDisplayStatusLoad() throws ValidCheckedException, IOException {
    String inputStream = "load test/res/pixel.ppm pixel";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel model = new ImgModelImpl();
    ImgView mockImageView = new MockingImgView();
    ImageController testController = new ImageControllerImpl(model, mockImageView);
    testController.start();
    assertEquals("Successful completion of the operation\n", mockImageView.toString());
  }

  @Test
  public void testDisplayStatusBrighten() throws ValidCheckedException, IOException {
    String inputStream = "load test/res/pixel.ppm pixel\nbrighten 10 pixel pixel-brighter";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel model = new ImgModelImpl();
    ImgView mockImageView = new MockingImgView();
    ImageController testController = new ImageControllerImpl(model, mockImageView);
    testController.start();
    assertEquals("Successful completion of the operation\nSuccessful completion of the " +
            "operation\n", mockImageView.toString());
  }

  @Test
  public void testDisplayStatusHFlip() throws ValidCheckedException, IOException {
    String inputStream = "load test/res/pixel.ppm pixel\nhorizontal-flip pixel pixel-hflip";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel model = new ImgModelImpl();
    ImgView mockImageView = new MockingImgView();
    ImageController testController = new ImageControllerImpl(model, mockImageView);
    testController.start();
    assertEquals("Successful completion of the operation\nSuccessful completion of the " +
            "operation\n", mockImageView.toString());
  }

  @Test
  public void testDisplayStatusVFlip() throws ValidCheckedException, IOException {
    String inputStream = "load test/res/pixel.ppm pixel\nvertical-flip pixel pixel-vflip";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel model = new ImgModelImpl();
    ImgView mockImageView = new MockingImgView();
    ImageController testController = new ImageControllerImpl(model, mockImageView);
    testController.start();
    assertEquals("Successful completion of the operation\nSuccessful completion of the " +
            "operation\n", mockImageView.toString());
  }

  @Test
  public void testDisplayStatusGreyscale() throws ValidCheckedException, IOException {
    String inputStream = "load test/res/pixel.ppm pixel\ngreyscale value-component pixel " +
            "pixel-gvalue";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel model = new ImgModelImpl();
    ImgView mockImageView = new MockingImgView();
    ImageController testController = new ImageControllerImpl(model, mockImageView);
    testController.start();
    assertEquals("Successful completion of the operation\nSuccessful completion of the " +
            "operation\n", mockImageView.toString());
  }

  @Test
  public void testDisplayStatusSplit() throws ValidCheckedException, IOException {
    String inputStream = "load test/res/pixel.ppm pixel\nrgb-split pixel pr pg pb";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel model = new ImgModelImpl();
    ImgView mockImageView = new MockingImgView();
    ImageController testController = new ImageControllerImpl(model, mockImageView);
    testController.start();
    assertEquals("Successful completion of the operation\nSuccessful completion of the " +
            "operation\n", mockImageView.toString());
  }

  @Test
  public void testDisplayStatusCombine() throws ValidCheckedException, IOException {
    String inputStream = "load test/res/pixel.ppm pixel\nrgb-split pixel pr " +
            "pg pb\nrgb-combine pixel-c pr pg pb";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel model = new ImgModelImpl();
    ImgView mockImageView = new MockingImgView();
    ImageController testController = new ImageControllerImpl(model, mockImageView);
    testController.start();
    assertEquals("Successful completion of the operation\nSuccessful completion of the " +
            "operation\nSuccessful completion of the operation\n", mockImageView.toString());
  }

  @Test
  public void testDisplayStatusRun() throws ValidCheckedException, IOException {
    String inputStream = "load test/res/pixel.ppm pixel\nrun test/testScripts/AllCommandScript.txt";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel model = new ImgModelImpl();
    ImgView mockImageView = new MockingImgView();
    ImageController testController = new ImageControllerImpl(model, mockImageView);
    testController.start();
    assertEquals("Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n" +
            "Successful completion of the operation\n", mockImageView.toString());
  }

  @Test
  public void testDisplayStatusSave() throws ValidCheckedException, IOException {
    String inputStream = "load test/res/pixel.ppm pixel\nsave test/res/pixel-save.ppm pixel";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel model = new ImgModelImpl();
    ImgView mockImageView = new MockingImgView();
    ImageController testController = new ImageControllerImpl(model, mockImageView);
    testController.start();
    assertEquals("Successful completion of the operation\nSuccessful completion of the " +
            "operation\n", mockImageView.toString());
  }

  @Test
  public void testDisplayStatusSaveWithHash() throws ValidCheckedException, IOException {
    String inputStream = "#load this\nload test/res/pixel.ppm pixel\n#save this\n" +
            "save test/res/pixel-save.ppm pixel";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel model = new ImgModelImpl();
    ImgView mockImageView = new MockingImgView();
    ImageController testController = new ImageControllerImpl(model, mockImageView);
    testController.start();
    assertEquals("Successful completion of the operation\nSuccessful completion of the " +
            "operation\n", mockImageView.toString());
  }

  @Test
  public void testDisplayStatusInvalid() throws IOException, ValidCheckedException {
    String in = "loading pixel pixel";
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
  public void testDisplayStatusBlur() throws ValidCheckedException, IOException {
    String inputStream = "load test/res/pixel.ppm pixel\nblur pixel pixel-blur";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel model = new ImgModelImpl();
    ImgView mockImageView = new MockingImgView();
    ImageController testController = new ImageControllerImpl(model, mockImageView);
    testController.start();
    assertEquals("Successful completion of the operation\nSuccessful completion of the " +
            "operation\n", mockImageView.toString());
  }

  @Test
  public void testDisplayStatusSharpen() throws ValidCheckedException, IOException {
    String inputStream = "load test/res/pixel.ppm pixel\nsharpen pixel pixel-sharp";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel model = new ImgModelImpl();
    ImgView mockImageView = new MockingImgView();
    ImageController testController = new ImageControllerImpl(model, mockImageView);
    testController.start();
    assertEquals("Successful completion of the operation\nSuccessful completion of the " +
            "operation\n", mockImageView.toString());
  }

  @Test
  public void testDisplayStatusDither() throws ValidCheckedException, IOException {
    String inputStream = "load test/res/pixel.ppm pixel\ndither pixel pixel-dither";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel model = new ImgModelImpl();
    ImgView mockImageView = new MockingImgView();
    ImageController testController = new ImageControllerImpl(model, mockImageView);
    testController.start();
    assertEquals("Successful completion of the operation\nSuccessful completion of the " +
            "operation\n", mockImageView.toString());
  }

  @Test
  public void testDisplayStatusSepia() throws ValidCheckedException, IOException {
    String inputStream = "load test/res/pixel.ppm pixel\nsepia pixel pixel-sepia";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel model = new ImgModelImpl();
    ImgView mockImageView = new MockingImgView();
    ImageController testController = new ImageControllerImpl(model, mockImageView);
    testController.start();
    assertEquals("Successful completion of the operation\nSuccessful completion of the " +
            "operation\n", mockImageView.toString());
  }

  @Test
  public void testDisplayStatusGreyscaleTransform() throws ValidCheckedException, IOException {
    String inputStream = "load test/res/pixel.ppm pixel\ngreyscale pixel pixel-grey";
    System.setIn(new ByteArrayInputStream(inputStream.getBytes()));
    ImgModel model = new ImgModelImpl();
    ImgView mockImageView = new MockingImgView();
    ImageController testController = new ImageControllerImpl(model, mockImageView);
    testController.start();
    assertEquals("Successful completion of the operation\nSuccessful completion of the " +
            "operation\n", mockImageView.toString());
  }

}
