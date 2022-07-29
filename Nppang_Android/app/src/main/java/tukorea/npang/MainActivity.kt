package tukorea.npang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    //firebase Auth
    private lateinit var Auth: FirebaseAuth

    //google client
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Auth = FirebaseAuth.getInstance()
        logout.setOnClickListener {
            Auth.signOut()
            val intent = Intent(this, LoginActivity::class.java) //로그인 페이지 이동
            startActivity(intent)
            this.finish()
        }
    }

}