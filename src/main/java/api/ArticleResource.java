package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.ArticleController;
import wrappers.ArticleWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.ARTICLES)
public class ArticleResource {

    private ArticleController articleController;

    @Autowired
    public void setArticleController(ArticleController articleController) {
        this.articleController = articleController;
    }

    @RequestMapping(value = Uris.SEARCH, method = RequestMethod.GET)
    public Page<ArticleWrapper> search(Pageable pageable) {
        Page<ArticleWrapper> page = articleController.search(pageable);
        return page;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ArticleWrapper> getAll() {
        return articleController.getAll();
    }

}
