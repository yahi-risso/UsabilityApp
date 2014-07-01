package es.upmcsic.car.usabilityapp.pruebas.bluetooth;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 66785338 on 12/06/14.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    TextView txtInfo;

    public MyBroadcastReceiver(TextView txtInfo){
        this.txtInfo=txtInfo;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (null != device.getName()) {
                Log.i("-->> ", device.getName());
                txtInfo.setText(txtInfo.getText() + "\n-->>[BLUETOOTH] DEVICE DISCOVERED: " + device.getName());
            } else if (null != device.getAddress()) {
                Log.i("-->> ", device.getAddress());
                txtInfo.setText(txtInfo.getText() + "\n-->>[BLUETOOTH] DEVICE DISCOVERED: " + device.getAddress());
            }
        }
    }
}
