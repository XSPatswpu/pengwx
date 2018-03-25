package org.wxstudy.pengwx.system.utils;

import java.util.UUID;

/**
 * Author: peng
 * Description:
 * Date: Created in 2018/3/17 下午3:03
 */
public class Identities {
    private Identities() {}

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
