package tukorea.npang

import android.app.Activity
import android.os.Bundle
import tukorea.npang.databinding.ActivityCategoryBinding

class CategoryActivity : Activity() {
    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}