package tukorea.npang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tukorea.npang.databinding.ActivityPostingBinding


class PostingActivity : Activity() {
    val db = Firebase.firestore
    private lateinit var binding: ActivityPostingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnJoin.setOnClickListener {
            //파이어스토어 컬렉션 connect
            val postdata = db.collection("LivePost")
            if (binding.etPostName.length() == 0 || binding.etContents.length() == 0) {
                Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                val category = binding.spinnerCategoryChoice.selectedItem
                val contents = binding.etContents.text
                val postname = binding.etPostName.text
                val storeName = binding.spinnerShopChoice.selectedItem //가게정보들어올때까지 임시로 세팅
                var PostInfoMation = hashMapOf(
                    "category" to category.toString().trim(),
                    "contents" to contents.toString().trim(),
                    "postname" to postname.toString().trim(),
                    "storeName" to storeName.toString().trim()
                )
                //컬렉션이름 게시물이름으로 설정
                postdata.document(binding.etPostName.text.toString()).set(PostInfoMation)
                    .addOnSuccessListener {
                        //모집하기 클릭시 실시간게시물 모두보기로 이동
                        var categoryDetail = Intent(this, LivePostsActivity::class.java)
                        startActivity(categoryDetail)
                    }.addOnFailureListener {
                        Toast.makeText(this, "게시글 올리기 실패 다시 시도하세요", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        //카테고리 관련 스피너
        val cateList = resources.getStringArray(R.array.cateList)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cateList)
        binding.spinnerCategoryChoice.adapter = adapter

        //가게 이름 - 치킨
        val shopList1 = resources.getStringArray(R.array.shopList_chicken)
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, shopList1)
        //가게 이름 - 중식
        val shopList2 = resources.getStringArray(R.array.shopList_china)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, shopList2)
        //가게 이름 - 피자
        val shopList3 = resources.getStringArray(R.array.shopList_pizza)
        val adapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_item, shopList3)
        //가게 이름 - 족발보쌈
        val shopList4 = resources.getStringArray(R.array.shopList_pork)
        val adapter4 = ArrayAdapter(this, android.R.layout.simple_spinner_item, shopList4)
        //가게 이름 - 분식
        val shopList5 = resources.getStringArray(R.array.shopList_bokki)
        val adapter5 = ArrayAdapter(this, android.R.layout.simple_spinner_item, shopList5)
        //가게 이름 - 기타
        val shopList6 = resources.getStringArray(R.array.shopList_exc)
        val adapter6 = ArrayAdapter(this, android.R.layout.simple_spinner_item, shopList6)

        //카테고리 관련 스피너 누르면 가게명 관련 스피너 동작
        binding.spinnerCategoryChoice.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //선택 안했을때
                }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    when (p2) {
                        0 -> {
                            binding.spinnerShopChoice.adapter = adapter1
                        }
                        1 -> {
                            binding.spinnerShopChoice.adapter = adapter2
                        }
                        2 -> {
                            binding.spinnerShopChoice.adapter = adapter3
                        }
                        3 -> {
                            binding.spinnerShopChoice.adapter = adapter4
                        }
                        4 -> {
                            binding.spinnerShopChoice.adapter = adapter5
                        }
                        5 -> {
                            binding.spinnerShopChoice.adapter = adapter6
                        }
                    }
                }
            }


    }
}