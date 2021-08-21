package uz.koinot.onlineshop.ui.shop

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.koinot.onlineshop.R
import uz.koinot.onlineshop.databinding.FragmentShopBinding

@AndroidEntryPoint
class ShopFragment : Fragment(R.layout.fragment_shop) {

    private val viewModel: ShopViewModel by viewModels()
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding
    private var adapter: ProductAdapter? = null
    private var selectProduct: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShopBinding.bind(view)
        setupData()

        binding!!.rvFlashSale.adapter = adapter
        binding!!.rvMegaSale.adapter = adapter
        binding!!.rvRecommended.adapter = adapter
    }

    private fun setupData() {
        adapter = ProductAdapter()

        selectProduct?.cancel()
        selectProduct = lifecycleScope.launch {
            viewModel.product.collectLatest {
                    adapter!!.submitData(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}