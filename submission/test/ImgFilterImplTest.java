import model.filter.ImgFilterImpl;


import org.junit.Test;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


/**
 * This class contains unit tests for the ImgFilterImpl class.
 */
public class ImgFilterImplTest {


  @Test
  public void testFilterApplication() {
    int[][][] input = {
            {{10, 20, 30}, {40, 50, 60}, {70, 80, 90}},
            {{100, 110, 120}, {130, 140, 150}, {160, 170, 180}},
            {{190, 200, 210}, {220, 230, 240}, {250, 255, 255}}
    };
    double[][] filter = {{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};
    Map<String, int[][][]> imageMap = new HashMap<>();
    int[][][] expectedOutput = {
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}},
            {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}}
    };
    int[][][] actualOutput = new ImgFilterImpl().filterApplication(input, filter, imageMap);
    assertEquals(Arrays.deepToString(expectedOutput), Arrays.deepToString(actualOutput));
  }

}