package com.proxy.scraper.proxyscraper.dto

import com.google.gson.annotations.SerializedName

data class ProxyRequest(
    @SerializedName("protocol")
    val protocol: String,
    @SerializedName("host")
    val host: String,
    @SerializedName("port")
    val port: Int
)
