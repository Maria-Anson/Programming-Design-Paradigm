/**
 * Fraction is an interface that has the default behaviours expected in fraction objects.
 */
public interface Fraction {

  /**
   * add function is used to calculate the sum of two fractions when an input fraction object is
   * given.
   *
   * @param other SimpleFraction object
   * @return SimpleFraction object : Sum of the two fractions
   */
  Fraction add(Fraction other);

  /**
   * add function is used to calculate the sum of two fractions when an input numerator and
   * denominator is given.
   *
   * @param numerator   Numerator of input fraction
   * @param denominator Denominator of input fraction
   * @return SimpleFraction object : Sum of the two fractions
   */
  Fraction add(int numerator, int denominator);

  /**
   * getDecimalValue is used to get the decimal value of the fraction which is rounded to the
   * required input places.
   *
   * @param places Number of places after decimal point
   * @return double : The decimal point value
   */
  double getDecimalValue(int places);

  /**
   * toString function displays the fields of fraction in the n/d format.
   *
   * @return String
   */
  String toString();
}
