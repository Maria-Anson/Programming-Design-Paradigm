import java.util.List;

/**
 * SetOfPoints is an interface that has all the required functionalities of the KD tree.
 */
public interface SetOfPoints {

  /**
   * A method add that takes a single Point2D object and adds this point to the set. The behavior of
   * this method if the point already exists in the set, is dependent on the implementation.
   *
   * @param point The point to be added to the kd tree
   */
  void add(Point2D point);

  /**
   * A method getPoints that does not take any arguments, and returns a List of all the points
   * currently in this set.
   *
   * @return List of all points in set
   */
  List getPoints();

  /**
   * A method allPointsWithinCircle that takes the center of a circle (as a Point2D object) and a
   * radius as a double and returns a List of all the points in this set that lie inside or on this
   * circle.
   *
   * @param center Center point of the circle
   * @param radius Radius of the circle
   * @return List of all point that lies inside the circle
   */
  List allPointsWithinCircle(Point2D center, double radius);

  /**
   * A method closestPoint that takes a single query point Point2D and returns the point in this set
   * that is closest to this query point. If no such point exists, this method should return null.
   *
   * @param point the input point
   * @return point that is closest to the given point
   */
  Point2D closestPoint(Point2D point);

  /**
   * toString is a function that is used to make a string representation of the kd tree.
   * @return a string representation
   */
  String toString();
}
