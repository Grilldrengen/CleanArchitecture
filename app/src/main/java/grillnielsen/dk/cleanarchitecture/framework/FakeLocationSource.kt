package grillnielsen.dk.cleanarchitecture.framework

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import grillnielsen.dk.data.DeviceLocationSource
import grillnielsen.dk.domain.Location as DomainLocation

import java.util.*

class FakeLocationSource : DeviceLocationSource {

    private val random = Random(System.currentTimeMillis())

    override fun getDeviceLocation(): DomainLocation {

        return DomainLocation(random.nextDouble() * 180 - 90, random.nextDouble() * 360 - 180, Date().toString())
    }
}