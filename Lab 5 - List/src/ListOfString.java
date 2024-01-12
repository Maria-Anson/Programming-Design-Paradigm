

/**
 * This interface represents all the operations to be supported by a list of strings.
 */
public interface ListOfString {

  /**
   * Return the number of strings in this list.
   *
   * @return the size of this list
   */
  int size();

  /**
   * Add the given string to the front of this list and return the resulting list.
   *
   * @param s the string to be added
   * @return the resulting list
   */
  ListOfString addFront(String s);

  /**
   * Add the given string to the back of this list and return the resulting list.
   *
   * @param s the string to be added
   * @return the resulting list
   */
  ListOfString addBack(String s);

  /**
   * A method that adds the given string at the given index in this list . If index is 0, it means
   * this string should be added to the front of this list.
   *
   * @param index the position to be occupied by this object, starting at 0
   * @param s     the string to be added
   * @return the resulting list
   * @throws IllegalArgumentException if an invalid index is passed
   */
  ListOfString add(int index, String s) throws IllegalArgumentException;

  /**
   * Get the string at the specified index, with 0 meaning the first object in this list.
   *
   * @param index the specified index
   * @return the string at the specified index
   * @throws IllegalArgumentException if an invalid index is passed
   */
  String get(int index) throws IllegalArgumentException;

  /**
   * Determine if the specified words are found, in that order somewhere in this list. The words
   * matching is case-insensitive. The words may not be next to each other, but should be in the
   * same order as specified.
   *
   * @param words the list of words to be found in this list
   * @return true if these words are found in that order in this list or if the list of words is
   */
  boolean find(String[] words);

  /**
   * Return an independent, but reversed version of this list.
   *
   * @return the reverse of this list
   */
  ListOfString reverse();

  /**
   * Return a list of strings that interleaves the elements of this list and the other, beginning
   * with this list.
   *
   * @return an interleaved list of strings
   */
  ListOfString interleave(ListOfString other);

  int findTraverse(String[] words, int i);

  ListOfString reverseHelper(ListOfString object);
}
