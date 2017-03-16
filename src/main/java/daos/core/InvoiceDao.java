package daos.core;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.core.Invoice;

public interface InvoiceDao extends JpaRepository<Invoice, Integer> {
    
    public Invoice findFirstByOrderByIdDesc();
    
    public Invoice findInvoiceByTicket_id(long id);

}
