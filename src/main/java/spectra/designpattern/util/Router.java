package spectra.designpattern.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import spectra.designpattern.model.Account;

public class Router
{
//    String[] accountIds = {"agent01", "agent02", "agent03", "agent04", "agent05"};
    
    List<Account> accounts;
    
    private Map keyMap;
    
    
    /**
     * singleton 생성.
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
        accounts = new ArrayList<Account>();
    }
    
    public void login(Account account)
    {
        accounts.add(account);
        
        System.out.println("상담원 로그인 : " + account.getAccountId() + ", " + account.getAccountName());
    }
    
    public void logout(Account account)
    {
        accounts.remove(account);
        
        System.out.println("상담원 로그아웃 : " + account.getAccountId() + ", " + account.getAccountName());
    }
    
    public void changeConcurrentAnswerNum(String accountId, int answernum)
    {
        for (int i = 0; i < accounts.size(); i++)
        {
            if (StringUtil.equals(accountId, accounts.get(i).getAccountId()))
            {
                accounts.get(i).setConcurrentAnswerNum(answernum);
                break;
            }
        }
        
        System.out.println("동시상담수 변경 accountId : " + accountId + ", answernum : " + answernum);
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
    
    public Account getRoutingAccount()
    {
        Random random = new Random();
        int idx = random.nextInt(this.accounts.size());
        return accounts.get(idx);
    }
    
    public static void main(String[] args)
    {
        System.out.println(Router.getInstance().nextTicketId());
        System.out.println(Router.getInstance().nextTicketId());
        System.out.println(Router.getInstance().nextTicketId());
        System.out.println(Router.getInstance().nextTicketId());
    }
}
