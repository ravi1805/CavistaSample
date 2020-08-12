package com.cavista.sample.data.remote.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IRemoteServiceApi {

//    https://api.imgur.com/3/gallery/search/{{sort}}/{{window}}/{{page}}?q=cats

    @GET("gallery/search/{page}")
    fun getSearchItems(@Path("page") page: Int, @Query("q") inputString: String): Call<ResponseBody>
}