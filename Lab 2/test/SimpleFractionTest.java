import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** This class contains all the test cases required to check the working of SimpleFraction class.
 *
 */
public class SimpleFractionTest {

  /**
   * inputTest function contains test case designed to check the behaviour when negative fraction is
   * given.
   */
  @Test
  public void invalidInputTest() {
    // Negative value in numerator
    boolean thrown1 = false;
    String message1 = "";
    try {
      new SimpleFraction(-20, 453);
    } catch (IllegalArgumentException error) {
      thrown1 = true;
      message1 = error.getMessage();
    }
    assertTrue(thrown1);
    assertEquals(message1, "This class can only represent a non-negative fraction");
    // Negative value in denominator
    boolean thrown2 = false;
    String message2 = "";
    try {
      new SimpleFraction(20, -453);
    } catch (IllegalArgumentException error) {
      thrown2 = true;
      message2 = error.getMessage();
    }
    assertTrue(thrown2);
    assertEquals(message2,"This class can only represent a non-negative fraction");
    // Negative value in both numerator and denominator which shouldn't raise error
    boolean thrown3 = false;
    String message3 = "";
    try {
      new SimpleFraction(-20, -453);
    } catch (IllegalArgumentException error) {
      thrown3 = true;
    }
    assertFalse(thrown3);
    assertEquals(message3,"");
    // Zero value in numerator and negative value in denominator which shouldn't raise error
    String message4 = "";
    boolean thrown4 = false;
    try {
      new SimpleFraction(0, -453);
    } catch (IllegalArgumentException error) {
      thrown4 = true;
      message4 = error.getMessage();
    }
    assertFalse(thrown4);
    assertEquals(message4, "");
    // Zero value in numerator and negative value in denominator which shouldn't raise error
    String message5 = "";
    boolean thrown5 = false;
    try {
      new SimpleFraction(10, 0);
    } catch (IllegalArgumentException error) {
      thrown5 = true;
      message5 = error.getMessage();
    }
    assertTrue(thrown5);
    assertEquals(message5, "The denominator cannot have zero as value");

  }

  /**
   * addUsingNumbers function is used to check the sum fraction of two numbers when the input is
   * given in terms. of integer numbers.
   */
  @Test
  public void addUsingNumbers() {
    // Simple fraction calculation 1/2 + 1/2
    Fraction fraction1 = new SimpleFraction(1, 2);
    assertEquals(fraction1.add(1, 2).toString(), "4/4");
    // Two Negative fraction calculation -1/-2 + -1/-2
    Fraction fraction2 = new SimpleFraction(-1, -2);
    assertEquals(fraction2.add(-1, -2).toString(), "4/4");
    // Checking big number calculation 95789319741/13213413 + 123467862378/1342342432
    Fraction fraction3 = new SimpleFraction(95789319, 13213413);
    assertEquals(fraction3.add(1234678623, 1342342432).toString(),
        "798369317/1500850784");
  }

  /**
   * addUsingObject function is used to check the sum fraction of two numbers when both the input
   * are objects.
   */
  @Test
  public void addUsingObject() {
    // Simple fraction calculation 1/2 + 1/2
    Fraction fraction = new SimpleFraction(1, 2);
    assertEquals(fraction.add(new SimpleFraction(1, 2)).toString(), "4/4");
    // Two Negative fraction calculation -1/-2 + -1/-2
    Fraction fraction2 = new SimpleFraction(-1, -2);
    assertEquals(fraction2.add(new SimpleFraction(-1, -2)).toString(), "4/4");
    // Checking big number calculation 95789319741/13213413 + 123467862378/1342342432
    Fraction fraction3 = new SimpleFraction(95789319, 13213413);
    assertEquals(fraction3.add(new SimpleFraction(1234678623, 1342342432)).toString(),
        "798369317/1500850784");
    // Adding two fraction with 0 numerator
    Fraction fraction4 = new SimpleFraction(0, 3);
    assertEquals(fraction4.add(new SimpleFraction(0, -1)).toString(),
        "0/-3");
  }

  /**
   * getDecimalValueTest function is used to check the decimal value of fraction with respect to
   * rounding places.
   */
  @Test
  public void getDecimalValueTest() {
    // A fraction to check if the decimals are getting rounded off as expected above 5
    Fraction fraction1 = new SimpleFraction(5, 3);
    assertEquals(fraction1.getDecimalValue(2), 1.67, 0);
    // A fraction to check if the decimals are getting rounded off as
    Fraction fraction2 = new SimpleFraction(10, 3);
    assertEquals(fraction2.getDecimalValue(2), 3.33, 0);
    // A fraction to check id the decimals are getting rounded when the values are large
    Fraction fraction3 = new SimpleFraction(95789319, 13213413);
    assertEquals(fraction3.getDecimalValue(2), 7.25, 0);
  }

  /**
   * toStringTest function is used to check if the string format is properly generated or not.
   */
  @Test
  public void toStringTest() {
    // Checking for a normal number
    Fraction fraction1 = new SimpleFraction(1, 2);
    assertEquals(fraction1.toString(), "1/2");
    // Checking if positive sign is removed when both the numerator and denominator
    Fraction fraction2 = new SimpleFraction(-1, -2);
    assertEquals(fraction2.toString(), "1/2");
  }
}