/**
 * TemperatureMonitor is an interface that has the behaviour that is required for a list of
 * thermostat to follow.
 */
public interface TemperatureMonitor {

  /**
   * Adds a thermostat to the list of thermostats.
   *
   * @param t The thermostat to add.
   */
  void add(Thermostat t);

  /**
   * Remove the thermostat from the list of thermostats.
   *
   * @param t The thermostat to be removed.
   */
  void remove(Thermostat t);

  /**
   * Returns the number of thermostats in the system.
   *
   * @return The number of thermostats in the system.
   */
  int getNumberOfThermostats();

  /**
   * Returns true if the room is too hot, false otherwise.
   *
   * @return A boolean value.
   */
  boolean tooMuchHeating();
}
