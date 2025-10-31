package example;

import java.util.ArrayList;
import java.util.List;

public class User {


    
    private String userName;
    private List<Message> inbox;
    private List<Message> outbox;
    private List<Message> spam;
    private SpamFilter spamFilter;


    public User(String userName) {
        this.userName = userName;
        this.inbox = new ArrayList<>();
        this.outbox = new ArrayList<>();
        this.spam = new ArrayList<>();
        this.spamFilter = null;
    }


    public String getUserName() {
        return userName;
    }

    public List<Message> getInbox() {
        return inbox;
    }

    public List<Message> getOutbox() {
        return outbox;
    }

    public List<Message> getSpam() {
        return spam;
    }

    public void setSpamFilter(SpamFilter spamFilter) {
        this.spamFilter = spamFilter;
    }

    public void sendMessage(String caption, String text, User receiver) {
        Message message = new Message(caption, text, this, receiver);
        this.outbox.add(message);

        if (receiver.spamFilter != null && receiver.spamFilter.isSpam(message)) {
            receiver.spam.add(message);
        } else {
            receiver.inbox.add(message);
        }
    }
}
