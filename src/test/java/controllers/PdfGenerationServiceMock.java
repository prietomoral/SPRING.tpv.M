package controllers;

import com.itextpdf.kernel.geom.PageSize;

import entities.core.Invoice;
import services.PdfGenerationService;

public class PdfGenerationServiceMock extends PdfGenerationService{

    private boolean executed = false;
    private boolean invoiceExecuted = false;

    @Override
    public void makePdf(String fileName, PageSize pageSize) {
        executed = true;
    }
    
    @Override
    public void makeInvoicePdf(Invoice invoice){
        invoiceExecuted = true;
    }

    public boolean isExecuted() {
        return executed;
    }
    
    public boolean isMakeInvoicePdfExecuted(){
        return invoiceExecuted;
    }
}
