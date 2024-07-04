package WmTNMigration.wm;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.entrust.toolkit.asn1.structures.Time;
import com.ibm.icu.util.TimeZone;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// --- <<IS-END-IMPORTS>> ---

public final class java

{
	// ---( internal utility methods )---

	final static java _instance = new java();

	static java _newInstance() { return new java(); }

	static java _cast(Object o) { return (java)o; }

	// ---( server methods )---




	public static final void createFileCommand (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(createFileCommand)>> ---
		// @sigtype java 3.5
		// [i] field:0:required filePath
		// [i] field:0:required inputFileContent
		// [o] field:0:required fileUpdated
		 IDataCursor pipelineCursor = pipeline.getCursor();
		 String	filePath = IDataUtil.getString( pipelineCursor, "filePath" );
		 String	inputFileContent = IDataUtil.getString( pipelineCursor, "inputFileContent" );
		 /*Path path=Paths.get(filePath);
		  if(!Files.exists(path))
		  {
		  }*/
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(filePath);
			byte[] strToBytes = inputFileContent.getBytes();
			outputStream.write(strToBytes);
			outputStream.close();
			IDataUtil.put( pipelineCursor, "fileUpdated", true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			IDataUtil.put( pipelineCursor, "fileUpdated", false);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			IDataUtil.put( pipelineCursor, "fileUpdated", false);
			e.printStackTrace();
		}
		   
			
		// --- <<IS-END>> ---

                
	}



