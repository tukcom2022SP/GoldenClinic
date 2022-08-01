package tukorea.npang

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*
import tukorea.npang.databinding.ActivitySignUpBinding


class SignUpActivity : Activity() {
    private lateinit var binding: ActivitySignUpBinding

    //firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        //회원가입시 파이어베이스 정보 등록
        binding.btnSignUp.setOnClickListener {
            createEmail(et_sign_up_email.text.toString(), et_sign_up_passwd.text.toString())
            Log.d("btn", "onCreate: ${et_sign_up_email.text}")
        }
    }

    private fun createEmail(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Toast.makeText(this, "good", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { e ->
                Log.d("createEmail", "createEmail:$e ")
                Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show()
            }
    }
}