package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import api.exceptions.NotFoundVouchers;
import daos.core.VoucherDao;
import entities.core.Voucher;
import wrappers.VoucherWrapper;

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
    
    public List<VoucherWrapper> getVouchers() throws NotFoundVouchers{
        List<Voucher> vouchers = voucherDao.findAll();
        if(vouchers == null || vouchers.size() == 0){
            throw new NotFoundVouchers();
        }
        
        List<VoucherWrapper> result = new ArrayList<>();
        for (Voucher voucher : vouchers) {
            result.add(new VoucherWrapper(voucher.getId(), voucher.getReference(), voucher.getValue(), voucher.getCreated(), voucher.getDateOfUse()));
        }
        return result;
    }
    
}
