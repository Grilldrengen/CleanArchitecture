package grillnielsen.dk.cleanarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import grillnielsen.dk.cleanarchitecture.R
import grillnielsen.dk.cleanarchitecture.framework.FakeLocationSource
import grillnielsen.dk.cleanarchitecture.framework.InMemoryLocationPersistenceSource
import grillnielsen.dk.cleanarchitecture.framework.LocationRoomDatabase
import grillnielsen.dk.cleanarchitecture.framework.dao.LocationDao
import grillnielsen.dk.data.LocationsRepository
import grillnielsen.dk.usecases.GetLocations
import grillnielsen.dk.usecases.RequestNewLocation
import kotlinx.android.synthetic.main.activity_main.*
import grillnielsen.dk.domain.Location

class MainActivity : AppCompatActivity() {

    private val adapter: LocationsAdapter = LocationsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.adapter = adapter

        val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        //renderLocations(mainViewModel.locations)

        //newLocationBtn.setOnClickListener { renderLocations(mainViewModel.newLocation) }
    }

    private fun renderLocations(locations: List<Location>) {
        adapter.setLocations(locations)
    }
}
