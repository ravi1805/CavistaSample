package com.cavista.sample.data.preferences

import android.content.Context
import android.content.SharedPreferences


abstract class AbstractPreference {
    private val TAG = "SharedPreferences"

    private lateinit var prefs: SharedPreferences

    val keys: Set<String>
        get() = prefs.all?.keys ?: emptySet()

    fun setPreferenceName(context: Context, prefName: String) {
        prefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    fun clear() {
        prefs.edit().clear().apply()
    }

    fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }

    fun putString(key: String, value: String?) {
        prefs.edit().putString(key, value).apply()
    }

    fun getString(key: String , default:String): String? {
        return prefs.getString(key, default)
    }

    fun putBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, value: Boolean): Boolean {
        return prefs.getBoolean(key, value)
    }

    fun put(key: String, value: Int) {
        prefs.edit().putInt(key, value).apply()
    }

    fun putLong(key: String, value: Long) {
        prefs.edit().putLong(key, value).apply()
    }

    fun getLong(key: String): Long {
        return prefs.getLong(key, 0)
    }

    fun getInt(key: String): Int {
        return prefs.getInt(key, 0)
    }

    fun containsKey(key: String): Boolean {
        return prefs.contains(key)
    }

    fun removeKey(key: String) {
        prefs.edit().remove(key).apply()
    }


    object PrefName {
        const val USER_INFO = "user_info"
    }

}
