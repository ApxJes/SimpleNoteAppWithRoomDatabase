package com.apsmt.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.apsmt.notes.data.Notes

class AdapterClass: RecyclerView.Adapter<AdapterClass.ItemViewHolder>() {

    var notesList = emptyList<Notes>()

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.txvTitle)
        val description: TextView = itemView.findViewById(R.id.txvDescription)
        val toUpdate: ConstraintLayout = itemView.findViewById(R.id.clickToUpdate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_note, parent, false))
    }

    override fun getItemCount(): Int = notesList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = notesList[position]
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description

        holder.toUpdate.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToEditFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(addNotes: List<Notes>){
        this.notesList = addNotes
        notifyDataSetChanged()
    }
}