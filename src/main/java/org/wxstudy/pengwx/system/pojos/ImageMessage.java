package org.wxstudy.pengwx.system.pojos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Author: peng
 * Description:
 * Date: Created in 2018/3/19 下午4:12
 */
@Getter
@Setter
@ToString
public class ImageMessage extends BaseMessage {
    private WxImage Image;
}
