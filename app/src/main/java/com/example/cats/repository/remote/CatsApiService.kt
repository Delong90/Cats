package com.example.cats.repository.remote

import com.example.cats.model.CatImageModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApiService {
    @GET("/v1/images/search")
    suspend fun getCatsImages(@Query("page") page: Int, @Query("limit") size: Int): List<CatImageModel>
}