package com.antino.avengers.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.antino.avengers.Others.PreferenceUtils
import com.antino.avengers.R
import com.antino.avengers.databinding.ActivityMainBinding
import com.antino.avengers.presentation.fragments.DeveloperFragment
import com.antino.avengers.presentation.fragments.ProjectManagerFragment
import com.antino.avengers.presentation.fragments.VPFragment


class HomeActivity : AppCompatActivity() ,
    ProjectManagerFragment.FragmentToActivity{
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this@HomeActivity
        binding.executePendingBindings()
       if(IsManger()=="manager"){
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

    override fun clicked(data: Int) {
        Log.d("listsize:12", "${data}")


        if(data>=0){
            val frag = DeveloperFragment()
            val manager: FragmentManager = supportFragmentManager
            val transaction: FragmentTransaction = manager.beginTransaction()

            val bundle = Bundle()
            bundle.putString("Idpassed", data.toString())

            frag.arguments = bundle

            transaction.replace(R.id.frameLayout, frag, "VP Fragment")
            transaction.commit()
        }

    }
}