package api.exceptions;

public class AlreadyExistEmbroideryException extends ApiException{
    
    private static final long serialVersionUID = -1344640670884805485L;

    public static final String DESCRIPTION = "Embroidery ya existe!";

    public static final int CODE = 1;

    public AlreadyExistEmbroideryException() {
        this("");
    }

    public AlreadyExistEmbroideryException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
