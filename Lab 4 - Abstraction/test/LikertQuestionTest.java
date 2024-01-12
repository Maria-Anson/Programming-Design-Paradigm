
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class contains all the test cases required to test the functionalities of LikertQuestion.
 */
public class LikertQuestionTest {

  private String longRandom;

  public LikertQuestionTest() {
    longRandom = "aosdifjaso oifhas;ldihv;al skdfha;osidghv;osiadhvbasdjkhvn";
  }

  @Test
  public void testCreateValidLikertQuestion() {
    Random r = new Random(200);
    for (int i = 0; i < 1000; i++) {
      int start = r.nextInt(longRandom.length() - 1);
      int end = start + r.nextInt(longRandom.length() - start - 1) + 1;
      String questionText = longRandom.substring(start, end);
      Question q = new LikertQuestion(questionText + "?");
      assertEquals(questionText + "?", q.getQuestionText());
      assertEquals("Likert", q.getType());
    }

  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateLikertQuestionNoText() {
    new LikertQuestion("");
  }


  @Test
  public void testAnswerCorrectly() {
    String[] answers = {"strongly agree",
        "agree",
        "neither agree nor disagree",
        "disagree",
        "strongly disagree"};
    for (String answer : answers) {
      Question q = new LikertQuestion("Is this a trick question?");
      assertFalse(q.hasBeenAnswered());

      q.answer(answer);
      assertEquals(answer.toLowerCase(), q.getEnteredAnswer());
      assertTrue(q.hasBeenAnswered());
    }
  }

  @Test
  public void testAnswerInCorrectly() {
    String[] answers = {"weakly disagree", ""};
    for (String answer : answers) {
      Question q = new LikertQuestion("Is this a trick question?");
      assertFalse(q.hasBeenAnswered());

      try {
        q.answer(answer);
        fail("Likert question accepted an invalid answer");
      } catch (IllegalArgumentException e) {
        assertFalse(q.hasBeenAnswered());
      }
    }
  }


}