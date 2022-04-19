package com.example.sandeep.cartapp.view.product.cartItem

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sandeep.cartapp.R
import com.example.sandeep.cartapp.view.product.UtilInterface
import com.example.sandeep.cartapp.view.product.model.CartData
import com.example.sandeep.cartapp.view.product.model.DeleteData
import com.example.sandeep.cartapp.view.product.model.UpdateData
import kotlinx.coroutines.launch

class RecyclerViewCartAdaptor(
    private val requireContext: Context,
    private var data: ArrayList<CartData>,
    private val objInterface: UtilInterface,
    val lifecycleScope: LifecycleCoroutineScope
) : RecyclerView.Adapter<RecyclerViewCartAdaptor.ItemViewHolder>() {

    private val loginId = "5eb1b87073ebc514e3fbc5fc"
    private val storeId = "6232d4063142bf316c2ed3b0"



    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        private val imgProduct: ImageView = view.findViewById(R.id.imgProduct)!!
        private val txtProductName = view.findViewById(R.id.txtProductName) as TextView
        private val count = view.findViewById(R.id.count) as TextView
        private val txtRate = view.findViewById(R.id.txtRate) as TextView
        private val btnMinus = view.findViewById(R.id.btnMinus) as Button
        private val btnPlus = view.findViewById(R.id.btnPlus) as Button
        private val delete: ImageView = view.findViewById(R.id.delete)

        @SuppressLint("SetTextI18n")
        fun bindView(cartData: CartData) {

            txtProductName.text = cartData.strProductName
            txtRate.text = "INR " + cartData.intSellingPrice.toString()
            count.text = cartData.intQuantity.toString()
            val c = cartData.arrayThumbnail!![0]

            Glide.with(requireContext).load(c.imageUrl.toString()).into(imgProduct)

            var a = cartData.intQuantity

            btnMinus.setOnClickListener {

                if (a != 1) {
                    Log.e("id", cartData.pkProductId.toString())
                    a = a?.minus(1)
                    val data = UpdateData(a, loginId, cartData.pkProductId, storeId)

                    lifecycleScope.launch {

                        objInterface.updateCart(data)

                    }


                    count.text = a.toString()
                }
            }
           btnPlus.setOnClickListener {
                a = a?.plus(1)
                val data = UpdateData(a, loginId, cartData.pkProductId, storeId)

                lifecycleScope.launch {
                    objInterface.updateCart(data)
                }


               count.text = a.toString()
            }

            delete.visibility = View.VISIBLE
            delete.let {
                it.setOnClickListener {
                    val data = DeleteData(loginId, cartData.pkProductId, storeId)

                    lifecycleScope.launch {

                        objInterface.deleteCart(data)

                    }

                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ItemViewHolder(root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.bindView(data[position])

    }

    override fun getItemCount(): Int {
        return data.size
    }
}