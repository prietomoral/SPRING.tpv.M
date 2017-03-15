package api;

import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import api.exceptions.AlertNullValuesAreNotAllowedException;
import api.exceptions.AlreadyExistUserFieldException;
import api.exceptions.ApiException;
import api.exceptions.ErrorMessage;
import api.exceptions.InvalidUserFieldException;
import api.exceptions.MalformedHeaderException;
import api.exceptions.MissingArticleIdException;
import api.exceptions.NotFoundAlertIdException;
import api.exceptions.NotFoundTicketIdException;

import api.exceptions.NotFoundUserIdException;
import api.exceptions.TicketHasInvoiceException;
import api.exceptions.UnauthorizedException;
import api.exceptions.NotFoundVouchers;
import api.exceptions.TicketHasInvalidUserException;
import api.exceptions.TicketIsNotClosedException;

@ControllerAdvice
public class ApiExceptionHandler {


	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NotFoundUserIdException.class, NotFoundTicketIdException.class, TicketHasInvalidUserException.class,
			NotFoundAlertIdException.class, NotFoundVouchers.class })
	@ResponseBody
	public ErrorMessage notFoundRequest(ApiException exception) {
		ErrorMessage apiErrorMessage = new ErrorMessage(exception);
		return apiErrorMessage;
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ UnauthorizedException.class })
	public void unauthorized(ApiException exception) {
		ErrorMessage apiErrorMessage = new ErrorMessage(exception);
		LogManager.getLogger(this.getClass()).info("  ERROR: UNAUTHORIZED, " + apiErrorMessage);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ MalformedHeaderException.class, InvalidUserFieldException.class,
			MissingArticleIdException.class, AlertNullValuesAreNotAllowedException.class })
	@ResponseBody
	public ErrorMessage badRequest(ApiException exception) {
		ErrorMessage apiErrorMessage = new ErrorMessage(exception);
		return apiErrorMessage;
	}

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({AlreadyExistUserFieldException.class, AlreadyExistUserFieldException.class, TicketIsNotClosedException.class, TicketHasInvoiceException.class})
    @ResponseBody
    public ErrorMessage conflictRequest(ApiException exception) {
        ErrorMessage apiErrorMessage = new ErrorMessage(exception);
        return apiErrorMessage;
    }

	// @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	// @ExceptionHandler({Exception.class})
	// @ResponseBody
	// public ErrorMessage exception(Exception exception) {
	// ErrorMessage apiErrorMessage = new ErrorMessage(exception);
	// return apiErrorMessage;
	// }

}
