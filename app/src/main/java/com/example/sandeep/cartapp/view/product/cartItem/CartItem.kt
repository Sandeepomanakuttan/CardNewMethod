package com.example.sandeep.cartapp.view.product.cartItem

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sandeep.cartapp.R
import com.example.sandeep.cartapp.view.product.UtilInterface
import com.example.sandeep.cartapp.view.product.adaptor.AddCart
import com.example.sandeep.cartapp.view.product.adaptor.DeleteData
import com.example.sandeep.cartapp.view.product.adaptor.UpdateData
import kotlinx.coroutines.launch

class CartItem : Fragment(),UtilInterface {


    var viewModel: CartItemViewModel?=null
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, CartItemViewModelProvider())[CartItemViewModel::class.java]

        val root = inflater.inflate(R.layout.cart_item_fragment, container, false)
        recycler=root.findViewById(R.id.recyclerView)





        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calling()



    }

    override fun onResume() {
        super.onResume()
        calling()
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel=null

    }

    override suspend fun updateCart(data: UpdateData) {
        viewModel?.updateCart(data)
    }

    override suspend fun deleteCart(data: DeleteData) {
        viewModel?.deleteCart(data)
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun calling() {
        viewModel?.cardItem?.observe(viewLifecycleOwner) {

            recycler.layoutManager = GridLayoutManager(requireContext(), 2)

            Log.e("status", it.toString())
            val adaptor = RecyclerViewCartAdaptor(requireContext(), it!!, this,lifecycleScope)
            recycler.adapter = adaptor
            recycler.setHasFixedSize(true)
            adaptor.notifyDataSetChanged()


        }
    }


}