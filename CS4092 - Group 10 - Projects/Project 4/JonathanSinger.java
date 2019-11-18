/*	Group 10 - Project 4
Brian Walsh 	13147811
Jonathan Singer 14136988
Ben Smith 		14160668
Sean Sinnott 	14161982	*/

//-----------------------------------IMPORTANT-----------------------------------
//I HAVE CHANGED THE MENU SLIGHTLY TO ACCOMODATE FOR THE SORTING PARTS OF PART 3
//SO JUST REPLACE THE MENU IN THE MAIN FILE WITH THIS ONE.
//-------------------------------------------------------------------------------
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
public class Project4
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
				String [] monthID = fileElements[0].split("/");
				aGameSale = new GameSale(Integer.parseInt(monthID[1]), Integer.parseInt(fileElements[1]), Integer.parseInt(fileElements[2]));
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
						case 1:	obtainSalesReportForGame();						break;
						case 2: obtainSalesReportForGenre();					break;
						case 3: obtainTotalSalesForEachGame(games, sales, 1);	break;
						case 4: obtainTotalSalesForEachGame(games, sales, 0);	break;
						case 5: obtainTotalSalesForEachGenre();					break;
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
			"\n3. Obtain the total sales report for each game in ascending sequence on game title." +
			"\n4. Obtain the total sales report for each game in descending sequence on game sales." +
			"\n5. Obtain the total sales report for each genre." +
			"\n0. Exit.";
		boolean validInput = false;
		String selection = "", menuChoicePattern = "[0-5]{1}";
		String errorMessage = "Invalid menu selection.";
			errorMessage += "\n\nValid options are 0 to 5 inclusive.";
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
	
	/*	Inputs:			(1)	games and sales ArrayLists passed in through method arguments.
						(2)	sortType determines if the games are sorted by title or units sold.
		Processes:		(1)	The passed-in ArrayLists are used to form a two-dimensional array containing
							only the information necessary for the report.
						(2) The two-dimensional array is bubble-sorted, depending on the value of sortType.
		Outputs:		The resulting report is displayed under headings "Game Title", "Units Sold" and "Price"
						in the command-line window.
		Created by:		Jonathan Singer
	*/
	public static void obtainTotalSalesForEachGame(ArrayList<GameDetail> games, ArrayList<GameSale> sales, int sortType)
	{	
		String outputFormat = "%-30s%-15s%-8s%s";
		String result = String.format(outputFormat, "Game Title", "Units Sold", "Price", "\n");
		String[][] totalGameSales = new String[games.size()][3];
		GameDetail aGame;
		GameDeveloper aDeveloper;
		GameGenre aGenre;
		GameSale aSale;
		String aGameTitle = "";
		int unitsSold, aGameID;
		double aGamePrice;
		for (int index = 0; index < games.size(); index++)
		{
			aGame = games.get(index);
			aGameTitle = aGame.getGameTitle();
			aGamePrice = aGame.getGamePrice();
			aGameID = aGame.getGameID();
			unitsSold = 0;
			for (int index2 = 0; index2 < sales.size(); index2++)
			{
				aSale = sales.get(index2);
				if (aSale.getGameID() == index+1)
					unitsSold = unitsSold + aSale.getSaleUnits();
			}
			totalGameSales[index][0] = aGameTitle;
			totalGameSales[index][1] = ""+unitsSold;
			totalGameSales[index][2] = ""+aGamePrice;
		}
		int pass, comparison;
		String [] temp = new String[4];
		boolean sorted = false;
		if (sortType == 0)
		{
			for (pass = 1; pass <= totalGameSales.length - 1 && !sorted; pass++)
			{
				sorted = true;
				for (comparison = 1; comparison <= totalGameSales.length - pass; comparison++)
				{
					if (Integer.parseInt(totalGameSales[comparison - 1][1]) < Integer.parseInt(totalGameSales[comparison][1]))
					{
						temp = totalGameSales[comparison - 1];
						totalGameSales[comparison - 1] = totalGameSales[comparison];
						totalGameSales[comparison] = temp;
						sorted = false;
					}
				}
			}
		}
		else
		{
			for (pass = 1; pass <= totalGameSales.length - 1 && !sorted; pass++)
			{
				sorted = true;
				for (comparison = 1; comparison <= totalGameSales.length - pass; comparison++)
				{
					if (totalGameSales[comparison - 1][0].compareTo(totalGameSales[comparison][0]) > 0)
					{
						temp = totalGameSales[comparison - 1];
						totalGameSales[comparison - 1] = totalGameSales[comparison];
						totalGameSales[comparison] = temp;
						sorted = false;
					}
				}
			}
		}
		for (int index = 0; index < totalGameSales.length; index ++)
			result += String.format(outputFormat, totalGameSales[index][0], totalGameSales[index][1], totalGameSales[index][2], "\n");
		System.out.print(result);
	}
	
	public static void obtainTotalSalesForEachGenre()
	{
	}
}