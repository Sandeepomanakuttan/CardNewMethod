package com.example.sandeep.cartapp.view.product.allProduct

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sandeep.cartapp.R
import com.example.sandeep.cartapp.view.product.MainInterface
import com.example.sandeep.cartapp.view.product.adaptor.AddCart
import com.example.sandeep.cartapp.view.product.viewModel.AllProRecyclerViewAdaptor
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class AllProduct : Fragment(), MainInterface,KodeinAware {


    override val kodein by closestKodein()
    private val factory : AllProductViewModelProvider by instance()
    private lateinit var viewModel: AllProductViewModel
    private lateinit var recycler:RecyclerView
    private lateinit var recyclerViewAda : AllProRecyclerViewAdaptor


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.all_product_fragment, container, false)
        recycler=root.findViewById(R.id.recyclerView)
        recyclerViewAda = AllProRecyclerViewAdaptor(requireContext(),this,lifecycleScope)



        setupViewModel()
        setupView()
        setupList()


        return root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun setupList() {
        lifecycleScope.launch {
            viewModel.getData.collectLatest {
                recyclerViewAda.submitData(it)
            }
        }
    }

    private fun setupView() {
        recycler.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            adapter = recyclerViewAda
            setHasFixedSize(true)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this,factory )[AllProductViewModel::class.java]

    }

    override suspend fun addCart(data: AddCart) {
        viewModel.addCart(data)
    }


}