package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import daos.core.ArticleDao;
import entities.core.Article;
import wrappers.ArticleWrapper;

@Controller
public class ArticleController {

    private ArticleDao articleDao;

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public Page<ArticleWrapper> search(Pageable pageable, String reference, String description, BigDecimal minRetailPrice,
            BigDecimal maxRetailPrice, boolean onlyOnStock) {
        Page<Article> page = articleDao.search(pageable, reference, description, minRetailPrice, maxRetailPrice, onlyOnStock);
        List<ArticleWrapper> articleWrappers = new ArrayList<>();
        for (Article article : page.getContent()) {
            ArticleWrapper articleWrapper = new ArticleWrapper(article.getId(), article.getReference(), article.getDescription(),
                    article.getRetailPrice(), article.getStock());
            articleWrappers.add(articleWrapper);
        }
        return new PageImpl<ArticleWrapper>(articleWrappers, pageable, page.getTotalElements());
    }

    public List<ArticleWrapper> getAll() {
        List<ArticleWrapper> articleWrappers = new ArrayList<>();
        for (Article article : articleDao.findAll()) {
            ArticleWrapper articleWrapper = new ArticleWrapper();
            articleWrapper.setDescription(article.getDescription());
            articleWrapper.setId(article.getId());
            articleWrappers.add(articleWrapper);
        }
        return articleWrappers;
    }

}
