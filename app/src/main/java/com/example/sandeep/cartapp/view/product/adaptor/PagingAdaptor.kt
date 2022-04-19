package com.example.sandeep.cartapp.view.product.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sandeep.cartapp.R
import com.example.sandeep.cartapp.view.product.model.ProductData

class PagingAdaptor() : PagingDataAdapter<ProductData,PagingAdaptor.PagViewModel>(COMPARATOR) {


    class PagViewModel(view : View) : RecyclerView.ViewHolder(view) {

        private val imgProduct = view.findViewById<ImageView>(R.id.imgProduct)!!
        private val txtProductName = view.findViewById(R.id.txtProductName) as TextView
        private val txtProductDec = view.findViewById(R.id.txtProductDec) as TextView
        private val txtRate = view.findViewById(R.id.txtRate) as TextView
        private val countId = view.findViewById(R.id.count) as TextView
        val btnMinus = view.findViewById(R.id.btnMinus) as Button
        private val btnPlus = view.findViewById(R.id.btnPlus) as Button

        fun bindView(productData: ProductData) {
            txtProductName.text = productData.strProductName
            txtProductDec.text = productData.productDc
            txtRate.text = "INR " + productData.intMRP.toString()

            val c = productData.arrayThumbnail!![0]


//            Glide.with().load(c.imageUrl.toString()).into(imgProduct)
//            var count=0
//            if (count!=0){
//                count= count.minus(1)
//
//                countId.text= count.toString()
//            }
//
//            btnPlus.setOnClickListener {
//                count= count.plus(1)
//                val data= AddCart(count,userId,productData.pkProductId,productData.strProductName,storeId)
//
//                listner.addCart(data)
//                countId.text= count.toString()


        //}
    }

    }

    override fun onBindViewHolder(holder: PagingAdaptor.PagViewModel, position: Int) {
        holder.bindView(getItem(position)!!)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PagingAdaptor.PagViewModel {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)
        return PagViewModel(root)
    }

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<ProductData>(){
            override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
                return oldItem.pkProductId == newItem.pkProductId
            }

            override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
                return oldItem == newItem
            }

        }
    }
}