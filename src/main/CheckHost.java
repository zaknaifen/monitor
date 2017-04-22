package main;

import java.io.*; 
import java.net.*;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


	public class CheckHost { 
		

		 public enum OSType {
			    Windows, MacOS, Linux, Other
			  };

			 
			  protected static OSType detectedOS;

			
			  public static OSType getOperatingSystemType() {
			    if (detectedOS == null) {
			      String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
			      if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
			        detectedOS = OSType.MacOS;
			      } else if (OS.indexOf("win") >= 0) {
			        detectedOS = OSType.Windows;
			      } else if (OS.indexOf("nux") >= 0) {
			        detectedOS = OSType.Linux;
			      } else {
			        detectedOS = OSType.Other;
			      }
			    }
			    return detectedOS;
			  }
			  
	
		
		
		public static void logger (String logInfo) {  

		    Logger logger = Logger.getLogger("MonitorCheckHostSrv");  
		    FileHandler fh;  

		    try {  

		          
		        fh = new FileHandler("MonitorCheckHost.log", true);  
		        logger.addHandler(fh);
		        SimpleFormatter formatter = new SimpleFormatter();  
		        fh.setFormatter(formatter);  

		        logger.setUseParentHandlers(false);
		        logger.info(logInfo);  
		        fh.close();

		    } catch (SecurityException e) {  
		        e.printStackTrace();  
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    }  

		    

		}
		
public static String version()
{
	String help = "Monitor Client - version 1.2 \n"
			+ "2017 \n";
	return help;
}

public static void debugEnable(String commandLineArgs, String msg){
	
	if(commandLineArgs.equals("debug")) {
		System.out.println(msg);
		logger(msg);
	}
	
	
}
		
		
public static void main(String commandLineArgs[]) throws Exception 
{ 
	
	debugEnable(commandLineArgs[0],"exec");
	String clientSentence; 
	
			ServerSocket welcomeSocket = new ServerSocket(6789); 
		while(true) { 
			
			Socket connectionSocket = welcomeSocket. 
			accept(); 
			BufferedReader inFromClient = 
					new BufferedReader(new InputStreamReader( 
							connectionSocket.getInputStream())); 
			
			DataOutputStream outToClient =new DataOutputStream(connectionSocket.getOutputStream()); 
			clientSentence = inFromClient.readLine(); 
			
			
			
			if(clientSentence == null){}
			else
			{
			if(clientSentence.equalsIgnoreCase("/version"))
			
			{
				
				debugEnable(commandLineArgs[0], version());
				outToClient.writeBytes(getOperatingSystemType().toString()+'\n');
				outToClient.writeBytes(version());
				outToClient.writeBytes("END \n");
			}
			else if(clientSentence.equalsIgnoreCase("/ipconfig"))
			
			{
				
				
				if (getOperatingSystemType().toString().equals("Linux")) outToClient.writeBytes(LxRuntime.ExecBash1("ifconfig"));
				if (getOperatingSystemType().toString().equals("Windows")) outToClient.writeBytes(LxRuntime.ExecWin("ipconfig"));
				outToClient.writeBytes("END \n");
			}
			else if(clientSentence.equalsIgnoreCase("/help"))
			
			{
				outToClient.writeBytes("USAGE: \n"
					+ "MonitorCheckHostSrv target_ip command \n"
					+ "\n"
					+ "\n"
					+ "/version				- show host version \n"
					+ "/function_name 				- execute function included in host \n"
					+ "/list					- list all function \n"
					+ "\"command_name parameter\"		- execute remote command on host \n"
					+ "\".\bat_name [argument]\"			- execute remote bat file \n"
					+ "\"./script_name [argument]\"		- execute remote bash script file \n");
				outToClient.writeBytes("END \n");
				
			}
				
			else if(clientSentence.equalsIgnoreCase("hdd"))
				
			{
				if (getOperatingSystemType().toString().equals("Linux")) outToClient.writeBytes(LxRuntime.ExecBash1("df -h"));
				if (getOperatingSystemType().toString().equals("Windows")) outToClient.writeBytes(LxRuntime.ExecWin(".\\hdd.bat"));
				outToClient.writeBytes("END \n");
				
			}
			
			
			else 
			{
				
				try{
					if (getOperatingSystemType().toString().equals("Linux")) 
						{outToClient.writeBytes(LxRuntime.ExecBash1(clientSentence));
						outToClient.writeBytes("END \n");
						}
					else if (getOperatingSystemType().toString().equals("Windows")) {
						outToClient.writeBytes(LxRuntime.ExecWin(clientSentence));
						outToClient.writeBytes("END \n");
					}
					else {
						outToClient.writeBytes("wrong cmd \n");
						outToClient.writeBytes("END \n");
					}
				}
				catch (Exception e)
				{
					outToClient.writeBytes("ERROR \n");
					outToClient.writeBytes("END \n");
				}
				
				
				
			}
				
				
		
			
				
				
		
			}
			
			
			
			} 
}

}
