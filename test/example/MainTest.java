package example;
import com.sun.tools.javac.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
class MainTest  {
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUpStreams() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    //1
    @Test
    void testAddUser() {
        UserStorage userStorage = new UserStorage();
        userStorage.addUser("Алиса");

        assertNotNull(userStorage.getUser("Алиса"));
        assertEquals("Алиса", userStorage.getUser("Алиса").getUserName());
    }
    //2
    @Test
    void testSendMessage() {
        UserStorage userStorage = new UserStorage();
        userStorage.addUser("Алиса");
        userStorage.addUser("Борис");

        User alice = userStorage.getUser("Алиса");
        User bob = userStorage.getUser("Борис");

        alice.sendMessage("Привет", "Как дела?", bob);

        assertEquals(1, bob.getInbox().size());
        assertEquals("Привет", bob.getInbox().getFirst().getCaption());
        assertEquals("Как дела?", bob.getInbox().getFirst().getText());
    }

    //3
    @Test
    void testInboxRetrieval() {
        UserStorage userStorage = new UserStorage();
        userStorage.addUser("Алиса");
        userStorage.addUser("Борис");

        User alice = userStorage.getUser("Алиса");
        User bob = userStorage.getUser("Борис");

        alice.sendMessage("Встреча", "Давай встретимся в 10 утра", bob);
        assertEquals(1, bob.getInbox().size());

        assertEquals("Встреча", bob.getInbox().getFirst().getCaption());
    }
    //4
    @Test
    void testPrintUserList() {
        UserStorage userStorage = new UserStorage();
        userStorage.addUser("Алиса");
        userStorage.addUser("Борис");

        userStorage.list();

        String output = outputStream.toString();
        assertTrue(output.contains("Алиса"));
        assertTrue(output.contains("Борис"));
    }
    //5
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
    //6
    @Test
    void testGetCaption() {
        User sender = new User("Алиса");
        User receiver = new User("Борис");

        Message message = new Message("Добро пожаловать", "Это тестовое сообщение", sender, receiver);

        assertEquals("Добро пожаловать", message.getCaption(), "Заголовок должен быть 'Добро пожаловать'");
    }
    //7
    @Test
    void testGetText() {
        User sender = new User("Алиса");
        User receiver = new User("Борис");

        Message message = new Message("Тема", "Это текст сообщения.", sender, receiver);
        assertEquals("Это текст сообщения.", message.getText(), "Текст должен совпадать с указанным значением");
    }
    //8
    @Test
    void testGetSender() {
        User sender = new User("Алиса");
        User receiver = new User("Борис");

        Message message = new Message("Тема", "Текст сообщения", sender, receiver);
        assertEquals(sender, message.getSender(), "Отправителем должна быть Алиса");
        assertEquals("Алиса", message.getSender().getUserName(), "Имя отправителя должно быть 'Алиса'");
    }
    //9
    @Test
    void testGetReceiver() {
        User sender = new User("Алиса");
        User receiver = new User("Борис");

        Message message = new Message("Тема", "Текст сообщения", sender, receiver);
        assertEquals(receiver, message.getReceiver(), "Получателем должен быть Борис");
        assertEquals("Борис", message.getReceiver().getUserName(), "Имя получателя должно быть 'Борис'");
    }
    //10
    @Test
    void testToString() {
        User sender = new User("Алиса");
        User receiver = new User("Борис");

        Message message = new Message("Приветствие", "Привет, Борис!", sender, receiver);

        String expectedOutput = "Message{caption='Приветствие', text='Привет, Борис!', sender=Алиса, receiver=Борис}";
        assertEquals(expectedOutput, message.toString(), "Вывод toString должен соответствовать ожидаемому формату");
    }
}
