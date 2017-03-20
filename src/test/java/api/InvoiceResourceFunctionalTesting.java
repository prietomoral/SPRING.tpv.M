package api;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;

import wrappers.IdTicketWrapper;

public class InvoiceResourceFunctionalTesting {
    
	private static String token;

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @BeforeClass
    public static void populate() {
        new RestService().populate();
        token = new RestService().loginAdmin();
    }
    
    @Test
    public void testIdTicketNotFound(){
        thrown.expect(new HttpMatcher(HttpStatus.NOT_FOUND));
        new RestBuilder<Object>(RestService.URL).path(Uris.INVOICES).body(new IdTicketWrapper(10)).basicAuth(token, "").post().build();
    }
    
    @Test
    public void testUserNotFoundInTicket(){
        thrown.expect(new HttpMatcher(HttpStatus.NOT_FOUND));
        new RestBuilder<Object>(RestService.URL).path(Uris.INVOICES).body(new IdTicketWrapper(1)).basicAuth(token, "").post().build();
    }
    
    @Test
    public void testTicketIsNotClosed(){
        thrown.expect(new HttpMatcher(HttpStatus.CONFLICT));
        new RestBuilder<Object>(RestService.URL).path(Uris.INVOICES).body(new IdTicketWrapper(2)).basicAuth(token, "").post().build();
    }
    
    @Test
    public void testTicketHasAlreadyInvoice(){
        thrown.expect(new HttpMatcher(HttpStatus.CONFLICT));
        new RestBuilder<Object>(RestService.URL).path(Uris.INVOICES).body(new IdTicketWrapper(4)).basicAuth(token, "").post().build();
    }
    
    @Test
    public void testCreateInvoice(){
        new RestBuilder<Object>(RestService.URL).path(Uris.INVOICES).body(new IdTicketWrapper(5)).basicAuth(token, "").post().build();
    }
    
    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }
}
