package lookandsay;

import java.math.BigInteger;

/**
 * Implementation of double linked list.
 */
public class DoubleLinkedList {

  private BigInteger val;
  private DoubleLinkedList prev;
  private DoubleLinkedList next;

  /**
   * Constructor where the object is created with a value.
   *
   * @param val initial value
   */
  public DoubleLinkedList(BigInteger val) {
    this.val = val;
    this.prev = null;
    this.next = null;
  }

  /**
   * Set the previous node.
   *
   * @param prev previous doublelinkedlist node.
   */
  public void setPrev(DoubleLinkedList prev) {
    this.prev = prev;
  }

  /**
   * Get the previous node.
   *
   * @return doublelinkedlist node.
   */
  public DoubleLinkedList getPrev() {
    return this.prev;
  }

  /**
   * Set the next node.
   *
   * @param next next doublelinkedlist node.
   */
  public void setNext(DoubleLinkedList next) {
    this.next = next;
  }

  /**
   * get the next node.
   *
   * @return doublelinkedlist node.
   */
  public DoubleLinkedList getNext() {
    return this.next;
  }

  /**
   * Get the value of the current node.
   *
   * @return value in biginteger
   */
  public BigInteger getValue() {
    return this.val;
  }

}
