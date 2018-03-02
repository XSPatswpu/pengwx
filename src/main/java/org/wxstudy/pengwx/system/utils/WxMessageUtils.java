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
    public static String textMessageToXml(TextMessage textMessage){

        XStream xstream = new XStream();

        xstream.alias("xml",textMessage.getClass());

        return xstream.toXML(textMessage);
    }
}
