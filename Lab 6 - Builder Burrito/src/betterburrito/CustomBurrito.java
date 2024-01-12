package betterburrito;

import burrito.PortionSize;
import burrito.Protein;
import burrito.Size;
import burrito.Topping;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a custom burrito that can have an arbitrary number of proteins and
 * toppings, both of arbitrary portion sizes.
 */
public class CustomBurrito implements ObservableBurrito {

  protected Size size;
  protected final Map<Protein, PortionSize> proteins;
  protected final Map<Topping, PortionSize> toppings;

  /**
   * Create a custom burrito of the specified size.
   *
   * @param size the size of this burrito
   */
  public CustomBurrito(Size size) {
    this.size = size;
    this.proteins = new HashMap<Protein, PortionSize>();
    this.toppings = new HashMap<Topping, PortionSize>();

  }

  @Override
  public PortionSize hasTopping(Topping name) {
    return this.toppings.getOrDefault(name, null);
  }

  @Override
  public PortionSize hasProtein(Protein name) {
    return this.proteins.getOrDefault(name, null);
  }

  @Override
  public double cost() {
    double cost = 0.0;
    for (Map.Entry<Protein, PortionSize> item : this.proteins.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultipler();
    }

    for (Map.Entry<Topping, PortionSize> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultipler();
    }
    return cost + this.size.getBaseCost();
  }

  /**
   * The CustomBurritoBuilder class is an inner class of the BurritoBuilder class. It is used to
   * handle all the fixed variables so that the later modification cannot be done.
   */
  public static class CustomBurritoBuilder extends BurritoBuilder<CustomBurritoBuilder> {

    CustomBurrito burritoObj;

    private void isSizeNull() {
      if (burritoObj.size == null) {
        throw new IllegalStateException("The size is null");
      }
    }

    public CustomBurritoBuilder() {
      burritoObj = new CustomBurrito(null);
    }

    @Override
    public ObservableBurrito build() {
      return burritoObj;
    }

    @Override
    protected CustomBurritoBuilder returnBuilder() {
      return this;
    }

    public CustomBurritoBuilder size(Size size) {
      burritoObj.size = size;
      return returnBuilder();
    }

    public CustomBurritoBuilder setSize(Size size) {
      burritoObj.size = size;
      return returnBuilder();
    }

    @Override
    public CustomBurritoBuilder addProtein(Protein p, PortionSize size) {
      isSizeNull();
      burritoObj.proteins.put(p, size);
      return returnBuilder();
    }

    @Override
    public CustomBurritoBuilder addTopping(Topping t, PortionSize size) {
      isSizeNull();
      burritoObj.toppings.put(t, size);
      return returnBuilder();
    }

  }

}
