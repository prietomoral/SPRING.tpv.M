package api.exceptions;

public class NotFoundAlertIdException extends ApiException {

	private static final long serialVersionUID = -8243829672941815345L;

	public static final String DESCRIPTION = "El identificador de la alerta  no existe";

	public static final int CODE = 404;

	public NotFoundAlertIdException() {
		this("");
	}

	public NotFoundAlertIdException(String detail) {
		super(DESCRIPTION + ". " + detail, CODE);
	}
}
