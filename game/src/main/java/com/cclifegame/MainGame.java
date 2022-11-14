package com.cclifegame;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This is the main game file for all the scenarios and also running the
 * functions supporting the flow of the game.
 */
public class MainGame {
    private boolean inGame;
    private int[] stats;
    private int block = 0;
    ArrayList<Scenario> Scenarios ;
    ArrayList<Ending> Endings;
    private Ending end;
    private Scenario scene;
    // All the 8 repositories for the scenarios
    private List<Scenario> go_touch_grass;
    private List<Scenario> admonition;
    private List<Scenario> tired;
    private List<Scenario> dorm_dweller;
    private List<Scenario> party_animal;
    private List<Scenario> workaholic;
    private List<Scenario> on_the_verge;
    private List<Scenario> normie;

    /**
     * Initiate the game with scenarios and endings.
     * @param Scenarios
     */
    public MainGame(ArrayList<Scenario> Scenarios, ArrayList<Ending> Endings) {
        this.inGame = true;
        this.stats = new int[]{50,50,50};
        this.block = 0;
        this.Scenarios = Scenarios;
        this.Endings = Endings;

        // Initiate the parser and read the scenarios

        sortScene();

        // in game loop
        while (inGame){
            //start of a block
            // if fits one of the first priority ending requirements:
            if (checkFirstEnding()){
                // debugging
                System.out.println("1st End true");
                // print ending description
                System.out.println(end.getDescription());
                inGame = false;
            } else if (block>32){
                // debugging
                System.out.println("Finished End true");
                // debugging
                System.out.println(this.getCategory());
                // get the correspondent ending with the assigned category
                end = Endings.get(this.getCategory()-1);
                System.out.println(end.getDescription());
                inGame = false;
            } else {
                // debugging
                System.out.println("Looping true");
                // initialize user choices for storing later
                int[]userChoice = new int[3];
                // testing:
                scene = randomDraw();
                scene.printScene();
                int choice = userInput();
                if (choice==1){
                    userChoice = scene.getLchoice();
                    scene.printChoiceDesc(choice);
                    scene.printConsequence(choice);
                } else if (choice==2) {
                    userChoice = scene.getRchoice();
                    scene.printChoiceDesc(choice);
                    scene.printConsequence(choice);
                } else {
                    System.out.println("Invalid choice!");
                }
                for (int i=0;i<3;i++){
                    this.stats[i] += userChoice[i] ;
                }
            }
            System.out.println("current stats:");
            System.out.println("Social life: "+this.stats[0]+" | Grades: "+this.stats[1]+" | Energy: "+this.stats[2]);
            block++;
        }
    }

    // Game flow functions
    /**
     * Checking if the current stats fits in any first priority ending
     * condition. If fits then end the game prematurely.
     */
    public boolean checkFirstEnding(){
        // this is done assuming the category is *onto* priority endings
        // get the category of the current stats
        if(this.getCategory()==9){
            end = Endings.get(8);
            return true;
        } else if(this.getCategory()==10){
            end = Endings.get(9);
            return true;
        } else if (this.getCategory()==11){
            end = Endings.get(10);
            return true;
        } else {
            return false;
        }
    }

    /**
     * A function to sort the scene by their categories.
     */
    public void sortScene(){
        List<Scenario> sceList = (ArrayList<Scenario>) Scenarios.clone();
        this.go_touch_grass = sceList.stream().filter(
                Scenario -> Scenario.getCategory()==1).collect(Collectors.toList());
        this.admonition = sceList.stream().filter(
                Scenario -> Scenario.getCategory()==2).collect(Collectors.toList());
        this.tired = sceList.stream().filter(
                Scenario -> Scenario.getCategory()==3).collect(Collectors.toList());
        this.dorm_dweller = sceList.stream().filter(
                Scenario -> Scenario.getCategory()==4).collect(Collectors.toList());
        this.party_animal = sceList.stream().filter(
                Scenario -> Scenario.getCategory()==5).collect(Collectors.toList());
        this.workaholic = sceList.stream().filter(
                Scenario -> Scenario.getCategory()==6).collect(Collectors.toList());
        this.on_the_verge = sceList.stream().filter(
                Scenario -> Scenario.getCategory()==7).collect(Collectors.toList());
        this.normie = sceList.stream().filter(
                Scenario -> Scenario.getCategory()==8).collect(Collectors.toList());
    }

