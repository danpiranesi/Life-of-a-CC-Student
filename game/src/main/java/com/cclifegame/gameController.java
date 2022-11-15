package com.cclifegame;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class gameController implements Initializable {

    public MainGame game;
    public int[] current = { 50, 50, 50 };
    int startCount = 0;
    boolean start = true;
    int rightCount = 0;
    int leftCount = 0;
    int right;
    int left;

    @FXML
    private Text sleepStat;
    @FXML
    private Text socialLifeStat;
    @FXML
    private Text gradesStat;
    @FXML
    private ImageView levelImage;
    @FXML
    private Label leftLevelText;
    @FXML
    private Label rightLevelText;
    @FXML
    private Label mainLevelText;
    @FXML
    private Label blockCounter;
    @FXML
    private Label hacker;
    @FXML
    private Button leftButton;
    @FXML
    private Button rightButton;
    @FXML
    private Button startButton;

    @FXML // starts game
    private void startGame() {
        if (startCount == 0) {
            Parser ps = new Parser(
                    "/Users/danschmidt/Documents/CC/CS2/FinalProject/Life-of-a-CC-Student/game/src/main/java/com/cclifegame/testScript.txt");
            // Start a game with all the parsed scene
            ArrayList<Ending> Endings = new ArrayList<Ending>();

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

            startButton.setText("Next");
            startButton.setStyle("-fx-background-color: #1e69e8");

            game = new MainGame(ps.getAllScenarios(), Endings, App.getKeep());
            startCount++;
        } else if (game.getBlock() <= 32 && game.getStats()[0] > 0 && game.getStats()[0] < 100
                && game.getStats()[1] > 0 && game.getStats()[1] < 100
                && game.getStats()[2] > 0 && game.getStats()[2] < 100) {
            leftCount = 0;
            rightCount = 0;
            hacker.setText("");
            game.setBlock(game.getBlock() + 1);
            game.printOneScenario();
            game.setStats(getCurrent());
            System.out.println("Player current stats " + Arrays.toString(game.getStats()));
            // TODO: add functionality to prevent player from rage tapping start to get
            // through blocks
        } else if (startCount > 1) {
            leftCount = 0;
            rightCount = 0;
            hacker.setText(
                    "Stop trying to skip blocks, the school needs your financial assistance. Press a decision button instead.");
        } else {
            mainLevelText.setText("You have to drop out");
        }

    }

    @FXML // upon left button click
    public void leftDec() {
        if (leftCount == 0) {
            applyLeftStats(game.getStats(), game.getSceneLeft(), game.getSceneConLeft());
            leftCount++;
            hacker.setText("");
        } else {
            hacker.setText("L. Song says no! Please press the next button instead.");
        }
    }

    @FXML // upon right button click
    public void rightDec() {
        if (rightCount == 0) {
            applyRightStats(game.getStats(), game.getSceneRight(), game.getSceneConRight());
            rightCount++;
            hacker.setText("");
        } else {
            hacker.setText("L. Song says no! Please press the next button instead.");
        }
    }

    @FXML // updates level text
    public void updateLevelText(String mainText, String leftText, String rightText, int block) {
        leftLevelText.setText(leftText);
        rightLevelText.setText(rightText);
        mainLevelText.setText(mainText);
        blockCounter.setText("Block " + block);
    }

    @FXML // updates stats
    public void updateStats(int[] stats) {
        sleepStat.setText(Integer.toString(stats[0]));
        socialLifeStat.setText(Integer.toString(stats[1]));
        gradesStat.setText(Integer.toString(stats[2]));
    }

    @FXML // updates image
    private void updateImage() {
        Image image = new Image(
                "file:/Users/danschmidt/Documents/CC/CS2/FinalProject/Life-of-a-CC-Student/cclifegame/src/main/java/com/cclifegame/college.png");
        levelImage.setImage(image);
    }

    @FXML
    private void resetGame() {
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    // ------------------------------NON FXML
    // CODE----------------------------------

    // updates players stats when left decision is chosen
    public void applyLeftStats(int[] currStats, int[] lStats, String message) {
        int sleep = currStats[0] + lStats[0];
        int social = currStats[1] + lStats[1];
        int grade = currStats[2] + lStats[2];
        current[0] = sleep;
        current[1] = social;
        current[2] = grade;
        sleepStat.setText((Integer.toString(sleep)));
        socialLifeStat.setText((Integer.toString(social)));
        gradesStat.setText((Integer.toString(grade)));
        mainLevelText.setText(message);
        leftLevelText.setText("");
        rightLevelText.setText("");
    }

    // updates players stats when right decision is chosen
    public void applyRightStats(int[] currStats, int[] rStats, String message) {
        int sleep = currStats[0] + rStats[0];
        System.out.println("curr count " + currStats[0]);
        System.out.println("r count " + rStats[0]);
        int social = currStats[1] + rStats[1];
        int grade = currStats[2] + rStats[2];
        current[0] = sleep;
        current[1] = social;
        current[2] = grade;
        sleepStat.setText((Integer.toString(sleep)));
        socialLifeStat.setText((Integer.toString(social)));
        gradesStat.setText((Integer.toString(grade)));
        mainLevelText.setText(message);
        leftLevelText.setText("");
        rightLevelText.setText("");
    }

    public int[] getCurrent() {
        System.out.println("current " + Arrays.toString(current));
        return current;
    }

}
