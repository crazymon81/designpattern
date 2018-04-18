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
        //상담원 로그인
        Account manager = new Manager("manager01", "정창일", 5);
        Account agent = new Agent("agent01", "홍경택", 3);
        
        System.out.println("\n=============== 사용자 로그인  =============================");
        Router.getInstance().login(manager);
        Router.getInstance().login(agent);
        
        System.out.println("\n===============  strategy 패턴에 의한 가용상담수 가중치 확인  =============================");
        // strategy 패턴 - 행위를 주입하여 기능실행
        manager.setCalculrator(new TwiceCalculator());
        agent.setCalculrator(new OnceCalculator());
        
        System.out.println("동시상담수 2로 세팅하여 가중치 적용결과 확인");
        manager.changeConcurrentAnswerNum(2);
        agent.changeConcurrentAnswerNum(2);
        
        System.out.println("\n===============  Observer 패턴에 의한 동시상담수 변경 알림 =============================");
        // 옵저버 패턴? - 모두에서 변경 알림
        Router.getInstance().changeAllConcurrentAnswerNum(3);
        Router.getInstance().notifyAllLoginAccount();
        
        System.out.println("\n===============  Observer 상담인입 차단 알림 =============================");
        Router.getInstance().setRoutingStatus(false);
        Router.getInstance().notifyAllRoutingStatus();
        
        System.out.println("\n===============  상담원 / 고객간 톡상담 테스트  =============================");
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
