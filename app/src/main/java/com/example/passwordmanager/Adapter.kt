package com.example.passwordmanager

import android.content.Context
import android.content.Intent
import android.media.RouteListingPreference
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val itemList: List<RouteListingPreference.Item>,
    private val context: Context
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.passwordListActivityRecyclerView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Get the clicked item
                    val clickedItem = itemList[position]
                    // Create an Intent to start the Activity
                    val intent = Intent(context, EditExistingPasswordActivity::class.java).apply {

                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    /* override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val view = LayoutInflater.from(parent.context)
             .inflate(R.layout.activity_password_list, parent, false)
         return ViewHolder(view)
     }
     */

    override fun getItemCount(): Int = itemList.size
}