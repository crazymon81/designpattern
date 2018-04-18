package spectra.designpattern.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import spectra.designpattern.Public;
import spectra.designpattern.factory.MessageFactory;
import spectra.designpattern.model.Message;
import spectra.designpattern.model.Ticket;
import spectra.designpattern.util.DateUtil;
import spectra.designpattern.util.Router;

public class TicketService implements Runnable
{
    private Router router;

    private Ticket ticket;

    private List<Message> messages = new ArrayList<Message>();

    public TicketService(String customerId, String channelType)
    {
        router = Router.getInstance();

        ticket = new Ticket(channelType);
        ticket.setTicketId(router.nextTicketId());
        ticket.setCustomerId(customerId);
    }

    public void send(String sender, String text)
    {
        this.send(sender, Public.MESSAGE_TYPE_TEXT, text);
    }
    
    public void send(String sender, String messageType, String text)
    {
        MessageFactory factory = new MessageFactory();
        
        Message message = factory.createMessage(messageType, text);
        message.setTicketId(ticket.getTicketId());
        message.setSeq(getNextMessageSeq());
        message.setUserId(sender);
        
        messages.add(message);
        
        this.ticket.push(message);
    }

    private int getNextMessageSeq()
    {
        int maxSeq = 0;
        for (Message message : messages)
        {
            int seq = message.getSeq();
            if (seq >= maxSeq)
            {
                maxSeq = seq;
            }
        }
        return maxSeq + 1;
    }

    public void start()
    {
        this.ticket.setTicketStatus("WAIT");
        this.ticket.setConnectDate(DateUtil.getCurrDateTimeStamp());
    }

    public void accept(String accountId)
    {
        this.ticket.setAccountId(accountId);
        this.ticket.setTicketStatus("ING");
        this.ticket.setStartDate(DateUtil.getCurrDateTimeStamp());
    }

    public void end()
    {
        this.ticket.setTicketStatus("END");
        this.ticket.setEndDate(DateUtil.getCurrDateTimeStamp());
    }

    public void print()
    {
        System.out.println(ticket.toString());

        Iterator<Message> iter = messages.iterator();

        while (iter.hasNext())
        {
            Message message = iter.next();
            System.out.println(message.toString());
        }
    }

    public void run()
    {
        try
        {
            this.start();
            this.send(ticket.getCustomerId(), Public.MESSAGE_TYPE_TEXT, ticket.getTicketId() + ", " + ticket.getCustomerId() + "'s 텍스트");
            this.accept(router.getRoutingAccount().getAccountId());
            this.send(ticket.getAccountId(), Public.MESSAGE_TYPE_TEXT, ticket.getTicketId() + ", " + ticket.getAccountId() + "'s 텍스트");
            this.send(ticket.getCustomerId(), Public.MESSAGE_TYPE_IMAGE, ticket.getTicketId() + ", " + ticket.getCustomerId() + "'s 이미지");
            this.send(ticket.getAccountId(), Public.MESSAGE_TYPE_IMAGE, ticket.getTicketId() + ", " + ticket.getAccountId() + "'s 이미지");
            this.send(ticket.getCustomerId(), Public.MESSAGE_TYPE_TEXT, ticket.getTicketId() + ", " + ticket.getCustomerId() + "'s 텍스트");
            this.send(ticket.getAccountId(), Public.MESSAGE_TYPE_KNW, ticket.getTicketId() + ", " + ticket.getAccountId() + "'s 상담지식");
            
            this.end();
            this.print();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
