package lookandsay;

import java.math.BigInteger;

/**
 * Implementation of iterator.
 */
public class LookAndSayIterator implements RIterator<BigInteger> {

  private BigInteger startSeed;
  private BigInteger end;
  private DoubleLinkedList currNode;

  /**
   * Constructor to initialize the object with startSeed and end value.
   *
   * @param startSeed starting number.
   * @param end       ending threshold.
   */
  public LookAndSayIterator(BigInteger startSeed, BigInteger end) {
    this.startSeed = startSeed;
    this.end = end;
    check(startSeed, true);
    currNode = new DoubleLinkedList(startSeed);
  }

  /**
   * Constructor to initialize the object with startSeed.
   *
   * @param startSeed starting number.
   */
  public LookAndSayIterator(BigInteger startSeed) {
    this.startSeed = startSeed;
    this.end = new BigInteger("9".repeat(100));
    check(startSeed, true);
    currNode = new DoubleLinkedList(startSeed);
  }

  /**
   * Constructor to initialize the object.
   */
  public LookAndSayIterator() {
    this.startSeed = BigInteger.valueOf(1);
    this.end = new BigInteger("9".repeat(100));
    check(startSeed, true);
    currNode = new DoubleLinkedList(startSeed);
  }

  private boolean check(BigInteger startSeed, boolean throwError) {
    if (throwError) {
      if (startSeed.compareTo(BigInteger.valueOf(0)) == -1 || startSeed.compareTo(end) == 1
          || String.valueOf(startSeed).contains("0")) {
        this.startSeed = null;
        end = null;
        throw new IllegalArgumentException("Please enter a valid startSeed and end value.");
      }
    } else {
      if (startSeed.compareTo(BigInteger.valueOf(0)) == 0 || startSeed.compareTo(end) == 1) {
        return false;
      }
    }
    return true;
  }

  private DoubleLinkedList calcNext() {
    String encodedString = "";
    String number = this.currNode.getValue().toString();
    for (int i = 0, count = 1; i < number.length(); i++) {
      if (i + 1 < number.length() && number.charAt(i) == number.charAt(i + 1)) {
        count++;
      } else {
        encodedString = encodedString.concat(Integer.toString(count))
            .concat(Character.toString(number.charAt(i)));
        count = 1;
      }
    }
    return new DoubleLinkedList(new BigInteger(encodedString));
  }

  private DoubleLinkedList calcPrev() {
    String encodedString = this.currNode.getValue().toString();
    String decodedString = "";

    if (encodedString.length() % 2 != 0) {
      return null;
    }
    int temp = 0;
    for (int i = 0; i < encodedString.length(); i++) {
      if (i % 2 == 0) {
        temp = Character.getNumericValue(encodedString.charAt(i));
      } else {
        decodedString += i * temp;
        temp = 0;
      }
    }
    return new DoubleLinkedList(new BigInteger(decodedString));
  }

  @Override
  public boolean hasPrevious() {
    DoubleLinkedList node;
    if (this.currNode.getPrev() == null) {
      node = calcPrev();
    } else {
      node = this.currNode.getPrev();
    }
    if (node == null) {
      return false;
    }
    return check(this.currNode.getValue(), false);
  }

  @Override
  public boolean hasNext() {
    DoubleLinkedList node;
    if (this.currNode.getNext() == null) {
      node = calcNext();
    } else {
      node = this.currNode.getNext();
    }
    return check(this.currNode.getValue(), false);
  }

  @Override
  public BigInteger prev() {
    DoubleLinkedList curr = currNode;
    if (hasPrevious()) {
      this.currNode.setPrev(calcPrev());
      this.currNode = this.currNode.getPrev();
      this.currNode.setNext(curr);
      System.out.println(curr.getValue());
      System.out.println(this.currNode.getValue());
      System.out.println("---");
    }
    return curr.getValue();
  }

  @Override
  public BigInteger next() {
    DoubleLinkedList curr = currNode;
    if (hasNext()) {
      this.currNode.setNext(calcNext());
      this.currNode = this.currNode.getNext();
      this.currNode.setPrev(curr);
    }
    return curr.getValue();
  }
}
