/**
 * This class extends the Particle interface that implements the displacement formula given in the
 * assignment with the time constraints.
 */
public class SimpleProjectile implements Particle {


  private float x;
  private float y;
  private float vx;
  private float vy;

  /**
   * Constructs a SimpleProjectile object and initialize it with the given x,y,vx,vy.
   *
   * @param x  the initial x coordinate
   * @param y  the initial y coordiante
   * @param vx the horizontal acceleration
   * @param vy the vertical acceleration
   */
  SimpleProjectile(float x, float y, float vx, float vy) {
    this.x = x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
  }

  /**
   * Find the x and y coordinate by using the Newton's law of Motion for the displacement of the
   * particle.
   *
   * @param time the time at which the state must be obtained
   * @return the state of this particle as a formatted string.
   */
  public String getState(float time) {

    float g = -9.81f;
    if (time < 0) {
      return String.format("At time %.2f: position is (%.2f, %.2f)", time, this.x, this.y);
    }
    // Calculating the time taken to reach ground
    // By substituting y_displacement = 0 in Newton's formula
    float time_to_ground = 2 * this.vy / Math.abs(g);
    // If given time is greater than the time it hit the ground
    // the object stays there with no change on the y value
    if (time >= time_to_ground) {
      float x_displacement = this.vx * time_to_ground;
      return String.format("At time %.2f: position is (%.2f, %.2f)", time, this.x + x_displacement,
          this.y);
    } else {
      // If the particle is in the midair, we calculate both the x and y displacements
      // Assuming the particle in only under the influence of gravity when it's in the y direction
      float x_displacement = this.vx * time;
      float y_displacement = this.vy * time + (0.5f * g * time * time);
      return String.format("At time %.2f: position is (%.2f, %.2f)", time, this.x + x_displacement,
          this.y + y_displacement);
    }
  }
}
