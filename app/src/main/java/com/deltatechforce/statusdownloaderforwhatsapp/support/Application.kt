package com.deltatechforce.statusdownloaderforwhatsapp.support

import android.app.Application

class Application: Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        SharedPreferences.init(this)

        SharedPreferences.setType("WhatsApp")
    }

    companion object {
        private var instance: Application? = null

        @JvmStatic
        fun applicationContext() : Application {
            return instance as Application
        }
    }
}