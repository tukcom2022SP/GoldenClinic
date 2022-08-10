package tukorea.npang

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import tukorea.npang.databinding.ActivityMypageBinding

class MyPageActivity : Activity() {
    val db = FirebaseFirestore.getInstance()
    var firebaseAuth=FirebaseAuth.getInstance()
    private lateinit var binding: ActivityMypageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMypageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnWritePost.setOnClickListener {
            var intent = Intent(this, MyPostActivity::class.java)
            startActivity(intent)
        }
        binding.btnParticipate.setOnClickListener {
            var intent2 = Intent(this, MyParticipateActivity::class.java)
            startActivity(intent2)
        }
        binding.btnLogout.setOnClickListener {
            signOut()
            var intent3=Intent(this,LoginActivity::class.java)
            startActivity(intent3)
        }
        //현재유저 이메일 알려주기
        binding.tvUserEmail.text=firebaseAuth.currentUser?.email


    }

    private fun signOut() {
        val sharedPreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putBoolean("autologin", false)

        editor.commit()// 로그아웃
        // Firebase sign out
        firebaseAuth.signOut()


    }
}