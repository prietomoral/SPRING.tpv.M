package api.exceptions;

public class NotFoundCashierBalancesException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No existen Balances de Caja en el sistema";

    public static final int CODE = 13;

    public NotFoundCashierBalancesException() {
        this("");
    }

    public NotFoundCashierBalancesException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
