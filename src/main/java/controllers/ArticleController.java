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
import entities.core.Provider;
import wrappers.ArticleWrapper;
import wrappers.CrudArticleWrapper;

@Controller
public class ArticleController {

	private ArticleDao articleDao;
	@Autowired
	private ProviderDao providerDao;

	@Autowired
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	public Page<ArticleWrapper> search(Pageable pageable, String reference, String description,
			BigDecimal minRetailPrice, BigDecimal maxRetailPrice, boolean onlyOnStock) {
		Page<Article> page = articleDao.search(pageable, reference, description, minRetailPrice, maxRetailPrice,
				onlyOnStock);
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

	public void add(CrudArticleWrapper article) throws AlreadyExistProductException {

		Article articleDB = articleDao.findOne(article.getId());
		if (articleDB != null) {
			throw new AlreadyExistProductException();
		} else {
			Provider provider = providerDao.findOne(article.getProviderId());
			Article newArticle = new Article();

			newArticle.setId(article.getId());
			newArticle.setReference(article.getReference());
			newArticle.setDescription(article.getDescription());
			newArticle.setRetailPrice(article.getRetailPrice());
			newArticle.setWholesalePrice(article.getWholesalePrice());
			newArticle.setProvider(provider);
			newArticle.setStock(0);

			this.articleDao.save(newArticle);
		}
	}

	public void delete(int id) {
		Article article = articleDao.findOne(Long.valueOf(id));
		articleDao.delete(article);
	}


    public void update(CrudArticleWrapper article) {

        Provider provider = providerDao.findOne(article.getProviderId());
       
        Article articleUpdate = articleDao.findOne(article.getId());
        
        articleUpdate.setId(article.getId());
        articleUpdate.setReference(article.getReference());
        articleUpdate.setDescription(article.getDescription());
        articleUpdate.setRetailPrice(article.getRetailPrice());
        articleUpdate.setWholesalePrice(article.getWholesalePrice());
        articleUpdate.setProvider(provider);
        articleUpdate.setStock(article.getStock());

        this.articleDao.save(articleUpdate);
        
    }

    public Article findOneArticle(long id) {
        Article article = articleDao.findOne(id);
        return article;
    }

}
