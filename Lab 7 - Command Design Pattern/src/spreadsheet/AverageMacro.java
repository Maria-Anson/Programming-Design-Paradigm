package spreadsheet;

import javax.crypto.Mac;

public class AverageMacro implements MacroCommand {
  private int startRow;
  private int startColumn;
  private int endRow;
  private int endColumn;
  private int destRow;
  private int destColumn;

  public AverageMacro(int startRow, int startColumn, int endRow, int endColumn,
      int destRow, int destColumn) throws IllegalArgumentException{
    Helper.checkForNegative(startRow);
    Helper.checkForNegative(startColumn);
    Helper.checkForNegative(endRow);
    Helper.checkForNegative(endColumn);
    Helper.checkForNegative(destColumn);
    Helper.checkForNegative(destRow);
    Helper.checkForRelation(startRow, endRow);
    Helper.checkForRelation(startColumn, endColumn);
    this.startRow = startRow;
    this.startColumn = startColumn;
    this.endColumn = endColumn;
    this.endRow = endRow;
    this.destColumn = destColumn;
    this.destRow = destRow;
  }

  @Override
  public void executeMacro(SpreadSheetWithMacro obj){
    double sum = 0;
    for (int i = this.startRow; i<=this.endRow;i++)
      for (int j=this.startColumn ; j<=this.endColumn; j++)
        sum = sum + obj.get(i,j);
    double average = sum/((this.endRow - this.startRow + 1) * (this.endColumn - this.startColumn + 1));
    obj.set(this.destRow, this.destColumn, average);
  }

}
