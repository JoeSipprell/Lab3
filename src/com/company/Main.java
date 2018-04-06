package com.company;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean quitProgram = false;

        while (true) {
            System.out.println("\nEnter the corresponding number for the program you would like to run\n");
            System.out.println("1. Set Password\n2. Word Count\n3. Phone Number Formatter\n4. ID Generator\n5. Quit");

            String programNum = input.nextLine();

            switch (programNum) {

                case "1":
                    passwordEntry(input);
                    break;

                case "2":
                    wordCount(input);
                    break;

                case "3":
                    phoneNumberGen(input);
                    break;

                case "4":
                    idGen(input);
                    break;

                case "5":
                    quitProgram = true;
                    break;

                default:
                    System.out.println("\n***ERROR: INVALID INPUT***");
                    System.out.println("Example: to run the Set Password program, enter just the digit '1'");
                    System.out.println("Press enter to try again");
                    input.nextLine();
                    break;
            }

            if (quitProgram) {
                System.out.println("\nProgram closed.");
                break;
            }
        }

    }

    // passwordEntry determines if user-inputted string contains between 6 and 10 characters, 1 number, 1 lowercase letter, and 1 uppercase letter.
    // is run from main method
    private static void passwordEntry(Scanner input)
    {
        System.out.println("\nPassword Entry Program");
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

    // wordCount counts the number of words in a sentence gotten from user input.
    // Any chunk of text that has whitespace on both sides is counted as a word.
    // run from main method
    private static void wordCount(Scanner input){
        System.out.println("\nWord Count Program");
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

    // phoneNumberGen formats a 10 digit number (stored as a string, gotten from user input) like a phone number.
    // is run from main method
    private static void phoneNumberGen(Scanner input){
        System.out.println("\nPhone Number Formatting Program");
        System.out.println("Please enter a 10 digit phone number, or enter '999' to quit.");

        String numEntered = "";
        while(!numEntered.equals("999")){
            numEntered = input.nextLine().trim();

            if(numEntered.equals("999")) { break; } // if user enters sentinel value

            if(numEntered.length() == 10){
                try{
                    long x = Long.parseLong(numEntered);

                    StringBuilder phoneNum = new StringBuilder(numEntered);

                    phoneNum.insert(0,'(').insert(4,')').insert(8,'-');

                    System.out.println(phoneNum);
                }
                catch(NumberFormatException e){
                    System.err.println("Your input can only have digits!");
                }
                finally{
                    System.out.println("Please enter a 10 digit phone number, or enter '999' to quit.");
                }
            }
            else{
                System.err.println("Your input needs to be 10 digits long");
            }
        }
    } // end of phoneNumberGen

    // idGen generates a user ID, starting with the user's full initials, followed by the users address number.
    // If user has a hyphenated name, both names are included in initials
    // is run from main method
    private static void idGen(Scanner input){
        System.out.println("\nID Generator Program");


        while (true) {
            System.out.println("Please enter your full name (enter '999' to quit): ");
            String nameIn = input.nextLine().trim();

            if(nameIn.equals("999")) { break; }

            System.out.print("Please enter your street: ");
            String addressIn = input.nextLine();




            String idOut = createID(nameIn, addressIn);

            if(idOut.contains("noinitials")){
                System.err.println("Error: No name detected from input");
            }
            if(idOut.contains("nodigits")){
                System.err.println("Error: No address was detected from input");
            }
            if(!idOut.contains("noinitials") && !idOut.contains("nodigits")) {
                System.out.println(idOut);
            }
            System.out.println("Press enter to create another ID");
            input.nextLine();
        }
    } // end idGen

    // createID returns a string that will contain the first letter of each word in fullName, followed by any numbers detected in address (if no errors occur)
    // returned string will contain error messages if any occur
    // used by idGen
    public static String createID(String fullName, String address)
    {
        String outString = "";
        boolean foundInitials = false, foundDigits = false;

        String[] fName = fullName.replace('-',' ').split("\\s");

        char[] addressChars = address.toCharArray();

        for (String name : fName) {
            if (!name.isEmpty()) {
                outString += name.toUpperCase().charAt(0);
                foundInitials = true;
            }
        }

        if(foundInitials == false){
            outString = "noinitials";
        }

        for (char digit : addressChars) {
            if(checkIfNumeric(digit)){
                outString += digit;
                foundDigits = true;
            }
        }

        if(foundDigits == false){
            outString += "nodigits";
        }

        return outString;
    } // end createID

    // checkIfNumeric returns a boolean value based on if the character is a number or not
    //      Is used by createID
    public static Boolean checkIfNumeric(char digit){
        boolean isNumeric;

        try {
            int x = Integer.parseInt(""+digit);
            isNumeric = true;
        }
        catch(NumberFormatException e){
            isNumeric = false;
        }

        return isNumeric;
    } // end checkIfNumeric
}
