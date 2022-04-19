package com.example.sandeep.cartapp.view.product.adaptor

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sandeep.cartapp.network.ApiServices
import com.example.sandeep.cartapp.view.product.model.ProductBody
import com.example.sandeep.cartapp.view.product.model.ProductData


class PagingSources(private val api :ApiServices): PagingSource<Int, ProductData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductData> {

        return try {
            val nextPageNumber = params.key ?: First_page

            val dataObj= ProductBody()
            dataObj.intPageLimit= 30
            dataObj.intSkipCount=nextPageNumber


            val response = api.getProduc(dataObj)

            LoadResult.Page(
                data = response.data!!,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < 249) nextPageNumber + 1 else null
            )
        }catch (e : Exception){
            LoadResult.Error(e)
        }
    }
    companion object{
        private const val First_page = 1;
    }

    override fun getRefreshKey(state: PagingState<Int, ProductData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
