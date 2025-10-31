package example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class SimpleSpamFilterTest {

    private SimpleSpamFilter simpleFilter = new SimpleSpamFilter();
    private final User sender = new User("отправитель");
    private final User receiver = new User("получатель");


    @Test
    public void testSimpleFilterSpamInText() {
        Message message = new Message("Это спам сообщение", "Обычный заголовок", sender, receiver);
        assertTrue(simpleFilter.isSpam(message));
    }

    @Test
    public void testSimpleFilterSpamInCaption() {
        Message message = new Message("Обычный текст", "Этот заголовок содержит спам", sender, receiver);
        assertTrue(simpleFilter.isSpam(message));
    }

    @Test
    public void testSimpleFilterSpamInBoth() {
        Message message = new Message("Содержит спам текст", "Также спам в заголовке", sender, receiver);
        assertTrue(simpleFilter.isSpam(message));
    }

    @Test
    public void testSimpleFilterNoSpam() {
        Message message = new Message("Обычное сообщение", "Обычный заголовок", sender, receiver);
        assertFalse(simpleFilter.isSpam(message));
    }

    @Test
    public void testSimpleFilterCaseInsensitivity() {
        Message message = new Message("Это СПАМ", "Обычный заголовок", sender, receiver);
        assertTrue(simpleFilter.isSpam(message));

        Message message2 = new Message("Обычный текст", "Содержит СПАМ", sender, receiver);
        assertTrue(simpleFilter.isSpam(message2));
    }

    @Test
    public void testSimpleFilterEmptyMessage() {
        Message message = new Message("", "", sender, receiver);
        assertFalse(simpleFilter.isSpam(message));
    }
}
