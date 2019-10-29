/**
 * // Robert Moseley
 * // Dr. Han
 * // Homework #3: Baby Names
 * // Sunday, February 3, 2019
 * // EGR 122, Section A
 * //
 * // Discussed with: Lauren Nelson, Tutor Robbie
 * //
 * // This programs reads from two files, one containing
 * // a list of popular baby names, and the other containing the
 * // meaning of those names. It takes user input in the form of
 * // a name and gender, finds the names, and then graphs them
 * // according to their popularity using the drawing panel library.
 **/

import java.io.*;
import java.util.*;
import java.awt.*;

public class BabyNames {

    //public constants used to store name/gender from the user
    //and the text returned from the two file searches
    public static String name;
    public static String gender;
    public static String firstline;
    public static String secondLine;

    // Filename class constant for meaning
    // Do NOT change the name of this constant
    private static final String MEANING_FILENAME = "meanings.txt";

    // Filename class constant for name
    // Do NOT change the name of this constant
    // Test with both "names.txt" and "names2.txt" (Before submission, change back to "names.txt")
    private static final String NAME_FILENAME = "names.txt";

    // Put other class constants here
    // You must use the exact name given in the spec for the required class constants
    public static final int LEGEND_HEIGHT = 30;
    public static final int LEGEND_WIDTH = 780;
    public static final int STARTING_YEAR = 1890;
    public static final int NUM_DECADES = 13;
    public static final int DECADE_WIDTH = 60;

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {

        Scanner fileScanner = new Scanner(new File("names.txt"));
        Scanner fileScanner2 = new Scanner(new File("meanings.txt"));

        //gets the start up text and input from the user to use in the search for name method
        startUpMessage();
        getUserInput();

        //finds the correct line to use in the graphics panel as well as outputs lines to console
        firstline = searchForName(name, gender, fileScanner);
        secondLine = searchForName(name, gender, fileScanner2);

        //creates the drawing panel and outputs the text from the name search method
        createFixedGraphics(firstline, secondLine, fileScanner2);
    }

    public static void startUpMessage() {
        System.out.println("This program allows you to search through the data");
        System.out.println("from the Social Security Administration");
        System.out.println("to see how popular a particular name has been");
        System.out.println("since 1890.");
        System.out.println();
    }

    //simple startup sequence that prints the intro text and gets the
    //name and gender from the user that is used to search the text files
    public static void getUserInput() {
        System.out.println("Name: ");
        name = input.nextLine();
        System.out.println("Gender (M or F)");
        gender = input.nextLine();
    }

    //This method takes in the user input name/gender and searches
    //the text files until it either finds the name/gender combo
    //or establishes that it does not exist
    public static String searchForName(String name, String gender, Scanner fileSearch) {

        //declares a string that is equal to the next line in the file
        String text = fileSearch.nextLine();

        //searches the file looking for the user's selections
        while (fileSearch.hasNextLine()) {
            text = fileSearch.nextLine();

            //determines whether or not the correct name has been found–ignores case if words
            Scanner textScanner = new Scanner(text);
            if (name.equalsIgnoreCase(textScanner.next()) && gender.equalsIgnoreCase(textScanner.next())) {
                System.out.println(text);
                return  text;
            }
        }
        String exitCode = ("\"" + name  + "\"" + " not found.");
        System.out.println(exitCode);
        return exitCode;
    }

    //this method handles creating the drawing panel and displaying the
    //text that was found in the files both as a green bar chart, and plain
    //text that is featured at the top of the panel.
    public static void createFixedGraphics(String string1, String string2, Scanner fileSearch) {
        //declaring an initial panel
        DrawingPanel panel;

        //specifying the panel size based on the file used
        if (NAME_FILENAME == "names.txt") {
            panel = new DrawingPanel(780,560);
        } else if (NAME_FILENAME == "names2.txt") {
            panel = new DrawingPanel(780,560 - LEGEND_HEIGHT);
        }

        Graphics g = panel.getGraphics();
        ((Graphics2D) g).setBackground(Color.white);

        //drawing the rectangles at the given positions using the class constants
        if (NAME_FILENAME == "names.txt") {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, LEGEND_WIDTH, LEGEND_HEIGHT);
            g.fillRect(0, 560 - LEGEND_HEIGHT, LEGEND_WIDTH, LEGEND_HEIGHT);
            g.setColor(Color.black);
            g.drawLine(0, LEGEND_HEIGHT, 780, LEGEND_HEIGHT);
            g.drawLine(0, 560 - LEGEND_HEIGHT, 780, 560 - LEGEND_HEIGHT);
            g.drawLine(0, 560, 780, 560);
            g.drawString(string2, 0, 16);
        } else if (NAME_FILENAME == "names2.txt") {

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, LEGEND_WIDTH, LEGEND_HEIGHT);
            g.fillRect(0, 560 - 2 * LEGEND_HEIGHT, LEGEND_WIDTH, LEGEND_HEIGHT);
            g.setColor(Color.black);
            //g.drawLine(0,0, 780, 0);
            g.drawLine(0, LEGEND_HEIGHT, 780, LEGEND_HEIGHT);
            g.drawLine(0, 560 - 2 * LEGEND_HEIGHT, 780, 560 - 2 * LEGEND_HEIGHT);
            g.drawLine(0, 560 - LEGEND_HEIGHT, 780, 560 - LEGEND_HEIGHT);
            g.drawString(string2, 0, 16);
            g.setColor(Color.BLACK);
        }
        variableGraphics(name, gender, fileSearch, g);
    }

    //this method is in charge of graphing the data from the file
    //it is called variable graphics because the output changes based on the input
    public static void variableGraphics(String name, String gender, Scanner fileSearch, Graphics g) {
        int j = 0;
        while (j < NUM_DECADES) {
            String year = Integer.toString(STARTING_YEAR + (j * 10));
            if (NAME_FILENAME == "names.txt") {
                g.drawString(year, DECADE_WIDTH * j, 552);
                j++;
            } else if (NAME_FILENAME == "names2.txt") {
                //String year = Integer.toString(STARTING_YEAR + (j * 10));
                g.drawString(year, DECADE_WIDTH * j, 552 - LEGEND_HEIGHT);
                j++;
            }
        }
        Scanner stringScanner = new Scanner(firstline);
        stringScanner.next();
        stringScanner.next();

        //retrieves each individual number from the line
        int i = 0;
        while (stringScanner.hasNext() && i < NUM_DECADES) {
            String text = stringScanner.next();
            int num = Integer.parseInt(text);

            if (num == 0 || num > 1000) {
                if (NAME_FILENAME == "names.txt") {
                    g.setColor(Color.BLACK);
                    g.drawString("0", i * DECADE_WIDTH, 560 - LEGEND_HEIGHT);
                    i++;
                } else if (NAME_FILENAME == "names2.txt") {
                    g.setColor(Color.BLACK);
                    g.drawString("0", i * DECADE_WIDTH, 560 - 2 * LEGEND_HEIGHT);
                    i++;
                }
            } else if (num > 0 && num <= 1000) {
                g.setColor(Color.green);
                g.fillRect(DECADE_WIDTH * i, (num / 2) + LEGEND_HEIGHT,
                        DECADE_WIDTH / 2, 500 - (num / 2));
                g.setColor(Color.black);
                g.drawString(text, DECADE_WIDTH * i, (num / 2) + LEGEND_HEIGHT);
                i++;
            }
        }
    }
}
