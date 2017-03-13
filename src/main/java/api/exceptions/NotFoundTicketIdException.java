package api.exceptions;

public class NotFoundTicketIdException extends ApiException{
	
	private static final long serialVersionUID = -1344640670884805123L;

    public static final String DESCRIPTION = "No se encuentra el ticket con el identificador utilizado";

    public static final int CODE = 11;

    public NotFoundTicketIdException() {
        this("");
    }

    public NotFoundTicketIdException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
