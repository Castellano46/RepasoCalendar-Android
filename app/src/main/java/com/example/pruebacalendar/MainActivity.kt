package com.example.pruebacalendar

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var calendarView: CalendarView
    private var events: MutableMap<String, String> = mutableMapOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calendarView = findViewById(R.id.calendar)

        val calendars: ArrayList<CalendarDay> = ArrayList()
        val calendar = Calendar.getInstance()

        calendar.set(2024, 9, 14)
        val calendarDay = CalendarDay(calendar)
        calendarDay.labelColor = R.color.red
        calendarDay.imageResource = R.drawable.baseline_fastfood_24
        calendars.add(calendarDay)
        events["14-09-2024"] = "Burger Day"
        calendarView.setCalendarDays(calendars)
        calendarView.setOnCalendarDayClickListener(object : OnCalendarDayClickListener{
            @SuppressLint("DefaultLocale")
            override fun onClick(calendarDay: CalendarDay) {
                val day = String.format("%02d", calendarDay.calendar.get(Calendar.DAY_OF_MONTH))
                val month = String.format("%02d", calendarDay.calendar.get(Calendar.MONTH))
                val year = calendarDay.calendar.get(Calendar.YEAR)
                if(events.containsKey("$day-$month-$year")){
                    Toast.makeText(baseContext, events["$day-$month-$year"],Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(baseContext, "Nothing to do",Toast.LENGTH_SHORT).show()
                }
            }
        })
        calendarView.setOnForwardPageChangeListener(object: OnCalendarPageChangeListener{
            @SuppressLint("DefaultLocale")
            override fun onChange() {
                val month = String.format("%02d", calendarView.currentPageDate.get(Calendar.MONTH))
                val year = calendarView.currentPageDate.get(Calendar.YEAR)
                Toast.makeText(baseContext, "$month-$year",Toast.LENGTH_SHORT).show()
            }
        })
    }
}