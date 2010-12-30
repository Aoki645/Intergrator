package inter.intergrator.file;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  

import java.nio.ByteBuffer;  
import java.nio.channels.FileChannel;  

import java.util.ArrayList;  
import java.util.List;  

public class FileUtils {  


	public String doUpload(byte[] bytes, String fileName) throws Exception  
	{  
		System.out.println("Processando arquivo " + fileName);
		//fileName = System.getProperty("java.io.tmpdir") + "/" + fileName;
		fileName = "c:/temp/" + fileName;
		File f = new File(fileName);  
		FileOutputStream fos = new FileOutputStream(f);  
		fos.write(bytes);  
		fos.close();  
		System.out.println("Finalizado arquivo " + fileName);
		return "success";  
	}  

	public List  getDownloadList()  
	{  
		System.out.println("entrando");
		//        File dir = new File(System.getProperty("java.io.tmpdir"));
		File dir = new File("c:/temp/");
		String[] children = dir.list();  
		List dirList = new ArrayList();  
		if (children == null) {  
			// Either dir does not exist or is not a directory  
		} else {  
			for (int i=0; i<children.length; i++) {  
				// Get filename of file or directory  
				dirList.add( children[i]);  
			}  
		}  
		return dirList;  
	}  

	public byte[] doDownload(String fileName)  
	{  
		FileInputStream fis;  
		byte[] data =null;  
		FileChannel fc;  

		try {  
			fis = new FileInputStream(System.getProperty("java.io.tmpdir") + "/" + fileName);  
			fc = fis.getChannel();  
			data = new byte[(int)(fc.size())];  
			ByteBuffer bb = ByteBuffer.wrap(data);  
			fc.read(bb);  
		} catch (FileNotFoundException e) {  
			// TODO  
		} catch (IOException e) {  
			// TODO  
		}  
		return data;  
	}  
}  