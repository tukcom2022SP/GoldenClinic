package tukorea.npang

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.common.io.Files.append
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tukorea.npang.databinding.ActivityLoginLayoutBinding

import tukorea.npang.databinding.ActivityParticipatingInBinding


class ParticipatingInActivity : Activity() {
    val db = Firebase.firestore
    private lateinit var binding: ActivityParticipatingInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipatingInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //RecycleView에서 선택된 정보 가져오기
        val Postsintent = intent
        binding.tvPpOstName.text = Postsintent.getStringExtra("postname")
        binding.tvPpContents.text = Postsintent.getStringExtra("contents")
        binding.tvPpStoreName.text = Postsintent.getStringExtra("storename")

        val menudata = db.collection("MENU")

        //ios랑 똑같은 구성의 원하시는 중첩딕셔너리일텐데 이중 hashmapof가 작동하는지는 모르겠습니다..
        val dictChicken = hashMapOf(
            "BBQ 정왕1호점" to hashMapOf(
                "황금올리브" to 20000,
                "황금올리브 양념" to 21500,
                "자메이카 통다리구이" to 21500
            ),
            "하임치킨 시화로데오점" to hashMapOf(
                "오븐바사삭" to 17000,
                "불금치킨" to 18000,
                "갈비천왕" to 18000,
                "볼케이노" to 18000
            ),
            "자담치킨 정왕점" to hashMapOf(
                "소보로치킨" to 19000,
                "불패치킨" to 21000,
                "맵슐랭치킨" to 21000
            )
        )
        val dictPizza = hashMapOf(
            "현구피자 정왕점" to hashMapOf(
                "콤비네이션" to 13900,
                "불고기피자" to 13900,
                "치즈피자" to 13900
            ),
            "수빈피자 시화로데오점" to hashMapOf(
                "순살가득피자닭" to 29900,
                "돈마호크" to 35900,
                "립스테이크" to 35900
            ),
            "윾빈이네 피자 정왕점" to hashMapOf(
                "리얼시카고 오리지날" to 22900,
                "BBQ 리얼 시카고" to 25900,
                "몽빼르 피자" to 21900
            )
        )
        val dictChinese = hashMapOf(
            "현구대반점" to hashMapOf(
                "유니콩 짜장면" to 6000,
                "참짬뽕" to 8000,
                "야채볶음밥" to 8000
            ),
            "더 베이징" to hashMapOf(
                "짜장면" to 8000,
                "짬뽕" to 9000,
                "탕수육" to 25000
            ),
            "아래향" to hashMapOf(
                "울면" to 7000,
                "콩국수" to 9000,
                "찹쌀탕수육" to 26000
            )
        )
        val dictPork = hashMapOf(
            "현구족발 정왕1호점" to hashMapOf(
                "족발" to 25000,
                "불족발" to 27000,
                "냉채족발" to 31000
            ),
            "현구보쌈 시화로데오점" to hashMapOf(
                "김치보쌈" to 27000,
                "1인보쌈" to 18000,
                "마늘보쌈" to 29000
            ),
            "오늘사족 본점" to hashMapOf(
                "족발한팩" to 12000,
                "보쌈함팩" to 12000,
                "마늘족한팩" to 13000
            )
        )
        val dictBbokki = hashMapOf(
            "돼지게티 정왕점" to hashMapOf(
                "실속세트" to 21000,
                "1인세트" to 13000,
                "실속치킨세트" to 18000
            ),
            "동대문엽기떡볶이 시화이마트점" to hashMapOf(
                "엽기메뉴" to 14000,
                "로제메뉴" to 18000,
                "엽봉" to 5000
            ),
            "삼첩분식 정왕점" to hashMapOf(
                "삼첩떡볶이" to 8900,
                "바질크림떡볶이" to 9900,
                "대구막창" to 11000
            )
        )
        val dictEtc = hashMapOf(
            "해피타코야끼&닭꼬치" to hashMapOf(
                "수제타코야끼22알" to 14000,
                "파닭꼬치" to 3500,
                "모둠꼬치" to 26000
            ),
            "써브웨이 시흥정왕점" to hashMapOf(
                "이탈리안비엠티" to 8700,
                "에그마요" to 7500,
                "스테이크치즈" to 10000
            ),
            "버거킹 시흥정왕점" to hashMapOf(
                "콰트로치즈와퍼" to 10900,
                "통새우와퍼" to 10900,
                "갈릭불고기와퍼" to 10500
            )
        )

