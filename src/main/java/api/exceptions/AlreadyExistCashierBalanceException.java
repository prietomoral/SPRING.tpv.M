package api.exceptions;

public class AlreadyExistCashierBalanceException extends ApiException{

    private static final long serialVersionUID = -1344640670884805485L;

    public static final String DESCRIPTION = "Balance de Caja ya existe para este día";

    public static final int CODE = 1;

    public AlreadyExistCashierBalanceException() {
        this("");
    }

    public AlreadyExistCashierBalanceException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
