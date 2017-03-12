package daos.core;


import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.Shopping;

public interface ShoppingDao extends JpaRepository<Shopping, Long>{
    @Query("select s from Ticket t JOIN t.shoppingList s where s.productId = ?1 AND t.created BETWEEN ?2 AND ?3 ORDER BY t.created")
    public List<Shopping> findShoppingsProductByDate(long id, Calendar inicio,Calendar fin);
    
    @Query("Select SUM(s.amount)from Shopping s where s.productId=?1")
    public List<Integer> findTotalSoldsProducts(Long productId);
    
    @Query("Select s.productId,s.description,SUM(s.amount) from Shopping s GROUP BY s.productId,s.description ORDER BY SUM(s.amount) DESC")
    public List<Object[]> findTotalSoldsProducts();
    /*
    @Query("Select NEW  daos.core.SemiWrapperStatisticShopping(s.productId, s.description, SUM(s.amount)) from Shopping s GROUP BY s.productId ORDER BY SUM(s.amount) DESC")
    public List<SemiWrapperStatisticShopping> findTotalSoldsProductsFormat();
    */
}
