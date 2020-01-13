package ms3CodeChal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
//I couldn't figure out how the Libraries work to let me connect to SQLite
//import java.sql.*;




//Grabs Data from csv file and places it into a list. 
public class GrabData {
	public static void main(String[] args) throws IOException {
	//Variables
	Scanner input = new Scanner(System.in);

	//Enter the csv file path here.
	String csvFile = "Entry Level Coding Challenge Page 2.csv";
	System.out.print(csvFile);
	//Regular Expression that finds Commas and Ignores Commas between Quotes
	String csvSplitBy = ",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
	ArrayList<String[]> fileData = new ArrayList<>(); 
	int successfulRecords = 0;
	int failedRecords = 0;
	int totalRecords = 0;

	//reads the csv file and places records in ArrayList<String[]> fileData
	try(BufferedReader br = new BufferedReader(new FileReader(csvFile)))
	{
		String line = "";
		while((line = br.readLine()) != null)
		{
			fileData.add(line.split(csvSplitBy));
		}
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	
	//Records the Total amount of Records Received.
	totalRecords = fileData.size();
	//Prompts User for database Name.
	System.out.print("Enter Database Name: ");
	//Takes input from the user and saves it to a string.
	String mystring = input.next() +".db\r\n";
	//Lets User Know what database they Entered. 
	System.out.print("Using Database: " + mystring);
	//Create File for Invalid Records
	System.out.println("\r\nEnter Error File Name: ");
	String efilename =   "tmp"+ File.separator+input.next() + ".csv";
	FileWriter csvWriter = new FileWriter(efilename);
	//Create a Log File
	System.out.println("Enter Log File Name: ");
	String lfilename = "tmp" +File.separator+ input.next() + ".log";
	FileWriter logWriter = new FileWriter(lfilename);
	//Create Database and Table
	//Code for Creating the Table that is incomplete. 
	/*System.out.println("CREATE TABLE "+mystring+".csvdata (\r\n" + 
			fileData.get(0)[0] +"  TEXT,\r\n" + 
			fileData.get(0)[1] +"  TEXT,\r\n" + 
			fileData.get(0)[2] +"  TEXT,\r\n" + 
			fileData.get(0)[3] +"  TEXT,\r\n" + 
			fileData.get(0)[4] +"  TEXT,\r\n" + 
			fileData.get(0)[5] +"  TEXT,\r\n" + 
			fileData.get(0)[6] +"  TEXT,\r\n" + 
			fileData.get(0)[7] +"  TEXT,\r\n" + 
			fileData.get(0)[8] +"  TEXT,\r\n" + 
			fileData.get(0)[9] +"  TEXT\r\n" + 
			");");*/
	//Records Failures and Successes
	for(int i = 1;i <totalRecords;i++)
	{
		if(isValid(fileData.get(i)))
				{
				successfulRecords++;
				printRecord(fileData.get(i));
				}else
				{
					failedRecords++;
					recordError(fileData.get(i),csvWriter);
				}
	}
	
	//Write Statistics to Log file
	logWriter.append("Log File\n");
	logWriter.append("Total Records: "+ totalRecords + "\n");
	logWriter.append("Successful Records: "+successfulRecords+ "\n");
	logWriter.append("Failed Records: "+ failedRecords+ "\n");
	logWriter.close();
	csvWriter.close();
	input.close();
	}
	
//Determines if Rows have the correct amount of columns.
public static boolean isValid(String[] array)
{
	boolean validity = true;
	
	for(int i = 0;i < array.length;i++)
	if(array[i].isEmpty())
		validity = false;
	
	return validity;
}
public static void printRecord(String[] array)
{
	/*Code for Entering SQLite Records. */
}
//This places Records into a chosen csv file.
public static void recordError(String[] array, FileWriter file) throws IOException
{
	for(int i = 0;i < array.length;i++)
	{
		file.append(array[i]);
		file.append(",");
	}
	file.append("\r\n");
}
}
