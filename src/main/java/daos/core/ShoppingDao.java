package daos.core;


import org.springframework.data.jpa.repository.JpaRepository;

import entities.core.Shopping;

public interface ShoppingDao extends JpaRepository<Shopping, Long>{
    /*
     * 
     * select description,amount, productId,Ticket_id,created from tpv.shopping as s join tpv.ticket_shopping as ts 
on s.id=ts.shoppingList_id 
join tpv.ticket as t on ts.Ticket_id=t.id
where created BETWEEN '2017-03-06 00:00:00' and '2017-03-06 21:13:01'*/
}
