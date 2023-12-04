package com.muratcangzm.valorantstore.views

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.muratcangzm.valorantstore.R
import com.muratcangzm.valorantstore.databinding.ActivityMainBinding
import com.muratcangzm.valorantstore.utils.BuildConfig
import com.muratcangzm.valorantstore.utils.NetworkUtils
import com.muratcangzm.valorantstore.utils.listeners.NetworkChangeListener
import com.muratcangzm.valorantstore.viewmodels.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val viewModel: DataViewModel by viewModels()
    private val networkListener = NetworkChangeListener()

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

        if (NetworkUtils.isInternetAvailable(this)) {

            viewModel.allModelLiveData.observe(this, Observer {


                Timber.tag("Vericik").d("${it[0]}")

                binding.bottomNavigation.visibility = View.VISIBLE
                binding.fragmentContainerView.visibility = View.VISIBLE
                binding.loadingScreen.loadingScreenLayout.visibility = View.GONE


            })
        }

    }


    override fun onStart() {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkListener, intentFilter)
        super.onStart()

    }

    override fun onStop() {
        unregisterReceiver(networkListener)
        super.onStop()
    }

}