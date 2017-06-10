import java.lang.Thread;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Hangman {
    
    public static void welcomeBanner() {
        System.out.print(
            " _   _                                         \n" + 
            "| | | | __ _ _ __   __ _ _ __ ___   __ _ _ __  \n" + 
            "| |_| |/ _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\ \n" + 
            "|  _  | (_| | | | | (_| | | | | | | (_| | | | |\n" + 
            "|_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|\n" + 
            "                   |___/                       \n" +
            "Andrew Cabey 2017 -- Hangman ASCII art to Bernhard Breytenbach\n"
            );
    }
    
    public static void endGameBanner() {
        System.out.print(
            "  ____                         ___                 \n" +
            " / ___| __ _ _ __ ___   ___   / _ \\__   _____ _ __ \n" +
            "| |  _ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__|\n" +
            "| |_| | (_| | | | | | |  __/ | |_| |\\ V /  __/ |   \n" +
            " \\____|\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|   \n"
            );
    }
    
    public static String randomWord() {
		try (Stream<String> fileInput = Files.lines(Paths.get("wordlist.txt"))) {
			Random rand = new Random();
			Object myStrings[] = fileInput.toArray();
			return myStrings[rand.nextInt(myStrings.length)].toString();
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    /******************************************************
    ** ASCII MAN!!! d-_-b One for every number of tries! **
    ******************************************************/
    public static String makeMan(int numTries) {
        switch ( numTries )
        {
            case 10:return "\n  ================|\n //               |\n ||               |\n ||               |\n ||              _^_\n ||             / ..\\\n ||            [  _  ]\n ||             \\___/\n ||\n ||               ||    - *Snap!!!*\n ||              /||\\\n ||             //||\\\\\n ||            // || \\\\\n ||            *  ||  *\n ||              //\\\\\n ||             //  \\\\\n /\\            //    \\\\\n//\\\\         ***      ***\n/||\\\\\n_||_\\\\\n";
            case 9:return "\n  ================|\n //               |\n ||               |\n ||               |\n ||              _^_\n ||             / ..\\\n ||            [  _  ]\n ||             \\___/\n ||               ||\n ||              /||\\\n ||             //||\\\\\n ||            // || \\\\\n ||            *  ||  *\n ||              //\n ||             //\n ||            //\n /\\          ***\n//\\\\ \n/||\\\\ \n_||_\\\\\n";
            case 8:return "\n  ================|\n //               |\n ||               |\n ||               |\n ||              _^_\n ||             / ..\\\n ||            [  _  ]\n ||             \\___/\n ||               ||\n ||              /||\\\n ||             //||\\\\\n ||            // || \\\\\n ||            *  ||  *\n ||\n ||\n ||\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n";
            case 7:return "\n  ================|\n //               |\n ||               |\n ||               |\n ||              _^_\n ||             / ..\\\n ||            [  _  ]\n ||             \\___/\n ||               ||\n ||              /||\n ||             //||\n ||            // ||\n ||            *  ||\n ||\n ||\n ||\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n";
            case 6:return "\n  ================|\n //               |\n ||               |\n ||               |\n ||              _^_\n ||             / ..\\\n ||            [  _  ]\n ||             \\___/\n ||               ||\n ||               ||\n ||               ||\n ||               ||\n ||               ||\n ||\n ||\n ||\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n";
            case 5:return "\n  ================|\n //               |\n ||               |\n ||               |\n ||              _^_\n ||             / ..\\\n ||            [  _  ]\n ||             \\___/\n ||               ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n";
            case 4:return "\n  ================|\n //               |\n ||               |\n ||               |\n ||              _^_\n ||             / ..\\\n ||            [  _  ]\n ||             \\___/\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n";
            case 3:return "\n  ================|\n //               |\n ||               |\n ||               |\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n";
            case 2:return "\n  ================\n //\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n";
            case 1:return "\n\n\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n ||\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n";
            case 0:return "\n\n\n\n\n\n\n\n\n\n\n\n\n /\\\n//\\\\\n/||\\\\\n_||_\\\\\n";
            default: return null;
        }
    }
    
    public static void clearConsole() {
        // ANSI Console clear
        System.out.print("\033[2J\033[;H");
    }
    
    public static void printSpaced(String item) {
        for (int i = 0; i < item.length(); i++) {
            System.out.print(item.charAt(i));
            if (i != item.length()-1) 
                System.out.print(' ');
        }
        System.out.println("");
    }
    
    public static void printSpaced(char[] item) {
        for (int i = 0; i < item.length; i++) {
            System.out.print(item[i]);
            if (i != item.length-1) 
                System.out.print(' ');
        }
        System.out.println("");
    }
    
    public static boolean checkLetter(String correctWord, char[] guessWord, char guess) {
        for (int i = 0; i < correctWord.length(); i++) {
            if (correctWord.toLowerCase().charAt(i) == guess) {
                guessWord[i] = guess;
            }
        }
        
        return (correctWord.indexOf(guess) != -1);
    }
    
    public static boolean checkGuessWord(String correctWord, char[] guessWord) {
        return String.valueOf(guessWord).equals(correctWord);
    }
    
    public static boolean hasBeenGuessed(ArrayList<Character> guessedLetters, char guess) {
        for (int i = 0; i < guessedLetters.size(); i++) {
            if (guessedLetters.get(i) == guess) {
                return true;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        /** 
         * Welcome to the game
         * Word is chosen, progress is displayed
         * 
         * User is prompted
         * 
         * Guess is checked
         * 
         * incorrect guesses are counted
         * 
         * Hangman and progress is displayed
         * 
         * User is prompted
        */
        Scanner kbInput = new Scanner(System.in);
        boolean doPlay = true;
        
        welcomeBanner();
        try {
            Thread.sleep(3000);
        }
        catch (Exception e) {}
        
        while (doPlay) {
            String correctWord = randomWord();
            char[] guessedWord = new char[correctWord.length()];
            ArrayList<Character> guessedLetters = new ArrayList<>(); 
            
            for (int i = 0; i < guessedWord.length; i++) {
                guessedWord[i] = '_';
            }
            
            int incorrectGuesses = 0;
            
            while ((incorrectGuesses < 10) && !checkGuessWord(correctWord, guessedWord)) {
                clearConsole();
                
                System.out.print(makeMan(incorrectGuesses));
                printSpaced(guessedWord);
                System.out.println("You have " + (10 - incorrectGuesses) + " incorrect guesses left!\n");
                
                // Get guess
                char guess = 0;
                String guessLine = "";
                do {
                    System.out.print("Guess a letter: ");
                    try {
                        guessLine = kbInput.nextLine();
                    }
                    catch (Exception e) {
                        
                    }
                    if (guessLine != null && guessLine != "") { // TODO String.empty
                        guess = guessLine.charAt(0);
                        
                        if (hasBeenGuessed(guessedLetters, guess)) {
                            System.out.println("You've already guessed " + guess);
                        }
                    }
                    else {
                        System.out.println("Try again!");
                    }
                    

                } while (hasBeenGuessed(guessedLetters, guess) == true);
                
                guessedLetters.add(guess);
                
                // Check guessed letter
                if (checkLetter(correctWord, guessedWord, guess) == false) {
                    incorrectGuesses++;
                }
                
            }
            
            endGameBanner();
            
            if (incorrectGuesses == 10) {
                System.out.print(makeMan(10));
                System.out.println("You lose :( The word was " + correctWord);
            }
            else {
                System.out.println("You win :) The word was " + correctWord);
            }
            
            System.out.print("Play again? (y/n): ");
            
            String playAgain;
            do {
               playAgain = kbInput.nextLine();
            } while (playAgain == null || playAgain.charAt(0) != 'y' || playAgain.charAt(0) != 'n');
            
            doPlay = (playAgain.toLowerCase().charAt(0) == 'y');
        }
        
        kbInput.close();
        
        
    }
}