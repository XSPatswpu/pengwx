package org.wxstudy.pengwx.system.utils;

import org.wxstudy.pengwx.system.pojos.TextMessage;

import java.util.Arrays;
import java.util.Date;

/**
 * Author: peng
 * Description: WX工具
 * Date: Created in 2018/2/28 下午3:18
 */
public class WxUtils {
    private static final String token = "shaocheng";

    /**
     * 消息类型常量
     */
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_NEWS = "news";

    /**
     * 事件类型常量
     */
    public static final String EVENT_SUBSCRIBE = "subscribe";
    public static final String EVENT_UNSUBSCRIBE = "unsubscribe";
    public static final String EVENT_CLICK = "CLICK";
    public static final String EVENT_VIEW = "VIEW";

    /**
     * 验证签名
     * @param signature 签名
     * @param timestamp 时间戳
     * @param nonce nonce
     * @return 验证结果
     */
    public static boolean checkSignature(String signature,String timestamp,String nonce){
        //用token、timestamp、nonce构造数组
        String[] arr = {token,timestamp,nonce};
        //给数组排序
        Arrays.sort(arr);
        //根据数组生成字符串(StringBuffer线程安全)
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            strBuffer.append(arr[i]);
        }
        //利用sha1算法生成字符串
        String result = Sha1Utils.getSha1(strBuffer.toString());

        //比较signature与sha1算法生成的结果
        return signature.equals(result);
    }

}
