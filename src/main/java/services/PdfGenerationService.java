package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;

import entities.core.Invoice;
import entities.core.Shopping;
import entities.core.Ticket;

@Service
public class PdfGenerationService {
    private final static String USER_HOME = System.getProperty("user.home");

    private final static String PDF_FILES_ROOT = "/tpv/pdfs/";

    private final static String PDF_FILE_EXT = ".pdf";
    
    private void makeDirectories(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
    }

    private Document getPdfDocument(String path, PageSize pageSize) throws FileNotFoundException {
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        return new Document(pdfDocument, pageSize);
    }

    public void makePdf(String fileName, PageSize pageSize) throws FileNotFoundException {
        String path = USER_HOME + PDF_FILES_ROOT + fileName + PDF_FILE_EXT;
        makeDirectories(path);
        Document pdfDocument = getPdfDocument(path, pageSize);
        pdfDocument.add(new Paragraph("Auto-generated PDF document."));
        pdfDocument.close();
    }
    
    public void makeInvoicePdf(Invoice invoice) throws FileNotFoundException{
        String ownPath = "/invoices/";
        String fileName = "INVOICE_" + invoice.getId();
        String path = USER_HOME + PDF_FILES_ROOT + ownPath + fileName + PDF_FILE_EXT;
        makeDirectories(path);
        Document pdfDocument = getPdfDocument(path, PageSize.A4);
        pdfDocument.add(new Paragraph(fileName));
        Ticket ticket = invoice.getTicket();
        pdfDocument.add(new Paragraph("================ Ticket ================"));
        pdfDocument.add(new Paragraph("Reference: " + ticket.getReference()));
        pdfDocument.add(new Paragraph("Ticket state: " + ticket.getTicketState().toString()));
        pdfDocument.add(new Paragraph("Created on: " + ticket.getCreated().getDisplayName(Calendar.SHORT_FORMAT, Calendar.LONG, Locale.getDefault())));
        pdfDocument.add(new Paragraph("Shopping list:"));
        List shoppingList = new List();
        for(Shopping shopping : ticket.getShoppingList()){
            String line = shopping.getDescription() + " "
                    + shopping.getAmount() + " "
                    + shopping.getDiscount() + " "
                    + shopping.getRetailPrice();
            shoppingList.add(new ListItem(line));
        }
        pdfDocument.add(shoppingList);
        pdfDocument.close();
    }
    
    public void makeTicketPdf(Ticket ticket) throws FileNotFoundException{
        String ownPath = "/tickets/";
        String fileName = "TICKET_" + ticket.getId();
        String path = USER_HOME + PDF_FILES_ROOT + ownPath + fileName + PDF_FILE_EXT;
        makeDirectories(path);
        Document pdfDocument = getPdfDocument(path, PageSize.A7);
        pdfDocument.add(new Paragraph("Reference: " + ticket.getReference()));
        pdfDocument.add(new Paragraph("Ticket state: " + ticket.getTicketState().toString()));
        pdfDocument.add(new Paragraph("Created on: " + ticket.getCreated().getDisplayName(Calendar.SHORT_FORMAT, Calendar.LONG, Locale.getDefault())));
        pdfDocument.add(new Paragraph("Shopping list:"));
        List shoppingList = new List();
        for(Shopping shopping : ticket.getShoppingList()){
            String line = shopping.getDescription() + " "
                    + shopping.getAmount() + " "
                    + shopping.getDiscount() + " "
                    + shopping.getRetailPrice();
            shoppingList.add(new ListItem(line));
        }
        pdfDocument.add(shoppingList);
        pdfDocument.close();
    }
}
