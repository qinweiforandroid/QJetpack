package com.qw.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import com.qw.livedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mMainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            binding = this
            setContentView(this.root)
        }
        binding.mLiveDataToolMediatorBtn.setOnClickListener {
            mMainViewModel.loadUser2(101)
        }
        binding.mLiveDataToolMapBtn.setOnClickListener {
            mMainViewModel.loadChinese("hello")
        }
        binding.mLiveDataToolSwitchMapBtn.setOnClickListener {
            mMainViewModel.queryUser(100)
        }

        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mMainViewModel.chineseData.observe(this, Observer {
            log(it)
        })
        mMainViewModel.userLiveData.observe(this, Observer {
            log("${it.id} " + it.name + " ${it.age}")
        })

        mMainViewModel.userMediatorLiveData.observe(this, Observer {
            log("${it.id} " + it.name + " ${it.age}")
        })
    }

    private fun log(msg: String) {
        Log.d("liveData", msg)
    }
}