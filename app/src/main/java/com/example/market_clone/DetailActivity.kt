package com.example.market_clone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.market_clone.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private var isLiked = false
    val moneyFormat: NumberFormat = NumberFormat.getInstance(Locale.getDefault())

    private val receivedItem: ProductInfo?
        get() = intent.getParcelableExtra<ProductInfo>("productInfo")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receivedItem?.let {
            _binding!!.detailImage.setImageResource(it.image)
            _binding!!.nickname.text = it.nickname
            _binding!!.location.text = it.location
            _binding!!.detailName.text = it.productName
            _binding!!.detailContent.text = it.detailText
            _binding!!.price.text = "${moneyFormat.format(it.price)}원"
            isLiked = it.isLiked == true

            _binding!!.detailLikeIcon.setImageResource(if (isLiked) {
                R.drawable.ic_fav_heart
            } else {
                R.drawable.ic_heart
            })

            _binding!!.backButton.setOnClickListener {
                backMain()
            }

            _binding!!.detailLikeIcon.setOnClickListener {
                if (!isLiked) {
                    _binding!!.detailLikeIcon.setImageResource(R.drawable.ic_fav_heart)
                    Snackbar.make(_binding!!.constLayout, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                    isLiked = true
                } else {
                    _binding!!.detailLikeIcon.setImageResource(R.drawable.ic_heart)
                    Snackbar.make(_binding!!.constLayout, "관심 목록에서 삭제되었습니다.", Snackbar.LENGTH_SHORT).show()
                    isLiked = false
                }
            }
        }
    }

    private fun backMain() {
        val likePosition = intent.getIntExtra("likePosition", 0)
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("likePosition", likePosition)
            putExtra("isLiked", isLiked)
        }
        setResult(RESULT_OK, intent)
        if (!isFinishing) finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        backMain()
    }
}