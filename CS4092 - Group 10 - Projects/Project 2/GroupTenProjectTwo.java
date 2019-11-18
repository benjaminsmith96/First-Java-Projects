/*	CS4092 - Group 10 - Project 2
	Jonathan Singer		14136988
	Ben Smith			14160668
	Sean Sinnott		14161982
	Brian Walsh			13147811 */

/*	List of files that should be created:
		AllDivisionsTournamentsLeagues.txt
			Structure:	TournamentLeagueDivisionNumber,TournamentLeagueDivisionName,WinPoints,DrawPoints,LossPoints
			Samples:	1,The Premiership,3,1,0
						2,The Championship,3,1,-3
						3,The Premiership,2,1,0
		TournamentLeagueDivisionTeams.txt (eg. ThePremiership1Teams.txt)
			Structure:	TeamNumber,TeamName
			Samples:	1,Arsenal
						2,Manchester United
						3,Manchester City
						4,Liverpool
		TournamentLeagueDivisionFixtures.txt (eg. TheChampionship2Fixtures.txt)
			Structure:	FixtureNumber,HomeTeamNumber,AwayTeamNumber
			Samples:	1,1,2
						2,3,4
						3,6,5
		TournamentLeagueDivisionResults.txt (eg. ThePremiership3Results.txt)
			Structure:	FixtureNumber,ScoreForHomeTeam,ScoreForAwayTeam
			Samples:	1,2,2
						2,2,3
						3,1,0	*/

import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
public class GroupTenProjectTwo
{
	/*	Inputs:		(1) Strings for menu options, handled by getMenuOption().
					(2) Menu choices (user input), handled by getMenuOption().
		Processes:	Using  while loops and case-switches, this method keeps the user inside of the menu
						until they either enter 0 or click cancel on the main menu.
		Outputs:	User's desired method commences.
		Edited by:	Jonathan Singer	*/
	public static void main(String [] args) throws IOException
	{
		int choice;
		String menuOption = "";
		while ((menuOption !=null) && (!(menuOption.equals("0"))))
		{
			menuOption = getMenuOption(false);
			if (menuOption !=null)
			{
				choice = Integer.parseInt(menuOption);
				if (choice !=0)
				{
					switch(choice)
					{
						case 1:	createNewLeague();		break;	
						case 2:
							while ((menuOption !=null) && (!(menuOption.equals("0"))))
							{
								menuOption = getMenuOption(true);
								if (menuOption !=null)
								{
									choice = Integer.parseInt(menuOption);
									if (choice !=0)
									{
										switch(choice)
										{
											case 1:	viewOutcomeOfPlayedFixtures();	break;	
											case 2:	enterFixtureOutcome();			break;
											case 3:	viewFutureFixtures();			break;
											case 4:	viewLeaderBoard();				break;
										}
									}
								}
							}
							menuOption = "";
						break;
					}
				}
			}
		}
	}

