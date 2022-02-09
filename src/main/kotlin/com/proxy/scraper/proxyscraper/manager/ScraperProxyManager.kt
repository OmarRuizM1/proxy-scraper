package com.proxy.scraper.proxyscraper.manager

import com.proxy.scraper.proxyscraper.api.ProxyApi
import com.proxy.scraper.proxyscraper.dto.ProxyRequest
import com.proxy.scraper.proxyscraper.scraper.proxies.ScraperService
import java.util.*
import org.springframework.stereotype.Component

@Component
class ScraperProxyManager(private val scraperService: List<ScraperService>, private val proxyApi: ProxyApi) {

    fun scrapAllProxies(): ArrayList<ProxyRequest> {
        val result = ArrayList<ProxyRequest>()
        scraperService.forEach { scraper: ScraperService -> result.addAll(scraper.getProxies()) }
        println("Getting all free proxies: " + result.size)
        proxyApi.saveAll(result).execute().body()
        return result
    }
}