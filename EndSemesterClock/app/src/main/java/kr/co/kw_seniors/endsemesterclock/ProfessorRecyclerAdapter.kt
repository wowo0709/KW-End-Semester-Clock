package kr.co.kw_seniors.endsemesterclock

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kr.co.kw_seniors.endsemesterclock.ProfessorRecyclerAdapter.ProfessorRecyclerHolder
import kr.co.kw_seniors.endsemesterclock.databinding.NoticeRecyclerItemBinding
import kr.co.kw_seniors.endsemesterclock.databinding.ProfessorRecyclerItemBinding

class ProfessorRecyclerAdapter: RecyclerView.Adapter<ProfessorRecyclerAdapter.ProfessorRecyclerHolder>() {

    // 어댑터에서 사용할 데이터 목록 변수
    var listData = mutableListOf<ProfessorItem>()
    // RoomHelper
    var helper: RoomHelper? = null

    inner class ProfessorRecyclerHolder(val binding: ProfessorRecyclerItemBinding): RecyclerView.ViewHolder(binding.root){

        var tmpProfessorItem: ProfessorItem? = null
        // 리스터 연결 등
        init{

        }

        // 아이템에 데이터를 세팅
        fun setProfessorItem(item: ProfessorItem){
            binding.Name.text = item.name
            binding.Position.text = item.position
            binding.TelNum.text = item.telNum
            binding.Email.text = item.email
            binding.Homepage.text = item.homepage

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessorRecyclerHolder {
        val binding = ProfessorRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)



        return ProfessorRecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfessorRecyclerHolder, position: Int) {
        val professorItem = listData.get(position)
        holder.setProfessorItem(professorItem)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

}