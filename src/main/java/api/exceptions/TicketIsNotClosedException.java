package api.exceptions;

public class TicketIsNotClosedException extends ApiException{
	
	private static final long serialVersionUID = -1344640670884456123L;

    public static final String DESCRIPTION = " El ticket introducido no está cerrado";

    public static final int CODE = 13;

    public TicketIsNotClosedException() {
        this("");
    }

    public TicketIsNotClosedException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
