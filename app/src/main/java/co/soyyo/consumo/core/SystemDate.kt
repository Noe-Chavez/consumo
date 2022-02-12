package co.soyyo.consumo.core

import android.os.Build
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SystemDate {

    companion object {

        private const val FORMAT_DATE_STRING = "yyyy-MM-dd"

        fun getPreviousDate(numberPreviousDays: Long): String {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val actualDate = LocalDate.now()
                val pastDays = actualDate.minusDays(numberPreviousDays)
                val format = DateTimeFormatter.ofPattern(FORMAT_DATE_STRING)
                return pastDays.format(format)
            }

            return "Build.VERSION.SDK_INT"
        }

    }


}