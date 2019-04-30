package grillnielsen.dk.usecases

import grillnielsen.dk.data.NamesRepository
import grillnielsen.dk.domain.Name as DomainName

class RequestNewName(private val namesRepository: NamesRepository) {

    fun newName(): List<DomainName> = namesRepository.requestNewName()

}