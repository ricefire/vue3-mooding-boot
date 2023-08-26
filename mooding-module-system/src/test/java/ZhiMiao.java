import cn.mooding.MoodingJarApplication;
import cn.mooding.modules.monitor.domain.server.Sys;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author BlueFire
 * @Date 30/8/2021 -上午8:04
 */
public class ZhiMiao {


    public static void main(String[] args) {
        String path = "https://cloud.cn2030.com/sc/wx/HandlerSubscribe.ashx?act=CustomerProduct&id=4231&lat=31.25956&lng=121.52609d";
        List<Header> headers = getCommonHeader();
        HttpGet get = new HttpGet(path);
        get.setHeaders(headers.toArray(new Header[0]));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity httpEntity = response.getEntity();
            String json =  EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
            Document document = Jsoup.parse(json);
            System.out.println(document);
            System.out.println("dgl:"+response);
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
        headers.add(new BasicHeader("referer", "https://servicewechat.com/wx2c7f0f3c30d99445/73/page-frame.html"));
        headers.add(new BasicHeader("cookie", "ASP.NET_SessionId=xdq2iqppusskivxo5n4mkyaq"));
        headers.add(new BasicHeader("content-type", "application/json"));
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Linux; Android 9; Redmi Note 7 Build/PKQ1.180904.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.136 Mobile Safari/537.36 MicroMessenger/7.0.6.1460(0x27000634) Process/appbrand0 NetType/4G Language/zh_CN"));
        headers.add(new BasicHeader("Host", "cloud.cn2030.com"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));
        headers.add(new BasicHeader("zftsl", "utf-8"));


//        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Linux; Android 5.1.1; SM-N960F Build/JLS36C; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.136 Mobile Safari/537.36 MMWEBID/1042 MicroMessenger/7.0.15.1680(0x27000F34) Process/appbrand0 WeChat/arm32 NetType/WIFI Language/zh_CN ABI/arm32"));
//        headers.add(new BasicHeader("Referer", "https://servicewechat.com/wxff8cad2e9bf18719/2/page-frame.html"));
//        headers.add(new BasicHeader("tk", Config.tk));
//        headers.add(new BasicHeader("Accept","application/json, text/plain, */*"));
//        headers.add(new BasicHeader("Host","miaomiao.scmttec.com"));
//        String cookie = Config.cookies;
//        if(!Config.responseCookie.isEmpty()){
//            String join = String.join("; ", new ArrayList<>(Config.responseCookie.values()));
//            cookie = join + "; " + cookie;
//        }
//        headers.add(new BasicHeader("Cookie", cookie));
        return headers;
    }
    private void dealHeader(CloseableHttpResponse response){
        Header[] responseHeaders = response.getHeaders("Set-Cookie");
        if (responseHeaders.length > 0) {
            for (Header responseHeader : responseHeaders) {
                String cookie = ((BufferedHeader) responseHeader).getBuffer().toString().split(";")[0].split(":")[1].trim();
                String[] split = cookie.split("=");
//                Config.responseCookie.put(split[0], cookie);
            }
        }
    }
}
