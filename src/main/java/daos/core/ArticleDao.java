package daos.core;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entities.core.Article;

public interface ArticleDao extends JpaRepository<Article, Long> {

    public Article findById(long id);

    @Query("SELECT a FROM Article a WHERE (:reference IS NULL OR :reference = '' OR a.reference = :reference) "
            + "AND (:description IS NULL OR a.description LIKE CONCAT('%',:description,'%')) "
            + "AND (:minRetailPrice IS NULL OR a.retailPrice >= :minRetailPrice) "
            + "AND (:maxRetailPrice IS NULL OR a.retailPrice <= :maxRetailPrice) "
            + "AND (:onlyOnStock IS NULL OR (:onlyOnStock = FALSE OR a.stock > 0))")
    public Page<Article> search(Pageable pageable, @Param("reference") String reference, @Param("description") String description,
            @Param("minRetailPrice") BigDecimal minRetailPrice, @Param("maxRetailPrice") BigDecimal maxRetailPrice,
            @Param("onlyOnStock") boolean onlyOnStock);
}
