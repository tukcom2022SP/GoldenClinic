package tukorea.npang

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
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
            if (binding.etSignUpEmail.length() == 0 || binding.etSignUpPasswd.length() == 0
                || binding.etSignUpPasswdConfirm.length() == 0 || binding.etUserName.length() == 0
                || binding.etPhoneNumber.length() == 0 || binding.etAccount.length() == 0) {
                Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
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
                    "userPhoneNumber" to userPhoneNumber.toString().trim(),
                    "uid" to firebaseAuth.currentUser?.uid
                )
                //user-email document 이름 설정

                db.collection("UserData").add(UserInfoMation)
                    .addOnSuccessListener { documentReference ->

                    }
                    .addOnFailureListener { e ->
                        Log.w("userdata", "Error adding document", e)
                    }
                Log.d("btn", "onCreate: ${binding.etSignUpEmail.text}")
            }
        }
    }

    private fun createEmail(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Toast.makeText(this, "회원가입성공 로그인창으로 이동합니다", Toast.LENGTH_SHORT).show()
                val movelogin = Intent(this, LoginActivity::class.java)
                startActivity(movelogin)
            }.addOnFailureListener {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Email-Error")
                    .setMessage("Email 중복입니다")
                    .setPositiveButton("확인", null)
                alertDialog.show()
            }
    }
}