/* Brain Walsh  13147811 */
import java.util.*;
import java.io.*;
public class BrianWalsh
{	
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
	
	public static void addNewAirport(String[] args)
	{
	}
	
	public static void editNameOfAirport(String[] args)
	{
	}
	
	public static void deleteAirportAndFlights(String[] args)throws IOException /* Brain Walsh  13147811 */
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
	
	public static void editTravelDaysAndDates(String[] args)
	{
	}
	
	public static void deleteFlight(String[] args)throws IOException /* Brain Walsh  13147811 */
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
	
	public static void searchFlights(String[] args)
	{
	}
}