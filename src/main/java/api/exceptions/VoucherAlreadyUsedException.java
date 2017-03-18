package api.exceptions;

public class VoucherAlreadyUsedException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Voucher ya utilizado.";

    public static final int CODE = 13;

    public VoucherAlreadyUsedException() {
        this("");
    }

    public VoucherAlreadyUsedException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
