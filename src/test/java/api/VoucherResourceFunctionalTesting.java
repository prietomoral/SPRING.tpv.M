package api;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;

import entities.core.Voucher;
import wrappers.VoucherWrapper;

public class VoucherResourceFunctionalTesting {


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void populate() {
        new RestService().populate();
    }
    

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
    

    @Test
    public void testGetVouchers(){    
        List<VoucherWrapper> result = Arrays.asList(new RestBuilder<VoucherWrapper[]>(RestService.URL).path(Uris.VOUCHERS).get().clazz(VoucherWrapper[].class).build());
        assertEquals(6, result.size());
    }
    
    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }
    
}


