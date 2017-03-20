package api.exceptions;

public class WarningNotCanLessCritical extends ApiException {

	private static final long serialVersionUID = 6739903246653164354L;

	public static final String DESCRIPTION = "Advertencia no puede ser menor critico";

	public static final int CODE = 400;

	public WarningNotCanLessCritical() {
		this("");
	}

	public WarningNotCanLessCritical(String detail) {
		super(DESCRIPTION + ". " + detail, CODE);
	}

}
