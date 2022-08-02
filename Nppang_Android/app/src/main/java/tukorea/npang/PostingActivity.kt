package tukorea.npang

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import tukorea.npang.databinding.ActivityLoginLayoutBinding
import tukorea.npang.databinding.ActivityParticipatingInBinding
import tukorea.npang.databinding.ActivityPostingBinding

private lateinit var binding: ActivityPostingBinding

class PostingActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostingBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_posting)

    }
}