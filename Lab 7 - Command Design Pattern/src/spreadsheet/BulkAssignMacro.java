package spreadsheet;

public class BulkAssignMacro implements MacroCommand{
  private int startRow;
  private int startColumn;
  private int endRow;
  private int endColumn;
  private double valueToAssign;

  public BulkAssignMacro(int startRow, int startColumn, int endRow, int endColumn,
      double valueToAssign){
    Helper.checkForNegative(startRow);
    Helper.checkForNegative(startColumn);
    Helper.checkForNegative(endRow);
    Helper.checkForNegative(endColumn);
    Helper.checkForRelation(startRow, endRow);
    Helper.checkForRelation(startColumn, endColumn);
    this.startRow = startRow;
    this.startColumn = startColumn;
    this.endRow = endRow;
    this.endColumn = endColumn;
    this.valueToAssign = valueToAssign;
  }

  @Override
  public void executeMacro(SpreadSheetWithMacro obj) {
    for (int i = this.startRow; i <= this.endRow; i++) {
      for (int j = this.startColumn; j <= this.endColumn; j++) {
        obj.set(i, j, valueToAssign);
      }
    }
  }
}
