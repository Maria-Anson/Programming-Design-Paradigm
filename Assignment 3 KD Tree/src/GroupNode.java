import java.util.List;

/**
 * GroupNode is a class that represents the nodes that are not leaf and which can contain multiple
 * points in it.
 */
public class GroupNode extends TreeStructure {
  /**
   * This is a constructor of the GroupNode.
   * @param left is a tree structure object which represents the left part from the current node
   * @param right is a tree structure object which represents the right part from the current node
   * @param on is a list of points on the current node
   */
  protected GroupNode(TreeStructure left, TreeStructure right, List<Point2D> on) {
    this.left = left;
    this.right = right;
    this.groupOfPoints = on;
  }

  @Override
  protected List<Point2D> getPointsOnNode() {
    return groupOfPoints;
  }

  @Override
  protected boolean isLeafNode() {
    return false;
  }

  @Override
  public String toString() {
    return "Group Node = " + this.groupOfPoints + "\nLeft Division =" + this.left
        + "\nRight Division = " + this.right;
  }

}
