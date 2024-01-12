/**
 * This class represents a 5-scale likert question, The scales are strongly agree, agree, neither
 * agree nor disagree, disagree and strongly disagree.
 */
public class LikertQuestion extends QuestionAbstract {

  //a valid question must have text
  public LikertQuestion(String text) throws IllegalArgumentException {
    super(text);
  }

  @Override
  public String getType() {
    return "Likert";
  }

  //a valid answer must be one of the 5 options, in a case-insensitive manner
  @Override
  public void answer(String enteredAnswer) {
    String[] options = {"strongly agree", "agree", "neither agree nor disagree", "disagree",
        "strongly disagree"};

    for (String option : options) {
      if (enteredAnswer.equalsIgnoreCase(option)) {
        this.enteredAnswer = enteredAnswer.toLowerCase();
        return;
      }
    }

    throw new IllegalArgumentException("Invalid answer");
  }

}
