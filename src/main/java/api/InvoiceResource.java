package api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.NotFoundTicketIdException;
import controllers.InvoiceController;
import wrappers.InvoiceWrapper;


@RestController
@RequestMapping(Uris.VERSION)
public class InvoiceResource {
	
	private InvoiceController invoiceController = new InvoiceController();
	
	@RequestMapping(value = Uris.INVOICES, method = RequestMethod.POST)
    public void createInvoice(@RequestBody InvoiceWrapper invoiceWrapper) throws NotFoundTicketIdException{
        if (!this.invoiceController.checkExistingIdTicket(invoiceWrapper)){
        	throw new NotFoundTicketIdException();
        }
    }

}
