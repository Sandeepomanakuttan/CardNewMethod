package com.example.sandeep.cartapp.view.product.cartItem

import androidx.recyclerview.widget.DiffUtil
import com.example.sandeep.cartapp.view.product.model.ProductData

class DiffUtils: DiffUtil.ItemCallback<ProductData>() {
    override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
        return oldItem.pkProductId == newItem.pkProductId
    }

    override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {

        return oldItem == newItem
    }

}