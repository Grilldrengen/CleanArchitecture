package grillnielsen.dk.cleanarchitecture.ui

import androidx.lifecycle.ViewModel
import grillnielsen.dk.domain.Name as DomainName
import grillnielsen.dk.usecases.GetNames
import grillnielsen.dk.usecases.RequestNewName
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(private var view: View?, private val getNames: GetNames, private val requestNewName: RequestNewName) : ViewModel() {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    fun onCreate() = scope.launch(Dispatchers.Main) {

        val names = {
            getNames.names()
        }

        view?.renderNames(names.invoke())
    }

    fun newNameClicked() = scope.launch(Dispatchers.Main) {

        val names = {
            requestNewName.newName()
        }

        view?.renderNames(names.invoke())
    }

    fun onDestroy() {
        parentJob.cancel()
        view = null
    }
}

interface View {
    fun renderNames(names: List<DomainName>)
}