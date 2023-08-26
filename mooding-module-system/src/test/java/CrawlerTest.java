import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Author BlueFire
 * @Date 29/8/2021 -下午3:55
 */
public class CrawlerTest {
    @Test
    public void wxCrawler() {
//        HttpClient httpClient = new DefaultHttpClient();

        //創建一個httpGet方法

//        HttpGet httpGet = new HttpGet("xxxxx");
        //設置httpGet的头部參數信息
//        httpGet.setHeader("Accept", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//        httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
//        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
//        httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
//        httpGet.setHeader("Connection", "keep-alive");
//        httpGet.setHeader("Cookie", "");
//        httpGet.setHeader("Host", "");
//        httpGet.setHeader("refer", "");
//        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
//        HttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();


        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = httpClientBuilder.build();
        RequestConfig configtime = RequestConfig.custom().setCircularRedirectsAllowed(true).setSocketTimeout(10000).setConnectTimeout(10000).build();

        HttpGet httpGet = new HttpGet("GET https://cloud.cn2030.com/sc/wx/HandlerSubscribe.ashx?act=CustomerProduct&id=4825&lat=31.25956&lng=121.52609");
        httpGet.setHeader("Cookie", "ASP.NET_SessionId=ustnukpfnnk24a5umydesqzw");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat");
        httpGet.setHeader("refer", "https://servicewechat.com/wx2c7f0f3c30d99445/79/page-frame.html");
        httpGet.setHeader("zftsl", "a4227c442774e307a0187b8c4e7e5b89");
        httpGet.setHeader("content-type", "application/json");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpGet.setHeader("Pragma","no-cache");

        httpGet.setHeader("Cache-Control","no-cache");
        httpGet.setHeader("Connection","keep-alive");
        httpGet.setHeader("Host","cloud.cn2030.com");
        httpGet.setConfig(configtime);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String contents = EntityUtils.toString(response.getEntity(), "utf-8");//utf-8、gbk
            Document document = Jsoup.parse(contents);
            System.out.println(document);
//        Elements elements = document.select("div#hza11 div.boxtxthot a");

//        for (Element element : elements) {
//            System.out.println(element.text() + " : " + element.attr("href"));
//
//        }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    boolean daili = false;


    private HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");

    private String useage = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";

    private RequestConfig configtime = RequestConfig.custom().setCircularRedirectsAllowed(true).setSocketTimeout(10000).setConnectTimeout(10000).build();

    public String getPageHtml(String url) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = httpClientBuilder.build();
        String html = "";
        HttpGet httpget = new HttpGet(url);
        httpget.addHeader("User-Agent", useage);
        httpget.setConfig(configtime);
        try {
            CloseableHttpResponse response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();

            html = EntityUtils.toString(entity, "utf-8");

            httpget.releaseConnection();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html;

    }

}
