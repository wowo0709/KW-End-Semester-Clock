package kr.co.kw_seniors.endsemesterclock

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.kw_seniors.endsemesterclock.databinding.ActivityOptionBinding
import java.util.*

class OptionActivity : AppCompatActivity() {

    val binding by lazy { ActivityOptionBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.result.text =  MainActivity.dateString + " / " + MainActivity.timeString

        binding.date.setOnClickListener {
            val cal = Calendar.getInstance()    //캘린더뷰 만들기
            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                MainActivity.dateString = "${year}-${month+1}-${dayOfMonth}"
                binding.result.text =  MainActivity.dateString + " / " + MainActivity.timeString
                MainActivity.End = MainActivity.dateFormat.parse("${MainActivity.dateString} / ${MainActivity.timeString}").time
                MainActivity.Now = Calendar.getInstance().apply{}.time.time
                MainActivity.dDay = MainActivity.End - MainActivity.Now
            }
            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()

        }

        binding.time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                MainActivity.timeString = "${hourOfDay}:${minute}:00"
                binding.result.text =  MainActivity.dateString + " / " + MainActivity.timeString
                MainActivity.End = MainActivity.dateFormat.parse("${MainActivity.dateString} / ${MainActivity.timeString}").time
                MainActivity.Now = Calendar.getInstance().apply{}.time.time
                MainActivity.dDay = MainActivity.End - MainActivity.Now
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),true).show()

        }
    }

}