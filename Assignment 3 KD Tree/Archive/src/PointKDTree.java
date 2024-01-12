import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * PointKDTree is a class that implements the SetOfPoints and has all the functions implemented to
 * satisfy the behaviour of the KD Tree.
 */
public class PointKDTree implements SetOfPoints {

  protected TreeStructure root;

  /**
   * PointKDTree is a constructor that takes a list of points as input and builds a Kd tree from it.
   * This function can throw an Illegal Argument Exception.
   *
   * @param points List of points that needs to be in the tree.
   */
  PointKDTree(List<Point2D> points) throws IllegalArgumentException {
    if (points == null) {
      throw new IllegalArgumentException("The list of points are empty.");
    } else if (points.contains(null)) {
      List<Point2D> filteredPoints = new ArrayList<>();
      for (int i = 0; i < points.size(); i++) {
        if (points.get(i) != null) {
          filteredPoints.add(points.get(i));
        }
      }
      if (filteredPoints.isEmpty()) {
        throw new IllegalArgumentException("The list of points are empty.");
      } else {
        this.root = buildKDTree(filteredPoints);
      }
    } else {
      this.root = buildKDTree(points);
    }
  }

  /**
   * Sort the list of points by their values, and return the list of indices of the sorted points.
   *
   * @param points A list of points.
   * @return A list of sorted indexes.
   */

  private List<Integer> getSortedIndices(List<Integer> points) {
    List<Integer> sortedIndexes = new ArrayList<>();
    for (int i = 0; i < points.size(); i++) {
      sortedIndexes.add(i);
    }
    // Using collection sort to get the sorted indexes.
    Collections.sort(sortedIndexes, new Comparator<>() {
      public int compare(Integer o1, Integer o2) {
        return points.get(o1).compareTo(points.get(o2));
      }
    });
    return sortedIndexes;
  }

  /**
   * getSortedPoints takes a list of points and a list of indices, and returns a new list of points
   * where the points are sorted by the indices.
   *
   * @param points        The list of points to sort.
   * @param sortedIndices The indices of the sorted points in the original list.
   * @return A list of points sorted by the given index.
   */
  private List<Point2D> getSortedPoints(List<Point2D> points, List<Integer> sortedIndices) {
    List<Point2D> sortedPoints = new ArrayList<>();
    for (int i = 0; i < sortedIndices.size(); i++) {
      sortedPoints.add(points.get(sortedIndices.get(i)));
    }
    return sortedPoints;
  }

  /**
   * buildKDTree is a function that builds the kd tree when the data points are given.
   *
   * @param points the list of points.
   * @return A TreeStructure object which is the KD tree.
   */
  private TreeStructure buildKDTree(List<Point2D> points) {
    List<Integer> xValOnPoints = new ArrayList<>();
    List<Integer> yValOnPoints = new ArrayList<>();

    // Getting the x values and y values of the points.
    for (int i = 0; i < points.size(); i++) {
      xValOnPoints.add(points.get(i).get(0));
      yValOnPoints.add(points.get(i).get(1));
    }

    // Getting the sorted indices based on both the x values and y values.
    List<Integer> xValOnPointsSorted = getSortedIndices(xValOnPoints);
    List<Integer> yValOnPointsSorted = getSortedIndices(yValOnPoints);

    List<Point2D> px = getSortedPoints(points, xValOnPointsSorted);
    List<Point2D> py = getSortedPoints(points, yValOnPointsSorted);

    int threshold = 1;
    // Calling the recursive function that builds the KD tree.
    return recursiveBuildKDTree(points, px, py, threshold, 0);
  }

