package spectra.designpattern.model.message;

public class MessageText extends Message
{
    private final String messageType = "TXT";
    
    public MessageText(String text)
    {
        super(text);
    }

    public String getMessageType()
    {
        return messageType;
    }

    @Override
    public String toString()
    {
        return "MessageText [messageType=" + messageType + ", getTicketId()=" + getTicketId() + ", getSeq()=" + getSeq() + ", getUserId()=" + getUserId() + ", getMessage()=" + getMessage() + "]";
    }
}
