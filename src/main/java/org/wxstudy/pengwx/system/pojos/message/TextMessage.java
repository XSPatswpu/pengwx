package org.wxstudy.pengwx.system.pojos.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.wxstudy.pengwx.system.pojos.message.BaseMessage;

/**
 * Author: peng
 * Description: 文本消息
 * Date: Created in 2018/3/1 下午9:38
 */
@Getter
@Setter
@ToString
public class TextMessage extends BaseMessage {
    private String Content;
}
