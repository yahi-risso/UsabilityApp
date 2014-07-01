package es.upmcsic.car.usabilityapp.model;

import java.io.Serializable;
import java.util.ArrayList;

import android.util.Log;

/**
 * Created by 66785338 on 27/05/14.
 */
public class Target implements Serializable {

	int DISTANCE_SIZE = 1;
	int VELOCITY_SIZE = 1;

	MyPoint location;
	int sizeX;
	int sizeY;
	ArrayList<MyPoint> pointer;
	Click click;
	Float[] distance;// Comentario considerar usar array y float por motivos de
						// eficiencia
	Float[] velocity;

	public Target() {
		// location = new MyPoint(0,0);
		sizeX = 0;
		sizeY = 0;
		pointer = new ArrayList<MyPoint>();
		click = new Click();
		distance = new Float[DISTANCE_SIZE];
		velocity = new Float[VELOCITY_SIZE];
	}

	public class Click implements Serializable {
		int T_SIZE = 1;
		int XY_SIZE = 1;
		int CLICK_OK_SIZE = 1;

		private MyPoint[] xy;
		private int[] t;
		private boolean[] clikOk;

		public Click() {
			setXy(new MyPoint[XY_SIZE]);
			setT(new int[T_SIZE]);
			setClikOk(new boolean[CLICK_OK_SIZE]);
		}

		public Click(MyPoint[] xy, int[] t, boolean[] clickOk) {
			this.setXy(xy);
			this.setT(t);
			this.setClikOk(clickOk);
		}

		public int[] getT() {
			return t;
		}

		public void setT(int[] t) {
			this.t = t;
		}

		public boolean[] getClikOk() {
			return clikOk;
		}

		public void setClikOk(boolean[] clikOk) {
			this.clikOk = clikOk;
		}

		public MyPoint[] getXy() {
			return xy;
		}

		public void setXy(MyPoint[] xy) {
			this.xy = xy;
		}
	}

	public MyPoint getLocation() {
		return location;
	}

	public void setLocation(MyPoint location) {
		this.location = location;
	}

	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public ArrayList<MyPoint> getPointer() {
		return pointer;
	}

	public void setPointer(ArrayList<MyPoint> pointer) {
		this.pointer = pointer;
	}

	public Click getClick() {
		return click;
	}

	public void setClick(Click click) {
		this.click = click;
	}

	public Float[] getDistance() {
		return distance;
	}

	public void setDistance(Float[] distance) {
		this.distance = distance;
	}

	public Float[] getVelocity() {
		return velocity;
	}

	public void setVelocity(Float[] velocity) {
		this.velocity = velocity;
	}

	public String toString() {
		String result = "*";
		try {
			result = "\tTARGET:\n";
			result += "\tSixe X: " + getSizeX() + "\n";
			result += "\tSixe Y: " + getSizeY() + "\n";
			if (distance.length != 0 && null != distance) {
				result += "\tDistance:\n\t\t";
				for (float p : distance) {
					result += p + "|";
				}
				result += "\n";
			}
			if (velocity.length != 0 && null != velocity) {
				result += "\tVelocity:\n\t\t";
				for (float p : velocity) {
					result += p + "|";
				}
				result += "\n";
			}
			result += "\tLocation X: " + location.x + "\n";
			result += "\tLocation Y: " + location.y + "\n";
			result += "\tPointer:\n\t\t";
			if (!pointer.isEmpty() && null != pointer) {
				for (MyPoint p : pointer) {
					result += p.toString() + "|";
				}
				result += "\n";
			}
			result += "\tCLICK:\n";
			if (click.getXy().length != 0 && null != click.getXy()) {
				result += "\t\tXY Point:\n\t\t";
				for (MyPoint p : click.getXy()) {
					result += p.toString() + "|";
				}
				result += "\n";
			}
			if (click.getT().length != 0 && null != click.getT()) {
				result += "\t\tT...integer:\n\t\t";
				for (int myT : click.getT()) {
					result += myT + "|";
				}
				result += "\n";
			}
			if (click.getClikOk().length != 0 && null != click.getClikOk()) {
				result += "\t\tCLickOK list:\n\t\t";
				for (boolean b : click.getClikOk()) {
					result += b + "|";
				}
				result += "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.i("EXCEPTION ", e.getMessage());
		}
		return result;
	}
}
