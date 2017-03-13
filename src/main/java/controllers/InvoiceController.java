package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.InvoiceDao;
import daos.core.TicketDao;
import entities.core.Invoice;
import entities.core.Ticket;
import wrappers.IdTicketWrapper;

@Controller
public class InvoiceController {
    
    private InvoiceDao invoiceDao;
    
    private TicketDao ticketDao;
    
    @Autowired
    public void setInvoiceDao (InvoiceDao invoiceDao){
        this.invoiceDao = invoiceDao;
    }
    
    @Autowired
    public void setTicketDao (TicketDao ticketDao){
        this.ticketDao = ticketDao;
    }
	
	public boolean validateIdTicket(IdTicketWrapper ticketWrapper){
		if (ticketDao.findById(ticketWrapper.getId()) == null){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean ticketHasUser(IdTicketWrapper ticketWrapper){
	    if (validateIdTicket(ticketWrapper)){
	        Ticket ticket = ticketDao.findById(ticketWrapper.getId());
	        if (ticket.getUser() == null){
	            return false;
	        }else{
	            return true;
	        }
	    }
	    return false;
	}
	
	public void createInvoice (IdTicketWrapper ticketWrapper){
	    Ticket ticket = ticketDao.findById(ticketWrapper.getId());
	    Invoice invoice = new Invoice (123, ticket);
	    this.invoiceDao.save(invoice);
	}

}
