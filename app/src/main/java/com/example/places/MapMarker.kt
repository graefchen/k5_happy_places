package com.example.places

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import org.osmdroid.util.GeoPoint

// Note: The Requirements are title, description und picture as well as a note.
//       The picture and note will be added later ...
// Using the Room Database:
// https://developer.android.com/training/data-storage/room
@Entity
data class MapMarker(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "lat") val lat : Double,
    @ColumnInfo(name = "long") val long : Double
)

@Dao
interface MapMarkerDao {
    @Query("SELECT * FROM MapMarker")
    fun getAll(): List<MapMarker>

    @Insert
    fun insertAll(vararg mapMarkers: MapMarker)

    @Delete
    fun delete(markMarker: MapMarker)
}

@Database(entities = [MapMarker::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mapMarkerDao(): MapMarkerDao
}