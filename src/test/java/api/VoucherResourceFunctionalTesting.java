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
import wrappers.IdentificationVoucherWrapper;
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
        assertEquals(7, result.size());
    }
    
    @Test
    public void testUseVoucher(){   
        BigDecimal value = new BigDecimal(20).setScale(2, BigDecimal.ROUND_HALF_UP);
        Voucher newVoucher = new RestBuilder<Voucher>(RestService.URL).path(Uris.VOUCHERS).body(value).post().clazz(Voucher.class).build();
        
        IdentificationVoucherWrapper body = new IdentificationVoucherWrapper(newVoucher.getId()+"");
        Voucher result = new RestBuilder<Voucher>(RestService.URL).path(Uris.VOUCHERS + "/use").body(body).put().clazz(Voucher.class).build();
     
        assertEquals(newVoucher.getValue(), result.getValue());
        assertEquals(newVoucher.getId(), result.getId());
    }

    @Test
    public void testUseVoucherNotFound(){  
        IdentificationVoucherWrapper body = new IdentificationVoucherWrapper("9");  
        thrown.expect(new HttpMatcher(HttpStatus.NOT_FOUND));
        new RestBuilder<Voucher>(RestService.URL).path(Uris.VOUCHERS + "/use").body(body).put().clazz(Voucher.class).build();
    }
    
    @Test
    public void testUseVoucherAlreadyUse(){  

        BigDecimal value = new BigDecimal(450).setScale(2, BigDecimal.ROUND_HALF_UP);
        Voucher newVoucher = new RestBuilder<Voucher>(RestService.URL).path(Uris.VOUCHERS).body(value).post().clazz(Voucher.class).build();

        IdentificationVoucherWrapper body = new IdentificationVoucherWrapper(newVoucher.getId()+ "");
        new RestBuilder<Voucher>(RestService.URL).path(Uris.VOUCHERS + "/use").body(body).put().clazz(Voucher.class).build();
     
        thrown.expect(new HttpMatcher(HttpStatus.CONFLICT));
        new RestBuilder<Voucher>(RestService.URL).path(Uris.VOUCHERS + "/use").body(body).put().clazz(Voucher.class).build();
    }
    
    @AfterClass
    public static void deleteAll() {
        new RestService().deleteAll();
    }
    
}


