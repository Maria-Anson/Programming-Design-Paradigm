import java.math.BigInteger;
import lookandsay.LookAndSayIterator;
import org.junit.Assert;
import org.junit.Test;


/**
 * Test class.
 */
public class LookAndSayIteratorTest {
  @Test
  public void testNext() {
    LookAndSayIterator iter = new LookAndSayIterator(new BigInteger("1"),
        new BigInteger("11111111111"));
    Assert.assertTrue(iter.hasNext());
    Assert.assertEquals(new BigInteger("1"), iter.next());
    Assert.assertTrue(iter.hasNext());
    Assert.assertEquals(new BigInteger("11"), iter.next());
    Assert.assertTrue(iter.hasNext());
    Assert.assertEquals(new BigInteger("21"), iter.next());
    Assert.assertTrue(iter.hasNext());
    Assert.assertEquals(new BigInteger("1211"), iter.next());
    Assert.assertTrue(iter.hasNext());
    Assert.assertEquals(new BigInteger("111221"), iter.next());
  }


}