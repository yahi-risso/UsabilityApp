package es.upmcsic.car.usabilityapp.device;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 66785338 on 27/05/14.
 */
public class TargetIMU {

    private Orientation orientation;

    public TargetIMU(){
        setOrientation(new Orientation());
    }

    public TargetIMU(Orientation orientation){
        this.setOrientation(orientation);
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public class Orientation{

        private List<Integer> alfa;//Comentario para todas estas listas considerar cambiar por array por motivo de eficiencia
        private List<Integer> beta;
        private List<Integer> gamma;
        private List<Integer> wx;
        private List<Integer> wy;
        private List<Integer> wz;

        public Orientation(){
            setAlfa(new ArrayList<Integer>());
            setBeta(new ArrayList<Integer>());
            setGamma(new ArrayList<Integer>());
            setWx(new ArrayList<Integer>());
            setWy(new ArrayList<Integer>());
            setWz(new ArrayList<Integer>());
        }

        public Orientation(List<Integer> alfa, List<Integer> beta, List<Integer> gamma,
                           List<Integer> wx, List<Integer> wy, List<Integer> wz){
            this.setAlfa(alfa);
            this.setBeta(beta);
            this.setGamma(gamma);
            this.setWx(wx);
            this.setWy(wy);
            this.setWz(wz);
        }

        public List<Integer> getAlfa() {
            return alfa;
        }

        public void setAlfa(List<Integer> alfa) {
            this.alfa = alfa;
        }

        public List<Integer> getBeta() {
            return beta;
        }

        public void setBeta(List<Integer> beta) {
            this.beta = beta;
        }

        public List<Integer> getGamma() {
            return gamma;
        }

        public void setGamma(List<Integer> gamma) {
            this.gamma = gamma;
        }

        public List<Integer> getWx() {
            return wx;
        }

        public void setWx(List<Integer> wx) {
            this.wx = wx;
        }

        public List<Integer> getWy() {
            return wy;
        }

        public void setWy(List<Integer> wy) {
            this.wy = wy;
        }

        public List<Integer> getWz() {
            return wz;
        }

        public void setWz(List<Integer> wz) {
            this.wz = wz;
        }
    }
}