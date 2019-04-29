package grillnielsen.dk.cleanarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import grillnielsen.dk.cleanarchitecture.R
import grillnielsen.dk.cleanarchitecture.framework.FakeLocationSource
import grillnielsen.dk.cleanarchitecture.framework.InMemoryLocationPersistenceSource
import grillnielsen.dk.data.LocationsRepository
import kotlinx.android.synthetic.main.activity_main.*
import grillnielsen.dk.domain.Location
import grillnielsen.dk.usecases.GetLocations
import grillnielsen.dk.usecases.RequestNewLocation

class MainActivity : AppCompatActivity(), View {

    private val adapter: LocationsAdapter = LocationsAdapter()
    private val viewModel: MainViewModel

    init {
        val persistence = InMemoryLocationPersistenceSource()
        val deviceLocation = FakeLocationSource()
        val locationsRepository = LocationsRepository(persistence, deviceLocation)
        viewModel = MainViewModel(
            this,
            GetLocations(locationsRepository),
            RequestNewLocation(locationsRepository)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.adapter = adapter

        viewModel.onCreate()

        newLocationBtn.setOnClickListener {

            viewModel.newLocationClicked()
        }
    }

    override fun renderLocations(locations: List<Location>) {
        adapter.setLocations(locations)
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }
}
