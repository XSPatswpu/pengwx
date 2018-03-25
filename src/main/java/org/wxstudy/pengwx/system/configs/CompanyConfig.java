package org.wxstudy.pengwx.system.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Author: peng
 * Description:
 * Date: Created in 2018/3/25 下午10:42
 */
@ConfigurationProperties(prefix = "companyMessage")
@Component
@Getter
@Setter
public class CompanyConfig {
    private String title;//文章标题
    private String description;//文章描述
    private String picUrl;
    private String url;

}
