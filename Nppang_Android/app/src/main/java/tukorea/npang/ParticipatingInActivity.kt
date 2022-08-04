package tukorea.npang

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import tukorea.npang.databinding.ActivityLoginLayoutBinding

import tukorea.npang.databinding.ActivityParticipatingInBinding


class ParticipatingInActivity : Activity() {
    private lateinit var binding: ActivityParticipatingInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipatingInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //RecycleView에서 선택된 정보 가져오기
        val Postsintent = intent
        binding.tvPpOstName.text = Postsintent.getStringExtra("postname")
        binding.tvPpContents.text=Postsintent.getStringExtra("contents")
        binding.tvPpStoreName.text=Postsintent.getStringExtra("storename")

        //메뉴 관련 스피너
        val menuList = resources.getStringArray(R.array.menuList)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList)
        binding.spinnerMenuChoice.adapter = adapter

        //참가 완료 AlertDialog
        binding.btnParticipate.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setTitle("참가완료")
                .setMessage("주문금액 : ")
                .setMessage("배달비 합계 : ")
                .setMessage("본인 부담 배달비 :  ")
                .setMessage("결제 예정시작 : ")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, which ->
                        Toast.makeText(this, "확인", Toast.LENGTH_SHORT).show()
                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, which ->
                        Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show()
                    })
            builder.show()
        }
    }
}