/**
 * SimpleFraction is a class that implements the Fraction interface. The class implements a
 * functions to add two fraction numbers and a function to calculate the decimal value.
 */
public class SimpleFraction implements Fraction {

  private int numerator;
  private int denominator;

  /**
   * This constructor is used to initialize the numerator and denominator. It also checks for
   * negative fraction values and raises respective exception.
   *
   * @param numerator   Numerator value of the fraction to represent
   * @param denominator Denominator value of the fraction to represent
   */
  public SimpleFraction(int numerator, int denominator) {
    this.numerator = numerator;
    this.denominator = denominator;
    if (this.denominator == 0) {
      throw new IllegalArgumentException("The denominator cannot have zero as value");
    }
    if (this.numerator == 0) {
      return;
    }
    if (this.numerator < 0 && this.denominator < 0) {
      this.numerator = -numerator;
      this.denominator = -denominator;
    } else if (this.numerator < 0 || this.denominator < 0) {
      throw new IllegalArgumentException("This class can only represent a non-negative fraction");
    }
  }

  @Override
  public Fraction add(Fraction other) {
    String[] parts = other.toString().split("/");
    int numerator = Integer.parseInt(parts[0]);
    int denominator = Integer.parseInt(parts[1]);

    int newNumerator = this.numerator * denominator + this.denominator * numerator;
    int newDenominator = this.denominator * denominator;
    return new SimpleFraction(newNumerator, newDenominator);
  }

  @Override
  public Fraction add(int numerator, int denominator) {
    return add(new SimpleFraction(numerator, denominator));
  }

  @Override
  public double getDecimalValue(int places) {
    double scaleFactor = Math.pow(10, places);
    double fraction = (double) this.numerator / this.denominator;
    return Math.round(fraction * scaleFactor) / scaleFactor;
  }

  @Override
  public String toString() {
    return String.format("%d/%d", this.numerator, this.denominator);
  }
}
