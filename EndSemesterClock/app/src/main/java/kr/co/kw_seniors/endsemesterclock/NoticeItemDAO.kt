package kr.co.kw_seniors.endsemesterclock

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

// NoticeActivity와 NoticeItem(Room)을 연결해주는 인터페이스
@Dao
interface NoticeItemDAO {
    // 다른 ORM 툴과는 다르게 Room 라이브러리는 조회를 하는 select 쿼리는 직접 작성해야 함
    @Query("select * from notice_item")
    fun getAll(): MutableList<NoticeItem>
    // 시작 지점부터 110개(10페이지)의 데이터 조회
    @Query("select * from notice_item where category=:category")
    fun getCategoryData(category: String): MutableList<NoticeItem>
    // REPLACE를 import 할 때는 androidx.room 패키지로 시작하는 것을 선택
    // 동일한 키를 가진 값이 입력되었을 경우 UPDATE 쿼리로 실행
    @Insert(onConflict = REPLACE)
    fun insert(noticeItem: NoticeItem)
    @Delete
    fun delete(noticeItem: NoticeItem)
}