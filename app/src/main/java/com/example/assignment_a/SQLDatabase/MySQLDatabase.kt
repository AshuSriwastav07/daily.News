package com.example.assignment_a.SQLDatabase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MySQLDatabase(var context:Context,var dbName:String,var dbVersion:Int):SQLiteOpenHelper(context,dbName,null,dbVersion) {

    companion object{
        val tableName:String="TagsInfo"
        val col1:String="TagNumber"
        val col2:String="TagName"
        val col3:String="TagValue"
        val col4:String="TagName"

    }

    val query:String = "CREATE TABLE $tableName ($col1 INTEGER PRIMARY KEY AUTOINCREMENT, $col2 TEXT UNIQUE , $col3 TEXT)"

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(query)

    }

    fun insertData(TagName:String,TagValue:String):Long{
        val db:SQLiteDatabase=this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(col2,TagName)
        contentValues.put(col3,TagValue)

        val result:Long=db.insert(tableName,null,contentValues)

        return result

    }

    fun GetData():Cursor{
        val db:SQLiteDatabase=this.readableDatabase
        val cursor:Cursor=db.rawQuery("Select * from "+ tableName,null)
        return cursor
    }
    fun deleteAllData() {
        val db = this.writableDatabase
        db.delete(tableName, null, null)
        db.close()
    }
}