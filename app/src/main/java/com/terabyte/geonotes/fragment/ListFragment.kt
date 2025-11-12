package com.terabyte.geonotes.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.terabyte.geonotes.R
import com.terabyte.geonotes.databinding.FragmentListBinding
import com.terabyte.geonotes.room.GeoNote
import com.terabyte.geonotes.ui.GeoNoteAdapter
import com.terabyte.geonotes.viewmodel.MainViewModel


class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    private lateinit var adapter: GeoNoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        adapter = GeoNoteAdapter(layoutInflater, ::onListItemClicked)
        binding.recyclerNotes.adapter = adapter

        binding.textAmountNotes.text = getString(R.string.amount_notes, 0)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.liveDataGeoNotes.observe(viewLifecycleOwner) { geoNotes ->
            adapter.submitList(geoNotes)
            binding.textAmountNotes.text = getString(R.string.amount_notes, geoNotes.size)
        }

        binding.buttonAddNote.setOnClickListener {
            
        }
    }

    private fun onListItemClicked(geoNote: GeoNote) {

    }

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }
}