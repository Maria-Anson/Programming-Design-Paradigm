import java.util.List;

/**
 * LeafNode is a class that represents the nodes that are leaf and which has null as left and right.
 */
public class LeafNode extends TreeStructure {

  /**
   * This is a constructor of the LeafNode.
   * @param pointList A single point
   */
  protected LeafNode(List<Point2D> pointList) {
    this.groupOfPoints = pointList;
    this.left = null;
    this.right = null;
  }

  @Override
  protected boolean isLeafNode() {
    return true;
  }

  @Override
  protected List<Point2D> getPointsOnNode() {
    return groupOfPoints == null ? null : groupOfPoints;
  }

  @Override
  public String toString() {
    return "Leaf Node = " + this.groupOfPoints;
  }
}
