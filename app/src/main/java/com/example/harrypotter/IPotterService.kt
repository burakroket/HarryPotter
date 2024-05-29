package com.example.harrypotter

import retrofit2.Call
import retrofit2.http.GET

interface IPotterService {
    @GET("/v1/books")
    fun getBooks(): Call<String>
}