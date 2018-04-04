package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //passwordEntry(input);
        wordCount(input);

    }

    private static void passwordEntry(Scanner input)
    {
        System.out.println("Please enter a password that is 6-10 characters long and contains 1 number, 1 letter, and 1 uppercase");
        String password;
        Boolean hasNum = false, hasLet = false, hasUpp = false;


        while (!hasNum || !hasLet || !hasUpp) {
            password = input.nextLine();

            for(int loopThrough = 0; loopThrough < password.length(); loopThrough++){
                if(password.length() < 6 || password.length() > 10){
                    break;
                }
                Character c = password.charAt(loopThrough);

                if(Character.isDigit(c)){
                    hasNum = true;
                }
                if(Character.isUpperCase(c) && Character.isLetter(c)){
                    hasUpp = true;
                }
                if(Character.isLowerCase(c)){
                    hasLet = true;
                }

            } // end password validation loop

            if(hasNum && hasLet && hasUpp){
                System.out.println("Password entry successful");
                break;
            }

            System.out.println("Sorry, your password did not meet the requirements listed above");

        } // end password entry loop

    } // end passwordEntry

    private static void wordCount(Scanner input){

        System.out.println("Please enter a sentence"); // input of sentence
        String sentence = input.nextLine();

        String fSentence = sentence.replace(',',' ').replace('!',' ').replace('?',' ').replace('.',' ').replace('-',' ').replace(';',' ');
            // replaces non-letter characters with spaces

        String[] words = fSentence.split("\\s");
            // splits the sentence based on whitespace

        int numWords = 0;
        for (String word : words) { if(!word.equals("")) {
                numWords += 1;
            }
        } // for loop increments numWords if the item word from words[] isn't an empty string

        System.out.println(String.format("There were %d words in the sentence.",numWords));
    } // end of wordCount

}
