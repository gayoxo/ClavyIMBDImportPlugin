package es.ucm.ilsa.imbd.ClavyIMBDImportQuickStart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

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
		C.procesaDBFilesFolder("files/ex1/", log);
		
		/**
		
		 try {
				String FileIO = System.getProperty("user.home")+File.separator+System.currentTimeMillis()+".clavy";
				
				System.out.println(FileIO);
				
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FileIO));

				oos.writeObject(C.getCollection());

				oos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			**/
	}

	private CompleteCollection getCollection() {
		return C;
	}

	private void procesaDBFilesFolder(String folderPath, ArrayList<String> log) {
		
		HashMap<Integer, String> tablaKEy=new HashMap<Integer, String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(folderPath+"Movie_keyword_names.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
            	String[] Total = linea.split("\t");
            	
            	String KeyInt = Total[0];
            	String KeyWord = Total[1];
            	tablaKEy.put(Integer.parseInt(KeyInt), KeyWord);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		HashMap<String, List<Integer>> tablaFilm=new HashMap<String, List<Integer>>();
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(folderPath+"List_of_movies.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
            	String[] Total = linea.split(" ");
            	String MovieNu = Total[0];
            	List<Integer> ListaKeywords=new LinkedList<Integer>();
            	for (int i = 1; i < Total.length; i++) {
            		ListaKeywords.add(Integer.parseInt(Total[i]));
				}
            	
            	tablaFilm.put(MovieNu, ListaKeywords);
            	
            	//System.out.println("Pelicula :" +MovieNu+" KeyWords:"+Arrays.toString(ListaKeywords.toArray()));
            	
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		
		for (Entry<String, List<Integer>> FilKey : tablaFilm.entrySet()) {
			
			List<String> SS=new LinkedList<String>();
			for (Integer intkey : FilKey.getValue()) {
				SS.add(tablaKEy.get(intkey));
			}
			
			System.out.println("Pelicula :" +FilKey.getKey()+" KeyWords:"+Arrays.toString(SS.toArray()));
			
		}
		
	}
}
