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
import daos.core.ProviderDao;
import entities.core.Article;
import entities.core.Embroidery;
import entities.core.Provider;
import wrappers.ArticleWrapper;

@Controller
public class ArticleController {

    private ArticleDao articleDao;
    @Autowired
    private ProviderDao providerDao;

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
        
        Provider provider;
        for (int i = 0; i < 4; i++) {
            provider = new Provider("company" + i, "address" + i, 666100000 + i, 916661000 + i, "No", "No");
            providerDao.save(provider);
        }

        List<Article> articles = articleDao.findAll();
        return articles;
    }

    public void add(Article article) {//throws AlreadyExistProductException {
        
     
      //  Article articleDB = articleDao.findOne(article.getId());
     //   if (articleDB != null) {
     //       throw new AlreadyExistProductException();
     //   } else {
        
           // Provider provider = new Provider("company" + 1, "address" + 1, 666100000 + 1, 916661000 + 1, "No", "No");
         // providerDao.save(provider);
            Provider provider = providerDao.findAll().get(0);
            Article newArticle= new Article();
            
            newArticle.setReference("1");
            newArticle.setRetailPrice(new BigDecimal(20 + 1));
            newArticle.setDescription("1");
            newArticle.setWholesalePrice(new BigDecimal(20 + 1));
            newArticle.setProvider(provider);
            newArticle.setStock(0);
        
    
        this.articleDao.save(newArticle);
      //  }
    }

    public void delete(int id) {
        Article article = articleDao.findOne(Long.valueOf(id));
        articleDao.delete(article);
    }

    public void edit(int id, Article article) {
        // TODO Auto-generated method stub

    }

}
