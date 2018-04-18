package spectra.designpattern.factory;

import spectra.designpattern.Public;
import spectra.designpattern.model.channel.Channel;
import spectra.designpattern.model.channel.ChannelApp;
import spectra.designpattern.model.channel.ChannelKakao;
import spectra.designpattern.model.channel.ChannelLine;
import spectra.designpattern.model.channel.ChannelPCWeb;
import spectra.designpattern.util.StringUtil;

public class ChannelFactory
{
    public Channel createChannel(String channelType)
    {
        Channel channel = null;
        if (StringUtil.equals(Public.CHANNEL_TYPE_PC_WEB, channelType))
        {
            channel = new ChannelPCWeb();
        }
        else if (StringUtil.equals(Public.CHANNEL_TYPE_APP, channelType))
        {
            channel = new ChannelApp();
        }
        else if (StringUtil.equals(Public.CHANNEL_TYPE_KAKAO, channelType))
        {
            channel = new ChannelKakao();
        }
        else if (StringUtil.equals(Public.CHANNEL_TYPE_LINE, channelType))
        {
            channel = new ChannelLine();
        }
        else 
        {
            channel = null;
        }
        
        return channel;
    }
}
