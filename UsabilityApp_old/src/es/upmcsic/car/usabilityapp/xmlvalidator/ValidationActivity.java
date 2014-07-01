package es.upmcsic.car.usabilityapp.xmlvalidator;

import es.upmcsic.car.usabilityapp.R;
import es.upmcsic.car.usabilityapp.pruebas.bluetooth.MyHandler;
import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ValidationActivity extends Activity {

	MyHandler resultHandler;
	TextView txtValidation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.validation);
		resultHandler = new MyHandler(this);
		
		txtValidation = (TextView) findViewById(R.id.textViewValidation);
		txtValidation.setMovementMethod(new ScrollingMovementMethod());
		Thread validateSession = new Thread(new ObjectCreatorThread(resultHandler, this));
		validateSession.start();
	}
	
	public void updateResults(String validation) {
		txtValidation.setText(txtValidation.getText() + validation);
    }
}
