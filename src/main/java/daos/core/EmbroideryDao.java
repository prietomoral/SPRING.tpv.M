package daos.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.Embroidery;

public interface EmbroideryDao extends JpaRepository<Embroidery, Long> {

    // TODO cambiar la query para admitir parámetros de búsqueda
    @Query("SELECT e FROM Embroidery e")
    public Page<Embroidery> search(Pageable pageable);
}