    /**
     * A function for drawing a scenario from the corresponding arraylist.
     */
    public Scenario randomDraw(){
        int cate = this.getCategory();
        if (cate == 1){
            // draw form go_touch_grass
            // generate a random integer within the indexes of the go_touch_grass arraylist
            int randI = randomInt(0,go_touch_grass.size());
            return go_touch_grass.get(randI);
        } else if (cate == 2) {
            // draw form admonition
            int randI = randomInt(0,admonition.size());
            return admonition.get(randI);
        } else if (cate == 3) {
            //TO-DO: draw form tired
            int randI = randomInt(0,tired.size());
            return tired.get(randI);
        } else if (cate == 4) {
            //TO-DO: draw form dorm_dweller
            int randI = randomInt(0,dorm_dweller.size());
            return dorm_dweller.get(randI);
        } else if (cate == 5) {
            //TO-DO: draw form party_animal
            int randI = randomInt(0,party_animal.size());
            return party_animal.get(randI);
        } else if (cate == 6) {
            //TO-DO: draw form workaholic
            int randI = randomInt(0,workaholic.size());
            return workaholic.get(randI);
        } else if (cate == 7) {
            //TO-DO: draw form on_the_verge
            int randI = randomInt(0,on_the_verge.size());
            return on_the_verge.get(randI);
        } else if (cate == 8) {
            //TO-DO: draw form normie
            int randI = randomInt(0,normie.size());
            return normie.get(randI);
        } else {
            System.out.println("Error: Category out of bound! \nPlease check your stats and loops.");
            return null;
        }
    }

    /**
     * A function to get the category of the player's current stats.
     * 1st Priority ending categories:
     * C9: SL below 0 (leave school)
     * C10: G below 0 (dropout)
     * C11: E below 0 (sudden cardiac death)
     *
     * 2nd categories:
     * C1: SL too low (go touch grass)
     * C2: G too low (admonition)
     * C3: E too low (tired)
     * C4: SL & G (dorm dweller)
     * C5: G & E (party animal)
     * C6: E & SL (workaholic)
     * C7: all too low (on the verge)
     * C8: all good (normie)
     */
    public int getCategory(){
        int[] currentStat = this.getStats();
        // Stats: Social life, Grade, Energy
        // for (int i=0;i<3;i++){
        // 1st Priority checks, anytime i < 0 just end the game
        // C1:
        if (!(currentStat[0]>0)){
            this.inGame = false;
            return 9;
        }
        // C2 if C1's not met:
        else if (!(currentStat[1] > 0)){
            this.inGame = false;
            return 10;
        }
        // C3 if C2 & C1's not met:
        else if (!(currentStat[2] > 0)){
            this.inGame = false;
            return 11;
        }
        // 2nd Priority checks -  if previous statements are not met (game still running)
        else {
            // Here the multiple-fit cases should be checked first
            // C7:
            if (currentStat[0] < 25 && currentStat[1] < 25 && currentStat[2] < 25) {
                return 7;
            }
            // C4:
            else if (currentStat[0] < 25 && currentStat[1] < 25) {
                return 4;
            }
            // C5:
            else if (currentStat[1] < 25 && currentStat[2] < 25) {
                return 5;
            }
            // C6:
            else if (currentStat[0] < 25 && currentStat[2] < 25) {
                return 6;
            }
            // C1:
            else if (currentStat[0] < 25) {
                return 1;
            }
            // C2:
            else if (currentStat[1] < 25) {
                return 2;
            }
            // C3:
            else if (currentStat[2] < 25) {
                return 3;
            }
            // C8:
            else {
                return 8;
            }
        }
    }

    /**
     * A function for taking an user input.
     * @return
     */
    public int userInput(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your choice (1 or 2): ");
        int n = input.nextInt();
        return n;
    }

    /**
     * A new function for generating a random int in between the lower and the upper bounds.
     * @return A random generated integer.
     */
    public int randomInt(int lower, int upper) {
        if (!(upper<0)){
            Random rand = new Random();
            int randI =rand.nextInt(upper)*(upper - lower+1) + lower;
            return randI;
        }
        return 1000;
    }

    public void addScene(Scenario scene){
        Scenarios.add(scene);
    }

    public void addEnding(Ending ends) {
        Endings.add(ends);
    }

    // Setters and getters

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public int[] getStats() {
        return stats;
    }

    public void setStats(int[] stats) {
        this.stats = stats;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public ArrayList<Scenario> getScenarios() {
        return Scenarios;
    }

    public void setScenarios(ArrayList<Scenario> scenarios) {
        Scenarios = scenarios;
    }

    public ArrayList<Ending> getEndings() {
        return Endings;
    }

    public void setEndings(ArrayList<Ending> endings) {
        Endings = endings;
    }
}
