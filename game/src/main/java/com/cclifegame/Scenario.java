package com.cclifegame;

/**
 * This is the public scenario class where all the scenario tiles are built in.
 */
public class Scenario {
    private String title;
    private int category;
    private String description;
    private String choice1;
    private String choice2;
    private String result1;
    private String result2;
    private int[] Lchoice;
    private int[] Rchoice;

    /**
     * @param title The name of this scenario. This will link the scenario to a picture.
     * @param category The category of this scenario. See MainGame method getCategory for details.
     * @param description A text description of the scenario (bodytext).
     * @param choice1 Left choice text.
     * @param choice2 Right choice text.
     * @param result1 Left choice result text.
     * @param result2 Right choice result text.
     * @param lchoice Left choice's modification on the player stats.
     * @param rchoice Right choice's modification on the player stats.
     */
    public Scenario(String title, int category, String description,
                    String choice1, String choice2,
                    String result1, String result2,
                    int[] lchoice, int[] rchoice) {

        this.title = title;
        this.category = category;
        this.description = description;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.result1 = result1;
        this.result2 = result2;
        this.Lchoice = lchoice;
        this.Rchoice = rchoice;
    }

    /**
     * A function for printing the scenarios.
     */
    public void printScene(){
        System.out.println(this.getDescription());
        System.out.println("Choice 1: "+this.getChoice1()+"\nChoice 2: "+this.getChoice2());
    }

    /**
     * A function to print the choice descriptions based on player's previous choice.
     * Used in the MainGame.
     * @param i the choice (1 or 2).
     */
    public void printChoiceDesc(int i){
        if (i == 1){
            System.out.println(this.getChoice1());
        } else if (i == 2) {
            System.out.println(this.getChoice2());
        } else {
            System.out.println("Invalid choice!");
        }
    }

    /**
     * A function to print the choice concequences based on player's previous choice.
     * Used in the MainGame.
     * @param i the choice (1 or 2).
     */
    public void printConsequence(int i){
        if (i == 1){
            System.out.println(this.getResult1());
        } else if (i == 2) {
            System.out.println(this.getResult2());
        } else {
            System.out.println("Invalid choice!");
        }
    }


    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public String getChoice1() {
        return choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public String getResult1() {
        return result1;
    }

    public String getResult2() {
        return result2;
    }

    public int[] getLchoice() {
        return Lchoice;
    }

    public int[] getRchoice() {
        return Rchoice;
    }
}