  /**
   * recursiveBuildKDTree is a function that builds the kd tree by splitting the points into three
   * groups: 1. Points that lie to the left of the splitting line. 2. Points that lie to the right
   * of the splitting line. 3. Points that lie on the splitting line. If the depth is odd we split
   * the nodes based on the x value in the points, else if the depth is even we split it on the y
   * value in the points.
   *
   * @param points        the list of points.
   * @param sortedXPoints The list of points sorted by x-coordinate.
   * @param sortedYPoints The list of points sorted by y-coordinate.
   * @param threshold     The minimum number of points in a node.
   * @param depth         The depth of the current node in the tree.
   * @return TreeStructure object which is a kd tree.
   */
  private TreeStructure recursiveBuildKDTree(List<Point2D> points, List<Point2D> sortedXPoints,
      List<Point2D> sortedYPoints, int threshold, int depth) {
    // If the points are empty, we return null.
    if (points.isEmpty()) {
      return null;
    }

    // Checking if the depth is odd and looking at the x points.
    if (sortedXPoints.size() <= threshold && depth % 2 == 0) {
      // If there is no sorted x points, return null
      if (sortedXPoints.size() == 0) {
        return null;
      }
      // Creating a leaf node with the sorted x points value when there is only one point
      return new LeafNode(sortedXPoints);
    }

    // Checking if the depth is even and looking at the y points.
    if (sortedYPoints.size() <= threshold && depth % 2 == 1) {
      // If there is no sorted y points, return null
      if (sortedYPoints.size() == 0) {
        return null;
      }
      // Creating a leaf node with the sorted y points value when there is only one point
      return new LeafNode(sortedYPoints);
    }
    int a;
    int b;
    int c;
    // Checking if depth is even
    if (depth % 2 == 0) {
      a = 1;
      b = 0;
      int size = sortedXPoints.size();
      int odd_check = size % 2;
      // The - median value on the sorted x points
      c = -sortedXPoints.get(size / 2 + odd_check).get(0);

    } else {
      a = 0;
      b = 1;
      int size = sortedYPoints.size();
      int odd_check = size % 2;
      // The - median value on the sorted y points
      c = -sortedYPoints.get(size / 2 + odd_check).get(1);
    }
    List<Point2D> pxBefore = new ArrayList<>();
    List<Point2D> pxAfter = new ArrayList<>();
    List<Point2D> pyBefore = new ArrayList<>();
    List<Point2D> pyAfter = new ArrayList<>();
    List<Point2D> on = new ArrayList<>();

    // Segregating the sorted x points based on the position of point from the line.
    for (int i = 0; i < sortedXPoints.size(); i++) {
      Point2D t = sortedXPoints.get(i);
      double sd = signedDistance(t, a, b, c);
      if (sd < 0) {
        pxBefore.add(t);
      } else if (sd > 0) {
        pxAfter.add(t);
      } else {
        on.add(t);
      }
    }

    // Segregating the sorted y points based on the position of point from the line.
    for (int i = 0; i < sortedYPoints.size(); i++) {
      Point2D t = sortedYPoints.get(i);
      double sd = signedDistance(t, a, b, c);
      if (sd < 0) {
        pyBefore.add(t);
      } else if (sd > 0) {
        pyAfter.add(t);
      }
    }
    // Building the left portion from the current node recursively
    TreeStructure left = recursiveBuildKDTree(points, pxBefore, pyBefore, threshold, depth + 1);
    // Building the right portion from the current node recursively
    TreeStructure right = recursiveBuildKDTree(points, pxAfter, pyAfter, threshold, depth + 1);

    // If there is no left and right possibility and there is only one point in the line.
    if (left == null && right == null && on.size() == 1) {
      return new LeafNode(on);
    }

    // Creating a group node with the left, right and data points on the line.
    return new GroupNode(left, right, on);
  }

  /**
   * signedDistance returns the distance from point to a line.
   *
   * @param t the point to be tested
   * @param a the x coordinate
   * @param b the y coordinate
   * @param c the constant term which is the - median
   * @return the signed distance
   */
  private double signedDistance(Point2D t, int a, int b, int c) {
    return (a * t.get(0) + b * t.get(1) + c);
  }

  @Override
  public void add(Point2D point) {
    this.root = recursiveAddHelper(this.root, point, 0);
  }

  /**
   * recursiveAddHelper is a helper function that is used to insert the point in the kdtree in a
   * recursive way.
   *
   * @param head  the current node we are looking at
   * @param point the point to be added to the tree
   * @param depth the depth of the current node
   * @return the head of the built tree
   */
  private TreeStructure recursiveAddHelper(TreeStructure head, Point2D point, int depth) {
    // Creating a leaf node with the point if head is null
    if (head == null) {
      List<Point2D> newPointList = new ArrayList<>();
      newPointList.add(point);
      head = new LeafNode(newPointList);
      return head;
    }
    // If the lowest point on the node is greater than the x/y value of point depending on the depth
    // Traversing the left area
    if (head.getPointsOnNode().get(0).get(depth % 2) > point.get(depth % 2)) {
      // If left node is not null, recursively traversing the left
      if (head.left != null) {
        head.left = recursiveAddHelper(head.left, point, depth + 1);
      } else {
        // Creating a new leaf node that goes to the left of the current node
        List<Point2D> newPointList = new ArrayList<>();
        newPointList.add(point);
        head = new GroupNode(new LeafNode(newPointList), head.right, head.getPointsOnNode());
      }
      return head;
    } // If the lowest point on the node is less than the x/y value of point depending on the depth
    // Traversing the right area
    else if (head.getPointsOnNode().get(0).get(depth % 2) < point.get(depth % 2)) {
      List<Point2D> newPointList = new ArrayList<>();
      newPointList.add(point);
      // If right node is not null, recursively traversing the right
      if (head.right != null) {
        head.right = recursiveAddHelper(head.right, point, depth + 1);
      } else {
        // Creating a new leaf node that goes to the right of the current node
        head = new GroupNode(head.left, new LeafNode(newPointList), head.getPointsOnNode());
      }
      return head;
    } else {
      // This is the case where the point lies on the splitting line
      // Checking for a leaf node
      if (head.isLeafNode()) {
        // Converting the leaf node to group node to accommodate multiple points
        List<Point2D> newPointList = new ArrayList<>();
        newPointList.addAll(head.getPointsOnNode());
        newPointList.add(point);
        return new GroupNode(null, null, newPointList);
      }
      // Updating the current node's point list with the point
      head.groupOfPoints.add(point);
      return head;
    }
  }

