package com.example.resttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<PostResponse>()
    lateinit var rvPost: RecyclerView
    lateinit var tvResponseCode:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updatePost()

        rvPost.setHasFixedSize(true)
        rvPost.layoutManager = LinearLayoutManager(this)
        
        RetrofitClient.instance.getPosts().enqueue(object : Callback<ArrayList<PostResponse>>{
            override fun onResponse(
                call: Call<ArrayList<PostResponse>>,
                response: Response<ArrayList<PostResponse>>
            ) {
                val responseCode = response.code().toString()
                tvResponseCode.text = responseCode
                response.body()?.let { list.addAll(it)}
                val adapter = PostAdapter(list)
                rvPost.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {

            }

        })
    }

    private fun updatePost() {
        RetrofitClient.instance.putPost(
        5,
        4,
        5,
        null,
        "ini text"
        ).enqueue(object : Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                tvResponseCode.text = "masuk"
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                tvResponseCode.text = "tidak masuk"
            }

        })


    }
}