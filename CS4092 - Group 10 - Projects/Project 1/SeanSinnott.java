import javax.swing.JOptionPane;
public class SeanSinnott
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
						case 1:	analyzeVowelContentOfWordPhrase();						break;
						case 2: analyzeConsonantFrequencyOfWordPhrase();				break;
						case 3: determineKeysRequiredToTypeWordPhrase();				break;
						case 4: determineRowsOfKeyboardRequiredToTypeWordPhrase();		break;
						case 5: doesWordPhraseContainAlternatingVowelsAndConsonants();	break;
						case 6: displayShortestAndLongestWord();						break;
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
/*	Inputs:		A word or phrase containing letters, numbers and symbols.
	Processes:	1)Checks to see if user provided an input.
				2)If they didn't then they will be asked to provide one. If they did, the program will remove all the white spaces.
				3)Using the isLetter method, the program will look through the input for an alphabetic character and increment 
				  a counter by one.
				4)Using the isDigit method, the program will look through the input for a numeric character and increment a second
				  counter by one.
				5)the program will then increment a third character by however many characters are left.
	Outputs:	The number of letters, numbers and symbols in the given input.
*/
		String inputFromUser, results;
		int counter = 0;
		int secondCounter = 0;
		int thirdCounter = 0;
		inputFromUser = JOptionPane.showInputDialog(null,"Enter a word or phrase","CharacterFrequency",2);
		while (inputFromUser.equals(""))
		{
			inputFromUser = JOptionPane.showInputDialog(null,"Input required");
		}
		if (inputFromUser.contains(" "))
		inputFromUser = inputFromUser.replaceAll(" ", "" );
		for (int i = 0; i < inputFromUser.length(); i++)
		{
			if (Character.isLetter(inputFromUser.charAt(i)))
				counter++;
			else if (Character.isDigit(inputFromUser.charAt(i)))
				secondCounter++;
			else
				thirdCounter++;
		}
		JOptionPane.showMessageDialog(null,counter + " letter(s), " + secondCounter + " number(s) and " + thirdCounter + " symbol(s).","CharacterFrequency",2);
	}
	
	public static void determineRowsOfKeyboardRequiredToTypeWordPhrase()
	{
	}
	
	public static void doesWordPhraseContainAlternatingVowelsAndConsonants()
	{
	}
	
	public static void displayShortestAndLongestWord()
	{
	}
	
	public static void areWordsPhrasesAnagramsOfEachOther()
	{
	}
	
	public static void determineIfWordPhraseSentenceIsPalindrome()
	{
/*	Inputs:		Accepts a word or phrase from the end user
	Processes:	1)Checks if the user supplied a valid input, if they didn't then an error message appears.
				2)If they did then program moves on to check if input is a word or a phrase by checking if the input contains spaces.
				3)If input is a phrase .ie. contains spaces, then the program splits the input into words and compares the first word  
				  with the last word and the second word with the second last word and so on until the for loop fails, in which case 
				  the phrase is not a palindrome, or the phrase gets passed through the loop in which case the phrase is a palindrome.
				4)If the input is a word  .ie. contains no spaces, then the program reverses the input and compares the first letter of
				  the original input with the first letter of the reversed input and the second letter of the original input with the
				  second letter of the reversed input and so on until the loop fails, in which case the word is not a palindrome, or 
				  the word gets passed through in which case the word is a palindrome.
	Outputs:	Either the word gets passed through the program and it is a palindrome or it fails and it is not a palindrome.	
*/
		String userinput = getWordOrPhraseFromEndUser("Enter word or phrase", "determineIfWordPhraseSentenceIsPalindrome");
		String results, reverse = "";
		if (userinput.equals(""))
			results = "Input Required";
		else
		{
			boolean isPalindrome = false;
			if (userinput.contains(" "))
			{
				isPalindrome = true;
				String [] words;
				words = userinput.split(" ");
				for (int j = 0; j < words.length && isPalindrome != false; j++)
				{
					if (!words[j].equals(words[words.length - (j+1)]))
						isPalindrome = false;
				}
			}
			else
			{
				for (int i = 0; i < userinput.length(); i++)
				{
					reverse = userinput.charAt(i) + reverse;
				}
			}
			if (userinput.equals(reverse) || isPalindrome == true)
				results = "Word or phrase is a palindrome.";
			else
				results = "Word or phrase is not a palindrome.";
		}
		JOptionPane.showMessageDialog(null,results,"determineIfWordPhraseSentenceIsPalindrome",2);
	}
}

	
   