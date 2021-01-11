package com.example.wikifetcher

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputQuery = findViewById(R.id.input_email) as EditText

        val Button = findViewById(R.id.button) as Button
        val textview = findViewById(R.id.textViewName) as TextView

        Button.setOnClickListener {
            val testString: String = inputQuery.getText().toString().replace(" ", "%20")
            val queue = Volley.newRequestQueue(this)
            val url = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro&explaintext&redirects=1&titles="
            val request_url = "$url$testString"

            val stringRequest = StringRequest(Request.Method.GET, request_url,
                { response ->
                    // Display the first 500 characters of the response string.
                    if (response is String) {
                        val result = response.split("extract").toTypedArray()[1].substring(3).split(".").toTypedArray()[0]
                        textview.setText("${result}");
                    }
                },
                { error ->
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                })
            queue.add(stringRequest)
        }
    }
}