package kr.co.kw_seniors.endsemesterclock

import android.icu.text.Transliterator
import android.provider.ContactsContract
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// ProfessorActivity에서 사용할 테이블을 나타내는 클래스
@Entity(tableName = "professor_item")
class ProfessorItem {
    // @ColumnInfo 로 테이블 컬럼 명시
    // 변수명과 다르게 하고 싶으면 @ColumnInfo(name="컬럼명")으로 작성
    @ColumnInfo
    @PrimaryKey(autoGenerate = true) // 키 명시, 자동 증가 옵션
    var no: Long? = null
    @ColumnInfo
    var category: String = ""
    @ColumnInfo
    var name = ""
    @ColumnInfo
    var position = ""
    @ColumnInfo
    var telNum = ""
    @ColumnInfo
    var email = ""
    @ColumnInfo
    var homepage = ""
    /*
    @Ignore 어노테이션을 적용하면 해당 변수가 테이블과 관계없는 변수임을 나타낼 수 있다.
     */
    // 생성자 작성하기
    constructor(category: String, name: String, position: String, telNum: String, email: String, homepage: String){
        this.category = category
        this.name = name
        this.position = position
        this.telNum = telNum
        this.email = email
        this.homepage = homepage
    }
}