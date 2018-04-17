package spectra.designpattern.model;

public class MessageText extends MessageBase
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
