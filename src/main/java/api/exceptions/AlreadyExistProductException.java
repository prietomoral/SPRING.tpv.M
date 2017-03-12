package api.exceptions;

public class AlreadyExistProductException extends ApiException{
    
    private static final long serialVersionUID = -1344640670884805485L;

    public static final String DESCRIPTION = "Embroidery ya existe!";

    public static final int CODE = 1;

    public AlreadyExistProductException() {
        this("");
    }

    public AlreadyExistProductException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
