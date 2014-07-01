package es.upmcsic.car.usabilityapp.xmlvalidator;

import java.io.File;
import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import es.upmcsic.car.usabilityapp.model.Session;
import es.upmcsic.car.usabilityapp.pruebas.bluetooth.MyHandler;

public class ParserJava_old implements Runnable{

	MyHandler resultHandler;
	
	public ParserJava_old(MyHandler resultHandler){
		this.resultHandler = resultHandler;
	}
	
	@Override
	public void run() {
		String fileName = "Session.xml";
		String schemaName = "session.xsd";
		boolean valid = false;
		try {
			 valid = (validateWithExtXSDUsingSAX(fileName, schemaName));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.i("-->> ","VALID = "+valid);
		publishValidationResult(valid+"");
	}
	
	public  boolean validateWithExtXSDUsingSAX(String xml, String xsd)
			throws ParserConfigurationException, IOException {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(false);
			factory.setNamespaceAware(true);

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			SAXParser parser = null;
			try {
				factory.setSchema(schemaFactory.newSchema(new Source[] { new StreamSource(xsd) }));
				parser = factory.newSAXParser();
			} catch (SAXException se) {
				System.out.println("SCHEMA : " + se.getMessage()); // problem in the XSD itself
				return false;
			}
			XMLReader reader = parser.getXMLReader();
			reader.setErrorHandler(new SimpleErrorHandler());
			reader.parse(new InputSource(xml));
			return true;
		} catch (ParserConfigurationException pce) {
			throw pce;
		} catch (IOException io) {
			throw io;
		} catch (SAXException se) {
			return false;
		}
	}

	public Session createSession(){
		Session session = new Session();
		
		return session;
	}
	
	private void publishValidationResult(String validation) {
        Bundle msgBundle = new Bundle();
        msgBundle.putString("validation", validation);
        Message msg = new Message();
        msg.setData(msgBundle);
        resultHandler.sendMessage(msg);
    }
}
