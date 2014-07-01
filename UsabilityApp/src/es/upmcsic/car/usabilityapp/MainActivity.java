package es.upmcsic.car.usabilityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import es.upmcsic.car.usabilityapp.pruebas.bluetooth.BluetoothActivity;
import es.upmcsic.car.usabilityapp.pruebas.io.ReadWriteActivity;
import es.upmcsic.car.usabilityapp.pruebas.ui.UIActivity;
import es.upmcsic.car.usabilityapp.xmlvalidator.ValidationActivity;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            
            
            Button buttonBluetooth = (Button) rootView.findViewById(R.id.buttonBluetooth);
            buttonBluetooth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), BluetoothActivity.class);
                    startActivity(intent);
                }
            });
            
            Button buttonValidateXML = (Button) rootView.findViewById(R.id.buttonValidate);
            buttonValidateXML.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ValidationActivity.class);
                    startActivity(intent);
                }
            });
            
            Button buttonIO = (Button) rootView.findViewById(R.id.buttonIO);
            buttonIO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ReadWriteActivity.class);
                    startActivity(intent);
                }
            });
            
            Button buttonInterface = (Button) rootView.findViewById(R.id.buttonInterface);
            buttonInterface.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), UIActivity.class);
                    startActivity(intent);
                }
            });
            return rootView;
        }
    }

}
