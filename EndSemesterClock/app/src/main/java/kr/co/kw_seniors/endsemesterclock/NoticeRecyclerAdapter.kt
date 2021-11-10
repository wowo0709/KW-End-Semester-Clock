package kr.co.kw_seniors.endsemesterclock

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.kw_seniors.endsemesterclock.databinding.NoticeRecyclerItemBinding

class NoticeRecyclerAdapter: RecyclerView.Adapter<NoticeRecyclerAdapter.NoticeRecyclerHolder>() {

    // 어댑터에서 사용할 데이터 목록 변수
    var listData = mutableListOf<NoticeItem>()
    // RoomHelper
    var helper: RoomHelper? = null

    inner class NoticeRecyclerHolder(val binding: NoticeRecyclerItemBinding): RecyclerView.ViewHolder(binding.root){

        var tmpNoticeItem: NoticeItem? = null
        // 리스터 연결 등
        init{

        }

        // 아이템에 데이터를 세팅
        fun setNoticeItem(item: NoticeItem){
            binding.textCategory.text = item.category
            binding.textTitle.text = item.title
            binding.textInfo.text = item.info

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeRecyclerHolder {
        val binding = NoticeRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)



        return NoticeRecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeRecyclerHolder, position: Int) {
        val noticeItem = listData.get(position)
        holder.setNoticeItem(noticeItem)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

}