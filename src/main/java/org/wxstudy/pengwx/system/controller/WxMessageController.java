package org.wxstudy.pengwx.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxstudy.pengwx.system.configs.CompanyConfig;
import org.wxstudy.pengwx.system.configs.MessageConfig;
import org.wxstudy.pengwx.system.utils.WxMessageUtils;
import org.wxstudy.pengwx.system.utils.WxUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Author: peng
 * Description:
 * Date: Created in 2018/2/28 下午7:41
 */
@RestController
@RequestMapping(value = "/wx-common")
public class WxMessageController {

    @Autowired
    private MessageConfig messageConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @PostMapping(value = "/message")
    public String textMessage(HttpServletRequest request){

        //从微信后台接受消息
        Map<String,String> messageMap = WxMessageUtils.xmlToMap(request);

        //开发者账号
        String toUserName = messageMap.get("ToUserName");
        //用户账号
        String fromUserName = messageMap.get("FromUserName");
        String msgType = messageMap.get("MsgType");
        String content = messageMap.get("Content");
        String event = messageMap.get("Event");
        String eventKey = messageMap.get("EventKey");

        //给微信后台响应的消息
        String message = null;

        if(WxUtils.MESSAGE_TEXT.equals(msgType)){//如果消息类型为text，则给微信端返回xml
            if("1".equals(content)){
                message = WxMessageUtils.initNewsMsg(messageConfig,toUserName,fromUserName);
            }else if("2".equals(content)){
                message = WxMessageUtils.initTextMsg(messageConfig.getCompanyInfo(),toUserName,fromUserName);
            }else if("?".equals(content) || "？".equals(content)){
                message = WxMessageUtils.initTextMsg(messageConfig.getSubscribeMsg(),toUserName,fromUserName);
            }else if("3".equals(content)){
                message = WxMessageUtils.initImageMsg("ilp0JPpJnNQ_1uwNEn-lXvBDxZDWqOygRAiH0hHUCiCWrCfi87yEvC-dqZkr6aAV",toUserName,fromUserName);
            }else{
                message = WxMessageUtils.initTextMsg("对不起，你的消息劳资不认识！",toUserName,fromUserName);
            }

        }else if(WxUtils.MESSAGE_EVENT.equals(msgType)){//如果消息类型为event
            if(WxUtils.EVENT_SUBSCRIBE.equals(event)){//事件类型为subscribe
                //初始化消息并发送给微信后台
                message = WxMessageUtils.initTextMsg(messageConfig.getSubscribeMsg(),toUserName,fromUserName);
            }else if(WxUtils.EVENT_CLICK.equals(event)){
                if("author".equals(eventKey)){
                    //发送作者简介
                    message = WxMessageUtils.initNewsMsg(messageConfig,toUserName,fromUserName);
                }else if("company".equals(eventKey)){
                    //发送公司简介
                    message = WxMessageUtils.initNewsMsg(companyConfig,toUserName,fromUserName);
                }
            }
        }

        return message;
    }

}
