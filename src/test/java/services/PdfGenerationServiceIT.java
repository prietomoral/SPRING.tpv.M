package services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itextpdf.kernel.geom.PageSize;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import daos.core.InvoiceDao;
import daos.core.TicketDao;
import entities.core.Invoice;
import entities.core.Ticket;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class PdfGenerationServiceIT {
    private final static String USER_HOME = System.getProperty("user.home");

    private final static String PDF_FILES_ROOT = "/tpv/pdfs/";

    private final static String PDF_FILE_EXT = ".pdf";

    @Autowired
    private PdfGenerationService pdfGenerationService;

    @Autowired
    private InvoiceDao invoiceDao;
    
    @Autowired
    private TicketDao ticketDao;
    
    @Test
    public void testIfPdfDocumentHasBeenGenerated() {
        String fileName = "pdftest";
        String path = USER_HOME + PDF_FILES_ROOT + fileName + PDF_FILE_EXT;
        try {
            pdfGenerationService.makePdf(fileName, PageSize.A4);
            assertTrue(new File(path).exists());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testMakeInvoicePdf() {
        String fileName = "INVOICE_20170001";
        String path = USER_HOME + PDF_FILES_ROOT + "/invoices/" + fileName + PDF_FILE_EXT;
        try {
            Invoice invoice = invoiceDao.findOne(20170001);
            if (invoice != null) {
                pdfGenerationService.makeInvoicePdf(invoice);
                assertTrue(new File(path).exists());
            } else {
                assertFalse(new File(path).exists());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    @Test
    public void testMakeTicketPdf(){
        String fileName = "TICKET_3";
        String path = USER_HOME + PDF_FILES_ROOT + "/tickets/" + fileName + PDF_FILE_EXT;
        try {
            Ticket ticket = ticketDao.findOne((long) 3);
            if (ticket != null) {
                pdfGenerationService.makeTicketPdf(ticket);
                assertTrue(new File(path).exists());
            } else {
                assertFalse(new File(path).exists());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }

    @After
    public void reset() {
        String path = USER_HOME + PDF_FILES_ROOT + "pdftest" + PDF_FILE_EXT;
        new File(path).delete();
        path = USER_HOME + PDF_FILES_ROOT + "/invoices/" + "INVOICE_20170001" + PDF_FILE_EXT;
        new File(path).delete();
        path = USER_HOME + PDF_FILES_ROOT + "/tickets/" + "TICKET_3" + PDF_FILE_EXT;
        new File(path).delete();
    }

}
