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
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "치킨")
            startActivity(intent)
        }
        chinaimage.setOnClickListener {
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "중식")
            startActivity(intent)
        }
        pizzaimage.setOnClickListener {
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "피자")
            startActivity(intent)
        }
        porkimage.setOnClickListener {
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "족발/보쌈")
            startActivity(intent)
        }
        bokkiimage.setOnClickListener {
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "분식")
            startActivity(intent)
        }
        burgerimage.setOnClickListener {
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "패스트푸드")
            startActivity(intent)
        }
    }
}


