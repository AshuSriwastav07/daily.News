package com.example.assignment_a.extraClass

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.ImageView
import com.example.assignment_a.Activities.OpenNewsArticleWeb
import com.example.assignment_a.DataFiles.MyApiInterface
import com.example.assignment_a.DataFiles.newsDataClass
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class setStory {

    fun setupStory(storyImageViews: Array<ImageView?>,activity:Activity ){

        val API_KEY="dedd5295309e4e40b4a08b451cbf69d2"
        val url="https://newsapi.org/v2/"

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApiInterface::class.java)


        val completeNewsData=retrofit.getStoryHighlight("india+finance+news","relevancy",API_KEY)

        var titles:Array<String> = arrayOf()
        var description:Array<String> = arrayOf()
        var urlToImage:Array<String> = arrayOf()
        var publishedAt:Array<String> = arrayOf()
        var content:Array<String> = arrayOf()
        var author:Array<String> = arrayOf()
        var ArticleUrls:Array<String> = arrayOf()


        completeNewsData.enqueue(object : Callback<newsDataClass> {
            override fun onResponse(p0: Call<newsDataClass>, response: Response<newsDataClass>) {
                val newsData=response.body()

                if(newsData!=null){
                    for (data in newsData.articles) {
                        if(data.title != null && data.description != null && data.publishedAt != null && !data.title.equals("[Removed]")) {
                            titles += data.title
                            description += data.description
                            urlToImage += data.urlToImage
                            publishedAt += data.publishedAt
                            content += data.content
                            author += data.source.name
                            ArticleUrls += data.url
                            Log.d("LINKURL",data.url)
                        }
                    }

                }

                for (i in storyImageViews.indices){
                    if(urlToImage.size>0){
                    Picasso.get().load(urlToImage[i]).resize(150,150).centerCrop().into(storyImageViews[i])
                        }
                }


                storyImageViews[0]?.setOnClickListener{
                    openWebArticle(activity,0,ArticleUrls)
                }
                storyImageViews[1]?.setOnClickListener{
                    openWebArticle(activity,1,ArticleUrls)
                }
                storyImageViews[2]?.setOnClickListener{
                    openWebArticle(activity,2,ArticleUrls)
                }
                storyImageViews[3]?.setOnClickListener{
                    openWebArticle(activity,3,ArticleUrls)
                }
                storyImageViews[4]?.setOnClickListener{
                    openWebArticle(activity,4,ArticleUrls)
                }
                storyImageViews[5]?.setOnClickListener{
                    openWebArticle(activity,5,ArticleUrls)
                }
                storyImageViews[6]?.setOnClickListener{
                    openWebArticle(activity,6,ArticleUrls)
                }
                storyImageViews[7]?.setOnClickListener{
                    openWebArticle(activity,7,ArticleUrls)
                }
                storyImageViews[8]?.setOnClickListener{
                    openWebArticle(activity,8,ArticleUrls)
                }
                storyImageViews[9]?.setOnClickListener{
                    openWebArticle(activity,9,ArticleUrls)
                }
                storyImageViews[10]?.setOnClickListener{
                    openWebArticle(activity,10,ArticleUrls)
                }
                storyImageViews[11]?.setOnClickListener{
                    openWebArticle(activity,11,ArticleUrls)
                }
                storyImageViews[12]?.setOnClickListener{
                    openWebArticle(activity,12,ArticleUrls)
                }
                storyImageViews[13]?.setOnClickListener{
                    openWebArticle(activity,13,ArticleUrls)
                }
                storyImageViews[14]?.setOnClickListener{
                    openWebArticle(activity,14,ArticleUrls)
                }





            }


            override fun onFailure(p0: Call<newsDataClass>, p1: Throwable) {
                Log.d("FinanceNewsAPIError",p1.message.toString())
            }

        })


    }

    fun openWebArticle(activity: Activity,positin:Int,ArticleUrls:Array<String>){
        val intent = Intent(activity,OpenNewsArticleWeb::class.java)
        intent.putExtra("storyURL",ArticleUrls[positin])

        activity.startActivity(intent)
    }
}