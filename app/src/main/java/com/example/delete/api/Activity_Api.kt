package com.example.delete.api

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delete.R


class Activity_Api : AppCompatActivity() {
    var adapter:Adapter?=null
    var arr:ArrayList<Model_abc> = ArrayList()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)
        val recyclerView: RecyclerView =findViewById(R.id.rc1)
//        val progressbarDialog= ProgressDialog(this)
//        progressbarDialog.setCancelable(false)
//        progressbarDialog.setMessage("please wait...")
//        progressbarDialog.show()
        recyclerView.layoutManager= LinearLayoutManager(this)
        arr.add(Model_abc(R.drawable.abc,"abc"))
        arr.add(Model_abc(R.drawable.def,"def"))
        arr.add(Model_abc(R.drawable.ghi,"ghi"))
        arr.add(Model_abc(R.drawable.jk,"jk"))
        arr.add(Model_abc(R.drawable.mn,"mn"))
        arr.add(Model_abc(R.drawable.op,"op"))

                adapter=Adapter(arr,this)
                recyclerView.adapter=adapter
                adapter!!.notifyDataSetChanged()

//        viewModel.onTopCoinsPairListAPICall()
//        viewModel.mutImageAPIResponse.observe(this, Observer {
//            if (it!=null){
//                progressbarDialog.dismiss()
//                arr.clear()
//                for (i in it){
//                    arr.add(it)
//                }
//                Log.e("TAG", "onCreate: $arr")
//                var adapter=Adapter(arr,this)
//                saveData(arr)
//                recyclerView.adapter=adapter
//            }
//        })
    }
//    private fun loadData() {
//        val recyclerView: RecyclerView =findViewById(R.id.rc1)
//        recyclerView.layoutManager= LinearLayoutManager(this)
//        // method to load arraylist from shared prefs
//        // initializing our shared prefs with name as
//        // shared preferences.
//        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
//
//        // creating a variable for gson.
//        val gson = Gson()
//
//        // below line is to get to string present from our
//        // shared prefs if not present setting it as null.
//        val json = sharedPreferences.getString("courses", null)
//
//        // below line is to get the type of our array list.
//        val type: Type = object : TypeToken<ArrayList<Model_Response?>?>() {}.type
//
//        // in below line we are getting data from gson
//        // and saving it to our array list
//        arr = gson.fromJson<Model_Response>(json, type) as ArrayList<Model_Response>
//
//        // checking below if the array list is empty or not
//        if (arr == null) {
//            // if the array list is empty
//            // creating a new array list.
//            arr = ArrayList<Model_Response>()
//        }
//        Log.e("TAG_ARR", "loadData:$arr ", )
//
//                val adapter=Adapter(arr,this)
//                recyclerView.adapter=adapter
//    }
//
//    private fun saveData(arr:ArrayList<Model_Response>) {
//        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        val gson = Gson()
//        val json = gson.toJson(arr)
//        editor.putString("courses", json)
//        editor.apply()
//        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show()
//    }
    override fun onResume() {
    adapter?.notifyDataSetChanged()
        super.onResume()

    }
}