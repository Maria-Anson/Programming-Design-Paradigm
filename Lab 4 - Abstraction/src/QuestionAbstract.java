/**
 * This is an abstract class that contains the common functions for all questions.
 */
public abstract class QuestionAbstract implements Question {

  protected String enteredAnswer;
  protected String questionText;

  /**
   * A constructor to intialize the variables.
   *
   * @param text text to be stored in questionText.
   * @throws IllegalArgumentException throws when encountered illegal text.
   */
  public QuestionAbstract(String text) throws IllegalArgumentException {
    if ((text.length() == 0)) {
      throw new IllegalArgumentException("Invalid question text");
    }
    this.enteredAnswer = "";
    this.questionText = text;
  }

  @Override
  public String getEnteredAnswer() {
    if (!hasBeenAnswered()) {
      throw new IllegalStateException("solution.Question not attempted yet!");
    } else {
      return enteredAnswer;
    }
  }

  @Override
  public String getQuestionText() {
    return questionText;
  }

  @Override
  public boolean hasBeenAnswered() {
    return (enteredAnswer != null && !enteredAnswer.equals(""));
  }

}
