package es.upmcsic.car.usabilityapp.xmlvalidator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


import mf.javax.xml.stream.XMLStreamException;
import mf.javax.xml.transform.Source;
import mf.javax.xml.transform.stream.StreamSource;
import mf.javax.xml.validation.Schema;
import mf.javax.xml.validation.SchemaFactory;
import mf.javax.xml.validation.Validator;
import mf.org.apache.xerces.jaxp.validation.XMLSchemaFactory;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.util.Log;
import es.upmcsic.car.usabilityapp.R;
import es.upmcsic.car.usabilityapp.model.Session;
import es.upmcsic.car.usabilityapp.pruebas.bluetooth.MyHandler;

public class ObjectCreatorThread implements Runnable{

	MyHandler resultHandler;
	Activity activity;
	
	public ObjectCreatorThread(MyHandler resultHandler, Activity activity){
		this.resultHandler = resultHandler;
		this.activity = activity;
	}
	
	@Override
	public void run() {
		String fileName = "Session.xml";
		String schemaName = "session_xsd.xsd";
		boolean validSession = validateWithExtXSDUsingSAX(fileName, schemaName);
		Log.i("-->> ","VALID SESSION = "+validSession);
		publishValidationResult(validSession+"");
		
		fileName = "Targets.xml";
		schemaName = "targets_xsd.xsd";
		boolean validTarget = validateWithExtXSDUsingSAX(fileName, schemaName);
		Log.i("-->> ","VALID TARGETS = "+validTarget);
		publishValidationResult(validTarget+"\n");
		
		if(validSession==true && validTarget==true){
			SessionParser parser = new SessionParser(activity);
			Session mySession = parser.parseSession();
			//Log.i("SESSION: ", mySession.toString());
			//publishValidationResult(mySession.toString());
			publishSession(mySession);
		}
	}

	public boolean validateWithExtXSDUsingSAX(String xml, String xsd){  //leer de sd. Ahora esta leyendo de raw
		Log.i("-->>",xml+"-"+xsd);
		boolean result = true;
        try {
            SchemaFactory  factory = new XMLSchemaFactory();
            Source xmlSource = null;
            Source schemaFile = null;
            if(xml.equalsIgnoreCase("Session.xml")){
            	Log.i("-->> ","Validating session.xml");
	             xmlSource = new StreamSource(activity.getResources().openRawResource(R.raw.session));
	             schemaFile = new StreamSource(activity.getResources().openRawResource(R.raw.session_xsd));
            }
            if(xml.equalsIgnoreCase("Targets.xml")){
            	Log.i("-->> ","Validating targets.xml");
            	xmlSource = new StreamSource(activity.getResources().openRawResource(R.raw.targets));
	             schemaFile = new StreamSource(activity.getResources().openRawResource(R.raw.targets_xsd));
            }
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlSource);
        } catch (SAXException e1) {
        	Log.i("EXCPTION e: ",e1.getMessage());
        	e1.printStackTrace();
            result =  false;
        } catch (IOException e2) {
        	Log.i("EXCPTION: ",e2.getMessage());
        	e2.printStackTrace();
        	result = false;
        } catch (Exception e3) {
            e3.printStackTrace();
            Log.i("EXCPTION: ",e3.getMessage());
            result =  false;
        } catch (Error e4) {
            e4.printStackTrace();
            Log.i("EXCPTION: ",e4.getMessage());
            result = false;
        }
    	return result;
	}
	
	private void publishValidationResult(String validation) {
        Bundle msgBundle = new Bundle();
        msgBundle.putString("validation", validation);
        Message msg = new Message();
        msg.setData(msgBundle);
        resultHandler.sendMessage(msg);
    }

	private void publishSession(Session mySession) {
		Bundle msgBundle = new Bundle();
        msgBundle.putSerializable("session", mySession);
        Message msg = new Message();
        msg.setData(msgBundle);
        resultHandler.sendMessage(msg);
	}
}