	public static final void documentMatch (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(documentMatch)>> ---
		// @sigtype java 3.5
		// [i] field:0:required inputString
		// [o] field:0:required startsWith
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	inputString = IDataUtil.getString( pipelineCursor, "inputString" );
		pipelineCursor.destroy();
		 String regex = "^WM.*";
		 String startsWith = Boolean.toString(inputString.matches(regex));
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "startsWith",  startsWith);
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void executeOSCommand (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(executeOSCommand)>> ---
		// @sigtype java 3.5
		String commandArray[] = {"cmd","/C","C:/SoftwareAG/IntegrationServer/instances/default/packages/WmTN/bin/tnexport -file C:/SoftwareAG/IntegrationServer/instances/default/packages/WmTN/bin/TNExport.xml"};
		/*ProcessBuilder processBuilder = new ProcessBuilder("tnexport.bat", "-file", "C:/SoftwareAG/IntegrationServer/instances/default/packages/WmTN/bin/TNExport.xml");
		 processBuilder.directory(new File("C:/SoftwareAG/IntegrationServer/instances/default/packages/WmTN/bin/"));
		 File log = new File("C:/SoftwareAG/IntegrationServer/instances/default/packages/WmTN/bin/log.txt");
		 processBuilder.redirectErrorStream(true);
		 processBuilder.redirectOutput(Redirect.appendTo(log));
		 Process p;
		try {
			p = processBuilder.start();
			p.waitFor();
			IDataCursor pipelineCursor = pipeline.getCursor();
		       IDataUtil.put( pipelineCursor, "output","success" );
		       pipelineCursor.destroy();
		} catch (IOException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		 
		System.out.println("Done");
		
		try {
		       Process process = Runtime.getRuntime().exec(commandArray);
		       process.waitFor();
		       IDataCursor pipelineCursor = pipeline.getCursor();
		       IDataUtil.put( pipelineCursor, "output","success" );
		       pipelineCursor.destroy();
		       // pipeline
		
		       // pipeline
		      /* IDataCursor pipelineCursor = pipeline.getCursor();
		       BufferedReader reader = new BufferedReader(
		                   new InputStreamReader(process.getInputStream()));
		       ArrayList<String> line = new ArrayList<String>();
		           String line1=null;
		           while ((line1 = reader.readLine()) != null) {
		              line.add(line1);
		           }
		       
		           reader.close();
		       
		       IDataUtil.put( pipelineCursor, "output",line.toArray() );
		       pipelineCursor.destroy();
		*/
		       
		   
		} catch (IOException | InterruptedException e) {
		    e.printStackTrace();
		    throw new ServiceException(e.getMessage());
		}
		       
			
		// --- <<IS-END>> ---

                
	}



	public static final void executeSystemCommand (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(executeSystemCommand)>> ---
		// @sigtype java 3.5
		// [o] field:0:required output
		// pipeline
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		IDataUtil.put( pipelineCursor, "output", System.getProperty("user.dir") );
		pipelineCursor.destroy();
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void getRuleName (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getRuleName)>> ---
		// @sigtype java 3.5
		// [i] field:0:required input
		// [o] field:0:required ruleID
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	input = IDataUtil.getString( pipelineCursor, "input" );
		String ruleID="";
		ruleID=extractRuleNameValue(input);
		if(ruleID!=null && ruleID!="")
			IDataUtil.put( pipelineCursor, "ruleID", ruleID );
		else
		IDataUtil.put( pipelineCursor, "ruleID", "" );
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void getTPADetails (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getTPADetails)>> ---
		// @sigtype java 3.5
		// [i] field:0:required input
		// [o] field:0:required senderID {"Unknown"}
		// [o] field:0:required receiverID {"Unknown"}
		// [o] field:0:required agreementID
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	input = IDataUtil.getString( pipelineCursor, "input" );
		pipelineCursor.destroy();
		String senderID="",receiverID="",agreementID="";
		senderID=extractValue(input,"Sender ID");
		receiverID=extractValue(input,"Receiver ID");
		agreementID=extractValue(input,"Agreement ID");
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		pipelineCursor.destroy();
		if(senderID!=null && senderID!="")
			IDataUtil.put( pipelineCursor_1, "senderID", senderID );
			else
			IDataUtil.put( pipelineCursor_1, "senderID", "" );
		
		
		if(receiverID!=null && receiverID!="")
		IDataUtil.put( pipelineCursor_1, "receiverID", receiverID );
		else
		IDataUtil.put( pipelineCursor_1, "receiverID", "" );
		if(agreementID!=null && agreementID!="")
		IDataUtil.put( pipelineCursor_1, "agreementID", agreementID );
		else
		IDataUtil.put( pipelineCursor_1, "agreementID", "" );
		
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void keyValuePair (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(keyValuePair)>> ---
		// @sigtype java 3.5
		// [i] field:0:required input
		// [i] field:0:required id
		// [o] field:0:required output
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	input = IDataUtil.getString( pipelineCursor, "input" );
		String	id = IDataUtil.getString( pipelineCursor, "id" );
		 String[] keyValuePairs = input.substring(1, input.length() - 1).split(", ");
		
		    // Create a map to store key-value pairs
		    Map<String, String> keyValueMap = new HashMap<>();
		    for (String pair : keyValuePairs) {
		        String[] keyValue = pair.split("=");
		        keyValueMap.put(keyValue[0], keyValue[1]);
		    }
		
		    // Get the value for the specified ID
		    String value = keyValueMap.get(id);
		    IDataUtil.put( pipelineCursor, "output", value );
		
		  
		// --- <<IS-END>> ---

                
	}



	public static final void mkdirCommand (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(mkdirCommand)>> ---
		// @sigtype java 3.5
		// [i] field:0:required filePath
		// [o] field:0:required dirCreated
		// [o] field:0:required filePathExists
  File f = null;
  boolean bool = false;
  
  try {
	  IDataCursor pipelineCursor = pipeline.getCursor();
	  String	filePath = IDataUtil.getString( pipelineCursor, "filePath" );
	  Path path=Paths.get(filePath);
	  if(!Files.exists(path))
	  {

     // returns pathnames for files and directory
     f = new File(filePath);
     // create
   
     bool = f.mkdir();
     IDataUtil.put( pipelineCursor, "dirCreated", bool);
     IDataUtil.put( pipelineCursor, "filePathExists", true);
	  }else{
		  IDataUtil.put( pipelineCursor, "dirCreated", false);
		  IDataUtil.put( pipelineCursor, "filePathExists", true);             
                
             }  
     pipelineCursor.destroy();
  }
  catch (Exception e){}
		// --- <<IS-END>> ---

                
	}



	public static final void parseRuleDetails (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(parseRuleDetails)>> ---
		// @sigtype java 3.5
		// [i] field:0:required input
		// [o] field:1:required output
		// pipeline
				IDataCursor pipelineCursor = pipeline.getCursor();
					String	input = IDataUtil.getString( pipelineCursor, "input" );
				pipelineCursor.destroy();
				if(input!=null){
				
				ArrayList<String> resultList = parseRule( input, "Internal ID:");
				 // pipeline
					IDataCursor pipelineCursor_1 = pipeline.getCursor();
					
					IDataUtil.put( pipelineCursor_1, "output",resultList.toArray(String[]::new));
					pipelineCursor_1.destroy();
				}
				else
				{IDataCursor pipelineCursor_1 = pipeline.getCursor();
				 
				IDataUtil.put( pipelineCursor_1, "output","");
				pipelineCursor_1.destroy();}
					// pipeline
					
		// --- <<IS-END>> ---

                
	}



	public static final void parseTPADetails (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(parseTPADetails)>> ---
		// @sigtype java 3.5
		// [i] object:0:required input
		// [o] field:1:required output
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			Object	input = IDataUtil.get( pipelineCursor, "input" );
		pipelineCursor.destroy();
		if(input!=null){
		
		ArrayList<String> resultList = extractDataBetween(input.toString(), "<<<TPA ID =");
		 // pipeline
			IDataCursor pipelineCursor_1 = pipeline.getCursor();
			
			IDataUtil.put( pipelineCursor_1, "output",resultList.toArray(String[]::new));
			pipelineCursor_1.destroy();
		}
		else
		{IDataCursor pipelineCursor_1 = pipeline.getCursor();
		 
		IDataUtil.put( pipelineCursor_1, "output","");
		pipelineCursor_1.destroy();}
			// pipeline
			
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void randomNumberGenerator (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(randomNumberGenerator)>> ---
		// @sigtype java 3.5
		// [o] field:0:required outputNumber
		 // Generate a random numeric part (assuming 8 digits)
		String numericPart = generateRandomNumericPart(7);
		
		// Create the complete random number
		String randomNumber =Instant.now().getEpochSecond()+numericPart; 
		
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "outputNumber", randomNumber );
		pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void stringMapToDocumentList (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stringMapToDocumentList)>> ---
		// @sigtype java 3.5
		// [i] field:2:required attributeConditions
		// [o] record:1:required attributeConditions
		// [o] - field:0:required attributeId
		// [o] - field:0:required operation
		// [o] - field:0:required value
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String[][]	attributeConditions = IDataUtil.getStringTable( pipelineCursor, "attributeConditions" );
		pipelineCursor.destroy();
		;
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		ArrayList<IData> documentList = new ArrayList<IData>();
			if(attributeConditions!=null){
		    for (String[] row : attributeConditions) {
		
		IData attributeConditions_1 = IDataFactory.create();
		IDataCursor attributeConditions_1Cursor = attributeConditions_1.getCursor();
		IDataUtil.put( attributeConditions_1Cursor, "attributeId", row[0] );
		IDataUtil.put( attributeConditions_1Cursor, "operation", row[1]);
		IDataUtil.put( attributeConditions_1Cursor, "value",  row[2] );
		attributeConditions_1Cursor.destroy();
		documentList.add(attributeConditions_1);
		    }
		// attributeConditions
		
		IDataUtil.put( pipelineCursor_1, "attributeConditions",documentList.toArray(new IData[documentList.size()]) );
		pipelineCursor_1.destroy();
			}
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---



 public static ArrayList<String> extractDataBetween(String inputString, String startDelimiter) {
        ArrayList<String> resultList = new ArrayList<>();
        String regex = startDelimiter + "(.*?)(?=(<<<|$))";  // Positive lookahead to match until the next '<<<' or end of string
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(inputString);

        while (matcher.find()) {
            String matchedData = matcher.group(1).trim();
            resultList.add(matchedData);
        }

        return resultList;
    }
 public static ArrayList<String> parseRule(String inputString, String startDelimiter) {
        ArrayList<String> resultList = new ArrayList<>();
        String regex = startDelimiter +  "(.*?)(?=(,|$))";  // Positive lookahead to match until the next '<<<' or end of string
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(inputString);

        while (matcher.find()) {
            String matchedData = matcher.group(1).trim();
            resultList.add(matchedData);
        }

        return resultList;
 }
        public static String extractValue(String inputString, String key) {
	        String regex = key + "\\s*=\\s*([^,]+)";
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(inputString);
	
	        if (matcher.find()) {
	            return matcher.group(1).trim();
	        } else {
	            return null;
	        }
	    }    
 	
 	
 public static String  extractRuleNameValue(String inputString) {

        String regex = "(?s)(.*?)\\s*Name:";  // Positive lookahead to match until the next '<<<' or end of string
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(inputString);

        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            return null;
        }
    }
 public static String generateRandomNumericPart(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // Generates a random digit (0-9)
        }
        return sb.toString();
    }

 
	// --- <<IS-END-SHARED>> ---
}

