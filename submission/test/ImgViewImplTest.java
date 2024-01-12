import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import view.ImgView;
import view.ImgViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class contains JUnit tests for the ImgViewImpl class.
 */
public class ImgViewImplTest {

  private final ByteArrayOutputStream outputStreamContent = new ByteArrayOutputStream();
  private final PrintStream streamOut = System.out;

  /**
   * This method sets up the output stream before running each test.
   */
  @Before
  public void setUp() {
    System.setOut(new PrintStream(outputStreamContent));
  }

  /**
   * This method restores the original output stream after each test is run.
   */
  @After
  public void restore() {
    System.setOut(streamOut);
  }

  @Test
  public void testDisplayStatusLoad() {
    ImgView view = new ImgViewImpl();
    String imageName = "test-pixel.ppm";
    String operation = "load";
    view.displayStatus(imageName, operation);
    String expectedMessage = String.format("Image %s Loaded Successfully\n", imageName);
    assertEquals(expectedMessage, systemOut());
  }

  @Test
  public void testDisplayStatusBrighten() {
    ImgView view = new ImgViewImpl();
    String imageName = "test-pixel.ppm";
    String operation = "brighten";
    view.displayStatus(imageName, operation);
    String expectedMessage = String.format("Image %s Brightened Successfully\n", imageName);
    assertEquals(expectedMessage, systemOut());
  }

  @Test
  public void testDisplayStatusHFlip() {
    ImgView view = new ImgViewImpl();
    String imageName = "test-pixel.ppm";
    String operation = "horizontal-flip";
    view.displayStatus(imageName, operation);
    String expectedMessage = String.format("Image %s flipped horizontal Successfully\n", imageName);
    assertEquals(expectedMessage, systemOut());
  }

  @Test
  public void testDisplayStatusVFlip() {
    ImgView view = new ImgViewImpl();
    String imageName = "test-pixel.ppm";
    String operation = "vertical-flip";
    view.displayStatus(imageName, operation);
    String expectedMessage = String.format("Image %s flipped vertical Successfully\n", imageName);
    assertEquals(expectedMessage, systemOut());
  }

  @Test
  public void testDisplayStatusSplit() {
    ImgView view = new ImgViewImpl();
    String imageName = "test-pixel.ppm";
    String operation = "rgb-split";
    view.displayStatus(imageName, operation);
    String expectedMessage = String.format("Image %s Split Successfully\n", imageName);
    assertEquals(expectedMessage, systemOut());
  }

  @Test
  public void testDisplayStatusRGBCombine() {
    ImgView view = new ImgViewImpl();
    String imageName = "test-pixel.ppm";
    String operation = "rgb-combine";
    view.displayStatus(imageName, operation);
    String expectedMessage = String.format("Image %s Combined Successfully\n", imageName);
    assertEquals(expectedMessage, systemOut());
  }

  @Test
  public void testDisplayStatusGrayscale() {
    ImgView view = new ImgViewImpl();
    String imageName = "test-pixel.ppm";
    String operation = "greyscale";
    view.displayStatus(imageName, operation);
    String expectedMessage = String.format("greyscale %s Created Successfully\n", imageName);
    assertEquals(expectedMessage, systemOut());
  }

  @Test
  public void testDisplayStatusSave() {
    ImgView view = new ImgViewImpl();
    String imageName = "test-pixel.ppm";
    String operation = "save";
    view.displayStatus(imageName, operation);
    String expectedMessage = String.format("Image %s Saved Successfully\n", imageName);
    assertEquals(expectedMessage, systemOut());
  }

  @Test
  public void testDisplayStatusRun() {
    ImgView view = new ImgViewImpl();
    String scriptName = "test-script.txt";
    String operation = "run";
    view.displayStatus(scriptName, operation);
    String expectedMessage = String.format("Script %s Ran Successfully\n", scriptName);
    assertEquals(expectedMessage, systemOut());
  }

  @Test
  public void testDisplayStatusBlur() {
    ImgView view = new ImgViewImpl();
    String imageName = "test-pixel.ppm";
    String operation = "blur";
    view.displayStatus(imageName, operation);
    String expectedMessage = String.format("Image %s Blurred Successfully\n", imageName);
    assertEquals(expectedMessage, systemOut());
  }

  @Test
  public void testDisplayStatusSharpen() {
    ImgView view = new ImgViewImpl();
    String imageName = "test-pixel.ppm";
    String operation = "sharpen";
    view.displayStatus(imageName, operation);
    String expectedMessage = String.format("Image %s Sharpened Successfully\n", imageName);
    assertEquals(expectedMessage, systemOut());
  }

  @Test
  public void testDisplayStatusSepia() {
    ImgView view = new ImgViewImpl();
    String imageName = "test-pixel.ppm";
    String operation = "sepia";
    view.displayStatus(imageName, operation);
    String expectedMessage = String.format("Image %s transformed to Sepia Successfully\n",
            imageName);
    assertEquals(expectedMessage, systemOut());
  }

  @Test
  public void testDisplayStatusDither() {
    ImgView view = new ImgViewImpl();
    String imageName = "test-pixel.ppm";
    String operation = "dither";
    view.displayStatus(imageName, operation);
    String expectedMessage = String.format("Image %s Dithered Successfully\n", imageName);
    assertEquals(expectedMessage, systemOut());
  }

  @Test
  public void testDisplayStatusTransformGreyScale() {
    ImgView view = new ImgViewImpl();
    String imageName = "test-pixel.ppm";
    String operation = "greyscale";
    view.displayStatus(imageName, operation);
    String expectedMessage = String.format("greyscale test-pixel.ppm Created Successfully\n",
            imageName);
    assertEquals(expectedMessage, systemOut());
  }

  private String systemOut() {
    return outputStreamContent.toString();
  }
}
