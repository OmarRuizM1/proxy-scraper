package com.proxy.scraper.proxyscraper.api

import com.proxy.scraper.proxyscraper.dto.Proxy
import com.proxy.scraper.proxyscraper.dto.ProxyRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProxyApi {

    @POST("/proxy/api/save")
    fun save(@Body proxy: ProxyRequest): Call<Void>

    @POST("/proxy/api/saveAll")
    fun saveAll(@Body proxy: List<ProxyRequest>): Call<Void>

    @POST("/proxy/api/saveByUrl")
    fun saveByUrl(@Body url: String): Call<Void>

    @GET("/proxy/api/findNotUsedProxy")
    fun findNotUsedProxy(): Call<Proxy?>

    @GET("/proxy/api/findAllNotUsed")
    fun findAllNotUsed(): Call<List<Proxy?>?>

    @POST("/proxy/api/updateUsedProxy")
    fun updateUsedProxy(@Body proxy: Proxy): Call<Void>

}