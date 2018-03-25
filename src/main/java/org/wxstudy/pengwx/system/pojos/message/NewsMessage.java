package org.wxstudy.pengwx.system.pojos.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Author: peng
 * Description:
 * Date: Created in 2018/3/9 下午11:27
 */
@Getter
@Setter
@ToString
public class NewsMessage extends BaseMessage {
    private Integer ArticleCount;
    private List<News> Articles;
}
