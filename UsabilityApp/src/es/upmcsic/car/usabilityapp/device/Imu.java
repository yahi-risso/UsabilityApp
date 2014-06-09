package es.upmcsic.car.usabilityapp.device;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by 66785338 on 27/05/14.
 */
public class Imu {

    private Configuration configuration;
    private List<TargetIMU> targetList;

    public Imu(){
        setConfiguration(new Configuration());
        setTargetList(new ArrayList<TargetIMU>());
    }

    public Imu(Configuration configuration, List<TargetIMU> targetList){
        this.setConfiguration(configuration);
        this.setTargetList(targetList);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public List<TargetIMU> getTargetList() {
        return targetList;
    }

    public void setTargetList(List<TargetIMU> targetList) {
        this.targetList = targetList;
    }
}
