package com.example.travelapp.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.travelapp.R
import com.example.travelapp.arch.AttractionViewModel
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

    val viewModel: AttractionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfig = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,appBarConfig)

        viewModel.init(this)
        viewModel.locationSelectedLiveData.observe(this) { attraction ->
            val uri =
                Uri.parse("geo:${attraction.location.latitude},${attraction.location.longitude}?z=9&q=${attraction.title}")
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) ||  super.onSupportNavigateUp()
    }

}