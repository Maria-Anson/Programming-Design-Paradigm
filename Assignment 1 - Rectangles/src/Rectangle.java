import java.util.NoSuchElementException;

/**
 * Rectangle class implements the polygon interface which overloads the functions defined there.
 */
public class Rectangle {

  private int x;
  private int y;
  private int height;
  private int width;
  private int ux;
  private int uy;

  /**
   * This is a constructor that is used to initialize the private fields of the class and raise
   * errors if invalid inputs are given.
   *
   * @param x      x coordinate of the lower corner of rectangle
   * @param y      y coordinate of the lower corner of rectangle
   * @param width  width/length of the rectangle
   * @param height height/breadth of the rectangle
   */
  public Rectangle(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;

    // Throwing an error both the height and width value is negative
    if (height <= 0 && width <= 0) {
      throw new IllegalArgumentException("Height and Width should be a positive value");
    } else {
      // Throwing an error if the height is negative
      if (height <= 0) {
        throw new IllegalArgumentException("Height should be a positive value");
      } else {
        this.height = height;
      }
      // Throwing an error both the width is negative
      if (width <= 0) {
        throw new IllegalArgumentException("Width should be a positive value");
      } else {
        this.width = width;
      }
    }
    this.ux = this.x + this.width;
    this.uy = this.y + this.height;
  }

  /**
   * overLap function is used to find whether the current rectangle overlap with the given input
   * rectangle.
   *
   * @param other Input rectangle object
   * @return Boolean True/False
   */
  public boolean overlap(Rectangle other) {
    // Checking if the rectangle lies on or after or before the x-axes of main rectangle
    if (other.x >= this.ux || other.ux <= this.x) {
      return false;
    }
    // Checking if the rectangle lies on or above or below the y-axes of main rectangle
    else {
      return other.y < this.uy && other.uy > this.y;
    }
  }

  /**
   * intersect function gives us a new rectangle that intersects both the current and given input
   * rectangle.
   *
   * @param other Input rectangle object
   * @return Intersected rectangle object
   */
  public Rectangle intersect(Rectangle other) {
    if (this.overlap(other)) {
      // Using max to select the x-axis and y-axis of the base of the intersection area of rectangle
      int intersectedLowerCornerx = Math.max(this.x, other.x);
      int intersectedLowerCornery = Math.max(this.y, other.y);
      // Using min to select the x-axis and y-axis of the top of the intersection area of rectangle
      int intersectedUpperCornerx = Math.min(this.ux, other.ux);
      int intersectedUpperCornery = Math.min(this.uy, other.uy);
      return new Rectangle(intersectedLowerCornerx, intersectedLowerCornery,
          intersectedUpperCornerx - intersectedLowerCornerx,
          intersectedUpperCornery - intersectedLowerCornery);
    } else {
      throw new NoSuchElementException("The given rectangles have no common overlapping region");
    }
  }

  /**
   * union function gives us a rectangle that combines both the current and given rectangle into
   * one.
   *
   * @param other Input rectangle object
   * @return Union of rectangles object
   */
  public Rectangle union(Rectangle other) {
    // Using min to select the x-axis and y-axis of the base of the union area of rectangle
    int intersectedLowerCornerx = Math.min(this.x, other.x);
    int intersectedLowerCornery = Math.min(this.y, other.y);
    // Using max to select the x-axis and y-axis of the base of the union area of rectangle
    int intersectedUpperCornerx = Math.max(this.ux, other.ux);
    int intersectedUpperCornery = Math.max(this.uy, other.uy);
    return new Rectangle(intersectedLowerCornerx, intersectedLowerCornery,
        intersectedUpperCornerx - intersectedLowerCornerx,
        intersectedUpperCornery - intersectedLowerCornery);
  }

  /**
   * toString function generates a string that displays the fields of the rectangle object.
   *
   * @return String Format of string in x, y, w, h
   */
  @Override
  public String toString() {
    return String.format("x:%d, y:%d, w:%d, h:%d", this.x, this.y, this.width, this.height);
  }
}