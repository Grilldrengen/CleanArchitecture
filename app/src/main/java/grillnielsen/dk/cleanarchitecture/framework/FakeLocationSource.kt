package grillnielsen.dk.cleanarchitecture.framework

import grillnielsen.dk.data.DeviceLocationSource
import grillnielsen.dk.domain.Location
import java.util.*

class FakeLocationSource : DeviceLocationSource {

    private val random = Random(System.currentTimeMillis())

    override fun getDeviceLocation(): Location =
        Location(random.nextDouble() * 180 - 90, random.nextDouble() * 360 - 180, Date().toString())
}