package api.exceptions;

public class UpdateInvalidCashierBalanceException extends ApiException{

    private static final long serialVersionUID = -1344640670884805485L;

    public static final String DESCRIPTION = "Sólo puede modificar el Balance de Caja del día actual";

    public static final int CODE = 1;

    public UpdateInvalidCashierBalanceException() {
        this("");
    }

    public UpdateInvalidCashierBalanceException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
