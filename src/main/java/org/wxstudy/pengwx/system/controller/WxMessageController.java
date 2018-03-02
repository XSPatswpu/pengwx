package org.wxstudy.pengwx.system.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxstudy.pengwx.system.pojos.TextMessage;
import org.wxstudy.pengwx.system.utils.WxMessageUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Author: peng
 * Description:
 * Date: Created in 2018/2/28 下午7:41
 */
@RestController
@RequestMapping(value = "/wx-message")
public class WxMessageController {

    @PostMapping(value = "/text")
    public String textMessage(HttpServletRequest request){

        //从微信后台接受消息
        Map<String,String> messageMap = WxMessageUtils.xmlToMap(request);

        //开发者账号
        String toUserName = messageMap.get("ToUserName");
        //用户账号
        String fromUserName = messageMap.get("FromUserName");
        String msgType = messageMap.get("MsgType");
        String content = messageMap.get("Content");

        System.out.println("toUserName:" + toUserName);
        System.out.println("fromUserName:" + fromUserName);
        System.out.println("msgType:" + msgType);
        System.out.println("content:" + content);

        String message = null;
        if("text".equals(msgType)){//如果消息类型为text，则给微信端返回xml
            TextMessage textMessage = new TextMessage();
            textMessage.setContent("您发送的消息是：" + content);
            textMessage.setFromUserName(toUserName);
            textMessage.setToUserName(fromUserName);
            textMessage.setMsgType("text");
            textMessage.setCreateTime(new Date().getTime());
            message = WxMessageUtils.textMessageToXml(textMessage);
            System.out.println(message);
        }

        return message;
    }

}
