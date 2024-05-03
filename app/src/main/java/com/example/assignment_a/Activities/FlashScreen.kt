package com.example.assignment_a.Activities

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_a.R
import com.example.assignment_a.SQLDatabase.MySQLDatabase

class FlashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash_screen)

        Handler(Looper.getMainLooper()).postDelayed({

            val dbConnection= MySQLDatabase(this@FlashScreen,"TagsInfo",1)
            dbConnection.readableDatabase
            val cursor: Cursor =dbConnection.GetData()

            if(cursor.count>0) {
                val intent= Intent(this,HomePageActivity::class.java)
                startActivity(intent)
            }else{
                val intent= Intent(this,SelectAndMakeNewsTabs::class.java)
                startActivity(intent)

            }
        }, 3000) // Delay in milliseconds (15 seconds)

    }
}