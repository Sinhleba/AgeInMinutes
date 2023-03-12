package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null
    private var tvHour : TextView? = null
    private var tvDay : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDataPicker: Button = findViewById(R.id.selectButton)   // Hàm nút nhấn
        tvSelectedDate = findViewById(R.id.tvSelectedDate)              // Hàm select date
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)          // Ham xuat ra phut
        tvHour = findViewById(R.id.tvHour)                          // Xuat ra gio
        tvDay =findViewById(R.id.tvDay)

        btnDataPicker.setOnClickListener {
            clickDatePicker()
        }
    }

        fun clickDatePicker() {

            val myCalendar = Calendar.getInstance()
            val year = myCalendar.get(Calendar.YEAR)
            val month = myCalendar.get(Calendar.MONTH)
            val day = myCalendar.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(this,
                { _, selectedYear, selectedMonth, selectedDayOfMonth ->

                    Toast.makeText(this,
                        "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear was selected.",
                        Toast.LENGTH_LONG).show()
                    // format day MM/DD/YYYY
                    val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                    tvSelectedDate?.text = selectedDate

                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                    val theDate = sdf.parse(selectedDate)

                    val selectedDateInMinutes = theDate.time / 60000 // 1 minutes = 60 000 miliseconds
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateInMinutes = currentDate.time / 60000
                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                    tvAgeInMinutes?.text = differenceInMinutes.toString()

                    val selectedDayInHours = theDate.time / 3600000
                    val currentDayInHours = currentDate.time / 3600000
                    val differentInHours = currentDayInHours - selectedDayInHours
                    tvHour?.text = differentInHours.toString()                      // Convert to hours

                    val selectedDayInDays = theDate.time / 86400000
                    val currentDayInDays = currentDate.time / 86400000
                    val differentInDays = currentDayInDays - selectedDayInDays
                    tvDay?.text = differentInDays.toString()                       // Convert to days
                },
                year,
                month,
                day
            )
            dpd.datePicker.maxDate = System.currentTimeMillis()     // Max day is today, you can't select the future day
            dpd.show()

        }
    }