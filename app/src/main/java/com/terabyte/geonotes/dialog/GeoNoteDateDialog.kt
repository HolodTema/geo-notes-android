package com.terabyte.geonotes.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.terabyte.geonotes.viewmodel.GeoNoteDetailsViewModel
import java.util.Calendar
import java.util.Date

class GeoNoteDateDialog : DialogFragment() {
    private val viewModel: GeoNoteDetailsViewModel by lazy {
        ViewModelProvider(requireActivity())[GeoNoteDetailsViewModel::class.java]
    }

    private val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        viewModel.geoNote.date = calendar.time
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(FRAGMENT_ARG_DATE, Date::class.java) as Date
        } else {
            arguments?.getSerializable(FRAGMENT_ARG_DATE) as Date
        }
        val calendar = Calendar.getInstance()
        calendar.time = date

        return DatePickerDialog(
            requireContext(),
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    companion object {
        private const val FRAGMENT_ARG_DATE = "fragmentArtDate"

        fun newInstance(date: Date): GeoNoteDateDialog {
            val bundle = Bundle().apply {
                putSerializable(FRAGMENT_ARG_DATE, date)
            }
            return GeoNoteDateDialog().apply {
                arguments = bundle
            }
        }
    }

}