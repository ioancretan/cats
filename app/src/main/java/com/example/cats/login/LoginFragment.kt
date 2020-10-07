package com.example.cats.login

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.cats.databinding.LoginFragmentBinding
import java.util.*


class LoginFragment : Fragment() {

    private lateinit var viewDataBinding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = LoginFragmentBinding.inflate(inflater, container, false).apply {
            loginViewModel =
                ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)
        }
        return viewDataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.loginViewModel?.userMutableLiveData?.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
               this@LoginFragment.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToBreedsFragment())

            })
    }

}
