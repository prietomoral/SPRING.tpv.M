package api.wrappersForTest;

import java.util.List;

import org.springframework.data.domain.Sort;

import wrappers.EmbroideryWrapper;

public class EmbroideryPageWrapper extends PageWrapper {

    private List<EmbroideryWrapper> content;

    public EmbroideryPageWrapper() {
    }

    public EmbroideryPageWrapper(List<EmbroideryWrapper> content, int number, int size, Sort sort, long totalElements, int totalPages) {
        super(content.size(), number, size, sort, totalElements, totalPages);
        this.content = content;
    }

    public List<EmbroideryWrapper> getContent() {
        return content;
    }

    public void setContent(List<EmbroideryWrapper> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "EmbroideryPageWrapper [" + super.toString() + ", content=" + content + "]";
    }

}
