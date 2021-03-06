package org.wxstudy.pengwx.system.utils;

import java.util.Arrays;

/**
 * Author: peng
 * Description: WX工具
 * Date: Created in 2018/2/28 下午3:18
 */
public class WxUtils {
    private static final String token = "shaocheng";

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
