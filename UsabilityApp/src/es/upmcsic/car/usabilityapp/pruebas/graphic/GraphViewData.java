package es.upmcsic.car.usabilityapp.pruebas.graphic;

import android.graphics.Point;

import com.jjoe64.graphview.GraphViewDataInterface;

public class GraphViewData implements GraphViewDataInterface {

	double x;
	double y;
	
	public GraphViewData(double x, double y) {
		this.x=x;
		this.y=y;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}

}
