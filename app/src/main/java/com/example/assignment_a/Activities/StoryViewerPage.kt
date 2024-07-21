package com.example.assignment_a.Activities

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_a.R
import com.squareup.picasso.Picasso

class StoryViewerPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_viewer_page)

        var BGImage:ImageView=findViewById(R.id.storyBGImage)
        var StoryTitle: TextView =findViewById(R.id.Storytitle)
        var StorySource: TextView =findViewById(R.id.StorySource)
        var StoryNews: TextView =findViewById(R.id.StoryDate)
        var StoryCompleteNews: TextView =findViewById(R.id.StoryCompleteNews)


        val StoryData: ArrayList<String>? = intent.getStringArrayListExtra("HeadlinesArticleUrl")

        Picasso.get().load(StoryData?.get(1)).fit().centerInside(). into(BGImage);
        StoryTitle.text=StoryData?.get(0)
        StorySource.text=StoryData?.get(4)
        StoryNews.text=StoryData?.get(3)
        StoryCompleteNews.text=StoryData?.get(2)

        if (StoryData != null) {
            Log.d("imageURL", StoryData.get(0))
        }


    }

    }
