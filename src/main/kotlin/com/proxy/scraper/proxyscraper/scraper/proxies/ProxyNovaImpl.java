//package com.proxy.scraper.proxyscraper.scraper.proxies;
//
//import com.proxy.scraper.proxyscraper.dto.Proxy;
//import com.proxy.scraper.proxyscraper.properties.ScraperProxyProperties;
//import com.proxy.scraper.proxyscraper.scraper.connection.ScraperDocument;
//import com.proxy.scraper.proxyscraper.scraper.constant.ScraperRegexConstants;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ProxyNovaImpl implements ScraperService {
//
//    @Autowired
//    private ScraperProxyProperties scraperProxyProperties;
//
//    @Override
//    public List<Proxy> getProxies() {
//        List<Proxy> proxies = new ArrayList<>();
//        List<String> resources = scraperProxyProperties.getProxyNova();
//
//        resources.stream().forEach(url -> {
//            Document document = ScraperDocument.Companion.getDocFromUrl(url);
//            Elements elements = document.body().getElementsByTag("td");
//
//            for (int j = 0; j < elements.size() - 1; j++) {
//                Matcher hostMatcher = ScraperRegexConstants.HOST_REGEX.matcher(elements.get(j).toString());
//                Matcher portMatcher = ScraperRegexConstants.PORT_REGEX.matcher(elements.get(j + 1).toString());
//                if (hostMatcher.find()) {
//                    String host = hostMatcher.group();
//                    String port = portMatcher.find() ? portMatcher.group() : "8080";
//                    String protocol = "HTTP"; //unknown
//                    Proxy proxy = new Proxy(null, protocol, host, Integer.parseInt(port), false);
//                    proxies.add(proxy);
//                }
//            }
//        });
//
//        return proxies;
//    }
//}
