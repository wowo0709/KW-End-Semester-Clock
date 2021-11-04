package kr.co.kw_seniors.endsemesterclock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.kw_seniors.endsemesterclock.databinding.FragmentCommonNoticeBinding


class CommonNoticeFragment : Fragment() {

    lateinit var binding: FragmentCommonNoticeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCommonNoticeBinding.inflate(inflater, container, false)

        val data = loadData()

        var adapter = NoticeRecyclerAdapter()
        adapter.listData = data
        binding.recyclerViewCommon.adapter = adapter
        binding.recyclerViewCommon.layoutManager = LinearLayoutManager(this.context)



        return binding.root
    }

    // Room에 저장된 아이템 리스트 불러오기  
    fun loadData(): MutableList<NoticeRecyclerItem>{
        val data: MutableList<NoticeRecyclerItem> = mutableListOf()

        // Room의 데이터 가져오기



        return data
    }

}