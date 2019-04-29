package grillnielsen.dk.cleanarchitecture.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import grillnielsen.dk.cleanarchitecture.framework.FakeLocationSource
import grillnielsen.dk.cleanarchitecture.framework.InMemoryLocationPersistenceSource
import grillnielsen.dk.data.LocationsRepository
import grillnielsen.dk.domain.Location as DomainLocation
import grillnielsen.dk.usecases.GetLocations
import grillnielsen.dk.usecases.RequestNewLocation
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(private var view: View?, private val getLocations: GetLocations, private val requestNewLocation: RequestNewLocation) : ViewModel() {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    fun onCreate() = scope.launch(Dispatchers.Main) {

        val locations = {
            getLocations.locations()
        }

        view?.renderLocations(locations.invoke())
    }

    fun newLocationClicked() = scope.launch(Dispatchers.Main) {

        val locations = {
            requestNewLocation.newLocation()
        }

        view?.renderLocations(locations.invoke())
    }

    fun onDestroy() {
        parentJob.cancel()
        view = null
    }
}

interface View {
    fun renderLocations(locations: List<DomainLocation>)
}