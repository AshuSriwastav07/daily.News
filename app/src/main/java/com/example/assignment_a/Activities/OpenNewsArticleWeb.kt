package com.example.assignment_a.Activities

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_a.R

class OpenNewsArticleWeb : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_news_article_web)

        val webView=findViewById<WebView>(R.id.WebView)
        val intent = intent

        val FinanceNewsUrl = intent.getStringExtra("FinanceNewsUrls")
        val HeadlinesUrl = intent.getStringExtra("HeadlinesArticleUrl")
        val DetailNewsUrls = intent.getStringExtra("DetailNewsUrls")

        if (!FinanceNewsUrl.isNullOrEmpty()) {
            webView.loadUrl(FinanceNewsUrl)
        } else if (!HeadlinesUrl.isNullOrEmpty()) {
            webView.loadUrl(HeadlinesUrl)
        }else if(!DetailNewsUrls.isNullOrEmpty()){
            webView.loadUrl(DetailNewsUrls)
        }else {
            Log.e("WebView", "Unable to Open News Page!")
            Toast.makeText(this,"Unable to Open Article in App!",Toast.LENGTH_LONG).show()
        }



    }
}