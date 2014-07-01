package es.upmcsic.car.usabilityapp.xmlvalidator;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {

	@Override
	public void error(SAXParseException arg0) throws SAXException {
		System.out.println("[ERROR] " + arg0.getMessage());
		throw arg0;
	}

	@Override
	public void fatalError(SAXParseException arg0) throws SAXException {
		System.out.println("[FATAL] " + arg0.getMessage());
		throw arg0;
	}

	@Override
	public void warning(SAXParseException arg0) throws SAXException {
		System.out.println("[WARNING] " + arg0.getMessage());
	}

}