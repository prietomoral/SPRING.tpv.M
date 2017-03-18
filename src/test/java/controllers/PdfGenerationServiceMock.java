package controllers;

import com.itextpdf.kernel.geom.PageSize;

import services.PdfGenerationService;

public class PdfGenerationServiceMock extends PdfGenerationService{

    private boolean executed = false;

    @Override
    public void makePdf(String fileName, PageSize pageSize) {
        executed = true;
    }

    public boolean isExecuted() {
        return executed;
    }
}
