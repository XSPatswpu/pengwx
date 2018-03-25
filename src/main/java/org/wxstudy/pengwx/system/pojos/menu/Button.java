package org.wxstudy.pengwx.system.pojos.menu;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: peng
 * Description:
 * Date: Created in 2018/3/21 下午6:32
 */
@Getter
@Setter
public class Button {
    private String name;
    private String type;
    private Button[] sub_button;
}
