package spectra.designpattern.model.account;

import spectra.designpattern.Public;

public class Manager extends Account
{
    protected final String roleId = Public.ROLE_ID_MANAGER;
    
    public Manager(String accountId, String accountName, int concurrentAnswerNum)
    {
        super();
        this.accountId = accountId;
        this.accountName = accountName;
        this.concurrentAnswerNum = concurrentAnswerNum;
    }
    
    public String getRoleId()
    {
        return roleId;
    }

    @Override
    public void changeConcurrentAnswerNum(int num)
    {
        this.concurrentAnswerNum = calculrator.calculator(num);
        
        System.out.println(this.accountId + "'s ���û����� [" + concurrentAnswerNum + "] ���� ����Ǿ����ϴ�.");
    }
     
    @Override
    public void notifyConcurrentAnswerNum()
    {
        System.out.println(this.accountId + "'s ���û��� �ϰ����� [" + this.concurrentAnswerNum + "]");
    }
}