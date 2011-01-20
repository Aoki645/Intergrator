package inter.intergrator.file;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import java.io.*;
import java.util.ArrayList;



public class XmlBean{
	private Document document;
	private Element element;
	private Element element2 ;

	int count;
	private ArrayList<Element> elementList = new ArrayList<Element>();

	XmlBean(){
		this.document = DocumentHelper.createDocument();
		element = document.addElement("XML");
	}

	public Document getDocument() {
		return document;
	}

	public Element getElement() {
		return element;
	}
	public ArrayList<Element> getElementList() {
		return elementList;
	}
	public void addElement2(){
		element2 = element.addElement("DATA");
	}

	public Element getElement2() {
		return element2;
	}


}
