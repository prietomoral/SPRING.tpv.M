package api;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;

import entities.core.Voucher;

public class VoucherResourceFunctionalTesting {


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCreateVoucherSuccess(){
        
        BigDecimal value = new BigDecimal(20);
        Voucher result = new RestBuilder<Voucher>(RestService.URL).path(Uris.VOUCHERS).body(value).post().clazz(Voucher.class).build();
        assertEquals(result.getValue(), value);
    }
    
    
    @Test
    public void testCreateVoucherUnsopportedMediaType(){
        thrown.expect(new HttpMatcher(HttpStatus.UNSUPPORTED_MEDIA_TYPE));
        String value = "test";
        new RestBuilder<Voucher>(RestService.URL).path(Uris.VOUCHERS).body(value).post().clazz(Voucher.class).build();
    }
}


