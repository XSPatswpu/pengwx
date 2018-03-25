package org.wxstudy.pengwx.system.pojos.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Author: peng
 * Description:
 * Date: Created in 2018/3/9 下午11:34
 */
@Getter
@Setter
@ToString
public class News {
    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;

}
