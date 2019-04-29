package grillnielsen.dk.cleanarchitecture.framework

import grillnielsen.dk.data.LocationPersistenceSource
import grillnielsen.dk.domain.Location

class InMemoryLocationPersistenceSource: LocationPersistenceSource {

    private var locations: List<Location> = emptyList()

    override fun getPersistedLocations(): List<Location> = locations

    override fun saveNewLocation(location: Location) {
        locations += location
    }
}