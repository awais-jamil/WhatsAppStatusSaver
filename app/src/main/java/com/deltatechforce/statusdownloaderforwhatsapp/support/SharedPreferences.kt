package com.deltatechforce.statusdownloaderforwhatsapp.support

import android.content.Context
import android.content.SharedPreferences
import android.location.Location

object SharedPreferences {

    private const val NAME = "SharedPreferences"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    private const val KEY_TYPE = "KEY_TYPE"

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit()
    and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    fun setType(type: String) {

        preferences.edit {
            it.putString(KEY_TYPE, type)
        }
    }

    fun getType(): String? {

        return  preferences.getString(KEY_TYPE, null)
    }
}