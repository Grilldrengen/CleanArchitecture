package grillnielsen.dk.cleanarchitecture.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import grillnielsen.dk.cleanarchitecture.framework.FakeLocationSource
import grillnielsen.dk.cleanarchitecture.framework.InMemoryLocationPersistenceSource
import grillnielsen.dk.cleanarchitecture.framework.LocationRoomDatabase
import grillnielsen.dk.data.LocationsRepository
import grillnielsen.dk.usecases.GetLocations
import grillnielsen.dk.usecases.RequestNewLocation
import grillnielsen.dk.domain.Location
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val getLocations: GetLocations
    private val requestNewLocation: RequestNewLocation

    init {
        val locationDao = LocationRoomDatabase.getDatabase(application).locationDao()
        val persistence = InMemoryLocationPersistenceSource(locationDao)
        val deviceLocation = FakeLocationSource()
        val locationsRepository = LocationsRepository(persistence, deviceLocation)

        getLocations = GetLocations(locationsRepository)
        requestNewLocation = RequestNewLocation(locationsRepository)
    }

    val locations = runBlocking(Dispatchers.Main) {
        getLocations.locations()
    }

    val newLocation = runBlocking(Dispatchers.Main) {
        requestNewLocation.newLocation()
    }
}