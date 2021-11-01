package kr.co.kw_seniors.endsemesterclock

import androidx.room.Database
import androidx.room.RoomDatabase

// RoomDatabase를 상속하는 추상 클래스 생성
@Database(entities = arrayOf(NoticeItem::class), version=1, exportSchema=false)
abstract class RoomHelper: RoomDatabase() {
    // NoticeItemDAO 인터페이스의 구현체를 사용할 수 있는 메서드명 정의
    // RoomHelper 인스턴스는 noticeItemDAO() 메서드를 호출함으로써 NoticeItemDAO 인터페이스 내에 선언된 DML 쿼리 메서드 사용 가능
    abstract fun noticeItemDAO(): NoticeItemDAO
}