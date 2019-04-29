package grillnielsen.dk.cleanarchitecture.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import grillnielsen.dk.cleanarchitecture.R

const val FINE_LOCATION_PERMISSION_REQ_CODE = 4


//Used to check if application have needed permissions
fun checkPermission(context: Context, reqCode: Int): Boolean {
    when(reqCode){
        FINE_LOCATION_PERMISSION_REQ_CODE -> {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
            return true
        }
        else -> return false
    }
}

//Used if application doesn't have the needed permissions to ask for it
fun requestPermissionForRead(context: Context): Boolean {
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, Manifest.permission.ACCESS_FINE_LOCATION)) {

            Snackbar.make(context.findViewById<View>(android.R.id.content), context.getString(R.string.permission_read_explanation), Snackbar.LENGTH_INDEFINITE)
                .setAction(context.getString(R.string.ok)) { ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), FINE_LOCATION_PERMISSION_REQ_CODE)}
                .show()
            return false
        }
        ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), FINE_LOCATION_PERMISSION_REQ_CODE)
        return false
    }
    return true
}