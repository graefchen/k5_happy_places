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
import androidx.room.Update

// Note: The Requirements are title, description und picture as well as a note.
//       The picture and note will be added later ...
// Using the Room Database:
// https://developer.android.com/training/data-storage/room
/**
 * The [MapMarker] data class.
 * @param id The id of the MapMarker, is automatically incremented and handled by [Room]
 * @param title The Title of the Marker
 * @param description A short description for the Marker
 * @param lat The Latitude Position of the Marker
 * @param long The Longitude Position of the Marker
 */
@Entity
data class MapMarker(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,
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

    @Query("SELECT * FROM MapMarker WHERE id = :id")
    fun getByID(id: Int): MapMarker

    /**
     * Inserting [MapMarker] into the [AppDatabase].
     */
    @Insert
    fun insert(vararg mapMarkers: MapMarker)

    /**
     * Updating an [MapMarker], is automatically handled and uses the [PrimaryKey] of the [MapMarker] Object.
     */
    @Update
    fun update(mapMarker: MapMarker)

    /**
     * Deleting the a single [MapMarker] from the [AppDatabase].
     */
    @Delete
    fun delete(markMarker: MapMarker)

    /**
     * Deleting an [MapMarker] from the [AppDatabase] by ID
     */
    @Query("DELETE FROM MapMarker WHERE id = :id")
    fun deleteById(id: Int)

    /**
     * Only to be used in testing!
     */
    @Query("DELETE FROM MapMarker")
    fun deleteAll()
}

@Database(entities = [MapMarker::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mapMarkerDao(): MapMarkerDao

    /**
     * The Singleton Instance of the DataBase.
     * Source code taken and adapted from
     * [android code labs](https://developer.android.com/codelabs/basic-android-kotlin-compose-persisting-data-room#6)
     */
    companion object Instance {
        @Volatile
        private var Instance: AppDatabase? = null

        /**
         * Returning the [AppDatabase]. Can be either called from the [Instance] or from the
         * [AppDatabase] by itself.
         * Sets the Instance if it has not been set already or just return the Instance.
         *
         * @param context The context of the current Activity.
         */
        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "markers")
                    .allowMainThreadQueries().build().also { Instance = it }
            }
        }
    }
}