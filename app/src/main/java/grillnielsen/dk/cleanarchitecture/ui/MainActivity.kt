package grillnielsen.dk.cleanarchitecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import grillnielsen.dk.cleanarchitecture.R
import grillnielsen.dk.cleanarchitecture.framework.FakeNameSource
import grillnielsen.dk.cleanarchitecture.framework.InMemoryNamePersistenceSource
import grillnielsen.dk.data.NamesRepository
import kotlinx.android.synthetic.main.activity_main.*
import grillnielsen.dk.domain.Name as DomainName
import grillnielsen.dk.usecases.GetNames
import grillnielsen.dk.usecases.RequestNewName

class MainActivity : AppCompatActivity(), View {

    private val adapter: NamesAdapter = NamesAdapter()
    private val viewModel: MainViewModel

    init {
        val persistence = InMemoryNamePersistenceSource()
        val deviceName = FakeNameSource(persistence)
        val namesRepository = NamesRepository(persistence, deviceName)
        viewModel = MainViewModel(
            this,
            GetNames(namesRepository),
            RequestNewName(namesRepository)
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

        newNameBtn.setOnClickListener {

            viewModel.newNameClicked()
        }
    }

    override fun renderNames(names: List<DomainName>) {
        adapter.setNames(names)
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }
}
