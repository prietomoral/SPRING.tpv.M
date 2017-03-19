package api.exceptions;

public class NotFoundVoucherException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No se encontro un Voucher con ese identificador";

    public static final int CODE = 13;

    public NotFoundVoucherException() {
        this("");
    }

    public NotFoundVoucherException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
