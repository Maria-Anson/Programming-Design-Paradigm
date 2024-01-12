package spreadsheet;

public class RangeAssignMacro implements MacroCommand {
  private int startRow;
  private int startColumn;
  private int endRow;
  private int endColumn;
  private double startValue;
  private int increment;

  public RangeAssignMacro(int startRow, int startColumn, int endRow, int endColumn,
      int startValue, int increment) throws IllegalArgumentException{
    Helper.checkForNegative(startRow);
    Helper.checkForNegative(startColumn);
    Helper.checkForNegative(endRow);
    Helper.checkForNegative(endColumn);
    Helper.checkForRelation(startRow, endRow);
    Helper.checkForRelation(startColumn, endColumn);
    this.startRow = startRow;
    this.startColumn = startColumn;
    this.endColumn = endColumn;
    this.endRow = endRow;
    this.startValue = startValue;
    this.increment = increment;
  }

  @Override
  public void executeMacro(SpreadSheetWithMacro obj){
    double temp = this.startValue;
    for (int i = this.startRow; i <= this.endRow; i++) {
      for (int j = this.startColumn; j <= this.endColumn; j++) {
        obj.set(i, j, temp);
        temp = temp + this.increment;
      }
    }
  }
}
