package org.wxstudy.pengwx.system.utils;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.wxstudy.pengwx.system.pojos.AccessToken;
import org.wxstudy.pengwx.system.pojos.menu.ClickButton;
import org.wxstudy.pengwx.system.pojos.menu.ViewButton;
import org.wxstudy.pengwx.system.pojos.menu.Button;
import org.wxstudy.pengwx.system.pojos.menu.Menu;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

/**
 * Author: peng
 * Description: WX工具
 * Date: Created in 2018/2/28 下午3:18
 */
public class WxUtils {

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

    public static final String APPID = "wx0679118707e3b4a1";
    public static final String TOKEN = "shaocheng";
    public static final String APPSECRET = "2683b0f6f22443b579cd0b6f7e8a4df0";

    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    public static final String ACCESS_TOKEN = "8_TvYM8Qd9evZMiZT-_4pRxt2Bs_Ac4jc5ql3KOHnVFgkvsViYrysex8Mm6KcjXO9fnQTDNcwGzAzi5DlwZnLkLZSO4rLe_-gYuIvtzajB2cnrKU0JbTpf4o_GG3AEYlyb7dpwMowvHAYqXTsmGUKcAIAUIY";
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    // 换行符
    public static final String NEW_LINE = "\r\n";//mac上，换行符为啥不直接是\n?
    public static final String BOUNDARY_PRE_FIX = "--";

    // 定义数据分隔线
    public static final String BOUNDARY = "========7d4a6d158c9";

    /**
     * 验证签名
     *
     * @param signature 签名
     * @param timestamp 时间戳
     * @param nonce     nonce
     * @return 验证结果
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        //用token、timestamp、nonce构造数组
        String[] arr = {TOKEN, timestamp, nonce};
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

    /**
     * HttpClient GET方法
     *
     * @param url GET请求url
     * @return 服务器返回的json对象
     */
    public static JSONObject doHttpGet(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "utf-8");

        return JSONObject.fromObject(result);

    }

    /**
     * httpClient POST方法
     * @param url POST请求url
     * @param entityStr POST请求参数
     * @return 服务器返回json对象
     * @throws IOException
     */
    public static JSONObject doHttpPost(String url,String entityStr) throws IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        httpPost.setEntity(new StringEntity(entityStr,"utf-8"));

        HttpResponse response = httpClient.execute(httpPost);

        String result = EntityUtils.toString(response.getEntity(),"utf-8");

        return JSONObject.fromObject(result);
    }

    /**
     * 获取AccessToken
     * @return AccessToken
     */
    public static AccessToken getAccessToken() {
        //构造url
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);

        //执行GET请求
        JSONObject json = null;
        try {
            json = doHttpGet(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置accessToken、createTime、expiresIn
        if (json != null) {
            AccessToken accessToken = new AccessToken();
            accessToken.setAccessToken(json.getString("access_token"));
            accessToken.setExpiresIn(json.getLong("expires_in"));
            accessToken.setCreateTime(System.currentTimeMillis());
            return accessToken;
        }

        return null;
    }

    /**
     * 文件上传
     *
     * @param filePath    文件路径
     * @param accessToken 凭证
     * @param type        http消息类型
     * @return POST请求，返回的Json
     */
    public static JSONObject upload(String filePath, String accessToken, String type) throws IOException {
        //创建File
        File file = new File(filePath);

        if (!file.isFile() || !file.exists()) {
            throw new IOException("文件不存在或不是一个文件");
        }

        //服务器的域名
        String strUrl = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
        URL url = new URL(strUrl);

        //创建连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //设置POST请求
        connection.setRequestMethod("POST");
        //设置DoOutput、DoInput、UseCaches
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);

        //设置请求头参数
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);
        //设置消息体上面的参数
        StringBuffer params = new StringBuffer();
        params.append(BOUNDARY_PRE_FIX);
        params.append(BOUNDARY);
        params.append(NEW_LINE);
        params.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"" + NEW_LINE);
        params.append("Content-Type: application/octet-stream");
        // 参数头设置完以后需要两个换行，然后才是参数内容
        params.append(NEW_LINE + NEW_LINE);

        OutputStream out = new DataOutputStream(connection.getOutputStream());

        //将头参数写入到输出流中
        out.write(params.toString().getBytes());
        //数据输入流、用于读取文件数据
        DataInputStream in = new DataInputStream(new FileInputStream(filePath));

        //设置消息体
        //读写缓存单位
        byte[] buffer = new byte[1024];
        int length;
        //读写操作
        while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        //最后加上换行
        out.write(NEW_LINE.getBytes());
        //关闭输入流
        in.close();

        //定义结尾标示
        byte[] endData = (BOUNDARY_PRE_FIX + BOUNDARY + BOUNDARY_PRE_FIX + NEW_LINE).getBytes();
        out.write(endData);
        out.flush();
        out.close();

        // 定义BufferedReader输入流来读取URL的响应
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        // 接收从URL中读到的数据
        StringBuffer result = new StringBuffer();

        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }

        reader.close();

        return JSONObject.fromObject(result.toString());
    }

    /**
     * 初始化菜单对象
     * @return 菜单json字符串
     */
    public static String initMenu(){
        Menu menu = new Menu();

        Button button1 = new Button();
        button1.setName("简介");

        ClickButton button11 = new ClickButton();
        button11.setName("作者");
        button11.setType("click");
        button11.setKey("author");
        ClickButton button12 = new ClickButton();
        button12.setName("公司");
        button12.setType("click");
        button12.setKey("company");
        Button[] button1Arr = {button11,button12};

        button1.setSub_button(button1Arr);

        ViewButton button2 = new ViewButton();
        button2.setName("博客");
        button2.setType("view");//注意type属性必须设置
        button2.setUrl("https://blog.csdn.net/xsp_happyboy");

        Button button3 = new Button();
        button3.setName("其他");
        ClickButton button31 = new ClickButton();
        button31.setName("地理位置");
        button31.setType("location_select");
        button31.setKey("location1");
        ClickButton button32 = new ClickButton();
        button32.setName("扫一扫");
        button32.setType("scancode_push");
        button32.setKey("scan1");
        Button[] button3Arr = {button31,button32};
        button3.setSub_button(button3Arr);

        Button[] buttonArr = {button1,button2,button3};

        menu.setButton(buttonArr);

        return objToString(menu);

    }

    /**
     * 对象转json字符串
     * @param object 对象
     * @return json字符串
     */
    public static String objToString(Object object){
        JSONObject json = JSONObject.fromObject(object);
        return json.toString();
    }

}
