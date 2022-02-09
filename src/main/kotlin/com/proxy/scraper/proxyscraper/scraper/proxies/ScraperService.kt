package com.proxy.scraper.proxyscraper.scraper.proxies

import com.proxy.scraper.proxyscraper.dto.ProxyRequest

interface ScraperService {
    fun getProxies(): List<ProxyRequest>
}