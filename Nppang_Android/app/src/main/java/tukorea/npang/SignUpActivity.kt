package tukorea.npang

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*
import tukorea.npang.databinding.ActivitySignUpBinding


class SignUpActivity : Activity() {
    //db선언
    val db = Firebase.firestore
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
            createEmail(
                et_sign_up_email.text.toString().trim(),
                et_sign_up_passwd.text.toString().trim()
            )
            val userBankAccount = binding.etSignUpEmail.text
            val userEmail = binding.etUserName.text
            val userName = binding.etAccount.text
            val userPhoneNumber = binding.etPhoneNumber.text
            var UserInfoMation = hashMapOf(
                "userBankAccount" to userBankAccount.toString().trim(),
                "userEmail" to userEmail.toString().trim(),
                "userName" to userName.toString().trim(),
                "userPhoneNumber" to userPhoneNumber.toString().trim()
            )


            db.collection("UserData").add(UserInfoMation)
                .addOnSuccessListener { documentReference ->
                    Log.d("userdata", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("userdata", "Error adding document", e)
                }
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