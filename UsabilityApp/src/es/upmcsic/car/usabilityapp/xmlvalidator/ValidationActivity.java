package es.upmcsic.car.usabilityapp.xmlvalidator;

import java.io.Serializable;


import es.upmcsic.car.usabilityapp.R;
import es.upmcsic.car.usabilityapp.model.Session;
import es.upmcsic.car.usabilityapp.pruebas.bluetooth.MyHandler;
import es.upmcsic.car.usabilityapp.pruebas.database.DBActivity;
import es.upmcsic.car.usabilityapp.pruebas.database.SaveDBThread;
import es.upmcsic.car.usabilityapp.pruebas.graphic.GraphicActivity;
import es.upmcsic.car.usabilityapp.pruebas.io.ReadWriteActivity;
import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ValidationActivity extends Activity {

	MyHandler resultHandler;
	TextView txtValidation;
	private Session session;
	
	Button buttonGraphic;
	Button buttonBBDD;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.validation);
		resultHandler = new MyHandler(this);
		
		txtValidation = (TextView) findViewById(R.id.textViewValidation);
		txtValidation.setMovementMethod(new ScrollingMovementMethod());
		Thread validateSession = new Thread(new ObjectCreatorThread(resultHandler, this));
		validateSession.start();

		buttonGraphic = (Button) findViewById(R.id.buttonGraphics);
		buttonGraphic.setVisibility(View.INVISIBLE);
		buttonGraphic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), GraphicActivity.class);
				//Log.i("***SESSION ENVIADA; ", getSession().toString());
				intent.putExtra("session", getSession());
                startActivity(intent);
			}
		});

		buttonBBDD = (Button) findViewById(R.id.buttonBBDD);
		buttonBBDD.setVisibility(View.INVISIBLE);
		buttonBBDD.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), DBActivity.class);
				intent.putExtra("session", getSession());
                startActivity(intent);
			}
		});
	}
	
	public Activity getActivity(){
		return this;
	}
	
	public void updateResults(String validation) {
		txtValidation.setText(txtValidation.getText() + validation);
    }
	
	public void updateResults(Serializable mySession) {
		setSession((Session) mySession);
		txtValidation.setText(getSession().toString());
		buttonGraphic.setVisibility(View.VISIBLE);
		buttonBBDD.setVisibility(View.VISIBLE);
    }

	Session getSession() {
		return session;
	}

	void setSession(Session session) {
		this.session = session;
	}
}
