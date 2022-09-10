package com.antino.avengers.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.antino.avengers.Others.PreferenceUtils
import com.antino.avengers.presentation.fragments.ProjectManagerFragment
import com.antino.avengers.R
import com.antino.avengers.databinding.ActivityMainBinding
import com.antino.avengers.presentation.fragments.VPFragment
import com.google.gson.Gson
import com.antino.avengers.presentation.fragments.DeveloperFragment


class HomeActivity : AppCompatActivity() ,
    ProjectManagerFragment.FragmentToActivity{
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this@HomeActivity
        binding.executePendingBindings()
       if(IsManger()!="manager"){
           val frag = ProjectManagerFragment()
           val manager: FragmentManager = supportFragmentManager
           val transaction: FragmentTransaction = manager.beginTransaction()
           transaction.add(R.id.frameLayout, frag, "Manager Fragment")
           transaction.commit()
       }
        else {
           val frag = VPFragment()
           val manager: FragmentManager = supportFragmentManager
           val transaction: FragmentTransaction = manager.beginTransaction()
           transaction.add(R.id.frameLayout, frag, "VP Fragment")
           transaction.commit()
       }

    }
    private fun IsManger():String{
        val loginPref = PreferenceUtils.getLogin()
       return loginPref!!.role?:""
    }

    override fun payonlineClicked(data: Int) {
        if(data>=0){
            val frag = VPFragment()
            val manager: FragmentManager = supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()
            transaction.replace(R.id.frameLayout, frag, "VP Fragment")
            transaction.commit()
        }

    }
}