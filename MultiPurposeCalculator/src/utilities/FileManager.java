package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import GUI.PanelGraphics;


public class FileManager 
{
	private static ArrayList<File> files = new ArrayList<File>();
	private static final int CIRCLE_3_NUMBER_OF_AREA = 8;
	private static final int CIRCLE_2_NUMBER_OF_AREA = 4;
	
	public static void initializeFiles()
	{
		StringBuilder dirPath = new StringBuilder();
		File tempFile = new File("tempFile");
		
		dirPath = dirPath.append(tempFile.getAbsolutePath());
		dirPath.delete(dirPath.length()-8, dirPath.length());
		
		File circleAreaPositions = new File(dirPath+"CircleAreaPositions");
		circleAreaPositions.mkdir();
		tempFile.delete();
		
		
		for(int i = 0; i < CIRCLE_3_NUMBER_OF_AREA; i++)
		{
			File areaPositions = new File("circle3areaPositions"+(i+1));
			
			files.add(areaPositions);
			
			 try 
			 {
			   if (areaPositions.createNewFile()) 
			   {
				   System.out.println("File created: " + areaPositions.getName());
			   } 
			   else
			   {
				   System.out.println("File: " + areaPositions.getName()+" already exists");
			   }
			 } 
			 catch (IOException e) 
			 {
			   System.out.println("An error occurred.");
			   e.printStackTrace();
			 }
		}
		
		for(int i = 0; i < CIRCLE_2_NUMBER_OF_AREA; i++)
		{
			File areaPositions = new File("circle2areaPositions"+(i+1));
			files.add(areaPositions);
			
			 try 
			 {
			   if (areaPositions.createNewFile()) 
			   {
			     System.out.println("File created: " + areaPositions.getName());
			   } 
			 } 
			 catch (IOException e) 
			 {
			   System.out.println("An error occurred.");
			   e.printStackTrace();
			 }
		}
		
	}
	
	public static ArrayList<PanelGraphics.Point> readFile(String fileName) 
	{
		ArrayList<PanelGraphics.Point> points = new ArrayList<PanelGraphics.Point>();
		StringBuilder dirPath = new StringBuilder();
		
		File tempFile = new File("tempFile");	
		dirPath = dirPath.append(tempFile.getAbsolutePath());
		dirPath.delete(dirPath.length()-8, dirPath.length());
		dirPath = dirPath.append("CircleAreaPositions");
		
		File circleAreaPositions = new File(dirPath.toString());
		circleAreaPositions.mkdir();
		tempFile.delete();
		
		File file = new File(dirPath+"\\"+fileName);

		try 
		{
			Scanner myReader = new Scanner(file);
					
			while (myReader.hasNextLine()) 
			{
				String xPos = "";
				String yPos = "";
				boolean isXPosition = true;
				String data = myReader.nextLine();
						
				for(char c : data.toCharArray())
				{
					if(c == ',')
					{
						isXPosition = false;
						continue;
					}
					else if(c == ';')
					{
						PanelGraphics.Point point = 
								new PanelGraphics.Point(Integer.valueOf(xPos), Integer.valueOf(yPos));
						points.add(point);
						xPos = "";
						yPos = "";
						isXPosition = true;
						continue;
					}
							
					if(isXPosition)
					{
						xPos = xPos.concat(Character.toString(c));
					}
					else
					{
						yPos = yPos.concat(Character.toString(c));
					}
				}
			}		
			myReader.close();
		} 
		catch(FileNotFoundException e) 
		{
			System.out.println("File Manager - Could not Open file "+fileName);
			e.printStackTrace();
		}
		
		return points;
	}
	
	public static void writeFile(ArrayList<PanelGraphics.Point> areaPos, String fileName) 
	{
	    try 
	    {
	      FileWriter myWriter = new FileWriter(fileName);
	      
	      for(PanelGraphics.Point point : areaPos)
	      {
	    	  myWriter.write(point.x +","+point.y+";");
	      }
	      
	      System.out.println("Successfully wrote to the file!");
	      myWriter.close();
	    } 
	    catch (IOException e) 
	    {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}
}
