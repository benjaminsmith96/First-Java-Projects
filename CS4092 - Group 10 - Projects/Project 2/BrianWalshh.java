import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
public class BrianWalshh
{
	public static int [][] leaderBoard;
	public static ArrayList<ArrayList<String>>teamsOrPlayers = new ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<Integer>>Fixtures = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Integer>>Results = new ArrayList<ArrayList<Integer>>();
	public static void main(String [] args)throws IOException
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
	