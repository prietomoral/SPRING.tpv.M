package services;

import java.util.ArrayList;
import java.util.List;

import entities.core.Article;
import entities.core.Provider;
import entities.core.TextilePrinting;
import entities.users.Authorization;
import entities.users.User;

public class TpvGraph {

    private List<User> userList;

    private List<Authorization> authorizationList;

    private List<TextilePrinting> textilePrintingList;

    private List<Provider> providerList;

    private List<Article> articleList;

    public TpvGraph() {
        super();
        userList = new ArrayList<>();
        authorizationList = new ArrayList<>();
        textilePrintingList = new ArrayList<>();
        providerList = new ArrayList<>();
        articleList = new ArrayList<>();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Authorization> getAuthorizationList() {
        return authorizationList;
    }

    public void setAuthorizationList(List<Authorization> authorizationList) {
        this.authorizationList = authorizationList;
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
