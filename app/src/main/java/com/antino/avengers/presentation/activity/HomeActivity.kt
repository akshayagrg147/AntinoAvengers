package com.antino.avengers.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.antino.avengers.Others.PreferenceUtils
import com.antino.avengers.R
import com.antino.avengers.databinding.ActivityMainBinding
import com.antino.avengers.presentation.fragments.DeveloperFragment
import com.antino.avengers.presentation.fragments.ProjectManagerFragment
import com.antino.avengers.presentation.fragments.VPFragment


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//       if(IsManger()=="manager"){
//           Navigation.findNavController(binding.root)
//               .navigate(R.id.action_pm_to_developer)
//       }
//        else {
//           Navigation.findNavController(binding.root)
//               .navigate(R.id.action_pm_to_developer)
//       }

    }
//    private fun IsManger():String{
//        val loginPref = PreferenceUtils.getLogin()
//       return loginPref!!.role?:""
//    }





}