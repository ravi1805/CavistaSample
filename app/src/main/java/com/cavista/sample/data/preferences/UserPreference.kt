package com.cavista.sample.data.preferences

import android.content.Context
import javax.inject.Inject

open class UserPreference @Inject constructor(val context: Context) : AbstractPreference() {
    init {
        setPreferenceName(context, SecureSharedPrefs.prefName.USER_INFO)
    }

    companion object {
        const val IS_USER_LOGGEDIN = "IS_USER_LOGGEDIN"
        const val KEY_USER_EMAILID = "KEY_USER_EMAIL_ID"
        const val KEY_USER_PASSWORD = "KEY_USER_PASSWORD"
    }
}