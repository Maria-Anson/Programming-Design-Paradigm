import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Reader;
import java.io.StringReader;
import java.util.Random;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import spreadsheet.SparseSpreadSheet;
import spreadsheet.SparseSpreadSheetWithMacro;
import spreadsheet.SpreadSheet;
import spreadsheet.SpreadSheetController;
import spreadsheet.SpreadSheetWithMacro;
import spreadsheet.SpreadSheetWithMacroController;

public class SparseSpreadSheetWithMacroTest{
  private SpreadSheetWithMacro sheet;

  @Before
  public void setup() {
    sheet = new SparseSpreadSheetWithMacro();
  }

  @Test
  public void testGetSet() {
    Random r = new Random(100);
    double[][] expectedSet = new double[100][100];
    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        double num = r.nextDouble();
        expectedSet[i][j] = num;
        assertTrue(sheet.isEmpty(i, j));
        assertEquals(0.0, sheet.get(i, j), 0.001);
        sheet.set(i, j, num);
        assertFalse(sheet.isEmpty(i, j));
      }
    }

    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        assertEquals(expectedSet[i][j], sheet.get(i, j), 0.01);
      }
    }
  }

  @Test
  public void testGetWidthHeight() {
    for (int i = 0; i < 100; i = i + 1) {
      for (int j = 0; j < 100; j = j + 1) {
        sheet.set(i, j, 0);
        assertEquals((i + 1), sheet.getHeight());
        if (i == 0) {
          assertEquals((j + 1), sheet.getWidth());
        } else {
          assertEquals(100, sheet.getWidth());
        }
      }
    }

    sheet.set(1000, 1000, 0);
    assertEquals(1001, sheet.getWidth());
    assertEquals(1001, sheet.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetWithNegativeRow() {
    sheet.set(0, 0, 1);
    sheet.set(0, 1, 9);
    sheet.get(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetWithNegativeColumn() {
    sheet.set(0, 0, 1);
    sheet.set(0, 1, 9);
    sheet.get(0, -1);
  }

  private boolean testBulkAssignHelper(SpreadSheetWithMacro sheet, int rowStart, int rowEnd, int colStart,
      int colEnd, double value) {
    for (int i = rowStart; i <= rowEnd; i++) {
      for (int j = colStart; j <= colEnd; j++) {
        System.out.println("i:" + i + ", j:" + j + ", value:" + sheet.get(i, j));
        if (sheet.get(i, j) != value) {
          return false;
        }
      }
    }
    return true;
  }

  @Test
  public void testBulkAssign() {

    SpreadSheetWithMacro model = new SparseSpreadSheetWithMacro();
    Reader rd = new StringReader("bulk-assign-value A 0 E 5 100\n");
    Appendable ap = System.out;
    SpreadSheetController controller = new SpreadSheetWithMacroController(model, rd, ap);
    controller.control();
    int rowStart = 0;
    int rowEnd = 4;
    int colStart = 0;
    int colEnd = 5;
    int value = 100;
    assertTrue(testBulkAssignHelper(model, rowStart, rowEnd, colStart, colEnd, value));
  }
  @Test
  public void testAverage() {

    SpreadSheetWithMacro model = new SparseSpreadSheetWithMacro();
    Reader rd = new StringReader("bulk-assign-value A 0 C 0 100\n "
        + "bulk-assign-value A 1 C 1 200\n "
        + "bulk-assign-value A 2 C 2 300\n"
        + "average A 0 C 2 D 4");
    Appendable ap = System.out;
    SpreadSheetController controller = new SpreadSheetWithMacroController(model, rd, ap);
    controller.control();
    assertEquals(200, model.get(3, 4), 0);
  }

  @Test
  public void testRangeAssign() {
    SpreadSheetWithMacro model = new SparseSpreadSheetWithMacro();
    Reader rd = new StringReader("range-assign A 0 D 0 5 2");
    Appendable ap = System.out;
    SpreadSheetController controller = new SpreadSheetWithMacroController(model, rd, ap);
    controller.control();
    assertEquals(5, model.get(0, 0), 0);
    assertEquals(7, model.get(1, 0), 0);
    assertEquals(9, model.get(2, 0), 0);
    assertEquals(11, model.get(3, 0), 0);

  }
}