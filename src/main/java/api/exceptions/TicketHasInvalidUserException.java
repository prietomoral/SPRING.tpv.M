package api.exceptions;

public class TicketHasInvalidUserException extends ApiException{
    
    private static final long serialVersionUID = -1344640670884805456L;

    public static final String DESCRIPTION = "El ticket no tiene usuario asociado o el usuario no tiene los datos necesarios";

    public static final int CODE = 10;

    public TicketHasInvalidUserException() {
        this("");
    }

    public TicketHasInvalidUserException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
