package kr.co.kw_seniors.endsemesterclock

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// NoticeActivity에서 사용할 테이블을 나타내는 클래스
@Entity(tableName = "notice_item")
class NoticeItem {
    // @ColumnInfo 로 테이블 컬럼 명시
    // 변수명과 다르게 하고 싶으면 @ColumnInfo(name="컬럼명")으로 작성
    @ColumnInfo
    @PrimaryKey(autoGenerate = true) // 키 명시, 자동 증가 옵션
    var no: Long? = null
    @ColumnInfo
    var url: String = ""
    @ColumnInfo
    var title = ""
    @ColumnInfo
    var info = ""
    /*
    @Ignore 어노테이션을 적용하면 해당 변수가 테이블과 관계없는 변수임을 나타낼 수 있다.
     */
    // 생성자 작성하기
    constructor(url: String, title: String, info: String){
        this.url = url
        this.title = title
        this.info = info
    }
}