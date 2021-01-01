package jsonproject;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;

import org.json.simple.parser.ParseException;

public class Main {
	public static void main(String args[]) throws ParseException, IOException
	{
		Scanner sc = new Scanner(System.in);
		Operations op=new Operations();
		CRD crd=new CRD();
		 String path ="";
			 
         File file;
		try {

			System.out.println("Do you want to provide the file path?(Y/N)");
             String s=sc.next();
             
            
		// if file path is not provided, file would be created in the given directory
			if (s.equalsIgnoreCase("N")) {
				
				 file = new File("employee.json");
				path = file.getAbsolutePath();
				
			}

	  // when file path is provided, file is created in the specified directory
			else {
			    path=sc.nextLine();
				path = path + "employee.json";
            
				 file = new File(path);

				//file.getParentFile().mkdir();

				//System.out.println("File would be created in the file path :" + filePath);
			}

			// checks if the file exists or not.
			if (file.createNewFile()) 
			{
				System.out.println("File has been created: " + file.getName());
				op.create(path, file);
			}
			 else 
			 {
				System.out.println("File exists already");
				
			 }
		} catch (IOException e) {

			System.err.println("An error occurred.");
			e.printStackTrace();

		}
		 Timer t = new Timer();
	        try {
				ScheduleTime s = new ScheduleTime(path);
				t.scheduleAtFixedRate(s, 10, 10);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Something with Timer");
			}
	        while(true)
	        {
	            
	            System.out.println("1. Create the data store  \n2. Read data for a given key \n3. Delete data for a given key \n4. Exit");
	            int c= sc.nextInt();
	            switch (c) {
				case 1:
					crd.writetofile(path);
					System.out.println("\nData has been stored successfully");
					break;
				case 2:
					crd.read(path);
					break;
				case 3:
					crd.delete(path);
					break;
                 case 4:
					System.out.println("\nTerminated");
					System.exit(0);
					break;
				default:
					System.out.println("Please enter a valid option\n");
					c = sc.nextInt();
                    break;
					
				}
			} 
	           
	}
}


