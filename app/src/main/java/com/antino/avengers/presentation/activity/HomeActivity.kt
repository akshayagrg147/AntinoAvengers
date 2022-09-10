package com.antino.avengers.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.antino.avengers.presentation.fragments.ProjectManagerFragment
import com.antino.avengers.R
import com.antino.avengers.databinding.ActivityMainBinding
import com.antino.avengers.presentation.fragments.DeveloperFragment


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this@HomeActivity
        binding.executePendingBindings()

        val frag = DeveloperFragment()
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.add(R.id.frameLayout, frag, "Test Fragment")
        transaction.commit()
    }
}