package com.example.assignment_a.Tabs

import android.os.Bundle
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeadLinesTab1Fragment : Fragment(), MyNewsHeadlinesAdapter.RecyclerViewEvent {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_head_lines_tab1, container, false)


        //imageViews

        val recyclerView=view.findViewById<RecyclerView>(R.id.HeadLinesRecyclerView)
        val activity = requireActivity()
        val API_Key="9e713526e2674a5d935e5286b0fc165d"
        val url ="https://newsapi.org/v2/"

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApiInterface::class.java)

        val retrofitData=retrofit.getHeadlines("in",API_Key)

        var titles:Array<String> = arrayOf()
        var images:Array<String> = arrayOf()
        var news:Array<String> = arrayOf()
        var dateTimes:Array<String> = arrayOf()
        var writtenBy:Array<String> = arrayOf()
        var completeArticleUrl:Array<String> = arrayOf()

        retrofitData.enqueue(object : Callback<newsDataClass> {

            override fun onResponse(p0: Call<newsDataClass>, response: Response<newsDataClass>) {
                if (isAdded) {
                    val newsData: newsDataClass? = response.body()

                    if (newsData != null) {
                        for (data in newsData.articles) {

                            if (data.title != null && data.urlToImage != null && data.description != null && data.publishedAt != null) {
                                titles += data.title
                                images += data.urlToImage
                                news += data.description
                                dateTimes += data.publishedAt
                                writtenBy += data.source.name
                                completeArticleUrl += data.url


                            }
                        }
                    }

                    // Set the layout manager
                    recyclerView.layoutManager = LinearLayoutManager(context)

                    // Initialize the adapter
                    val adapter = MyNewsHeadlinesAdapter(
                        activity,
                        titles,
                        dateTimes,
                        images,
                        news,
                        writtenBy,
                        completeArticleUrl
                    )
                    // Set the adapter
                    recyclerView.adapter = adapter


                }
            }

            override fun onFailure(p0: Call<newsDataClass>, response: Throwable) {
//                Log.d("APINews",response.toString())
            }

        })



        return view
    }

    override fun onItemClick(url: Array<String>, position: Int) {

      }




}

