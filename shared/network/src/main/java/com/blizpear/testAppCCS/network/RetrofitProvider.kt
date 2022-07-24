package com.blizpear.testAppCCS.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitProvider {

	private const val BASE_URL = "https://api.fer.ee/"

	private val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

	private val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

	private val moshi: Moshi = Moshi.Builder()
		.add(KotlinJsonAdapterFactory())
		.build()

	val retrofit: Retrofit = Retrofit.Builder()
		.baseUrl(BASE_URL)
		.addConverterFactory(MoshiConverterFactory.create(moshi))
		.client(okHttpClient)
		.build()
}