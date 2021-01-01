package jsonproject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class CRD {
	
	Operations op=new Operations();
	String key=new String();
	JSONObject ob = new JSONObject();
	JSONObject p = new JSONObject();
	Scanner sc=new Scanner(System.in);
	
	public void read(String pathname) {
		try {
			JSONObject data = op.get(pathname);
			System.out.println("Enter Key: ");
		     key = sc.nextLine();
		     
			if(key.length() < 32) {
				if (data.get(key) != null)
	            {
					JSONObject k = (JSONObject)data.get(key);
					String s = (String) k.get("street");
					String c= (String) k.get("city");
					String st = (String) k.get("state");
			        
			        System.out.println("Key: " + key);
			  System.out.println("Street -" + s + " \nCity -" + c + "\nState -" + st);
	            } else
	                System.out.println("Values not present for this key");
			} else {
				System.out.println("Key is too long( >32chars)");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	@SuppressWarnings("unchecked")
	public void writetofile(String pathname) {
		
		try {
			
			JSONObject data = op.get(pathname);
			System.out.println("Enter Key: ");
			 key = sc.nextLine();
			if(key.length() < 32 && op.checksize(pathname)) 
			{
				if (data.get(key) == null)
	            {
					
					System.out.println("Enter Street: ");
					ob.put("street", sc.next()); 
					System.out.println("Enter City: ");
					ob.put("city", sc.next()); 
					System.out.println("Enter State: ");
					ob.put("state", sc.next()); 
					
				
					System.out.println("Want to execute Time to Live Property?(Y/N) ");
					if(sc.next().equalsIgnoreCase("Y")) {
						JSONObject time = new JSONObject();
						System.out.println("Enter time in seconds: ");
						LocalDateTime instant= LocalDateTime.now();
						int t=sc.nextInt();
						time.put("Time in seconds", t);
						time.put("Saving time",""+instant);
						time.put("Key", key);
						ob.put("Time To Live", time);
					}
					p.put(key, ob);
					data.putAll(p);
					if(p.toString().length()>=16384) {
						System.out.println("Value Exceeds 16KB");
					}
					else {
						op.save(pathname, data);
					    }
					
	            } 
				else {
	                System.out.println("Key already exists");
	               }
			}
			else {
				System.out.println("Key is too long( >32 chars)");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void delete(String pathname) throws IOException {
		JSONObject data = op.get(pathname);
		
		System.out.println("Enter Key: ");
	     key = sc.nextLine();
		if (data.get(key) == null)
        {
			System.out.println("Invalid key");
        } else {
        	
        	data.remove(key);
			op.save(pathname, data);
        }
	}

}
