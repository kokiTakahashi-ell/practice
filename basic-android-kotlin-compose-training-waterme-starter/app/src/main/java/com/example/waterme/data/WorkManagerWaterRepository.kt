/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.waterme.data

import android.R.attr.data
import android.content.Context
import android.media.MediaFormat.KEY_DURATION
import android.net.Uri
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.Worker
import com.example.waterme.model.Plant
import com.example.waterme.worker.WaterReminderWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.TimeUnit
import kotlin.comparisons.then
import kotlin.jvm.java

class WorkManagerWaterRepository(context: Context) : WaterRepository {
    private val workManager = WorkManager.getInstance(context)
//    override val outputWorkInfo: Flow<WorkInfo?> = MutableStateFlow(null)

    override val plants: List<Plant>
        get() = DataSource.plants

    override fun scheduleReminder(duration: Long, unit: TimeUnit, plantName: String) {
//        var continuation = workManager.beginWith(OneTimeWorkRequest.from(WaterReminderWorker::class.java))
        val data = Data.Builder()
            .putString(WaterReminderWorker.nameKey, plantName)
            .putLong(WaterReminderWorker.durationKey, duration)
            .putString(WaterReminderWorker.unitKey, unit.name)
            .build()
        val workerReminderBuilder = OneTimeWorkRequestBuilder<WaterReminderWorker>()
            .setInputData(data)
            .setInitialDelay(duration, unit)
            .build()
        //起点の次であるBlurWorkerをqueueに追加
        workManager.enqueueUniqueWork(
            plantName,
            ExistingWorkPolicy.REPLACE,
            workerReminderBuilder
        )
    }
}
