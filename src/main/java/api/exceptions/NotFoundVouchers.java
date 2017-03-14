package api.exceptions;

public class NotFoundVouchers extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No existen Vouchers en el sistema";

    public static final int CODE = 13;

    public NotFoundVouchers() {
        this("");
    }

    public NotFoundVouchers(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
