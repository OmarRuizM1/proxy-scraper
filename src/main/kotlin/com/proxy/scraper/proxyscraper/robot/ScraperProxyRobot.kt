package com.proxy.scraper.proxyscraper.robot

import com.proxy.scraper.proxyscraper.manager.ScraperProxyManager
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScraperProxyRobot(private val scraperProxyManager: ScraperProxyManager) {

    @Scheduled(fixedRate = SCHEDULE_IN_MINUTES * 60000)
    fun run() {
        scraperProxyManager.scrapAllProxies()
    }

    companion object {
        const val SCHEDULE_IN_MINUTES: Long = 5
    }
}