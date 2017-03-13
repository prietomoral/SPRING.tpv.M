package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.NotFoundTicketIdException;
import api.exceptions.TicketHasNoUserException;
import controllers.InvoiceController;
import wrappers.IdTicketWrapper;


@RestController
@RequestMapping(Uris.VERSION)
public class InvoiceResource {
	
	private InvoiceController invoiceController;
	
	@Autowired
	public void setInvoiceController(InvoiceController invoiceController){
	    this.invoiceController = invoiceController;
	}
	
	@RequestMapping(value = Uris.INVOICES, method = RequestMethod.POST)
    public void createInvoice(@RequestBody IdTicketWrapper ticketWrapper) throws NotFoundTicketIdException, TicketHasNoUserException{
        if (!this.invoiceController.validateIdTicket(ticketWrapper)){
        	throw new NotFoundTicketIdException();
        }else if (!this.invoiceController.ticketHasUser(ticketWrapper)){
            throw new TicketHasNoUserException();
        }else{
            this.invoiceController.createInvoice(ticketWrapper);
        }
    }

}
