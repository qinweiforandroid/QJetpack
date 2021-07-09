package com.qw.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qw.dagger.LoginFragment
import com.qw.dagger.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.mContent,LoginFragment(),"")
            .commitAllowingStateLoss()
    }
}