package controllers;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.itextpdf.kernel.geom.PageSize;

import services.PdfGenerationService;

@Controller
public class PdfGenerationController {

    private PdfGenerationService pdfGenerationService;
    
    @Autowired
    public void setPdfGenerationService(PdfGenerationService pdfGenerationService){
        this.pdfGenerationService = pdfGenerationService;
    }
 
    public void generatePdf(String fileName) throws FileNotFoundException{
        pdfGenerationService.makePdf(fileName, PageSize.A4);       
    }
}
