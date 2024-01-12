
import org.junit.Test;
import java.util.Random;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for MCQ.
 */
public class MultipleChoiceQuestionTest {

  private String longRandom;

  public MultipleChoiceQuestionTest() {
    longRandom = "1.answer1,2.answer2,3.answer3";
  }

  /**
   * Test case to check valid question creation.
   */

  @Test
  public void testCreateValidMCQ() {
    Random r = new Random(200);
    for (int i = 0; i < 1000; i++) {
      int start = r.nextInt(longRandom.length() - 1);
      int end = start + r.nextInt(longRandom.length() - start - 1) + 1;
      String questionText = longRandom.substring(start, end);
      Question q = new MultipleChoiceQuestion(questionText);
      assertEquals("MCQ", q.getType());
    }
  }

  /**
   * Test case to check quesition creation with no text.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testCreateMCQNoText() {
    new MultipleChoiceQuestion("");
  }

  /**
   * Test case to check valid answers.
   */
  @Test
  public void testAnswerCorrectly() {

    String[] answers = {
        "1.answer1",
        "1.answer1,2.answer2",
        "1.answer1,2.answer2,3.answer3"
    };

    Question q1 = new MultipleChoiceQuestion("Is this a trick question?");
    assertFalse(q1.hasBeenAnswered());
    q1.answer(answers[0]);

    assertEquals("answer1", q1.getEnteredAnswer());
    assertTrue(q1.hasBeenAnswered());

    Question q2 = new MultipleChoiceQuestion("Is this a trick question?");
    assertFalse(q2.hasBeenAnswered());
    q2.answer(answers[1]);

    assertEquals("answer1,answer2", q2.getEnteredAnswer());
    assertTrue(q2.hasBeenAnswered());

    Question q3 = new MultipleChoiceQuestion("Is this a trick question?");
    assertFalse(q3.hasBeenAnswered());
    q3.answer(answers[2]);

    assertEquals("answer1,answer2,answer3", q3.getEnteredAnswer());
    assertTrue(q3.hasBeenAnswered());
  }

  /**
   * Test case to check invalid answers.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testAnswerInCorrectly() {

    String[] answers = {
        "5.answer1",
        "1.answer1,3.answer2",
        "1.answer1,2.answer2,32.answer3"
    };

    Question q1 = new MultipleChoiceQuestion("Is this a trick question?");
    assertFalse(q1.hasBeenAnswered());
    q1.answer(answers[0]);

    assertEquals("answer1", q1.getEnteredAnswer());
    assertTrue(q1.hasBeenAnswered());

    Question q2 = new MultipleChoiceQuestion("Is this a trick question?");
    assertFalse(q2.hasBeenAnswered());
    q2.answer(answers[1]);

    assertEquals("answer1,answer2", q2.getEnteredAnswer());
    assertTrue(q2.hasBeenAnswered());

    Question q3 = new MultipleChoiceQuestion("Is this a trick question?");
    assertFalse(q3.hasBeenAnswered());
    q3.answer(answers[2]);

    assertEquals("answer1,answer2,answer3", q3.getEnteredAnswer());
    assertTrue(q3.hasBeenAnswered());
  }
}


