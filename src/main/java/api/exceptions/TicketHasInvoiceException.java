package api.exceptions;

public class TicketHasInvoiceException extends ApiException{
	
	private static final long serialVersionUID = -1344640670884123123L;

    public static final String DESCRIPTION = " El ticket introducido ya tiene una factura asociada";

    public static final int CODE = 12;

    public TicketHasInvoiceException() {
        this("");
    }

    public TicketHasInvoiceException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
