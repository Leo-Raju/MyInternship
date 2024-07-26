package com.example.sampleapp

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.launch

class DiaryViewModel(private val repository: DiaryRepository, application: DiaryApplication) : AndroidViewModel(application) {

    fun getEntriesById(id: Int): LiveData<List<Diary>> {
        return repository.getEntriesById(id).asLiveData()
    }


    fun insert(diary: Diary) = viewModelScope.launch {
        repository.insert(diary)
    }

    fun update(diary: Diary) = viewModelScope.launch {
        repository.update(diary)
    }

    fun delete(diary: Diary) = viewModelScope.launch {
        repository.delete(diary)
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    fun getCoordinates(context: Context): Location? {
        if (this::fusedLocationClient.isInitialized) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        }
        val priority = Priority.PRIORITY_BALANCED_POWER_ACCURACY
        val result = fusedLocationClient.getCurrentLocation(priority, null).result
        return result
    }
}

    class DiaryViewModelFactory(private val repository: DiaryRepository, private val application: DiaryApplication) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DiaryViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DiaryViewModel(repository, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }