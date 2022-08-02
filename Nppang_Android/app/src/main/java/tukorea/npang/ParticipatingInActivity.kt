package tukorea.npang

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import tukorea.npang.databinding.ActivityLoginLayoutBinding
import tukorea.npang.databinding.ActivityParticipatingInBinding

private lateinit var binding: ActivityParticipatingInBinding

class ParticipatingInActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipatingInBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_participating_in)


    }
}