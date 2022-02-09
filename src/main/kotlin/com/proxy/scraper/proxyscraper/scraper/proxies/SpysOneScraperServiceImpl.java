package com.proxy.scraper.proxyscraper.scraper.proxies;

import com.proxy.scraper.proxyscraper.dto.ProxyRequest;
import com.proxy.scraper.proxyscraper.properties.ScraperProxyProperties;
import com.proxy.scraper.proxyscraper.scraper.constant.ScraperRegexConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SpysOneScraperServiceImpl implements ScraperService {

    private static final Pattern HTTPS_REGEX = Pattern.compile(">S<");
    private Document document;

    @Autowired
    private ScraperProxyProperties scraperProxyProperties;

    @Override
    public List<ProxyRequest> getProxies() {
        List<ProxyRequest> proxies = new ArrayList<>();
        if (prepare()) {
            if (document != null) {
                Elements elements = document.body().getElementsByClass("spy14");
                elements.forEach(e -> {
                    String td = e.toString();
                    Matcher hostMatcher = ScraperRegexConstants.HOST_REGEX.matcher(td);
                    if (hostMatcher.find()) {

                        String parentTd = e.parentNode().parentNode().toString();
                        Matcher protocolMatcher = HTTPS_REGEX.matcher(parentTd);
                        String protocol = protocolMatcher.find() ? "https" : "http";
                        String host = hostMatcher.group();
                        String port = "8080";
                        ProxyRequest proxy = new ProxyRequest(protocol, host, Integer.parseInt(port));
                        proxies.add(proxy);
                        System.out.println("found: " + proxy);
                    }
                });
            }
        }
        return proxies;
    }

    private boolean prepare() {
        boolean result = false;
        String url = scraperProxyProperties.getSpysOne();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        org.apache.http.HttpResponse httpResponse = null;
        BufferedReader bufferedReader;
        StringBuffer stringBuffer = new StringBuffer();
        String line;

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(getParams(), "UTF-8"));
            httpResponse = httpClient.execute(httpPost);
        } catch (IOException e) {
            System.out.println("Error preparing getting Proxies of: %s : " + e);
        }
        if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                }
            } catch (IOException e) {
                System.out.println("Error reading lines of BufferedReader in class: %s : " + e);
            }
            document = Jsoup.parse(String.valueOf(stringBuffer));
            result = true;
        }
        return result;
    }

    private List<NameValuePair> getParams() {
        List<NameValuePair> params = new ArrayList<>(5);
        params.add(new BasicNameValuePair("xpp", "5")); //500 results
        params.add(new BasicNameValuePair("xf1", "0"));
        params.add(new BasicNameValuePair("xf2", "0"));
        params.add(new BasicNameValuePair("xf4", "2")); //8080 only
        params.add(new BasicNameValuePair("xf5", "0"));
        return params;
    }

    public Document getDocument() {
        return document;
    }
}
