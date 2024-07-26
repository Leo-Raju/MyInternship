package com.example.sampleapp

import kotlinx.coroutines.flow.Flow

class DiaryRepository(private val diaryDao: DiaryDao) {
    fun getEntriesById(id: Int): Flow<List<Diary>> = diaryDao.getEntriesById(id)
    suspend fun insert(diary: Diary) {
        diaryDao.insert(diary)
    }
    suspend fun update(diary: Diary) {
        diaryDao.update(diary)
    }
    suspend fun delete(diary: Diary) {
        diaryDao.delete(diary)
    }
}