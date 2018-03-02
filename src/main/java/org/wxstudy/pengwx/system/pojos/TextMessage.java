package org.wxstudy.pengwx.system.pojos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Author: peng
 * Description: 文本消息
 * Date: Created in 2018/3/1 下午9:38
 */
@Getter
@Setter
@ToString
public class TextMessage {

    private String ToUserName;

    private String FromUserName;

    private Long CreateTime;

    private String MsgType;

    private String Content;

    private String MsgId;
}
