package spreadsheet;

public class Helper{
  static void checkForNegative(int num){
    if (num<0)
      throw new IllegalArgumentException("Row or column value cannot be negative");
  }

  static void checkForRelation(int start, int end){
    if (end<start)
      throw new IllegalArgumentException("The end value of column or row cannot be smaller than the "
          + "start value.");
  }

}
