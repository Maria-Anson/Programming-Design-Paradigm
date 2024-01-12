import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * This class tests the different behaviours of SimpleThermostat class.
 */
public class SimpleThermostatTest {

  /**
   * A method that is testing the constructor of the class SimpleThermostat.
   */
  @Test
  public void constructorTest() {
    // Condition to check for temperature greater than 50 degree Celsius
    boolean thrown1 = false;
    String errorMessage1 = "";
    try {
      new SimpleThermostat("A1", 51);
    } catch (IllegalArgumentException exception) {
      thrown1 = true;
      errorMessage1 = exception.getMessage();
    }
    assertTrue(thrown1);
    assertEquals(errorMessage1, "Temperature cannot be greater than 50");
    // Condition to check if a blank is empty
    boolean thrown2 = false;
    String errorMessage2 = "";
    try {
      new SimpleThermostat("", 43);
    } catch (IllegalArgumentException exception) {
      thrown2 = true;
      errorMessage2 = exception.getMessage();
    }
    assertTrue(thrown2);
    assertEquals(errorMessage2, "ID cannot be blank");
    // Condition to check for a normal case
    Thermostat thermostat = new SimpleThermostat("A01", 30);
    assertEquals(thermostat.getID(), "A01");
    assertEquals(thermostat.getSetTemperature(), 30 + 273.15, 0);
  }

  /**
   * Testing the equals method of the class SimpleThermostat.
   */
  @Test
  public void equalsTest() {
    // Same id and rounded to same temp
    Thermostat thermostat1 = new SimpleThermostat("A01", 25.371);
    Thermostat thermostat2 = new SimpleThermostat("A01", 25.374);
    assertEquals(thermostat1, thermostat2);
    // Different id and same temp
    Thermostat thermostat3 = new SimpleThermostat("A01", 25.37);
    Thermostat thermostat4 = new SimpleThermostat("A02", 25.37);
    assertFalse(thermostat3.equals(thermostat4));
    // Different id and different temp
    Thermostat thermostat5 = new SimpleThermostat("A01", 25.37);
    Thermostat thermostat6 = new SimpleThermostat("A02", 27.37);
    assertFalse(thermostat5.equals(thermostat6));
    // Same id and same temp
    Thermostat thermostat7 = new SimpleThermostat("A01", 25.37);
    Thermostat thermostat8 = new SimpleThermostat("A01", 25.37);
    assertEquals(thermostat7, thermostat8);
  }

  /**
   * Testing the getId method of the class SimpleThermostat.
   */
  @Test
  public void getIdTest() {
    Thermostat thermostat1 = new SimpleThermostat("A01", 25.371);
    assertEquals(thermostat1.getID(), "A01");
  }

  /**
   * Testing the getSetTemperature method of the class SimpleThermostat.
   */
  @Test
  public void getSetTemperatureTest() {
    Thermostat thermostat1 = new SimpleThermostat("A01", 25.371);
    assertEquals(thermostat1.getSetTemperature(), 25.371 + 273.15, 0);
  }

  /**
   * Testing the increaseSetTemperature method of the class SimpleThermostat.
   */
  @Test
  public void increaseSetTemperatureTest() {
    Thermostat thermostat1 = new SimpleThermostat("A01", 25.371);
    thermostat1.increaseSetTemperature();
    assertEquals(thermostat1.getSetTemperature(), 0.1 + 25.371 + 273.15, 0);
  }

  /**
   * Testing the decreaseSetTemperature method of the class SimpleThermostat.
   */
  @Test
  public void decreaseSetTemperatureTest() {
    Thermostat thermostat1 = new SimpleThermostat("A01", 25.371);
    thermostat1.decreaseSetTemperature();
    assertEquals(thermostat1.getSetTemperature(), -0.1 + 25.371 + 273.15, 0);
  }

}