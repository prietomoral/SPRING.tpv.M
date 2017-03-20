package api;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    
    @RequestMapping(value = Uris.INVOICES, method = RequestMethod.GET)
    public void generateInvoicePdf(@RequestParam(required = true) int id) throws FileNotFoundException {
        pdfGenerationController.generateInvoicePdf(id);
    }
    
    @RequestMapping(value = Uris.TICKETS, method = RequestMethod.GET)
    public void generateTicketPdf(@RequestParam(required = true) long id) throws FileNotFoundException {
        pdfGenerationController.generateTicketPdf(id);
    }
    
    
}