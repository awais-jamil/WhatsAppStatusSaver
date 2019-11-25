package com.deltatechforce.statusdownloaderforwhatsapp.viewmodels

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Handler
import android.provider.MediaStore
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deltatechforce.statusdownloaderforwhatsapp.models.StatusModel
import com.deltatechforce.statusdownloaderforwhatsapp.support.Application
import com.deltatechforce.statusdownloaderforwhatsapp.support.MyConstants
import com.deltatechforce.statusdownloaderforwhatsapp.support.SharedPreferences
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.channels.FileChannel
import java.util.*
import kotlin.collections.ArrayList


class DashboardViewModel: ViewModel() {

    val dataLoaded = MutableLiveData<Boolean> ()

    lateinit var  imageStatusList: ArrayList<StatusModel>
    lateinit var  videoStatusList: ArrayList<StatusModel>
    lateinit var selectedVideo: StatusModel
    lateinit var selectedImage: StatusModel
    fun getStatus(){
        imageStatusList = ArrayList()
        videoStatusList = ArrayList()
        when(SharedPreferences.getType()){
            "WhatsApp" -> {

                if(MyConstants.WHATSAPP_STATUS_DIRECTORY.exists()){

                    var statusFiles = MyConstants.WHATSAPP_STATUS_DIRECTORY.listFiles()
                    if(statusFiles != null && statusFiles.size > 0){
                        Arrays.sort(statusFiles)

                        for(file in statusFiles){
                            val status = StatusModel(file, file.name, file.absolutePath)

//                            if(status.isVideo)
//                                status.setThumbnail(getThumbnail(status))

                            if(!status.isVideo){
                                imageStatusList.add(status)
                            } else {
                                videoStatusList.add(status)
                            }
                        }

                        dataLoaded.value = true
                    } else {
                        dataLoaded.value = false
                    }
                }
            }
            "GBWhatsApp" -> {

                if(MyConstants.GBWHATSAPP_STATUS_DIRECTORY.exists()){

                    var statusFiles = MyConstants.GBWHATSAPP_STATUS_DIRECTORY.listFiles()
                    if(statusFiles != null && statusFiles.size > 0){
                        Arrays.sort(statusFiles)

                        for(file in statusFiles){
                            val status = StatusModel(file, file.name, file.absolutePath)
//                            if(status.isVideo)
//                                status.setThumbnail(getThumbnail(status))

                            if(!status.isVideo){
                                imageStatusList.add(status)
                            } else {
                                videoStatusList.add(status)
                            }

                        }
                        dataLoaded.value = true
                    } else {
                        dataLoaded.value = false
                    }
                }
            }
            "BWhatsApp" -> {

            }
        }

    }

    fun downloadStatus(status: StatusModel){
        var file = File(MyConstants.SAVED_DIRECTORY)

        if(!file.exists()){
            file.mkdirs()
        }

        var destFile = File(file.path+File.separator + status.title)
        if(destFile.exists()){
            destFile.delete()
        }

        copyFile(status.file, destFile)

        Toast.makeText(Application.applicationContext(),"DownLoad Complete",  Toast.LENGTH_SHORT).show()

        var intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        intent.setData(Uri.fromFile(destFile))
        Application.applicationContext().sendBroadcast(intent)
    }

    fun copyFile(status: File, destFile: File){
        if(!destFile.parentFile.exists()){
            destFile.parentFile.mkdirs()
        }
        if(!destFile.exists()){
            destFile.createNewFile()
        }


        lateinit var source: FileChannel
        lateinit var destination: FileChannel

        source = FileInputStream(status).channel
        destination = FileOutputStream(destFile).channel

        destination.transferFrom(source, 0, source.size())

        source.close()
        destination.close()
    }

//    fun getThumbnail(file: StatusModel): Bitmap {
//
////        if(file.isVideo){
//            return ThumbnailUtils.createVideoThumbnail(file.file.absolutePath, MediaStore.Video.Thumbnails.FULL_SCREEN_KIND)
////        } else {
////            return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(file.file.absolutePath), MyConstants.THUMBSIZE, MyConstants.THUMBSIZE)
////        }
//    }

}