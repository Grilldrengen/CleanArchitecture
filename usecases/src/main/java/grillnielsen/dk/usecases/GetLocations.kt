package grillnielsen.dk.usecases

import grillnielsen.dk.data.LocationsRepository
import grillnielsen.dk.domain.Location as DomainLocation

class GetLocations(private val locationsRepository: LocationsRepository) {

    fun locations(): List<DomainLocation> = locationsRepository.getSavedLocations()

}