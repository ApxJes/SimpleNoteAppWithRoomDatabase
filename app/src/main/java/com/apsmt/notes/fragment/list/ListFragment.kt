package com.apsmt.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.apsmt.notes.data.NoteViewModel
import com.apsmt.notes.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private  var _binding: FragmentListBinding? = null
    private  val binding get() = _binding!!

    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.floatingBtnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        val adapter = AdapterClass()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        mNoteViewModel.readAllNotes.observe(viewLifecycleOwner){ addNotes ->
            adapter.setData(addNotes)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    @Deprecated("Deprecated in Java", ReplaceWith("inflater.inflate(R.menu.delete_menu, menu)"))
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    @Deprecated("Deprecated in Java", ReplaceWith(
        "super.onOptionsItemSelected(item)",
        "androidx.fragment.app.Fragment"
    )
    )
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.delete -> {
                deleteAllUser()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser(){
        AlertDialog.Builder(requireContext())
            .setTitle("Delete everything")
            .setMessage("Are you sure to delete everything?")
            .setPositiveButton("Yes") { _, _ ->
                mNoteViewModel.deleteAllNotes()
                Toast.makeText(
                    requireContext(),
                    "Successfully delete",
                    Toast.LENGTH_SHORT
                ).show()

            }
            .setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(
                    requireContext(),
                    "Cancel to delete",
                    Toast.LENGTH_SHORT
                ).show()
            }.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}