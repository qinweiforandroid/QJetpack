package com.qw.coroutines

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var mainVM :MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var label = findViewById<TextView>(R.id.label)
        mainVM=ViewModelProvider(this).get(MainViewModel::class.java)
        mainVM.error.observe(this,{
            label.text=it
        })
        mainVM.repos.observe(this,{
            label.text=it[0].name
        })
        mainVM.loadRepos()
    }
}