package com.example.marsphotos.fake

import com.example.marsphotos.data.NetworkMarsPhotosRepository
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.InternalSerializationApi
import org.junit.Test
import org.junit.Assert.assertEquals

class NetworkMarsRepositoryTest {
    @OptIn(InternalSerializationApi::class)
    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList() =
        runTest {
            val repository = NetworkMarsPhotosRepository(
                marsApiService = FakeMarsApiService(),
            )
            assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
        }
}