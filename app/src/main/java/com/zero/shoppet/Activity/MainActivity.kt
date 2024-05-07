package com.zero.shoppet.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.zero.shoppet.Adapter.BestSellerAdapter
import com.zero.shoppet.Adapter.CategoryAdapter
import com.zero.shoppet.Adapter.SliderAdapter
import com.zero.shoppet.Model.SliderModel
import com.zero.shoppet.R
import com.zero.shoppet.ViewModel.MainViewModel
import com.zero.shoppet.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBannes()
        initCategorys()
        initBestSeller()
        bottomNavigition()
    }

    private fun bottomNavigition() {
        binding.muahang.setOnClickListener{ startActivity(Intent(this,CartActivity::class.java))}
    }

    private fun initBestSeller() {
        binding.progressBarBanchay.visibility=View.VISIBLE
        viewModel.bestseller.observe(this, Observer {
            binding.banView.layoutManager=GridLayoutManager(this,2)
            binding.banView.adapter=BestSellerAdapter(it)
            binding.progressBarBanchay.visibility=View.GONE
        })
        viewModel.loadBestSeller()
    }


    //slider bar
    private fun initBannes(){
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banners.observe(this, {
            banner(it)
            binding.progressBarBanner.visibility = View.GONE
        })
        viewModel.loadBanners()
    }
    private fun banner(images: List<SliderModel>){
        binding.viewPagerSlider.adapter=SliderAdapter(images, binding.viewPagerSlider)
        binding.viewPagerSlider.clipToPadding=false
        binding.viewPagerSlider.clipChildren=false
        binding.viewPagerSlider.offscreenPageLimit=3
        binding.viewPagerSlider.getChildAt(0).overScrollMode= RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer= CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)
        if(images.size>1){
            binding.dotIndicator.visibility= View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPagerSlider)
        }

    }
    // loai sản phảm
    private fun initCategorys() {
        binding.progressBarBanner.visibility= View.VISIBLE
        viewModel.category.observe(this, {
            binding.loaiView.layoutManager= LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.loaiView.adapter=CategoryAdapter(it)
            binding.progressBarLoai.visibility= View.GONE
        })
        viewModel.loadCategory()
    }
}