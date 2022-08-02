package tukorea.npang

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import tukorea.npang.databinding.ActivityLoginLayoutBinding
import tukorea.npang.databinding.ActivityParticipatingInBinding



class ParticipatingInActivity : Activity() {
    private lateinit var binding: ActivityParticipatingInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipatingInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //메뉴 관련 스피너
        val menuList = resources.getStringArray(R.array.menuList)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, menuList)
        binding.spinnerMenuChoice.adapter = adapter

    }
}