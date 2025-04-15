package com.example.firstapp

import RetrofitClient
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var btn2: Button
    private lateinit var btnSignIn: Button
    private lateinit var inputUsername : EditText
    private lateinit var inputPassword : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        RetrofitClient.instance.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    response.body()?.forEach {
                        Log.d("API_RESPONSE", "ID: ${it.id}, Name: ${it.name}, Email: ${it.email}")
                    }
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("API_ERROR", t.message ?: "Error")
            }
        })


        btn2 = this.findViewById(R.id.btn2)
        btnSignIn = this.findViewById(R.id.signin)

        inputUsername = this.findViewById<EditText>(R.id.inputUsername)
        inputPassword = this.findViewById<EditText>(R.id.inputPassword)

        btn2.setOnClickListener {
            //open main2
            val intentDestination = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intentDestination)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        btnSignIn.setOnClickListener {
            //open main2
//            val intentDestination = Intent(this@MainActivity, HomeActivity::class.java)
//            startActivity(intentDestination)

            // Kirim data ke server menggunakan Volley
            val queue = Volley.newRequestQueue(this)
            val url = "http://10.0.2.2/backendApp/register.php"

            val postRequest = object : StringRequest(
                Method.POST, url,
                { response ->
                    // Cek jika server kirim status success
                    if (response.contains("success", ignoreCase = true)) {
                        Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Login gagal: $response", Toast.LENGTH_LONG).show()
                    }
                },
                { error ->
                    Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_LONG).show()
                }
            ) {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    val usernaming = inputUsername.text.toString()
                    val passworded = inputPassword.text.toString()
                    params["username"] = usernaming
                    params["password"] = passworded
                    return params
                }
            }

            queue.add(postRequest)

        }
    }
}