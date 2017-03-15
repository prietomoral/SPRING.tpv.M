package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.NotFoundTicketIdException;
import api.exceptions.TicketHasInvoiceException;
import api.exceptions.TicketHasInvalidUserException;
import api.exceptions.TicketIsNotClosedException;
import controllers.InvoiceController;
import wrappers.IdTicketWrapper;
import entities.core.Invoice;


@RestController
@RequestMapping(Uris.VERSION + Uris.INVOICES)
public class InvoiceResource {
	
	private InvoiceController invoiceController;
	
	@Autowired
	public void setInvoiceController(InvoiceController invoiceController){
	    this.invoiceController = invoiceController;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public void createInvoice(@RequestBody IdTicketWrapper ticketWrapper) throws NotFoundTicketIdException, TicketIsNotClosedException, TicketHasInvalidUserException, TicketHasInvoiceException{
	    if (!this.invoiceController.validateIdTicket(ticketWrapper)){
            throw new NotFoundTicketIdException();
	    }else if (!this.invoiceController.ticketHasInvoice(ticketWrapper)){
	        throw new TicketHasInvoiceException();
	    }else if (!this.invoiceController.isTicketClosed(ticketWrapper)){
	        throw new TicketIsNotClosedException();
        }else if (!this.invoiceController.ticketHasValidUser(ticketWrapper)){
            throw new TicketHasInvalidUserException();
        }else{
            this.invoiceController.createInvoice(ticketWrapper);
        }
    }
	
    @RequestMapping(method = RequestMethod.GET)	
    public List<Invoice> listInvoices(){
	    return invoiceController.getAll();
	}
    
    @RequestMapping(value = Uris.ID, method = RequestMethod.GET)  
    public Invoice getInvoicesById(@PathVariable(value = "id") int id){
        return invoiceController.getInvoiceById(id);
    }

}
