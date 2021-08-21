package uz.koinot.onlineshop.ui.shop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import uz.koinot.onlineshop.data.model.Product
import uz.koinot.onlineshop.data.paged.ProductPagingSource
import uz.koinot.onlineshop.data.repository.ProductRepository
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

//    private var _productFlow = MutableStateFlow<UiStateList<Product>>(UiStateList.EMPTY)
//    val productFlow: StateFlow<UiStateList<Product>> get() = _productFlow

    val product: Flow<PagingData<Product>> = getProductList()

//    fun getAllProduct(page: Int) = viewModelScope.launch {
//        _productFlow.value = UiStateList.LOADING
//        try {
//                val res = repository.getProduct(page)
//                if (res.message == "") {
//                    _productFlow.value = UiStateList.SUCCESS(res.`object`)
//                } else {
//                    _productFlow.value = UiStateList.ERROR(res.message, true, res.success)
//                }
//        } catch (e: Exception) {
//            _productFlow.value = UiStateList.ERROR(e.userMessage() ?: "not found")
//        }
//    }

    private fun getProductList(): Flow<PagingData<Product>> {
        return Pager(PagingConfig(20)) {
            ProductPagingSource(repository)
        }.flow.cachedIn(viewModelScope)
    }

}