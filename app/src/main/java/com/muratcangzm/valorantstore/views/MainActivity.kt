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


    private var _binding: ActivityMainBinding? = null
    private val binding
    get() = _binding!!
    private val viewModel: DataViewModel by viewModels()
    private val networkListener = NetworkChangeListener()

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)

        binding.bottomNavigation.visibility = View.GONE


        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->


            if (destination.id == R.id.weaponryDetailFragment || destination.id == R.id.agentDetailFragment)
                binding.bottomNavigation.visibility = View.GONE
            else
                binding.bottomNavigation.visibility = View.VISIBLE


        }

        if (NetworkUtils.isInternetAvailable(this)) {

            viewModel.allModelLiveData.observe(this, Observer {

                if (it.isNotEmpty()) {

                    Timber.tag("Vericik").d("${it[0]}")
                    binding.loadingScreen.loadingScreenLayout.visibility = View.GONE
                    binding.bottomNavigation.visibility = View.VISIBLE
                    binding.fragmentContainerView.visibility = View.VISIBLE

                } else {

                    binding.fragmentContainerView.visibility = View.GONE

                }

            })
        } else {

            binding.bottomNavigation.visibility = View.GONE
            binding.loadingScreen.loadingScreenLayout.visibility = View.VISIBLE

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

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkListener)
        _binding = null

    }

}