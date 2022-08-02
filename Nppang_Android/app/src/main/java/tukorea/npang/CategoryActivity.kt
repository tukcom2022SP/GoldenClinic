package tukorea.npang

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import tukorea.npang.databinding.ActivityCategoryBinding

class CategoryActivity : Activity() {
    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgChickenimage.setOnClickListener {
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "치킨")
            startActivity(intent)
        }
        binding.imgChinaimage.setOnClickListener {
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "중식")
            startActivity(intent)
        }
        binding.imgPizzaimage.setOnClickListener {
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "피자")
            startActivity(intent)
        }
        binding.imgPorkimage.setOnClickListener {
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "족발/보쌈")
            startActivity(intent)
        }
        binding.imgBokkiimage.setOnClickListener {
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "분식")
            startActivity(intent)
        }
        binding.imgBurgerimage.setOnClickListener {
            val intent = Intent(this, CategoryDetailsActivity::class.java)
            intent.putExtra("카테고리", "패스트푸드")
            startActivity(intent)
        }
        binding.btnSeeAll.setOnClickListener {
            var intent3=Intent(this,LivePostsActivity::class.java)
            startActivity(intent3)
        }
    }
}


