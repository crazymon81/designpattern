package spectra.designpattern.model;

public class MessageKnw extends Message
{
    private final String messageType = "KNW";
    
    public MessageKnw(String knwUrl)
    {
        super(knwUrl);
    }

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
