package com.library.lby.firebase


import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.antino.avengers.R
import com.antino.avengers.presentation.activity.HomeActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


import kotlinx.coroutines.runBlocking

const val channelId = "notification_channel"
const val channelName = "com.antino.avengers"

class MyFirebaseMessagingService constructor(
) : FirebaseMessagingService() {

    private val TOKEN_FCM = MyFirebaseMessagingService::class.java.simpleName

    override fun onNewToken(token: String) {

        super.onNewToken(token);
        Log.d(TOKEN_FCM, "Token: " + token)

    }


//generate the notification
//attach the notification created with the custom layout
//show the notification


    override fun onMessageReceived
                (remoteMessage: RemoteMessage) {

        Log.d("FCM_NOTIFY", "onMessageReceived: "+remoteMessage.notification)
        if(remoteMessage.notification != null){
            Log.d("FCM_NOTIFY", "onMessageReceived: "+     remoteMessage.notification!!.title!!+remoteMessage.notification!!.body!!)

            val action= remoteMessage.data.get("click_action")

            generateNotification(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!,action)
        }

    }

    @SuppressLint("RemoteViewLayout")
    fun getRemoteView(title: String, message: String) : RemoteViews {
        val remoteView = RemoteViews("com.antino.avengers",
            R.layout.notification)

        remoteView.setTextViewText(R.id.title, title)
        remoteView.setTextViewText(R.id.message, message)
        remoteView.setImageViewResource(R.id.app_logo,R.drawable.ic_launcher_background)

        return remoteView

    }

    fun generateNotification(title: String, message: String, action: String?){



        val intent = Intent(this, HomeActivity::class.java )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("click_action",action)

        val pendingIntent = PendingIntent.getActivity(this,
            0, intent,PendingIntent.FLAG_ONE_SHOT)


        //channel id, channel name

        var builder : NotificationCompat.Builder = NotificationCompat.Builder(applicationContext,
            channelId)

            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder=builder.setContent(getRemoteView(title,message))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel= NotificationChannel(channelId, channelName,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)

        }

        notificationManager.notify(0,builder.build())

    }
}