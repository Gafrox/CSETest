package com.example.csetest.navigation

import com.example.csetest.ui.addcar.AddCarFragment
import com.example.csetest.ui.carlist.CarListFragment
import com.example.csetest.ui.viewpicture.ViewPictureFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun cars() = FragmentScreen { CarListFragment() }

    fun viewPicture(id: Int) = FragmentScreen { ViewPictureFragment.newInstance(id) }

    fun addCar() = FragmentScreen { AddCarFragment() }

    fun editCar(id: Int) = FragmentScreen { AddCarFragment.newInstance(id) }
}