package com.example.harrypotter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.potterdb.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val potterService = retrofit.create(IPotterService::class.java)
        potterService.getBooks().enqueue(object :Callback<String> {
            override fun onResponse(p0: Call<String>, p1: Response<String>) {
                val resObj = JSONObject(p1.body().toString())
                val bookArrayObj = resObj.getJSONArray("data")
                val listem = ArrayList<BookDataModel>()
                for(i in 0 until bookArrayObj.length()){
                    val item = bookArrayObj.getJSONObject(i)
                    val id = item.getString("id")
                    val author = item.getJSONObject("attributes").getString("author")
                    val title = item.getJSONObject("attributes").getString("title")
                    val wiki = item.getJSONObject("attributes").getString("wiki")
                    val releaseDate = item.getJSONObject("attributes").getString("release_date")
                    val cover = item.getJSONObject("attributes").getString("cover")


                    val model = BookDataModel(id,title,author,"",cover,releaseDate,wiki)
                    listem.add(model)
                }

                val rvList = findViewById<RecyclerView>(R.id.rvList)
                rvList.layoutManager = LinearLayoutManager(baseContext)
                val adapter = BookAdapter(listem)
                rvList.adapter = adapter
            }

            override fun onFailure(p0: Call<String>, p1: Throwable) {
                Toast.makeText(baseContext,"Hata Alındı",Toast.LENGTH_SHORT).show()
            }


        })




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}