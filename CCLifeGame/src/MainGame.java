import jdk.jfr.Category;
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
    // We should have 8 repository for the scenarios afterwards
    ArrayList<Scenario> Scenarios ;
    ArrayList<Ending> Endings;
    private Ending end;
    private Scenario scene;

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
            } else if (block>0){
                // debugging
                System.out.println("Finished End true");
                // debugging
                System.out.println(this.getCategory());
                // get the correspondent ending with the assigned category
                end = Endings.get(this.getCategory());
                System.out.println(end.getDescription());
                inGame = false;
            } else {
                // debugging
                System.out.println("Looping true");
                // initial user choices
                int[]userChoice = new int[3];
                // System.out.println(end.getDescription());
                // scene = thecorrespondentArrayList.randomdraw();
                // testing:
                scene = Scenarios.get(0);
                System.out.println(scene.getDescription());
                System.out.println(Arrays.toString(scene.getChoices()));
                int choice = userInput();
                if (choice==1){
                    userChoice = scene.getLchoice();
                    System.out.println(scene.getChoices()[0]);
                } else if (choice==2) {
                    userChoice = scene.getRchoice();
                    System.out.println(scene.getChoices()[1]);
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
            end = Endings.get(9);
            return true;
        } else if(this.getCategory()==10){
            end = Endings.get(10);
            return true;
        } else if (this.getCategory()==11){
            end = Endings.get(11);
            return true;
        } else {
            return false;
        }
    }

    public void sortScene(){
        List<Scenario> sceList = (ArrayList<Scenario>) Scenarios.clone();
        List<Scenario> tired = sceList.stream().filter(
                Scenario -> Scenario.getCategory()==9).collect(Collectors.toList());
    }
    /**
     * A function to get the category of the player's current stats.
     * 1st Priority ending categories:
     * C1: SL below 0 (leave school)
     * C2: G below 0 (dropout)
     * C3: E below 0 (sudden cardiac death)
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
            if (currentStat[0] < 30 && currentStat[1] < 30 && currentStat[2] < 30) {
                return 7;
            }
            // C4:
            else if (currentStat[0] < 30 && currentStat[1] < 30) {
                return 4;
            }
            // C5:
            else if (currentStat[1] < 30 && currentStat[2] < 30) {
                return 5;
            }
            // C6:
            else if (currentStat[0] < 30 && currentStat[2] < 30) {
                return 6;
            }
            // C1:
            else if (currentStat[0] < 30) {
                return 1;
            }
            // C2:
            else if (currentStat[1] < 30) {
                return 2;
            }
            // C3:
            else if (currentStat[2] < 30) {
                return 3;
            }
            // C8:
            else {
                return 8;
            }
        }
    }

    public int userInput(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your choice (1 or 2): ");
        int n = input.nextInt();
        return n;
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
