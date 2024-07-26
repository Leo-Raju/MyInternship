package com.example.sampleapp

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.flow.Flow


@Entity(tableName = "entries")
data class Diary(
    val content: String,
    val time : Long,
    var latitude : Double,
    var longitude : Double,
    @PrimaryKey(autoGenerate = true)val id: Int = 0,
)

@Dao
interface DiaryDao {
    @Insert
    suspend fun insert(diary: Diary)

    @Update
    suspend fun update(diary: Diary)

    @Delete
    suspend fun delete(diary: Diary)

    @Query("SELECT * FROM entries")
    fun getAllEntries(): Flow<List<Diary>>

    @Query("SELECT * FROM entries WHERE id = :id")
    fun getEntriesById(id: Int): Flow<List<Diary>>
}

@Database(entities = [Diary::class], version = 1)
abstract class DiaryRoomDatabase : RoomDatabase() {
    abstract fun diaryDao(): DiaryDao

    companion object {
        @Volatile
        private var INSTANCE: DiaryRoomDatabase? = null

        fun getInstance(context: Context): DiaryRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DiaryRoomDatabase::class.java,
                    "diary_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(DiaryDatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }
        private class DiaryDatabaseCallback() : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}

