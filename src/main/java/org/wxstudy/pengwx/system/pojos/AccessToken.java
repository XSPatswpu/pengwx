package org.wxstudy.pengwx.system.pojos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Author: peng
 * Description:
 * Date: Created in 2018/3/17 下午12:50
 */
@Getter
@Setter
@ToString
public class AccessToken {

    private String accessToken;

    private Long createTime;//创建时间

    private Long expiresIn;//有效时间
}
