package com.sun.unsplash_01.service

import android.app.DownloadManager
import android.app.Service
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import com.sun.unsplash_01.R
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.lang.Exception

class DownloadImage(private val context: Context) {

    fun downloadImage(url: String): Boolean {
        val appName = context.getString(R.string.app_name).replace(" ", "")
        val namePhoto = "$appName-${System.currentTimeMillis()}"
//        val myDir = File(
//            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//                .toString()
//        )
//        if (!myDir.exists()) {
//            myDir.mkdirs()
//        }

        val manager = context.getSystemService(Service.DOWNLOAD_SERVICE) as DownloadManager
        val downloadUri: Uri = Uri.parse(url)
        val request = DownloadManager.Request(downloadUri)

        request.setAllowedNetworkTypes(
            DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE
        )
            .setAllowedOverRoaming(true)
            .setTitle(namePhoto)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$namePhoto.jpeg")

        manager.enqueue(request)

//        return myDir.absolutePath + File.separator.toString() + currenDate + ".jpg"
        return true
    }

    fun saveImage(bitmapImage: Bitmap) {
        try {
            val myDir = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    .toString()
            )
            if (!myDir.exists()) {
                myDir.mkdirs()
            }
            val fileName = File(myDir, "${System.currentTimeMillis()}.jpg")
            val outputStream = FileOutputStream(fileName)
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.run {
                flush()
                close()
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        Toast.makeText(context, "Save image success!", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(context: Context) = DownloadImage(context)
    }
}
