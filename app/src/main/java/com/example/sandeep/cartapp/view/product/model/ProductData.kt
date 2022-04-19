package com.example.sandeep.cartapp.view.product.model

data class ProductData(
    var pkProductId: String? = null,
    var strProductName: String? = null,
    var strDescription: String? = null,
    var productDc: String? = null,
    var intMRP : Float?=null,
    var arrayThumbnail: ArrayList<Image>? = null
)
data class ProductResponse(
    val success: Boolean?,
    val message: String?,
    val count: Int?,
    val data: ArrayList<ProductData>?
)
data class ProductBody(
    var intPageLimit: Int?=null,
    val strStoreId: String = "6232d4063142bf316c2ed3b0",
    var intSkipCount: Int?=null,
    val blnExpress: Boolean = false,
    val strCategoryId: String = "6232d63c3142bf316c2ed3d3"
)


data class CartBody(
    val strLoginUserId: String = "5eb1b87073ebc514e3fbc5fc",
    val strStoreId: String = "6232d4063142bf316c2ed3b0",
    val blnExpress: Boolean = false,
    )

data class CartResponse(
    val success: Boolean?,
    val message: String?,
    val count: Int?,
    val data: ArrayList<CartData>?
)

data class AddCrtResponse(
    val success: Boolean?,
    val message: String?,
    val data: ArrayList<CartData>?
)

data class CartData(
    val pkCartId : String?=null,
    val intQuantity : Int?=null,
    val strProductName : String?=null,
    val intSellingPrice : Float?=null,
    val pkProductId : String?=null,
    val arrayThumbnail : ArrayList<Image>?=null
)

data class UpdateData(var intQuantity: Int?=null,
                      var strLoginUserId:String?=null,
                      var strProductId : String?=null,
                      var strStoreId : String?=null )

data class DeleteData(val strLoginUserId:String?,
                      val strProductId : String?,
                      val strStoreId : String? )

data class AddCart(
    var intQuantity: Int,
    val strLoginUserId:String?,
    val strProductId: String?,
    val strProductName: String?,
    val strStoreId: String?,
    val strStoreName:String="")


data class AddResponse(val data: AddCart)

data class UpdateResponse(val success: Boolean?,
                          val message: String?,
                          val data: ArrayList<CartData>?=null)



data class Image(val imageUrl: String? = null)