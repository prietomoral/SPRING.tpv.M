package api;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;

import wrappers.IdTicketWrapper;

public class InvoiceResourceFunctionalTesting {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testIdTicketNotFound(){
        thrown.expect(new HttpMatcher(HttpStatus.NOT_FOUND));
        new RestBuilder<Object>(RestService.URL).path(Uris.INVOICES).body(new IdTicketWrapper(10)).post().build();
    }
    
    @Test
    public void testUserNotFoundInTicket(){
        thrown.expect(new HttpMatcher(HttpStatus.NOT_FOUND));
        new RestBuilder<Object>(RestService.URL).path(Uris.INVOICES).body(new IdTicketWrapper(1)).post().build();
    }
    
    @Test
    public void testCreateInvoice(){
        new RestBuilder<Object>(RestService.URL).path(Uris.INVOICES).body(new IdTicketWrapper(4)).post().build();
    }
    
}