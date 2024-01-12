package exception;

/**
 * This is a custom checked exception that indicates an error occurred while validating user input
 * or processing an image operation. This exception should be thrown when a user input is invalid
 * or an operation cannot be completed due to an error. This exception extends the built-in
 * Exception class and adds a constructor that takes a String argument as the error message.
 */
public class ValidCheckedException extends Exception {

  /**
   * Constructor for initialization.
   *
   * @param errorMessage message for exception.
   */
  public ValidCheckedException(String errorMessage) {
    super(errorMessage);
  }

}
