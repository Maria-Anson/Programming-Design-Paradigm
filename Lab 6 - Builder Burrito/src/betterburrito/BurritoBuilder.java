package betterburrito;

import burrito.PortionSize;
import burrito.Protein;
import burrito.Size;
import burrito.Topping;

/**
 * BurritoBuilder is created as an abstract class.
 *
 * @param <T> is a generic class of BurritoBuilder
 */
public abstract class BurritoBuilder<T extends BurritoBuilder<T>> {

  /**
   * addProtein is a method to add protein to burrito.
   *
   * @param p    name of protein
   * @param size portion size
   * @return BurritoBuilder object
   */
  public abstract BurritoBuilder addProtein(Protein p, PortionSize size);

  /**
   * addTopping is a methos to add topping to the burrito.
   *
   * @param t    name of topping
   * @param size portion size
   * @return BurritoBuilder object
   */
  public abstract BurritoBuilder addTopping(Topping t, PortionSize size);

  /**
   * setSize is a method to set the size of burrito.
   *
   * @param size size of burrito
   * @return BurritoBuilder object
   */
  public abstract BurritoBuilder setSize(Size size);

  /**
   * build builds the burrito.
   *
   * @return ObservableBurrito object
   */
  public abstract ObservableBurrito build();

  /**
   * returnBuilder is a function that returns the BurritoBuilder object.
   *
   * @return BurritoBuilder object
   */
  protected abstract T returnBuilder();

  /**
   * size sets the size of the burrito.
   *
   * @param size size of burrito
   * @return BurritoBuilder object
   */
  public abstract T size(Size size);
}
