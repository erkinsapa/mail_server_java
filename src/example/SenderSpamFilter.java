package example;

public class SenderSpamFilter implements SpamFilter {
    private String spamSender;

    public SenderSpamFilter(String spamSender) {
        this.spamSender = spamSender.toLowerCase();
    }

    @Override
    public boolean isSpam(Message message) {
        return message.getSender().getUserName().toLowerCase().equals(spamSender);
    }
}