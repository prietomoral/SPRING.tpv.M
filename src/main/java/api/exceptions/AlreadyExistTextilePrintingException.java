package api.exceptions;

public class AlreadyExistTextilePrintingException extends ApiException{
    
    private static final long serialVersionUID = -1344640670984805385L;

    public static final String DESCRIPTION = "Este Textile Printing ya existe!";

    public static final int CODE = 1;

    public AlreadyExistTextilePrintingException() {
        this("");
    }

    public AlreadyExistTextilePrintingException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
