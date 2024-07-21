package com.example.assignment_a.Tabs

import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_a.Adapters.MyFinanceAdapter
import com.example.assignment_a.DataFiles.MyApiInterface
import com.example.assignment_a.DataFiles.newsDataClass
import com.example.assignment_a.R
import com.example.assignment_a.SQLDatabase.MySQLDatabase
import com.example.assignment_a.extraClass.setStory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FinanceNewsTab3Fragment : Fragment(), MyFinanceAdapter.RecyclerViewEvent {

    private lateinit var recyclerView: RecyclerView
    private val imageViews: Array<ImageView?> = arrayOfNulls(15)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_finance_news_tab3, container, false)
        recyclerView = view.findViewById(R.id.CompleteNewsRecyclerView)
        val activity = requireActivity()

        imageViews[0] = view.findViewById(R.id.storyImageView1)
        imageViews[1] = view.findViewById(R.id.storyImageView2)
        imageViews[2] = view.findViewById(R.id.storyImageView3)
        imageViews[3] = view.findViewById(R.id.storyImageView4)
        imageViews[4] = view.findViewById(R.id.storyImageView5)
        imageViews[5] = view.findViewById(R.id.storyImageView6)
        imageViews[6] = view.findViewById(R.id.storyImageView7)
        imageViews[7] = view.findViewById(R.id.storyImageView8)
        imageViews[8] = view.findViewById(R.id.storyImageView9)
        imageViews[9] = view.findViewById(R.id.storyImageView10)
        imageViews[10] = view.findViewById(R.id.storyImageView11)
        imageViews[11] = view.findViewById(R.id.storyImageView12)
        imageViews[12] = view.findViewById(R.id.storyImageView13)
        imageViews[13] = view.findViewById(R.id.storyImageView14)
        imageViews[14] = view.findViewById(R.id.storyImageView15)



        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApiInterface::class.java)

        val apiKey = "9e713526e2674a5d935e5286b0fc165d"

        val url="https://newsapi.org/v2/"



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


        val completeNewsData=retrofit.getFinanceNews("India finance","popularity",apiKey)

        var titles:Array<String> = arrayOf()
        var description:Array<String> = arrayOf()
        var urlToImage:Array<String> = arrayOf()
        var publishedAt:Array<String> = arrayOf()
        var content:Array<String> = arrayOf()
        var author:Array<String> = arrayOf()
        var ArticleUrls:Array<String> = arrayOf()


        completeNewsData.enqueue(object : Callback<newsDataClass>{
            override fun onResponse(p0: Call<newsDataClass>, response: Response<newsDataClass>) {
                if (isAdded) {
                    val newsData = response.body()
                    if (newsData != null) {
                        for (data in newsData.articles) {
                            if (data.title != null && data.description != null && data.publishedAt != null && !data.title.equals(
                                    "[Removed]"
                                )
                            ) {
                                titles += data.title
                                description += data.description
                                urlToImage += data.urlToImage
                                publishedAt += data.publishedAt
                                content += data.content
                                author += data.source.name
                                ArticleUrls += data.url
                                Log.d("LINKURL", data.url)
                            }
                        }

                    }

                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    val adapter = MyFinanceAdapter(
                        activity,
                        titles,
                        publishedAt,
                        urlToImage,
                        description,
                        author,
                        ArticleUrls
                    )
                    // Set the adapter
                    recyclerView.adapter = adapter
                }
            }
            override fun onFailure(p0: Call<newsDataClass>, p1: Throwable) {
                Log.d("FinanceNewsAPIError",p1.message.toString())
            }

        })

        val storySetUp = setStory()
        storySetUp.setupStory(imageViews,activity)

        return view
    }
    override fun onItemClick(url: Array<String>, position: Int) {

    }


}
