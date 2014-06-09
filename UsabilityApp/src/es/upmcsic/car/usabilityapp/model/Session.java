package es.upmcsic.car.usabilityapp.model;

import java.util.Date;
import java.util.List;
import java.sql.Time;

/**
 * Created by 66785338 on 27/05/14.
 */
public class Session {

    private String userId;
    private String caregiverId;
    private Time timeOfDay;
    private Date date;//Comentario creo que este campo sobre
    private double version;
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
    
    List<Target> targetList;
    int clickFailed;
    int clickSucceeded;

    public Session(){
        setUserId("");
        setCaregiverId("");
        setTimeOfDay(new Time(0,0,0));
        setDate(new Date());
        setVersion(0.0);
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
    }

    public Session(String userId, String caregiverId, Time timeOfDay, Date date, double version,
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
    }

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

    public double getVersion() {
        return version;
    }

    public Session setVersion(double version) {
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
    
    public static void main(String[] args) {
        
   //Comentario ¿qué codigo te gusta mas?
        
        //La forma de crear un objeto con tu clase original
        Session session = new Session("Usuario", "Cuidador", new Time(99999999),
                new Date(),  21.3, 1024, 768, 40, 0, 40, 40, 33, new Time(999999), new Time(99999), 100);
        
        //La forma de crear un objeto con mis modificaciones;  Mas código, pero mas claro
        Session session2 = new Session();
        session2.setUserId("Usuario").setCaregiverId("Cuidador").setTimeOfDay(new Time(99999999))
               .setDate(new Date()).setVersion(21.3).setScreenWidth(1024).setScreenHeight(768)
               .setNumberTargets(40).setId(0).setTargetSize(40)
               .setTargetDistance(40).setTaskId(33).setInitTask(new Time(999999))                
               .setFinTask(new Time(999999)).setVideoTime(100);
    }
}
