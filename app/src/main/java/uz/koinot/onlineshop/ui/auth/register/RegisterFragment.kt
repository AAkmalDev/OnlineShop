package uz.koinot.onlineshop.ui.auth.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import uz.koinot.onlineshop.R
import uz.koinot.onlineshop.data.api.AuthService
import uz.koinot.onlineshop.data.model.User
import uz.koinot.onlineshop.databinding.FragmentRegisterBinding
import uz.koinot.onlineshop.utils.UiStateObject
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    @Inject
    lateinit var api: AuthService

    private var _binding: FragmentRegisterBinding? = null
    val binding get() = _binding!!
    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegisterBinding.bind(view)

        binding.apply {



            edit6.setOnClickListener {
                val firstName = edit1.text.toString()
                val lastName = edit2.text.toString()
                val phoneNumber = edit3.text.toString()
                val password = edit4.text.toString()
                val confirmPassword = edit5.text.toString()

                viewModel.register(User(firstName, lastName, phoneNumber, password))
            }
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                viewModel.registerFlow.collect {
                    when (it) {
                        is UiStateObject.SUCCESS -> {
                            showProgress(false)
                            viewModel.reRegister()
                        }
                        is UiStateObject.ERROR -> {
                            showProgress(false)
                        }
                        is UiStateObject.LOADING -> {
                            showProgress(true)
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun showProgress(status: Boolean) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}