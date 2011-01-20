package inter.intergrator.file;

import java.io.File;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XmlUtils {

	public static String validateDocument(File file){
		
		SAXReader reader = new SAXReader();
		reader.setValidation(false);
		reader.setErrorHandler(new SimpleErrorHandler());
		try {
			  reader.read(file).toString();
		
		} catch (DocumentException e) {
		return e.getMessage();
		}
		return "";
	}
}
