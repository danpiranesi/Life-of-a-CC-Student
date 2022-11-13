package com.cclifegame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class gameController {

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

    @FXML // updates level text
    private void updateLevelText() {
        leftLevelText.setText("test");
        rightLevelText.setText("test");
        mainLevelText.setText("test");
        blockCounter.setText("Block 2");
    }

    @FXML // updates image
    private void updateImage() {
        Image image = new Image(
                "file:/Users/danschmidt/Documents/CC/CS2/FinalProject/Life-of-a-CC-Student/cclifegame/src/main/java/com/cclifegame/college.png");
        levelImage.setImage(image);
    }

    @FXML // updates players stats when left decision is chosen TODO: load players curr
          // stats in int[] leftStats, int[] playerCurrStats
    private void applyLeftStats() {
        sleepStat.setText("50");
        socialLifeStat.setText("50");
        gradesStat.setText("50");
        updateImage();
        updateLevelText();
    }

    @FXML // updates players stats when right decision is chosen TODO: load players curr
          // stats in int[] leftStats, int[] playerCurrStats
    private void applyRightStats() {
        sleepStat.setText(Integer.toString(70));
        socialLifeStat.setText("65");
        gradesStat.setText("27");
    }

    @FXML
    private void resetGame() {

    }

}
