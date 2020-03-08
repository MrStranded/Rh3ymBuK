package ch.epicodes.buk.application

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import ch.epicodes.buk.R
import ch.epicodes.buk.domain.Library
import kotlinx.android.synthetic.main.buk_item.view.*

class BukAdapter(private val context: Context, private val library: Library): RecyclerView.Adapter<BukAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val bukItem: LinearLayout): RecyclerView.ViewHolder(bukItem)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {// create a new view
        val button = LayoutInflater.from(parent.context)
            .inflate(R.layout.buk_item, parent, false) as LinearLayout
        return MyViewHolder(button)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bukItem.bukButton.text = library.buks[position].name

        holder.bukItem.bukButton.setOnClickListener {
            val intent = Intent(context, BukActivity::class.java).apply {
                putExtra("buk", library.buks[position].name)
            }
            context.startActivity(intent)
        }

        holder.bukItem.settingsButton.setOnClickListener {
            val intent = Intent(context, BukSettingsActivity::class.java).apply {
                putExtra("buk", library.buks[position].name)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = library.buks.size

}