package org.wxstudy.pengwx.system.utils;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.wxstudy.pengwx.system.configs.MessageConfig;
import org.wxstudy.pengwx.system.pojos.News;
import org.wxstudy.pengwx.system.pojos.NewsMessage;
import org.wxstudy.pengwx.system.pojos.TextMessage;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Author: peng
 * Description: Xml与Java转换工具
 * Date: Created in 2018/3/1 下午6:27
 */
public class WxMessageUtils {

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
     * 初始化textMessage
     * @param content 消息内容
     * @param toUserName 开发者账号
     * @param fromUserName 用户账号
     * @return 消息xml
     */
    public static String initTextMsg(String content,String toUserName,String fromUserName){
        TextMessage textMessage = new TextMessage();
        textMessage.setContent(content);
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setMsgType(WxUtils.MESSAGE_TEXT);
        textMessage.setCreateTime(new Date().getTime());
        return WxMessageUtils.messageToXml(textMessage);
    }

    /**
     * 图文消息转xml
     * @param newsMsg 图文消息
     * @return xml
     */
    public static String newsToXml(NewsMessage newsMsg){
        XStream xstream = new XStream();

        xstream.alias("xml",newsMsg.getClass());
        xstream.alias("item",News.class);

        return xstream.toXML(newsMsg);
    }

    /**
     * 初始化NewsMessage
     * @param msgConfig 消息配置类
     * @param toUserName 开发者账号
     * @param fromUserName 用户账号
     * @return 消息xml
     */
    public static String initNewsMsg(MessageConfig msgConfig,String toUserName,String fromUserName){
        NewsMessage newsMsg = new NewsMessage();
        newsMsg.setToUserName(fromUserName);
        newsMsg.setFromUserName(toUserName);
        newsMsg.setMsgType(WxUtils.MESSAGE_NEWS);
        newsMsg.setCreateTime(new Date().getTime());
        newsMsg.setArticleCount(1);
        List<News> newsList = new ArrayList<>();
        News news = new News();
        news.setTitle(msgConfig.getTitle());
        news.setDescription(msgConfig.getDescription());
        news.setPicUrl(msgConfig.getPicUrl());
        news.setUrl(msgConfig.getUrl());
        newsList.add(news);
        newsMsg.setArticles(newsList);
        String msg = newsToXml(newsMsg);
        System.out.println(msg);
        return msg;
    }

}
