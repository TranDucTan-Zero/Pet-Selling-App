package com.zero.shoppet.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.RecyclerListener
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.zero.shoppet.Model.SliderModel
import com.zero.shoppet.R

class SliderAdapter(
    private var sliderItems: List<SliderModel>,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<SliderAdapter.SliderViewHodel>() {
private lateinit var context: Context
private val runnable= Runnable{
    sliderItems=sliderItems
    notifyDataSetChanged()
}

    class SliderViewHodel(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imageView:ImageView= itemView.findViewById(R.id.imageSlider)
        fun setImage(sliderItems:SliderModel,context:Context){
            val requestOptions = RequestOptions().transform(CenterInside())

            Glide.with(context)
                .load(sliderItems.url)
                .apply(requestOptions)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): SliderAdapter.SliderViewHodel {
        context =parent.context
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.slider_image_contaiber,parent,false)
        return SliderViewHodel(view)
    }

    override fun onBindViewHolder(holder: SliderAdapter.SliderViewHodel, position: Int) {
       holder.setImage(sliderItems[position],context)

        if (position==sliderItems.lastIndex-1){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int =sliderItems.size
}