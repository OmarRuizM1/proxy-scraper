package com.proxy.scraper.proxyscraper.scraper.connection

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class ScraperDocument {
    companion object {
        fun getDocFromUrl(url: String?): Document? {
            try {
                return Jsoup.connect(url).userAgent("Chrome").get()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }
}