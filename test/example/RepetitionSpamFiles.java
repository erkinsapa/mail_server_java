package example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class RepetitionSpamFiles {
    private RepetitionSpamFilter repetitionFilter = new RepetitionSpamFilter(1);
    private final User sender = new User("sender");
    private final User receiver = new User("receiver");

    @Test
    public void testRepetitionFilterBelowThreshold() {
        Message message = new Message("This word word appears twice", "Normal caption" , sender, receiver);
        assertFalse(repetitionFilter.isSpam(message));
    }

    @Test
    public void testRepetitionFilterAtThreshold() {
        Message message = new Message("This word word word appears three times", "Normal caption", sender, receiver);
        assertFalse(repetitionFilter.isSpam(message));
    }

    @Test
    public void testRepetitionFilterAboveThreshold() {
        Message message = new Message("This word word word word appears four times", "Normal caption" , sender, receiver);
        assertFalse(repetitionFilter.isSpam(message));
    }

    @Test
    public void testRepetitionFilterMultipleWords() {
        Message message = new Message("The the the dog dog cat", "Normal caption" , sender, receiver);
        assertFalse(repetitionFilter.isSpam(message));
    }

    @Test
    public void testRepetitionFilterCaseInsensitivity() {
        Message message = new Message("Word WORD word WoRd", "Normal caption" , sender, receiver);
        assertFalse(repetitionFilter.isSpam(message));
    }

    @Test
    public void testRepetitionFilterEmptyMessage() {
        Message message = new Message("", "Caption only" , sender, receiver);
        assertFalse(repetitionFilter.isSpam(message));
    }

    @Test
    public void testRepetitionFilterIgnoresPunctuation() {
        Message message = new Message("word, word. word! word?", "Normal caption" , sender, receiver);
        assertFalse(repetitionFilter.isSpam(message));
    }

    @Test
    public void testRepetitionFilterDifferent() {
        RepetitionSpamFilter strictFilter = new RepetitionSpamFilter(1);
        Message message = new Message("Word word", "Normal caption" , sender, receiver);
        assertFalse(strictFilter.isSpam(message));

        RepetitionSpamFilter lenientFilter = new RepetitionSpamFilter(5);
        assertFalse(lenientFilter.isSpam(message));
    }
}
