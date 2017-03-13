package api.exceptions;

public class TicketHasNoUserException extends ApiException{
    
    private static final long serialVersionUID = -1344640670884805456L;

    public static final String DESCRIPTION = "El ticket no tiene usuario asociado";

    public static final int CODE = 10;

    public TicketHasNoUserException() {
        this("");
    }

    public TicketHasNoUserException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
