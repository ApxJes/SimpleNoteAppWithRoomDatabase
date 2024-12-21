package com.apsmt.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.apsmt.notes.data.NoteViewModel
import com.apsmt.notes.data.Notes


class AddFragment : Fragment() {

    private lateinit var mNoteViewModel: NoteViewModel
    private lateinit var inputTitle: EditText
    private lateinit var inputDescription: EditText
    private lateinit var btnAdd: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        inputTitle = view.findViewById(R.id.edtInputTitle)
        inputDescription = view.findViewById(R.id.edtInputDescription)
        btnAdd = view.findViewById(R.id.btnDone)

        btnAdd.setOnClickListener {
            addNotesToListFragment()
        }

        return view
    }

    private fun addNotesToListFragment(){
        val title = inputTitle.text.toString()
        val description = inputDescription.text.toString()

       if(title.isNotEmpty() && description.isNotEmpty()){
           val notes = Notes(title = title, description = description, id = 0)
           mNoteViewModel.addNotes(notes)
           Toast.makeText(
               requireContext(),
               "Successful added",
               Toast.LENGTH_SHORT
           ).show()

           findNavController().navigate(R.id.action_addFragment_to_listFragment)
       }

        else {
            Toast.makeText(
                requireContext(),
                "Fill out title and description",
                Toast.LENGTH_SHORT
            ).show()
       }
    }

}