package tukorea.npang

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import tukorea.npang.databinding.ActivitySignUpBinding


class SignUpActivity : Activity() {
    //db선언
    val db = Firebase.firestore

    //binding선언
    private lateinit var binding: ActivitySignUpBinding

    //firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Auth 경로설정
        firebaseAuth = FirebaseAuth.getInstance()

        //회원가입시 파이어베이스 정보 등록,파이어스토어 연동
        binding.btnSignUp.setOnClickListener {
            createEmail(
                binding.etSignUpEmail.text.toString().trim(),
                binding.etSignUpPasswd.text.toString().trim()
            )
            //
            val userBankAccount = binding.etAccount.text
            val userEmail = binding.etSignUpEmail.text
            val userName = binding.etUserName.text
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
            Log.d("btn", "onCreate: ${binding.etSignUpEmail.text}")
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