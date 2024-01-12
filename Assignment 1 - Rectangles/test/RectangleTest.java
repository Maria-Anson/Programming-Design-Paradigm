import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;
import org.junit.Test;

/**
 * This class contains all the test cases required to check all the functionalities defined in the
 * interface.
 */
public class RectangleTest {

  /**
   * inputTestWithNegativeWidth is used to check if IllegalArgumentException is thrown for width
   * with negative value.
   */
  @Test
  public void inputTestWithNegativeWidth() {
    // Rectangle object with negative value in width which should raise error
    boolean thrown = false;
    String errorMessage = "";
    try {
      new Rectangle(0, 0, -10, 5);
    } catch (IllegalArgumentException exception) {
      thrown = true;
      errorMessage = exception.getMessage();
    }
    assertEquals(thrown, true);
    assertEquals(errorMessage, "Width should be a positive value");
  }

  /**
   * inputTestWithNegativeWidth is used to check if IllegalArgumentException is thrown for height
   * with negative value.
   */
  @Test
  public void inputTestWithNegativeHeight() {
    // Rectangle object with negative value in width which should raise error
    boolean thrown = false;
    String errorMessage = "";
    try {
      new Rectangle(0, 0, 10, -5);
    } catch (IllegalArgumentException exception) {
      thrown = true;
      errorMessage = exception.getMessage();
    }
    assertEquals(thrown, true);
    assertEquals(errorMessage, "Height should be a positive value");
  }

  /**
   * inputTestWithNegativeWidth is used to check if IllegalArgumentException is thrown for width and
   * height with negative value.
   */
  @Test
  public void inputTestwithNegatvieWidthHeight() {
    // Rectangle object with negative value in both width and height which should raise error
    boolean thrown = false;
    String errorMessage = "";
    try {
      new Rectangle(0, 0, -10, -5);
    } catch (IllegalArgumentException exception) {
      thrown = true;
      errorMessage = exception.getMessage();
    }
    assertEquals(thrown, true);
    assertEquals(errorMessage, "Height and Width should be a positive value");
  }

  /**
   * inputTestWithNegativeWidth is used to check if no error is thrown for width and height with
   * positive value.
   */
  @Test
  public void inputTestwithPositiveWidthHeight() {
    // Rectangle object with negative values in x y coordinates which should not raise error
    boolean thrown = false;
    String errorMessage = "";
    try {
      new Rectangle(-2, -3, 10, 5);
    } catch (IllegalArgumentException exception) {
      thrown = true;
      errorMessage = exception.getMessage();
    }
    assertEquals(thrown, false);
    assertEquals(errorMessage, "");

  }

  @Test
  public void overlapTest() {
    // Overlapping samples Case 1 as described in assignment
    Rectangle rectangle1 = new Rectangle(1, 1, 4, 5);
    Rectangle rectangle2 = new Rectangle(-1, 3, 5, 6);
    assertEquals(rectangle1.overlap(rectangle2), true);
    // Overlapping samples Case 2 as described in assignment
    Rectangle rectangle3 = new Rectangle(2, 1, 7, 5);
    Rectangle rectangle4 = new Rectangle(4, 2, 3, 3);
    assertEquals(rectangle3.overlap(rectangle4), true);
    // On the line samples Case 3 with y axis on above
    Rectangle rectangle5 = new Rectangle(2, 1, 2, 3);
    Rectangle rectangle6 = new Rectangle(2, 4, 2, 2);
    assertEquals(rectangle5.overlap(rectangle6), false);
    // Above the line samples Case 3 samples with y axis
    Rectangle rectangle7 = new Rectangle(2, 1, 2, 3);
    Rectangle rectangle8 = new Rectangle(2, 5, 2, 2);
    assertEquals(rectangle7.overlap(rectangle8), false);
    // On the line samples Case 3 with y axis on under
    Rectangle rectangle9 = new Rectangle(2, 4, 2, 2);
    Rectangle rectangle10 = new Rectangle(2, 1, 2, 3);
    assertEquals(rectangle9.overlap(rectangle10), false);
    // Under the line samples Case 3 samples with y axis
    Rectangle rectangle11 = new Rectangle(2, 5, 2, 2);
    Rectangle rectangle12 = new Rectangle(2, 1, 2, 3);
    assertEquals(rectangle11.overlap(rectangle12), false);
    // On the line sample Case 3 with x axis on left
    Rectangle rectangle13 = new Rectangle(2,2,3,2);
    Rectangle rectangle14 = new Rectangle(-1,1,3,4);
    assertEquals(rectangle13.overlap(rectangle14), false);
    // Left to the line sample Case 3 with x axis
    Rectangle rectangle15 = new Rectangle(2,2,3,2);
    Rectangle rectangle16 = new Rectangle(-1,1,2,4);
    assertEquals(rectangle15.overlap(rectangle16), false);
    // On the line sample Case 3 with x axis on right
    Rectangle rectangle17 = new Rectangle(-1,1,3,4);
    Rectangle rectangle18 = new Rectangle(2,2,3,2);
    assertEquals(rectangle17.overlap(rectangle18), false);
    // Right to the line sample Case 3 with x axis
    Rectangle rectangle19 = new Rectangle(-1,1,2,4);
    Rectangle rectangle20 = new Rectangle(2,2,3,2);
    assertEquals(rectangle19.overlap(rectangle20), false);
  }

