package com.terabyte.geonotes.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.terabyte.geonotes.R
import com.terabyte.geonotes.application.MyApplication
import com.terabyte.geonotes.databinding.FragmentListBinding
import com.terabyte.geonotes.room.RoomManager
import com.terabyte.geonotes.ui.RecyclerNotesAdapter
import com.terabyte.geonotes.viewmodel.MainViewModel


class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        viewModel.liveDataNotes.observe(viewLifecycleOwner) { notes ->
            binding.recyclerNotes.adapter = RecyclerNotesAdapter(notes)
            binding.textAmountNotes.text = "Amount notes: ${notes.size}"
        }

        return binding.root
    }


}