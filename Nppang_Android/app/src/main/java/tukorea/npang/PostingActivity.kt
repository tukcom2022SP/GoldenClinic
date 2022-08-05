package tukorea.npang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
            val postdata=db.collection("LivePost")
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
                postdata.document(binding.etPostName.text.toString()).set(PostInfoMation).addOnSuccessListener {
                    //모집하기 클릭시 실시간게시물 모두보기로 이동
                    var categoryDetail=Intent(this,LivePostsActivity::class.java)
                    startActivity(categoryDetail)
                }.addOnFailureListener {
                    Toast.makeText(this, "게시글 올리기 실패 다시 시도하세요", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //카테고리 관련 스피너
        val cateList = resources.getStringArray(R.array.cateList)
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, cateList)
        binding.spinnerCategoryChoice.adapter = adapter1

        //가게명 관련 스피너
        val shopList = resources.getStringArray(R.array.shopList)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, shopList)
        binding.spinnerShopChoice.adapter = adapter

    }
}