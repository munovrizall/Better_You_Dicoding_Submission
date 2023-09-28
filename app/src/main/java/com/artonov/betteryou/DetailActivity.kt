package com.artonov.betteryou

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.artonov.betteryou.databinding.ActivityDetailBinding
import com.artonov.betteryou.model.Book
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_BOOK = "extra_book"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataBook = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra<Book>(EXTRA_BOOK, Book::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Book>(EXTRA_BOOK)
        }

        binding.apply {
            tvName.text = dataBook?.bookName
            tvYear.text = dataBook?.bookYear
            tvAuthor.text = dataBook?.bookAuthor
            tvDescription.text = dataBook?.bookDescription
            tvTitle.text = dataBook?.bookName
            ivBack.setOnClickListener() {
                finish()
            }
        }

        Glide.with(this)
            .load(dataBook?.bookImages) // URL Gambar
            .into(binding.bookPhoto)

        buttonSetup(dataBook?.bookLink)

    }

    private fun buttonSetup(link: String?) {
        binding.btnBuy.setOnClickListener() {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(link)

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "Tidak ada browser yang mendukung", Toast.LENGTH_SHORT).show()
            }
        }

        binding.actionShare.setOnClickListener() {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, link)

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(intent, "Bagikan link melalui"))
            } else {
                Toast.makeText(this, "Tidak ada aplikasi yang mendukung", Toast.LENGTH_SHORT).show()
            }

        }
    }
}