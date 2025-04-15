package com.example.firstapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    private lateinit var btn4: Button
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etUsername = findViewById(R.id.etRegisterUsername)
        etPassword = findViewById(R.id.etRegisterPassword)
        etEmail = findViewById(R.id.etRegisterEmail)
        btnRegister = findViewById(R.id.btnRegister)

        // Value
        val username = etUsername.text
        val password = etPassword.text

        btnRegister.setOnClickListener {
            val username = etUsername.text
            val password = etPassword.text

            val message = "Nama: $username\nPassword: $password"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        }


    }

}