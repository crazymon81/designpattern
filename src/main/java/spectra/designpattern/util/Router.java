package spectra.designpattern.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Router
{
    String[] accountIds = {"agent01", "agent02", "agent03", "agent04", "agent05"};
    
    private Map keyMap;
    
    
    /**
     * singleton »ý¼º.
     * @return
     */
    public static Router getInstance()
    {
        return SingletonHolder.instance;
    }

    private static final class SingletonHolder
    {
        static final Router instance = new Router();
    }
    
    private Router()
    {
        this.keyMap = new HashMap();
    }
    
    public String nextTicketId()
    {
        String lastTicketId = (String) this.keyMap.get("ticketId");
        if (lastTicketId == null)
        {
            lastTicketId = "TCKT0000000000";
        }
        String nextTicketId = lastTicketId.substring(0, 4) + StringUtil.leftPad(Integer.parseInt(lastTicketId.substring(4)) + 1, 9, "0");
        this.keyMap.put("ticketId", nextTicketId);
        
        return nextTicketId;
    }
    
    public String getRoutingAccount()
    {
        Random random = new Random();
        int idx = random.nextInt(this.accountIds.length);
        return accountIds[idx];
    }
    
    public static void main(String[] args)
    {
        System.out.println(Router.getInstance().nextTicketId());
        System.out.println(Router.getInstance().nextTicketId());
        System.out.println(Router.getInstance().nextTicketId());
        System.out.println(Router.getInstance().nextTicketId());
    }
}
