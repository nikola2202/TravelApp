package com.example.travelapp.arch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelapp.data.Attraction

class AttractionViewModel: ViewModel() {

    private val repository = AttractionRepository()

    //HomeFragment
    val attractionListLiveData = MutableLiveData<List<Attraction>>()

    //AttractionDetailFragment
    val selectedAttractionLiveData = MutableLiveData<Attraction>()

    fun init(context: Context) {
        val attractionsList = repository.parseAttractions(context)
        attractionListLiveData.postValue(attractionsList)
    }

    fun onAttractionSelected(attractionId: String) {
        val attraction = attractionListLiveData.value?.find {
            it.id == attractionId
        } ?: return

        selectedAttractionLiveData.postValue(attraction)

    }

}