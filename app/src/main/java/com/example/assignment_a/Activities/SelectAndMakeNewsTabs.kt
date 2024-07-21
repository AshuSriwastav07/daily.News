package com.example.assignment_a.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment_a.R
import com.example.assignment_a.SQLDatabase.MySQLDatabase
import com.example.assignment_a.databinding.ActivityHomePageBinding


class SelectAndMakeNewsTabs : AppCompatActivity() {

    lateinit var binding: ActivityHomePageBinding


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set content view for the Home Page activity

        binding= ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbConnection=MySQLDatabase(this@SelectAndMakeNewsTabs,"TagsInfo",1)


        val submitNewsTags:Button=findViewById(R.id.submitNewsTags)

        var checkedCount = 0

// Set onClickListener for each checkbox
        binding.news1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkedCount++
                if (checkedCount > 3) {
                    binding.news1.isChecked = false
                    Toast.makeText(this, "You can only select up to 3 checkboxes", Toast.LENGTH_SHORT).show()
                }
            } else {
                checkedCount--
            }
        }


        binding.news2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkedCount++
                if (checkedCount > 3) {
                    binding.news2.isChecked = false
                    Toast.makeText(this, "You can only select up to 3 checkboxes", Toast.LENGTH_SHORT).show()
                }
            } else {
                checkedCount--
            }
        }


        binding.news3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkedCount++
                if (checkedCount > 3) {
                    binding.news3.isChecked = false
                    Toast.makeText(this, "You can only select up to 3 checkboxes", Toast.LENGTH_SHORT).show()
                }
            } else {
                checkedCount--
            }
        }

        binding.news4.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkedCount++
                if (checkedCount > 3) {
                    binding.news4.isChecked = false
                    Toast.makeText(this, "You can only select up to 3 checkboxes", Toast.LENGTH_SHORT).show()
                }
            } else {
                checkedCount--
            }
        }

        binding.news5.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkedCount++
                if (checkedCount > 3) {
                    binding.news5.isChecked = false
                    Toast.makeText(this, "You can only select up to 3 checkboxes", Toast.LENGTH_SHORT).show()
                }
            } else {
                checkedCount--
            }
        }

        binding.news6.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkedCount++
                if (checkedCount > 3) {
                    binding.news6.isChecked = false
                    Toast.makeText(this, "You can only select up to 3 checkboxes", Toast.LENGTH_SHORT).show()
                }
            } else {
                checkedCount--
            }
        }

        binding.news7.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkedCount++
                if (checkedCount > 3) {
                    binding.news7.isChecked = false
                    Toast.makeText(this, "You can only select up to 3 checkboxes", Toast.LENGTH_SHORT).show()
                }
            } else {
                checkedCount--
            }
        }

        submitNewsTags.setOnClickListener{

            Toast.makeText(this, "Please Wait News Tabs are creating...", Toast.LENGTH_SHORT).show()

            val newsCheckboxes = arrayOf(binding.news1, binding.news2, binding.news3, binding.news4, binding.news5, binding.news6, binding.news7)

            val newsTitles = arrayOf(
                "Crypto", "Tech", "Stocks", "Coding", "Entertainment",
                "New Startups", "Sports" )

            Handler(Looper.getMainLooper()).postDelayed({

            for (i in newsCheckboxes.indices) {
                if (newsCheckboxes[i].isChecked) {
                    val result = dbConnection.insertData(newsTitles[i], "true")
                    if (result == -1L) {
                        Toast.makeText(this, "Data not Inserted", Toast.LENGTH_LONG).show()
                    }
                }
            }

                // Create an Intent to start the next activity
                val intent= Intent(this,HomePageActivity::class.java)
                startActivity(intent)
                finish() // Finish the splash screen activity to prevent going back to it

            }, 5000) // Delay in milliseconds (15 seconds)

        }




    }

    }


