package com.example.busschedule.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.BusApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for HomeViewModel
        initializer {
            BusScheduleViewModel(/*this.createSavedStateHandle()*/busApplication().container.busRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.busApplication(): BusApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as BusApplication)
