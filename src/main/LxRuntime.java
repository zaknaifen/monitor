
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LxRuntime {

	
	

	
	
	public static String ExecBash1(String cmd)  {
		StringBuffer output = new StringBuffer();
		
		Process p;
		try {
			
		String r =  Runtime.getRuntime().toString();
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			
			
			BufferedReader reader =
						new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String line = "";
			while ((line = reader.readLine())!=null) {
				output.append(line+'\n');
				
			}
			output.append("");
		//	output.append("END");
			
		
		
			}
		catch (Exception e)
		{
			
		}
		
		return output.toString();
	}
	
	


	public static String ExecBash1(String[] cmd) {
StringBuffer output = new StringBuffer();
		
		Process p;
		try {
			
		String r =  Runtime.getRuntime().toString();
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			
			
			BufferedReader reader =
						new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String line = "";
			while ((line = reader.readLine())!=null) {
				output.append(line+'\n');
				
			}
			output.append("");
		//	output.append("END");
			
		
		
			}
		catch (Exception e)
		{
			
		}
		
		return output.toString();
	}
	
	public static String ExecWin(String command)
	{
		StringBuffer output = new StringBuffer();
		try {
	        Process process = Runtime.getRuntime().exec(command);
	        System.out.println("the output stream is "+process.getOutputStream());
	        BufferedReader reader=new BufferedReader( new InputStreamReader(process.getInputStream()));
	        String s; 
	        while ((s = reader.readLine()) != null){
	          //  System.out.println(s);
	            
	            output.append(s+'\n');
	        }      
	        output.append("");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return output.toString();
	}
	
	
	
	
	
	
}



