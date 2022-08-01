package tukorea.npang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_category.*
import tukorea.npang.databinding.ActivityCategoryBinding

class CategoryActivity : Activity() {
    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chickenimage.setOnClickListener {
            val intent1 = Intent(this, CategoryDetailsActivity::class.java)
            startActivity(intent1)
        }
    }
}