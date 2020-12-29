package com.android.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import com.android.jetpack.databinding.LoginActivityBinding
import com.android.jetpack.vm.LoginViewModel
import com.qw.framework.ui.BaseActivity
import javax.inject.Inject

/**
 * Created by qinwei on 12/29/20 3:11 PM
 * email: qinwei_it@163.com
 */
class LoginActivity : BaseActivity() {

    private lateinit var binding: LoginActivityBinding

    @Inject
    lateinit var loginViewModel: LoginViewModel
    override fun setContentView() {
        (application as App).appComponent.inject(this)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initView() {
        binding.mLoginBtn.setOnClickListener {
            login(binding.mLoginNameLabel.text.toString(), binding.mLoginPwdLabel.text.toString())
        }
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    private fun login(name: String, pwd: String) {
        loginViewModel.login(name, pwd)
    }
}