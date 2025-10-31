package example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class SenderSpamFilterTest {

    private SpamFilter filter;
    private User spammer;
    private User legitUser;
    private User receiver;

    @BeforeEach
    void setup() {
        spammer = new User("SpamUser");
        legitUser = new User("GoodUser");
        receiver = new User("Receiver");
    }

    @Test
    void testBlockSpamSender() {
        filter = new SenderSpamFilter("spamuser");
        Message spam = new Message("Купите слона", "Дешево", spammer, receiver);
        assertTrue(filter.isSpam(spam));
    }

    @Test
    void testAllowLegitimateSender() {
        filter = new SenderSpamFilter("spammer");
        Message legit = new Message("Добрый день", "Как дела?", legitUser, receiver);
        assertFalse(filter.isSpam(legit));
    }

    @Test
    void testCaseInsensitiveMatching() {
        filter = new SenderSpamFilter("SPAMUSER");
        Message spam = new Message("Акция", "Скидки 90%", spammer, receiver);
        assertTrue(filter.isSpam(spam));
    }

    @Test
    void testEmptySenderFilter() {
        filter = new SenderSpamFilter("");
        Message msg = new Message("Тест", "Пустое имя", legitUser, receiver);
        assertFalse(filter.isSpam(msg));
    }

    @Test
    void testPartialMatchNotSpam() {
        filter = new SenderSpamFilter("spam");
        User almostSpam = new User("NotASpammer");
        Message msg = new Message("Сообщение", "Текст", almostSpam, receiver);
        assertFalse(filter.isSpam(msg));
    }

    @Test
    void testMultipleMessages() {
        filter = new SenderSpamFilter("spamuser");

        Message spam1 = new Message("Реклама", "Купите это", spammer, receiver);
        Message spam2 = new Message("Распродажа", "Торопитесь", spammer, receiver);
        Message legit = new Message("Работа", "Документы", legitUser, receiver);

        assertAll(
                () -> assertTrue(filter.isSpam(spam1)),
                () -> assertTrue(filter.isSpam(spam2)),
                () -> assertFalse(filter.isSpam(legit))
        );
    }

    @Test
    void testConstructorWithNull() {
        assertThrows(NullPointerException.class, () -> new SenderSpamFilter(null));
    }
}
