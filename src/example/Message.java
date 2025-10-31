package example;

public class Message {
    private String caption;
    private String text;
    private User sender;
    private User receiver;

    public Message(String caption, String text, User sender, User receiver) {
        this.caption = caption;
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
    }


    public String getCaption() {
        return caption;
    }

    public String getText() {
        return text;
    }

    public User getSender() {
        return sender;
    }
    
    public User getReceiver() {
        return receiver;
    }

    @Override
    public String toString() {
        return "Сообщения{" +
                "Заголовок'" + caption + '\'' +
                "Текст'" + text + '\'' +
                ", Отправил" + sender.getUserName() +
                ", Получил" + receiver.getUserName() +
                '}';
    }
}
