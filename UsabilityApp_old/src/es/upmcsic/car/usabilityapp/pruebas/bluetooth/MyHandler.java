package es.upmcsic.car.usabilityapp.pruebas.bluetooth;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

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
	            activity.updateResults(message.getData().getString("validation"));
	        }
		}
    }
}

