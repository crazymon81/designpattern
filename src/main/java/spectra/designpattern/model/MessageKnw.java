package spectra.designpattern.model;

public class MessageKnw extends MessageBase
{
    public MessageKnw(String text)
    {
        super(text);
    }

    private final String messageType = "KNW";

    public String getMessageType()
    {
        return messageType;
    }
    
    @Override
    public String toString()
    {
        return "MessageKnw [messageType=" + messageType + ", getTicketId()=" + getTicketId() + ", getSeq()=" + getSeq() + ", getUserId()=" + getUserId() + ", getMessage()=" + getMessage() + "]";
    }
}
