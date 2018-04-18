package spectra.designpattern.model;

public class Account
{
    String accountId;

    String accountName;

    String roleId;

    int concurrentAnswerNum;

    public Account(String accountId, String accountName, String roleId, int concurrentAnswerNum)
    {
        super();
        this.accountId = accountId;
        this.accountName = accountName;
        this.roleId = roleId;
        this.concurrentAnswerNum = concurrentAnswerNum;
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

    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    public int getConcurrentAnswerNum()
    {
        return concurrentAnswerNum;
    }

    public void setConcurrentAnswerNum(int concurrentAnswerNum)
    {
        this.concurrentAnswerNum = concurrentAnswerNum;
    }
}