package com.qw.dagger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.qw.dagger.dagger.DaggerApplicationComponent
import com.qw.dagger.databinding.LoginActivityBinding
import com.qw.dagger.vm.LoginViewModel
import javax.inject.Inject

/**
 * Created by qinwei on 12/29/20 3:11 PM
 * email: qinwei_it@163.com
 */
class LoginFragment : Fragment() {
    private lateinit var binding: LoginActivityBinding

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginActivityBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mLoginBtn.setOnClickListener {
            login(binding.mLoginNameLabel.text.toString(), binding.mLoginPwdLabel.text.toString())
        }

        DaggerApplicationComponent.create()
            .loginComponent()
            .create()
            .inject(this)
    }

    private fun login(name: String, pwd: String) {
        loginViewModel.login(name, pwd)
    }
}