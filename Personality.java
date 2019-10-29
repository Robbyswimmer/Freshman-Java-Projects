/**
 * // Robert Moseley
 * // Dr. Han
 * // Homework #4: Personality Test
 * // Sunday, February 17, 2019
 * // EGR 122, Section A
 * //
 * // Discussed with: Tutor Robbie, Grayson
 * //
 * //This program reads from a text file and uses the
 * //provided data to assign a personality type to each
 * //person listed in the text file. Once a personality type
 * //has been assigned that data is written to another file.
 * //This is done for all of the data in the first file.
 **/

import java.io.*;
import java.util.*;

public class Personality {
    public static final int DIMENSION = 4;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        //prints the intro sequence of text to orient the user
        introText();

        //gets the name of the files from the user to use for processing
        System.out.print("input file name? ");
        String inputFileName = input.nextLine();
        System.out.print("output file name? ");
        String outputFileName = input.nextLine();

        //reads from personality.txt and writes the data to output.txt
        processFile(inputFileName, outputFileName);
    }

    public static void introText() {
        System.out.println("This program processes a file of answers to the");
        System.out.println("Keirsey Temperament Sorter. It converts the");
        System.out.println("various A and B answers for each person into");
        System.out.println("a sequence of B-percentages and then into a");
        System.out.println("four-letter personality type.");
        System.out.println();
    }

    public static void processFile(String inputFile, String outputFile) throws FileNotFoundException {

        //Scanner for scanning personality.txt
        Scanner fileScanner = new Scanner(new File(inputFile));

        //Printstream for scanning output.txt
        PrintStream outputToFile = new PrintStream(new File(outputFile));

        //this loops through the file and first processes the data, and then outputs the formatted data
        while (fileScanner.hasNextLine()) {

            //this gets the first two lines of the personality file
            //it uses the first as the name, and the second as the data
            String nameText = fileScanner.nextLine();
            String dataText = fileScanner.nextLine();

            //populates the arrays and then calculates the percentage of b-answers
            //for every type of question in the data
            int[] adjustedArray = incArray(dataText);

            //this creates the personality type based on the b-percentages found
            //in the code directly above
            String personality = createPersonality(adjustedArray, nameText);

            //this outputs the newly formatted data to output.txt
            outputToFile.println(personality);
        }
    }

    //this method is in charge of incrementing the arrays for b, -, and a
    private static int[] incArray(String nextLine) {
        //these 3 arrays are representative of each character in the data that is being processed
        int[] bArray = new int[DIMENSION];
        int[] dashArray = new int[DIMENSION];

        //gets the data that needs to be processed
        Scanner token = new Scanner(nextLine);
        String word = token.next();

        //these statements and loop get the array for each character
        for (int i = 0; i < 70; i++) {
            bArrayHelper(word, i, 'b', 'B', bArray);
            bArrayHelper(word, i,'-', '-', dashArray);
        }

        //this for loop correctly finds the percentages of b-answers
        for (int i = 0; i < 4; i++) {
            double totalAnswers = 20 - dashArray[i];
            if (i == 0) totalAnswers = 10 - dashArray[0];
            double bAnswers = bArray[i];
            double newPercent = (bAnswers / totalAnswers) * 100;
            int percent = (int) Math.round(newPercent);
            bArray[i] = percent;
        }
        return bArray;
    }

    //this is a helper method to increment the arrays for the b-chars, a-chars, and dash-chars
    private static void bArrayHelper(String searchWord, int i, char searchChar, char searchChar2, int[] arrayToIncrease) {
        if (searchWord.charAt(i) == searchChar || searchWord.charAt(i) == searchChar2) {
            if (i % 7 == 0) arrayToIncrease[0]++;
            if (i % 7 == 1 || i % 7 == 2) arrayToIncrease[1]++;
            if (i % 7 == 3 || i % 7 == 4) arrayToIncrease[2]++;
            if (i % 7 == 5 || (i % 7 == 6 && i != 0)) arrayToIncrease[3]++;
        }
    }

    //this is a helper method that assembles the personality type using the adjusted array percentages
    private static String createPersonality(int[] adjustedArray, String nameText) {
        //empty string for appending too
        String personalityType = "";

        //adds a letter to the personality type given the number in each index of the array
        personalityType = personalityHelper(adjustedArray, 0, personalityType, "I", "E");
        personalityType = personalityHelper(adjustedArray, 1, personalityType, "N", "S");
        personalityType = personalityHelper(adjustedArray, 2, personalityType, "F", "T");
        personalityType = personalityHelper(adjustedArray, 3, personalityType, "P", "J");

        return nameText + ": " + "[" + adjustedArray[0] + ", " + adjustedArray[1]
                + ", " + adjustedArray[2] + ", " + adjustedArray[3] + "] = " + personalityType;
    }

    //this helper method is used to eliminate the number of if-statements in the createPersonality method
    private static String personalityHelper (int[] adjustedArray, int arraySpot, String personalityType,
                                           String firstChar, String secondChar) {
        if (adjustedArray[arraySpot] > 50) {
            personalityType += firstChar;
        } else if (adjustedArray[arraySpot] < 50) {
            personalityType += secondChar;
        } else {
            personalityType += "X";
        }
        return personalityType;
    }
}


