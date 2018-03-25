package org.wxstudy.pengwx.system.utils;

import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wxstudy.pengwx.system.pojos.AccessToken;

import java.io.IOException;

import static org.wxstudy.pengwx.system.utils.WxUtils.ACCESS_TOKEN;


/**
 * Author: peng
 * Description:
 * Date: Created in 2018/3/17 下午2:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WxUtilsTest {

    @Test
    public void getAccessToken() {
        AccessToken accessToken = WxUtils.getAccessToken();
        System.out.println("access_token:" + accessToken.getAccessToken());
        System.out.println("expiresIn:" + accessToken.getExpiresIn());
        System.out.println("createTime:" + accessToken.getCreateTime());

    }
    @Test
    public void upload() throws IOException{
        JSONObject json = WxUtils.upload("/Users/xiangshaopeng/file/peng.png", ACCESS_TOKEN,"image");
        System.out.println(json.toString());
//        String mediaId = WxUtils.upload2("/Users/xiangshaopeng/file/one.jpg",WxUtils.ACCESS_TOKEN,"image");
//        System.out.println(mediaId);
    }

    @Test
    public void initMenu() throws IOException{
        String url = WxUtils.CREATE_MENU_URL.replace("ACCESS_TOKEN",ACCESS_TOKEN);

        String menu = WxUtils.initMenu();

        JSONObject json = WxUtils.doHttpPost(url,menu);
        System.out.println(json.toString());
    }


}