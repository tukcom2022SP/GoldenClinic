package tukorea.npang

import android.app.Activity
import android.os.Bundle
import tukorea.npang.databinding.ActivityMypageBinding

class MyPageActivity : Activity() {
    private lateinit var binding: ActivityMypageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}