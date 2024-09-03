package com.example.apidemoopenweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {


    private lateinit var frag: VPFragment
    private lateinit var fragManager: FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Initialize Fragment Manager
        frag = VPFragment()
        fragManager = supportFragmentManager
        fragManager.beginTransaction().replace(R.id.frameLayout, frag ).commit()
        fragManager.executePendingTransactions()




        // Observe LiveData from the ViewModel

    }

}

