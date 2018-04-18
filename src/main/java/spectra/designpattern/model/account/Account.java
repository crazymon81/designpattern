package spectra.designpattern.model.account;

import spectra.designpattern.strategy.ICalculator;

public abstract class Account
{
    protected String accountId;

    protected String accountName;

    protected int concurrentAnswerNum;
    
    protected ICalculator calculrator;
    
    public abstract String getRoleId();
    
    // ���� ���û��� ����
    public abstract void changeConcurrentAnswerNum(int num);
    
    // �Ŵ����� ���� ���û��� �ϰ����� �˸�
    public abstract void notifyConcurrentAnswerNum();
    
//    public abstract notifyRoutingStatus(boolean status);
    
    public void setCalculrator(ICalculator calculrator)
    {
        this.calculrator = calculrator;
    }
    
    public Account()
    {
        
    }
    
    public String getAccountId()
    {
        return accountId;
    }

    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }

    public String getAccountName()
    {
        return accountName;
    }

    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    }

    public int getConcurrentAnswerNum()
    {
        return concurrentAnswerNum;
    }

    public void setConcurrentAnswerNum(int concurrentAnswerNum)
    {
        this.concurrentAnswerNum = concurrentAnswerNum;
    }

    public void notifyRoutingStatus(boolean routingStatus)
    {
        System.out.println("���� ������� ���ɻ��� [" + routingStatus + "]");
    }
}