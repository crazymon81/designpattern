package spectra.designpattern.model.message;

public class MessageImage extends Message
{
    private final String messageType = "IMG";
    
    public MessageImage(String imageUrl)
    {
        super(imageUrl);
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
