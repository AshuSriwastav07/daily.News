package com.example.assignment_a.Tabs

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_a.Adapters.MyNewsHeadlinesAdapter
import com.example.assignment_a.DataFiles.MyApiInterface
import com.example.assignment_a.DataFiles.newsDataClass
import com.example.assignment_a.R
import com.example.assignment_a.SQLDatabase.MySQLDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserDefineTab2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View=inflater.inflate(R.layout.fragment_user_define_tab2, container, false)

        val activity = requireActivity()
        val API_KEY="9e713526e2674a5d935e5286b0fc165d"
        val url="https://newsapi.org/v2/"

        val retrofit= Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApiInterface::class.java)



        val db= MySQLDatabase(requireContext(),"TagsInfo",1)
        val cursor: Cursor =db.GetData()
        var query:String=""
        var count:Int=0
        var tagName=ArrayList<String>()


        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                tagName.add(cursor.getString(1))
                query += cursor.getString(1)

                // Check if there are more items in the cursor
                if (!cursor.isLast) {
                    query += "+"
                }
            }
        }

        Log.d("TagData",query)


        val retrofitData=retrofit.getUserDefineTabData(tagName[2],API_KEY)

        retrofitData.enqueue(object : Callback<newsDataClass> {
            override fun onResponse(p0: Call<newsDataClass>, response: Response<newsDataClass>) {

                val newsData: newsDataClass?=response.body()

                var titles:Array<String> = arrayOf()
                var images:Array<String> = arrayOf()
                var news:Array<String> = arrayOf()
                var dateTimes:Array<String> = arrayOf()
                var writtenBy:Array<String> = arrayOf()
                var completeArticleUrl:Array<String> = arrayOf()

                if(newsData!=null){
                    for (data in newsData.articles){
                        if(data.title != null && data.urlToImage != null && data.description != null && data.publishedAt != null) {
                            titles += data.title
                            images += data.urlToImage
                            news += data.description
                            dateTimes += data.publishedAt
                            writtenBy += data.source.name
                            completeArticleUrl += data.url
                        }

                    }
                }

                val recyclerView=view.findViewById<RecyclerView>(R.id.UserDefineTab2)
                // Set the layout manager
                recyclerView.layoutManager = LinearLayoutManager(context)

                // Initialize the adapter
                val adapter = MyNewsHeadlinesAdapter(activity,titles,dateTimes,images,news,writtenBy,completeArticleUrl)
                // Set the adapter
                recyclerView.adapter = adapter
            }

            override fun onFailure(p0: Call<newsDataClass>, response: Throwable) {

            }

        })
        return view
    }

}
