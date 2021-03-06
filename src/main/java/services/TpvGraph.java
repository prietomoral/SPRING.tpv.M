package services;

import entities.core.Alert;
import entities.core.Article;
import entities.core.CashierBalance;
import entities.core.Embroidery;
import entities.core.Invoice;
import entities.core.Provider;
import entities.core.TextilePrinting;
import entities.core.Ticket;
import entities.core.Voucher;
import entities.users.Authorization;
import entities.users.Token;
import entities.users.User;

import java.util.ArrayList;
import java.util.List;

public class TpvGraph {

    private List<User> userList;

    private List<Authorization> authorizationList;

    private List<Token> tokenList;

    private List<Voucher> voucherList;

    private List<Provider> providerList;

    private List<Article> articleList;

    private List<Embroidery> embroideryList;

    private List<TextilePrinting> textilePrintingList;

    private List<Ticket> ticketList;

    private List<Invoice> invoiceList;

    private List<Alert> alertList;

    private List<CashierBalance> cashierBalanceList;

    public TpvGraph() {
        super();
        userList = new ArrayList<>();
        authorizationList = new ArrayList<>();
        tokenList = new ArrayList<>();
        voucherList = new ArrayList<>();
        articleList = new ArrayList<>();
        embroideryList = new ArrayList<>();
        textilePrintingList = new ArrayList<>();
        providerList = new ArrayList<>();
        ticketList = new ArrayList<>();
        invoiceList = new ArrayList<>();
        alertList = new ArrayList<>();
        cashierBalanceList = new ArrayList<>();
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

    public List<Token> getTokenList() {
        return tokenList;
    }

    public void setTokenList(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public List<Voucher> getVoucherList() {
        return voucherList;
    }

    public void setVoucherList(List<Voucher> voucherList) {
        this.voucherList = voucherList;
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

    public List<Embroidery> getEmbroideryList() {
        return embroideryList;
    }

    public void setEmbroideryList(List<Embroidery> embroideryList) {
        this.embroideryList = embroideryList;
    }

    public List<TextilePrinting> getTextilePrintingList() {
        return textilePrintingList;
    }

    public void setTextilePrintingList(List<TextilePrinting> textilePrintingList) {
        this.textilePrintingList = textilePrintingList;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public List<Alert> getAlertList() {
        return alertList;
    }

    public void setAlertList(List<Alert> alertList) {
        this.alertList = alertList;
    }

    public List<CashierBalance> getCashierBalanceList() {
        return cashierBalanceList;
    }

    public void setCashierBalanceList(List<CashierBalance> cashierBalanceList) {
        this.cashierBalanceList = cashierBalanceList;
    }

}
