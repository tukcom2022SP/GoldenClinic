package tukorea.npang

import android.app.Activity
import android.os.Bundle
import android.util.Log
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
            val category = binding.etContents.text
            val contents = binding.etContents.text
            val postname = binding.etPostName.text
            val storeName = binding.etPostName.text //가게정보들어올때까지 임시로 세팅
            var PostInfoMation = hashMapOf(
                "category" to contents.toString().trim(),
                "contents" to contents.toString().trim(),
                "postname" to postname.toString().trim(),
                "storeName" to contents.toString().trim()
            )
            db.collection("LivePost").add(PostInfoMation)
                .addOnSuccessListener { documentReference ->
                    Log.d("LEePost", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("Post", "Error adding document", e)
                }
        }

    }
}