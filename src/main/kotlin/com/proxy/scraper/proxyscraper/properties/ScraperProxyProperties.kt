package com.proxy.scraper.proxyscraper.properties

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class ScraperProxyProperties(
    @Value("\${scraper.spysOne}")
    val spysOne: String,
    @Value("\${scraper.fplNet}")
    val fplNet: String,
    @Value("#{'\${scraper.proxyNova}'.split(',')}")
    val proxyNova: List<String>
)
