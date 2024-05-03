package com.example.assignment_a.Adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment_a.Activities.OpenNewsArticleWeb
import com.example.assignment_a.R
import com.squareup.picasso.Picasso

class MyNewsHeadlinesAdapter(val context: Activity,val headlinesTitles:Array<String>,val headLinesDateTime:Array<String>,val headLinesImageUrl:Array<String>,val headLinesNews:Array<String>,val newsPaperName:Array<String>,val ArticleUrl:Array<String>):
    RecyclerView.Adapter<MyNewsHeadlinesAdapter.MyViewHolder>(){

        inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener{

            val title:TextView=itemView.findViewById(R.id.HeadLineTitle)
            val dateTime:TextView=itemView.findViewById(R.id.HeadLineTime)
            val headLineImage:ImageView=itemView.findViewById(R.id.HeadLineImageView)
            val headLineNews:TextView=itemView.findViewById(R.id.HeadLineNews)

            init {
                itemView.setOnClickListener(this)
            }


            override fun onClick(v: View?) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val URL = ArticleUrl[position]
                    Log.d("MyNewsHeadlinesAdapter", "ArticleUrl[$position]: $URL") // Debug log

                    val intent = Intent(context, OpenNewsArticleWeb::class.java)
                    intent.putExtra("HeadlinesArticleUrl", URL)
                    context.startActivity(intent)


                }

            }

        }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val itemView=LayoutInflater.from(context).inflate(R.layout.headlines_item_view,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return headlinesTitles.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.title.text = headlinesTitles[position]
            var dateTimeEdit=headLinesDateTime[position].replace("T"," ")
            dateTimeEdit=dateTimeEdit.replace("Z"," ")
            val dateAndNewsPaper=dateTimeEdit+" , By "+newsPaperName[position]
            holder.dateTime.text = dateAndNewsPaper

            holder.headLineNews.text = headLinesNews[position]

            Picasso.get()
                .load(headLinesImageUrl[position])
                .fit()
                .into(holder.headLineImage)
        }

        interface RecyclerViewEvent{
            fun onItemClick(url:Array<String>,position: Int)
        }
    }



/*New Finance Adapter class*/

class MyFinanceAdapter(val context: Activity, private val financeTitles:Array<String>, private val financeDateTime:Array<String>, private val financeImageUrl:Array<String>, private val financeNews:Array<String>, private val financeNewsSource:Array<String>, private val articleUrl:Array<String>):
    RecyclerView.Adapter<MyFinanceAdapter.MyViewHolder>(){
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener{

        val title:TextView=itemView.findViewById(R.id.HeadLineTitle)
        val dateTime:TextView=itemView.findViewById(R.id.HeadLineTime)
        val financeImageView:ImageView=itemView.findViewById(R.id.HeadLineImageView)
        val financeNews:TextView=itemView.findViewById(R.id.HeadLineNews)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position=adapterPosition
            if(position!=RecyclerView.NO_POSITION){

                val intent = Intent(context, OpenNewsArticleWeb::class.java)
                val URL = articleUrl[position] // Assuming articleUrl[position] returns a String
                intent.putExtra("FinanceNewsUrls", URL)
                context.startActivity(intent)


            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(context).inflate(R.layout.headlines_item_view,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return financeTitles.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = financeTitles[position]
        var dateTimeEdit=financeDateTime[position].replace("T"," ")
        dateTimeEdit=dateTimeEdit.replace("Z"," ")
        val dateAndNewsPaper=dateTimeEdit+" , By "+financeNewsSource[position]
        holder.dateTime.text = dateAndNewsPaper

        holder.financeNews.text = financeNews[position]

        Picasso.get()
            .load(financeImageUrl[position])
            .fit()
            .into(holder.financeImageView)
    }


    interface RecyclerViewEvent{
        fun onItemClick(url:Array<String>,position: Int)
    }

}

/*Detail News Adapter*/

class DetailNewsAdapter(val context: Activity,private val newsTitle:Array<String>, private val newsContent:Array<String>,private val newsDateTime:Array<String>,private val newsImageUrl:Array<String>,private val newsSource:Array<String>,private val articleUrl:Array<String>):RecyclerView.Adapter<DetailNewsAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val title:TextView=itemView.findViewById(R.id.HeadLineTitle)
        val dateTime:TextView=itemView.findViewById(R.id.HeadLineTime)
        val ImageView:ImageView=itemView.findViewById(R.id.HeadLineImageView)
        val News:TextView=itemView.findViewById(R.id.HeadLineNews)

        init {
            itemView.setOnClickListener(this)

        }
        override fun onClick(v: View?) {
            val position=adapterPosition
            if(position!=RecyclerView.NO_POSITION){
                val intent = Intent(context, OpenNewsArticleWeb::class.java)
                val URL = articleUrl[position] // Assuming articleUrl[position] returns a String
                intent.putExtra("DetailNewsUrls", URL)
                context.startActivity(intent)
            }
        }
    }




    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView=LayoutInflater.from(context).inflate(R.layout.headlines_item_view,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.title.text=newsTitle[position]
        holder.dateTime.text=newsDateTime[position] + " "+ newsSource[position]
        holder.News.text=newsContent[position]

        Picasso.get()
            .load(newsImageUrl[position])
            .fit()
            .into(holder.ImageView)



    }

    override fun getItemCount(): Int {
      return newsTitle.size
    }

    interface RecyclerViewEvent{
        fun onItemClick(url:Array<String>,position: Int)
    }


}



