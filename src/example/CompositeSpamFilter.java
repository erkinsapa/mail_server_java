package example;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class CompositeSpamFilter implements SpamFilter {
    private List<SpamFilter> filters;

    public CompositeSpamFilter(List<SpamFilter> filters) {
        this.filters = filters;
    }

        // не смог реализовать(
    @Override
    public boolean isSpam(Message message) {
        for (SpamFilter filter : filters) {
            if (filter.isSpam(message)) {
                return true;
            }
        }
        return false;
    }
}