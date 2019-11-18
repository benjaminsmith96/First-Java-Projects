/*	Group 10 - Project 4
Brian Walsh 	13147811
Jonathan Singer 14136988
Ben Smith 		14160668
Sean Sinnott 	14161982	*/
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
public class GroupTenProjectFour
{
	public static void main(String [] args) throws IOException
	{
		ArrayList<GameGenre> genres = new ArrayList<GameGenre>();
		ArrayList<GameDeveloper> developers = new ArrayList<GameDeveloper>();
		ArrayList<GameDetail> games = new ArrayList<GameDetail>();
		ArrayList<GameSale> sales = new ArrayList<GameSale>();
		File aFile;
		Scanner in;
		String filename1 = "GameGenres.txt";
		String filename2 = "GameDevelopers.txt";
		String filename3 = "GamesDetails.txt";
		String filename4 = "GamesSales.txt";
		String lineFromFile;
		String fileElements [];
		GameGenre aGameGenre;
		GameDeveloper aGameDeveloper;
		GameDetail aGameDetail;
		GameSale aGameSale;
		
		aFile = new File(filename1);
		if (!aFile.exists())
			System.out.print(filename1 + " was not found");
		else
		{
			in = new Scanner(aFile);
			while(in.hasNext())
			{
				lineFromFile = in.nextLine();
				fileElements = lineFromFile.split(",");
				aGameGenre = new GameGenre(Integer.parseInt(fileElements[0]), fileElements[1]);		
				genres.add(aGameGenre);
			}		
			in.close();
		}
		aFile = new File(filename2);
		if (!aFile.exists())
			System.out.print(filename2 + " was not found");
		else
		{
			in = new Scanner(aFile);
			while(in.hasNext())
			{
				lineFromFile = in.nextLine();
				fileElements = lineFromFile.split(",");
				aGameDeveloper = new GameDeveloper(Integer.parseInt(fileElements[0]), fileElements[1]);		
				developers.add(aGameDeveloper);
			}		
			in.close();
		}  
		aFile = new File(filename3);
		if (!aFile.exists())
			System.out.print(filename3 + " was not found");
		else
		{
			in = new Scanner(aFile);
			while(in.hasNext())
			{
				lineFromFile = in.nextLine();
				fileElements = lineFromFile.split(",");
				aGameDetail = new GameDetail(Integer.parseInt(fileElements[0]), fileElements[1], Integer.parseInt(fileElements[2]), 
											Integer.parseInt(fileElements[3]), Double.parseDouble(fileElements[4]));		
				games.add(aGameDetail);
			}		
			in.close();
		}
		aFile = new File(filename4);
	    if (!aFile.exists())
	        System.out.print(filename4 + " was not found");
	   else
	   {
	      in = new Scanner(aFile);
	      while(in.hasNext())
	      {
	            lineFromFile = in.nextLine();
				fileElements = lineFromFile.split(",");
				String [] date = fileElements[0].split("/");
				String Month = date[1];
				aGameSale = new sales(Integer.parseInt(Month), Integer.parseInt(fileElements[1]), Integer.parseInt(fileElements[2]));		
				sales.add(aGameSale);
          }		
	       in.close();
	   }

		int choice;
		String input = "";
		String menuOption = "";
		while ((menuOption !=null) && (!(menuOption.equals("0"))))
		{
			menuOption = getMenuOption();
			if (menuOption !=null)
			{
				choice = Integer.parseInt(menuOption);
				if (choice !=0)
				{
					switch(choice)
					{
						case 1:	obtainSalesReportForGame();		break;
						case 2: obtainSalesReportForGenre();	break;
						case 3: obtainTotalSalesForEachGame();	break;
						case 4: obtainTotalSalesForEachGenre(sales,games,genres); break;
						case 0: break;
					}
				}
			}
		}
	}

 	public static String getMenuOption()
	{
		String menuOptions = "1. Obtain sales report for a game." +
			"\n2. Obtain sales report for a genre." +
			"\n3. Obtain the total sales report for each game." +
			"\n4. Obtain the total sales report for each genre." +
			"\n0. Exit.";
		boolean validInput = false;
		String selection = "", menuChoicePattern = "[0-4]{1}";
		String errorMessage = "Invalid menu selection.";
			errorMessage += "\n\nValid options are 0 to 4 inclusive.";
			errorMessage += "\nSelect OK to retry.";
		while (!(validInput))
		{
			selection = JOptionPane.showInputDialog(null, menuOptions, 
				"Choose number of option you wish to have executed", 3);
			if (selection == null || selection.matches(menuChoicePattern))
				validInput = true;
			else
				JOptionPane.showMessageDialog(null, errorMessage, 
					"Error in user input", 2);
		}
		return selection;
	}
	/*	Fill your code into the appropriate methods below,
		and upload to the drive with your file saved as your name.	*/
	public static void obtainSalesReportForGame()
	{
	}
	
	public static void obtainSalesReportForGenre()
	{
	}
	
	public static void obtainTotalSalesForEachGame()
	{	
		/*int aGameID = 		getGameID();
		String aGameTitle = getGameTitle();
		int aGenreID = 		getGenreID();
		int aDeveloperID = 	getDeveloperID();
		int aGamePrice = 	getGamePrice();*/
	}
	public static void obtainTotalSalesForEachGenre(ArrayList<GameSale> sales,ArrayList<GameDetail> details, ArrayList<GameGenre> genres)
	{
		ArrayList<report4> reportNum4 = new ArrayList<report4>();
		
		GameSale aSale;
		GameDetail aDetail;
		report4 aReport;
		int gID,gDID,unitSold,gameID,gameSalesID;	
		
		double gPrice;
		
		for(int i = 0; i < sales.size(); i++)
		{
			aSale = sales.get(i);
			gID = aSale.getGameID();
			unitSold = aSale.getSaleUnits();
	    }
	    for(int k = 0; k < genres.size(); k++)
	    {
		    int count = 0;
		    gID = genres.get(k).getGenreID();
		for(int j = 0; j < details.size(); j++)
	    {    
			aDetail = details.get(j);
			gDID = aDetail.getGenreID();
			gameID = aDetail.getGameID();
			
			if(gID == gDID)
		    {
			     
			for(int h = 0; h < sales.size(); h++)
			{
		     aSale = sales.get(h);
			 gameSalesID = aSale.getGameID();
			 unitSold = aSale.getSaleUnits();
			 if(gameID == gameSalesID)
			 {
				 
				 count+= unitSold;
			 }			 			 
			}
		    
			}
				
			//gPrice = aDetail.getGamePrice();
		}
		reportNum4.add(new report4(gID,0,count,0));
	   }
	   
	   for(int l = 0; l < reportNum4.size(); l++)
	   {
		   aReport = reportNum4.get(l);
		   System.out.println(aReport.getGenreID() + " " + aReport.getUnitsSold());
	   }
		
	}
}
	
	