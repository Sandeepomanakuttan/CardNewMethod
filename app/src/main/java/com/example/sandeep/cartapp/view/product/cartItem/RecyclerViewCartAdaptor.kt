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
import com.example.sandeep.cartapp.view.product.adaptor.CartData
import com.example.sandeep.cartapp.view.product.adaptor.DeleteData
import com.example.sandeep.cartapp.view.product.adaptor.UpdateData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RecyclerViewCartAdaptor(
    private val requireContext: Context,
    private var data: ArrayList<CartData>,
    private val objInterface: UtilInterface,
    val lifecycleScope: LifecycleCoroutineScope
) : RecyclerView.Adapter<RecyclerViewCartAdaptor.ItemViewHolder>() {

    private val loginId = "5eb1b87073ebc514e3fbc5fc";
    private val storeId = "6232d4063142bf316c2ed3b0"

    var itemClickListener: ((position: Int, name: String) -> Unit)? = null


    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        //fun bindView(data:String , position: Int){

        // }

        val imgProduct = view.findViewById<ImageView>(R.id.imgProduct)!!
        val txtProductName = view.findViewById(R.id.txtProductName) as TextView
        val txtProductDec = view.findViewById(R.id.txtProductDec) as TextView
        val count = view.findViewById(R.id.count) as TextView
        val txtRate = view.findViewById(R.id.txtRate) as TextView
        val btnMinus = view.findViewById(R.id.btnMinus) as Button
        val btnPlus = view.findViewById(R.id.btnPlus) as Button
        val delete = view.findViewById(R.id.delete) as ImageView


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ItemViewHolder(root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val lister = data[position]
        holder.txtProductName.text = lister.strProductName
        holder.txtRate.text = "INR " + lister.intSellingPrice.toString()
        holder.count.text = lister.intQuantity.toString()
        val c = lister.arrayThumbnail!![0]

        Glide.with(requireContext).load(c.imageUrl.toString()).into(holder.imgProduct)

        var a = lister.intQuantity

        holder.btnMinus.setOnClickListener {

            if (a != 1) {
                Log.e("id", lister.pkProductId.toString())
                a = a?.minus(1)
                val data = UpdateData(a, loginId, lister.pkProductId, storeId)

                lifecycleScope.launch {

                    objInterface.updateCart(data)

                }


                holder.count.text = a.toString()
            }
        }
        holder.btnPlus.setOnClickListener {
            a = a?.plus(1)
            val data = UpdateData(a, loginId, lister.pkProductId, storeId)

            lifecycleScope.launch {
                objInterface.updateCart(data)
            }


            holder.count.text = a.toString()
        }

        holder.delete.visibility = View.VISIBLE
        holder.delete.let {
            it.setOnClickListener {
                val data = DeleteData(loginId, lister.pkProductId, storeId)

                lifecycleScope.launch {

                    objInterface.deleteCart(data)

                }

            }
        }


    }

    override fun getItemCount(): Int {
        return data.size
    }
}