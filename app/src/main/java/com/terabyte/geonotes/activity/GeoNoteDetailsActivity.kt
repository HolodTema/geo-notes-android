package com.terabyte.geonotes.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.terabyte.geonotes.R
import com.terabyte.geonotes.databinding.ActivityGeoNoteDetailsBinding
import com.terabyte.geonotes.viewmodel.GeoNoteDetailsViewModel

class GeoNoteDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGeoNoteDetailsBinding

    private val viewModel: GeoNoteDetailsViewModel by lazy {
        ViewModelProvider(this)[GeoNoteDetailsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeoNoteDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.buttonBack.setOnClickListener {
            backToMainActivity()
        }
    }

    override fun onStop() {
        super.onStop()

    }

    private fun backToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}