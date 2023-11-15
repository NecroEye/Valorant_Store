package com.muratcangzm.valorantstore.views

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.muratcangzm.valorantstore.databinding.ActivityMainBinding
import com.muratcangzm.valorantstore.viewmodels.DataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private val viewModel: DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.getEvents.observe(this) {
            Log.d("Deneme", "onCreate: ${it.status}")
        }


    }
}