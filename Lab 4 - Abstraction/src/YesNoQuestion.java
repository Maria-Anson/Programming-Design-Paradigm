/**
 * This class represents a yes/no question.
 */
public class YesNoQuestion extends QuestionAbstract {

  /**
   * a question must be non empty and should end with a question mark.
   */

  public YesNoQuestion(String text) throws IllegalArgumentException {
    super(text);
    if (questionText.charAt(questionText.length() - 1) != '?') {
      throw new IllegalArgumentException("Invalid question text");
    }
  }

  @Override
  public String getType() {
    return "YesNo";
  }

  //a valid answer must be a yes or no
  @Override
  public void answer(String enteredAnswer) {
    if ((enteredAnswer.toLowerCase().equals("yes")) || (enteredAnswer.toLowerCase().equals("no"))) {
      this.enteredAnswer = enteredAnswer.toLowerCase();
    } else {
      throw new IllegalArgumentException("Invalid answer: " + enteredAnswer);
    }
  }

}
