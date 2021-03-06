package spectra.designpattern.model.channel;

import spectra.designpattern.Public;

public class ChannelLine extends Channel
{
    private final String channelType = Public.CHANNEL_TYPE_LINE;
    
    public String getChannelType()
    {
        return channelType;
    }
    
    @Override
    public int push(String text)
    {
        System.out.println("[" + channelType + "] 채널 푸시 발송 내용 : " + text);
        return 1;
    }
}
