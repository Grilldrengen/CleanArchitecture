package grillnielsen.dk.cleanarchitecture.framework

import grillnielsen.dk.data.DeviceNameSource
import grillnielsen.dk.domain.Name as DomainName
import java.util.Calendar.*
import java.util.GregorianCalendar

class FakeNameSource(private val names: InMemoryNamePersistenceSource) : DeviceNameSource {

        private fun generateBirthday(): String {

            val gc = GregorianCalendar()

            val year = randBetween(1900, 2000)

            gc.set(YEAR, year)

            val dayOfYear = randBetween(1, gc.getActualMaximum(DAY_OF_YEAR))

            gc.set(DAY_OF_YEAR, dayOfYear)

            return gc.get(YEAR).toString() + "-" + (gc.get(MONTH) + 1) + "-" + gc.get(DAY_OF_MONTH)
        }

        private fun randBetween(start: Int, end: Int): Int {
            return start + Math.round(Math.random() * (end - start)).toInt()
        }


    override fun getDeviceName(): DomainName {

        return DomainName(names.firstNames.random() + " " + names.lastNames.random(), generateBirthday())
    }
}