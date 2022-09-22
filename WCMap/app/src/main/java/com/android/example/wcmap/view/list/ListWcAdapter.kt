package com.android.example.wcmap.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.example.wcmap.R
import com.android.example.wcmap.databinding.FragmentListBinding
import com.android.example.wcmap.model.wc.Records

// Adapter is between the Recycler View and the viewHolder.
class ListWcAdapter(private val clickListener: WcListener) : RecyclerView.Adapter<TextItemViewHolder>() {

    // List of toilets.
    var data = listOf<Records>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    // Create the ViewHolder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        // Get a layoutInflater from the context attached to the parent view.
        val layoutInflater = LayoutInflater.from(parent.context)
        // Inflate the layout "item" in a view.
        val view = layoutInflater
            .inflate(R.layout.fragment_list_item, parent, false) as TextView
        // Create a new ViewHolder with the view.
        return TextItemViewHolder(view)
    }

    // Fills the ViewHolder with data.
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        // Get the corresponding data (wc) in the model.
        val dataToDisplay = data[position]

        // Update UI of the item.
        holder.textView.text = dataToDisplay.fields.specloc

        // Make the item clickable.
        holder.textView.setOnClickListener{clickListener.onClick(dataToDisplay)}
    }

    // Number of items in wc list.
    override fun getItemCount(): Int {
        return data.size
    }
}

// Declaration of the ViewHolder, component that will contain the view of the item and its position.
class TextItemViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)


// The class that handles the event at an item level.
class WcListener(val clickListener: (id: Records) -> Unit){
    fun onClick(id: Records) = clickListener(id)
}
