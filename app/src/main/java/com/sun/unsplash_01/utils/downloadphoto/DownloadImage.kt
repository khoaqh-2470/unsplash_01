package com.sun.unsplash_01.utils.downloadphoto

import android.app.DownloadManager
import android.app.Service
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class DownloadImage {

    fun downloadImage(context: Context, url: String): Boolean {

        val namePhoto = "$TITLE_PHOTO-${System.currentTimeMillis()}"
        val manager = context.getSystemService(Service.DOWNLOAD_SERVICE) as DownloadManager
        val downloadUri: Uri = Uri.parse(url)

        val request = DownloadManager.Request(downloadUri).apply {
            setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE
            )
            setAllowedOverRoaming(false)
            setTitle(namePhoto)
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                namePhoto + FILE_EXTENSION
            )
        }

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
            val fileName = File(myDir, "${System.currentTimeMillis()}$FILE_EXTENSION")
            val outputStream = FileOutputStream(fileName)
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.run {
                flush()
                close()
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    companion object {

        const val TITLE_PHOTO = "Unsplash"
        const val FILE_EXTENSION = ".png"
        fun newInstance() = DownloadImage()
    }
}
