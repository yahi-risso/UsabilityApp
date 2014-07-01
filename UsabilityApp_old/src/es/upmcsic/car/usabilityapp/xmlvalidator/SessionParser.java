package es.upmcsic.car.usabilityapp.xmlvalidator;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.graphics.Point;
import android.util.Log;
import es.upmcsic.car.usabilityapp.R;
import es.upmcsic.car.usabilityapp.model.Session;
import es.upmcsic.car.usabilityapp.model.Target;

public class SessionParser {

	static List<Target> targetList;
	static Session session;
	static Target target;
	Activity activity;

	public SessionParser(Activity activity) {
		this.activity = activity;
	}

	public Session parseSession() {
		readSessionXML();
		readTargetsXML();
		return session;
	}

	private void readSessionXML() {
		XmlPullParserFactory factory;
		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			InputStream in = activity.getResources().openRawResource(R.raw.session);
			xpp.setInput(in, "UTF-8");
			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {  				//SWITCH
				if (eventType == XmlPullParser.START_DOCUMENT) {
					System.out.println("Start document");
				} else if (eventType == XmlPullParser.END_DOCUMENT) {
					System.out.println("End document");
				} else if (eventType == XmlPullParser.START_TAG) {
					System.out.println("Start tag " + xpp.getName());
					if ("Session".equalsIgnoreCase(xpp.getName())) {
						writeSessionAttributes(xpp);
					}
				} else if (eventType == XmlPullParser.END_TAG) {
					System.out.println("End tag " + xpp.getName());
				} else if (eventType == XmlPullParser.TEXT) {
					//System.out.println("Text " + xpp.getText());
				}
				eventType = xpp.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readTargetsXML() {
		String text = "";
		int[] xArrayInt = null;
		int[] yArrayInt = null;
		int[] tArrayInt = null;
		boolean[] arrayBooleanClickOk = null;
		XmlPullParserFactory factory;
		try {
			factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			InputStream in = activity.getResources().openRawResource(R.raw.targets);
			xpp.setInput(in, "UTF-8");
			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {  				//SWITCH
				if (eventType == XmlPullParser.START_DOCUMENT) {
					System.out.println("Start document");
				} else if (eventType == XmlPullParser.END_DOCUMENT) {
					System.out.println("End document");
				} else if (eventType == XmlPullParser.START_TAG) {
					if("Targets".equalsIgnoreCase(xpp.getName())) {
						targetList = new ArrayList<Target>();
					}
					if("Target".equalsIgnoreCase(xpp.getName())) {
						target = new Target();
					}
					if("Location".equalsIgnoreCase(xpp.getName())) {
						int x = Integer.parseInt(xpp.getAttributeValue(null, "x"));
						int y = Integer.parseInt(xpp.getAttributeValue(null, "y"));
						target.setLocation(new Point(x,y));
					}
					if("Size".equalsIgnoreCase(xpp.getName())) {
						int sizeX = Integer.parseInt(xpp.getAttributeValue(null, "x"));
						int sizeY = Integer.parseInt(xpp.getAttributeValue(null, "y"));
						target.setSizeX(sizeX);
						target.setSizeY(sizeY);
					}
					if("TotalSuccessRate".equalsIgnoreCase(xpp.getName())) {
						session.setClickFailed(Integer.parseInt(xpp.getAttributeValue(null, "clickFailed")));
						session.setClickSucceeded(Integer.parseInt(xpp.getAttributeValue(null, "clickSucceeded")));
					}
					if("Pointer".equalsIgnoreCase(xpp.getName())){
						//....
					}
				} else if (eventType == XmlPullParser.END_TAG) {
					System.out.println("End tag " + xpp.getName());
					if("Target".equalsIgnoreCase(xpp.getName())) {
						targetList.add(target);
					}
					if("Targets".equalsIgnoreCase(xpp.getName())) {
						session.setTargetList(targetList);
					}
					if("Distance".equalsIgnoreCase(xpp.getName())) {
						String[] distanceStg = text.split(" ");
						Float[] distanceArray = new Float[distanceStg.length];
						for(int i=0; i<distanceStg.length;i++){
							distanceArray[i]=Float.parseFloat(distanceStg[i]);
						}
						target.setDistance(distanceArray);
					}
					if("Velocity".equalsIgnoreCase(xpp.getName())) {
						String[] velocityStg = text.split(" ");
						Float[] velocityArray = new Float[velocityStg.length];
						for(int i=0; i<velocityStg.length;i++){
							velocityArray[i]=Float.parseFloat(velocityStg[i]);
						}
						target.setVelocity(velocityArray);
					}
					if("X".equalsIgnoreCase(xpp.getName())){
						String[] arrayX = text.split(" ");
						xArrayInt = new int[arrayX.length];
						for(int i=0; i<xArrayInt.length;i++){
							xArrayInt[i]=Integer.parseInt(arrayX[i]);
						}
					}
					if("Y".equalsIgnoreCase(xpp.getName())){
						String[] arrayY = text.split(" ");
						yArrayInt = new int[arrayY.length];
						for(int i=0; i<yArrayInt.length;i++){
							yArrayInt[i]=Integer.parseInt(arrayY[i]);
						}
					}
					if("T".equalsIgnoreCase(xpp.getName())){
						String[] arrayT = text.split(" ");
						tArrayInt = new int[arrayT.length];
						for(int i=0; i<tArrayInt.length;i++){
							tArrayInt[i]=Integer.parseInt(arrayT[i]);
						}
					}
					if("ClickOk".equalsIgnoreCase(xpp.getName())){
						String[] arrayClickOk = text.split(" ");
						arrayBooleanClickOk = new boolean[arrayClickOk.length];
						for(int i=0; i<arrayBooleanClickOk.length;i++){
							arrayBooleanClickOk[i]=Boolean.parseBoolean(arrayClickOk[i]);
						}
					}
					if("Pointer".equalsIgnoreCase(xpp.getName())){
						List<Point> pointer = new ArrayList<Point>();
						Log.i("LONGITUD DE X: ", String.valueOf(xArrayInt.length));
						Log.i("LONGITUD DE Y: ", String.valueOf(yArrayInt.length));
						Point p;
						for(int i=0; i<xArrayInt.length;i++){
							p = new Point(xArrayInt[i], yArrayInt[i]);
							pointer.add(p);
						}
						target.setPointer(pointer);
					}
					if("Click".equalsIgnoreCase(xpp.getName())){
						Target.Click c = target.new Click();
						Point[] pointer = new Point[xArrayInt.length];
						Log.i("LONGITUD DE X: ", String.valueOf(xArrayInt.length));
						Log.i("LONGITUD DE Y: ", String.valueOf(yArrayInt.length));
						Point p;
						for(int i=0; i<xArrayInt.length;i++){
							p = new Point(xArrayInt[i], yArrayInt[i]);
							pointer[i]=p;
						}
						c.setXy(pointer);
						c.setT(tArrayInt);
						c.setClikOk(arrayBooleanClickOk);
						target.setClick(c);
					}
				} else if (eventType == XmlPullParser.TEXT) {
					//System.out.println("Text " + xpp.getText());
					text = xpp.getText().trim();
				}
				eventType = xpp.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeSessionAttributes(XmlPullParser reader) {
		session = new Session();
		session.setUserId(reader.getAttributeValue(null, "userId"));
		session.setCaregiverId(reader.getAttributeValue(null, "caregiverId"));
		String[] time = reader.getAttributeValue(null, "timeOfDay").split(":");
		int sec = Integer.parseInt(time[2].substring(0, time[2].indexOf(".")));
		session.setTimeOfDay(new Time(Integer.parseInt(time[0]), Integer.parseInt(time[1]), sec));
		String a = reader.getAttributeValue(null, "date");
		//System.out.println("A: [" + a + "]");
		String[] date = reader.getAttributeValue(null, "date").split("-");
		Date mydate = new Date();
		// !!!!!!!!! SET DATE!!!! usar String[] date
		session.setDate(mydate);
		session.setVersion(reader.getAttributeValue(null, "version"));
		session.setScreenWidth(Integer.parseInt(reader.getAttributeValue(null,
				"screenWidth")));
		session.setScreenHeight(Integer.parseInt(reader.getAttributeValue(null,
				"screenHeight")));
		session.setNumberTargets(Integer.parseInt(reader.getAttributeValue(
				null, "numberTargets")));
		session.setId(Double.parseDouble(reader.getAttributeValue(null, "id")));
		session.setTargetSize(Integer.parseInt(reader.getAttributeValue(null,
				"targetSize")));
		session.setTargetDistance(Integer.parseInt(reader.getAttributeValue(
				null, "targetDistance")));
		session.setTaskId(Integer.parseInt(reader.getAttributeValue(null,
				"taskId")));
		time = reader.getAttributeValue(null, "initTask").split(":");
		sec = Integer.parseInt(time[2].substring(0, time[2].indexOf(".")));
		session.setInitTask(new Time(Integer.parseInt(time[0]), Integer
				.parseInt(time[1]), sec));
		time = reader.getAttributeValue(null, "finTask").split(":");
		sec = Integer.parseInt(time[2].substring(0, time[2].indexOf(".")));
		session.setFinTask(new Time(Integer.parseInt(time[0]), Integer
				.parseInt(time[1]), sec));
		session.setVideoTime(Integer.parseInt(reader.getAttributeValue(null,
				"videoTime")));

	}

}
