package com.proxy.scraper.proxyscraper.scraper.proxies;

import com.proxy.scraper.proxyscraper.dto.ProxyRequest;
import com.proxy.scraper.proxyscraper.properties.ScraperProxyProperties;
import com.proxy.scraper.proxyscraper.scraper.connection.ScraperDocument;
import com.proxy.scraper.proxyscraper.scraper.constant.ScraperRegexConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FPLNetImpl implements ScraperService {

    private static final Pattern HTTPS_REGEX = Pattern.compile(">yes<");

    @Autowired
    private ScraperProxyProperties scraperProxyProperties;

    @Override
    public List<ProxyRequest> getProxies() {
        List<ProxyRequest> proxies = new ArrayList<>();
        String url = scraperProxyProperties.getFplNet();
        Document document = ScraperDocument.Companion.getDocFromUrl(url);
        if (document != null) {
            Elements elements = document.body().getElementsByTag("td");
            for (int i = 0; i < elements.size() - 6; i++) {
                String td = elements.get(i).toString();
                Matcher hostMatcher = ScraperRegexConstants.HOST_REGEX.matcher(td);
                if (hostMatcher.find()) {
                    Matcher portMatcher = ScraperRegexConstants.PORT_REGEX.matcher(elements.get(i + 1).toString());
                    String protocol = HTTPS_REGEX.matcher(elements.get(i + 6).toString()).find() ? "HTTPS" : "HTTP";
                    String host = hostMatcher.group();
                    String port = portMatcher.find() ? portMatcher.group() : "8080";
                    ProxyRequest proxyRequest = new ProxyRequest(protocol, host, Integer.parseInt(port));
                    proxies.add(proxyRequest);
                }
            }
        }
        return proxies;
    }
}

