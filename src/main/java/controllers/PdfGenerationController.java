package controllers;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.itextpdf.kernel.geom.PageSize;

import daos.core.InvoiceDao;
import daos.core.TicketDao;
import entities.core.Invoice;
import entities.core.Ticket;
import services.PdfGenerationService;

@Controller
public class PdfGenerationController {

    private PdfGenerationService pdfGenerationService;
    private InvoiceDao invoiceDao;
    private TicketDao ticketDao;
    
    @Autowired
    public void setPdfGenerationService(PdfGenerationService pdfGenerationService){
        this.pdfGenerationService = pdfGenerationService;
    }
    
    @Autowired
    public void setInvoiceDao(InvoiceDao invoiceDao){
        this.invoiceDao = invoiceDao;
    }
    
    @Autowired
    public void setTicketDao(TicketDao ticketDao){
        this.ticketDao = ticketDao;
    }
 
    public void generatePdf(String fileName) throws FileNotFoundException{
        pdfGenerationService.makePdf(fileName, PageSize.A4);       
    }
    
    public void generateInvoicePdf(int invoiceId) throws FileNotFoundException{
        Invoice invoice = invoiceDao.findOne(invoiceId);
        pdfGenerationService.makeInvoicePdf(invoice);  
    }
    
    public void generateTicketPdf(long ticketId) throws FileNotFoundException{
        Ticket ticket = ticketDao.findOne(ticketId);
        pdfGenerationService.makeTicketPdf(ticket);
    }
    
    public boolean ticketExists(long ticketId){
        return ticketDao.findOne(ticketId) != null;
    }
    
    public boolean invoiceExists(int invoiceId){
        return invoiceDao.findOne(invoiceId) != null;
    }
}
