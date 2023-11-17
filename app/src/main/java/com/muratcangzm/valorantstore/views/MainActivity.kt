package com.muratcangzm.valorantstore.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muratcangzm.valorantstore.databinding.ActivityMainBinding
import com.muratcangzm.valorantstore.utils.BuildConfig
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())




    }
}