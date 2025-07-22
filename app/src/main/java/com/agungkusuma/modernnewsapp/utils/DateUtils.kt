package com.agungkusuma.modernnewsapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun formatDate(isoDate: String): String {
        return try {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            parser.timeZone = TimeZone.getTimeZone("UTC")
            val date = parser.parse(isoDate)

            val formatter = SimpleDateFormat("EEE, dd MMMM yyyy HH:mm", Locale("id", "ID"))
            formatter.format(date ?: Date())
        } catch (_: Exception) {
            ""
        }
    }
}