package uz.koinot.onlineshop.data.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.koinot.onlineshop.data.model.Product
import uz.koinot.onlineshop.data.repository.ProductRepository

class ProductPagingSource(private val repository: ProductRepository) : PagingSource<Int, Product>() {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val nextPage = params.key ?: 1
            val productListResponse = repository.getProduct(nextPage)
            val prevKey = if (nextPage == 1) null else nextPage - 1
            val nextKey = if (productListResponse.`object`!!.isEmpty()) null else nextPage + 1
            LoadResult.Page(
                data = productListResponse.`object`!!,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}