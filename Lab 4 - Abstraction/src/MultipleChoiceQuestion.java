/**
 * A new class represent a multiple choice question.
 */
public class MultipleChoiceQuestion extends QuestionAbstract {

  /**
   * A constructor to initialize the variables.
   *
   * @param text the text to be stored in questionText.
   */
  public MultipleChoiceQuestion(String text) {
    super(text);
  }

  @Override
  public String getType() {
    return "MCQ";
  }

  @Override
  public void answer(String enteredAnswer) {
    int count = 1;
    for (String ans : enteredAnswer.split(",")) {
      if (Integer.parseInt(String.valueOf(ans.charAt(0))) == count) {
        this.enteredAnswer += "," + ans.substring(2);
        count++;
      } else {
        throw new IllegalArgumentException("Invalid answer");
      }
    }
    this.enteredAnswer = this.enteredAnswer.substring(1);
  }
}
