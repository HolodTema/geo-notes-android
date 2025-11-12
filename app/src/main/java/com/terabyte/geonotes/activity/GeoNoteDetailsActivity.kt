package com.terabyte.geonotes.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.terabyte.geonotes.R
import com.terabyte.geonotes.databinding.ActivityGeoNoteDetailsBinding
import com.terabyte.geonotes.room.GeoNote
import com.terabyte.geonotes.viewmodel.GeoNoteDetailsViewModel
import java.text.SimpleDateFormat
import java.util.Locale
import com.terabyte.geonotes.viewmodel.GeoNoteDetailsViewModel.ActivityMode

class GeoNoteDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGeoNoteDetailsBinding

    private val viewModel: GeoNoteDetailsViewModel by lazy {
        ViewModelProvider(this)[GeoNoteDetailsViewModel::class.java]
    }

    private var isButtonDeleteClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeoNoteDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val geoNoteFromIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(INTENT_KEY_GEO_NOTE, GeoNote::class.java)
        } else {
            intent.getParcelableExtra(INTENT_KEY_GEO_NOTE)
        }
        if (!viewModel.isInitialized()) {
            if (geoNoteFromIntent == null) {
                viewModel.activityMode = ActivityMode.ACTIVITY_MODE_INSERT_NOTE
                viewModel.insertGeoNote()
            }
            else {
                viewModel.activityMode = ActivityMode.ACTIVITY_MODE_UPDATE_NOTE
                viewModel.geoNote = geoNoteFromIntent
            }
        }
        updateUI(viewModel.geoNote)
    }

    override fun onStart() {
        super.onStart()
        binding.buttonBack.setOnClickListener {
            backToMainActivity()
        }

        binding.editNoteText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                s?.let {
                    viewModel.geoNote.text = s.toString()
                }
            }
        })

        binding.editLatitude.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                s?.let {
                    viewModel.geoNote.latitude = s.toString().toDouble()
                }
            }
        })

        binding.editLongitude.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                s?.let {
                    viewModel.geoNote.longitude = s.toString().toDouble()
                }
            }
        })

        binding.buttonDelete.setOnClickListener {
            viewModel.deleteGeoNote {
                isButtonDeleteClicked = true
                backToMainActivity()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        if (!isButtonDeleteClicked) {
            viewModel.updateGeoNote()
        }
    }

    private fun updateUI(geoNote: GeoNote) {
        binding.editNoteText.setText(geoNote.text)
        binding.editLatitude.setText(geoNote.latitude.toString())
        binding.editLongitude.setText(geoNote.longitude.toString())

        binding.colorIndicator.background.setTint(geoNote.color)

        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        binding.textDate.text = dateFormat.format(geoNote.date)

        if (viewModel.activityMode == ActivityMode.ACTIVITY_MODE_INSERT_NOTE) {
            binding.textNoteDetailsMode.text = getString(R.string.note_details_mode_insert_header)
        }
        if (viewModel.activityMode == ActivityMode.ACTIVITY_MODE_UPDATE_NOTE) {
            binding.textNoteDetailsMode.text = getString(R.string.note_details_mode_update_header)
        }
    }

    private fun backToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val INTENT_KEY_GEO_NOTE = "intentKeyGeoNote"

        fun newIntent(context: Context, geoNote: GeoNote? = null): Intent {
            val intent = Intent(context, GeoNoteDetailsActivity::class.java)
            intent.putExtra(INTENT_KEY_GEO_NOTE, geoNote)
            return intent
        }
    }
}