/**
 * Thermostat is an interface that has all the expected behaviour that every individual thermostat
 * should follow.
 */
public interface Thermostat {

  /**
   * Returns the unique ID of the current object.
   *
   * @return The ID of the object.
   */
  String getID();

  /**
   * A method to get the temperature in degrees Kelvin that the thermostat is set to.
   *
   * @return The set temperature.
   */
  double getSetTemperature();

  /**
   * A method that increases the set temperature for the thermostat.
   */
  void increaseSetTemperature();

  /**
   * A method that decreases the set temperature for the thermostat.
   */
  void decreaseSetTemperature();
}
