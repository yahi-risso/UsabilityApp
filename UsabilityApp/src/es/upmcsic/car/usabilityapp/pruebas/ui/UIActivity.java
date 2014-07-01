package es.upmcsic.car.usabilityapp.pruebas.ui;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import es.upmcsic.car.usabilityapp.R;
import es.upmcsic.car.usabilityapp.model.Session;
import es.upmcsic.car.usabilityapp.pruebas.bluetooth.MyHandler;
import es.upmcsic.car.usabilityapp.pruebas.database.DBActivity;
import es.upmcsic.car.usabilityapp.pruebas.graphic.GraphicActivity;
import es.upmcsic.car.usabilityapp.xmlvalidator.ObjectCreatorThread;

public class UIActivity extends Activity implements OnNavigationListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_interface);

		// Set up the action bar to show a dropdown list.
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		
		ArrayList<String> itemList = new ArrayList<String>();
		itemList.add("Enero");
		itemList.add("Febrero");
		itemList.add("Marzo");
		itemList.add("Abril");
		itemList.add("Mayo");
		itemList.add("Junio");
		itemList.add("Julio");
		itemList.add("Agosto");
		itemList.add("Septiembre");
		itemList.add("Octubre");
		itemList.add("Noviembre");
		itemList.add("Diciembre");
		ArrayAdapter<String> aAdpt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, itemList);
		actionBar.setListNavigationCallbacks(aAdpt, this);

	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}
}
