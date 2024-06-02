package com.example.ashokadocs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ashokadocs.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase
import android.content.Intent

lateinit var bind : ActivityMainBinding
class MainActivity : AppCompatActivity() {

    lateinit var recyclerbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerbtn = findViewById(R.id.recyclerviewbutton)

        recyclerbtn.setOnClickListener {

            var i=Intent(this,DocActivity::class.java)
            startActivity(i)
        }
    }

}