package uz.koinot.onlineshop.ui.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.koinot.onlineshop.data.model.Product
import uz.koinot.onlineshop.databinding.LayoutFoodItemBinding

class ProductAdapter : PagingDataAdapter<Product, ProductAdapter.VH>(ProductModelComparator) {


    override fun onBindViewHolder(holder: VH, position: Int) {
        val product: Product = getItem(position)!!
        product.let {
            holder.onBind(product)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        LayoutFoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    inner class VH(private val layoutFoodItemBinding: LayoutFoodItemBinding) : RecyclerView.ViewHolder(layoutFoodItemBinding.root) {
        fun onBind(product: Product) {
            layoutFoodItemBinding.tvProductName.text = product.name
            layoutFoodItemBinding.tvProductPrice.text = product.salePrice.toString()
            layoutFoodItemBinding.tvProductAvgScore.text = product.inComPrice.toString()

        }
    }

    companion object {
        private val ProductModelComparator = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return (oldItem.id == newItem.id)
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }
}