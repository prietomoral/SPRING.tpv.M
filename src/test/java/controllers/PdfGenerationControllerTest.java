package controllers;

import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class PdfGenerationControllerTest {

    @Autowired
    PdfGenerationController pdfGenerationController;

    @Test
    public void testGeneratePdf() {
        try {
            pdfGenerationController.generatePdf("testpdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGenerateInvoicePdf() {
        try {
            pdfGenerationController.generateInvoicePdf(123456);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    @Test
    public void testGenerateTicketPdf() {
        try {
            pdfGenerationController.generateTicketPdf(3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }

}
