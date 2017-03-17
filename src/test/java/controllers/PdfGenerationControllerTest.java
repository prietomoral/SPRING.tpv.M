package controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class PdfGenerationControllerTest {

    PdfGenerationController pdfGenerationController;
    PdfGenerationServiceMock pdfGenerationServiceMock;

    @Before
    public void setUp() {
        pdfGenerationController = new PdfGenerationController();
        pdfGenerationServiceMock = new PdfGenerationServiceMock();
        pdfGenerationController.setPdfGenerationService(pdfGenerationServiceMock);
    }
    
    @Test
    public void testGeneratePdf() {
        assertFalse(pdfGenerationServiceMock.isExecuted());
        try {
            pdfGenerationController.generatePdf("testpdf");
            assertTrue(pdfGenerationServiceMock.isExecuted());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }

}
