package tukorea.npang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tukorea.npang.databinding.ActivityMypageBinding

class MyPageActivity : Activity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMypageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Firebase.firestore
        super.onCreate(savedInstanceState)
        binding = ActivityMypageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnWritePost.setOnClickListener {
            var intent = Intent(this, MyPostActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogout.setOnClickListener {

        }

    }

    private fun signOut() { // 로그아웃
        // Firebase sign out
        firebaseAuth.signOut()

    }
}