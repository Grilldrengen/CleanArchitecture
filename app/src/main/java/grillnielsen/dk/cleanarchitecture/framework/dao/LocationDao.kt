package grillnielsen.dk.cleanarchitecture.framework.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import grillnielsen.dk.domain.Location

@Dao
interface LocationDao {

    @Query("SELECT * from location_table ORDER BY date ASC")
    fun getPersistedLocations(): List<Location>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNewLocation(location: Location)

}