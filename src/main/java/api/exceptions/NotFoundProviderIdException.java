package api.exceptions;

public class NotFoundProviderIdException extends ApiException {
    private static final long serialVersionUID = -8243829572941815345L;

    public static final String DESCRIPTION = "El identificador del proveedor no existe";

    public static final int CODE = 1;

    public NotFoundProviderIdException() {
        this("");
    }

    public NotFoundProviderIdException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }
}
