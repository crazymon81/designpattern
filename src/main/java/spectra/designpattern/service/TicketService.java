package spectra.designpattern.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import spectra.designpattern.Public;
import spectra.designpattern.model.MessageBase;
import spectra.designpattern.model.MessageImage;
import spectra.designpattern.model.MessageKnw;
import spectra.designpattern.model.MessageText;
import spectra.designpattern.model.Ticket;
import spectra.designpattern.util.DateUtil;
import spectra.designpattern.util.Router;
import spectra.designpattern.util.StringUtil;

public class TicketService implements Runnable
{
    private Router router;

    private Ticket ticket;

    private List<MessageBase> messages = new ArrayList<MessageBase>();

    public TicketService(String customerId)
    {
        router = Router.getInstance();

        ticket = new Ticket();
        ticket.setTicketId(router.nextTicketId());
        ticket.setCustomerId(customerId);
    }

    public void send(String sender, String text)
    {
        this.send(sender, Public.MESSAGE_TYPE_TEXT, text);
    }
    
    public void send(String sender, String messageType, String text)
    {
        MessageBase message = null;
        if (StringUtil.equals(Public.MESSAGE_TYPE_IMAGE, messageType))
        {
            message = new MessageImage(text);
        }
        else if (StringUtil.equals(Public.MESSAGE_TYPE_KNW, messageType))
        {
            message = new MessageKnw(text);
        }
        else
        {
            message = new MessageText(text);
        }
        message.setTicketId(ticket.getTicketId());
        message.setSeq(getNextMessageSeq());
        message.setUserId(sender);
        message.setMessage(text);
        messages.add(message);
    }

    private int getNextMessageSeq()
    {
        int maxSeq = 0;
        for (MessageBase message : messages)
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

        Iterator<MessageBase> iter = messages.iterator();

        while (iter.hasNext())
        {
            MessageBase message = iter.next();
            System.out.println(message.toString());
        }
    }

    public void run()
    {
        try
        {
            this.start();
            Thread.sleep(200);
            this.send(ticket.getCustomerId(), Public.MESSAGE_TYPE_TEXT, ticket.getTicketId() + ", " + ticket.getCustomerId() + "'s 텍스트");
            Thread.sleep(200);
            this.accept(router.getRoutingAccount());
            Thread.sleep(200);
            this.send(ticket.getAccountId(), Public.MESSAGE_TYPE_TEXT, ticket.getTicketId() + ", " + ticket.getAccountId() + "'s 텍스트");
            Thread.sleep(200);
            
            this.send(ticket.getCustomerId(), Public.MESSAGE_TYPE_IMAGE, ticket.getTicketId() + ", " + ticket.getCustomerId() + "'s 이미지");
            this.send(ticket.getAccountId(), Public.MESSAGE_TYPE_IMAGE, ticket.getTicketId() + ", " + ticket.getAccountId() + "'s 이미지");
            this.send(ticket.getCustomerId(), Public.MESSAGE_TYPE_TEXT, ticket.getTicketId() + ", " + ticket.getCustomerId() + "'s 텍스트");
            this.send(ticket.getAccountId(), Public.MESSAGE_TYPE_KNW, ticket.getTicketId() + ", " + ticket.getAccountId() + "'s 상담지식");
            
            this.end();
            Thread.sleep(200);
            this.print();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
