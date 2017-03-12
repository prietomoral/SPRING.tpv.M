package daos.core;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entities.core.Embroidery;

public interface EmbroideryDao extends JpaRepository<Embroidery, Long> {

    @Query("SELECT e FROM Embroidery e WHERE (:reference IS NULL OR e.reference = :reference) "
            + "AND (:description IS NULL OR e.description LIKE CONCAT('%',:description,'%')) "
            + "AND (:minRetailPrice IS NULL OR e.retailPrice >= :minRetailPrice) "
            + "AND (:maxRetailPrice IS NULL OR e.retailPrice <= :maxRetailPrice) "
            + "AND (:minStitches IS NULL OR e.stitches >= :minStitches) " + "AND (:maxStitches IS NULL OR e.stitches <= :maxStitches) "
            + "AND (:minColors IS NULL OR e.colors >= :minColors) " + "AND (:maxColors IS NULL OR e.colors <= :maxColors) "
            + "AND (:minSquareMillimeters IS NULL OR e.squareMillimeters >= :minSquareMillimeters) "
            + "AND (:maxSquareMillimeters IS NULL OR e.squareMillimeters <= :maxSquareMillimeters)")
    public Page<Embroidery> search(Pageable pageable, @Param("reference") String reference, @Param("description") String description,
            @Param("minRetailPrice") BigDecimal minRetailPrice, @Param("maxRetailPrice") BigDecimal maxRetailPrice,
            @Param("minStitches") Integer minStitches, @Param("maxStitches") Integer maxStitches, @Param("minColors") Integer minColors,
            @Param("maxColors") Integer maxColors, @Param("minSquareMillimeters") Integer minSquareMillimeters,
            @Param("maxSquareMillimeters") Integer maxSquareMillimeters);
}