	/*	Inputs:		(1) Boolean to decide which menu will be displayed.
					(2) Number to select menu item (user input). Error messages are displayed if invalid input is given.
		Processes:	subMenu decides if the main menu or the sub-menu is to be displayed.
		Outputs:	String containing the information below (including error messages) depending on which menu is called.
					Menu:		(1)	Create new league/division/tournament.
								(2)	Load existing league/division/tournament.
					Sub-Menu:	(1) View a list of fixtures played to date and outcome of these fixtures. (This list should
									show the name of each team/player involved in a fixture along with the outcome.)
								(2) Enter the outcome for a fixture. (Should present the name of 
									each team/player involved in the fixture and request the actual score.)
								(3) View a list of the fixtures yet to be played. (Should show the name 
									of each team/player that will be involved in the fixture.)
								(4) View an up-to-date leader-board for a league/division/tournament.
		Edited by:	Jonathan Singer */
 	public static String getMenuOption(boolean subMenu)
	{
		String menuOptions, menuChoicePattern, selection = "";
		String errorMessage = "Invalid menu selection.";
		if (subMenu == true)
		{
			menuOptions = "1. View a list of fixtures with outcomes played to date." +
				"\n2. Enter the outcome for a fixture." +
				"\n3. View a list of the fixtures yet to be played." +
				"\n4. View an up-to-date leader-board." +
				"\n0. Back.";
			menuChoicePattern = "[0-4]{1}";
			errorMessage += "\n\nValid options are 0 to 4 inclusive." +
				"\nSelect OK to retry.";
		}
		else
		{
			menuOptions = "1. Start a new division/tournament/league." +
				"\n2. Access an existing division/tournament/league." +
				"\n0. Exit.";
			menuChoicePattern = "[0-2]{1}";
			errorMessage += "\n\nValid options are 0 to 2 inclusive." +
				"\nSelect OK to retry.";
		}
		boolean validInput = false;
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
	
	/*	Method to handle general user input. Allows alphabetic characters, numbers and symbols.
		Error displayed only if input is empty.	Null exception handled by returning an empty string which
			can then be handled by the parent method in order to return to the menu with no errors.
			Example:	String test = getInputFromEndUser("Enter a non-empty string.", "Input test.");
						if (test.equals(""))
							return;
						else
							//rest of code...
		Creator:	Jonathan Singer	*/
	public static String getInputFromEndUser(String windowMessage, String windowTitle)
	{
		String userInput = "";
		String errorMessage = "No input provided." + "\n\nSelect OK to retry.";
		boolean validInput = false;
		while (validInput == false)
		{
			userInput = JOptionPane.showInputDialog(null, windowMessage, windowTitle, 3);
			if (userInput == null)
				return "";
			if (userInput.equals(""))
				JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 2);
			else
				validInput = true;
		}
		return userInput;
	}

	/*	Inputs:		(1)	File containing teams/players.
					(2) Number of the team whose name you want.
					(3) Name of the league/division/tournament that contains the team.
		Processes:	While loop goes takes the teamNumber and goes to the next line of the teams/players file until it reaches that number,
					taking the team name (by splitting the line into an array) from that line as the return value.
		Outputs:	Returns a string containing the name of the team based on the team number in the teams/players file.
		Creator:	Jonathan Singer	*/	
	public static String getTeamName(int teamNumber, String leagueName) throws IOException
	{
		String teamName = "";
		FileReader teamsReader = new FileReader(leagueName+"TeamsOrPlayers.txt");
		Scanner teamsScanner = new Scanner(teamsReader);
		String lineFromTeams = "";
		String[] teams = lineFromTeams.split(",");
		while (teamName.equals("") && teamsScanner.hasNextLine())
		{
			lineFromTeams = teamsScanner.nextLine();
			teams = lineFromTeams.split(",");
			if (teamNumber == Integer.parseInt(teams[0]))
				teamName = teams[1];
		}
		teamsScanner.close();		teamsReader.close();
		return teamName;
	}

