package com.example.assignment_a.Activities

import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_a.R

class OpenNewsArticleWeb : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_news_article_web)

        val webView = findViewById<WebView>(R.id.WebView)
        val intent = intent

        val FinanceNewsUrl = intent.getStringExtra("FinanceNewsUrls")
        val HeadlinesUrl = intent.getStringExtra("HeadlinesArticleUrl")
        val DetailNewsUrls = intent.getStringExtra("DetailNewsUrls")
        val storyUrl = intent.getStringExtra("storyURL")

        // Set up WebViewClient to handle URL loading
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()
                view?.loadUrl(url)
                return true
            }
        }

        // Enable JavaScript and other settings
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true

        // Load the appropriate URL
        when {
            !FinanceNewsUrl.isNullOrEmpty() -> {
                webView.loadUrl(FinanceNewsUrl)
            }
            !HeadlinesUrl.isNullOrEmpty() -> {
                webView.loadUrl(HeadlinesUrl)
            }
            !DetailNewsUrls.isNullOrEmpty() -> {
                webView.loadUrl(DetailNewsUrls)
            }
            !storyUrl.isNullOrEmpty() -> {
                webView.loadUrl(storyUrl)
            }

            else -> {
                Log.e("WebView", "Unable to Open News Page!")
                Toast.makeText(this, "Unable to Open Article in App!", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun shouldOverrideUrlLoading(webView:WebView, request: WebResourceRequest):Boolean{
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()
                when {
                    url.startsWith("http://") || url.startsWith("https://") -> {
                        view?.loadUrl(url)
                        return true
                    }
                    url.startsWith("mailto:") -> {
                        // Handle mailto links
                        // val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(url))
                        // startActivity(intent)
                        return true
                    }
                    url.startsWith("tel:") -> {
                        // Handle tel links
                        // val intent = Intent(Intent.ACTION_DIAL, Uri.parse(url))
                        // startActivity(intent)
                        return true
                    }
                    else -> return false
                }
            }
        }
        return false
    }

}