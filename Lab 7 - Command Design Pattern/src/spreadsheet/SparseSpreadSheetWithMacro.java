package spreadsheet;

public class SparseSpreadSheetWithMacro extends SparseSpreadSheet implements SpreadSheetWithMacro {

  public SparseSpreadSheetWithMacro(){
    super();
  }
  @Override
  public void execute(MacroCommand obj) {
    obj.executeMacro(this);
  }
}
