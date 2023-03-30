package com.example.travelapp.ui.fragment

import androidx.fragment.app.Fragment
import com.example.travelapp.data.Attraction
import com.example.travelapp.ui.MainActivity

abstract class BaseFragment: Fragment() {

    protected val navController by lazy {
        (activity as MainActivity).navController
    }

    protected val attractions: List<Attraction>
        get() = (activity as MainActivity).attractionsList

}