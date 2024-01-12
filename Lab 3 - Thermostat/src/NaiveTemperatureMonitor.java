import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a temperature monitor. It tracks several thermostats, and specifically
 * monitors how many of them have been set to too hot.
 */

public class NaiveTemperatureMonitor implements TemperatureMonitor {

  private final List<Thermostat> thermostatList;

  public NaiveTemperatureMonitor() {
    this.thermostatList = new ArrayList<Thermostat>();
  }

  @Override
  public void add(Thermostat t) {
    this.thermostatList.add(t);
  }

  @Override
  public void remove(Thermostat t) {
    this.thermostatList.remove(t);
  }

  @Override
  public int getNumberOfThermostats() {
    return this.thermostatList.size();
  }

  @Override
  public boolean tooMuchHeating() {
    double values = 0;
    int size = getNumberOfThermostats();
    for (Thermostat t : thermostatList) {
      values += t.getSetTemperature();
    }
    double average = values / size;
    return average > 23 + 273.15;
  }
}
