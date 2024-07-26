package com.example.sampleapp

import android.app.Application

class DiaryApplication : Application() {
    val database by lazy { DiaryRoomDatabase.getInstance(this) }
    val repository by lazy { DiaryRepository(database.diaryDao()) }
}