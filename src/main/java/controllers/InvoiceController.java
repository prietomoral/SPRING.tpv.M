package controllers;

import org.springframework.stereotype.Controller;

import wrappers.InvoiceWrapper;

@Controller
public class InvoiceController {
	
	public boolean checkExistingIdTicket(InvoiceWrapper invoiceWrapper){
		if (invoiceWrapper.getId_ticket() == 1){
			return true;
		}else{
			return false;
		}
	}

}
