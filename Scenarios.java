public class Scenarios {
    private String scenarioText;
    private String choice1;
    private String choice2;
    private String pickedChoice1;
    private String pickedChoice2;
    private int energy;
    private int grades;
    private int social;

    public Scenarios(String scenarioText, String choice1, String choice2, String pickedChoice1, String pickedChoice2, int energy, int grades, int social){
        scenarioText = scenarioText;
        choice1 = choice1;
        choice2 = choice2;
        pickedChoice1 = pickedChoice1;
        pickedChoice2 = pickedChoice2;
        energy = 0;
        grades = 0;
        social = 0;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setGrades(int grades) {
        this.grades = grades;
    }

    public void setSocial(int social) {
        this.social = social;
    }

    public int getEnergy() {
        return energy;
    }

    public int getGrades() {
        return grades;
    }

    public int getSocial() {
        return social;
    }

    public void setScenarioText(String scenarioText) {
        this.scenarioText = scenarioText;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public void setPickedChoice1(String pickedChoice1) {
        this.pickedChoice1 = pickedChoice1;
    }

    public void setPickedChoice2(String pickedChoice2) {
        this.pickedChoice2 = pickedChoice2;
    }

    public String getScenarioText() {
        return scenarioText;
    }

    public String getChoice1() {
        return choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public String getPickedChoice1() {
        return pickedChoice1;
    }

    public String getPickedChoice2() {
        return pickedChoice2;
    }
}
