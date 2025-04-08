package com.example.marsphotos.fake

import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.network.MarsApiService
import kotlinx.serialization.InternalSerializationApi

class FakeMarsApiService : MarsApiService {
    @OptIn(InternalSerializationApi::class)
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}