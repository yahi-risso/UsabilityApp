package es.upmcsic.car.usabilityapp.pruebas.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import es.upmcsic.car.usabilityapp.R;

public class ReadWriteActivity extends Activity {

	EditText txtToWrite;
	TextView txtReading;
	String FILE_NAME = "Prueba.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read_write);

		txtToWrite = (EditText) findViewById(R.id.editTextToWrite);
		txtReading = (TextView) findViewById(R.id.textRead);
		//txtReading.setMovementMethod(new ScrollingMovementMethod());

		Button buttonWrite = (Button) findViewById(R.id.buttonWrite);
		buttonWrite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
			}
		});

		Button buttonRead = (Button) findViewById(R.id.buttonRead);
		buttonRead.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				read();
			}
		});

	}

	public void save() {
		try {
			FileOutputStream fOut = openFileOutput(FILE_NAME, MODE_WORLD_READABLE);
			String txt = txtToWrite.getText().toString();
			fOut.write(txt.getBytes());
			fOut.close();
			Toast.makeText(getBaseContext(), "file saved", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void read(){
		try {
			FileInputStream fin = openFileInput(FILE_NAME);
			int c;
			String temp = "";
			while ((c = fin.read()) != -1) {
				temp = temp + Character.toString((char) c);
			}
			txtReading.setText(temp);
			Toast.makeText(getBaseContext(), "file read", Toast.LENGTH_SHORT).show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
}
