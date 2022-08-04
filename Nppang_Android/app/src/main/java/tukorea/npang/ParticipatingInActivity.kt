package tukorea.npang

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
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

    }
}