package tukorea.npang

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_category.*
import tukorea.npang.databinding.ActivityCategoryDetailsBinding

class CategoryDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chickenimage.setOnClickListener {
            binding.categorytitle.text = "카테고리-치킨"
        }
    }
}
