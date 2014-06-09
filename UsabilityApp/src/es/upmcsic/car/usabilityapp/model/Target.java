package es.upmcsic.car.usabilityapp.model;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;

/**
 * Created by 66785338 on 27/05/14.
 */
public class Target {

	Point location;
    int sizeX;
    int sizeY;
    List<Point> pointer;
    Click click;
    List<Double> distance;//Comentario considerar usar array  y float por motivos de eficiencia
    List<Double> velocity;

    public class Click{
        private List<Point> xy;
        private List<Integer> t;//Comentario También valorar cambiarlas a array en el futuro Por motivo de rendimiento
        //Aunque aquí lo veo menos problemático porque creo que vendrán menos puntos
        private boolean clikOk;

        public Click(){
            setXy(new ArrayList<Point>());
            setT(new ArrayList<Integer>());
            setClikOk(false);
        }

        public Click(List<Point> xy, List<Integer> t, boolean clickOk){
            this.setXy(xy);
            this.setT(t);
            this.setClikOk(clickOk);
        }

        public List<Integer> getT() {
            return t;
        }

        public void setT(List<Integer> t) {
            this.t = t;
        }

        public boolean isClikOk() {
            return clikOk;
        }

        public void setClikOk(boolean clikOk) {
            this.clikOk = clikOk;
        }

		private List<Point> getXy() {
			return xy;
		}

		private void setXy(List<Point> xy) {
			this.xy = xy;
		}
    }

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
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

	public List<Point> getPointer() {
		return pointer;
	}

	public void setPointer(List<Point> pointer) {
		this.pointer = pointer;
	}

	public Click getClick() {
		return click;
	}

	public void setClick(Click click) {
		this.click = click;
	}

	public List<Double> getDistance() {
		return distance;
	}

	public void setDistance(List<Double> distance) {
		this.distance = distance;
	}

	public List<Double> getVelocity() {
		return velocity;
	}

	public void setVelocity(List<Double> velocity) {
		this.velocity = velocity;
	}

}
