import java.util.Objects;

/**
 * A SimpleThermostat is a Thermostat that has an ID and a setTemperature.
 */
public class SimpleThermostat implements Thermostat {

  private String id;
  private double setTemperature;

  /**
   * A constructor that takes in a String ID and a double setTemperature. It checks if th
   * setTemperature is less than or equal to 50, and throws an IllegalArgumentException when
   * necessary. It also checks if the ID is blank, and if it is, it throws an
   * IllegalArgumentException.
   *
   * @param id The unique id that is to given for a thermostat
   * @param setTemperature The initial temperature to be given to the thermostat
   */
  public SimpleThermostat(String id, double setTemperature) throws IllegalArgumentException {
    if (setTemperature <= 50) {
      this.setTemperature = setTemperature;
    } else {
      throw new IllegalArgumentException("Temperature cannot be greater than 50");
    }
    if (id.equals("")) {
      throw new IllegalArgumentException("ID cannot be blank");
    } else {
      this.id = id;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Thermostat)) {
      return false;
    }
    SimpleThermostat thermostatObject = (SimpleThermostat) o;
    //double difference = Math.abs(this.setTemperature - thermostatObject.setTemperature);
    //return ((this.id == thermostatObject.id) && difference < 0.005 ));
    double roundedThisTemp = Math.round(this.setTemperature * 100) / 100;
    double roundedOtherTemp = Math.round(this.setTemperature * 100) / 100;

    return ((this.id == thermostatObject.id) && (roundedThisTemp == roundedOtherTemp));

  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.id);
  }

  @Override
  public String getID() {
    return this.id;
  }

  @Override
  public double getSetTemperature() {
    return this.setTemperature + 273.15;
  }

  @Override
  public void increaseSetTemperature() {
    this.setTemperature = this.setTemperature + 0.1;
  }

  @Override
  public void decreaseSetTemperature() {
    this.setTemperature = this.setTemperature - 0.1;
  }
}
