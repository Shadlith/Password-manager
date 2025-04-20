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
    private val itemList: List<PasswordEntity>,
    private val context: Context
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val siteNameTextField: TextView = itemView.findViewById<TextView>(R.id.site_name)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Get the clicked item
                    val clickedItem = itemList[position]
                    // Create an Intent to start the Activity
                    val intent = Intent(context, EditExistingPasswordActivity::class.java)
                    intent.putExtra("primary_key", itemList[position].id)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = itemList.get(position)
        val siteName = holder.siteNameTextField.setText(entry.siteName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val listItemView = inflater.inflate(R.layout.password_row_item, parent, false)
        return ViewHolder(listItemView)
    }

    /* override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val view = LayoutInflater.from(parent.context)
             .inflate(R.layout.activity_password_list, parent, false)
         return ViewHolder(view)
     }
     */

    override fun getItemCount(): Int = itemList.size
}