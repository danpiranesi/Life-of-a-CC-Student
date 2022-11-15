package com.cclifegame;

import java.util.ArrayList;

/**
 * This is the main file for initiating and running the game.
 */
public class test {
    /**
     * Game constructor.
     */
    public test() {
        // TODO The endings needs a new structure of organization instead of hard-code

        ArrayList Endings = new ArrayList();

        // imi ending
        String endEnroll = "You have successfully enrolled in the college. A whole new life awaits!";
        Ending end1 = new Ending("Enrolled", 8, endEnroll);
        String endGap = "You decided to have a gap year and comeback later to the school.";
        Ending end2 = new Ending("Gap", 9, endGap);
        Ending ph = new Ending("PH", 0, "This is a place holder scene");

        // Every other endings has been set with placeholders.
        // The category your stats are in at the end of the game will decide which
        // ending you
        // end up with.
        Endings.add(ph);
        Endings.add(ph);
        Endings.add(ph);
        Endings.add(ph);
        Endings.add(ph);
        Endings.add(ph);
        Endings.add(ph);
        Endings.add(ph);
        Endings.add(end1);
        Endings.add(end2);
        Endings.add(ph);

        // Parse the txt file
        Parser ps = new Parser(
                "/Users/danschmidt/Documents/CC/CS2/FinalProject/Life-of-a-CC-Student/game/src/main/java/com/cclifegame/testScript.txt");
        // Start a game with all the parsed scene
        // MainGame gm = new MainGame(ps.getAllScenarios(), Endings);
    }

    /**
     * Main method for running the game.
     */
    public static void main(String[] args) {
        test run = new test();
    }
}