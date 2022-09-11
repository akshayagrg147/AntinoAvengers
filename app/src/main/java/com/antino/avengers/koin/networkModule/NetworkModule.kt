package com.antino.avengers.koin.networkModule

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val NetworkModule = module {

    factory { provideGson() }
    factory { provideOkHttpClient() }
    single { provideRetrofit(get(), get()) }
}

fun provideGson(): Gson {
    return GsonBuilder()
        .setLenient()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
}

fun provideOkHttpClient() : OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(ApiResponseInterceptor())
        //.addInterceptor(UpdateApiUrlInterceptor())
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

}

fun provideRetrofit(factory: Gson, okHttpClient: OkHttpClient): Retrofit {

    return Retrofit.Builder()
        .baseUrl("https://2705-2401-4900-5d80-50ac-acc9-de0b-6aec-7fbb.in.ngrok.io")
        .client(okHttpClient)
        //.addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create(factory))
        .client(okHttpClient)
        .build()
}