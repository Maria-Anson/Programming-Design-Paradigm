import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

/**
 * NaiveTemperatureMonitorTest is a class that contains all the test cases for each behaviour of the
 * class.
 */
public class NaiveTemperatureMonitorTest {

  /**
   * A test case for the add method.
   */
  @Test
  public void addTest() {
    TemperatureMonitor monitor1 = new NaiveTemperatureMonitor();
    monitor1.add(new SimpleThermostat("A01", 23));
    monitor1.add(new SimpleThermostat("A02", 25));
    assertEquals(monitor1.getNumberOfThermostats(), 2);
  }

  /**
   * Testing the remove method.
   */
  @Test
  public void removeTest() {
    TemperatureMonitor monitor = new NaiveTemperatureMonitor();
    Thermostat t1 = new SimpleThermostat("A01", 23);
    monitor.add(t1);
    Thermostat t2 = new SimpleThermostat("A02", 25);
    monitor.add(t2);
    assertEquals(monitor.getNumberOfThermostats(), 2);
    monitor.remove(t2);
    assertEquals(monitor.getNumberOfThermostats(), 1);
  }

  /**
   * Testing the getNumberOfThermostats method.
   */
  @Test
  public void getNumberOfThermostatsTest() {
    TemperatureMonitor monitor = new NaiveTemperatureMonitor();
    Thermostat t1 = new SimpleThermostat("A01", 23);
    monitor.add(t1);
    Thermostat t2 = new SimpleThermostat("A02", 25);
    monitor.add(t2);
    assertEquals(monitor.getNumberOfThermostats(), 2);
  }

  /**
   * Testing the tooMuchHeating method.
   */
  @Test
  public void tooMuchHeatingTest() {
    TemperatureMonitor monitor = new NaiveTemperatureMonitor();
    Thermostat t1 = new SimpleThermostat("A01", 23);
    monitor.add(t1);
    Thermostat t2 = new SimpleThermostat("A02", 25);
    monitor.add(t2);
    assertEquals(monitor.getNumberOfThermostats(), 2);
    assertTrue(monitor.tooMuchHeating());
  }
}

