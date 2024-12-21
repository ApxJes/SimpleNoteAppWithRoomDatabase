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
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apsmt.notes.data.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment() {

    private lateinit var floatingAddBtn: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        floatingAddBtn = view.findViewById(R.id.floatingBtnAdd)
        floatingAddBtn.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        recyclerView = view.findViewById(R.id.recyclerView)

        val adapter = AdapterClass()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        mNoteViewModel.readAllNotes.observe(viewLifecycleOwner){ addNotes ->
            adapter.setData(addNotes)
        }

        setHasOptionsMenu(true)


        return view
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
}