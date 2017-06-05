package api.exceptions;

public class NotFoundCashierBalanceException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No existe un Balance de Caja con ese id en el sistema";

    public static final int CODE = 13;

    public NotFoundCashierBalanceException() {
        this("");
    }

    public NotFoundCashierBalanceException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
