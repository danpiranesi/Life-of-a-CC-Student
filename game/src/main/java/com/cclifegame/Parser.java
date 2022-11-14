import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This is a parser for preparing the txt file into scenarios.
 */
public class Parser {
    private ArrayList<Scenario> scenarios;

    /**
     * The main parser function. It will read a whole txt file and separate each scenario
     * @param filename the file path to read from.
     */
    public Parser(String filename) {
        File scenarioFile = new File(filename);
        String wholeText = "";
        try {
            // A scanner to take in all the text from the file
            Scanner scanny = new Scanner(scenarioFile);
            while (scanny.hasNext()) {
                wholeText += scanny.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
        // This line split each scenario by ##########################  and store them into a string array.
        String[] splitByScenario = wholeText.split("##########################\n");
        scenarios = new ArrayList();
        // Looping through the entire txt file
        for (int i = 0; i < splitByScenario.length; i++) {
            scenarios.add(scenarioFromString(splitByScenario[i]));
        }
    }

    /**
     * A function that read a single scenario and construct it into a scenario object.
     * @param readString the raw string (a chunk of scenario text) to read from.
     * @return a built scenario object.
     */
    public Scenario scenarioFromString(String readString) {
        // Read each line separately
        String[] lines = readString.split("&&&\n");
        // get the title
        String title = lines[0].split("::")[1];
        // get the category as an int
        int category =  Integer. parseInt(lines[1].split("::")[1]);
        // get the
        String scenarioText = lines[2].split("::")[1];
        String choice1 = lines[3].split("::")[1];
        String choice2 = lines[4].split("::")[1];
        String pickedChoice1 = lines[5].split("::")[1];
        String pickedChoice2 = lines[6].split("::")[1];
        int[] Lchoice = {0,0,0}; int[] Rchoice = {0,0,0};

        // For 2 line after the previous fixed input, scan the result1 stats and the result2 stats.
        for (int i = 7; i < lines.length; i++) {
            String field = lines[i].split("::")[0];
            String content = lines[i].split("::")[1];
            if (field.equals("RESULT1")) {
                for ( int j = 0; j < 3; j++) {
                    Lchoice[j] = Integer.parseInt(content.split(";")[j]);
                }
            } else if (field.equals("RESULT2")) {
                for ( int j = 0; j < 3; j++) {
                    Rchoice[j] = Integer.parseInt(content.split(";")[j]);
                }
            }
        }
        // construct a new scenario by the above variables
        Scenario newScenario = new Scenario(title, category, scenarioText,
                choice1, choice2, pickedChoice1, pickedChoice2, Lchoice, Rchoice);
        return newScenario;
    }

    /**
     * A function to get all the scenarios.
     * @return The arraylist of all scenario objects parsed called scenarios.
     */
    public ArrayList<Scenario> getAllScenarios() {
        return scenarios;
    }

    /**
     * A main class for debugging. The printed description should be
     * "You are feeling more tired and down everyday than you used to.
     * You start thinking about how maybe a “prestigious” higher education isn’t for you.
     * Maybe being an adult just sucks.
     * You talk to your friends and they can’t offer any solid advice."
     */
    public static void main(String[] args) {
        Parser ps = new Parser("D:/Personal Folder/Education/Senior/CP222/Life-of-a-CC-Student/CCLifeGame/src/SampleScenario.txt");
        ArrayList<Scenario> scenarios = ps.getAllScenarios();
        System.out.println(scenarios.get(0).getDescription());

    }
}
