package daos.core;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entities.core.TextilePrinting;

public interface TextilePrintingDao extends JpaRepository<TextilePrinting, Long> {

    @Query("SELECT t FROM TextilePrinting t WHERE (:reference IS NULL OR :reference = '' OR t.reference = :reference) "
            + "AND (:description IS NULL OR t.description LIKE CONCAT('%',:description,'%')) "
            + "AND (:minRetailPrice IS NULL OR t.retailPrice >= :minRetailPrice) "
            + "AND (:maxRetailPrice IS NULL OR t.retailPrice <= :maxRetailPrice) AND (:type IS NULL OR t.type = :type)")
    public Page<TextilePrinting> search(Pageable pageable, @Param("reference") String reference, @Param("description") String description,
            @Param("minRetailPrice") BigDecimal minRetailPrice, @Param("maxRetailPrice") BigDecimal maxRetailPrice,
            @Param("type") String type);
}
