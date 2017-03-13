package wrappers;

import entities.core.Ticket;

public class InvoiceWrapper {
    
    private int id;
    
    private Ticket ticket;
	
	public InvoiceWrapper(){
	    
	}
	
	public InvoiceWrapper(int id, Ticket ticket){
	    this.id = id;
	    this.ticket = ticket;
	}
	
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket(){
		return ticket;
	}

    @Override
    public String toString() {
        return "InvoiceWrapper [id=" + id + ", ticket=" + ticket + "]";
    }

}
