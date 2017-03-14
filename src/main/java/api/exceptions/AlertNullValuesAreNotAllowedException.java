package api.exceptions;

public class AlertNullValuesAreNotAllowedException extends ApiException {

	private static final long serialVersionUID = 5322508063172627633L;

	public static final String DESCRIPTION = "No se permiten valores vacíos";

	public static final int CODE = 422;

	public AlertNullValuesAreNotAllowedException() {
		this("");
	}

	public AlertNullValuesAreNotAllowedException(String detail) {
		super(DESCRIPTION + ". " + detail, CODE);
	}
}
