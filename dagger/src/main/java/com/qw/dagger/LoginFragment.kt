package com.qw.dagger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qw.dagger.dagger.DaggerApplicationComponent
import com.qw.dagger.databinding.LoginActivityBinding
import com.qw.dagger.vm.LoginViewModel
import com.qw.framework.ui.BaseFragment
import javax.inject.Inject

/**
 * Created by qinwei on 12/29/20 3:11 PM
 * email: qinwei_it@163.com
 */
class LoginFragment : BaseFragment() {
    private lateinit var binding: LoginActivityBinding

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun getCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginActivityBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun initView(view: View) {
        binding.mLoginBtn.setOnClickListener {
            login(binding.mLoginNameLabel.text.toString(), binding.mLoginPwdLabel.text.toString())
        }
    }

    override fun initData() {
        DaggerApplicationComponent.create()
            .loginComponent()
            .create()
            .inject(this)
    }

    private fun login(name: String, pwd: String) {
        loginViewModel.login(name, pwd)
    }
}