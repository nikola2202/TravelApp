package com.example.travelapp.arch

import android.content.Context
import com.example.travelapp.R
import com.example.travelapp.data.Attraction
import com.example.travelapp.data.AttractionsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class AttractionRepository {

    private val moshi = Moshi.Builder().addLast(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory()).build()

    fun parseAttractions(context: Context): ArrayList<Attraction> {

        val textFromFile =
            context.resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }
        val adapter: JsonAdapter<AttractionsResponse> =
            moshi.adapter(AttractionsResponse::class.java)

        return adapter.fromJson(textFromFile)!!.attractions as ArrayList<Attraction>

    }

}