        //메뉴별 가격. 가격 계산 시도, 키값으로만 숫자를 얻을 수 있게 해놓았는데 계산은 실패: 어떻게 해시맵과 스피너를 연결해야 할지 모르겠습니다
        val menu_price = hashMapOf(
            "황금올리브" to 20000,
            "황금올리브 양념" to 21500,
            "자메이카 통다리구이" to 21500,
            "오븐바사삭" to 17000,
            "불금치킨" to 18000,
            "갈비천왕" to 18000,
            "볼케이노" to 18000,
            "소보로치킨" to 19000,
            "불패치킨" to 21000,
            "맵슐랭치킨" to 21000,
            "콤비네이션" to 13900,
            "불고기피자" to 13900,
            "치즈피자" to 13900,
            "순살가득피자닭" to 29900,
            "돈마호크" to 35900,
            "립스테이크" to 35900,
            "리얼시카고 오리지날" to 22900,
            "BBQ 리얼 시카고" to 25900,
            "몽빼르 피자" to 21900,
            "유니콩 짜장면" to 6000,
            "참짬뽕" to 8000,
            "야채볶음밥" to 8000,
            "짜장면" to 8000,
            "짬뽕" to 9000,
            "탕수육" to 25000,
            "울면" to 7000,
            "콩국수" to 9000,
            "찹쌀탕수육" to 26000,
            "족발" to 25000,
            "불족발" to 27000,
            "냉채족발" to 27000,
            "김치보쌈" to 27000,
            "1인보쌈" to 18000,
            "마늘보쌈" to 29000,
            "족발한팩" to 12000,
            "보쌈함팩" to 12000,
            "마늘족한팩" to 13000,
            "실속세트" to 21000,
            "1인세트" to 13000,
            "실속치킨세트" to 18000,
            "엽기메뉴" to 14000,
            "로제메뉴" to 14000,
            "엽봉" to 5000,
            "삼첩떡볶이" to 8900,
            "바질크림떡볶이" to 9900,
            "대구막창" to 11000,
            "수제타코야끼22알" to 14000,
            "파닭꼬치" to 3500,
            "모둠꼬치" to 26000,
            "이탈리안비엠티" to 8700,
            "에그마요" to 7500,
            "스테이크치즈" to 10000,
            "콰트로치즈와퍼" to 10900,
            "통새우와퍼" to 10900,
            "갈릭불고기와퍼" to 10500
        )


