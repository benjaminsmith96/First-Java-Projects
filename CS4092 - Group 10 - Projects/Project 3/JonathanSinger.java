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
	Created by:	Ben Smith	*/	
	public static void main(String [] args) throws IOException
	{
		String errorMessageTwo = "Invalid number of command line arguments.";
		String instructions = "Command must be in the following format:\n"+
			"Add Airport:\tGroupTenProjectThree AA airportName airportCode\n"+
			"Edit Airport:\tGroupTenProjectThree EA airportCode airportName\n"+
			"Delete Airport:\tGroupTenProjectThree DA airportCode\n"+
			"Edit Flight:\tGroupTenProjectThree EF flightCode weekDays startDate endDate\n"+
			"Delete Flight:\tGroupTenProjectThree DF flightCode\n"+
			"Search Flight:\tGroupTenProjectThree SF sourceAirportName destinationAirportName\n"+
			"Search Flight:\tGroupTenProjectThree SD sourceAirportName destinationAirportName startDate\n";
		if (args.length == 0)
			System.out.print(instructions);
		else if(args.length < 6)
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
	
	public static void addNewAirport(String[] args)
	{
	}
	
	public static void editNameOfAirport(String[] args)
	{
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
	
	/*	Inputs:		Commandline arguments as defined by the message returned if no arguments are given;
						sourceAirport, destinationAirport and (optional) departureDate.
		Processes:	Finds and formats the relative information in Flights.txt into a more readable/user-friendly state.
		Outputs:	Prints the flights, matching the criteria of the arguments, found in the Flights.txt file.
		Created by:	Jonathan Singer	*/
	public static void searchFlights(String[] args) throws IOException
	{
		String commandFormat = "Command must be in the following format:\n"
			+"GroupTenProjectThree SF sourceAirport destinationAirport\n"
			+"GroupTenProjectThree SD sourceAirport destinationAirport departureDate\n"
			+"sourceAirport:\t\tname of source airport e.g. Shannon\n"
			+"destinationAirport:\tname of destination airport\n"
			+"departureDate:\t\tdd/mm/yyyy\n";
		if (args.length < 3 || args.length > 4)
			System.out.print("Incorrect number of arguments.\n"+commandFormat);
		else
		{
			FileReader fileReader = new FileReader("Flights.txt");
			Scanner fileScanner = new Scanner(fileReader);
			String lineFromFile = "";
			String[] flights = lineFromFile.split(",");
			String sourceAirportFromFile = "";
			String destinationAirportFromFile = "";
			String departureDateFromFile = "";
			String sourceAirport = getAirportNameOrCode(args[1], 1);
			String destinationAirport = getAirportNameOrCode(args[2], 1);
			String departureDate = "";
			if (args[0].equals("SD"))
				departureDate = args[3];
			String outputFormat = "%-16s%-24s%-24s%-19s%-17s%-16s%-15s%-15s";
			String matchingFlight = "";
			String header = String.format(outputFormat, "Flight Code", "Source Airport", "Destination Airport", 
							"Departure Time", "Arrival Time", "Flight Days", "Start Date", "End Date");
			boolean matchFound = false;
			while (fileScanner.hasNextLine())
			{
				lineFromFile = fileScanner.nextLine();
				flights = lineFromFile.split(",");
				sourceAirportFromFile = flights[1];
				destinationAirportFromFile = flights[2];
				departureDateFromFile = flights[6];
				matchingFlight = String.format(outputFormat, flights[0], getAirportNameOrCode(flights[1], 0), getAirportNameOrCode(flights[2], 0), 
					flights[3].substring(0,2)+":"+flights[3].substring(2), flights[4].substring(0,2)+":"+flights[4].substring(2), 
					flights[5], flights[6], flights[7]);
				if (sourceAirport.equals(sourceAirportFromFile) && destinationAirport.equals(destinationAirportFromFile))
				{
					if (args.length == 4 && departureDate.equals(departureDateFromFile))
					{
						if (matchFound == false)
						{
							System.out.println(header);
							matchFound = true;
						}
						System.out.println(matchingFlight);
					}
					else if (args.length == 3)
					{
						if (matchFound == false)
						{
							System.out.println(header);
							matchFound = true;
						}
						System.out.println(matchingFlight);
					}
				}
			}
			fileScanner.close();	fileReader.close();
			if (matchFound == false)
				System.out.println("No flights were found.");
			if (!doesCodeExist(args[1], 1))
				System.out.println(args[1]+" is not a valid airport.");
			if (!doesCodeExist(args[2], 1))
				System.out.println(args[2]+" is not a valid airport.");
		}
	}
}