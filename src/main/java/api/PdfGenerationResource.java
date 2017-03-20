package api;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.InvoiceNotFoundException;
import api.exceptions.TicketNotFoundException;
import controllers.PdfGenerationController;

@RestController
@RequestMapping(Uris.VERSION + Uris.GENERATE_PDF)
public class PdfGenerationResource {

    private PdfGenerationController pdfGenerationController;

    @Autowired
    public void setPdfGenerationController(PdfGenerationController pdfGenerationController) {
        this.pdfGenerationController = pdfGenerationController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void generatePdf(@RequestParam(required = true) String fileName) throws FileNotFoundException {
        pdfGenerationController.generatePdf(fileName);
    }
    
    @RequestMapping(value = Uris.INVOICES, method = RequestMethod.POST)
    public void generateInvoicePdf(@RequestParam(required = true) int id) throws FileNotFoundException, InvoiceNotFoundException {
        if(pdfGenerationController.invoiceExists(id)){
            pdfGenerationController.generateInvoicePdf(id);
        }else{
            throw new InvoiceNotFoundException("Invoice: " + id);
        }
    }
    
    @RequestMapping(value = Uris.TICKETS, method = RequestMethod.POST)
    public void generateTicketPdf(@RequestParam(required = true) long id) throws FileNotFoundException, TicketNotFoundException {
        if(pdfGenerationController.ticketExists(id)){
            pdfGenerationController.generateTicketPdf(id);
        }else{
            throw new TicketNotFoundException("Ticket: " + id);
        }    
    }
    
    
}