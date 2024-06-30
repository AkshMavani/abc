package com.example.delete.api

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHandler  // creating a constructor for our database handler.
    (context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    // below method is for creating a database by running a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT, "
                + TRACKS_COL + " TEXT)")
        db.execSQL(query)
    }

    // this method is use to add new course to our sqlite database.
    fun addNewCourse(
        courseName: Boolean,
        cname:String
    ) {
        val db = this.writableDatabase


        val values = ContentValues()
        values.put(TRACKS_COL, courseName)
        values.put(NAME_COL, cname)
        db.insert(TABLE_NAME, null, values)

    }
    fun readCourses(): ArrayList<CourseModal>? {
        val db = this.readableDatabase
        val cursorCourses = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val courseModalArrayList: ArrayList<CourseModal> = ArrayList<CourseModal>()
        if (cursorCourses.moveToFirst()) {
            do {
                courseModalArrayList.add(
                    CourseModal(
                        cursorCourses.getInt(2),
                        cursorCourses.getString(1)
                    )
                )
            } while (cursorCourses.moveToNext())
            // moving our cursor to next.
        }

        return courseModalArrayList
    }
    fun updateHeartState(isFilled: Boolean,name:String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(TRACKS_COL, if (isFilled) 1 else 0)
        }
    db.update(TABLE_NAME, values, "$NAME_COL = ?", arrayOf(name))
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
     fun heartStateExists(nme: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_NAME, arrayOf(NAME_COL), "$NAME_COL = ?", arrayOf(nme), null, null, null)
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }
    companion object {
        private const val DB_NAME = "coursedb"
        private const val DB_VERSION = 3
        private const val TABLE_NAME = "mycourses"
        private const val ID_COL = "id"
        private const val NAME_COL = "name"

        // below variable id for our course duration column.
        private const val DURATION_COL = "duration"

        // below variable for our course description column.
        private const val DESCRIPTION_COL = "description"

        // below variable is for our course tracks column.
        private const val TRACKS_COL = "tracks"
    }
}