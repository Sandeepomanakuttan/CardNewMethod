package com.example.sandeep.cartapp.view.product.viewModel

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sandeep.cartapp.R
import com.example.sandeep.cartapp.view.product.MainInterface
import com.example.sandeep.cartapp.view.product.adaptor.AddCart
import com.example.sandeep.cartapp.view.product.adaptor.ProductData
import com.example.sandeep.cartapp.view.product.cartItem.DiffUtils
import kotlinx.coroutines.launch

class AllProRecyclerViewAdaptor(
    val requireContext: Context,
    val listener: MainInterface,
    val lifecycleScope: LifecycleCoroutineScope
) :
    PagingDataAdapter<ProductData, AllProRecyclerViewAdaptor.MyViewModel>(DiffUtils()) {

    private val userId = "5eb1b87073ebc514e3fbc5fc"
    private val storeId = "6232d4063142bf316c2ed3b0"

    inner class MyViewModel(view: View) : RecyclerView.ViewHolder(view) {

        private val imgProduct = view.findViewById<ImageView>(R.id.imgProduct)!!
        private val txtProductName = view.findViewById(R.id.txtProductName) as TextView
        private val txtProductDec = view.findViewById(R.id.txtProductDec) as TextView
        private val txtRate = view.findViewById(R.id.txtRate) as TextView
        private val countId = view.findViewById(R.id.count) as TextView
        val btnMinus = view.findViewById(R.id.btnMinus) as Button
        private val btnPlus = view.findViewById(R.id.btnPlus) as Button

        fun bindView(data: ProductData) {

            txtProductName.text = data.strProductName
            txtProductDec.text = data.productDc
            txtRate.text = data.intMRP.toString()

            val c = data.arrayThumbnail!![0]

            Glide.with(requireContext).load(c.imageUrl.toString()).into(imgProduct)
            var count = 0
            if (count != 0) {
                Log.e("id", data.pkProductId.toString())
                count = count.minus(1)

                countId.text = count.toString()
            }

            btnPlus.setOnClickListener {
                count = count.plus(1)
                val data = AddCart(count, userId, data.pkProductId, data.strProductName, storeId)
                lifecycleScope.launch {

                    listener.addCart(data)

                }
                countId.text = count.toString()

            }
            btnMinus.setOnClickListener {
                count= count.minus(1)
                countId.text=count.toString()
            }
        }
    }

    override fun onBindViewHolder(holder: AllProRecyclerViewAdaptor.MyViewModel, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bindView(it)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllProRecyclerViewAdaptor.MyViewModel {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return MyViewModel(root)
    }

}

interface CallBacker {
    fun addCart(data: AddCart)
}
