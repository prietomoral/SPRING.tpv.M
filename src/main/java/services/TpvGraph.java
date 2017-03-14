package services;

import java.util.ArrayList;
import java.util.List;

import entities.core.Article;
import entities.core.Provider;
import entities.core.TextilePrinting;

public class TpvGraph {

    private List<TextilePrinting> textilePrintingList;

    private List<Provider> providerList;

    private List<Article> articleList;

    public TpvGraph() {
        super();
        textilePrintingList = new ArrayList<TextilePrinting>();
        providerList = new ArrayList<Provider>();
        articleList = new ArrayList<Article>();
    }

    public List<TextilePrinting> getTextilePrintingList() {
        return textilePrintingList;
    }

    public void setTextilePrintingList(List<TextilePrinting> textilePrintingList) {
        this.textilePrintingList = textilePrintingList;
    }

    public List<Provider> getProviderList() {
        return providerList;
    }

    public void setProviderList(List<Provider> providerList) {
        this.providerList = providerList;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

}
