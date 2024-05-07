package com.zero.shoppet.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1762.Helper.ManagmentCart
import com.zero.shoppet.Adapter.CartAdapter
import com.zero.shoppet.Helper.ChangeNumberItemsListener
import com.zero.shoppet.R
import com.zero.shoppet.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding:ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax:Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart= ManagmentCart(this)
        setVariable();
        initCartList();
        calculateCart();
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery =10
        tax= Math.round(managmentCart.getTotalFee()*percentTax*100)/100.0
        val total= Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100
        val itemTotal=Math.round(managmentCart.getTotalFee()*100)/100
        with(binding){
            totalEtxt.text="${itemTotal} đ"
            taxtxt.text="$tax đ"
            deliverytxt.text="$delivery đ"
            totalEtxt.text="$total đ"
        }


    }

    private fun initCartList() {
        binding.cartView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.cartView.adapter=CartAdapter(managmentCart.getListCart(),this,object :ChangeNumberItemsListener{
            override fun onChanged() {
                calculateCart()
            }
        })
    }

    private  fun setVariable(){
        binding.back.setOnClickListener{
            finish()
        }
    }

}