package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import api.exceptions.NotFoundProviderIdException;
import daos.core.ArticleDao;
import daos.core.ProviderDao;
import entities.core.Article;
import entities.core.Provider;
import wrappers.ProviderAddWrapper;
import wrappers.ProviderIdCompanyWrapper;
import wrappers.ProviderWrapper;

@Controller
public class ProviderController {
    private ProviderDao providerDao;

    private ArticleDao articleDao;

    @Autowired
    public void setProviderDao(ProviderDao providerDao) {
        this.providerDao = providerDao;
    }

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public Provider add(ProviderAddWrapper providerWrapper) {
        Provider provider = new Provider(providerWrapper.getCompany(), providerWrapper.getAddress(), providerWrapper.getMobile(),
                providerWrapper.getPhone(), providerWrapper.getPaymentConditions(), providerWrapper.getNote());

        return providerDao.save(provider);
    }

    public List<ProviderWrapper> getAll() {
        List<ProviderWrapper> providerList = new ArrayList<>();

        for (Provider provider : providerDao.findAll()) {
            providerList.add(new ProviderWrapper(provider.getId(), provider.getCompany(), provider.getAddress(), provider.getMobile(),
                    provider.getPhone(), provider.getPaymentConditions(), provider.getNote()));
        }

        return providerList;
    }

    public List<ProviderIdCompanyWrapper> getAllIdCompany() {
        List<ProviderIdCompanyWrapper> providerList = new ArrayList<>();

        for (Provider provider : providerDao.findAll()) {
            providerList.add(new ProviderIdCompanyWrapper(provider.getId(), provider.getCompany()));
        }

        return providerList;
    }

    public ProviderWrapper getOneById(int id) throws NotFoundProviderIdException {
        Provider provider = providerDao.findOne(id);
        
        if(provider == null) {
            throw new NotFoundProviderIdException();
        }
        
        return new ProviderWrapper(provider.getId(), provider.getCompany(), provider.getAddress(), provider.getMobile(),
                provider.getPhone(), provider.getPaymentConditions(), provider.getNote());
    }

    public void delete(int id) {
        Provider provider = providerDao.findOne(id);

        if (provider != null) {
            List<Article> articles = articleDao.findAll();
            for (Article a : articles) {
                Provider p = a.getProvider();
                if (p != null && p.equals(provider)) {
                    a.setProvider(null);
                    articleDao.save(a);
                }
            } 
        }
        providerDao.delete(id);
    }

    public void deleteAll() {
        List<Provider> providers = providerDao.findAll();

        if (!providers.isEmpty()) {
            List<Article> articles = articleDao.findAll();

            for (Article a : articles) {
                a.setProvider(null);
                articleDao.save(a);
            }

            providerDao.deleteAll();
        }
    }

    public void update(ProviderWrapper providerWrapper) {
        Provider providerDB = providerDao.findOne(providerWrapper.getId());

        providerDB.setAddress(providerWrapper.getAddress());
        providerDB.setCompany(providerWrapper.getCompany());
        providerDB.setMobile(providerWrapper.getMobile());
        providerDB.setNote(providerWrapper.getNote());
        providerDB.setPaymentConditions(providerWrapper.getPaymentConditions());
        providerDB.setPhone(providerWrapper.getPhone());
        
        providerDao.save(providerDB);
    }
}
