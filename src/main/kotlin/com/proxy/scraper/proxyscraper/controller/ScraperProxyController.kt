package com.proxy.scraper.proxyscraper.controller

import com.proxy.scraper.proxyscraper.dto.ProxyRequest
import com.proxy.scraper.proxyscraper.manager.ScraperProxyManager
import java.util.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/scrap")
class ScraperProxyController(private val scraperProxyManager: ScraperProxyManager) {

    @GetMapping("/allProxies")
    fun scrapAllProxies(): ArrayList<ProxyRequest> {
        return scraperProxyManager.scrapAllProxies()
    }
}