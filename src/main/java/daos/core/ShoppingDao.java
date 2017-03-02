package daos.core;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.Shopping;

public interface ShoppingDao extends JpaRepository<Shopping, Long>{

    @Query(value="select sum(amount) as amount,"
            + " productId, description "
            + "from shopping "
            + "group by productId "
            + "order by amount desc",
            nativeQuery=true)
    public List<String> statisticsSoldProducts();
    
}
