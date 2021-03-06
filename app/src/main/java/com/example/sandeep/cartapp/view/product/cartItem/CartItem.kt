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
import com.example.sandeep.cartapp.view.product.model.DeleteData
import com.example.sandeep.cartapp.view.product.model.UpdateData
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class CartItem : Fragment(),UtilInterface,KodeinAware {

    override val kodein by closestKodein()

    private val factory : CartItemViewModelProvider by instance()
    private var viewModel: CartItemViewModel?=null
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ViewModelProvider(this, factory)[CartItemViewModel::class.java].also { viewModel = it }

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
       lifecycleScope.launch {   viewModel?.get()
           calling()}

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