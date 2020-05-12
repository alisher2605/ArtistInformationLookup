package com.example.artistinformationlookup.Adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.artistinformationlookup.Entities.CategoryItem
import com.example.artistinformationlookup.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.show_choice_adapter.view.*

class CategoryAdapter(
    private val items: List<CategoryItem> = listOf(),
    private val onItemClick: (CategoryItem) -> Unit
): RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.show_choice_adapter, parent, false)
        return  ItemViewHolder(view)
    }

    override fun getItemCount() = items.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    inner class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bindItem(item: CategoryItem) {
            view.category_text.text = item.categoryName
            Picasso.get().load(item.imageUrl)
                .into(view.category_image)
            view.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}