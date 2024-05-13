package com.example.csetest.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object Utils {

    fun copyImageToInternalStorage(context: Context, resourceId: Int, filename: String): Uri? {
        return try {
            val inputStream = context.resources.openRawResource(resourceId)
            val file = File(context.filesDir, filename)
            val outputStream = FileOutputStream(file)
            inputStream.copyTo(outputStream)
            inputStream.close()
            outputStream.close()
            Uri.fromFile(file)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun copyImageUriToInternalStorage(
        context: Context,
        imageUri: Uri,
        filename: String
    ): File {
        val inputStream = context.contentResolver.openInputStream(imageUri)
        inputStream?.use { input ->
            val outputStream = FileOutputStream(File(context.filesDir, filename))
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
        return File(context.filesDir, filename)
    }
}