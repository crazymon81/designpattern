package spectra.designpattern;

import spectra.designpattern.model.account.Account;
import spectra.designpattern.model.account.Agent;
import spectra.designpattern.model.account.Manager;
import spectra.designpattern.service.TicketService;
import spectra.designpattern.strategy.OnceCalculator;
import spectra.designpattern.strategy.TwiceCalculator;
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
        //���� �α���
        Account manager = new Manager("manager01", "��â��", 5);
        Account agent = new Agent("agent01", "ȫ����", 3);
        
        System.out.println("\n=============== ����� �α���  =============================");
        Router.getInstance().login(manager);
        Router.getInstance().login(agent);
        
        System.out.println("\n===============  strategy ���Ͽ� ���� ������� ����ġ Ȯ��  =============================");
        // strategy ���� - ������ �����Ͽ� ��ɽ���
        manager.setCalculrator(new TwiceCalculator());
        agent.setCalculrator(new OnceCalculator());
        
        System.out.println("���û��� 2�� �����Ͽ� ����ġ ������ Ȯ��");
        manager.changeConcurrentAnswerNum(2);
        agent.changeConcurrentAnswerNum(2);
        
        System.out.println("\n===============  Observer ���Ͽ� ���� ���û��� ���� �˸� =============================");
        // ������ ����? - ��ο��� ���� �˸�
        Router.getInstance().changeAllConcurrentAnswerNum(3);
        Router.getInstance().notifyAllLoginAccount();
        
        System.out.println("\n===============  Observer ������� ���� �˸� =============================");
        Router.getInstance().setRoutingStatus(false);
        Router.getInstance().notifyAllRoutingStatus();
        
        System.out.println("\n===============  ���� / ���� ���� �׽�Ʈ  =============================");
        int MAX_CUSTOMER_COUNT = 2;
        for (int i = 0; i < MAX_CUSTOMER_COUNT; i++)
        {
            String customerId = "customer" + i;
            String channelType = i % 2 == 0 ? Public.CHANNEL_TYPE_KAKAO : Public.CHANNEL_TYPE_LINE;
            
            TicketService service = new TicketService(customerId, channelType);
            
            Thread t = new Thread(service);
            t.start();
        }
    }
}
