package wrappers;

import java.util.List;

import org.springframework.data.domain.Sort;

public class ArticlePageWrapper extends PageWrapper {

    private List<ArticleWrapper> content;

    public ArticlePageWrapper() {
    }

    public ArticlePageWrapper(List<ArticleWrapper> content, int number, int size, Sort sort, long totalElements, int totalPages) {
        super(content.size(), number, size, sort, totalElements, totalPages);
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
