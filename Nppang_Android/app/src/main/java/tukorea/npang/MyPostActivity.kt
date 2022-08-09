package tukorea.npang

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import tukorea.npang.databinding.ActivityMyPostBinding

class MyPostActivity : Activity() {
    //firebase Auth
    val db = FirebaseFirestore.getInstance()
    private var itemList = arrayListOf<ListLayout>()
    private var itemList2 = arrayListOf<String>()
    private lateinit var binding:ActivityMyPostBinding
    val adapter=ListAdapter(itemList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var firebaseAuth= FirebaseAuth.getInstance()
        binding= ActivityMyPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvList.layoutManager=
            LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.rvList.adapter=adapter

        db.collection("LivePost").get()
                .addOnSuccessListener { result->
               // if(itemList2[0]==firebaseAuth.currentUser?.uid.toString()){
                    itemList.clear()
                    for (document in result) {  // 가져온 문서들은 result에 들어감
                        val item =
                            ListLayout(
                                document["contents"] as String,
                                document["postname"] as String,
                                document["category"] as String,
                                document["storeName"] as String,
                                document["group"]as ArrayList<String>
                            )
                        if(item.group[0]==firebaseAuth.currentUser?.uid){
                            itemList.add(item)
                        }

                    }
                    adapter.notifyDataSetChanged()

            }.addOnFailureListener { exception ->
                // 실패할 경우
                Log.w("MainActivity", "Error getting documents: $exception")
            }

    }
}