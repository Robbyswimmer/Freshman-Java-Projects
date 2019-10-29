// Robert Moseley
// Dr. Han
// Homework #2: Cafe Wall
// Friday, January 25, 2019
// EGR 122, Section A
//
// This program uses the Drawing Panel library to create generate graphics
// and then use those graphics to demonstrate the cafe wall illusion which
// shows the straight lines can look like they are sloping when positioned
// next to one another. This is accomplished using two main methods 1) singleRow
// which generates rows of the squares, and 2) grid which generates grids of the rows.

import java.awt.*;

public class CafeWall {

    //The the DrawingPanel and Graphics are declared outside of main so that they
    //can be accessed by the singleRow and Grid methods shown below
    public static DrawingPanel panel = new DrawingPanel(650, 420);
    public static Graphics g = panel.getGraphics();

    //This constant determines the spacing between rows in a given grid
    //the default spacing is 2, but can be adjusted by changing the num. below
    public static final int mortar = 2;

    public static void main(String[] args) {

        panel.setBackground(Color.gray);

        //prints the two single rows of squares in the correct positions
        singleRow(24,3,0,0);
        singleRow(36,4,40,80);

        //prints the 4 different grids with the correct offset, size, and position
        grid (20, 5, 20,140, 0);
        grid (24, 3, 250,180, 16);
        grid (20, 5, 425, 200, 16);
        grid (32, 2, 450, 20, 32);
    }

    //generates rows of alternating black and white squares–the parameters can specify position and num. of pairs
    public static void singleRow(int size, int pairs, int xPos, int yPos) {

        for (int i = 0; i < pairs; i++) {

            //prints the black squares in the correct position by using the size constant to get each position
            g.setColor(Color.black);
            g.fillRect(xPos + (size * 2 * i), yPos, size, size);

            //these 5 lines of code set the diamond color to blue and correctly position the diamond lines
            //double spacing is used to make the code easier to read
            //In the drawLine's called below the "size" int is used to correctly scale and position the lines
            g.setColor(Color.blue);

            g.drawLine(xPos + (size * 2 * i), yPos + (size / 2),
                    xPos + (size / 2) + (size * 2 * i),yPos + size);

            g.drawLine(xPos + (size / 2) + (size * 2 * i), yPos + size,
                    xPos + size + (size * 2 * i),yPos + (size / 2));

            g.drawLine(xPos + size + (size * 2 * i), yPos + (size / 2),
                    xPos + (size / 2) + (size * 2 * i), yPos);

            g.drawLine(xPos + (size / 2) + (size * 2 * i), yPos,
                    xPos + (size * 2 * i), yPos + (size / 2));

            //prints the white squares in the correct position following each black square
            g.setColor(Color.white);
            g.fillRect(xPos + size + (size * 2 * i), yPos, size, size);
        }
    }

    //This method is used to generates grids made up of rows generated from the singleRow method above
    public static void grid (int size, int pairs, int xPos, int yPos, int offset) {

        //this for loop is used to correctly print the num. of rows in the right position and offset
        for (int i = 0; i < pairs * 2; i++) {
            singleRow(size, pairs, xPos + offset * ( i % 2), yPos + i * size + mortar * i);
        }
    }
}