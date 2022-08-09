package tukorea.npang

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import tukorea.npang.databinding.ActivityFindMyInfoBinding

class FindMyInfoActivity : Activity() {

    //firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var binding: ActivityFindMyInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindMyInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnResetPasswd.setOnClickListener {
            if (binding.etFindEmail.length() == 0) {
                Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                findPassword()
            }
        }
    }

    fun findPassword() {
        FirebaseAuth.getInstance()
            .sendPasswordResetEmail(binding.etFindEmail.text.toString().trim())
            .addOnSuccessListener {
                Toast.makeText(this, "비밀번호 재설정 이메일을 전송했습니다.", Toast.LENGTH_SHORT).show()
            }
    }
}
