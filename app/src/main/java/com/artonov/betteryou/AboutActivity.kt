package com.artonov.betteryou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.artonov.betteryou.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener() {
            finish()
        }
    }
}