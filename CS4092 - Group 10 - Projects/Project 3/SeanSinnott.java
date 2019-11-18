/*	CS4092 - Group 10 - Project 3
	Jonathan Singer 	14136988
	Ben Smith			14160668
	Sean Sinnott		14161982
	Brain Walsh			13147811 */
import java.util.*;
import java.io.*;
public class GroupTenProjectThree
{
/* 	READ THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	READ THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	When your method is called by the switch case it calls every thing in args, so it is up to you to display
	an error message if the wrong amount of command line arguments has been displayed.
	If you only have one method which does two questions just put your method in the relevant case numbers as I 
	have done with case 6 and case 7
*/	
	public static void main(String [] args) throws IOException
	{
		String errorMessageTwo = "Invalid number of command line arguments.";
		if(args.length > 1 && args.length < 6)
		{
			int num = 0;
			boolean found = false;
			String errorMessage = "Invalid command, please try again.";
			String command = args[0];
			String[] listOfC = {"AA", "EA", "DA", "EF", "DF", "SF", "SD"};
			command = command.toUpperCase();
			for(int a = 0; a < listOfC.length && found == false; a++)
			{
				if(command.equals(listOfC[a]))
				{
					found = true;
					num = a + 1;
				}
			}
			if(found == false)
			System.out.print(errorMessage);
			else
			{
				switch (num)
				{
					case 1: addNewAirport(args);					break;
					case 2: editNameOfAirport(args);				break;
					case 3: deleteAirportAndFlights(args);			break;
					case 4: editTravelDaysAndDates(args);			break;
					case 5: deleteFlight(args);						break;
					case 6: searchFlights(args);					break;
					case 7: searchFlights(args);					break;
					default: System.out.print(errorMessage);
				}
			}
		}
		else
		System.out.print(errorMessageTwo);
	}
	
	/*	This method will return true if the flight-code or airport-code exists.
		codeType = 0 will search for a flight-code.
		codeType = 1 will search for an airport-code.	
		e.g. boolean airportCodeExists = doesCodeExist("SNN", 1)
			will be true because the airport-code SNN exists in Airports.txt.	
		Created by:	Jonathan Singer	*/
	public static boolean doesCodeExist(String code, int codeType) throws IOException
	{
		boolean codeExists = false;
		FileReader fileReader;
		if (codeType == 1)
			fileReader = new FileReader("Airports.txt");
		else
			fileReader = new FileReader("Flights.txt");
		Scanner fileScanner = new Scanner(fileReader);
		String lineFromFile = "";
		while (codeExists == false && fileScanner.hasNextLine())
		{
			lineFromFile = fileScanner.nextLine();
			if (lineFromFile.contains(code))
				codeExists = true;
		}
		fileScanner.close();	fileReader.close();
		return codeExists;
	}
	
	/*	This method will return either the airport-name or airport-code relative to 'airportCodeOrName',
		depending on the value of 'type'.
		type = 0 will treat airportCodeOrName as the airport-code, and return the name of that airport.
		type = 1 will treat airportCodeOrName as the airport-name, and return the code of that airport.
		e.g.	getAirportNameOrCode("SNN", 0) will return "Shannon".
				getAirportNameOrCode("Shannon", 1) will return "SNN".
		Created by:	Jonathan Singer	*/
	public static String getAirportNameOrCode(String airportCodeOrName, int type) throws IOException
	{
		String airportNameOrCode = "";
		FileReader fileReader = new FileReader("Airports.txt");
		Scanner fileScanner = new Scanner(fileReader);
		String lineFromFile = "";
		String[] airports = lineFromFile.split(",");
		int from, to;
		if (type == 0)
		{
			from = 1;	to = 0;
		}
		else
		{
			from = 0;	to = 1;
		}
		while (airportNameOrCode.equals("") && fileScanner.hasNextLine())
		{
			lineFromFile = fileScanner.nextLine();
			airports = lineFromFile.split(",");
			if (airports[from].contains(airportCodeOrName))
				airportNameOrCode = airports[to];
		}
		fileScanner.close();	fileReader.close();
		return airportNameOrCode;
	}
	
	public static void addNewAirport(String [] args) throws IOException
	{
		ArrayList<String> flightDetails = new ArrayList<String>();
		File airportFile;
		Scanner scanFile;
		PrintWriter inputToFile;
		String lineFromFile = "";
		String airportName = args[1];
		String airportCode = args[2];
		String [] elements;
		boolean found = false;
		airportFile = new File("Airports.txt");
		if (args.length != 3)
			System.out.println("Only input three command-line arguments, e.g AA airportName airportCode");
		else
		{	
			if (args[2].length() < 3 || args[2].length() > 3)
				System.out.println("Airport code can only be three characters long");
			else
			{
				if (airportFile.exists())
				{
					scanFile = new Scanner(airportFile);
					while(scanFile.hasNext() && !found)
					{
						lineFromFile = scanFile.nextLine();
						elements = lineFromFile.split(",");
						if (elements[1].equalsIgnoreCase(airportCode))
							found = true;
						else
							flightDetails.add(lineFromFile);
					}
					scanFile.close();
					if (found)
						System.out.println("Airport already exists");
					else
					{
						flightDetails.add(airportName + "," + airportCode);	   
						Collections.sort(flightDetails);
						inputToFile = new PrintWriter(airportFile);
						for (int i = 0; i < flightDetails.size(); i++)
							inputToFile.println(flightDetails.get(i));
						inputToFile.close();
						System.out.println("Airport details have been added to file");
					}
				}
				else
					System.out.println("File does not exist");
			}
		}
	}
	
	public static void editNameOfAirport(String[] args) throws IOException
	{
		ArrayList<String> flightDetails = new ArrayList<String>();
		File airportFile;
		String newAirportName = args[2];
		String airportCode = args[1];
		airportFile = new File("Airports.txt");
		if (args.length != 3)
			System.out.println("Only input three command-line arguments, e.g EA airportCode newAirportName");
		else
		{
			if (args[1].length() < 3 || args[1].length() > 3)
				System.out.println("Airport code can only be three characters long");
			else
			{
				if (airportFile.exists())
				{
					Scanner inFile = new Scanner(airportFile);
					while (inFile.hasNext())
					{
						String aLineFromFile = inFile.nextLine();
						if (aLineFromFile.startsWith(airportCode));
							//flightDetails.add(aLineFromFile);
							flightDetails.add(newAirportName + "," + airportCode);
							Collections.sort(flightDetails);
					}
					inFile.close();
					PrintWriter outFile = new PrintWriter(airportFile);
					for (int i = 0; i < flightDetails.size(); i++)
						outFile.println(flightDetails.get(i));
					outFile.close();
					System.out.println("Airport name has been updated");
				}
				else
					System.out.println("File does not exist");
			}
		}	
	}
	
	public static void deleteAirportAndFlights(String[] args)
	{
	}
	
	public static void editTravelDaysAndDates(String[] args)
	{
	}
	
	public static void deleteFlight(String[] args)
	{
	}
	
	public static void searchFlights(String[] args)
	{
	}
}