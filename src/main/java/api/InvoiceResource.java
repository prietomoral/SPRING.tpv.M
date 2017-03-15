package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.NotFoundTicketIdException;
import api.exceptions.TicketHasInvoiceException;
import api.exceptions.TicketHasNoUserException;
import api.exceptions.TicketIsNotClosedException;
import controllers.InvoiceController;
import wrappers.IdTicketWrapper;
import entities.core.Invoice;


@RestController
@RequestMapping(Uris.VERSION)
public class InvoiceResource {
	
	private InvoiceController invoiceController;
	
	@Autowired
	public void setInvoiceController(InvoiceController invoiceController){
	    this.invoiceController = invoiceController;
	}
	
	@RequestMapping(value = Uris.INVOICES, method = RequestMethod.POST)
    public void createInvoice(@RequestBody IdTicketWrapper ticketWrapper) throws NotFoundTicketIdException, TicketIsNotClosedException, TicketHasNoUserException, TicketHasInvoiceException{
	    if (!this.invoiceController.validateIdTicket(ticketWrapper)){
            throw new NotFoundTicketIdException();
	    }else if (!this.invoiceController.ticketHasInvoice(ticketWrapper)){
	        throw new TicketHasInvoiceException();
	    }else if (!this.invoiceController.isTicketClosed(ticketWrapper)){
	        throw new TicketIsNotClosedException();
        }else if (!this.invoiceController.ticketHasUser(ticketWrapper)){
            throw new TicketHasNoUserException();
        }else{
            this.invoiceController.createInvoice(ticketWrapper);
        }
    }
	
    @RequestMapping(value = Uris.INVOICES, method = RequestMethod.GET)	
    public List<Invoice> listInvoices(){
	    return invoiceController.getAll();
	}

}
