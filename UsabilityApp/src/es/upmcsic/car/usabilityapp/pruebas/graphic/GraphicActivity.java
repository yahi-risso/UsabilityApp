package es.upmcsic.car.usabilityapp.pruebas.graphic;

import java.util.ArrayList;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import es.upmcsic.car.usabilityapp.R;
import es.upmcsic.car.usabilityapp.model.MyPoint;
import es.upmcsic.car.usabilityapp.model.Session;
import es.upmcsic.car.usabilityapp.model.Target;

public class GraphicActivity extends Activity {
	
	Session session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graphics);
		
		Intent myIntent = getIntent(); // gets the previously created intent
		this.session = (Session)myIntent.getSerializableExtra("session");
		final TextView txt = (TextView) findViewById(R.id.textInfoIntent);
		txt.setMovementMethod(new ScrollingMovementMethod());
		
		Spinner spinner = (Spinner) findViewById(R.id.spinnerTargets);
		ArrayList<String> list = new ArrayList<String>();
		for(int i=1;i<session.getNumberTargets();i++){
			list.add("TARGET "+(i));
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(parent.getContext(), "SELECTED TARGET: " + (position+1),
						Toast.LENGTH_SHORT).show();
				//Target target = session.getTargetList().get(position);
				dibujar(session.getTargetList().get(position));
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	public void dibujar(Target t){
	
		// init example series data
		Log.i("-->>TARGET SELECTED DISTANCE SIZE: ",t.getDistance().length+"");
		GraphViewData[] pointArray = new GraphViewData[t.getDistance().length];
		for(int i = 0; i<t.getDistance().length; i++){
			float distance = t.getDistance()[i];
			pointArray[i] = new GraphViewData(i, distance);
		}
		GraphViewSeries exampleSeries = new GraphViewSeries(pointArray);
		GraphView graphView = new LineGraphView(
		    this.getApplicationContext() // context
		    , "Distance Graph" // heading
		);
		graphView.addSeries(exampleSeries); // data
		 
		graphView.setViewPort(0,10);
		graphView.setScrollable(true);
		graphView.setScalable(true);
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.layoutGraphicDistance);
		layout.addView(graphView);
		
		Log.i("-->>TARGET SELECTED VELOCITY SIZE: ",t.getVelocity().length+"");
		pointArray = new GraphViewData[t.getVelocity().length];
		for(int i = 0; i<t.getVelocity().length; i++){
			float velocity = t.getVelocity()[i];
			pointArray[i] = new GraphViewData(i, velocity);
		}
		exampleSeries = new GraphViewSeries(pointArray);
		graphView = new LineGraphView(
		    this.getApplicationContext() // context
		    , "Velocity Graph" // heading
		);
		graphView.addSeries(exampleSeries); // data
		 
		graphView.setViewPort(0,1000);
		graphView.setScrollable(true);
		graphView.setScalable(true);
		
		layout = (LinearLayout) findViewById(R.id.layoutGraphicVelocity);
		layout.addView(graphView);
	}
	
	public Activity getActivity(){
		return this;
	}
}
