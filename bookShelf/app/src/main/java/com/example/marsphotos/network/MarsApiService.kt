/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.marsphotos.network

import com.example.marsphotos.model.GoogleBooksResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
//import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

//import retrofit2.http.Query

interface MarsApiService {
    // 書籍の一覧を取得
    @GET("volumes")
    suspend fun getBooks(
        @Query("q") query: String, // 検索キーワード
        @Query("maxResults") maxResults: Int = 30 // 最大取得件数
    ): GoogleBooksResponse
}
