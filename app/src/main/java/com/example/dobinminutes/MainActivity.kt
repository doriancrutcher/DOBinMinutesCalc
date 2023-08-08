package com.example.dobinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var selectedDateTextView: TextView? = null
    private var viewMinutes: TextView? = null
    private var viewHours:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        selectedDateTextView = findViewById(R.id.selectedDate)
        viewMinutes = findViewById(R.id.minutesView)
        viewHours=findViewById(R.id.viewHours)

        btnDatePicker.setOnClickListener {

            clickDatePicker()
        }
    }

   private fun clickDatePicker() {


        val myCalendar =
            Calendar.getInstance() // Get a current instance of the current calendar date
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(
                    this,
                    "The Year was ${selectedYear} Month is ${selectedMonth + 1} the day is ${selectedDayOfMonth}",
                    Toast.LENGTH_LONG
                ).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/${selectedYear}"
                selectedDateTextView?.setText(selectedDate)


                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    val selectedDateInHours= theDate.time/3600000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000

                        val currentDateInHours= currentDate.time/3600000

                        val differenceInMinutes = selectedDateInMinutes - currentDateInMinutes
                        val differenceInHours= selectedDateInHours - currentDateInHours



                        viewMinutes?.text = differenceInMinutes.toString()
                        viewHours?.text=differenceInHours.toString()

                    }


                }


            },

            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


    }

}