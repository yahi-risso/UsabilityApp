package es.upmcsic.car.usabilityapp.xmlvalidator;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.util.Log;
import es.upmcsic.car.usabilityapp.R;
import es.upmcsic.car.usabilityapp.model.MyPoint;
import es.upmcsic.car.usabilityapp.model.Session;
import es.upmcsic.car.usabilityapp.model.Target;

public class SessionParser {

	ArrayList<Target> targetList;
	Session session;
	Target target;
	Activity activity;
	
	
	private enum Tag_1 { Targets, Target, Location, Size, Pointer, TotalSuccessRate,  Distance, Velocity, X, Y, T, ClickOk, Click, Default; }

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
			InputStream in = activity.getResources().openRawResource(
					R.raw.session);
			xpp.setInput(in, "UTF-8");
			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) { // SWITCH
				switch(eventType){
					case XmlPullParser.START_DOCUMENT: {
						System.out.println("Start document");
						break;
					} 
					case XmlPullParser.END_DOCUMENT: {
						System.out.println("End document");
						break;
					}
					case XmlPullParser.START_TAG: {
						System.out.println("Start tag " + xpp.getName());
						if ("Session".equalsIgnoreCase(xpp.getName())) {
							writeSessionAttributes(xpp);
						}
						break;
					}  
					case XmlPullParser.END_TAG: {
						break;
					}  
					case XmlPullParser.TEXT: {
						break;
					}
				}
				eventType = xpp.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			//Log.i("SESSION BEFORE: ",session.toString());
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
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
					case XmlPullParser.START_DOCUMENT: {
						System.out.println("Start document");
						break;
					}
					case XmlPullParser.END_DOCUMENT: {
						System.out.println("End document");
						break;
					}
					case XmlPullParser.START_TAG: {
						String value = xpp.getName();
						//Log.i("-->>VALUE: ", value);
						Tag_1 tagName1 = null;
						try{ 
							tagName1= Tag_1.valueOf(value);
						}catch(Exception e){
							e.printStackTrace();
							tagName1=Tag_1.Default;
						}
						switch(tagName1){
							case Targets: {
								targetList = new ArrayList<Target>();
								break;
							}
							case Target: {
								target = new Target();
								break;
							}
							case Location: {
								int x = Integer.parseInt(xpp.getAttributeValue(null, "x"));
								int y = Integer.parseInt(xpp.getAttributeValue(null, "y"));
								target.setLocation(new MyPoint(x, y));
								break;
							}
							case Size: {
								int sizeX = Integer.parseInt(xpp.getAttributeValue(null, "x"));
								int sizeY = Integer.parseInt(xpp.getAttributeValue(null, "y"));
								target.setSizeX(sizeX);
								target.setSizeY(sizeY);
								break;
							}
							case TotalSuccessRate: {
								session.setClickFailed(Integer.parseInt(xpp
										.getAttributeValue(null, "clickFailed")));
								session.setClickSucceeded(Integer.parseInt(xpp
										.getAttributeValue(null, "clickSucceeded")));
								break;
							}
							default:{
								break;
							}
						}
						break;
					}
					case XmlPullParser.END_TAG: {
						String value = xpp.getName();
						Tag_1 tagName=null;
						try{
							tagName = Tag_1.valueOf(value);
						}catch(Exception e){
							e.printStackTrace();
							tagName = Tag_1.Default;
						}
						switch(tagName){
							case Target: {
								targetList.add(target);
								break;
							}
							case Targets: {
								session.setTargetList(targetList);
								break;
							}
							case Distance: {
								String[] distanceStg = text.split(" ");
								Float[] distanceArray = new Float[distanceStg.length];
								for (int i = 0; i < distanceStg.length; i++) {
									distanceArray[i] = Float.parseFloat(distanceStg[i]);
								}
								target.setDistance(distanceArray);
								break;
							}
							case Velocity: {
								String[] velocityStg = text.split(" ");
								Float[] velocityArray = new Float[velocityStg.length];
								for (int i = 0; i < velocityStg.length; i++) {
									velocityArray[i] = Float.parseFloat(velocityStg[i]);
								}
								target.setVelocity(velocityArray);
								break;
							}
							case X: {
								String[] arrayX = text.split(" ");
								xArrayInt = new int[arrayX.length];
								for (int i = 0; i < xArrayInt.length; i++) {
									xArrayInt[i] = Integer.parseInt(arrayX[i]);
								}
								break;
							}
							case Y: {
								String[] arrayY = text.split(" ");
								yArrayInt = new int[arrayY.length];
								for (int i = 0; i < yArrayInt.length; i++) {
									yArrayInt[i] = Integer.parseInt(arrayY[i]);
								}
								break;
							}
							case T: {
								String[] arrayT = text.split(" ");
								tArrayInt = new int[arrayT.length];
								for (int i = 0; i < tArrayInt.length; i++) {
									tArrayInt[i] = Integer.parseInt(arrayT[i]);
								}
								break;
							}
							case ClickOk: {
								String[] arrayClickOk = text.split(" ");
								arrayBooleanClickOk = new boolean[arrayClickOk.length];
								for (int i = 0; i < arrayBooleanClickOk.length; i++) {
									arrayBooleanClickOk[i] = Boolean
											.parseBoolean(arrayClickOk[i]);
								}
								break;
							}
							case Pointer: {
								ArrayList<MyPoint> pointer = new ArrayList<MyPoint>();
								Log.i("-->[POINTER] LONGITUD DE X: ", String.valueOf(xArrayInt.length));
								Log.i("-->[POINTER] LONGITUD DE Y: ", String.valueOf(yArrayInt.length));
								MyPoint p;
								for (int i = 0; i < xArrayInt.length; i++) {
									p = new MyPoint(xArrayInt[i], yArrayInt[i]);
									pointer.add(p);
								}
								target.setPointer(pointer);
								break;
							}
							case Click: {
								Target.Click c = target.new Click();
								MyPoint[] MyPointer = new MyPoint[xArrayInt.length];
								//Log.i("LONGITUD DE X: ", String.valueOf(xArrayInt.length));
								//Log.i("LONGITUD DE Y: ", String.valueOf(yArrayInt.length));
								MyPoint p;
								for (int i = 0; i < xArrayInt.length; i++) {
									p = new MyPoint(xArrayInt[i], yArrayInt[i]);
									MyPointer[i] = p;
								}
								c.setXy(MyPointer);
								c.setT(tArrayInt);
								c.setClikOk(arrayBooleanClickOk);
								target.setClick(c);
								break;
							}
							default:{
								break;
							}
						}
						break;
					}
					case XmlPullParser.TEXT: {
						text = xpp.getText().trim();
						break;
					}
				}
				eventType = xpp.next();
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeSessionAttributes(XmlPullParser reader) {
		session = new Session();
		session.setUserId(reader.getAttributeValue(null, "userId"));
		session.setCaregiverId(reader.getAttributeValue(null, "caregiverId"));
		String[] time = reader.getAttributeValue(null, "timeOfDay").split(":");
		int sec = Integer.parseInt(time[2].substring(0, time[2].indexOf(".")));
		session.setTimeOfDay(new Time(Integer.parseInt(time[0]), Integer
				.parseInt(time[1]), sec));
		String a = reader.getAttributeValue(null, "date");
		// System.out.println("A: [" + a + "]");
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
