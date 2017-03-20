package controllers;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.itextpdf.kernel.geom.PageSize;

import daos.core.InvoiceDao;
import entities.core.Invoice;
import services.PdfGenerationService;

@Controller
public class PdfGenerationController {

    private PdfGenerationService pdfGenerationService;
    private InvoiceDao invoiceDao;
    
    @Autowired
    public void setPdfGenerationService(PdfGenerationService pdfGenerationService){
        this.pdfGenerationService = pdfGenerationService;
    }
    
    @Autowired
    public void setInvoiceDao(InvoiceDao invoiceDao){
        this.invoiceDao = invoiceDao;
    }
 
    public void generatePdf(String fileName) throws FileNotFoundException{
        pdfGenerationService.makePdf(fileName, PageSize.A4);       
    }
    
    public void generateInvoicePdf(int invoiceId) throws FileNotFoundException{
        Invoice invoice = invoiceDao.findOne(invoiceId);
        if(invoice != null){
            pdfGenerationService.makeInvoicePdf(invoice);
        }        
    }
}
