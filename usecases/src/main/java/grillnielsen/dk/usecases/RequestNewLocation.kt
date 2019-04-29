package grillnielsen.dk.usecases

import grillnielsen.dk.data.LocationsRepository
import grillnielsen.dk.domain.Location as DomainLocation

class RequestNewLocation(private val locationsRepository: LocationsRepository) {

    fun newLocation(): List<DomainLocation> = locationsRepository.requestNewLocation()

}