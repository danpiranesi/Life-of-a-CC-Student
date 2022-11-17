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
    public int[] currentR = { 50, 50, 50 };
    int startCount = 0;
    int rightCount = 0;
    int leftCount = 0;
    int right;
    int left;
    boolean leftI = true;
    boolean rightI = true;
    boolean button = true;

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
    private Text mainLevelText;
    @FXML
    private Text welcomeText;
    @FXML
    private Text source;
    @FXML
    private Text levelInfo;
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
        welcomeText.setText("");
        source.setText("");
        levelInfo.setText("");
        leftButton.setDisable(false);
        rightButton.setDisable(false);
        if (startCount == 0) {
            Parser ps = new Parser(
                    "/Users/danschmidt/Documents/CC/CS2/FinalProject/Life-of-a-CC-Student/game/src/main/java/com/cclifegame/testScript.txt");
            // Start a game with all the parsed scene

            // updateImage("test");
            startButton.setText("Next");
            startButton.setStyle("-fx-background-color: #f26d00");
            // updateImage("test");
            game = new MainGame(ps.getAllScenarios(), App.getKeep());
            startCount++;
        } else if (button && game.getBlock() <= 32 && game.getStats()[0] > 0 && game.getStats()[0] < 100
                && game.getStats()[1] > 0 && game.getStats()[1] < 100
                && game.getStats()[2] > 0 && game.getStats()[2] < 100) {
            leftI = true;
            rightI = true;
            button = false; // prevent this from being pressed many times
            hacker.setText("");
            game.setBlock(game.getBlock() + 1);
            game.printOneScenario();
            game.setStats(getCurrent());
            // System.out.println("Player current stats " +
            // Arrays.toString(game.getStats()));
        } else if (button == false) {
            levelImage.setImage(null);
            hacker.setText(
                    "Stop trying to skip blocks, the school needs your financial assistance! Please press a decision button instead.");
        } else {
            if (game.getStats()[0] <= 0) {
                updateImage("gameOver.png");
                mainLevelText.setText(
                        "You either partied too hard, studied too hard, or got bad luck, chose to get an EPIC pass, and had insomnia. Since you are now nocturnal, you drop out of CC and go to a public state school where you can take night classes like a 40-year old father.");
            }
            if (game.getStats()[0] >= 100) {
                updateImage("gameOver.png");
                mainLevelText.setText(
                        "You never know what day it is and miss everyone's texts and calls because your lazy ass is constantly asleep. You have to drop out because everyone forgot you existed.");
            }
            if (game.getStats()[1] <= 0) {
                updateImage("gameOver.png");
                mainLevelText.setText(
                        "You haven't socialized in so long that you think the kawaii anime schoolgirls on your Android Crunchyroll app are actually talking to you. You become delusional and have to drop out because you can no longer talk to anyone. Good thing mom's basement is open.");
            }
            if (game.getStats()[1] >= 100) {
                updateImage("gameOver.png");
                mainLevelText.setText(
                        "You socialize so well and make so many connections at this wealthy school that you get invited to a friend's family trip to Europe. You make it big on TikTok while abroad and become a full-time influencer, dropping out and leaving your academic life behind.");
            }
            if (game.getStats()[2] <= 0) {
                updateImage("gameOver.png");
                mainLevelText.setText("Your grades are terrible. Like even UCCS won't accept you. You flunk out.");
            }
            if (game.getStats()[2] >= 100) {
                updateImage("gameOver.png");
                mainLevelText.setText(
                        "You drop out because you are so smart that Google reaches out to you with a six-figure job at their HQ in California, and you accept. Bye bye CC.");
            }
            leftButton.setDisable(true);
            rightButton.setDisable(true);
            startButton.setText("Again?");
            startButton.setStyle("-fx-background-color: #f21400");

            startCount = 0;
            leftI = true;
            rightI = true;
            game.setBlock(0);
            current[0] = 50;
            current[1] = 50;
            current[2] = 50;
            game.setStats(current);

            // TODO reset all attributes

        }
    }

    @FXML // upon left button click
    public void leftDec() {
        if (leftI == true) {
            applyLeftStats(game.getStats(), game.getSceneLeft(), game.getSceneConLeft());
            leftI = false; // prevent the button from being pressed multiple times
            rightI = false;
            button = true;
            hacker.setText("");
        } else {
            levelImage.setImage(null);
            hacker.setText("Are you trying to violate the Honor Code?! Please press Next button!");
        }
    }

    // TODO finish building method for image retrival
    // game.getScene();
    // scene.getTitle();

    @FXML // upon right button click
    public void rightDec() {
        if (rightI == true) {
            applyRightStats(game.getStats(), game.getSceneRight(), game.getSceneConRight());
            rightI = false; // prevent the button from being pressed multiple times
            leftI = false;
            button = true;
            hacker.setText("");
        } else {
            levelImage.setImage(null);
            hacker.setText("Are you trying to violate the Honor Code?! Please press Next button!");
        }
    }

    @FXML // updates level text
    public void updateLevelText(String mainText, String leftText, String rightText, int block, String imageName) {
        leftLevelText.setText(leftText);
        rightLevelText.setText(rightText);
        mainLevelText.setText(mainText);
        blockCounter.setText("Block " + block);
        updateImage(imageName);
    }

    @FXML // updates stats
    public void updateStats(int[] stats) {
        sleepStat.setText(Integer.toString(stats[0]));
        socialLifeStat.setText(Integer.toString(stats[1]));
        gradesStat.setText(Integer.toString(stats[2]));
    }

    @FXML // updates image
    public void updateImage(String imageID) {
        Image image = new Image(
                getClass().getResourceAsStream(imageID));
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
