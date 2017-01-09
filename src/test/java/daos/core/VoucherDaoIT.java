package daos.core;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.Voucher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class VoucherDaoIT {

    @Autowired
    private VoucherDao voucherDao;

    @Test
    public void testCreate() {
        Voucher voucher = new Voucher(new BigDecimal(1));
        voucherDao.save(voucher);
        assertEquals(1, voucherDao.count());
    }

    @After
    public void dellete() {
        voucherDao.deleteAll();
    }

}
