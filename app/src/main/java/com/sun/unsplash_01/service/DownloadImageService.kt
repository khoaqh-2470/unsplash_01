package com.sun.unsplash_01.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.*
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.sun.unsplash_01.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.*
import java.net.URL
import java.net.URLConnection


class DownloadImageService : Service() {


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.getParcelableExtra<Bitmap>("photo")?.let {
            createNotification(
                System.currentTimeMillis().toInt(),
                it
            )
        }
        return START_NOT_STICKY
    }

    private fun createNotification(idNotification: Int, bitmapPhoto: Bitmap) {
        val namePhoto = getString(R.string.app_name) + idNotification
        val intent =
            Intent(DownloadManager.ACTION_VIEW_DOWNLOADS).setAction(Intent.ACTION_GET_CONTENT)
        val pendingIntent = PendingIntent.getService(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("default", "Default", NotificationManager.IMPORTANCE_LOW)
            notificationManager.createNotificationChannel(channel)
        }
        val notification =
            NotificationCompat.Builder(applicationContext, "default").apply {
                setContentTitle(namePhoto)
                setContentText("2 minus")
                setSmallIcon(R.drawable.ic_download)
                priority = NotificationCompat.PRIORITY_LOW
                setOngoing(true)
                setOnlyAlertOnce(true)
                setContentIntent(pendingIntent)
            }
//        startForeground(idNotification, notification.build())
        val notificationManagerCompat = NotificationManagerCompat.from(this).apply {
            notify(idNotification, notification.build())
        }

        stopSelf()
//            NotificationManagerCompat.from(this).apply {
//                notify(idNotification, notification.build())
//            }
        notification.setProgress(100, 0, false)
        notificationManagerCompat.notify(idNotification, notification.build())

//        savePhoto(2343)
    }

    private fun savePhoto(bitmapPhoto: Bitmap) {

    }

    companion object {
        fun newInstance(context: Context, bitmapPhoto: Bitmap) =
            context.startService(
                Intent(context, DownloadImageService::class.java).putExtra(
                    "photo",
                    bitmapPhoto
                )
            )
    }
}
