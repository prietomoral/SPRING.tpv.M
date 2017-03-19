package api.exceptions;

public class NotFoundAlertFamilyIdException extends ApiException {

    private static final long serialVersionUID = -8243829672941815345L;

    public static final String DESCRIPTION = "El identificador de la familia de alertas no existe";

    public static final int CODE = 404;

    public NotFoundAlertFamilyIdException() {
        this("");
    }

    public NotFoundAlertFamilyIdException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }
}
