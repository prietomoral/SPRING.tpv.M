package api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.AlreadyExistProductException;
import controllers.ArticleController;
import entities.core.Article;
import wrappers.ArticleWrapper;
import wrappers.CrudArticleWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.ARTICLES)
public class ArticleResource {

    private ArticleController articleController;

    @Autowired
    public void setArticleController(ArticleController articleController) {
        this.articleController = articleController;
    }

    @RequestMapping(value = Uris.SEARCH, method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER') or hasRole('OPERATOR')")
    public Page<ArticleWrapper> search(Pageable pageable, @RequestParam(required = false) String reference,
            @RequestParam(required = false) String description, @RequestParam(required = false) BigDecimal minRetailPrice,
            @RequestParam(required = false) BigDecimal maxRetailPrice,
            @RequestParam(required = false, defaultValue = "false") boolean onlyOnStock) {
        return articleController.search(pageable, reference, description, minRetailPrice, maxRetailPrice, onlyOnStock);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Article> all() {
        return articleController.all();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody CrudArticleWrapper article) throws AlreadyExistProductException {
        this.articleController.add(article);
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") int id) {
        this.articleController.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody CrudArticleWrapper article) {
        this.articleController.update(article);
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.GET)
    public Article findOneArticle(@PathVariable(value = "id") long id) {
        return articleController.findOneArticle(id);
    }

}
