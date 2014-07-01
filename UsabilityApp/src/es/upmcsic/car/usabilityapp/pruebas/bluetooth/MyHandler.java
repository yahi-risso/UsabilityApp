package es.upmcsic.car.usabilityapp.pruebas.bluetooth;

import android.os.Handler;
import android.os.Message;

import java.io.Serializable;
import java.lang.ref.WeakReference;

import es.upmcsic.car.usabilityapp.pruebas.io.ReadWriteActivity;
import es.upmcsic.car.usabilityapp.xmlvalidator.ValidationActivity;

/**
 * Created by 66785338 on 13/06/14.
 */
public class MyHandler extends Handler {

    private WeakReference<?> currentActivity;

    public MyHandler(BluetoothActivity activity){
    	currentActivity = new WeakReference<BluetoothActivity>(activity);
    }

    public MyHandler(ValidationActivity validation) {
    	currentActivity = new WeakReference<ValidationActivity>(validation);
	}

	@Override
    public void handleMessage(Message message){
		if(currentActivity.get() instanceof BluetoothActivity){
	        BluetoothActivity activity = (BluetoothActivity)currentActivity.get();
	        if (activity!= null){
	            activity.updateResults(message.getData().getString("info"));
	        }
		}
		if(currentActivity.get() instanceof ValidationActivity){
			ValidationActivity activity = (ValidationActivity)currentActivity.get();
	        if (activity!= null){
	        	if(message.getData().getString("validation") != null)
	        		activity.updateResults(message.getData().getString("validation"));
	        	if(message.getData().getString("infoDB") != null)
	        		activity.updateResults(message.getData().getString("infoDB"));
	        }
	        Serializable ses = message.getData().getSerializable("session");
	        if(ses!=null) activity.updateResults(ses);
		}
    }
}

