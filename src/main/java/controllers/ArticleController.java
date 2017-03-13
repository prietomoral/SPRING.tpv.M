package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import api.exceptions.AlreadyExistProductException;
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
            articleWrappers.add(new ArticleWrapper(article));
        }
        return new PageImpl<ArticleWrapper>(articleWrappers, pageable, page.getTotalElements());
    }

    public List<Article> all() {

        List<Article> articles = articleDao.findAll();
        return articles;
    }

    public void add(Article article) throws AlreadyExistProductException {
        Article articleDB = articleDao.findOne(article.getId());
        if (articleDB != null) {
            throw new AlreadyExistProductException();
        } else {
            this.articleDao.save(article);
        }
    }

    public void delete(int id) {
        Article article = articleDao.findOne(Long.valueOf(id));
        articleDao.delete(article);
    }

    public void edit(int id, Article article) {
        // TODO Auto-generated method stub

    }

}
