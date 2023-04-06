package com.example.travelapp.ui.fragment.home

import com.airbnb.epoxy.EpoxyController
import com.example.travelapp.R
import com.example.travelapp.data.Attraction
import com.example.travelapp.databinding.ViewHolderAttractionBinding
import com.example.travelapp.ui.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class HomeFragmentController(
    private val onClickedCallback: (String) -> Unit
): EpoxyController() {

    var isLoading: Boolean = false
        set(value) {
            field = value
            if(field) {
                requestModelBuild()
            }
        }

    var attractions = ArrayList<Attraction>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    override fun buildModels() {
        if(isLoading) {
            //todo
            return
        }
        if(attractions.isEmpty()) {
            //todo
            return
        }
        attractions.forEach { attraction ->
            AttractionEpoxyModel(attraction,onClickedCallback)
                .id(attraction.id)
                .addTo(this)
        }
    }

    data class AttractionEpoxyModel(
        val attraction: Attraction,
        val onClicked: (String) -> Unit
    ):ViewBindingKotlinModel<ViewHolderAttractionBinding>(R.layout.view_holder_attraction) {
        override fun ViewHolderAttractionBinding.bind() {
            titleTextView.text = attraction.title
            Picasso.get().load(attraction.image_url).into(headerImageView)
            monthsToVisitTextView.text = attraction.months_to_visit

            root.setOnClickListener {
                onClicked(attraction.id)
            }
        }
    }

}