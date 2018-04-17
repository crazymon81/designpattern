package spectra.designpattern;

import java.util.ArrayList;
import java.util.List;

import spectra.designpattern.model.Ticket;
import spectra.designpattern.service.TicketService;

public class Main
{
    /**
     * 
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException
    {
        int MAX_CUSTOMER_COUNT = 2;
        
        for (int i = 0; i < MAX_CUSTOMER_COUNT; i++)
        {
            String customerId = "customer" + i;
            
            TicketService service = new TicketService(customerId, Public.CHANNEL_TYPE_KAKAO);
            
            Thread t = new Thread(service);
            t.start();
        }
    }
}
