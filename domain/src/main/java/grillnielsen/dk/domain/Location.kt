package grillnielsen.dk.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "location_table")
data class Location (
    val latitude: Double,
    val longitude: Double,
    @PrimaryKey
    val date: String
    )