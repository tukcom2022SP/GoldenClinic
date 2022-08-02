package tukorea.npang

import android.app.Activity
import android.os.Bundle
import tukorea.npang.databinding.ActivityLivePostsBinding

class LivePostsActivity :Activity(){
    private lateinit var binding:ActivityLivePostsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLivePostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}