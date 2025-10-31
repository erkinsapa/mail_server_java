package example;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class KeywordsSpamFilterTest {

    @Test
    void testSpamKeywordInText() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter(Set.of("реклама", "бесплатно"));
        User sender = new User("Алиса");
        User receiver = new User("Борис");
        Message message = new Message("Привет", "Здесь только реклама", sender, receiver);
        assertTrue(filter.isSpam(message), "Должно определяться как спам по ключевому слову в тексте");
    }

    @Test
    void testSpamKeywordInCaption() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter(Set.of("акция", "скидка"));
        User sender = new User("Алиса");
        User receiver = new User("Борис");
        Message message = new Message("Большая скидка сегодня", "Приходите на встречу", sender, receiver);
        assertTrue(filter.isSpam(message), "Должно определяться как спам по ключевому слову в заголовке");
    }

    @Test
    void testNoSpamKeywords() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter(Set.of("спам", "подписка"));
        User sender = new User("Алиса");
        User receiver = new User("Борис");
        Message message = new Message("Новости", "Это обычное сообщение", sender, receiver);
        assertFalse(filter.isSpam(message), "Не должно определяться как спам, ключевых слов нет");
    }

    @Test
    void testCaseInsensitiveSpamKeyword() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter(Set.of("СПеЦиАЛьНое"));
        User sender = new User("Алиса");
        User receiver = new User("Борис");
        Message message = new Message("Специальное предложение", "Суперакция", sender, receiver);
        assertTrue(filter.isSpam(message), "Должно быть найдено ключевое слово независимо от регистра");
    }

    @Test
    void testMultipleKeywords() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter(Set.of("подарок", "бонус", "конкурс"));
        User sender = new User("Алиса");
        User receiver = new User("Борис");
        Message msg1 = new Message("Подарок для тебя", "Поздравляем!", sender, receiver);
        Message msg2 = new Message("Новости", "Участвуй в конкурсе", sender, receiver);
        Message msg3 = new Message("Бонус", "Для лучших клиентов", sender, receiver);

        assertAll(
                () -> assertTrue(filter.isSpam(msg1), "Ключевое слово 'подарок' из заголовка"),
                () -> assertTrue(filter.isSpam(msg2), "Ключевое слово 'конкурс' из текста"),
                () -> assertTrue(filter.isSpam(msg3), "Ключевое слово 'бонус' из заголовка")
        );
    }

    @Test
    void testEmptyKeywords() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter(Set.of());
        User sender = new User("Алиса");
        User receiver = new User("Борис");
        Message message = new Message("Любое сообщение", "Любой текст", sender, receiver);
        assertFalse(filter.isSpam(message), "Ключевых слов нет, сообщение не спам");
    }
}
