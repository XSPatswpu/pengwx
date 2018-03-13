package org.wxstudy.pengwx.system.configs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Author: peng
 * Description:
 * Date: Created in 2018/3/9 上午11:43
 */
@ConfigurationProperties(prefix = "message")
@Component
@Getter
@Setter
@ToString
public class MessageConfig {

    private String subscribeMsg;

    private String selfInfo;

    private String companyInfo;

    private String title;//文章标题

    private String description;//文章描述

    private String picUrl;

    private String url;

}
