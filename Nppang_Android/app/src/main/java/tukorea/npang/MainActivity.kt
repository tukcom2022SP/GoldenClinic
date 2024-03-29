package tukorea.npang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import tukorea.npang.databinding.ActivityMainBinding

class MainActivity : Activity() {
    //firebase Auth
    private lateinit var auth: FirebaseAuth

    //Binding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java) //로그인 페이지 이동
            startActivity(intent)
            this.finish()
        }
    }
}