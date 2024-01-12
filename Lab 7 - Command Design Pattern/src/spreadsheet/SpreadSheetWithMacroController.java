package spreadsheet;

import java.util.Scanner;

public class SpreadSheetWithMacroController extends SpreadSheetController{
  private SpreadSheetWithMacro sheet;

  public SpreadSheetWithMacroController(SpreadSheetWithMacro sheet, Readable readable, Appendable appendable) {
    super(sheet, readable, appendable);
    this.sheet = sheet;
  }
  @Override
  protected void processCommand(String userInstruction, Scanner sc, SpreadSheet sheet) {
    System.out.println(userInstruction);
    switch (userInstruction) {
      case "bulk-assign-value": {
        try {
          int fromRow = getRowNum(sc.next());
          int fromCol = sc.nextInt();
          int toRow = getRowNum(sc.next());
          int toCol = sc.nextInt();
          double value = sc.nextDouble();
          this.sheet.execute(new BulkAssignMacro(fromRow, toRow, fromCol, toCol, value));
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      }
      case "average": {
        try {
          int fromRow = getRowNum(sc.next());
          int fromCol = sc.nextInt();
          int toRow = getRowNum(sc.next());
          int toCol = sc.nextInt();
          int destRow = getRowNum(sc.next());
          int destCol = sc.nextInt();
          this.sheet.execute(new AverageMacro(fromRow, fromCol, toRow, toCol, destRow, destCol));
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      }
      case "range-assign": {
        try {
          int fromRow = getRowNum(sc.next());
          int fromCol = sc.nextInt();
          int toRow = getRowNum(sc.next());
          int toCol = sc.nextInt();
          int startValue = sc.nextInt();
          int increment = sc.nextInt();
          this.sheet.execute(new RangeAssignMacro(fromRow, toRow, fromCol, toCol, startValue,
              increment));
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      }
      default:
        super.processCommand(userInstruction, sc, this.sheet);
    }
  }

  @Override
  protected void printMenu() throws IllegalStateException {

    writeMessage("bulk-assign-value from-row from-col-num to-row to-col-num value "
        + "(Sets value of given row-column range)" + System.lineSeparator());
    super.printMenu();
  }
}
