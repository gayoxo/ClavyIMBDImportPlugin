package es.ucm.ilsa.imbd.ClavyIMBDImportQuickStart;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import fdi.ucm.server.modelComplete.collection.CompleteCollection;


public class CollectionIMDB 
{
	private boolean debugfile;
	public CompleteCollection C=new CompleteCollection();
	public ArrayList<String> log;

	public static void main(String[] args) {
		CollectionIMDB C=new CollectionIMDB();
		ArrayList<String> log = new ArrayList<String>();
		C.debugfile=true;
		C.procesaDBFilesFolder("files/ex1/Patient", log);
		
		 try {
				String FileIO = System.getProperty("user.home")+File.separator+System.currentTimeMillis()+".clavy";
				
				System.out.println(FileIO);
				
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FileIO));

				oos.writeObject(C.getCollection());

				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	private Object getCollection() {
		return C;
	}

	private void procesaDBFilesFolder(String string, ArrayList<String> log) {
		// TODO Auto-generated method stub
		
	}
}