  @Override
  public List getPoints() {
    List<Point2D> allPoints = new ArrayList<>();
    if (this.root == null) {
      return allPoints;
    }
    return recursiveGetPointsHelper(this.root, allPoints);
  }

  /**
   * recursiveGetPointsHelper is a function that returns the list of all the points in the tree
   * using recursion.
   *
   * @param head             head of the tree where the current node is at
   * @param pointsFoundSoFar a list to accumulate the points through recurdsion
   * @return list of all points in the tree
   */
  private List<Point2D> recursiveGetPointsHelper(TreeStructure head,
      List<Point2D> pointsFoundSoFar) {
    if (head != null) {
      // Traversing the left
      pointsFoundSoFar = recursiveGetPointsHelper(head.left, pointsFoundSoFar);
      // Adding the point in  current node
      pointsFoundSoFar.addAll(head.getPointsOnNode());
      // Traversing the right
      pointsFoundSoFar = recursiveGetPointsHelper(head.right, pointsFoundSoFar);
    }
    return pointsFoundSoFar;
  }

  /**
   * recursiveCirclePointFinder is a function that is used to find all the points that lies within
   * the dimensions of the given circle in the kd tree.
   *
   * @param center           center of the circle
   * @param radius           radius of the circle
   * @param depth            the current depth during the recursion
   * @param head             the current position of node during the recursion
   * @param pointsFoundSoFar the list which is accumulated throughout the recursion
   */
  private void recursiveCirclePointFinder(Point2D center, double radius, int depth,
      TreeStructure head, List<Point2D> pointsFoundSoFar) {
    // If the head is null, we just recurse back to the called function
    if (head == null) {
      return;
    }
    // Traversing through all the points on the current node
    for (int i = 0; i < head.getPointsOnNode().size(); i++) {
      Point2D currentPoint = head.getPointsOnNode().get(i);
      double sd = currentPoint.distance(center);
      // Checking if the point distance to given center is less than the radius
      if (sd <= radius) {
        // Adding as the point is less than radius, which implies that point is inside the circle
        pointsFoundSoFar.add(currentPoint);
      }
    }

    // Checking the lowest point in the node to determine the direction of travel
    Point2D closestPointToCheckIfInCircle = head.getPointsOnNode().get(0);
    int closestxVal = closestPointToCheckIfInCircle.get(0);
    int closestyVal = closestPointToCheckIfInCircle.get(1);

    if (depth % 2 == 0) {
      // Checking if the closest x value is greater than the center x value. If it is, then we
      // recursively call the function on the left side of the tree.
      if (closestxVal + radius >= center.get(0)) {
        recursiveCirclePointFinder(center, radius, depth + 1, head.left, pointsFoundSoFar);
      }
      // Checking if the closest x value is less than the radius of the circle. If it is, then we
      // recursively call the function on the right side of the tree.
      if (closestxVal - radius <= center.get(0)) {
        recursiveCirclePointFinder(center, radius, depth + 1, head.right, pointsFoundSoFar);
      }
    } else {
      // Checking if the closest y value is greater than the center y value. If it is, then we
      // recursively call the function on the left side of the tree.
      if (closestyVal + radius >= center.get(1)) {
        recursiveCirclePointFinder(center, radius, depth + 1, head.left, pointsFoundSoFar);
      }
      // Checking if the closest y value is less than or equal to the center y value. If it is, then
      // we recursively call the function on the right side of the tree.
      if (closestyVal - radius <= center.get(1)) {
        recursiveCirclePointFinder(center, radius, depth + 1, head.right, pointsFoundSoFar);
      }
    }
  }


  @Override
  public List allPointsWithinCircle(Point2D center, double radius) {
    List<Point2D> pointsInsideTheCircle = new ArrayList<>();
    recursiveCirclePointFinder(center, radius, 0, this.root, pointsInsideTheCircle);
    return pointsInsideTheCircle;
  }

  /**
   * findClosestPoint finds the closest point to the current point in the tree.
   *
   * @param listOfPoints list of all points to check for
   * @param point        the current pivot point to check closest distance for
   * @return The closest point to the given point.
   */
  private Point2D findClosestPoint(List<Point2D> listOfPoints, Point2D point) {
    double min = Double.MAX_VALUE;
    Point2D closestPoint = null;
    // Loooping through all the points in the tree
    for (int i = 0; i < listOfPoints.size(); i++) {
      Point2D currentPoint = listOfPoints.get(i);
      double currentDistance = point.distance(currentPoint);
      // Updating the min distance and closest point so far
      if (currentDistance < min) {
        min = currentDistance;
        closestPoint = currentPoint;
      }
    }
    return closestPoint;
  }

  @Override
  public Point2D closestPoint(Point2D point) {
    return findClosestPoint(this.getPoints(), point);
  }

  @Override
  public String toString() {
    if (this.root != null) {
      return (this.root.toString());
    } else {
      return ("This tree has no entry");
    }
  }
}
