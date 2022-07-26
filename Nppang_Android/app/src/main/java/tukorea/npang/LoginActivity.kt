package tukorea.npang

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import tukorea.npang.databinding.ActivityLoginLayoutBinding


class LoginActivity : Activity(), View.OnClickListener {
    //firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth

    //google client
    private lateinit var googleSignInClient: GoogleSignInClient

    //Binding
    private lateinit var binding: ActivityLoginLayoutBinding
    val db = FirebaseFirestore.getInstance()    // Firestore 인스턴스 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreference = getSharedPreferences("sp1", Context.MODE_PRIVATE)
        val autologin = sharedPreference.getBoolean("autologin", false)
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            if (autologin) {
                val intent5 = Intent(this, CategoryActivity::class.java)
                startActivity(intent5)
            }
        }

        //btn_googleSignIn.setOnClickListener (this) // 구글 로그인 버튼
        binding.btnGoogleSignIn.setOnClickListener { signIn() }
        //회원가입이동창
        binding.sign.setOnClickListener {
            val intent2 = Intent(this, SignUpActivity::class.java)
            startActivity(intent2)
        }
        //회원정보찾기 이동창
        binding.btnFindMyInfo.setOnClickListener {
            val intent3 = Intent(this, FindMyInfoActivity::class.java)
            startActivity(intent3)
        }
        //로그인버튼 클릭후 카테고리 이동
        binding.btnLogin.setOnClickListener {
            if (binding.etEmail.length() == 0 || binding.etPassword.length() == 0) {
                Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                loginEmail(
                    binding.etEmail.text.toString().trim(),
                    binding.etPassword.text.toString()
                )
            }
        }
        //Google 로그인 옵션 구성. requestIdToken 및 Email 요청
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        //firebase auth 객체
        firebaseAuth = FirebaseAuth.getInstance()

        //자동로그인
        binding.keepSignedIn.setOnCheckedChangeListener { buttonView, ischecked ->
            val editor: SharedPreferences.Editor = sharedPreference.edit()
            editor.putBoolean("autologin", ischecked)

            editor.commit()
        }

    }


    // onStart. 유저가 앱에 이미 구글 로그인을 했는지 확인
    public override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account !== null) { // 이미 로그인 되어있을시 바로 메인 액티비티로 이동
            toMainActivity(firebaseAuth.currentUser)
        }
    } //onStart End

    // onActivityResult
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)

            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("LoginActivity", "Google sign in failed", e)
            }
        }
    } // onActivityResult End

    // firebaseAuthWithGoogle
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("LoginActivity", "firebaseAuthWithGoogle:" + acct.id!!)

        //Google SignInAccount 객체에서 ID 토큰을 가져와서 Firebase Auth로 교환하고 Firebase에 인증
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.w("LoginActivity", "firebaseAuthWithGoogle 성공", task.exception)
                    toMainActivity(firebaseAuth?.currentUser)
                } else {
                    Log.w("LoginActivity", "firebaseAuthWithGoogle 실패", task.exception)

                }
            }
    }// firebaseAuthWithGoogle END


    // toMainActivity
    fun toMainActivity(user: FirebaseUser?) {
        if (user != null) { // MainActivity 로 이동
            startActivity(Intent(this, CategoryActivity::class.java))
            finish()
        }
    } // toMainActivity End

    // signIn
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    // signIn End
    override fun onClick(p0: View?) {
    }

    private fun loginEmail(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            val intent4 = Intent(this, CategoryActivity::class.java)
            startActivity(intent4)
        }.addOnFailureListener { e ->
            Log.d("createEmail", "createEmail:$e ")
            Toast.makeText(this, "아이디/비밀번호 오류", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signOut() { // 로그아웃
        // Firebase sign out
        firebaseAuth.signOut()

        // Google sign out
        googleSignInClient.signOut().addOnCompleteListener(this) {
            //updateUI(null)
        }
    }

    private fun revokeAccess() { //회원탈퇴
        // Firebase sign out
        firebaseAuth.signOut()
        googleSignInClient.revokeAccess().addOnCompleteListener(this) {

        }
    }

    //private const val TAG = "GoogleActivity"
    companion object {
        const val RC_SIGN_IN = 99
    }
}