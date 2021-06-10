/**
 * An exception object related to type errors cause in the parsing of a user's
 * program.
 * 
 * @author Mauricio Vides
 * 
 */
class TypeError extends Exception {
    private static final long serialVersionUID = 1L;

    public TypeError(final String message) {
        super(message);
    }
}