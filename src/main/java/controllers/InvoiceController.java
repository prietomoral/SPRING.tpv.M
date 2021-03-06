package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.InvoiceDao;
import daos.core.TicketDao;
import entities.core.Invoice;
import entities.core.Ticket;
import entities.core.TicketState;
import entities.users.User;
import services.DataService;
import wrappers.IdTicketWrapper;

@Controller
public class InvoiceController {
    
    private InvoiceDao invoiceDao;
    
    private TicketDao ticketDao;
    
    private DataService dataService;
    
    @Autowired
    public void setInvoiceDao (InvoiceDao invoiceDao){
        this.invoiceDao = invoiceDao;
    }
    
    @Autowired
    public void setTicketDao (TicketDao ticketDao){
        this.ticketDao = ticketDao;
    }
    
    @Autowired
    public void setDataService (DataService dataService){
        this.dataService = dataService;
    }
	
	public boolean validateIdTicket(IdTicketWrapper ticketWrapper){
		if (ticketDao.findById(ticketWrapper.getId()) == null){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean ticketHasValidUser(IdTicketWrapper ticketWrapper){
	    if (validateIdTicket(ticketWrapper)){
	        User user = ticketDao.findById(ticketWrapper.getId()).getUser();
	        if (user == null){
	            return false;
	        }else{
	            if(user.getAddress() == null || user.getDni() == null || user.getEmail() == null){
	                return false;
	            }else{
	                return true;
	            }
	        }
	    }
	    return false;
	}

	public boolean ticketHasInvoice(IdTicketWrapper ticketWrapper){
        List <Invoice> invoices = invoiceDao.findAll();
        for (Invoice invoice : invoices){
            if(invoice.getTicket().getId() == ticketWrapper.getId()){
                return false;
            }
        }
        return true;
    }
	
	public boolean isTicketClosed(IdTicketWrapper ticketWrapper){
	    Ticket ticket = ticketDao.findById(ticketWrapper.getId());
	    if(ticket.getTicketState() == TicketState.CLOSED){
	        return true;
	    }else{
	        return false;
	    }
	}
	
	public int getNextInvoiceId (){
        int count = invoiceDao.findAll().size();
        if (count == 0){
            return 20170001;
        }else{
            return invoiceDao.findFirstByOrderByIdDesc().getId() + 1;
        }
    }
	
	public void createInvoice (IdTicketWrapper ticketWrapper){
	    Ticket ticket = ticketDao.findById(ticketWrapper.getId());
	    Invoice invoice = new Invoice (getNextInvoiceId(), ticket);
	    this.invoiceDao.save(invoice);
	}

    public List<Invoice> getAll() {
        List <Invoice> invoices = invoiceDao.findAll();
        return invoices;
    }
    
    public Invoice getInvoiceById(int id){
        return invoiceDao.findOne(id);
    }
    
    public void populate(){
        dataService.populate();
    }

	public Invoice getInvoiceByTicketID(IdTicketWrapper ticketWrapper) {
		Invoice invoice = invoiceDao.findInvoiceByTicket_id(ticketWrapper.getId());
		return invoice;
	}
}
