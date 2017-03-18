package api;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.NotFoundVoucherException;
import api.exceptions.NotFoundVouchersException;
import api.exceptions.VoucherAlreadyUsedException;
import controllers.VoucherController;
import entities.core.Voucher;
import wrappers.IdentificationVoucherWrapper;
import wrappers.VoucherWrapper;

@RestController
@RequestMapping(Uris.VERSION)
public class VoucherResource {
    
    
    private VoucherController voucherController;
    
    @Autowired
    public void setVoucherController(VoucherController voucherController){
        this.voucherController = voucherController;
    }

    
    @RequestMapping(value = Uris.VOUCHERS, method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER') or hasRole('OPERATOR')")
    public Voucher createVoucher(@RequestBody BigDecimal value) {
       return voucherController.createVoucher(value, Calendar.getInstance());
    }
    
    @RequestMapping(value = Uris.VOUCHERS, method = RequestMethod.GET)
    public List<VoucherWrapper> getVouchers() throws NotFoundVouchersException {
       return voucherController.getVouchers();
    }
    
    @RequestMapping(value = Uris.VOUCHERS + "/use", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('MANAGER') or hasRole('OPERATOR')")
    public Voucher useVoucher(@RequestBody IdentificationVoucherWrapper identification) throws NotFoundVoucherException, VoucherAlreadyUsedException {
       return voucherController.useVoucher(identification.getIdentification());
    }
    
}
