package api.exceptions;

public class InvalidFieldModifyUserException extends ApiException {

    private static final long serialVersionUID = -1344640612384805385L;

    public static final String DESCRIPTION = "";

    public static final int CODE = 22;

    public InvalidFieldModifyUserException() {
        this("");
    }

    public InvalidFieldModifyUserException(String detail) {
        super(DESCRIPTION + "" + detail, CODE);
    }

}
