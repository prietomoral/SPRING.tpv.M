package api;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;

public class PdfGenerationResourceFunctionalTesting {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Before
    public void setUpOnce(){
        new RestService().populate();
        new RestService().registerAndLoginManager();    
    }
    
    @Test
    public void testNonExistentInvoice() {

    }

    @Test
    public void testGenerateTicketPdfUnauthorired() {
        thrown.expect(new HttpMatcher(HttpStatus.UNAUTHORIZED));
        new RestBuilder<Object>(RestService.URL)
        .path(Uris.GENERATE_PDF).path(Uris.TICKETS)
        .param("id", "23456478754544")
        .get()
        .build();
    }
    
    @Test
    public void testGenerateInvoicePdfUnauthorized(){
        thrown.expect(new HttpMatcher(HttpStatus.UNAUTHORIZED));
        new RestBuilder<Object>(RestService.URL)
        .path(Uris.GENERATE_PDF).path(Uris.INVOICES)
        .param("id", "23456478754544")
        .get()
        .build();
    }

    @After
    public void tearDownOnce() {
        new RestService().deleteAll();
    }

}
