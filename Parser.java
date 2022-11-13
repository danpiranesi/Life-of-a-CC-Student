import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Parser {
    private ArrayList<Scenarios> scenarios;

    public Parser(String fileNameIn) {
        File scenarioFile = new File(fileNameIn);
        String wholeText = "";
        try {

            Scanner scanny = new Scanner(scenarioFile);


            while (scanny.hasNext()) {

                wholeText += scanny.nextLine() + "\n";
            }


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String[] splitByScenario = wholeText.split("##########################\n");

        scenarios = new ArrayList();


        for (int i = 0; i < splitByScenario.length; i++) {
            scenarios.add(scenarioFromString(splitByScenario[i]));
        }
    }

    public Scenarios scenarioFromString(String readString) {
        String[] lines = readString.split("&&&\n");
        String scenarioText = lines[0].split("::")[1];
        String choice1 = lines[1].split("::")[1];
        String choice2 = lines[2].split("::")[1];
        String pickedChoice1 = lines[3].split("::")[1];
        String pickedChoice2 = lines[4].split("::")[1];
        int energy = 0;
        int grades = 0;
        int social = 0;

        for (int i = 5; i < lines.length; i++) {
            String field = lines[i].split("::")[0];
            if (field.equals("ENERGY")) {
                int value = Integer.parseInt(lines[i].split("::")[1]);
                energy = value;
            } else if (field.equals("GRADES")) {
                int value = Integer.parseInt(lines[i].split("::")[1]);
                grades = value;
            } else if (field.equals("SOCIAL")) {
                int value = Integer.parseInt(lines[i].split("::")[1]);
                social = value;
            }
        }

        Scenarios newScenario;
            newScenario = new Scenarios(scenarioText, choice1, choice2, pickedChoice1, pickedChoice2, energy, grades, social);

        return newScenario;
    }
    public ArrayList<Scenarios> getAllScenarios() {
        return scenarios;
    }
}
