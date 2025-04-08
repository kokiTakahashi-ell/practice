package com.example.marsphotos.fake

import com.example.marsphotos.model.MarsPhoto
import kotlinx.serialization.InternalSerializationApi

object FakeDataSource {

    const val idOne = "img1"
    const val idTwo = "img2"
    const val imgOne = "url.1"
    const val imgTwo = "url.2"
    @OptIn(InternalSerializationApi::class)
    val photosList = listOf(
        MarsPhoto(
            id = idOne,
            imgSrc = imgOne
        ),
        MarsPhoto(
            id = idTwo,
            imgSrc = imgTwo
        )
    )
}