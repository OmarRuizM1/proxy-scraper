package com.proxy.scraper.proxyscraper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ProxyScraperApplication

fun main(args: Array<String>) {
	runApplication<ProxyScraperApplication>(*args)
}
