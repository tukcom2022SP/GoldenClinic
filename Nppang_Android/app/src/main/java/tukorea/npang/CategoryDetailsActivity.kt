package tukorea.npang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_category_details.*
import tukorea.npang.databinding.ActivityCategoryDetailsBinding

class CategoryDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstintent = intent
        categorytitle.text = firstintent.getStringExtra("카테고리")


    }
}
