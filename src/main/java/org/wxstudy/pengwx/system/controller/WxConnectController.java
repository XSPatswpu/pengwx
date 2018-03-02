package org.wxstudy.pengwx.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wxstudy.pengwx.system.utils.WxUtils;

/**
 * Author: peng
 * Description:
 * Date: Created in 2018/2/28 下午3:04
 */
@RestController
@RequestMapping(value = "/wx-message")
public class WxConnectController {

    @GetMapping(value = "/signature")
    public String wxConnect(@RequestParam("signature") String signature,@RequestParam("timestamp")String timestamp,
                            @RequestParam("nonce") String nonce , @RequestParam("echostr") String echostr){
        //调用检查签名方法
        if(WxUtils.checkSignature(signature,timestamp,nonce)){
            return echostr;
        }
        return "";
    }

}
