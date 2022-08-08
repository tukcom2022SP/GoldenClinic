package tukorea.npang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import tukorea.npang.databinding.ActivityCategoryDetailsBinding
import java.util.ArrayList

class CategoryDetailsActivity() : Activity() {
    //binding
    private lateinit var binding: ActivityCategoryDetailsBinding
    val db = FirebaseFirestore.getInstance()    // Firestore 인스턴스 선언
    val itemList = arrayListOf<ListLayout>()    // 리스트 아이템 배열
    val adapter = ListAdapter(itemList)         // 리사이클러 뷰 어댑터
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvList.adapter = adapter

        //카테고리 Name설정
        val firstintent = intent

        binding.tvCategorytitle.text = firstintent.getStringExtra("카테고리")

        val categoryname = firstintent.getStringExtra("카테고리")
        db.collection("LivePost").whereEqualTo("category", categoryname)  // 컬렉션별로 정보 뽑기
            .get()      // 문서 가져오기
            .addOnSuccessListener { result ->
                // 성공할 경우
                itemList.clear()
                for (document in result) {  // 가져온 문서들은 result에 들어감
                    val item =
                        ListLayout(
                            document["postname"] as String,
                            document["contents"] as String,
                            document["category"] as String,
                            document["storeName"] as String
                        )
                    itemList.add(item)
                }
                adapter.notifyDataSetChanged()  // 리사이클러 뷰 갱신
            }
            .addOnFailureListener { exception ->
                // 실패할 경우
                Log.w("MainActivity", "Error getting documents: $exception")
            }
        //게시물클릭시 해당게시물로이동
        adapter.setItemClickListner(object : ListAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val intent =
                    Intent(this@CategoryDetailsActivity, ParticipatingInActivity::class.java)
                intent.putExtra("postname", "${itemList[position].postname}")
                intent.putExtra("contents", "${itemList[position].contents}")
                intent.putExtra("storename", "${itemList[position].storeName}")
                startActivity(intent)
            }
        })




        binding.btnWritePost.setOnClickListener {
            val intent = Intent(this, PostingActivity::class.java)
            intent.putExtra("postname", binding.tvCategorytitle.text)
            startActivity(intent)
        }

    }


}

