package com.apsmt.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.apsmt.notes.data.NoteViewModel
import com.apsmt.notes.data.Notes
import com.apsmt.notes.databinding.FragmentEditBinding
import com.apsmt.notes.databinding.FragmentListBinding

class EditFragment : Fragment() {

    private  var _binding: FragmentEditBinding? = null
    private  val binding get() = _binding!!

    private lateinit var mNoteViewModel: NoteViewModel
    private val args: EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditBinding.inflate(inflater, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.edtUpdateTitle.setText(args.currentNote.title)
        binding.edtUpdateDescription.setText(args.currentNote.description)

        binding.btnUpdate.setOnClickListener {
            updateNote()
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateNote(){
        val title = binding.edtUpdateTitle.text.toString()
        val description =binding.edtUpdateDescription.text.toString()

        if(title.isNotEmpty() && description.isNotEmpty()){
            val updateNote = Notes(
                title = title,
                description = description,
                id = args.currentNote.id)

            mNoteViewModel.updateNotes(updateNote)
            Toast.makeText(
                requireContext(),
                "Successful update",
                Toast.LENGTH_SHORT
            ).show()

            findNavController().navigate(R.id.action_editFragment_to_listFragment)
        }

        else{
            Toast.makeText(
                requireContext(),
                "Fill out title and description",
                Toast.LENGTH_SHORT
            ).show()
        }
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
                deleteUser()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser(){
        AlertDialog.Builder(requireContext())
            .setTitle("Delete ${args.currentNote.title}")
            .setMessage("Are you sure to delete ${args.currentNote.title}?")
            .setPositiveButton("Yes") { _, _ ->
                mNoteViewModel.deleteNotes(args.currentNote)
                Toast.makeText(
                    requireContext(),
                    "Successfully delete ${args.currentNote.title}",
                    Toast.LENGTH_SHORT
                ).show()

                findNavController().navigate(R.id.action_editFragment_to_listFragment)
            }
            .setNegativeButton("Cancel") { _, _ ->
                Toast.makeText(
                    requireContext(),
                    "Cancel to delete ${args.currentNote}",
                    Toast.LENGTH_SHORT
                ).show()
            }.create().show()
    }
}