import javax.swing.JOptionPane;
public class GroupTenProjectOne
{
	public static void main(String [] args)
	{
		int choice;
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
						case 1:	analyzeVowelContentOfWordPhrase();											break;
						case 2: analyzeConsonantFrequencyOfWordPhrase();	break;
						case 3: determineKeysRequiredToTypeWordPhrase();									break;
						case 4: determineRowsOfKeyboardRequiredToTypeWordPhrase(getWordOrPhraseFromEndUser("Enter word/phrase/sentence", "QWERTY keyboard row check"));	break;
						case 5: doesWordPhraseContainAlternatingVowelsAndConsonants();						break;
						case 6: displayShortestAndLongestWord(getWordOrPhraseFromEndUser("Enter word/phrase/sentence", "Longest and shortest words"));	break;
						case 7: areWordsPhrasesAnagramsOfEachOther();										break;
						case 8: determineIfWordPhraseSentenceIsPalindrome();								break;
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
		String selection = "", menuChoicePattern = "[0-8]{1}";
		String errorMessage = "Invalid menu selection.";
			errorMessage += "\n\nValid options are 0 to 8 inclusive.";
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
	
	public static boolean arrayCheck(char[] arrayA, char[] arrayB)//checks if all of array a is in b
	{	//Brian Walsh 13147811
        boolean foundAll = true, foundOne;
        for(int i = 0; i < arrayA.length && foundAll; i++){//Will fail if an element is not found in arrayB and foundAll is set to false
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
	
	public static String analyzeLetterFrequencyOfWordPhrase(String input, String letters, String letterType)
	{
		
		boolean containsLetters = false;
		int[] letterFreq = new int[letters.length()];
		String lowerSpacelessPhrase = input.replaceAll(" ", "").toLowerCase();
		String currentLetter = "", stringPosition = "", result = "";
		boolean matchFound;
		for (int count = 0; count <= lowerSpacelessPhrase.length()-1; count++)
		{
			currentLetter = lowerSpacelessPhrase.substring(count, count+1);
			if (letters.contains(currentLetter))
			{
				matchFound = false;
				for (int count2 = 0; matchFound != true; count2++)
				{
					stringPosition = letters.substring(count2, count2+1);
					if (stringPosition.equals(currentLetter))
					{
						matchFound = true;
						letterFreq[count2] = letterFreq[count2]+1;
					}
				}
			}
		}
		for (int count3 = 0; count3 <= letterFreq.length-1; count3++)
			if (letterFreq[count3] > 0)
			{
				result = result + letters.substring(count3, count3+1) + ": " + letterFreq[count3] + "\n";
				if (containsLetters != true)
					containsLetters = true;
			}
		if (containsLetters != true)
			result = "Input contains no "+letterType+"s.";
		return result;
	}
	
	public static void analyzeVowelContentOfWordPhrase()
	{
	/*	Inputs:		The input is received from the method getWordOrPhraseFromEndUser it is then put into userInput.	
		Processes:	We first check to see if the userInput contains any vowels if not we bypass the rest of the program.
					We then replace everything but the vowels to make it more efficient and check if vowels are in alphabetical order reverse alphabetical order,
					or if all the vowels are there but are in no particular order. We call a method which counts the vowels.
		Outputs:	We display the results using JOptionPane.
	*/
	 String result = "";
	 String vowels = "aeiou";
     String userInput = getWordOrPhraseFromEndUser("Enter word, phrase or sentence.", "Analyze vowel content of word/phrase"), vowel;
	 String revowels = "";
	 boolean IsThereVowels = false;
	 boolean vowelsOrder = true;
	 boolean vowelsAOrder = false;
	 boolean vowelsROrder = false;
	 userInput = userInput.toLowerCase();
	 userInput = userInput.replaceAll("[^a-z]", "");
	 for (int i = 0; i < userInput.length(); i++)
	 {
	  if (vowels.contains(userInput.substring(i, i+1)))
	  IsThereVowels = true;
	 }
	 if (IsThereVowels == true)
	 {
	  result ="There are vowels";
	  userInput = userInput.replaceAll("[^a, e, i, o, u]", "");
	  if (userInput.indexOf(vowels) !=-1)
	  vowelsAOrder = true;
	  {
	   if (vowelsAOrder == true)
       result +="\n"+"vowels are in alphabetical order";
       else result +="\n"+"vowels aren't in alphabetical order";
	  }
	  for(int y = vowels.length()-1;y>=0;y--)
	  {
	   revowels += vowels.substring(y, y+1);
      }
      if (userInput.indexOf(revowels) !=-1)
      vowelsROrder = true;
      {
       if (vowelsROrder == true)
       result +="\n"+"vowels are in reverse alphabetical order";
       else result +="\n"+"vowels aren't in reverse alphabetical order";
      }
      if(!vowelsAOrder && !vowelsROrder)
	  {
      for (int x=0;x<vowels.length()-1;x++)
	  {
	   vowel = vowels.substring(x, x+1);
	   if (userInput.indexOf(vowel) ==-1)
	   vowelsOrder = false;
	  }
	  if (vowelsOrder != false)
	  result +="\n"+"word phrase or sentence contains all the vowels but in no order";
      }
     }
	 else if (IsThereVowels == false)
	 result ="There are no vowels";
	 JOptionPane.showMessageDialog(null, result + "\n" +analyzeLetterFrequencyOfWordPhrase(userInput, vowels, "vowel"));
	}
	
	public static void analyzeConsonantFrequencyOfWordPhrase()
	{
		String windowTitle = "Analyze consonant frequency of word/phrase.";
		String input = getWordOrPhraseFromEndUser("Enter word/phrase/sentence.", windowTitle);
		String output = analyzeLetterFrequencyOfWordPhrase(input, "bcdfghjklmnpqrstvwxyz", "consonant");
		JOptionPane.showMessageDialog(null, output, windowTitle, 2);
	}
	
	public static void determineKeysRequiredToTypeWordPhrase()
	{
	}
	
	public static void determineRowsOfKeyboardRequiredToTypeWordPhrase(String input)
	{
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
	 /*	Inputs:		The input is received from the method getWordOrPhraseFromEndUser it is then put into userInput. 
		Processes:	The userInput is converted to lower case and we replace everything that isn't an alphabetic character.
					We go into a for loop and we check the first letter to see if it contains a vowel or a consonants via an if else statement.
					It then falls into another if statement to see if it contains the alternate letter a boolean is then set to true or false, depending on whether it is alternating.
		Outputs:	We use JOptionPane to print out if it is alternating or not.
	 */
	 String result = "";
	 String vowels = "aeiou";
	 String consonants = "bcdfghjklmnpqrstvwxyz";
	 String userInput = getWordOrPhraseFromEndUser("Enter word, phrase or sentence.", "Analyze vowel content of word/phrase");
	 boolean alternate = true;
	 userInput = userInput.toLowerCase();
	 userInput = userInput.replaceAll("[^a-z]", "");
	 for ( int i = 0; i < userInput.length() - 1 && alternate;i++)
	 {
	  if (vowels.contains(userInput.substring(i, i + 1)))
	  {
	   if (consonants.contains(userInput.substring(i + 1, i + 2)))
	   alternate = true;
	   else
	   alternate = false;
      }
	  else if (consonants.contains(userInput.substring(i, i + 1))) 
      {
       if (vowels.contains(userInput.substring(i + 1, i + 2)))
	   alternate = true;
	   else
	   alternate = false;
      }
     }
     if (alternate == false)
     result = "The word, phrase or sentence doesn't contain alternating vowels and consonants.";
     else
     result = "The word, phrase or sentence does contain alternating vowels and consonants.";
     JOptionPane.showMessageDialog(null, result,"Does a word/phrase or sentence contain alternating vowels and consonants?", 1);
	}
	
	public static void displayShortestAndLongestWord(String input)
	{	//Brian Walsh 13147811
		String result = "";
		String [] words= input.split("\\s+");//splits input into words array
		String longest = words[0],shortest = words[0];
		for(String i:words){ //finds longest and shortest
		if(i.length() > longest.length()){
		longest = i;
	                                     }
		if(i.length() < shortest.length()){
		shortest = i;
	                                      }
                           }
        result = "Maximum word length is: " + longest.length() + "\nWords matching this length are: ";//finds words matching this length              
        for(String i : words){                              
		if(i.length() == longest.length()){
		result += i + ", ";
	                                       }
                                       }
        result += "\nMinimum word length is: " + shortest.length() + "\nWords matching this length are: ";//finds words matching shortest word length               
        for(String i : words){                  
		if(i.length() == shortest.length()){
		result += i + ", ";
	                                       }
                                       }
	    JOptionPane.showMessageDialog(null, result, "Longest and shortest words", 2);//output 
	}
	
	public static void areWordsPhrasesAnagramsOfEachOther()
	{
		String phrase1 = getWordOrPhraseFromEndUser("Enter first word/phrase.", "Are words/phrases anagrams of each other?");
		String phrase1Processed = phrase1.replace(" ", "").toLowerCase();
		String phrase2 = "", phrase2Processed = "";
		while (phrase2Processed.length() != phrase1Processed.length())
		{
			phrase2 = getWordOrPhraseFromEndUser("Enter second word/phrase of same length.", "Are words/phrases anagrams of each other?");
			phrase2Processed = phrase2.replace(" ", "").toLowerCase();
		}
		String phrase2Progress = phrase2Processed;
		String currentLetter1 = "", currentLetter2 = "", result = "";
		int matchingLetters = 0;
		boolean matchFound = true;
		for (int count = 0; count < phrase1Processed.length() && matchFound == true; count++)
		{
			matchFound = false;
			currentLetter1 = phrase1Processed.substring(count, count+1);
			for (int count2 = 0; matchFound != true && count2 < phrase2Progress.length(); count2++)
			{
				currentLetter2 = Character.toString(phrase2Progress.charAt(count2));
				if (currentLetter1.equals(currentLetter2))
				{
					matchingLetters++;
					matchFound = true;
					phrase2Progress = phrase2Progress.substring(0, count2+1).replace(currentLetter2, "_")+phrase2Progress.substring(count2+1);
					//The below line is not necessary. It's only purpose is to show what is happening in the command-line interface.
					System.out.println(currentLetter1+" "+phrase2Progress);
				}
			}
		}
		if (matchingLetters == phrase1Processed.length())
			result = phrase2 + " is an anagram of " + phrase1+".";
		else
			result = phrase2 + " is not an anagram of " + phrase1+".";
		JOptionPane.showMessageDialog(null, result, "", 2);
	}
	
	public static void determineIfWordPhraseSentenceIsPalindrome()
	{
	}
}