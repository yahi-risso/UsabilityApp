package es.upmcsic.car.usabilityapp.pruebas.database;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import es.upmcsic.car.usabilityapp.model.Session;
import es.upmcsic.car.usabilityapp.pruebas.bluetooth.MyHandler;

public class SaveDBThread implements Runnable {

    private MyHandler resultHandler;
    Session session;
    Activity activity;
    DatabaseHandler db;
    
    public SaveDBThread (MyHandler resultHandler, Session session, Activity activity) {
        this.resultHandler = resultHandler;
        this.session = session;
        this.activity = activity;
    }

    public void run() {
    	db = new DatabaseHandler(activity);
    	guardarEnBBDD(session);
    	String info = "GUARDADA SESSION";
        publishProgress(info);
    }

    private void guardarEnBBDD(Session session2) {
		String idUser = session2.getUserId();
        Log.i("Insert: ", "Inserting .."); 
        db.addSession(session2);
	}
    
    private void leerDeBBDD(){
    	 // Reading all contacts
        Log.d("Reading: ", "Reading session.. "+session.getUserId()); 
        List<Session> sessions = db.getSessionsByField(db.USER_ID, session.getUserId());       
         
        for (Session ses : sessions) {
        	Log.d("Name: ", ses.toString());
        }
    }

	private void publishProgress(String infoDB) {
        Bundle msgBundle = new Bundle();
        msgBundle.putString("infoDB", infoDB);
        Message msg = new Message();
        msg.setData(msgBundle);
        resultHandler.sendMessage(msg);
    }
}