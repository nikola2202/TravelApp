package com.example.travelapp.ui.fragment

import androidx.fragment.app.Fragment
import com.example.travelapp.ui.MainActivity

abstract class BaseFragment: Fragment() {

    protected val navController by lazy {
        (activity as MainActivity).navController
    }

}