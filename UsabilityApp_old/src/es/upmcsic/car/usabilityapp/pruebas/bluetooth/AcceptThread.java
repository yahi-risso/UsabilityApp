package es.upmcsic.car.usabilityapp.pruebas.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by 66785338 on 10/06/14.
 */
public class AcceptThread implements Runnable {

    private final BluetoothServerSocket mmServerSocket;
    private MyHandler resultHandler;

    public AcceptThread(BluetoothAdapter mBluetoothAdapter, MyHandler resultHandler) {
        this.resultHandler = resultHandler;
        BluetoothServerSocket tmp = null;
        UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        String NAME = "UsabilityApp";
        try {
            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
            Log.i("-->> ", "BluetoothServer OK");
            String info="\n-->>[BLUETOOTH] " + "BluetoothServer OK";
            publishProgress(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mmServerSocket = tmp;
    }

    public void run() {
        Log.i("-->> ","RUN.........");
        BluetoothSocket socket = null;
        while (true) {
            try {
                socket = mmServerSocket.accept();
                Log.i("-->> ", "SOCKET ACCEPTED");
                String info="\n-->>[BLUETOOTH] " + "Socket Accepted";
                publishProgress(info);
            } catch (IOException e) {
                Log.i("-->> ", e.getMessage());
                break;
            }
            if (socket != null) { // If a connection was accepted
                manageConnectedSocket(socket);
                try {
                    mmServerSocket.close();
                } catch (IOException e) {
                    Log.i("-->> ", e.getMessage());
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void manageConnectedSocket(BluetoothSocket socket) {
        Log.i("-->> ", "dentro de MANAGE CONNECTION");
        String result = "";
        DataInputStream in = null;
        try {
            in = new DataInputStream(socket.getInputStream());
            result = in.readUTF();
            Log.i("-->> LEIDO: ",result);
            final String finalResult = result;
            String info="\n-->>[BLUETOOTH] " + "Leido: "+ finalResult;
            publishProgress(info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void publishProgress(String newItem) {
        Bundle msgBundle = new Bundle();
        msgBundle.putString("info", newItem);
        Message msg = new Message();
        msg.setData(msgBundle);
        resultHandler.sendMessage(msg);
    }
}