package spectra.designpattern.model;

import spectra.designpattern.Public;

public class ChannelKakao extends Channel
{
    private final String channelType = Public.CHANNEL_TYPE_KAKAO;
    
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
