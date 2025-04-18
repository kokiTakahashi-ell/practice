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
package com.example.racetracker

import com.example.racetracker.ui.RaceParticipant
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals

class RaceParticipantTest {
    private val raceParticipant = RaceParticipant(
        name = "Test",
        maxProgress = 100,
        progressDelayMillis = 500L,
        initialProgress = 0,
        progressIncrement = 1
    )

    @Test
    fun raceParticipant_RaceStarted_ProgressUpdated() = runTest {
        val expectedProgress = 1
        launch { raceParticipant.run() }
        advanceTimeBy(raceParticipant.progressDelayMillis)
        runCurrent()
        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }


    @Test
    fun raceParticipant_RaceFinished_ProgressUpdated() = runTest {
        launch { raceParticipant.run() }
        advanceTimeBy(raceParticipant.maxProgress * raceParticipant.progressDelayMillis)
        runCurrent()
        assertEquals(100, raceParticipant.currentProgress)
    }

    @Test
    fun raceParticipant_RaceCompletesSuccessfully() = runTest {
        launch { raceParticipant.run() }
        advanceTimeBy(raceParticipant.maxProgress * raceParticipant.progressDelayMillis)
        runCurrent()
        assertEquals(raceParticipant.maxProgress, raceParticipant.currentProgress)
    }

    @Test(expected = IllegalArgumentException::class)
    fun raceParticipant_InvalidMaxProgress_ThrowsException() {
        RaceParticipant(name = "Invalid", maxProgress = 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun raceParticipant_InvalidProgressIncrement_ThrowsException() {
        RaceParticipant(name = "Invalid", progressIncrement = 0)
    }

    @Test
    fun raceParticipant_InitialProgressAtMax_NoProgressUpdate() = runTest {
        val participant = RaceParticipant(
            name = "Boundary",
            maxProgress = 100,
            initialProgress = 100
        )
        launch { participant.run() }
        advanceTimeBy(participant.progressDelayMillis)
        runCurrent()
        assertEquals(100, participant.currentProgress) // 進捗は更新されない
    }

    @Test
    fun raceParticipant_ProgressJustBeforeMax_UpdatesToMax() = runTest {
        val participant = RaceParticipant(
            name = "Boundary",
            maxProgress = 100,
            initialProgress = 99,
            progressIncrement = 1
        )
        launch { participant.run() }
        advanceTimeBy(participant.progressDelayMillis)
        runCurrent()
        assertEquals(100, participant.currentProgress) // 最大値に達する
    }
}
