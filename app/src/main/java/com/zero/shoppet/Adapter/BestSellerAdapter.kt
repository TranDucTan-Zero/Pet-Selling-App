package com.zero.shoppet.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.zero.shoppet.Activity.DetailActivity
import com.zero.shoppet.Model.ItemsModel
import com.zero.shoppet.databinding.ViewholderBanchayBinding
import com.zero.shoppet.databinding.ViewholderLoaiBinding

class BestSellerAdapter(val items:MutableList<ItemsModel>):RecyclerView.Adapter<BestSellerAdapter.Viewholder>() {
    private var context:Context?=null
    class Viewholder(val binding: ViewholderBanchayBinding):
    RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BestSellerAdapter.Viewholder {
        context= parent.context
        val binding=ViewholderBanchayBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: BestSellerAdapter.Viewholder, position: Int) {
        holder.binding.name.text= items[position].title
        holder.binding.price.text= items[position].price.toString()+"đ"
        holder.binding.star.text=items[position].rating.toString()
        val repuestOption =RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .apply(repuestOption)
            .into(holder.binding.ptcBestSeller)

holder.itemView.setOnClickListener{
    val intent=Intent(holder.itemView.context, DetailActivity::class.java)
    intent.putExtra("object", items[position])
    holder.itemView.context.startActivity(intent)
}
    }

    override fun getItemCount(): Int =items.size
}