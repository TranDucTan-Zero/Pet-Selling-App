package com.zero.shoppet.Activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.project1762.Helper.ManagmentCart
import com.zero.shoppet.Adapter.PicListAdapter
import com.zero.shoppet.Adapter.SizeListAdapter
import com.zero.shoppet.Model.ItemsModel
import com.zero.shoppet.R
import com.zero.shoppet.databinding.ActivityDetailBinding
import com.zero.shoppet.databinding.ViewholderPicListBinding

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item:ItemsModel
    private var numberOrder=1
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managmentCart= ManagmentCart(this)
        getBundle()
        initList()

    }

    private fun initList() {
        val sizeList=ArrayList<String>()
        for (size in item.size){
            sizeList.add(size.toString())

        }
        binding.sizeList.adapter=SizeListAdapter(sizeList)
        binding.sizeList.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val colorList =ArrayList<String>()
        for (imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }
        Glide.with(this)
            .load(colorList[0])
            .into(binding.picMain)

        binding.picList.adapter =PicListAdapter(colorList, binding.picMain)
        binding.picList.layoutManager=
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

    }

    private  fun getBundle(){
        item= intent.getParcelableExtra("object")!!
         binding.name.text=item.title
         binding.description.text=item.description
         binding.price.text= item.price.toString() +"đ"
         binding.numberstar.text="${item.rating} Đánh giá "
         binding.namepro.text=item.sellerName
         binding.addtocarrt.setOnClickListener{
             item.numberInCart = numberOrder
             managmentCart.insertItems(item)
         }
         binding.back.setOnClickListener{finish()}
         binding.cartBtn.setOnClickListener{
             startActivity(Intent(this@DetailActivity,CartActivity::class.java))
         }
         Glide.with(this)
             .load(item.sellerPic)
             .apply(RequestOptions().transform(CenterCrop()))
             .into(binding.picSeller)
         binding.chat.setOnClickListener{
             val sendIntent=Intent(Intent.ACTION_VIEW)
             sendIntent.setData(Uri.parse("sms:"+item.sellerTell))
             sendIntent.putExtra("sms_body","type your message")
             startActivity(intent)
         }
         binding.phone.setOnClickListener{
             val phone =item.sellerTell.toString()
             val intent =Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",phone,null))
             startActivity(intent)
         }
    }
}