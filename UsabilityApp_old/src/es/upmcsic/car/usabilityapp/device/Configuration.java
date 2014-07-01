package es.upmcsic.car.usabilityapp.device;

/**
 * Created by 66785338 on 27/05/14.
 */
public class Configuration {

    private Calibration calibration;
    private int frequency;

    public Configuration(){
        setCalibration(new Calibration());
        setFrequency(0);
    }

    public Configuration(Calibration calibration, int frequency){
        this.setCalibration(calibration);
        this.setFrequency(frequency);
    }

    public Calibration getCalibration() {
        return calibration;
    }

    public void setCalibration(Calibration calibration) {
        this.calibration = calibration;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    private class Calibration {

        private int alfa0;
        private int beta0;
        private int gamma0;
        private int romV;
        private int romH;
        private boolean lateralControl;

        public Calibration(){
            setAlfa0(0);
            setBeta0(0);
            setGamma0(0);
            setRomV(0);
            setRomH(0);
            setLateralControl(false);
        }

        public Calibration(int alfa0, int beta0, int gamma0, int romV, int romH, boolean lateralControl){
            this.setAlfa0(alfa0);
            this.setBeta0(beta0);
            this.setGamma0(gamma0);
            this.setRomV(romV);
            this.setRomH(romH);
            this.setLateralControl(lateralControl);
        }

        public int getAlfa0() {
            return alfa0;
        }

        public void setAlfa0(int alfa0) {
            this.alfa0 = alfa0;
        }

        public int getBeta0() {
            return beta0;
        }

        public void setBeta0(int beta0) {
            this.beta0 = beta0;
        }

        public int getGamma0() {
            return gamma0;
        }

        public void setGamma0(int gamma0) {
            this.gamma0 = gamma0;
        }

        public int getRomV() {
            return romV;
        }

        public void setRomV(int romV) {
            this.romV = romV;
        }

        public int getRomH() {
            return romH;
        }

        public void setRomH(int romH) {
            this.romH = romH;
        }

        public boolean isLateralControl() {
            return lateralControl;
        }

        public void setLateralControl(boolean lateralControl) {
            this.lateralControl = lateralControl;
        }
    }
}
