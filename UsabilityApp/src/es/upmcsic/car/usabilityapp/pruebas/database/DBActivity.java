package es.upmcsic.car.usabilityapp.pruebas.database;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import es.upmcsic.car.usabilityapp.R;
import es.upmcsic.car.usabilityapp.model.Session;

public class DBActivity extends Activity {

	Session session;
	private DatabaseHandler db;
	TextView txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.database);

		db = new DatabaseHandler(this.getApplicationContext());
		db.getWritableDatabase();
		
		Intent myIntent = getIntent(); // gets the previously created intent
		this.session = (Session) myIntent.getSerializableExtra("session");

		txt = (TextView) findViewById(R.id.textInfoDB);
		txt.setMovementMethod(new ScrollingMovementMethod());

		Button buttonWriteDB = (Button) findViewById(R.id.buttonWriteDB);
		buttonWriteDB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				guardarEnBBDD(session);
			}
		});
		
		Button buttonReadDB = (Button) findViewById(R.id.buttonReadDB);
		buttonReadDB.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				leerDeBBDD(session.getUserId());
			}
		});
	}

	private void guardarEnBBDD(Session session2) {
		String idUser = session2.getUserId();
		Log.i("Insert: ", "Inserting ..");
		db.addSession(session2);
		txt.setText("GuaRDADO en DB");
	}

	private void leerDeBBDD(String user) {
		Log.d("Reading: ", "Reading session.. " + user);
		List<Session> sessions = db.getSessionsByField(db.USER_ID, user);
		txt.setText("Sesiones leidas con "+db.USER_ID+" = "+user);
		for (Session ses : sessions) {
			txt.setText(ses.toString());
		}
	}
}
