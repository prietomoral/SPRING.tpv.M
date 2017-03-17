package services;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class PdfGenerationServiceTest {
    private final static String USER_HOME = System.getProperty("user.home");

    private final static String PDF_FILES_ROOT = "/tpv/pdfs/";

    private final static String PDF_FILE_EXT = ".pdf";
    
    private final static String FILENAME = "pdftest";
    
    private final static String PATH = USER_HOME + PDF_FILES_ROOT + FILENAME + PDF_FILE_EXT;
    
    @Autowired
    private PdfGenerationService pdfGenerationService;
    
    @Test
    public void testIfPdfDocumentHasBeenGenerated() {
        try {
            pdfGenerationService.makePdf(FILENAME, PageSize.A4);
            assertTrue(new File(PATH).exists());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    @After
    public void reset(){
        new File(PATH).delete();      
    }

}
