package kr.ac.kpu.gcproject

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //firebase Auth
    private lateinit var Auth: FirebaseAuth
    //google client
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Auth= FirebaseAuth.getInstance()
        logout.setOnClickListener {
            Auth.signOut()
            var intent=Intent(this,LoginActivity::class.java) //로그인 페이지 이동
            startActivity(intent)
            this.finish()
        }
    }

}