        //메뉴 선택 관련 스피너 선언: 해시맵이랑 연결 안돼있음
        val menuList1 = resources.getStringArray(R.array.menuList_chicken_bbq)
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList1)
        val menuList2 = resources.getStringArray(R.array.menuList_chicken_haim)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList2)
        val menuList3 = resources.getStringArray(R.array.menuList_chicken_jadam)
        val adapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList3)
        val menuList4 = resources.getStringArray(R.array.menuList_china_hyungu)
        val adapter4 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList4)
        val menuList5 = resources.getStringArray(R.array.menuList_china_beiging)
        val adapter5 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList5)
        val menuList6 = resources.getStringArray(R.array.menuList_china_under)
        val adapter6 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList6)
        val menuList7 = resources.getStringArray(R.array.menuList_pizza_hyungu)
        val adapter7 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList7)
        val menuList8 = resources.getStringArray(R.array.menuList_pizza_subin)
        val adapter8 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList8)
        val menuList9 = resources.getStringArray(R.array.menuList_pizza_eukbin)
        val adapter9 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList9)
        val menuList10 = resources.getStringArray(R.array.menuList_pork_hyungu)
        val adapter10 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList10)
        val menuList11 = resources.getStringArray(R.array.menuList_bossam_hyungu)
        val adapter11 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList11)
        val menuList12 = resources.getStringArray(R.array.menuList_pork_today)
        val adapter12 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList12)
        val menuList13 = resources.getStringArray(R.array.menuList_bokki_pig)
        val adapter13 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList13)
        val menuList14 = resources.getStringArray(R.array.menuList_bokki_yeopgi)
        val adapter14 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList14)
        val menuList15 = resources.getStringArray(R.array.menuList_bokki_three)
        val adapter15 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList15)
        val menuList16 = resources.getStringArray(R.array.menuList_exc_taco)
        val adapter16 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList16)
        val menuList17 = resources.getStringArray(R.array.menuList_exc_subway)
        val adapter17 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList17)
        val menuList18 = resources.getStringArray(R.array.menuList_exc_burgerking)
        val adapter18 = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList18)


        //게시물에 따른 스피너 변경
        when (binding.tvPpStoreName.text) {
            "BBQ 정왕1호점" -> {
                binding.spinnerMenuChoice.adapter = adapter1
            }
            "하임치킨 시화로데오점" -> {
                binding.spinnerMenuChoice.adapter = adapter2
            }
            "자담치킨 정왕점" -> {
                binding.spinnerMenuChoice.adapter = adapter3
            }
            "현구대반점" -> {
                binding.spinnerMenuChoice.adapter = adapter4
            }
            "더 베이징" -> {
                binding.spinnerMenuChoice.adapter = adapter5
            }
            "아래향" -> {
                binding.spinnerMenuChoice.adapter = adapter6
            }
            "현구피자 정왕점" -> {
                binding.spinnerMenuChoice.adapter = adapter7
            }
            "수빈피자 시화로데오점" -> {
                binding.spinnerMenuChoice.adapter = adapter8
            }
            "윾빈이네 피자 정왕점" -> {
                binding.spinnerMenuChoice.adapter = adapter9
            }
            "현구족발 정왕1호점" -> {
                binding.spinnerMenuChoice.adapter = adapter10
            }
            "현구보쌈 시화로데오점" -> {
                binding.spinnerMenuChoice.adapter = adapter11
            }
            "오늘사족 본점" -> {
                binding.spinnerMenuChoice.adapter = adapter12
            }
            "돼지게티 정왕점" -> {
                binding.spinnerMenuChoice.adapter = adapter13
            }
            "동대문엽기떡볶이 시화이마트점" -> {
                binding.spinnerMenuChoice.adapter = adapter14
            }
            "삼첩분식 정왕점" -> {
                binding.spinnerMenuChoice.adapter = adapter15
            }
            "해피타코야끼&닭꼬치" -> {
                binding.spinnerMenuChoice.adapter = adapter16
            }
            "써브웨이 시흥정왕점" -> {
                binding.spinnerMenuChoice.adapter = adapter17
            }
            "버거킹 시흥정왕점" -> {
                binding.spinnerMenuChoice.adapter = adapter18
            }

        }

        //스피너를 누르면 선택한 메뉴 추가
        binding.spinnerMenuChoice.adapter
        binding.spinnerMenuChoice.setSelection(0, false)
        binding.spinnerMenuChoice.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //선택 안했을때
                }
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val item = binding.spinnerMenuChoice.selectedItem.toString()
                    binding.tvSelectedMenu.append(item + "\n")
                    }
                }


        //참가 완료 AlertDialog
        binding.btnParticipate.setOnClickListener {

            val builder = AlertDialog.Builder(this)
                .setTitle("참가완료")
                .setMessage(
                    "주문금액 : "
                            + "\n"
                            + "배달비 합계 : "
                            + "\n"
                            + "본인 부담 배달비 :  "
                            + "\n"
                            + "결제 예정시작 : "
                )
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, which ->
                        Toast.makeText(this, "확인", Toast.LENGTH_SHORT).show()
                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, which ->
                        Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show()
                    })
            builder.show()

        }
    }
}