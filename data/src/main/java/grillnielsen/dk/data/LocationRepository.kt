package grillnielsen.dk.data

import grillnielsen.dk.domain.Location as DomainLocation

class LocationsRepository(private val locationPersistenceSource: LocationPersistenceSource, private val deviceLocationSource: DeviceLocationSource) {

    fun getSavedLocations(): List<DomainLocation> = locationPersistenceSource.getPersistedLocations()

    fun requestNewLocation(): List<DomainLocation> {
        val newLocation = deviceLocationSource.getDeviceLocation()
        locationPersistenceSource.saveNewLocation(newLocation)
        return getSavedLocations()
    }
}

interface LocationPersistenceSource {

    fun getPersistedLocations(): List<DomainLocation>
    fun saveNewLocation(location: DomainLocation)
}

interface DeviceLocationSource {

    fun getDeviceLocation(): DomainLocation
}