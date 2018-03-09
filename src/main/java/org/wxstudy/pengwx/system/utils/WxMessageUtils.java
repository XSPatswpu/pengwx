package org.wxstudy.pengwx.system.utils;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.wxstudy.pengwx.system.pojos.TextMessage;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: peng
 * Description: Xml与Java转换工具
 * Date: Created in 2018/3/1 下午6:27
 */
public class WxMessageUtils {

    /**
     * 消息类型常量
     */
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";

    /**
     * 事件类型常量
     */
    public static final String EVENT_SUBSCRIBE = "subscribe";
    public static final String EVENT_UNSUBSCRIBE = "unsubscribe";
    public static final String EVENT_CLICK = "CLICK";
    public static final String EVENT_VIEW = "VIEW";

    /**
     * xml转Map
     * @param request HttpServletRequest
     * @return Map
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request){
        Map<String,String> map = new HashMap<>();

        SAXReader reader = new SAXReader();

        try {
            InputStream ins = request.getInputStream();

            Document doc = reader.read(ins);

            Element rootElement = doc.getRootElement();

            List<Element> elements = rootElement.elements();

            for (Element element : elements) {
                map.put(element.getName(),element.getText());
            }

            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        return map;
    }

    /**
     * TestMessage转xml
     * @param textMessage TestMessage
     * @return xml
     */
    public static String messageToXml(TextMessage textMessage){

        XStream xstream = new XStream();

        xstream.alias("xml",textMessage.getClass());

        return xstream.toXML(textMessage);
    }

    /**
     * 初始化message
     * @param content 消息内容
     * @param toUserName 开发者账号
     * @param fromUserName 用户账号
     * @param messageType 消息类型
     * @return 消息xml
     */
    public static String initMessage(String content,String toUserName,String fromUserName,String messageType){
        TextMessage textMessage = new TextMessage();
        textMessage.setContent(content);
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setMsgType(WxMessageUtils.MESSAGE_TEXT);
        textMessage.setCreateTime(new Date().getTime());
        return WxMessageUtils.messageToXml(textMessage);
    }

}
