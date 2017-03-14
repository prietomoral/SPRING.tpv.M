package controllers;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import api.exceptions.NotFoundVouchers;
import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import daos.core.VoucherDao;
import entities.core.Voucher;
import wrappers.VoucherWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class VoucherControllerIT {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Autowired
    VoucherController voucherController;
    
    @Autowired
    VoucherDao voucherDao;
    
    
    @Test
    public void testCreateVoucherSuccess() {
        BigDecimal value = new BigDecimal(80);
        Calendar date = Calendar.getInstance();
        Voucher newVoucher = voucherController.createVoucher(value, date);

        assertEquals(newVoucher.getValue(), value);
        assertEquals(newVoucher.getCreated(), date);
    }    
    
    @Test
    public void testGetVouchersSuccess() {
        List<Voucher> bdVouchers = voucherDao.findAll();
        List<VoucherWrapper> result;
        try {
            result = voucherController.getVouchers();
            assertEquals(bdVouchers.size(), result.size());
        } catch (NotFoundVouchers e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetVouchersNotFound() {
        voucherDao.deleteAll();
        try {
            voucherController.getVouchers();
        } catch (NotFoundVouchers e) {
            assertEquals("No existen Vouchers en el sistema. ", e.getMessage());
            e.printStackTrace();
        }
        
    }
}
