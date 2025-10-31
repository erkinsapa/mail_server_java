package example;

import java.util.HashMap;
import java.util.Map;

public class RepetitionSpamFilter implements SpamFilter {
    private int maxRepetitions;

    public RepetitionSpamFilter(int maxRepetitions) {
        this.maxRepetitions = maxRepetitions;
    }

    @Override
    public boolean isSpam(Message message) {
        String text = message.getText().toLowerCase();
        String[] words = text.split("\\W+");
        Map<String, Integer> wordCounts = new HashMap<>();

        for (String word : words) {
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            if (wordCounts.get(word) > maxRepetitions) {
                return (true);
            }
        }

        return (false);
    }
}