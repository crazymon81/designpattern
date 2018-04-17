package spectra.designpattern.factory;

import spectra.designpattern.Public;
import spectra.designpattern.model.Message;
import spectra.designpattern.model.MessageImage;
import spectra.designpattern.model.MessageKnw;
import spectra.designpattern.model.MessageText;
import spectra.designpattern.util.StringUtil;

public class MessageFactory
{
    public Message createMessage(String messageType, String text)
    {
        Message message = null;
        if (StringUtil.equals(Public.MESSAGE_TYPE_IMAGE, messageType))
        {
            message = new MessageImage(text);
        }
        else if (StringUtil.equals(Public.MESSAGE_TYPE_KNW, messageType))
        {
            message = new MessageKnw(text);
        }
        else if (StringUtil.equals(Public.MESSAGE_TYPE_TEXT, messageType))
        {
            message = new MessageText(text);
        }
        else 
        {
            message = null;
        }
        
        return message;
    }
}
