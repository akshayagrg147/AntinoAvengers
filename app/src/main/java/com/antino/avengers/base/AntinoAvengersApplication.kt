package com.antino.avengers.base

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.antino.avengers.Others.PreferenceUtils
import com.antino.avengers.koin.appModule.ApiModule
import com.antino.avengers.koin.appModule.RepositoryModule
import com.antino.avengers.koin.appModule.ViewModelModule
import com.antino.avengers.koin.networkModule.NetworkModule
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AntinoAvengersApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //setupExceptionHandler()
        PreferenceUtils.with(this)



        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AntinoAvengersApplication)
            modules(listOf(RepositoryModule, ViewModelModule, NetworkModule, ApiModule))
        }
//        get FCM token
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.d("fcmToken",token)
                PreferenceUtils.putString("PREF_FCM_TOKEN",token.toString())
            }
        }
    }

    private fun setupExceptionHandler(){
        Handler(Looper.getMainLooper()).post {
            while (true) {
                try {
                    Looper.loop()
                } catch (e: Throwable) {

                }
            }
        }
        Thread.setDefaultUncaughtExceptionHandler { t, e ->

        }
    }
}
