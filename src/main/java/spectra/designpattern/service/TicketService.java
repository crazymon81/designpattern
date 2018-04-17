package spectra.designpattern.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import spectra.designpattern.model.Message;
import spectra.designpattern.model.Ticket;
import spectra.designpattern.util.DateUtil;
import spectra.designpattern.util.Router;

public class TicketService implements Runnable
{
    private Router router;

    private Ticket ticket;

    private List<Message> messages = new ArrayList<Message>();

    public TicketService(String customerId)
    {
        router = Router.getInstance();

        ticket = new Ticket();
        ticket.setTicketId(router.nextTicketId());
        ticket.setCustomerId(customerId);
    }

    public void send(String sender, String text)
    {
        Message message = new Message(text);
        message.setTicketId(ticket.getTicketId());
        message.setSeq(getNextMessageSeq());
        message.setUserId(sender);
        message.setMessage(text);
        messages.add(message);
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
            Thread.sleep(200);
            this.send(ticket.getCustomerId(), ticket.getTicketId() + ", " + ticket.getCustomerId() + "'s 메시지");
            Thread.sleep(200);
            this.accept(router.getRoutingAccount());
            Thread.sleep(200);
            this.send(ticket.getAccountId(), ticket.getTicketId() + ", " + ticket.getAccountId() + "'s 메시지");
            Thread.sleep(200);
            
            this.send(ticket.getCustomerId(), ticket.getTicketId() + ", " + ticket.getCustomerId() + "'s 메시지");
            this.send(ticket.getAccountId(), ticket.getTicketId() + ", " + ticket.getAccountId() + "'s 메시지");
            this.send(ticket.getCustomerId(), ticket.getTicketId() + ", " + ticket.getCustomerId() + "'s 메시지");
            this.send(ticket.getAccountId(), ticket.getTicketId() + ", " + ticket.getAccountId() + "'s 메시지");
            this.send(ticket.getCustomerId(), ticket.getTicketId() + ", " + ticket.getCustomerId() + "'s 메시지");
            this.send(ticket.getAccountId(), ticket.getTicketId() + ", " + ticket.getAccountId() + "'s 메시지");
            this.send(ticket.getCustomerId(), ticket.getTicketId() + ", " + ticket.getCustomerId() + "'s 메시지");
            this.send(ticket.getAccountId(), ticket.getTicketId() + ", " + ticket.getAccountId() + "'s 메시지");
            
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
