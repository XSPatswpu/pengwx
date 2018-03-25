package org.wxstudy.pengwx.system.pojos.message;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: peng
 * Description:
 * Date: Created in 2018/3/25 下午10:23
 */
@Getter
@Setter
public class Event extends BaseMessage {
    private String Event;
    private String EventKey;
}
