package daos.core;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.Voucher;

public interface VoucherDao extends JpaRepository<Voucher, Integer> {

    @Query("select SUM(v.value) from Voucher v where v.dateOfUse IS NULL")
    BigDecimal sumValueInVouchersActive();
    
}
