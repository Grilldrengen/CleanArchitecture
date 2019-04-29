package grillnielsen.dk.usecases

import grillnielsen.dk.data.LocationsRepository
import grillnielsen.dk.domain.Location

class GetLocations(private val locationsRepository: LocationsRepository) {

    fun locations(): List<Location> = locationsRepository.getSavedLocations()

}