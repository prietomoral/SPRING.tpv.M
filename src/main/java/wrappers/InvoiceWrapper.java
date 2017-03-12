package wrappers;

public class InvoiceWrapper {

	private int id_ticket;
	
	public InvoiceWrapper(){
	}
	
	public InvoiceWrapper(int id_ticket){
		this.id_ticket = id_ticket;
	}
	
	public int getId_ticket(){
		return id_ticket;
	}
}
