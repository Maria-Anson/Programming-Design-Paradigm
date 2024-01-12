package lookandsay;

import java.util.Iterator;

/**
 * Interface that extends the Iterator.
 *
 * @param <T> input object type.
 */
public interface RIterator<T> extends Iterator<T> {

  T prev();

  boolean hasPrevious();
}
