package spreadsheet;

public interface SpreadSheetWithMacro extends SpreadSheet{
  void execute(MacroCommand obj);
}
