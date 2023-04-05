package com.example.travelapp.ui.fragment

import androidx.fragment.app.Fragment
import com.example.travelapp.arch.AttractionViewModel
import com.example.travelapp.data.Attraction
import com.example.travelapp.ui.MainActivity

abstract class BaseFragment: Fragment() {

    protected val navController by lazy {
        (activity as MainActivity).navController
    }

    protected val activityViewModel: AttractionViewModel
        get() = (activity as MainActivity).viewModel

}