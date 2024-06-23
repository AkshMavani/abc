package com.example.delete.bllotothh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.delete.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity_list extends AppCompatActivity {
    Button btnDiscover;
    private CardView cvList;
    private RecyclerView rvList;
    private ListAdapter2 adapter;
    private ArrayList<Model2> list = new ArrayList<>();
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothManager bluetoothManager;
    private static final UUID MY_UUID = UUID.fromString("0000110A-0000-1000-8000-00805F9B34FB");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        btnDiscover = findViewById(R.id.btnDiscover);
        cvList = findViewById(R.id.cvList);
        rvList = findViewById(R.id.devicelist);

        bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        btnDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity_list.this, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_DENIED) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        ActivityCompat.requestPermissions(MainActivity_list.this, new String[]{Manifest.permission.BLUETOOTH_SCAN}, 100);
                        return;
                    }
                }
                IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
                intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
                registerReceiver(receiver, intentFilter);
                bluetoothAdapter.startDiscovery();
            }
        });

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter);

        adapter = new ListAdapter2(list, new RecylerviewCliCK() {
            @Override
            public void itemClick(String address) {
                BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);
                connectToDevice(device);
            }
        });

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);
    }

    private void connectToDevice(BluetoothDevice device) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= 31) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, 101);
            }
            return;
        }
        new Thread(new Runnable() {
            @SuppressLint("MissingPermission")
            @Override
            public void run() {
                BluetoothSocket socket = null;
                try {
                    socket = device.createRfcommSocketToServiceRecord(MY_UUID);
                    bluetoothAdapter.cancelDiscovery(); // Cancel discovery to save resources and improve connection speed
                    socket.connect();
                    Log.e("TAG123", "Connected to: " + device.getName());
                } catch (IOException e) {
                    Log.e("TAG123", "Connection failed", e);
                    closeSocket(socket);
                }
            }
        }).start();
    }

    private void closeSocket(BluetoothSocket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                Log.e("TAG123", "Failed to close socket", e);
            }
        }
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (ActivityCompat.checkSelfPermission(MainActivity_list.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                Model2 bluetooth = new Model2();
                bluetooth.name = device.getName();
                bluetooth.address = device.getAddress();
                int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);
                bluetooth.rssi = rssi;
                list.add(bluetooth);
                double distance = calculateDistance(rssi);
                adapter.notifyDataSetChanged();
                Log.e("TAG123", "onReceive: " + device.getName());
                Log.e("TAG123", "onReceive: " + distance);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
    private double calculateDistance(int rssi) {
        int txPower = -59; // Default TX power value, you may need to adjust this value based on your environment and device
        return Math.pow(10, (txPower - rssi) / 20.0);
    }
}
