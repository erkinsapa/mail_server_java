package example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void testMessageCreation() {
        User sender = new User("Алиса");
        User receiver = new User("Борис");
        Message message = new Message("Привет", "Как дела?", sender, receiver);
        assertNotNull(message, "Сообщение не должно быть null");
        assertEquals("Привет", message.getCaption(), "Заголовок должен совпадать с указанным значением");
        assertEquals("Как дела?", message.getText(), "Текст должен совпадать с указанным значением");
        assertEquals(sender, message.getSender(), "Отправитель должен совпадать с указанным пользователем");
        assertEquals(receiver, message.getReceiver(), "Получатель должен совпадать с указанным пользователем");
    }

    @Test
    void testGetCaption() {
        User sender = new User("Алиса");
        User receiver = new User("Борис");

        Message message = new Message("Добро пожаловать", "Это тестовое сообщение", sender, receiver);

        assertEquals("Добро пожаловать", message.getCaption(), "Заголовок должен быть 'Добро пожаловать'");
    }

    @Test
    void testGetText() {
        User sender = new User("Алиса");
        User receiver = new User("Борис");

        Message message = new Message("Тема", "Это текст сообщения.", sender, receiver);
        assertEquals("Это текст сообщения.", message.getText(), "Текст должен совпадать с указанным значением");
    }

    @Test
    void testGetSender() {
        User sender = new User("Алиса");
        User receiver = new User("Борис");

        Message message = new Message("Тема", "Текст сообщения", sender, receiver);
        assertEquals(sender, message.getSender(), "Отправителем должна быть Алиса");
        assertEquals("Алиса", message.getSender().getUserName(), "Имя отправителя должно быть 'Алиса'");
    }

    @Test
    void testGetReceiver() {
        User sender = new User("Алиса");
        User receiver = new User("Борис");

        Message message = new Message("Тема", "Текст сообщения", sender, receiver);
        assertEquals(receiver, message.getReceiver(), "Получателем должен быть Борис");
        assertEquals("Борис", message.getReceiver().getUserName(), "Имя получателя должно быть 'Борис'");
    }

    @Test
    void testToString() {
        User sender = new User("Алиса");
        User receiver = new User("Борис");

        Message message = new Message("Приветствие", "Привет, Борис!", sender, receiver);

        String expectedOutput = "Message{caption='Приветствие', text='Привет, Борис!', sender=Алиса, receiver=Борис}";
        assertEquals(expectedOutput, message.toString(), "Вывод toString должен соответствовать ожидаемому формату");
    }
}
