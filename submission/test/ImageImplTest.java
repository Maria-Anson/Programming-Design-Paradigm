import model.ImageImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Test class for ImageImpl.
 */
public class ImageImplTest {

  @Test
  public void testGetHeight() {
    ImageImpl image = new ImageImpl(10, 20, 255, new int[10][20][3]);
    assertEquals(10, image.getHeight());
  }

  @Test
  public void testGetWidth() {
    ImageImpl image = new ImageImpl(10, 20, 255, new int[10][20][3]);
    assertEquals(20, image.getWidth());
  }

  @Test
  public void testGetMaxValue() {
    ImageImpl image = new ImageImpl(10, 20, 255, new int[10][20][3]);
    assertEquals(255, image.getMaxValue());
  }

  @Test
  public void testGetImage() {
    int[][][] imageData = new int[10][20][3];
    ImageImpl image = new ImageImpl(10, 20, 255, imageData);
    assertSame(imageData, image.getImage());
  }

  @Test
  public void testConstructor() {
    int[][][] imageData = new int[10][20][3];
    ImageImpl image = new ImageImpl(10, 20, 255, imageData);
    assertEquals(10, image.getHeight());
    assertEquals(20, image.getWidth());
    assertEquals(255, image.getMaxValue());
    assertSame(imageData, image.getImage());
  }

}