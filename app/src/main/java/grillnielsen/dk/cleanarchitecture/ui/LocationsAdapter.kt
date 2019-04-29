package grillnielsen.dk.cleanarchitecture.ui

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import grillnielsen.dk.cleanarchitecture.R
import kotlinx.android.synthetic.main.view_location_item.view.*
import grillnielsen.dk.domain.Location

class LocationsAdapter : RecyclerView.Adapter<LocationsAdapter.Holder>() {

    private var locations = emptyList<Location>()

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val itemData = locations[position]
        holder.bind(itemData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflatedView = parent.inflate(R.layout.view_location_item)
        return Holder(inflatedView)
    }

    override fun getItemCount() = locations.size

    internal fun setLocations(locations: List<Location>) {
        this.locations = locations
        notifyDataSetChanged()
    }

    inner class Holder(private var view: View) : RecyclerView.ViewHolder(view) {

        private var holderData: Location? = null

        @SuppressLint("SetTextI18n")
        fun bind(holderData: Location) {
            this.holderData = holderData
            view.locationCoordinates.text = holderData.latitude.toString() + " " + holderData.longitude.toString()
            view.locationDate.text = holderData.date.toString()

        }
    }
}