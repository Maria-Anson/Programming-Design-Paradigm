package betterburrito;

import burrito.PortionSize;
import burrito.Protein;
import burrito.Size;
import burrito.Topping;

/**
 * VeggieBurrito is a class that represents veggie burrito.
 */
public class VeggieBurrito extends CustomBurrito {
  public VeggieBurrito(Size size) {
    super(size);
  }

  /**
   * VeggieBurritoBuilder is a class that is used to create a veggieburrito.
   */
  public static class VeggieBurritoBuilder extends CustomBurritoBuilder {

    /**
     * Creating a new CustomBurrito object by adding default toppings and proteins.
     */
    public VeggieBurritoBuilder() {
      burritoObj = new CustomBurrito(Size.Normal);
      this.addProtein(Protein.BlackBeans, PortionSize.Normal)
          .addTopping(Topping.MediumSalsa, PortionSize.Normal)
          .addTopping(Topping.Cheese, PortionSize.Normal)
          .addTopping(Topping.Lettuce, PortionSize.Normal)
          .addTopping(Topping.Guacamole, PortionSize.Normal);
    }

    public VeggieBurritoBuilder noCheese() {
      burritoObj.toppings.remove(Topping.Cheese);
      return returnBuilder();
    }

    public VeggieBurritoBuilder noBlackBeans() {
      burritoObj.proteins.remove(Protein.BlackBeans);
      return returnBuilder();
    }

    public VeggieBurritoBuilder noMediumSalsa() {
      burritoObj.toppings.remove(Topping.MediumSalsa);
      return returnBuilder();
    }

    public VeggieBurritoBuilder noLettuce() {
      burritoObj.toppings.remove(Topping.Lettuce);
      return returnBuilder();
    }

    public VeggieBurritoBuilder noGuacamole() {
      burritoObj.toppings.remove(Topping.Guacamole);
      return returnBuilder();
    }

    @Override
    public VeggieBurritoBuilder size(Size size) {
      burritoObj.size = size;
      return returnBuilder();
    }

    @Override
    public ObservableBurrito build() {
      return burritoObj;
    }

    @Override
    protected VeggieBurritoBuilder returnBuilder() {
      return this;
    }


  }

}
