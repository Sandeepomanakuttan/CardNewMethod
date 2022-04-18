package com.example.sandeep.cartapp.view.product.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sandeep.cartapp.R

class RecyclerViewAdaptor(
    private val context: Context,
    private var list: ArrayList<ProductData>,
    private val listner : CallBack
) : RecyclerView.Adapter<RecyclerViewAdaptor.ItemViewHolder>() {

    private val userId="5eb1b87073ebc514e3fbc5fc"
    private val storeId="6232d4063142bf316c2ed3b0"
    inner class ItemViewHolder(view:View) : RecyclerView.ViewHolder(view) {


        private val imgProduct= view.findViewById<ImageView>(R.id.imgProduct)!!
        private val txtProductName= view.findViewById(R.id.txtProductName) as TextView
        private val txtProductDec= view.findViewById(R.id.txtProductDec) as TextView
        private val txtRate= view.findViewById(R.id.txtRate) as TextView
        private val countId= view.findViewById(R.id.count) as TextView
        val btnMinus= view.findViewById(R.id.btnMinus) as Button
        private val btnPlus= view.findViewById(R.id.btnPlus) as Button

        fun bindView(productData: ProductData) {
            txtProductName.text = productData.strProductName
            txtProductDec.text = productData.productDc
            txtRate.text = "INR "+productData.intMRP.toString()

            val c=productData.arrayThumbnail!![0]

            Glide.with(context).load(c.imageUrl.toString()).into(imgProduct)
            var count=0
            if (count!=0){
                count= count.minus(1)

                countId.text= count.toString()
            }

           btnPlus.setOnClickListener {
                count= count.plus(1)
                val data= AddCart(count,userId,productData.pkProductId,productData.strProductName,storeId)

                listner.addCart(data)
                countId.text= count.toString()

            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)
        return ItemViewHolder(root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindView(list[position])

        }




    override fun getItemCount(): Int {
       return list.size
    }

    fun updateList(data: ArrayList<ProductData>) {

        list = data

        notifyDataSetChanged()

    }
}

interface CallBack{
    fun addCart(data: AddCart)
}