  /**
   * This function contains a list of instances to check if the intersection is working as
   * intended.
   */
  @Test
  public void intesectTest() {
    // Case 1 where there is an intersection
    Rectangle rectangle1 = new Rectangle(2, -6, 3, 5);
    Rectangle rectangle2 = new Rectangle(3, -7, 4, 4);
    Rectangle intersect1 = rectangle1.intersect(rectangle2);
    assertEquals(intersect1.toString(), "x:3, y:-6, w:2, h:3");
    // Case 2 where the small rectangle is inside the bigger rectangle
    Rectangle rectangle3 = new Rectangle(2, 1, 5, 4);
    Rectangle rectangle4 = new Rectangle(3, 2, 3, 2);
    Rectangle intersect2 = rectangle3.intersect(rectangle4);
    assertEquals(intersect2.toString(), "x:3, y:2, w:3, h:2");
    // Case 3 where the rectangle doesn't intersect
    Rectangle rectangle5 = new Rectangle(3, 3, 2, 3);
    Rectangle rectangle6 = new Rectangle(3, 7, 5, 1);
    Boolean thrown1 = false;
    String errorMessage1 = "";
    try {
      rectangle5.intersect(rectangle6);
    } catch (NoSuchElementException error) {
      thrown1 = true;
      errorMessage1 = error.getMessage();
    }
    assertTrue(thrown1);
    assertEquals(errorMessage1, "The given rectangles have no common overlapping region");
    // Case 3 where rectangles doesn't intersect but share a line
    Rectangle rectangle7 = new Rectangle(3, 3, 2, 3);
    Rectangle rectangle8 = new Rectangle(3, 6, 5, 1);
    Boolean thrown2 = false;
    String errorMessage2 = "";
    try {
      rectangle7.intersect(rectangle8);
    } catch (NoSuchElementException error) {
      thrown2 = true;
      errorMessage2 = error.getMessage();
    }
    assertTrue(thrown2);
    assertEquals(errorMessage2, "The given rectangles have no common overlapping region");
  }

  /**
   * This function contains a list of instances to check if the union is working as intended.
   */
  @Test
  public void unionTest() {
    // Case 1 there is an intersection
    Rectangle rectangle1 = new Rectangle(0, 0, 20, 10);
    Rectangle rectangle2 = new Rectangle(-50, -50, 5, 5);
    Rectangle union1 = rectangle1.union(rectangle2);
    assertEquals(union1.toString(), "x:-50, y:-50, w:70, h:60");
    // Case 2 where the small rectangle is inside the bigger rectangle
    Rectangle rectangle3 = new Rectangle(2, 1, 5, 4);
    Rectangle rectangle4 = new Rectangle(3, 2, 3, 2);
    Rectangle union2 = rectangle3.union(rectangle4);
    assertEquals(union2.toString(), "x:2, y:1, w:5, h:4");
    // Case 3 where the rectangle doesn't intersect
    Rectangle rectangle5 = new Rectangle(-3, -3, 3, 2);
    Rectangle rectangle6 = new Rectangle(-2, 1, 3, 2);
    Rectangle union3 = rectangle5.union(rectangle6);
    assertEquals(union3.toString(), "x:-3, y:-3, w:4, h:6");
  }
}