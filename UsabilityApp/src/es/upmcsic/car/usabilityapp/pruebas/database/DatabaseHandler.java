package es.upmcsic.car.usabilityapp.pruebas.database;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import es.upmcsic.car.usabilityapp.model.Session;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
 
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UsabilityDB";
    private static final String TABLE_SESSIONS = "sessions";
 
    static final String KEY_ID = "id_session";
    static final String USER_ID = "user_id";
    static final String CAREGIVER = "caregiver_id";
    static final String TIME_OF_DAY	= "time_of_dat";
    static final String DATE = "date";
    static final String VERSION = "version";
    static final String SCREEN_WIDTH = "screen_width";
    static final String SCREEN_HEIGHT = "screen_height";
    static final String NUMBER_TARGETS = "number_targets";
    static final String ID = "id";
    static final String TARGET_SIZE = "target_size";
    static final String TARGET_DISTANCE = "target_distance";
    static final String TASK_ID = "task_id";
    static final String INIT_TASK = "init_task";
    static final String FIN_TASK = "fin_task";
    static final String VIDEOTIME = "videotime";
    static final String CLICK_FAILED = "click_failed";
    static final String CLICK_SUCCEEDED = "click_succeeded";
    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SESSIONS_TABLE = "CREATE TABLE " + TABLE_SESSIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," 
        		+ USER_ID + " TEXT,"
        		+ CAREGIVER + " TEXT,"
        		+ TIME_OF_DAY + " TEXT,"
        		+ DATE + " TEXT,"
        		+ VERSION + " TEXT,"
        		+ SCREEN_WIDTH + " TEXT,"
        		+ SCREEN_HEIGHT + " TEXT,"
        		+ NUMBER_TARGETS + " TEXT,"
        		+ ID + " TEXT,"
        		+ TARGET_SIZE + " TEXT,"
        		+ TARGET_DISTANCE + " TEXT,"
        		+ TASK_ID + " TEXT,"
        		+ INIT_TASK + " TEXT,"
        		+ FIN_TASK + " TEXT,"
                + VIDEOTIME + " TEXT," 
                + CLICK_FAILED + " TEXT,"
                + CLICK_SUCCEEDED + " TEXT" + ")";
        db.execSQL(CREATE_SESSIONS_TABLE);
        Log.i("-->> CREADA TABLA ", TABLE_SESSIONS);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SESSIONS);
        // Create tables again
        onCreate(db);
        Log.i("-->> ACTUALIZADA TABLA ", TABLE_SESSIONS);
    }

    public void addSession(Session session) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(USER_ID, session.getUserId()); 
        values.put(CAREGIVER, session.getCaregiverId()); 
        values.put(TIME_OF_DAY, session.getTimeOfDay().toString()); 
        values.put(DATE, session.getDate().toString()); 
        values.put(VERSION, session.getVersion()); 
        values.put(SCREEN_WIDTH, session.getScreenWidth());
        values.put(SCREEN_HEIGHT, session.getScreenHeight()); 
        values.put(NUMBER_TARGETS, session.getNumberTargets()); 
        values.put(ID, session.getId()); 
        values.put(TARGET_SIZE, session.getTargetSize()); 
        values.put(TARGET_DISTANCE, session.getTargetDistance()); 
        values.put(TASK_ID, session.getTaskId());  
        values.put(INIT_TASK, session.getInitTask().toString()); 
        values.put(FIN_TASK, session.getFinTask().toString()); 
        values.put(VIDEOTIME, session.getVideoTime());
        values.put(CLICK_FAILED, session.getClickFailed());
        values.put(CLICK_SUCCEEDED, session.getClickSucceeded());
        // Inserting Row
        db.insert(TABLE_SESSIONS, null, values);
        db.close(); // Closing database connection
    }
 
    public Session getSession(int idSession) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_SESSIONS, new String[] { KEY_ID,
        		USER_ID, CAREGIVER, TIME_OF_DAY, DATE, VERSION, SCREEN_WIDTH, SCREEN_HEIGHT, 
        		NUMBER_TARGETS, ID, TARGET_SIZE, TARGET_DISTANCE, TASK_ID, INIT_TASK, 
        		FIN_TASK, VIDEOTIME, CLICK_FAILED, CLICK_SUCCEEDED }, KEY_ID + "=?",
                new String[] { String.valueOf(idSession) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        String userId = cursor.getString(1);
        String caregiverId = cursor.getString(2);
        Time timeOfDay = Time.valueOf(cursor.getString(3));
        Date date = new Date(cursor.getString(4));
        String version = cursor.getString(5);
        int screenWidth = Integer.parseInt(cursor.getString(6));
        int screenHeight = Integer.parseInt(cursor.getString(7));
        int numberTargets = Integer.parseInt(cursor.getString(8));
        double id = Double.parseDouble(cursor.getString(9));
        int targetSize = Integer.parseInt(cursor.getString(10));
        int targetDistance = Integer.parseInt(cursor.getString(11));
        int taskId = Integer.parseInt(cursor.getString(12));
        Time initTask = Time.valueOf(cursor.getString(13));
        Time finTask = Time.valueOf(cursor.getString(14));
        int videoTime = Integer.parseInt(cursor.getString(15));
        int clickFailed = Integer.parseInt(cursor.getString(16));
        int clickSucceeded = Integer.parseInt(cursor.getString(17));
        
        Session session = new Session();
        session.setUserId(userId).setCaregiverId(caregiverId).setTimeOfDay(timeOfDay).setDate(date)
        .setVersion(version).setScreenWidth(screenWidth).setScreenHeight(screenHeight)
        .setNumberTargets(numberTargets).setId(id).setTargetSize(targetSize).
        setTargetDistance(targetDistance).setTaskId(taskId).setInitTask(initTask).
        setFinTask(finTask).setVideoTime(videoTime).setClickFailed(clickFailed).setClickSucceeded(clickSucceeded);
        return session;
    }
     
    // Getting All Sessions
    public ArrayList<Session> getAllSessions() {
        ArrayList<Session> sessionList = new ArrayList<Session>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SESSIONS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	 String userId = cursor.getString(1);
                 String caregiverId = cursor.getString(2);
                 Time timeOfDay = Time.valueOf(cursor.getString(3));
                 Date date = new Date(cursor.getString(4));
                 String version = cursor.getString(5);
                 int screenWidth = Integer.parseInt(cursor.getString(6));
                 int screenHeight = Integer.parseInt(cursor.getString(7));
                 int numberTargets = Integer.parseInt(cursor.getString(8));
                 double id = Double.parseDouble(cursor.getString(9));
                 int targetSize = Integer.parseInt(cursor.getString(10));
                 int targetDistance = Integer.parseInt(cursor.getString(11));
                 int taskId = Integer.parseInt(cursor.getString(12));
                 Time initTask = Time.valueOf(cursor.getString(13));
                 Time finTask = Time.valueOf(cursor.getString(14));
                 int videoTime = Integer.parseInt(cursor.getString(15));

                 int clickFailed = Integer.parseInt(cursor.getString(16));
                 int clickSucceeded = Integer.parseInt(cursor.getString(17));
                 
                 Session session = new Session();
                 session.setUserId(userId).setCaregiverId(caregiverId).setTimeOfDay(timeOfDay)
                 .setDate(date).setVersion(version).setScreenWidth(screenWidth).setScreenHeight(screenHeight)
                 .setNumberTargets(numberTargets).setId(id).setTargetSize(targetSize).setTargetDistance(targetDistance)
                 .setTaskId(taskId).setInitTask(initTask).setFinTask(finTask).setVideoTime(videoTime)
                 .setClickFailed(clickFailed).setClickSucceeded(clickSucceeded);
                 
                // Adding session to list
                sessionList.add(session);
            } while (cursor.moveToNext());
        }
        // return session list
        return sessionList;
    }
 
    public ArrayList<Session> getSessionsByField(String fieldName, String fieldValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Session> sessionList = new ArrayList<Session>();
        Cursor cursor = db.query(TABLE_SESSIONS, new String[] { KEY_ID,
        		USER_ID, CAREGIVER, TIME_OF_DAY, DATE, VERSION, SCREEN_WIDTH, SCREEN_HEIGHT, 
        		NUMBER_TARGETS, ID, TARGET_SIZE, TARGET_DISTANCE, TASK_ID, INIT_TASK, 
        		FIN_TASK, VIDEOTIME, CLICK_FAILED, CLICK_SUCCEEDED }, fieldName + "=?",
                new String[] { String.valueOf(fieldValue) }, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
            	 String userId = cursor.getString(1);
                 String caregiverId = cursor.getString(2);
                 Time timeOfDay = Time.valueOf(cursor.getString(3));
                 //STRING TO DATE
                 //Date date = new Date(cursor.getString(4));
                 Date date = new Date();
                 String version = cursor.getString(5);
                 int screenWidth = Integer.parseInt(cursor.getString(6));
                 int screenHeight = Integer.parseInt(cursor.getString(7));
                 int numberTargets = Integer.parseInt(cursor.getString(8));
                 double id = Double.parseDouble(cursor.getString(9));
                 int targetSize = Integer.parseInt(cursor.getString(10));
                 int targetDistance = Integer.parseInt(cursor.getString(11));
                 int taskId = Integer.parseInt(cursor.getString(12));
                 Time initTask = Time.valueOf(cursor.getString(13));
                 Time finTask = Time.valueOf(cursor.getString(14));
                 int videoTime = Integer.parseInt(cursor.getString(15));

                 int clickFailed = Integer.parseInt(cursor.getString(16));
                 int clickSucceeded = Integer.parseInt(cursor.getString(17));
                 
                 Session session = new Session();
                 session.setUserId(userId).setCaregiverId(caregiverId).setTimeOfDay(timeOfDay)
                 .setDate(date).setVersion(version).setScreenWidth(screenWidth).setScreenHeight(screenHeight)
                 .setNumberTargets(numberTargets).setId(id).setTargetSize(targetSize).setTargetDistance(targetDistance)
                 .setTaskId(taskId).setInitTask(initTask).setFinTask(finTask).setVideoTime(videoTime)
                 .setClickFailed(clickFailed).setClickSucceeded(clickSucceeded);
                 
                // Adding session to list
                sessionList.add(session);
            } while (cursor.moveToNext());
        }
        // return session list
        return sessionList;
    }
    
    // Updating session
    public int updateSession(Session session, int sessionId) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(USER_ID, session.getUserId()); 
        values.put(CAREGIVER, session.getCaregiverId()); 
        values.put(TIME_OF_DAY, session.getTimeOfDay().toString()); 
        values.put(DATE, session.getDate().toString()); 
        values.put(VERSION, session.getVersion()); 
        values.put(SCREEN_WIDTH, session.getScreenWidth());
        values.put(SCREEN_HEIGHT, session.getScreenHeight()); 
        values.put(NUMBER_TARGETS, session.getNumberTargets()); 
        values.put(ID, session.getId()); 
        values.put(TARGET_SIZE, session.getTargetSize()); 
        values.put(TARGET_DISTANCE, session.getTargetDistance()); 
        values.put(TASK_ID, session.getTaskId());  
        values.put(INIT_TASK, session.getInitTask().toString()); 
        values.put(FIN_TASK, session.getFinTask().toString()); 
        values.put(VIDEOTIME, session.getVideoTime());
        values.put(CLICK_FAILED, session.getClickFailed());
        values.put(CLICK_SUCCEEDED, session.getClickSucceeded());
        
        // updating row
        return db.update(TABLE_SESSIONS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(sessionId) });
    }
 
    // Deleting session
    public void deleteSession(int sessionId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SESSIONS, KEY_ID + " = ?",
                new String[] { String.valueOf(sessionId) });
        db.close();
    }
 
 
    // Getting session Count
    public int getSessionCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SESSIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
 
}