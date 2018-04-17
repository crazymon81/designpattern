package spectra.designpattern.model;

import spectra.designpattern.factory.ChannelFactory;

public class Ticket
{
    private String ticketId;

    private String accountId;

    private String customerId;

    private String connectDate;

    private String startDate;

    private String endDate;

    private String ticketStatus;
    
    private Channel channel;

    public Ticket()
    {
        
    }
    
    public Ticket(String channelType)
    {
        this.channel = new ChannelFactory().createChannel(channelType);
    }
  
    public String getTicketId()
    {
        return ticketId;
    }

    public void setTicketId(String ticketId)
    {
        this.ticketId = ticketId;
    }

    public String getAccountId()
    {
        return accountId;
    }

    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(String customerId)
    {
        this.customerId = customerId;
    }

    public String getConnectDate()
    {
        return connectDate;
    }

    public void setConnectDate(String connectDate)
    {
        this.connectDate = connectDate;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getTicketStatus()
    {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus)
    {
        this.ticketStatus = ticketStatus;
    }

    public Channel getChannel()
    {
        return channel;
    }

    public void setChannel(Channel channel)
    {
        this.channel = channel;
    }

    @Override
    public String toString()
    {
        return "Ticket [ticketId=" + ticketId + ", accountId=" + accountId + ", customerId=" + customerId + ", connectDate=" + connectDate + ", startDate=" + startDate + ", endDate=" + endDate + ", ticketStatus=" + ticketStatus + "]";
    }
}
