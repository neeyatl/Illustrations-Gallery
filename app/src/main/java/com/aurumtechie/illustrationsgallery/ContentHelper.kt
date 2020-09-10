package com.aurumtechie.illustrationsgallery

import android.app.Activity
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast


object ContentHelper {

    /** Gets paths of all the images from the device
     * @author Neeyat Lotlikar
     * @param activity currently active activity object
     * @return a list of String paths to all the images stored on the device
     * @see getImagesPathFromLocation*/
    fun getAllImagesPaths(activity: Activity): List<String> {
        val paths = mutableListOf<String>()

        paths.addAll(
            getImagesPathFromLocation(
                activity,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )
        )

        val isSDPresent = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        if (isSDPresent) paths.addAll(
            getImagesPathFromLocation(
                activity,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
        )

        return paths.toList()
    }

    /** Gets paths to all images from device storage
     * @author Neeyat Lotlikar
     * @param activity currently active activity object
     * @param storageUri uri to the storage device which needs to be searched for images
     * @return a list of String paths to all the images within the storageUri location */
    private fun getImagesPathFromLocation(activity: Activity, storageUri: Uri): List<String> {
        val paths = mutableListOf<String>() // List to store the image paths

        val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID)
        val orderBy = MediaStore.Images.Media._ID

        // Stores all the images from the gallery in Cursor
        activity.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
            null, orderBy
        )?.use { cursor ->
            for (i in 0 until cursor.count) { // count is the total number of images
                cursor.moveToPosition(i)
                val dataColumnIndex: Int = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                paths.add(i, cursor.getString(dataColumnIndex)) // Store the path of the image
                Log.i("PATH", paths[i])
            }
        } ?: Toast.makeText(
            activity,
            "$storageUri - Cursor is null. No images found.",
            Toast.LENGTH_LONG
        ).show()

        return paths
    }

}