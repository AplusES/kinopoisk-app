package com.example.skillcinema.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Объект для создания и настройки Retrofit клиента.
 * Обеспечивает единую точку доступа к API Кинопоиска.
 */
object RetrofitClient {
    private const val BASE_URL = "https://kinopoiskapiunofficial.tech"
    private const val API_KEY = "bae658da-6864-444d-94f9-2935bfed5c18"

    /**
     * Настройка HTTP клиента с перехватчиком для добавления API ключа.
     */
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor()) // Добавляем кастомный интерсептор для API ключа
        .build()

    /**
     * Кастомный перехватчик для добавления API ключа в заголовок запроса.
     */
    private class ApiKeyInterceptor : Interceptor {
        /**
         * Перехватывает запрос и добавляет к нему API ключ в заголовок.
         *
         * @param chain Цепочка вызовов интерсепторов
         * @return Ответ на запрос
         */
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("X-API-KEY",API_KEY)
                .build()
            return chain.proceed(request)
        }

    }

    /**
     * Настройка Retrofit с базовым URL, HTTP клиентом и конвертером JSON.
     */
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Экземпляр API Кинопоиска, созданный на основе Retrofit.
     * Позволяет выполнять запросы к API, определенные в интерфейсе KinopoiskApi.
     */
    val kinopoiskApi: KinopoiskApi = retrofit.create(KinopoiskApi::class.java)

}

