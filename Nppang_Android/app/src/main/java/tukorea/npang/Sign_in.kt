package tukorea.npang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_layout.*

class Sign_in : AppCompatActivity() {
    //firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth
    //google client
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        sign_in.setOnClickListener {
            createEmail()
        }
    }
    private fun createEmail(){
        firebaseAuth.createUserWithEmailAndPassword("","").addOnCompleteListener(this){
                it ->
            if(it.isSuccessful){
                var user=firebaseAuth.currentUser
                Toast.makeText(this,"Authentication success", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Authentication failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun loginEmail(){
        firebaseAuth.signInWithEmailAndPassword("","").addOnCompleteListener(this){
                it->
            if(it.isSuccessful){
                Toast.makeText(this,"signInWithEmail success", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"signInWithEmail failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}