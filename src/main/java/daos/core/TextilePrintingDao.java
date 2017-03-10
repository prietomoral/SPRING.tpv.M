package daos.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.TextilePrinting;

public interface TextilePrintingDao extends JpaRepository<TextilePrinting, Long> {

    // TODO cambiar la query para admitir parámetros de búsqueda
    @Query("SELECT t FROM TextilePrinting t")
    public Page<TextilePrinting> search(Pageable pageable);
}
