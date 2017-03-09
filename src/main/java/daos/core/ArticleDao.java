package daos.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.Article;

public interface ArticleDao extends JpaRepository<Article, Long> {
    public List<Article> findAll();

    public Article findById(long id);

    // TODO cambiar la query para admitir parámetros de búsqueda
    @Query("SELECT a FROM Article a")
    public Page<Article> search(Pageable pageable);
}
