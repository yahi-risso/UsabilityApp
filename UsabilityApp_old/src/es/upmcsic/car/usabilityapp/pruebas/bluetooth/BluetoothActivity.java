package es.upmcsic.car.usabilityapp.pruebas.bluetooth;

/**
 * Created by 66785338 on 27/05/14.
 */

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

import es.upmcsic.car.usabilityapp.R;

public class BluetoothActivity extends Activity {

    TextView txtInfo;

    BluetoothAdapter mBluetoothAdapter;
    MyBroadcastReceiver mReceiver;
    MyHandler resultHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth);

        resultHandler = new MyHandler(this);

        Button buttonActiveBT = (Button) findViewById(R.id.buttonActiveBluetooth);
        buttonActiveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activeBluetooth();
            }
        });

        Button buttonGetDevices = (Button) findViewById(R.id.buttonGetDevices);
        buttonGetDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDevices();
            }
        });

        Button buttonListener = (Button) findViewById(R.id.buttonListener);
        buttonListener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listenConnections();
            }
        });

        Button buttonConnect = (Button) findViewById(R.id.buttonConnect);
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //connect();
            }
        });

        txtInfo = (TextView) findViewById(R.id.textViewInfo);

    }

    public void activeBluetooth() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Log.i("-->>[BLUETOOTH] ", "NOT SUPPORTED");
            txtInfo.setText(txtInfo.getText() + "\n-->>[BLUETOOTH] " + "NOT SUPPORTED");
        } else {
            Log.i("-->>[BLUETOOTH] ", "SUPPORTED!!!!!!!!");
            txtInfo.setText(txtInfo.getText() + "\n-->>[BLUETOOTH] " + " SUPPORTED");
            if (!mBluetoothAdapter.isEnabled()) {
                Log.i("-->>[BLUETOOTH] ", "NOT ENABLED");
                txtInfo.setText(txtInfo.getText() + "\n-->>[BLUETOOTH] " + "NOT ENABLED");
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 0);
            } else {
                Log.i("-->>[BLUETOOTH] ", "ENABLED!!!!!!!!");
                txtInfo.setText(txtInfo.getText() + "\n-->>[BLUETOOTH] " + "ENABLED");
                String status;
                String mydeviceaddress = mBluetoothAdapter.getAddress();
                String mydevicename = mBluetoothAdapter.getName();
                status = mydevicename + " : " + mydeviceaddress;
                Log.i("-->>[BLUETOOTH] ", "STATUS: " + status);
                txtInfo.setText(txtInfo.getText() + "\n-->>[BLUETOOTH] STATUS: " + status);
            }
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 500);
            startActivity(discoverableIntent);
        }
    }

    public void getDevices() {
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) { // If there are paired devices
            for (BluetoothDevice device : pairedDevices) {
                Log.i("-->>[BLUETOOTH] DEVICE PAIRED: ", device.toString());
                txtInfo.setText(txtInfo.getText() + "\n-->>[BLUETOOTH] " + "DEVICE PAIRED: " + device.toString());
            }
        } else {
            Log.i("-->>[BLUETOOTH] ", "NO PAIRED DEVICES");
            txtInfo.setText(txtInfo.getText() + "\n-->>[BLUETOOTH] " + "NO PAIRED DEVICES");
        }

        mBluetoothAdapter.startDiscovery();
        mReceiver = new MyBroadcastReceiver(txtInfo);
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);
    }

    public void listenConnections() {
        Thread listenThread = new Thread(new AcceptThread(mBluetoothAdapter, resultHandler));
        listenThread.start();
        Log.i("-->> [BLUETOOTH]", "Listening....");
        txtInfo.setText(txtInfo.getText() + "\n-->>[BLUETOOTH] Listening.....");
    }

    public void updateResults(String info) {
        txtInfo.setText(txtInfo.getText() + info);
    }
}