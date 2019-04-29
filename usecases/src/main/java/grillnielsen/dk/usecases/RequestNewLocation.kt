package grillnielsen.dk.usecases

import grillnielsen.dk.data.LocationsRepository
import grillnielsen.dk.domain.Location

class RequestNewLocation(private val locationsRepository: LocationsRepository) {

    fun newLocation(): List<Location> = locationsRepository.requestNewLocation()

}