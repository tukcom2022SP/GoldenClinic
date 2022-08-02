package tukorea.npang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import tukorea.npang.databinding.ActivityCategoryDetailsBinding

class CategoryDetailsActivity() : Activity() {
    //binding
    private lateinit var binding: ActivityCategoryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //카테고리 Name설정
        val firstintent = intent
        binding.tvCategorytitle.text = firstintent.getStringExtra("카테고리")

        binding.btnWritePost.setOnClickListener {
            val intent = Intent(this, PostingActivity::class.java)
            startActivity(intent)
        }

    }


}

