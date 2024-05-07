package com.zero.shoppet.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zero.shoppet.Model.CategoryModel

import com.zero.shoppet.databinding.ViewholderLoaiBinding

class CategoryAdapter(val items:MutableList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private lateinit var context: Context
    inner class ViewHolder(val binding: ViewholderLoaiBinding):
RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        context = parent.context
        val binding=ViewholderLoaiBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
       val item=items[position]
        holder.binding.titleCat.text=item.title
        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pigCat)

    }

    override fun getItemCount(): Int =items.size
}