package org.wxstudy.pengwx.system.pojos.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Author: peng
 * Description: message基本信息
 * Date: Created in 2018/3/9 下午11:20
 */
@Getter
@Setter
@ToString
public class BaseMessage {

    private String ToUserName;

    private String FromUserName;

    private Long CreateTime;

    private String MsgType;

    private String MsgId;

}
