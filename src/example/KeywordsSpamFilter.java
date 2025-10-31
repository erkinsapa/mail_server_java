package example;

import java.util.Set;

public class KeywordsSpamFilter implements SpamFilter {
    private Set<String> keywords;

    public KeywordsSpamFilter(Set<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean isSpam(Message message) {
        String text = message.getText().toLowerCase();
        String caption = message.getCaption().toLowerCase();
        for (String keyword : keywords) {
            if (text.contains(keyword.toLowerCase()) || caption.contains(keyword.toLowerCase())) {
                return (true);
            }
        }
        return (false);
    }
}