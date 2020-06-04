import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Results
{
	public static void main (String[] args)
	{
		BufferedReader reader = null;
		
		//Give an output for a user who hasn't supplied an argument:
		if (args.length == 0)
		{
			System.out.println("No filename specified");
			System.exit(1);
		}
		
		//Give a message if there is an fnfe error:
		try
		{
			reader = new BufferedReader(new FileReader(args[0]));
		}
			catch (FileNotFoundException fnfe)
		{
			System.out.println("Error on opening file data");
			System.exit(2);
		}
		
		String inputLine="";
		String[][] resultsArray = new String[60][3];
		int lineCount = 0;
		
		// Do stuff until we reach the end of our document:
		while (inputLine != null)
		{
			try
			{
					// Our last iteration was not null so we scan the line after that i.e. the line
					// corresponding to our current iteration (it may be null):
					inputLine = reader.readLine();
					
			}
			
			catch (IOException ioe)
			{
					System.out.println("Error on opening file data");
					System.exit(3);
			}
			// We check that we are not at null (i.e end of doc on our current iteration:
			if (inputLine != null)
			{
				// if we are not at null we do something with the current scanned line:
				if (inputLine.length() > 0)
					{
						StringTokenizer tokenizer = new StringTokenizer(inputLine);
						int i = 0;
						while(tokenizer.hasMoreTokens())
						{
							resultsArray[lineCount][i] = tokenizer.nextToken();
							i++;
						}
						lineCount++;
					}
			}
		}
		
		if (lineCount == 0)
		System.out.println("This document is empty!");
		else
		{
			bubble(resultsArray, lineCount);
			System.out.println("Name" + "       " + "Mark");
			for (int i = 0; i < lineCount; i++)
			{
				
				String formattedString = String.format("%-10s %3s ", resultsArray[i][1] + " " + resultsArray[i][0], resultsArray[i][2]);
				System.out.println(formattedString);
			}
			
		}
		
	}
	
	
	public static void bubble (String[][] marks, int lineCount)
	{
		int i, j; 
		String[] temp;
		// i is used to take first score and go forward
		// j is used to take last score and go backwards
		for ( i = 0; i < lineCount - 1; i++)
		{
			for ( j = lineCount - 1; j > i; j--)
			{
				if(marks[j][0].compareToIgnoreCase(marks[j - 1][0]) < 0
					|| (marks[j][0].compareToIgnoreCase(marks[j - 1][0]) == 0 && marks[j][1].compareToIgnoreCase(marks[j - 1][1]) < 0 )
					)
				{
					// save the line with lower number:
					temp = marks[j];
					// Replace the line with lower number, with the line with higher number:
					marks[j] = marks[j-1];
					// Replace the line with higher number, with the saved line with lower number:
					marks[j-1] = temp;
				}
			}
		}
	}
	
	
} 