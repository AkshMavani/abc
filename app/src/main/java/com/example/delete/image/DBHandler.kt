package com.example.delete.image

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHandler(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {

        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WALLPAPER + " TEXT, "
                + IMG_COL + " TEXT)")

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query)
    }

    // this method is use to add new course to our sqlite database.
    fun addNewCourse(
        wallpaper:Int,
        img_url: String?,
    ) {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(IMG_COL, img_url)
        values.put(WALLPAPER, wallpaper)
        db.insert(TABLE_NAME, null, values)

    }

    fun updateCourse(
        wallpaper:Int,
        img_url: String?,
    ) {

        val db = this.writableDatabase
        val values = ContentValues()

        values.put(IMG_COL, img_url)
        values.put(WALLPAPER, wallpaper)
        db.update(TABLE_NAME, values, "description=?", arrayOf(wallpaper.toString()))

    }
    fun valueExists(value: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $WALLPAPER = ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(value))
        val exists = cursor.count > 0
        cursor.close()

        return exists
    }

    // we have created a new method for reading all the courses.
    fun readCourses(): ArrayList<model_wallpaper>? {

        val db = this.readableDatabase

        val cursorCourses = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        val courseModalArrayList: ArrayList<model_wallpaper> = ArrayList<model_wallpaper>()

        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from
                // cursor to our array list.
                courseModalArrayList.add(
                    model_wallpaper(
                        cursorCourses.getInt(1),
                        cursorCourses.getString(2),

                    )
                )
            } while (cursorCourses.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.

        return courseModalArrayList
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }


    companion object {
        // creating a constant variables for our database.
        // below variable is for our database name.
        private const val DB_NAME = "coursedb"

        // below int is our database version
        private const val DB_VERSION = 3

        // below variable is for our table name.
        private const val TABLE_NAME = "mycourses"

        // below variable is for our id column.
        private const val ID_COL = "id"

        // below variable is for our course name column
        private const val NAME_COL = "name"

        // below variable id for our course duration column.
        private const val DURATION_COL = "duration"

        // below variable for our course description column.
        private const val WALLPAPER = "description"

        // below variable is for our course tracks column.
        private const val IMG_COL = "tracks"
    }

}