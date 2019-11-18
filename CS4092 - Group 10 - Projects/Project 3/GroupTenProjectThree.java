/*	CS4092 - Group 10 - Project 3
	Jonathan Singer 	14136988
	Ben Smith			14160668
	Sean Sinnott		14161982
	Brian Walsh			13147811 */
import java.util.*;
import java.io.*;
import java.text.*;
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
		String commandFormat = "Command must be in the following format:\n"
			+"GroupTenProjectThree AA airportName airportCode\n"
			+"GroupTenProjectThree EA airportCode airportName\n"
			+"GroupTenProjectThree DA airportCode\n"
			+"GroupTenProjectThree EF FlightCode newDays newStartDate newEndDate\n"
			+"GroupTenProjectThree DF FlightCode\n"
            +"GroupTenProjectThree SF sourceAirport destinationAirport\n"
            +"GroupTenProjectThree SD sourceAirport destinationAirport departureDate\n"
            +"sourceAirport:\t\tairport-code of source e.g. SNN\n"
            +"destinationAirport:\tairport-code of destination\n"
            +"departureDate:\t\tdd/mm/yyyy\n";
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
			System.out.println(errorMessage);
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
					default: System.out.println(errorMessage);
				}
			}
		}
		else
		System.out.println(errorMessageTwo+"\n"+commandFormat);
	}
	/*    This method will return true if the flight-code or airport-code exists.
        codeType = 0 will search for a flight-code.
        codeType = 1 will search for an airport-code.    
        e.g. boolean airportCodeExists = doesCodeExist("SNN", 1)
            will be true because the airport-code SNN exists in Airports.txt.    
        Created by:    Jonathan Singer    */
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
        fileScanner.close();    fileReader.close();
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
	
	public static void addNewAirport(String[] args) throws IOException
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
	
	public static void deleteAirportAndFlights(String[] args)throws IOException 
	{
		 File Airports = new File("Airports.txt");        
         ArrayList<String> FileWithRemovedAirport = new ArrayList<String>();       
         
         boolean Found = false;
         
         if(args.length > 2 || args.length < 2)
         System.out.println("There must be 2 command line arguments only");
         if(args[1].length() > 3)
         System.out.println("Airport code can comprise of 3 alphabetical characters only");
         
         else
         {
         
         if(Airports.exists())
         {  	         
	       Scanner in = new Scanner(Airports);  
           String currentLine;                                                                                    
           while(in.hasNext())
            {                                                                           
	              currentLine = in.nextLine();
		          String [] lineElements;
		          lineElements = currentLine.split(",");
		          
		          if(!args[1].equals(lineElements[1]))
		          
		             FileWithRemovedAirport.add(currentLine);
	              
		          if(args[1].equals(lineElements[1]))
		          {	            
		            System.out.println("Airport with code " + args[1] + " was removed from Airports");
		            Found = true;
	              }              		                             		       	                    		                                                                   
            }
            
            in.close();
           
            if(!Found)
            System.out.println("This airport does not exist in Airports.txt");
            else
            {
	        deleteFlight(args);    
            PrintWriter outFile = new PrintWriter(Airports);
            for(int i = 0; i < FileWithRemovedAirport.size(); i++)
              outFile.println(FileWithRemovedAirport.get(i));
              outFile.close();     
            }                              
         }
       else
       System.out.println("No Airports Exist at this time");
         }
	}
	/*	Inputs:		Command-line arguments as defined by the message returned if no arguments are given;
						FlightCode NewDays newStartDate newEndDate.
		Processes:	Validates command-line arguments such as the days and dates, and then edits the days and 
						dates for the specified flight in Flights.txt. 
		Outputs:	Providing the command-line arguments are valid the new flight information is put into Flights.txt.
		Created by:	Ben Smith	*/
	public static void editTravelDaysAndDates(String[] args) throws IOException
	{
		if(args.length == 5)
		{
			String flightCode = args[1];
			String newDays = args[2];
			String newStartDate = args[3];
			String newEndDate = args[4];
			if(newDays.length() == 7)
			{
				String [] days = {"M","T","W","T","F","S","S"};
				boolean validInput = true;
				String aCharachter;
				newDays = newDays.toUpperCase();
				for(int i=0; i < days.length && validInput;i++)
				{
					aCharachter = newDays.substring(i, i + 1);
					if(days[i].contains(aCharachter))
					validInput = true;
					else if(aCharachter.contains("-"))
					validInput = true;
					else
					validInput = false;
				}
				if(validInput == true)
				{
					String pattern = "[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}";
					String flightFileLine = "";
					String [] flightFileArray;
					boolean validStartDate = false, validEndDate = false;
					if(newStartDate.matches(pattern))
					validStartDate = true;
					else
					System.out.println("Invalid start date");
					if(newEndDate.matches(pattern))
					validEndDate = true;
					else
					System.out.println("Invalid end date");
					if(validStartDate && validEndDate)
					{
						validStartDate = validEndDate = false;
						if (isValidDate(newStartDate) == true)
						validStartDate = true;
						else
						System.out.println(newStartDate + " is not a valid date.");
						if (isValidDate(newEndDate) == true)
						validEndDate = true;
						else
						System.out.println(newEndDate + " is not a valid date.");
					}
					if(biggerDate(newStartDate, newEndDate) == true)
					{
						if(doesCodeExist(flightCode, 0) == true)
						{
							Scanner s = new Scanner(new File("Flights.txt"));
							ArrayList<String> list = new ArrayList<String>();
							while (s.hasNext())
							{
								flightFileLine = s.nextLine();
								if(flightFileLine.contains(flightCode))
								{
									flightFileArray = flightFileLine.split(",");
									flightFileArray[5] = newDays;
									flightFileArray[6] = newStartDate;
									flightFileArray[7] = newEndDate;
									list.add(flightFileArray[0]+","+flightFileArray[1]+","+flightFileArray[2]+","+flightFileArray[3]+","+flightFileArray[4]+","+flightFileArray[5]+","+flightFileArray[6]+","+flightFileArray[7]);
								}
								else
								list.add(flightFileLine);
							}
							s.close();
							PrintWriter toFile = new PrintWriter(new FileWriter("Flights.txt"));
							for(int j=0; j < list.size(); j++)
							{
								toFile.println(list.get(j));
							}
							toFile.close();
						}
						else
						System.out.println(flightCode + " does not exist.");
					}
					else
					System.out.println(newStartDate + " and " + newEndDate + " are not valid dates.");
				}
				else
				System.out.println("Days input is invalid");
			}
			else
			System.out.println("Number of days are invalid");
		}
		else
		System.out.println("Invalid number of command line arguments.");
	}
	
	/*	Inputs:		A date is brought in through the command-line arguments.
		Processes:	Validates the date, checking that the number of days are right etc... 
		Outputs:	It returns true or false depending on whether the date is valid or not.
		Created by:	Ben Smith	*/	
	public static boolean isValidDate(String userInput)
	{
		GregorianCalendar aCalendar = new GregorianCalendar();
		String dateParts = aCalendar.get(Calendar.DATE) + "/" + ((aCalendar.get(Calendar.MONTH)) + 1) + "/" + aCalendar.get(Calendar.YEAR);
		int positionFirstSlash, positionLastSlash, ddInt, mmInt, yyInt;
		int [] daysArray = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		boolean dateIsValid = true;
		positionFirstSlash = userInput.indexOf("/");
		positionLastSlash = userInput.lastIndexOf("/");
		ddInt = Integer.parseInt(userInput.substring(0, positionFirstSlash));
		mmInt = Integer.parseInt(userInput.substring(positionFirstSlash + 1, positionLastSlash));
		yyInt = Integer.parseInt(userInput.substring(positionLastSlash + 1));
		if ((ddInt == 0) || (mmInt == 0) || (yyInt == 0))
		dateIsValid = false;
		else if (mmInt > 12)
		dateIsValid = false;
		else if ((ddInt == 29) && (mmInt == 2) && ((((yyInt % 4 == 0) && (yyInt % 100 !=0)) || (yyInt % 400 == 0))))
		dateIsValid = true;
		else if (ddInt > daysArray[mmInt - 1])
		dateIsValid = false;
		if(dateIsValid = true)
		{
			dateIsValid = biggerDate(dateParts, userInput);
		}
		return dateIsValid;
	}
	
	/*	Inputs:		Two dates are brought in via the methods: editTravelDaysAndDates and isValidDate.
		Processes:	They are converted to Date format and are compared to see if one date is bigger than the other.
		Outputs:	Depending on whether one date is bigger, smaller or the same size the method returns true or false.
		Created by:	Ben Smith	*/
	public static boolean biggerDate(String startDate, String endDate) 
	{
		boolean startDateIsBigger = false;
		try
		{
			DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			Date firstDate = dateFormatter.parse(startDate);
			Date secondDate = dateFormatter.parse(endDate);
			if(firstDate.compareTo(secondDate) < 0)
			startDateIsBigger = true; 
			else if(firstDate.compareTo(secondDate) == 0)
			startDateIsBigger = true;
			else
			startDateIsBigger = false;
		}
		catch(ParseException pe)
		{
			System.out.println("Unable to format one or more dates");
		}
		return startDateIsBigger;
	}
	
	public static void deleteFlight(String[] args)throws IOException
	{
	   boolean found = false;
	   File Flights = new File("Flights.txt");
       ArrayList<String> FileWithRemovedFlight = new ArrayList<String>();
       
       if(args.length > 2 || args.length < 2)
         System.out.println("There must be 2 command line arguments only");
       if(args[1].length() > 3)
         System.out.println("Airport code can comprise of 3 alphabetical characters only");
         else
         {
       if(Flights.exists())
       {        
	       
	       Scanner inFile = new Scanner(Flights);  
           String lineFromFile;                                                                                    
           while(inFile.hasNext())
            {     
	                                                                               
	              lineFromFile = inFile.nextLine();
		          String [] lineElementsFlights;
		          lineElementsFlights = lineFromFile.split(",");
		          if((!args[1].equals(lineElementsFlights[1])) && (!args[1].equals(lineElementsFlights[2])))			         
			         FileWithRemovedFlight.add(lineFromFile);
			      if((args[1].equals(lineElementsFlights[1])) || (args[1].equals(lineElementsFlights[2])))
			      { 
			         System.out.println("Airport with code " + args[1] + " was removed from Flights");
			         found = true;
		          }
			                                       
                      
            }
            inFile.close();
            if(!found)
            System.out.println("This airport does not exist in Flights");
            else
            {
            PrintWriter outFileFlight = new PrintWriter(Flights);
            for(int i = 0; i < FileWithRemovedFlight.size(); i++)
              outFileFlight.println(FileWithRemovedFlight.get(i));
              outFileFlight.close();
            }
                  
         }
        else 
        System.out.println("No Flights currently exist");
        }
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