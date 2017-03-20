package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.itextpdf.io.font.otf.Glyph;
import com.itextpdf.io.font.otf.GlyphLine;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfOutputStream;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;

import entities.core.Invoice;
import entities.core.Shopping;
import entities.core.Ticket;

@Service
public class PdfGenerationService {
    private final static String USER_HOME = System.getProperty("user.home");

    private final static String PDF_FILES_ROOT = "/tpv/pdfs/";

    private final static String PDF_FILE_EXT = ".pdf";
    
    private final static float[] SHOPPING_LIST_COLUMNS_WIDTHS = new float[]{
            10.0f, 30.0f, 30.0f, 10.0f, 40.0f, 30.0f
    };
    
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
        pdfDocument.add(new Paragraph("====================== Ticket ======================"));
        pdfDocument.add(new Paragraph("Reference: " + ticket.getReference()));
        pdfDocument.add(new Paragraph("Ticket state: " + ticket.getTicketState().toString()));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        pdfDocument.add(new Paragraph("Created on: " + formatter.format(ticket.getCreated().getTime())));
        pdfDocument.add(new Paragraph("Shopping list:"));
        Table shoppingListTable = new Table(SHOPPING_LIST_COLUMNS_WIDTHS);
        shoppingListTable.addCell("Id");
        shoppingListTable.addCell("Amount");
        shoppingListTable.addCell("Discount");
        shoppingListTable.addCell("ProductId");
        shoppingListTable.addCell("Description");
        shoppingListTable.addCell("Retail price");
        for(Shopping shopping : ticket.getShoppingList()){
            shoppingListTable.addCell(String.valueOf(shopping.getId()));
            shoppingListTable.addCell(String.valueOf(shopping.getAmount()));
            shoppingListTable.addCell(String.valueOf(shopping.getDiscount() + "%"));
            shoppingListTable.addCell(String.valueOf(shopping.getProductId()));
            shoppingListTable.addCell(String.valueOf(shopping.getDescription()));
            shoppingListTable.addCell(String.valueOf(shopping.getRetailPrice() + "€"));
        }
        pdfDocument.add(shoppingListTable);
        pdfDocument.close();
    }
    
    public void makeTicketPdf(Ticket ticket) throws FileNotFoundException{
        String ownPath = "/tickets/";
        String fileName = "TICKET_" + ticket.getId();
        String path = USER_HOME + PDF_FILES_ROOT + ownPath + fileName + PDF_FILE_EXT;
        makeDirectories(path);
        Document pdfDocument = getPdfDocument(path, PageSize.A7);
        pdfDocument.setFontSize(3.0f);
        pdfDocument.add(new Paragraph("Reference: " + ticket.getReference()));
        pdfDocument.add(new Paragraph("Ticket state: " + ticket.getTicketState().toString()));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        pdfDocument.add(new Paragraph("Created on: " + formatter.format(ticket.getCreated().getTime())));
        pdfDocument.add(new Paragraph("Shopping list:"));
        Table shoppingListTable = new Table(SHOPPING_LIST_COLUMNS_WIDTHS);
        shoppingListTable.addCell("Id");
        shoppingListTable.addCell("Amount");
        shoppingListTable.addCell("Discount");
        shoppingListTable.addCell("ProductId");
        shoppingListTable.addCell("Description");
        shoppingListTable.addCell("Retail price");
        for(Shopping shopping : ticket.getShoppingList()){
            shoppingListTable.addCell(String.valueOf(shopping.getId()));
            shoppingListTable.addCell(String.valueOf(shopping.getAmount()));
            shoppingListTable.addCell(String.valueOf(shopping.getDiscount() + "%"));
            shoppingListTable.addCell(String.valueOf(shopping.getProductId()));
            shoppingListTable.addCell(String.valueOf(shopping.getDescription()));
            shoppingListTable.addCell(String.valueOf(shopping.getRetailPrice() + "€"));
        }
        pdfDocument.add(shoppingListTable);
        pdfDocument.close();
    }
}
