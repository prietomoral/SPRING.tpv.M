package controllers;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.VoucherDao;
import entities.core.Voucher;

@Controller
public class VoucherController {

    
    private VoucherDao voucherDao;
    
    @Autowired
    public void setVoucherDao(VoucherDao voucherDao) {
        this.voucherDao = voucherDao;
    }
    
    public Voucher createVoucher(BigDecimal value, Calendar date){
        Voucher voucher = new Voucher(value, date);
        return voucherDao.save(voucher);
    }
    
}