	/*	Inputs: 	(1) Name of league/division/tournament.
					(2) Number of participating teams/players (>= 4 <= 32).
					(3) Choose if fixtures are played once or home and away.
					(4) Point to be awarded to a team/player based on the outcome of a fixture (for each 
						of win, draw, and loss).
					(5) Name of each team/player (in an array).
		Processes:	(1) Generate file containing names of all leagues/divisions/tournaments if it doesn't already exist.
					(2)	Generate fixtures from inputs using generateFixtures(), and append its name to the league-list file.
		Outputs: 	(1)	File containing names of all leagues/division/tournaments.
					(2) File containing fixtures using generateFixtures().
		Creator:	Sean Sinnott	*/
	public static void createNewLeague() throws IOException
	{
		FileWriter aFile = new FileWriter("AllDivisionsTournamentsLeagues.txt", true);
		PrintWriter out = new PrintWriter(aFile);
		String tournamentName ="", winPoints = "", drawPoints = "", lossPoints = "", frequencyOfHomeGames = "", frequencyOfAwayGames = "", results = "";
		String alphabeticPattern = "([a-zA-Z]+)|(([a-zA-Z]+\\s[a-zA-Z]+)+)";
		String numericPattern = "([0-9]{1,2}+)";
		int teamAmount = 0;
		ArrayList<String> teamNames = new ArrayList<String>();
		boolean validInput = false;
		boolean number = false;
		boolean j = false;
		String 	errorMessage = "Invalid input.";
				errorMessage += "\nOnly input alphabetic characters for tournament/team names";
				errorMessage += "\nOnly input numeric characters for number of teams and points for a win/draw/loss";
				errorMessage += "\nSelect OK to retry";
		
		while (!(validInput))
		{
			tournamentName = JOptionPane.showInputDialog(null,"Enter name of League/Tournament/Division", "createNewLeague", 1);
			if (tournamentName.matches(alphabeticPattern))
			{	
				while(!(number))	
				{
					number = true;
					try	
					{
						teamAmount = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter number of teams or players", "createNewLeague", 1));
					}
					catch (NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);
						number = false;
					}
				}
				if (Integer.toString(teamAmount).matches(numericPattern))
				{
					String aTeamName = JOptionPane.showInputDialog(null,"Enter name of team", "createNewLeague", 1);
					if (aTeamName.matches(alphabeticPattern))
					{
						while (j)
						{
							for (int i = 0; i < teamAmount;)
							{
								if (!(teamNames.contains(aTeamName)))
								{
									teamNames.add(aTeamName);
									i++;
								}
								else
									j =true;
								System.out.println(teamAmount+" : "+ i);
							}
						}	
						winPoints = JOptionPane.showInputDialog(null,"Enter number of points for a win", "createNewLeague", 1);
						if (winPoints.matches(numericPattern))
						{
							drawPoints = JOptionPane.showInputDialog(null,"Enter number of points for a draw", "createNewLeague", 1);
							if (drawPoints.matches(numericPattern))
							{
								lossPoints = JOptionPane.showInputDialog(null,"Enter number of points for a loss", "createNewLeague", 1);
								if (lossPoints.matches(numericPattern))
								{
									frequencyOfHomeGames = JOptionPane.showInputDialog(null,"Enter number of times a team will play at home", "createNewLeague", 1);
									if (frequencyOfHomeGames.matches(numericPattern))
									{
										frequencyOfAwayGames = JOptionPane.showInputDialog(null,"Enter number of times a team will play away", "createNewLeague", 1);
										if (frequencyOfHomeGames.matches(numericPattern))
											validInput = true;
										else
											JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);
									}
									else
										JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);
								}
								else
									JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);
							}
							else
								JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);
						}
						else
							JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);
					}
					else
						JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);
				}
				else
					JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);	
			}
			else
				JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);
		}
		results ="\n" + "Name of Tournament: \t" + tournamentName + "\n" + "Amount of Teams: \t" + teamAmount + "\n" + "Names of Teams: \t" + teamNames + 
				"\n" + "Points for a Win: \t" + winPoints + "\n" + "Points for a Draw: \t" + drawPoints + "\n" + "Points for a Loss: \t" + lossPoints +
				"\n" + "Number of games a team will play at home: \t" + frequencyOfHomeGames +
				"\n" + "Number of games a team will play away from home: \t" + frequencyOfAwayGames + "\n";
		out.print(results);
		out.close();
		aFile.close();
	}

	/*	Inputs:		Arguments of method.
		Processes:	Generate fixtures (page 114)
		Outputs:	File containing fixtures.
		Creator:	Ben Smith	*/
	public static void generateFixtures(String leagueName, int teamAmount) throws IOException
	{
		int totalNumberOfRounds, numberOfMatchesPerRound;
		int homeTeamNumber, awayTeamNumber, even, odd, roundNumber, matchNumber, homeTeam, awayTeam, numberOfTeams;
		int numberOfFixtures = 0;
		boolean additionalTeamIncluded = false;
		String game;
		String [] [] fixtures;
		String [] [] revisedFixtures;
		String [] elementsOfFixture;
		String [] splitForFile;
		String fixtureAsText;
		numberOfTeams = teamAmount; 
		if (numberOfTeams % 2 == 1)
		{
			numberOfTeams++;
			additionalTeamIncluded = true;
		}
		totalNumberOfRounds = numberOfTeams - 1;
		numberOfMatchesPerRound = numberOfTeams / 2;
		fixtures = new String[totalNumberOfRounds][numberOfMatchesPerRound];
		for(roundNumber = 0; roundNumber < totalNumberOfRounds; roundNumber++)
		{
			for(matchNumber = 0; matchNumber < numberOfMatchesPerRound; matchNumber++)
			{
				homeTeamNumber = (roundNumber + matchNumber) % (numberOfTeams - 1);
				awayTeamNumber = (numberOfTeams - 1 - matchNumber + roundNumber) % (numberOfTeams - 1);
				if (matchNumber == 0)
				awayTeamNumber = numberOfTeams - 1;
				fixtures[roundNumber][matchNumber] = (homeTeamNumber + 1) + " v " + (awayTeamNumber + 1);
			}
		}
		revisedFixtures = new String[totalNumberOfRounds][numberOfMatchesPerRound];
		even = 0;
		odd = numberOfTeams / 2;
		for(int i = 0; i < fixtures.length; i++)
		{
			if (i % 2 == 0)
			revisedFixtures[i] = fixtures[even++];
			else
			revisedFixtures[i] = fixtures[odd++];
		}
		fixtures = revisedFixtures;
		for(roundNumber = 0; roundNumber < fixtures.length; roundNumber++)
		{
			if (roundNumber % 2 == 1)
			{
				fixtureAsText = fixtures[roundNumber][0];
				elementsOfFixture = fixtureAsText.split(" v ");
				fixtures[roundNumber][0] = elementsOfFixture[1] + " v " + elementsOfFixture[0];
			}
		}
		if (additionalTeamIncluded == true)
		{
			PrintWriter toFile = new PrintWriter(new FileWriter(leagueName+"Fixtures.txt"));
			for(roundNumber = 0; roundNumber < totalNumberOfRounds; roundNumber++)
			{
				System.out.println("Round " + (roundNumber + 1) + "\t\t");
				for(matchNumber = 0; matchNumber < numberOfMatchesPerRound - 1; matchNumber++)
				{
					System.out.println("\tMatch " + (matchNumber + 1) + ": " + fixtures[roundNumber][matchNumber+1] + "\t");
					System.out.println();
					game = fixtures[roundNumber][matchNumber+1];
					splitForFile = game.split(" v ");
					homeTeam = Integer.parseInt(splitForFile[0]);
					awayTeam = Integer.parseInt(splitForFile[1]);
					numberOfFixtures++;
					toFile.println(numberOfFixtures+","+homeTeam+","+awayTeam);
				}
			}
			toFile.close();
		}
		else
		{
			PrintWriter toFile = new PrintWriter(new FileWriter(leagueName+"Fixtures.txt"));
			for(roundNumber = 0; roundNumber < totalNumberOfRounds; roundNumber++)
			{
				System.out.println("Round " + (roundNumber + 1) + "\t\t");
				for(matchNumber = 0; matchNumber < numberOfMatchesPerRound; matchNumber++)
				{
					System.out.println("\tMatch " + (matchNumber + 1) + ": " + fixtures[roundNumber][matchNumber] + "\t");
					System.out.println();
					game = fixtures[roundNumber][matchNumber];
					splitForFile = game.split(" v ");
					homeTeam = Integer.parseInt(splitForFile[0]);
					awayTeam = Integer.parseInt(splitForFile[1]);
					numberOfFixtures++;
					toFile.println(numberOfFixtures+","+homeTeam+","+awayTeam);
				}
			}
			toFile.close();
		}
		System.out.println("You will have to use the mirror image of these fixtures for return fixtures.");
	}

	/*	Inputs:		(1)	AllDivisionsTournamentsLeagues.txt
					(2)	File containing results.
					(3)	File containing fixtures.
					(4)	Name of the league.
		Processes:	(1)	Reading and closing files (results, fixtures, allDivisionsTournamentsLeagues.txt).
					(2)	Getting the team names from the team numbers using getTeamName().
					(3)	Formatting the results from the results file in a visually-appealing way using printf statements inside a while loop.
		Outputs:	Display fixture results from results file as a list in the command-line interface (eg. 1. Manchester United 1 - 2 Arsenal).
		Creator:	Jonathan Singer	*/
	public static void viewOutcomeOfPlayedFixtures() throws IOException
	{
		String leagueName = getInputFromEndUser("Enter the name of the league/division/tournament (case sensitive) to display its current results.",
			"View results of played fixtures.");
		if (leagueName.equals(""))
			return;
		else
		{
			File allLeaguesFile = new File("AllDivisionsTournamentsLeagues.txt");
			if (!allLeaguesFile.exists())
				JOptionPane.showMessageDialog(null, "There are no divisions/tournaments/leagues to load.", "Error", 2);
			else
			{
				FileReader allLeaguesReader = new FileReader("AllDivisionsTournamentsLeagues.txt");
				Scanner allLeaguesScanner = new Scanner(allLeaguesReader);
				String lineFromAllLeagues = "";
				while (allLeaguesScanner.hasNext() && !lineFromAllLeagues.contains(leagueName))
					lineFromAllLeagues = allLeaguesScanner.nextLine();
				allLeaguesScanner.close();	allLeaguesReader.close();
				if (!lineFromAllLeagues.contains(leagueName))
					JOptionPane.showMessageDialog(null, "Division/Tournament/League \""+leagueName+"\" not found." +
						"\n\nPlease check that you typed the name correctly.", "Error", 2);
				else
				{
					FileReader resultsReader = new FileReader(leagueName+"Results.txt");
					Scanner resultsScanner = new Scanner(resultsReader);
					String lineFromResults = "";
					String[] results;
					FileReader fixturesReader = new FileReader(leagueName+"Fixtures.txt");
					Scanner fixturesScanner = new Scanner(fixturesReader);
					String lineFromFixtures = "";
					String[] fixtures;
					int fixtureNumber, homeTeamNumber, awayTeamNumber, homeTeamScore, awayTeamScore;
					String homeTeamName = "", awayTeamName = "";
					while (resultsScanner.hasNext())
					{
						lineFromResults = resultsScanner.nextLine();
						results = lineFromResults.split(",");
						lineFromFixtures = fixturesScanner.nextLine();
						fixtures = lineFromFixtures.split(",");
						fixtureNumber = Integer.parseInt(results[0]);
						homeTeamNumber = Integer.parseInt(fixtures[1]);
						awayTeamNumber = Integer.parseInt(fixtures[2]);
						homeTeamName = getTeamName(homeTeamNumber, leagueName);
						awayTeamName = getTeamName(awayTeamNumber, leagueName);
						homeTeamScore = Integer.parseInt(results[1]);
						awayTeamScore = Integer.parseInt(results[2]);
						System.out.printf("%4s%30s%s", fixtureNumber+".", homeTeamName, " "+homeTeamScore+" - "+awayTeamScore+" "+awayTeamName+"\n");
					}
					fixturesScanner.close();	fixturesReader.close();
					resultsScanner.close();		resultsReader.close();
				}
			}
		}
	}

	/*	Inputs:		(1)	File containing fixtures (league name specified by user).
					(2)	Number of fixture.
					(3)	Score of both teams.
		Processes:	Replace matching fixtures in results file with fixture containing results, if file does not exist, create it.
		Outputs:	League results file containing all of the played fixtures.
		Creator:	Sean Sinnott	*/
	public static void enterFixtureOutcome() throws IOException
	{
		String leagueName = getInputFromEndUser("Enter name of league/division/tournament (Case sensitive).", "Enter fixture outcome.");
			if (leagueName.equals(""))
				return;
			else
			{
				/*File resultsFile = new File(leagueName+"Results.txt");
				if (!resultsFile.exists())
				{
					FileWriter results = new FileWriter(leagueName+"Results.txt",true);
					PrintWriter output = new PrintWriter(results);
					ouput.print("");
					output.close();	results.close();
				}
				FileReader resultsReader = new FileReader("AllDivisionsTournamentsLeagues.txt");
				Scanner resultsScanner = new Scanner(resultsReader);
				String lineFromResults = "";*/
				FileWriter results = new FileWriter(leagueName+"Results.txt",true);
				PrintWriter output = new PrintWriter(results);
				String input = "";
				String alphabeticPattern = "([a-zA-Z]+)|(([a-zA-Z]+\\s[a-zA-Z]+)+)";
				String numericPattern = "([0-9]{1,2}+)";
				String homeTeamName = "";
				String awayTeamName = "";
				String 	errorMessage = "Invalid input.";
						errorMessage += "\nOnly input alphabetic characters for tournament/team names";
						errorMessage += "\nOnly input numeric characters for number of teams and points for a win/draw/loss";
						errorMessage += "\nSelect OK to retry";
				int numberOfFixtures = 0;
				int homeResults = 0;
				int awayResults = 0;
				int counter = 0;
				boolean validInput = false;
				boolean number = false;
				boolean home = false;
				boolean away = false;
				boolean j = false;
				while (!(validInput))
				{
					while(!(number))	
					{
						number = true;
						try	
						{
							numberOfFixtures = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter number of results", "Fixture Outcomes", 1));
						}
						catch (NumberFormatException e)
						{
							JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);
							number = false;
						}
					}
					if (Integer.toString(numberOfFixtures).matches(numericPattern))
					{
						while (!(j))
						{
							j = true;
							counter++;
							if (counter <= numberOfFixtures)
							{
								homeTeamName = JOptionPane.showInputDialog(null,"Enter name of home team","Fixture Outcomes",1);
								if (homeTeamName.matches(alphabeticPattern))
								{	
									while(!(home))	
									{
										home = true;
										try	
										{
											homeResults = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter home result", "Fixture Outcomes", 1));
										}
										catch (NumberFormatException e)
										{
											JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);
											home = false;
										}
									}
									if (Integer.toString(homeResults).matches(numericPattern))
									{
										awayTeamName = JOptionPane.showInputDialog(null,"Enter name of away team","Fixture Outcomes",1);
										if (awayTeamName.matches(alphabeticPattern))
										{
											while(!(away))	
											{
												away = true;
												try	
												{
													awayResults = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter away result", "Fixture Outcomes", 1));
												}
												catch (NumberFormatException e)
												{
													JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);
													away = false;
												}
											}
											if (Integer.toString(awayResults).matches(numericPattern))
												validInput = true;
											else
												JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);
										}
										else
											JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);
									}
									else
										JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);
								}
								else
									JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);
							}
							else
								j = true;
						}	
					}
					else
						JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 0);
					input = homeTeamName + "\t" + homeResults + "-" + awayResults + "\t" + awayTeamName + "\n";
					
				}
				output.print(input);
				output.close();
				results.close();
		}
	}

	/*	Inputs:		(1) File containing fixtures (league name specified by user).
					(2) File containing results
		Processes:	
		Outputs:	Display unplayed fixtures from file as a list in a window.
		Creator:	Ben Smith	*/
	public static void viewFutureFixtures() throws IOException
	{
		String leagueName = getInputFromEndUser("Enter the name of the league/division/tournament (case sensitive) to display its upcoming fixtures.",
			"View fixtures that have yet to be played.");
		if (leagueName.equals(""))
			return;
		else
		{
			File allLeaguesFile = new File("AllDivisionsTournamentsLeagues.txt");
			if (!allLeaguesFile.exists())
				JOptionPane.showMessageDialog(null, "There are no divisions/tournaments/leagues to load.", "Error", 2);
			else
			{
				FileReader allLeaguesReader = new FileReader("AllDivisionsTournamentsLeagues.txt");
				Scanner allLeaguesScanner = new Scanner(allLeaguesReader);
				String lineFromAllLeagues = "";
				while (allLeaguesScanner.hasNext() && !lineFromAllLeagues.contains(leagueName))
					lineFromAllLeagues = allLeaguesScanner.nextLine();
				allLeaguesScanner.close();	allLeaguesReader.close();
				if (!lineFromAllLeagues.contains(leagueName))
					JOptionPane.showMessageDialog(null, "Division/Tournament/League \""+leagueName+"\" not found." +
						"\n\nPlease check that you typed the name correctly.", "Error", 2);
				else
				{
					File aFile1 = new File(leagueName+"Results.txt");
					FileReader aFileRead = new FileReader(leagueName+"Fixtures.txt");
					int lineCount = 0, count = 0;
					int fixtureNumber, homeTeamNumber, awayTeamNumber;
					String homeTeamName = "", awayTeamName = "";
					String aLineFromFile = "";
					String[] fixtures;
		
					Scanner in = new Scanner(aFile1);
					while (in.hasNext())
					{
						in.nextLine();
						lineCount++;
					}
					in.close();
					
					in = new Scanner(aFileRead);
					while (in.hasNext())
					{
						count++;
						aLineFromFile = in.nextLine();
						if(count > lineCount)
						{
							fixtures = aLineFromFile.split(",");
							fixtureNumber = Integer.parseInt(fixtures[0]);
							homeTeamNumber = Integer.parseInt(fixtures[1]);
							awayTeamNumber = Integer.parseInt(fixtures[2]);
							homeTeamName = getTeamName(homeTeamNumber, leagueName);
							awayTeamName = getTeamName(awayTeamNumber, leagueName);
							System.out.printf("%4s%30s%s", fixtureNumber+".", homeTeamName, " v "+awayTeamName+"\n");
						}
					}
					in.close();
					aFileRead.close();
				}
			}
		}
	}

	/*	Inputs:		File containing results (league name specified by user).
		Processes:	
		Outputs:	Display values from file as a leader-board in the form of a table.
		Creator:	Brian Walsh	*/
	public static int [][] leaderBoard;
	public static ArrayList<ArrayList<String>>teamsOrPlayers = new ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<Integer>>Fixtures = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Integer>>Results = new ArrayList<ArrayList<Integer>>();
	public static void viewLeaderBoard() throws IOException
	{
		
		boolean readFile;
		
		readFile = readFilesToArrayLists();
		
		if(!readFile)
		System.out.println("The right number of files do not exist");
		else
		{
			CreateLeaderBoard();
			ProcessResults();
			OrderLeaderBoard();
			DisplayLeaderBoard();
		}  
	    
    }
    public static boolean readFilesToArrayLists()throws IOException
    {
	    File results = new File("PremiershipResults.txt");
		File fixtures = new File("PremiershipFixtures.txt");
		File TeamsOrPlayers = new File("PremiershipTeamsOrPlayers.txt");
		
		
		String [] fileElements;
		if(TeamsOrPlayers.exists() && fixtures.exists() && results.exists())
		{
		teamsOrPlayers.add(new ArrayList<String>());
		teamsOrPlayers.add(new ArrayList<String>());
		
		Fixtures.add(new ArrayList<Integer>());
		Fixtures.add(new ArrayList<Integer>());
		Fixtures.add(new ArrayList<Integer>());
		
		Results.add(new ArrayList<Integer>());
		Results.add(new ArrayList<Integer>());
		Results.add(new ArrayList<Integer>());				
		
		Scanner in = new Scanner(TeamsOrPlayers);	
		while(in.hasNext())
		{
	    fileElements = (in.nextLine()).split(",");
	    teamsOrPlayers.get(0).add(fileElements[0]);
	    teamsOrPlayers.get(1).add(fileElements[1]);
        }
        in.close();
        
        Scanner read = new Scanner(fixtures);	
		while(read.hasNext())
		{
	    fileElements = (read.nextLine()).split(",");
	    Fixtures.get(0).add(Integer.parseInt(fileElements[0]));
	    Fixtures.get(1).add(Integer.parseInt(fileElements[1]));
	    Fixtures.get(2).add(Integer.parseInt(fileElements[2]));
        }
        read.close();
        
        Scanner line = new Scanner(results);	
		while(line.hasNext())
		{
	    fileElements = (line.nextLine()).split(",");
	    Results.get(0).add(Integer.parseInt(fileElements[0]));
	    Results.get(1).add(Integer.parseInt(fileElements[1]));
	    Results.get(2).add(Integer.parseInt(fileElements[2]));
        }
        line.close();
        
        return true;
         
       }
       else
       return false;   
    }
    public static void CreateLeaderBoard()
    {
	    
	    int rows = teamsOrPlayers.get(0).size();
	    int columns = 14;
	    leaderBoard = new int [rows][columns];
	    
	    for(int i = 0; i < leaderBoard.length; i++)
	    leaderBoard[i][0] = Integer.parseInt(teamsOrPlayers.get(0).get(i));
	    
	    
    }
    public static void ProcessResults()
    {
	    int position;
	    int fixtureNumber,homeTeamScore,awayTeamScore,homeTeamNumber,awayTeamNumber;
	    for(int i = 0; i < Results.get(0).size();i++)
	    {
		    fixtureNumber = Results.get(0).get(i);
		    homeTeamScore = Results.get(1).get(i);
		    awayTeamScore = Results.get(2).get(i);
		    position = Fixtures.get(0).indexOf(fixtureNumber);
		    homeTeamNumber = Fixtures.get(1).get(position);
		    awayTeamNumber = Fixtures.get(2).get(position);
	    
	    if(homeTeamScore == awayTeamScore)
	    {
	    RecordFixtureResultForHomeTeam(homeTeamNumber,0,1,0,homeTeamScore,awayTeamScore,1);
	    RecordFixtureResultForAwayTeam(awayTeamNumber,0,1,0,homeTeamScore,awayTeamScore,1);
        }
	    else if(homeTeamScore > awayTeamScore)
	    {
		RecordFixtureResultForHomeTeam(homeTeamNumber,1,0,0,homeTeamScore,awayTeamScore,3);    
	    RecordFixtureResultForAwayTeam(awayTeamNumber,0,0,1,homeTeamScore,awayTeamScore,0);
        }
	    
	    else
	    {
	    RecordFixtureResultForHomeTeam(homeTeamNumber,0,0,1,homeTeamScore,awayTeamScore,0);
	    RecordFixtureResultForAwayTeam(awayTeamNumber,1,0,0,homeTeamScore,awayTeamScore,3);
        }
      }
	   
	    
    }
    public static void OrderLeaderBoard()
    {
	     int [][] temp = new int[leaderBoard.length][leaderBoard[0].length];
         boolean finished = false;
         while (!finished)
         {
           finished = true;
           for (int i = 0; i < leaderBoard.length - 1; i++)
             {
             if (leaderBoard[i][13] < leaderBoard[i + 1][13])
              {
               for (int j = 0; j < leaderBoard[i].length; j++)
               {
                 temp[i][j]            = leaderBoard[i][j];
                 leaderBoard[i][j]     = leaderBoard[i + 1][j];
                 leaderBoard[i + 1][j] = temp[i][j];
               }
                 finished = false;
             }
            }
        }
	    
    }
    public static void DisplayLeaderBoard()
    {
	    
	    
	    for(int i = 0; i < leaderBoard.length; i++)
	    {		    	                   
	        System.out.printf("%4d",leaderBoard[i][1]);
		    System.out.printf("%4d",leaderBoard[i][2]);
		    System.out.printf("%4d",leaderBoard[i][3]);
		    System.out.printf("%4d",leaderBoard[i][4]);
		    System.out.printf("%4d",leaderBoard[i][5]);
		    System.out.printf("%4d",leaderBoard[i][6]);
		    System.out.printf("%4d",leaderBoard[i][7]);
		    System.out.printf("%4d",leaderBoard[i][8]);
		    System.out.printf("%4d",leaderBoard[i][9]);
		    System.out.printf("%4d",leaderBoard[i][10]);
		    System.out.printf("%4d",leaderBoard[i][11]);
		    System.out.printf("%5d",leaderBoard[i][12]);
		    System.out.printf("%5d",leaderBoard[i][13]);
		    System.out.println();
	    }
         	    
    }
    public static void RecordFixtureResultForHomeTeam(int htn,int w,int d,int l,int hts,int ats,int p )
    {
	    leaderBoard[htn - 1][1]++; //games played
	    leaderBoard[htn - 1][2]+= w;
	    leaderBoard[htn - 1][3]+= d;
	    leaderBoard[htn - 1][4]+= l;
	    leaderBoard[htn - 1][5]+= hts;
	    leaderBoard[htn - 1][6]+= ats;
	    leaderBoard[htn - 1][12]+= (hts - ats);
	    leaderBoard[htn - 1][13]+= p;   
    }
    public static void RecordFixtureResultForAwayTeam(int atn,int w,int d,int l,int hts,int ats,int p)
    {
	    leaderBoard[atn - 1][1]++; //games played
	    leaderBoard[atn - 1][7]+= w;
	    leaderBoard[atn - 1][8]+= d;
	    leaderBoard[atn - 1][9]+= l;
	    leaderBoard[atn - 1][10]+= ats;
	    leaderBoard[atn - 1][11]+= hts;
	    leaderBoard[atn - 1][12]+= (ats - hts);
	    leaderBoard[atn - 1][13]+= p;     
    }
}