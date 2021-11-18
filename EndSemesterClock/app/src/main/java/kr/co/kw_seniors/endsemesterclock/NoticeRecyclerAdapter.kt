package kr.co.kw_seniors.endsemesterclock

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.kw_seniors.endsemesterclock.databinding.NoticeRecyclerItemBinding

class NoticeRecyclerAdapter: RecyclerView.Adapter<NoticeRecyclerAdapter.NoticeRecyclerHolder>() {

    // 어댑터에서 사용할 데이터 목록 변수
    var listData = mutableListOf<NoticeItem>()
    // RoomHelper
    var helper: RoomHelper? = null
    // 데이터 수정을 위해서 NoticeActivity 프로퍼티 생성
    var noticeActivity: NoticeActivity? = null

    inner class NoticeRecyclerHolder(val binding: NoticeRecyclerItemBinding): RecyclerView.ViewHolder(binding.root){

        var tmpNoticeItem: NoticeItem? = null

        // 아이템에 데이터를 세팅
        fun setNoticeItem(item: NoticeItem){
            binding.textCategory.text = item.category
            binding.textTitle.text = item.title
            binding.textInfo.text = item.info
            if(item.favorite == "true"){
                binding.btnFavorite.setImageResource(android.R.drawable.btn_star_big_on)
            }else{
                binding.btnFavorite.setImageResource(android.R.drawable.btn_star_big_off)
            }
            // 즐겨찾기 버튼 클릭 시 즐겨찾기 등록/해제 및 아이콘 변경
            binding.btnFavorite.setOnClickListener {
                if(item.favorite == "true"){
                    binding.btnFavorite.setImageResource(android.R.drawable.btn_star_big_off)
                    item.favorite = "false"
                    Toast.makeText(binding.root.context,
                                    "즐겨찾기 해제되었습니다.",
                                    Toast.LENGTH_SHORT).show()
                }else{
                    binding.btnFavorite.setImageResource(android.R.drawable.btn_star_big_on)
                    item.favorite = "true"
                    Toast.makeText(binding.root.context,
                                "즐겨찾기 등록되었습니다.",
                                Toast.LENGTH_SHORT).show()
                }
                // 데이터 변경 알리기
                helper?.noticeItemDAO()?.update(item)
                notifyDataSetChanged()
            }
            // 아이템 클릭 시 url 연결
            itemView.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                binding.root.context.startActivity(intent)
            }

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