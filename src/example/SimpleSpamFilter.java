package example;

public class SimpleSpamFilter implements SpamFilter {
    @Override
    public boolean isSpam(Message message) {
        String text = message.getText().toLowerCase();
        String caption = message.getCaption().toLowerCase();
        return text.contains("spam") || caption.contains("spam");
    }
}

//

//

//

//

//

//

//

//

//

//




