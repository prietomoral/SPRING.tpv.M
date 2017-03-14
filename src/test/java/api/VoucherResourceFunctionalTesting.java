package api;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import daos.core.VoucherDao;
import entities.core.Voucher;
import wrappers.VoucherWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class VoucherResourceFunctionalTesting {


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    VoucherDao voucherDao;
    

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
        List<Voucher> bdVouchers = voucherDao.findAll();
        List<VoucherWrapper> result = Arrays.asList(new RestBuilder<VoucherWrapper[]>(RestService.URL).path(Uris.VOUCHERS).get().clazz(VoucherWrapper[].class).build());

        assertEquals(bdVouchers.size(), result.size());
    }
    
    
    @Test
    public void testGetVouchersNotFound(){
        voucherDao.deleteAll();
        thrown.expect(new HttpMatcher(HttpStatus.NOT_FOUND));
        new RestBuilder<Voucher>(RestService.URL).path(Uris.VOUCHERS).get().clazz(Voucher.class).build();
    }
}


