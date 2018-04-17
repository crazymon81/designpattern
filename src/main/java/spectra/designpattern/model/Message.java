package spectra.designpattern.model;

public abstract class Message
{
    protected abstract String getMessageType();
    
    private String ticketId;

    private int seq;

    private String userId;

    private String message;
    
    public Message(String text)
    {
        this.message = text;
    }
    
    public String getTicketId()
    {
        return ticketId;
    }

    public void setTicketId(String ticketId)
    {
        this.ticketId = ticketId;
    }

    public int getSeq()
    {
        return seq;
    }

    public void setSeq(int seq)
    {
        this.seq = seq;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
    
    @Override
    public String toString()
    {
        return "Message [ticketId=" + ticketId + ", seq=" + seq + ", userId=" + userId + ", message=" + message + "]";    }
}
