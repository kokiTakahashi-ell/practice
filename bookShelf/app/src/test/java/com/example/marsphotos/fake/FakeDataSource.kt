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
package com.example.marsphotos.fake

import com.example.marsphotos.fake.FakeDataSource.booksList
import com.example.marsphotos.fake.FakeDataSource.thumbnailOne
import com.example.marsphotos.fake.FakeDataSource.thumbnailTwo
import com.example.marsphotos.model.BookItem
import com.example.marsphotos.model.GoogleBooksResponse
import com.example.marsphotos.model.ImageLinks
import com.example.marsphotos.model.VolumeInfo


object FakeDataSource {

    private const val idOne = "book1"
    private const val idTwo = "book2"
    private const val thumbnailOne = "url.one"
    private const val thumbnailTwo = "url.two"

    val booksList = listOf(
        BookItem(
            id = idOne,
            volumeInfo = VolumeInfo(
                imageLinks = ImageLinks(
                    thumbnail = thumbnailOne
                )
            )
        ),
        BookItem(
            id = idTwo,
            volumeInfo = VolumeInfo(
                imageLinks = ImageLinks(
                    thumbnail = thumbnailTwo
                )
            )
        )
    )

    val googleBooksResponse = GoogleBooksResponse(
        items = booksList
    )
}
