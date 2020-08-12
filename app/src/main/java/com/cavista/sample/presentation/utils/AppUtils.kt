package com.cavista.sample.presentation.utils

import java.text.SimpleDateFormat
import java.util.*

object AppUtils {

    /**
     * Format date in dd/mm/yy
     * @param longDateValue long value of date
     */

    fun convertLongToDateString(longDateValue: Long): String {
        try {
            val date = Date(longDateValue)
            val df2 = SimpleDateFormat("dd/MM/yy")
            return df2.format(date)
        } catch (e: Exception) {
            println("Date parsing exception: " + e.localizedMessage)
        }
        return ""
    }

}