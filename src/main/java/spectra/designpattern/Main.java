package spectra.designpattern;

import spectra.designpattern.model.Account;
import spectra.designpattern.service.TicketService;
import spectra.designpattern.util.Router;

public class Main
{
    /**
     * 
     * @param args
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws InterruptedException
    {
        //상담원 로그인
        Router.getInstance().login(new Account("agent01", "정창일", "ACCTMGR", 2));
        Router.getInstance().login(new Account("agent01", "홍경택", "ACCTAGT", 1));
        
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
