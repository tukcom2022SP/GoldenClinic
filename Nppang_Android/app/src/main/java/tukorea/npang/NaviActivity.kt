package com.example.navigationApp

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import tukorea.npang.R
import tukorea.npang.databinding.ActivityNaviBinding

class NaviActivity : Activity() {
    private lateinit var binding: ActivityNaviBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}