package spectra.designpattern.model.channel;

public abstract class Channel
{
    public abstract String getChannelType();
    
    public abstract int push(String text);
    
    protected String channelId;
    
    protected String channelName;

    public String getChannelId()
    {
        return channelId;
    }

    public void setChannelId(String channelId)
    {
        this.channelId = channelId;
    }

    public String getChannelName()
    {
        return channelName;
    }

    public void setChannelName(String channelName)
    {
        this.channelName = channelName;
    }
}
