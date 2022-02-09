package com.proxy.scraper.proxyscraper.scraper.constant;

import java.util.regex.Pattern;

public class ScraperRegexConstants {
    public static final Pattern HOST_REGEX = Pattern.compile("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b");
    public static final Pattern PORT_REGEX = Pattern.compile("\\d+");
}
