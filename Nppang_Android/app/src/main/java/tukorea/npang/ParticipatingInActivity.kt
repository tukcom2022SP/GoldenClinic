package tukorea.npang

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tukorea.npang.databinding.ActivityParticipatingInBinding


class ParticipatingInActivity : Activity() {
    //firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityParticipatingInBinding
    private lateinit var menumap2: List<Pair<String, Int>>
    private var data: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //db선언
        val db = Firebase.firestore
        binding = ActivityParticipatingInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        val menumap = hashMapOf(
            "BBQ 정왕1호점" to listOf("황금올리브" to 20000, "황금올리브 양념" to 21500, "자메이카 통다리구이" to 21500),
            "하임치킨 시화로데오점" to listOf(
                "오븐바사삭" to 17000,
                "불금치킨" to 18000,
                "갈비천왕" to 18000,
                "볼케이노" to 18000
            ),
            "자담치킨 정왕점" to listOf("소보로치킨" to 19000, "불패치킨" to 21000, "맵슐랭치킨" to 21000),
            "현구피자 정왕점" to listOf("콤비네이션" to 13900, "불고기피자" to 13900, "치즈피자" to 13900),
            "수빈피자 시화로데오점" to listOf("순살가득피자닭" to 29900, "돈마호크" to 35900, "립스테이크" to 35900),
            "윾빈이네 피자 정왕점" to listOf(
                "리얼시카고 오리지날" to 22900,
                "BBQ리얼 시카고" to 25900,
                "몽빼르 피자" to 21900
            ),
            "현구대반점" to listOf("유니콩 짜장면" to 6000, "참짬뽕" to 8000, "야채볶음밥" to 8000),
            "더베이징" to listOf("짜장면" to 8000, "짬뽕" to 9000, "탕수육" to 25000),
            "아래향" to listOf("울면" to 7000, "콩국수" to 9000, "찹쌀탕수육" to 26000),
            "현구족발 정왕 1호점" to listOf("족발" to 25000, "불족발" to 27000, "냉체족발" to 31000),
            "현구보쌈 시화로데오점" to listOf("김치보쌈" to 27000, "1인보쌈" to 18000, "마늘보쌈" to 29000),
            "오늘사족 본점" to listOf("족발한팩" to 12000, "보쌈한팩" to 12000, "마늘쪽한팩" to 13000),
            "돼지게티 정왕점" to listOf("실속세트" to 21000, "1인세트" to 13000, "실속치킨세트" to 18000),
            "동대문엽기떡볶이 시화이마트점" to listOf("엽기메뉴" to 14000, "로제메뉴" to 18000),
            "삼첩분식 정왕점" to listOf("삼첩떡볶이" to 8900, "바질크림 떡볶이" to 9900, "대구막창" to 11000),
            "해피타코야끼&닭꼬치" to listOf("수제타코야끼22알" to 14000, "파닭꼬치" to 3500, "모둠꼬치" to 26000),
            "써브웨이 시흥저정왕점" to listOf("이탈리안비엠티" to 8700, "에그마요" to 7500, "스테이크치즈" to 10000),
            "버거킹 시흥정왕점" to listOf("콰트로치즈와퍼" to 10900, "통새우와퍼" to 10900, "갈릭불고기와퍼" to 10500)
        )
        //RecycleView에서 선택된 정보 가져오기
        val Postsintent = intent
        binding.tvPpOstName.text = Postsintent.getStringExtra("postname")
        binding.tvPpContents.text = Postsintent.getStringExtra("contents")
        binding.tvPpStoreName.text = Postsintent.getStringExtra("storename")
        menumap2 = menumap.get(binding.tvPpStoreName.text) as List<Pair<String, Int>>

        for ((first, second) in menumap2) {
            data.add(first)
        }


        //메뉴 관련 스피너
        //val menuList = resources.getStringArray(R.array.menuList)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data)
        binding.spinnerMenuChoice.adapter = adapter


        //참가 완료 AlertDialog
        binding.btnParticipate.setOnClickListener {
            var user = (firebaseAuth.currentUser?.uid)
            db.collection("LivePost").document(binding.tvPpOstName.text.toString())
                .update("group", FieldValue.arrayUnion(user))
            //set(userdata, SetOptions.merge())
            /*val builder = AlertDialog.Builder(this)
                .setTitle("참가완료")
                .setMessage("주문금액 : ")
                .setMessage("배달비 합계 : ")
                .setMessage("본인 부담 배달비 :  ")
                .setMessage("결제 예정시작 : ")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, which ->
                        Toast.makeText(this, "확인", Toast.LENGTH_SHORT).show()
                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, which ->
                        Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show()
                    })
            builder.show()*/
        }
    }
}