package spectra.designpattern;

import java.util.ArrayList;
import java.util.List;

import spectra.designpattern.model.Ticket;
import spectra.designpattern.service.TicketService;

public class Main
{
    /*private List<String> accountIds = new ArrayList();
    
    private List<String> customerIds = new ArrayList();
    
    private List<Ticket> ingTickets = new ArrayList();
    
    private List<Ticket> endedTickets = new ArrayList();
    
    public void addAccount(String accountId)
    {
        this.accountIds.add(accountId);
    }
    
    public void addCustomer(String customerId)
    {
        this.customerIds.add(customerId);
    }*/
    
    /**
     * 
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException
    {
        int MAX_CUSTOMER_COUNT = 20;
        
        for (int i = 0; i < MAX_CUSTOMER_COUNT; i++)
        {
            String customerId = "customer" + i;
            
            TicketService service = new TicketService(customerId);
            
            Thread t = new Thread(service);
            t.start();
        }
    }
}
