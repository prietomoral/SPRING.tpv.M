package daos.core;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.core.Article;

public interface ArticleDao extends JpaRepository<Article, Long> {

}
