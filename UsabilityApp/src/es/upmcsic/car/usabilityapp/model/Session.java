package es.upmcsic.car.usabilityapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.sql.Time;

/**
 * Created by 66785338 on 27/05/14.
 */
public class Session implements Serializable{

    private String userId;
    private String caregiverId;
    private Time timeOfDay;
    private Date date;//Comentario creo que este campo sobre
    private String version;
    private int screenWidth;
    private int screenHeight;
    private int numberTargets;
    private double id;
    private int targetSize;
    private int targetDistance;
    private int taskId;
    private Time initTask;
    private Time finTask;
    private int videoTime;
    
    private ArrayList<Target> targetList;
	private int clickFailed;
    private int clickSucceeded;

    public Session(){
        setUserId("");
        setCaregiverId("");
        setTimeOfDay(new Time(0,0,0));
        setDate(new Date());
        setVersion("0.0");
        setScreenWidth(0);
        setScreenHeight(0);
        setNumberTargets(0);
        setId(0);
        setTargetSize(0);
        setTargetDistance(0);
        setTaskId(0);
        setInitTask(new Time(0,0,0));
        setFinTask(new Time(0,0,0));
        setVideoTime(0);
        setTargetList(new ArrayList<Target>());
        setClickFailed(0);
        setClickSucceeded(0);
    }

    /*public Session(String userId, String caregiverId, Time timeOfDay, Date date, String version,
                   int screenWidth, int screenHeight, int numberTargets, double id, int targetSize,
                   int targetDistance, int taskId, Time initTask, Time finTask, int videoTime){
        this.setUserId(userId);
        this.setCaregiverId(caregiverId);
        this.setTimeOfDay(timeOfDay);
        this.setDate(date);
        this.setVersion(version);
        this.setScreenWidth(screenWidth);
        this.setScreenHeight(screenHeight);
        this.setNumberTargets(numberTargets);
        this.setId(id);
        this.setTargetSize(targetSize);
        this.setTargetDistance(targetDistance);
        this.setTaskId(taskId);
        this.setInitTask(initTask);
        this.setFinTask(finTask);
        this.setVideoTime(videoTime);
    }*/

    public String getUserId() {
        return userId;
    }

    public Session setUserId(String userId) {
        this.userId = userId;
        return this;        
    }

    public String getCaregiverId() {
        return caregiverId;
    }

    public Session setCaregiverId(String caregiverId) {
        this.caregiverId = caregiverId;
        return this;        
    }

    public Time getTimeOfDay() {
        return timeOfDay;
    }

    public Session setTimeOfDay(Time timeOfDay) {
        this.timeOfDay = timeOfDay;
        return this;        
    }

    public Date getDate() {
        return date;
    }

    public Session setDate(Date date) {
        this.date = date;
        return this;        
    }

    public String getVersion() {
        return version;
    }

    public Session setVersion(String version) {
        this.version = version;
        return this;        
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public Session setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
        return this;        
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public Session setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
        return this;        
    }

    public int getNumberTargets() {
        return numberTargets;
    }

    public Session setNumberTargets(int numberTargets) {
        this.numberTargets = numberTargets;
        return this;        
    }

    public double getId() {
        return id;
    }

    public Session setId(double id) {
        this.id = id;
        return this;        
    }

    public int getTargetSize() {
        return targetSize;
    }

    public Session setTargetSize(int targetSize) {
        this.targetSize = targetSize;
        return this;        
    }

    public int getTargetDistance() {
        return targetDistance;
    }

    public Session setTargetDistance(int targetDistance) {
        this.targetDistance = targetDistance;
        return this;        
    }

    public int getTaskId() {
        return taskId;
    }

    public Session setTaskId(int taskId) {
        this.taskId = taskId;
        return this;        
    }

    public Time getInitTask() {
        return initTask;
    }

    public Session setInitTask(Time initTask) {
        this.initTask = initTask;
        return this;        
    }

    public Time getFinTask() {
        return finTask;
    }

    public Session setFinTask(Time finTask) {
        this.finTask = finTask;
        return this;        
    }

    public int getVideoTime() {
        return videoTime;
    
    }

    public Session setVideoTime(int videoTime) {
        this.videoTime = videoTime;
        return this;        
    }
    
    public ArrayList<Target> getTargetList() {
 		return targetList;
 	}

 	public void setTargetList(ArrayList<Target> targetList) {
 		this.targetList = targetList;
 	}

 	public int getClickFailed() {
 		return clickFailed;
 	}

 	public Session setClickFailed(int clickFailed) {
 		this.clickFailed = clickFailed;
 		return this;
 	}

 	public int getClickSucceeded() {
 		return clickSucceeded;
 	}

 	public Session setClickSucceeded(int clickSucceeded) {
 		this.clickSucceeded = clickSucceeded;
 		return this;
 	}
    
   /* public static void main(String[] args) {
        
        //La forma de crear un objeto con mis modificaciones;  Mas codigo, pero mas claro
        Session session2 = new Session();
        session2.setUserId("Usuario").setCaregiverId("Cuidador").setTimeOfDay(new Time(99999999))
               .setDate(new Date()).setVersion("21.3").setScreenWidth(1024).setScreenHeight(768)
               .setNumberTargets(40).setId(0).setTargetSize(40)
               .setTargetDistance(40).setTaskId(33).setInitTask(new Time(999999))                
               .setFinTask(new Time(999999)).setVideoTime(100);
    }*/
    
    public String toString(){
    	String result = "userId "+getUserId()+"\n";
        result += "caregiverId "+getCaregiverId()+"\n";
        result += "timeOfDay "+getTimeOfDay()+"\n";
        result += "date "+getDate()+"\n";
        result += "version "+getVersion()+"\n";
        result += "screenWidth "+getScreenWidth()+"\n";
        result += "screenHeight "+getScreenHeight()+"\n";
        result += "numberTargets "+getNumberTargets()+"\n";
        result += "id "+getId()+"\n";
        result += "targetSize "+getTargetSize()+"\n";
        result += "targetDistance "+getTargetDistance()+"\n";
        result += "taskId "+getTaskId()+"\n";
        result += "initTask "+getInitTask()+"\n";
        result += "finTask "+getFinTask()+"\n";
        result += "videoTime "+getVideoTime()+"\n";
        if(!getTargetList().isEmpty() && null!=getTargetList()){
	        for(Target g:targetList){
	        	result += g.toString()+"\n";
	        }
        }
        result += "clickFailed "+getClickFailed()+"\n";
        result += "clickSucceeded "+getClickSucceeded()+"\n";
        return result;
    }
}
