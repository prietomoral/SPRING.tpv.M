package api;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.VoucherController;
import entities.core.Voucher;

@RestController
@RequestMapping(Uris.VERSION)
public class VoucherResource {
    
    
    private VoucherController voucherController;
    
    @Autowired
    public void setVoucherController(VoucherController voucherController){
        this.voucherController = voucherController;
    }

    
    @RequestMapping(value = Uris.VOUCHERS, method = RequestMethod.POST)
    public Voucher userRegistration(@RequestBody BigDecimal value) {
       return voucherController.createVoucher(value, Calendar.getInstance());
    }
    
}
