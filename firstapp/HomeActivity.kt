package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnSeeUsers: Button = findViewById(R.id.btnSeeUsers)
        val btnAddUser: Button = findViewById(R.id.btnAddUser)

        btnSeeUsers.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

//        btnAddUser.setOnClickListener {
//            val intent = Intent(this, AddUserActivity::class.java)
//            startActivity(intent)
//        }
    }
}
