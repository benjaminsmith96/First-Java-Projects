import javax.swing.JOptionPane;
public class BrianWalsh
{
	public static void main(String [] args)
	{
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
						case 1:	analyzeVowelContentOfWordPhrase();						break;
						case 2: analyzeConsonantFrequencyOfWordPhrase();				break;
						case 3: determineKeysRequiredToTypeWordPhrase();				break;
						case 4: determineRowsOfKeyboardRequiredToTypeWordPhrase(getWordOrPhraseFromEndUser("Enter word/phrase/sentence", "QWERTY keyboard row check"));	break;
						case 5: doesWordPhraseContainAlternatingVowelsAndConsonants();	break;
						case 6: displayShortestAndLongestWord(getWordOrPhraseFromEndUser("Enter word/phrase/sentence", "Longest and shortest words"));	break;
						case 7: areWordsPhrasesAnagramsOfEachOther();					break;
						case 8: determineIfWordPhraseSentenceIsPalindrome();			break;
						case 0: break;
					}
				}
			}
		}
	}

 	public static String getMenuOption()
	{
		String menuOptions = "1. Analyze vowel content of word/phrase." +
			"\n2. Analyze consonant frequency of a word/phrase." +
			"\n3. Determine keys required to type a word/phrase." +
			"\n4. Determine rows of QWERTY keyboard required to type a word/phrase." +
			"\n5. Does a word/phrase contain alternating vowels and consonants?" +
			"\n6. Display the shortest and longest word in a sentence." +
			"\n7. Are two words/phrases anagrams of each other?" +
			"\n8. Is a word/phrase/sentence a palindrome?" +
			"\n0. Exit.";
		boolean validInput = false;
		String selection = "", menuChoicePattern = "[0-7]{1}";
		String errorMessage = "Invalid menu selection.";
			errorMessage += "\n\nValid options are 0 to 7 inclusive.";
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
	
	public static String getWordOrPhraseFromEndUser(String windowMessage, String windowTitle)
	{
		boolean validInput = false;
		String userinput = "";
		String pattern = "([a-zA-Z]+)|(([a-zA-Z]+\\s[a-zA-Z]+)+)";
		String errorMessage = "Invalid input.";
			errorMessage += "\n\nEnter a word or phase comprising of alphabetic characters only.";
			errorMessage += "\nSelect OK to retry";
		while (!(validInput))
		{
			userinput = JOptionPane.showInputDialog(null, windowMessage, windowTitle, 3);
			if (userinput == null || userinput.matches(pattern))
				validInput = true;
			else
				JOptionPane.showMessageDialog(null, errorMessage, "Error in user input", 2);
		}
		return userinput;
	}
	
	/*
		Fill your code into the appropriate methods below. Use above method getWordOrPhraseFromEndUser()
		in your method to take input from the user.
		After you have your assigned methods completed, upload this file to the drive with your name
		as the class name (also change it in the file). e.g. JonathanSinger.java
	*/
	public static void analyzeVowelContentOfWordPhrase()
	{
	}
	
	public static void analyzeConsonantFrequencyOfWordPhrase()
	{
	}
	
	public static void determineKeysRequiredToTypeWordPhrase()
	{
       
		
		
	}
	
	public static void determineRowsOfKeyboardRequiredToTypeWordPhrase(String input)
	{
		//brianw walsh 13147811
		String result = "";
        char[] row1 = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'}, row2 = {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'}, row3 = {'z', 'x', 'c', 'v', 'b', 'n', 'm'};
		char [] enteredWordPhrase = ((input.replaceAll("\\s+", "").toLowerCase())).toCharArray();
		
		if(arrayCheck(enteredWordPhrase, row1)){// "arrayCheck" is a seperate method used to check if all of array enteredWordPhrase is in array row1
            result = "This can be typed using only row one of the QWERTY keyboard";
        }
        else if(arrayCheck(enteredWordPhrase, row2)){ //and the same for row 2 and 3
            result = "This can be typed using only row two of the QWERTY keyboard";
        }
        else if(arrayCheck(enteredWordPhrase, row3)){
            result = "This can be typed using only row three of the QWERTY keyboard";
        }
        else{
            result = "This cannot be typed using any one row of the QWERTY keyboard";
        }
        JOptionPane.showMessageDialog(null, result, "QWERTY row check", 2);
	}
	
	public static void doesWordPhraseContainAlternatingVowelsAndConsonants()
	{
	}
	
	public static void displayShortestAndLongestWord(String input)
	{
		String result = "";
		String [] words= input.split("\\s+");//splits sentence into words
		String longest = words[0],shortest = words[0];
		
		for(String i:words){ //finds longest and shortest
		if(i.length() > longest.length()){
		longest = i;
	                                     }
		if(i.length() < shortest.length()){
		shortest = i;
	                                      }
                           }
        result = "Maximum word length is: " + longest.length() + "\nWords matching this length are: ";   //finds words matching this length              
        for(String i : words){                              
		if(i.length() == longest.length()){
		result += i + ", ";
	                                       }
                                       }                  
                           
        result += "\nMinimum word length is: " + shortest.length() + "\nWords matching this length are: ";   //finds words matching shortest word length               
        for(String i : words){                  
		if(i.length() == shortest.length()){
		result += i + ", ";
	                                       }
                                       }
                                       
        
	    JOptionPane.showMessageDialog(null, result, "Longest and shortest words", 2);                                   
                           
		
		
		
	}
	
	public static void areWordsPhrasesAnagramsOfEachOther()
	{
	}
	
	public static void determineIfWordPhraseSentenceIsPalindrome()
	{
	}

    public static boolean arrayCheck(char[] arrayA, char[] arrayB)//checks if all of array a is in b
    {
        boolean foundAll = true, foundOne;

        for(int i = 0; i < arrayA.length && foundAll; i++){ //Will fail if an element is not found in arrayB and foundAll is set to false
            foundOne = false;
            for(int j = 0; j < arrayB.length && !foundOne; j++){
                if(arrayA[i] == arrayB[j]){
                    foundOne = true;
                                          }
                                                               }
            if(!foundOne){
                foundAll = false;
                         }
                                                          }
        return foundAll;
     }  

}


	
   