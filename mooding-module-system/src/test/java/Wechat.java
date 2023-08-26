import cn.mooding.modules.monitor.domain.server.Sys;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 爬取微信公众号数据
 *
 * @Author BlueFire
 * @Date 1/9/2021 -上午7:06
 */
public class Wechat {

    public static void main(String[] args) {
        String path = "https://mp.weixin.qq.com/s/hsqfeRnA1wPx6aS_OEYYHg";
        List<Header> headers = getCommonHeader();
        // 设置代理IP、端口、协议（请分别替换）
        HttpHost proxy = new HttpHost("60.195.62.150", 80, "http");
        // 把代理设置到请求配置
        RequestConfig defaultRequestConfig = RequestConfig.custom().setProxy(proxy).build();
        HttpGet get = new HttpGet(path);
        get.setHeaders(headers.toArray(new Header[0]));
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity httpEntity = response.getEntity();
            String json = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
            Document document = Jsoup.parse(json);
            System.out.println(document);
            System.out.println("dgl:" + response);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Header> getCommonHeader() {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("charset", "utf-8"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip, deflate, br"));
        headers.add(new BasicHeader("cookie", "XWINDEXGREY=0; tvfe_boss_uuid=d0b269ac138fe20b; pgv_pvid=6352168174; ptcz=ccc4cdeb611296fe6ad5e449aba55ead1be1e8f41dfc710d01819e25bdd73bba; pgv_pvi=3521411072; pac_uid=1_102601560; RK=gESQWaECcJ; mm_lang=zh_CN; ua_id=eTgddcAshxWjGPPBAAAAAI1-lY0TCgBNO0OZi__mF0I=; xid=bebfe921cc1c8cea409a92da983f44e2; ts_uid=7102381886; eas_sid=q1b6v1Q3g1i3u2D9z1y2H4K5z2; ied_qq=o0102601560; uin_cookie=o0102601560; pt2gguin=o0102601560; rewardsn=; wxtokenkey=777"));
        headers.add(new BasicHeader("content-type", "application/json"));
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Linux; Android 9; Redmi Note 7 Build/PKQ1.180904.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.136 Mobile Safari/537.36 MicroMessenger/7.0.6.1460(0x27000634) Process/appbrand0 NetType/4G Language/zh_CN"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));

        return headers;
    }

    /**
     * selenium 解决数据异步加载问题
     */
    @Test
    public void wechatSelenim() {
        String path = "https://mp.weixin.qq.com/s/hsqfeRnA1wPx6aS_OEYYHg";
        System.getProperties().setProperty("webdriver.chrome.driver", "D://project/git/mooding-boot/mooding-boot/mooding-module-system/src/main/resources/chromedriver_win32/chromedriver.exe");
        // 设置无头浏览器，这样就不会弹出浏览器窗口
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.get(path);
        // 获取到要闻新闻列表
        System.out.println("DGL" + webDriver.findElements(By.xpath("//*[@id=\"js_content\"]")));
        List<WebElement> webElements = webDriver.findElements(By.className("result-success"));
        System.out.println("Ssss:" + webElements.size());
    }

    @Test
    public void ipPorxy() {
        //爬取的目标网站，url记得换下。。。！！！
        String ip = "121.4.36.93";
        int port = 8888;

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
        try {
            long a = System.currentTimeMillis();
//            System.setProperty("https.proxySet", "true");
//            System.getProperties().put("https.proxyHost", ip);
//            System.getProperties().put("https.proxyPort", port);
            String url = "https://gitee.com/moodingtech/vue3-mooding-boot";
            //爬取的目标网站，url记得换下。。。！！！
           /* Document doc = Jsoup.connect("http://49.235.52.198:8088/login?redirect=/home")
                    .timeout(5000)
//                    .proxy(ip, port, null)
//                    .data(map)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Linux; Android 9; Redmi Note 7 Build/PKQ1.180904.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.136 Mobile Safari/537.36 MicroMessenger/7.0.6.1460(0x27000634) Process/appbrand0 NetType/4G Language/zh_CN")
                   .header("referer", "https://mp.weixin.qq.com/s/hsqfeRnA1wPx6aS_OEYYHg")//这个来源记得换..
                    .get();*/
            Document   doc = Jsoup.connect(url).proxy(proxy).ignoreContentType(true).timeout(3000) .header("referer", "http://49.235.52.198:8088/login?redirect=/home").proxy(proxy).get();

            System.out.println(doc);
            System.out.println(ip + ":" + port + "访问时间:" + (System.currentTimeMillis() - a) + "   访问结果: " + doc.text());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
