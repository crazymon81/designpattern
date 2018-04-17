package spectra.designpattern.model;

public class MessageImage extends MessageBase
{
    private final String messageType = "IMG";
    
    public MessageImage(String text)
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
        return "MessageImage [messageType=" + messageType + ", getTicketId()=" + getTicketId() + ", getSeq()=" + getSeq() + ", getUserId()=" + getUserId() + ", getMessage()=" + getMessage() + "]";
    }
}
