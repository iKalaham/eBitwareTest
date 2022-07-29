package com.kalaham.ebitwaretest.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface BitwareService {

    @GET("/jokes/random\n")
    suspend fun getJoke(): Response<ChuckJokes>

}