
/**
 * This represents a non-empty node of the list. It contains a piece of data along with the rest of
 * the list
 */
public class NonEmptyList implements ListOfString {

  private String str;
  private ListOfString rest;

  public NonEmptyList(String s, ListOfString rest) {
    this.str = s;
    this.rest = rest;
  }

  @Override
  public int size() {
    return 1 + this.rest.size();
  }

  @Override
  public ListOfString addFront(String s) {
    return new NonEmptyList(s, this);
  }

  @Override
  public ListOfString addBack(String s) {
    this.rest = this.rest.addBack(s);
    return this;
  }

  @Override
  public ListOfString add(int index, String s) {
    if (index == 0) {
      return addFront(s);
    } else {
      return new NonEmptyList(this.str, this.rest.add(index - 1, s));
    }
  }

  @Override
  public String get(int index) throws IllegalArgumentException {
    if (index == 0) {
      return this.str;
    }
    return this.rest.get(index - 1);
  }

  @Override
  public boolean find(String[] words) {
    int counter = findTraverse(words, 0);
    return counter == words.length;
  }

  @Override
  public int findTraverse(String[] words, int i) {
    if (words.length == i) {
      return 0;
    }
    if (this.str.equalsIgnoreCase(words[i])) {
      return 1 + this.rest.findTraverse(words, i + 1);
    } else {
      return this.rest.findTraverse(words, i);
    }
  }

  @Override
  public ListOfString reverse() {
    return reverseHelper(new EmptyList());
  }

  @Override
  public ListOfString reverseHelper(ListOfString object) {
    return this.rest.reverseHelper(object.addFront(this.str));
  }

  @Override
  public ListOfString interleave(ListOfString other) {
    return new NonEmptyList(this.str, other.interleave(this.rest));
  }

}
