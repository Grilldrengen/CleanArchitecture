package grillnielsen.dk.cleanarchitecture.framework

import grillnielsen.dk.cleanarchitecture.framework.dao.LocationDao
import grillnielsen.dk.data.LocationPersistenceSource
import grillnielsen.dk.domain.Location

class InMemoryLocationPersistenceSource(private val locationDao: LocationDao): LocationPersistenceSource {

    override fun getPersistedLocations(): List<Location> = locationDao.getPersistedLocations()

    override fun saveNewLocation(location: Location) {
        locationDao.saveNewLocation(location)
    }
}