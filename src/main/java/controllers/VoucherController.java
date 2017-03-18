package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import api.exceptions.NotFoundVoucherException;
import api.exceptions.NotFoundVouchersException;
import api.exceptions.VoucherAlreadyUsedException;
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
    
    @Transactional
    public Voucher createVoucher(BigDecimal value, Calendar date){
        Voucher voucher = new Voucher(value, date);
        return voucherDao.save(voucher);
    }
    
    public List<VoucherWrapper> getVouchers() throws NotFoundVouchersException{
        List<Voucher> vouchers = voucherDao.findAll();
        if(vouchers == null || vouchers.size() == 0){
            throw new NotFoundVouchersException();
        }
        
        List<VoucherWrapper> result = new ArrayList<>();
        for (Voucher voucher : vouchers) {
            result.add(new VoucherWrapper(voucher.getId(), voucher.getReference(), voucher.getValue(), voucher.getCreated(), voucher.getDateOfUse()));
        }
        return result;
    }
    
    public Voucher useVoucher(String identification) throws NotFoundVoucherException, VoucherAlreadyUsedException{
    	Voucher result;
    	
    	if (isNumeric(identification)) {
    		Integer id = Integer.valueOf(identification);
        	result = voucherDao.findOne(id);
    	} else {
    		result = voucherDao.findByReference(identification);
    	}
    	
    	if(result == null){
    		throw new NotFoundVoucherException();
    	}
    	
    	if(result.getDateOfUse() != null){
    		throw new VoucherAlreadyUsedException();
    	}
    	result.setDateOfUse(Calendar.getInstance());
    	voucherDao.save(result);
    	return result;
    }
    
    public static boolean isNumeric(String str) {  
      try {  
        Integer.parseInt(str);  
      } catch(NumberFormatException nfe) {  
        return false;  
      }  
      return true;  
    }

    
}
