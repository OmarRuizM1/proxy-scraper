package com.proxy.scraper.proxyscraper.configuration

import com.google.gson.GsonBuilder
import com.proxy.scraper.proxyscraper.api.ProxyApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Configuration
class ProxyConfig(@Value("\${proxyApiHost}") val proxyApiHost: String) {

    @Bean
    fun getProxyApi(): ProxyApi {
        return Retrofit.Builder()
            .baseUrl(proxyApiHost)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build().create(ProxyApi::class.java)
    }
}