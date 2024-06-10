package com.example.delete.bllotothh

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delete.R


class MainActivity : AppCompatActivity(),RecylerviewCliCK {
    lateinit var tvSwitchText: TextView
    lateinit var btnSwitch: SwitchCompat
    lateinit var cvList: CardView
    lateinit var rvList: RecyclerView
    private var adapter: ListAdapter? = null
    private val list: ArrayList<Model> = ArrayList<Model>()
    lateinit var bluetoothAdapter: BluetoothAdapter
    lateinit var bluetoothManager: BluetoothManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)
        tvSwitchText = findViewById(R.id.tvswitchText)
        btnSwitch = findViewById(R.id.btnSwitch)
        cvList = findViewById(R.id.cvList)
        rvList = findViewById(R.id.devicelist)

        adapter = ListAdapter(list,this)
        rvList.setLayoutManager(LinearLayoutManager(this))
        rvList.setAdapter(adapter)


        btnSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton: CompoundButton?, b: Boolean ->
            if (b) {
                if (ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.BLUETOOTH_CONNECT
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        ActivityCompat.requestPermissions(
                            this@MainActivity,
                            arrayOf(Manifest.permission.BLUETOOTH_CONNECT),
                            100
                        )
                        return@OnCheckedChangeListener
                    }
                }
                bluetoothManager = getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
                bluetoothAdapter = if (Build.VERSION.SDK_INT >= 31) {
                    bluetoothManager!!.getAdapter()
                } else {
                    BluetoothAdapter.getDefaultAdapter()
                }
                val pairedDevices = bluetoothAdapter.getBondedDevices()
                if (pairedDevices.size > 0) {
                    list.clear()
                    for (device in pairedDevices) {
                        val bluetooth = Model()
                        bluetooth.name = device.name
                        list.add(bluetooth)
                    }
                    adapter!!.notifyDataSetChanged()
                }
                //check self permisson
                cvList.setVisibility(View.VISIBLE)
                tvSwitchText.setText("Show List")
            } else {
                tvSwitchText.setText("Hide List")
                cvList.setVisibility(View.GONE)
            }
        })

    }

    override fun itemClick(s: String?) {

    }

}