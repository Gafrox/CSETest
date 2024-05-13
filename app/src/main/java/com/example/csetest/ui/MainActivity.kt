package com.example.csetest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.csetest.App
import com.example.csetest.R
import com.example.csetest.databinding.ActivityMainBinding
import com.example.csetest.db.CarDb
import com.example.csetest.repository.CarRepository
import com.example.csetest.repository.CarRepositoryImpl
import com.github.terrakok.cicerone.androidx.AppNavigator

lateinit var repository: CarRepository

class MainActivity : AppCompatActivity() {
    private val navigatorHolder = App.INSTANCE.navigatorHolder
    private val navigator = AppNavigator(this, R.id.container)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = CarRepositoryImpl(CarDb.getInstance(application).carDao())
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}