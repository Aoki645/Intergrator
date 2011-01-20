package inter.intergrator.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	public String doUpload(byte[] bytes, String fileName) throws Exception {

		System.out.println("Processando arquivo " + fileName);
		fileName = "c:/temp/" + fileName;
		File f = new File(fileName);
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(bytes);
		fos.close();
		System.out.println("Finalizado arquivo " + fileName);
		return "success";
	}

	public List getDownloadList() {
		System.out.println("entrando");
		File dir = new File("c:/temp/");
		String[] children = dir.list();
		List dirList = new ArrayList();
		if (children == null) {
		} else {
			for (int i = 0; i < children.length; i++) {
				dirList.add(children[i]);
			}
		}
		return dirList;
	}

	public byte[] doDownload(String fileName) {
		FileInputStream fis;
		byte[] data = null;
		FileChannel fc;

		try {
			fis = new FileInputStream(System.getProperty("java.io.tmpdir")
					+ "/" + fileName);
			fc = fis.getChannel();
			data = new byte[(int) (fc.size())];
			ByteBuffer bb = ByteBuffer.wrap(data);
			fc.read(bb);
		} catch (FileNotFoundException e) {
			// TODO
		} catch (IOException e) {
			// TODO
		}
		return data;
	}

	public void cancelUploads(String fileName) {
		File file = new File("c:/temp/" + fileName);
		if (file.exists()) {
			file.delete();
			System.out.println("Arquivo cancelado");
		} else {
			System.out.println("Arquivo não cancelado");
		}

	}

	public ArrayList<String> readFile(String fileName) {
		ArrayList<String> listLines = new ArrayList<String>();
		File file = new File("c:/temp/" + fileName);
		String lines = "";
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			int count = 0;
			while ((line = br.readLine()) != null && count < 10) {
				listLines.add(line);
				count++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listLines;
	}

	private String[] getColumnFildsSplit(String line, String regex) {
		return line.split(regex);
	}

	private String[] getColumnFildsDefault(int length) {
		String[] split = new String[length];
		for (int j = 0; j < length; j++) {
			split[j] = "line" + j;
		}
		return split;
	}

	public String populateColumnsSplit(String[] list, String regex,
			boolean firstLineFildName) {

		String[] filds = null;
		XmlBean xmlbean = new XmlBean();

		int index = 0;
		if (firstLineFildName) {
			filds = getColumnFildsSplit(list[index++], regex);
		} else {
			filds = getColumnFildsDefault(list[0].length());
		}
		int elementCount = 0;

		System.out.println("Criando XML2" + regex);
		while (index < list.length) {
			int count = 0;
			xmlbean.addElement2();
			for (String col : list[index].split(regex)) {
				xmlbean.getElementList().add(
						xmlbean.getElement2().addElement(filds[count]));
				xmlbean.getElementList().get(elementCount).setText(col);
				elementCount++;
				count++;
			}

			index++;

		}
		System.out.println(xmlbean.getDocument().asXML());
		return xmlbean.getDocument().asXML();

	}


	public String[] getColumnFildsPosition(ArrayList<Integer> pos, String line) {
		String[] column = new String[pos.size() / 2];
		for (int i = 0, j = 0; i < column.length; i++, j++) {
			column[i] = line.substring((pos.get(j)), (pos.get(++j))).trim();

		}
		return column;
	}

	public String populateColumnsPosition(ArrayList<Integer> pos,
			String [] line, boolean firstLineFildName) {
		String[] filds = null;
		XmlBean xmlbean = new XmlBean();

		if (firstLineFildName) {
			filds = getColumnFildsPosition(pos, line[0]);
		} else {
			filds = getColumnFildsDefault(pos.size() / 2);
		}

		int elementCount = 0;
		int count = 0;
		for(String value: line){
			xmlbean.addElement2();
			for (String col : filds) {				

				xmlbean.getElementList().add(xmlbean.getElement2().addElement(col));

				xmlbean.getElementList().get(elementCount).setText(value.substring(pos.get(count),pos.get(1+count)));				

				elementCount++;
				count += 2;	
			}	
			count = 0;			
		}
		System.out.println(xmlbean.getDocument().asXML());
		return xmlbean.getDocument().asXML();

	}
	public static void main(String[] args) {
		FileUtils file = new FileUtils();
		String a [] = file.getColumnFildsSplit("a~a~a~a~a~a~", "~");
		for (String b : a){
			System.out.println(b);
		}
	}
	
}