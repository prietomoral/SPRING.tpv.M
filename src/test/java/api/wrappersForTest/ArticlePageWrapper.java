package api.wrappersForTest;

import java.util.List;

import org.springframework.data.domain.Page;

import wrappers.ArticleWrapper;

public class ArticlePageWrapper extends PageWrapper {

    private List<ArticleWrapper> content;

    public ArticlePageWrapper() {
    }

    public ArticlePageWrapper(List<ArticleWrapper> content, Page<?> page) {
        super(page);
        this.content = content;
    }

    public List<ArticleWrapper> getContent() {
        return content;
    }

    public void setContent(List<ArticleWrapper> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticlePageWrapper [" + super.toString() + ", content=" + content + "]";
    }

}
