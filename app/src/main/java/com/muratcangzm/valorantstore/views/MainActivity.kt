package com.muratcangzm.valorantstore.views

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.muratcangzm.valorantstore.R
import com.muratcangzm.valorantstore.databinding.ActivityMainBinding
import com.muratcangzm.valorantstore.utils.BuildConfig
import com.muratcangzm.valorantstore.viewmodels.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val viewModel: DataViewModel by viewModels()

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)


        viewModel.allModelLiveData.observe(this, Observer {

            Timber.tag("Vericik").d("${it[0]}")

            binding.bottomNavigation.visibility = View.VISIBLE
            binding.fragmentContainerView.visibility = View.VISIBLE
            binding.loadingScreen.loadingScreenLayout.visibility = View.GONE


        })


    }


}