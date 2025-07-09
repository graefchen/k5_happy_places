package com.example.places

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

// Note: The Requirements are title, description und picture as well as a note.
//       The picture and note will be added later ...
// Using the Room Database:
// https://developer.android.com/training/data-storage/room
@Entity
data class MapMarker(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "lat") val lat: Double,
    @ColumnInfo(name = "long") val long: Double
)

/**
 * The [MapMarker] Data Access Object
 */
@Dao
interface MapMarkerDao {
    /**
     * Getting all [MapMarker] from the [AppDatabase].
     */
    @Query("SELECT * FROM MapMarker")
    fun getAll(): List<MapMarker>

    /**
     * Inserting [MapMarker] into the [AppDatabase].
     */
    @Insert
    fun insertAll(vararg mapMarkers: MapMarker)

    /**
     * Deleting the whole [AppDatabase].
     */
    @Delete
    fun delete(markMarker: MapMarker)
}

@Database(entities = [MapMarker::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mapMarkerDao(): MapMarkerDao

    // The Singleton Instance of the DataBase
    // From:
    // https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room#6
    companion object Instance {
        @Volatile
        private var Instance: AppDatabase? = null

        /**
         * Returning the [AppDatabase].
         */
        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "markers")
                    .allowMainThreadQueries().build().also { Instance = it }
            }
        }
    }
}