package controllers;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import entities.core.Voucher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class VoucherControllerIT {
    
    @Autowired
    VoucherController voucherController;
    
    @Test
    public void testCreateVoucherSuccess() {
        BigDecimal value = new BigDecimal(80);
        Calendar date = Calendar.getInstance();
        Voucher newVoucher = voucherController.createVoucher(value, date);

        assertEquals(newVoucher.getValue(), value);
        assertEquals(newVoucher.getCreated(), date);
       
    }    


}
