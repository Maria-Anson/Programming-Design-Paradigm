import org.junit.Assert;
import org.junit.Test;

/**
 * This class contains different samples to test the working of SimpleProjectile class based on the
 * constraints given in assignment.
 */
public class SimpleProjectileTest {

  /**
   * Checking with the given sample which goes to the if clause as time=time to ground.
   */
  @Test
  public void testSampleCheck1() {
    //Creating an object particle with value x=0,y=0,vx=0,vy=10.
    SimpleProjectile particle = new SimpleProjectile(0, 0, 0, 10);
    //Calculating the displaced x and y at time t=2.0387.
    String formattedString = particle.getState(2.0387f);
    Assert.assertEquals(formattedString,
        String.format("At time %.2f: position is (%.2f, %.2f)", 2.0387f, 0f, 0f));
  }

  /**
   * Checking with negative value for time.
   */
  @Test
  public void testSampleCheck2() {

    //Creating an object particle with value x=0,y=0,vx=0,vy=10.
    SimpleProjectile particle = new SimpleProjectile(10, 0, 0, 10);
    //Calculating the displaced x and y at time t=-2.0387.
    String formattedString = particle.getState(-2.0387f);
    Assert.assertEquals(formattedString,
        String.format("At time %.2f: position is (%.2f, %.2f)", -2.0387f, 10f, 0f));
  }

  /**
   * Checking with a time less than time to ground which goes to the else clause.
   */
  @Test
  public void testSampleCheck3() {
    //Creating an object particle with value x=0,y=0,vx=0,vy=10.
    SimpleProjectile particle = new SimpleProjectile(0, 0, 0, 10);
    //Calculating the displaced x and y at time t=2.0.
    String formattedString = particle.getState(2f);
    Assert.assertEquals(formattedString,
        String.format("At time %.2f: position is (%.2f, %.2f)", 2f, 0f, 0.38f));
  }

  /**
   * Checking with a time greater than time to ground which goes to the if clause.
   */
  @Test
  public void testSampleCheck4() {
    //Creating an object particle with value x=0,y=0,vx=0,vy=10.
    SimpleProjectile particle = new SimpleProjectile(0, 0, 0, 10);
    //Calculating the displaced x and y at time t=2.0.
    String formattedString = particle.getState(5.2f);
    Assert.assertEquals(formattedString,
        String.format("At time %.2f: position is (%.2f, %.2f)", 5.2f, 0f, 0f));
  }


}