package grillnielsen.dk.cleanarchitecture.ui

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import grillnielsen.dk.cleanarchitecture.R
import kotlinx.android.synthetic.main.view_location_item.view.*
import grillnielsen.dk.domain.Location as DomainLocation

class LocationsAdapter : RecyclerView.Adapter<LocationsAdapter.Holder>() {

    private var locations = emptyList<DomainLocation>()

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val itemData = locations[position]
        holder.bind(itemData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflatedView = parent.inflate(R.layout.view_location_item)
        return Holder(inflatedView)
    }

    override fun getItemCount() = locations.size

    internal fun setLocations(locations: List<DomainLocation>) {
        this.locations = locations
        notifyDataSetChanged()
    }

    inner class Holder(private var view: View) : RecyclerView.ViewHolder(view) {

        private var holderData: DomainLocation? = null

        @SuppressLint("SetTextI18n")
        fun bind(holderData: DomainLocation) {
            this.holderData = holderData
            view.locationCoordinates.text = holderData.latitude.toString() + " " + holderData.longitude.toString()
            view.locationDate.text = holderData.date.toString()

        }
    }
}