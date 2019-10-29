// Robert Moseley
// Dr. Han
// Homework #1: Rocket Ship
// Friday, January 18, 2019
// EGR 222, Section A
//
// This program will print the shape of a Rocket Ship based on
// a constant size variable given to the program that varies the
// size of the rocket depending on how large the number is.

public class DrawRocket {

        //Constant variable used to determine the size of the rocket ship
        //Every method below utilizes this constant in order to correctly scale
        //the size of the rocket ship depending on how large a number is given as the constant
        public static final int SIZE = 4;

        //The main method us only used to call the methods created below
        //Main is responsible for the overall appearance of the rocket
        public static void main(String[] args) {
                rocketTop();
                betweenPiece();
                topMid();
                betweenPiece();
                bottomMid();
                betweenPiece();
                rocketTop();
        }

        //This method prints the top and bottom shapes of the rocket
        public static void rocketTop() {
                for (int i = 1; i < SIZE * 2; i++) {
                        loopMaker(" ", (SIZE * 2) - i);
                        loopMaker("/", i);
                        System.out.print("*++*");
                        loopMaker("\\", i);
                        System.out.println();
                }
        }

        // This method creates the line the appears between each major piece of the rocket
        // the length of the line is automatically adjusted using the SIZE constant
        public static void betweenPiece() {
                System.out.print("**");
                loopMaker("=+", SIZE * 2);
                System.out.println("**");
        }

        // This creates the upper-middle part of the rocket using the two shapes created below
        public static void topMid() {
                topDiamond();
                botDiamond();
        }

        // This creates the bottom-middle part of the rocket using the two shapes created below
        public static void bottomMid() {
                botDiamond();
                topDiamond();
        }

        //this is a private method that creates the part of the rocket with the upside down triangles
        private static void topDiamond() {
                for (int i = 0; i < SIZE ; i++) {
                        System.out.print("||");
                        loopMaker(".", i);
                        loopMaker("\\/", SIZE - i);
                        loopMaker("..", i);
                        loopMaker("\\/", SIZE - i);
                        loopMaker(".", i);
                        System.out.print("||");
                        System.out.println();
                }
        }

        //this is a private method that creates the part of the rocket with the upwards-facing triangles
        private static void botDiamond() {
                for (int i = 1; i < SIZE + 1; i++) {
                        System.out.print("||");
                        loopMaker(".", SIZE - i);
                        loopMaker("/\\", i);
                        loopMaker("..", SIZE - i);
                        loopMaker("/\\", i);
                        loopMaker(".", SIZE - i);
                        System.out.print("||");
                        System.out.println();
                }
        }

        //this is a private helper method that reduces the number of for loops used throughout the program
        private static void loopMaker(String s, int x) {
                for (int j = 0; j < x; j++) {
                        System.out.print(s);
                }
        }
}


