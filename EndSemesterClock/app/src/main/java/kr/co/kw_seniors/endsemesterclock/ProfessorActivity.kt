package kr.co.kw_seniors.endsemesterclock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.kw_seniors.endsemesterclock.databinding.ActivityNoticeBinding
import kr.co.kw_seniors.endsemesterclock.databinding.ActivityProfessorBinding

class ProfessorActivity : AppCompatActivity() {

    val binding by lazy{ ActivityProfessorBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}