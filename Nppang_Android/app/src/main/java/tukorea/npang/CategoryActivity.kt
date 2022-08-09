package tukorea.npang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import tukorea.npang.databinding.ActivityCategoryBinding

class CategoryActivity : Activity() {
    private lateinit var binding: ActivityCategoryBinding
    val db = FirebaseFirestore.getInstance()    // Firestore 인스턴스 선언
    val itemList = arrayListOf<ListLayoutCategory>()    // 리스트 아이템 배열
    val adapter = ListAdapterCategory(itemList)         // 리사이클러 뷰 어댑터
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //리싸이클뷰 연결
        binding.rvList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvList.adapter = adapter
        db.collection("LivePost")   // 작업할 컬렉션
            .get()      // 문서 가져오기
            .addOnSuccessListener { result ->
                // 성공할 경우
                itemList.clear()
                for (document in result) {  // 가져온 문서들은 result에 들어감
                    val item =
                        ListLayoutCategory(
                            document["postname"] as String,
                            document["category"] as String
                        )
                    itemList.add(item)
                }
                adapter.notifyDataSetChanged()  // 리사이클러 뷰 갱신
            }
            .addOnFailureListener { exception ->
                // 실패할 경우
                Log.w("MainActivity", "Error getting documents: $exception")
            }
        binding.imgChickenimage.setOnClickListener {
            Intent(this, CategoryDetailsActivity::class.java).apply {
                putExtra("카테고리", "치킨")
                startActivity(this)
            }
        }
        binding.imgChinaimage.setOnClickListener {
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "중식")
            startActivity(intent)
        }
        binding.imgPizzaimage.setOnClickListener {
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "피자")
            startActivity(intent)
        }
        binding.imgPorkimage.setOnClickListener {
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "족발 보쌈")
            startActivity(intent)
        }
        binding.imgBokkiimage.setOnClickListener {
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "분식")
            startActivity(intent)
        }
        binding.imgBurgerimage.setOnClickListener {
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "기타")
            startActivity(intent)
        }
        binding.btnSeeAll.setOnClickListener {
            var intent3 = Intent(this, LivePostsActivity::class.java)
            startActivity(intent3)
        }
    }
}


