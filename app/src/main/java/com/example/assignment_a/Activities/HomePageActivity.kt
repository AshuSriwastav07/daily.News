package com.example.assignment_a.Activities

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.assignment_a.Adapters.ViewPagerAdapter
import com.example.assignment_a.R
import com.example.assignment_a.SQLDatabase.MySQLDatabase
import com.example.assignment_a.Tabs.DetailesNewsTab2Fragment
import com.example.assignment_a.Tabs.FinanceNewsTab3Fragment
import com.example.assignment_a.Tabs.HeadLinesTab1Fragment
import com.example.assignment_a.Tabs.UserDefineTab1
import com.example.assignment_a.Tabs.UserDefineTab2
import com.google.android.material.tabs.TabLayout

class HomePageActivity : AppCompatActivity() {

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        // Clear the back stack
        val intent = Intent(this, HomePageActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

        // Close the app
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page_activity)

        val db=MySQLDatabase(this@HomePageActivity,"TagsInfo",1)
        val cursor:Cursor=db.GetData()

        val deleteButton:ImageButton=findViewById(R.id.deleteDB)
        val tabLayout:TabLayout=findViewById(R.id.tabLayout)
        val viewPager:ViewPager=findViewById(R.id.viewPager)   //Always check which item you are finding with right name and item type
        val adapter = ViewPagerAdapter(supportFragmentManager)  // set tabs in viewpager

        var tagName=ArrayList<String>()
        if(cursor.count>0){
            while (cursor.moveToNext()){
                tagName.add(cursor.getString(1))
            }
        }

        if(tagName.isNotEmpty()) {

            adapter.addFragment(HeadLinesTab1Fragment(), "Fast")
            adapter.addFragment(FinanceNewsTab3Fragment(), "Finance")

            when (tagName.count()){
                1 -> adapter.addFragment(DetailesNewsTab2Fragment(), tagName[0])
                2 -> {
                    adapter.addFragment(DetailesNewsTab2Fragment(), tagName[0])
                    adapter.addFragment(UserDefineTab1(), tagName[1])
                }
                3 -> {
                    adapter.addFragment(DetailesNewsTab2Fragment(), tagName[0])
                    adapter.addFragment(UserDefineTab1(), tagName[1])
                    adapter.addFragment(UserDefineTab2(), tagName[2])
                }

            }

        }

        Log.d("TagData",tagName.count().toString())

        deleteButton.setOnClickListener{

            db.deleteAllData()
            // Optionally, notify the user that data has been deleted
            Toast.makeText(this, "All News Tags Deleted Select Again.", Toast.LENGTH_SHORT).show()
            val intent=Intent(this,SelectAndMakeNewsTabs::class.java)
            startActivity(intent)
        }


    /*if(tagName.isNotEmpty()){
        if(tagName.count().equals(1)) {
            adapter.addFragment(UserDefineTab1(), tagName[0])
        }else if(tagName.count()==2) {
            adapter.addFragment(UserDefineTab1(), tagName[0])
            adapter.addFragment(UserDefineTab2(), tagName[1])
         }else if(tagName.count()>=3){
            adapter.addFragment(UserDefineTab1(), tagName[0])
            adapter.addFragment(UserDefineTab2(), tagName[1])
            adapter.addFragment(UserDefineTab3(), tagName[2])
        }

        }*/


        viewPager.adapter=adapter
        tabLayout.setupWithViewPager(viewPager)




    }
}