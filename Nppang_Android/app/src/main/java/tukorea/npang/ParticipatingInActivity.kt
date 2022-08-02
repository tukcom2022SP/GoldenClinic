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


    }
}