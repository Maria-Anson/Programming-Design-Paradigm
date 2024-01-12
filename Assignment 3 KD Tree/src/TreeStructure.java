import java.util.List;

/**
 * TreeStructure is an abstract class that represents a KD tree. This tree contains leaf node and
 * group node types.
 */
public abstract class TreeStructure {

  // List of variable that is needed
  protected List<Point2D> groupOfPoints;
  protected TreeStructure left;
  protected TreeStructure right;

  /**
   * isLeafNode returns whether the current node is leaf or group node by using boolean.
   *
   * @return boolean true/false
   */
  abstract boolean isLeafNode();

  /**
   * getPointsOnNode returns the list of all points in the current node.
   *
   * @return list of all points
   */
  abstract List<Point2D> getPointsOnNode();
}
