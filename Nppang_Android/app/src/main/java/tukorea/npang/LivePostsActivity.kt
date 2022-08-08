package tukorea.npang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import tukorea.npang.databinding.ActivityLivePostsBinding
import java.util.ArrayList

class LivePostsActivity : Activity() {
    //binding
    private lateinit var binding: ActivityLivePostsBinding
    val db = FirebaseFirestore.getInstance()    // Firestore 인스턴스 선언
    val itemList = arrayListOf<ListLayout>()    // 리스트 아이템 배열
    val adapter = ListAdapter(itemList)         // 리사이클러 뷰 어댑터
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLivePostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //리싸이클뷰 connect
        binding.rvList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvList.adapter = adapter

        //게시물클릭시 해당게시물로이동
        adapter.setItemClickListner(object :ListAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val intent = Intent(this@LivePostsActivity, ParticipatingInActivity::class.java)
                intent.putExtra("postname", "${itemList[position].postname}")
                intent.putExtra("contents", "${itemList[position].contents}")
                intent.putExtra("storename", "${itemList[position].storeName}")
                startActivity(intent)
            }
        })

        //Firebase connect
        db.collection("LivePost")   // 작업할 컬렉션
            .get()      // 문서 가져오기
            .addOnSuccessListener { result ->
                // 성공할 경우
                itemList.clear()
                for (document in result) {  // 가져온 문서들은 result에 들어감
                    val item =
                        ListLayout(document["contents"] as String, document["postname"] as String,document["category"]as String,document["storeName"]as String)
                    itemList.add(item)
                }
                adapter.notifyDataSetChanged()  // 리사이클러 뷰 갱신
            }
            .addOnFailureListener { exception ->
                // 실패할 경우
                Log.w("MainActivity", "Error getting documents: $exception")
            }

    }
}