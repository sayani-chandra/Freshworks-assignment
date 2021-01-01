package jsonproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Operations {

	public void create(String pathname, File f) throws FileNotFoundException, IOException, ParseException {
		// TODO Auto-generated method stub
	 JSONParser p = new JSONParser();  
	 JSONObject j = (JSONObject) p.parse(new FileReader(f));
	      save(pathname,j);
	}

	public void save(String pathname, JSONObject ob) throws IOException {
		// TODO Auto-generated method stub
			OutputStream o = null;
			try {
				if(checksize(pathname)) {
					File f1=new File(pathname);
					o = new FileOutputStream(f1, false);
					o.write(ob.toJSONString().getBytes(), 0, ob.toJSONString().length());
					}
				} 
				catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					o.close();
				}	
		}

	public boolean checksize(String pathname) {
		// TODO Auto-generated method stub
			long size = 1073741824L; 
			
				if (determinesize(pathname)>size) {
					return false;
			     } 
			
			return true;
		}

	public long determinesize(String pathname) {
		// TODO Auto-generated method stub
		File obj = new File(pathname);
		return obj.length();
	}
	
	
	public JSONObject get(String pathname) throws IOException {
		JSONParser p = new JSONParser();
		FileReader reader = null;
	      try {
	    	 reader = new FileReader(pathname);
	         
	         if(!reader.toString().isEmpty() && !reader.toString().equals("")) 
	         {
	        	 JSONObject j = (JSONObject) p.parse(reader);
	        	 return j;
	         }
	      } catch(Exception e) {
	         e.printStackTrace();
	      } 
		return null;
	}
		

	
		
	}


