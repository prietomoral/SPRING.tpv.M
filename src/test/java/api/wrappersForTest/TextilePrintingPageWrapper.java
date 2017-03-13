package api.wrappersForTest;

import java.util.List;

import org.springframework.data.domain.Page;

import wrappers.TextilePrintingWrapper;

public class TextilePrintingPageWrapper extends PageWrapper {

    private List<TextilePrintingWrapper> content;

    public TextilePrintingPageWrapper() {
    }

    public TextilePrintingPageWrapper(List<TextilePrintingWrapper> content, Page<?> page) {
        super(page);
        this.content = content;
    }

    public List<TextilePrintingWrapper> getContent() {
        return content;
    }

    public void setContent(List<TextilePrintingWrapper> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextilePrintingPageWrapper [" + super.toString() + ", content=" + content + "]";
    }

}
