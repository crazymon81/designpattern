package spectra.designpattern.model.channel;

import spectra.designpattern.Public;

public class ChannelPCWeb extends Channel
{
    private final String channelType = Public.CHANNEL_TYPE_PC_WEB;
    
    public String getChannelType()
    {
        return channelType;
    }
    
    @Override
    public int push(String text)
    {
        System.out.println("[" + channelType + "] ä���� Ǫ�ø� �߼��� �� �����ϴ�.");
        return 0;
    }
}
