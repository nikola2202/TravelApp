package com.example.travelapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.travelapp.R
import com.example.travelapp.data.Attraction
import com.example.travelapp.data.AttractionsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.net.Proxy

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private lateinit var appBarConfig: AppBarConfiguration

    val attractionsList: List<Attraction> by lazy {
        parseAttractions()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfig = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,appBarConfig)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) ||  super.onSupportNavigateUp()
    }

    private fun parseAttractions(): List<Attraction> {

        val textFromFile = resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }

        val moshi = Moshi.Builder().addLast(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory()).build()

        val type = Types.newParameterizedType(List::class.java, Attraction::class.java)
        val adapter: JsonAdapter<AttractionsResponse> = moshi.adapter(AttractionsResponse::class.java)
        return adapter.fromJson(textFromFile)!!.attractions

    }

}