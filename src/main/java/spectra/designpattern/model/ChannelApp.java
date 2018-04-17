package spectra.designpattern.model;

import spectra.designpattern.Public;

public class ChannelApp extends Channel
{
    private final String channelType = Public.CHANNEL_TYPE_APP;
    
    public String getChannelType()
    {
        return channelType;
    }

    @Override
    public int push(String text)
    {
        System.out.println("[" + channelType + "] ä�� Ǫ�� �߼� ���� : " + text);
        return 1;
    }
}
