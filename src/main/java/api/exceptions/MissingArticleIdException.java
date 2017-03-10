package api.exceptions;

public class MissingArticleIdException extends ApiException {
    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Artículo no existente";

    public static final int CODE = 1;

    public MissingArticleIdException() {
        this("");
    }

    public MissingArticleIdException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }
}
