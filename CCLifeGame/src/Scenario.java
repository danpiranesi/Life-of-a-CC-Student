public class Scenario {
    private String title;
    private int category;
    private String description;
    private String[] choices;
    private int[] Lchoice;
    private int[] Rchoice;

    public Scenario(String title, int category, String description, String[] choices, int[] lchoice, int[] rchoice) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.choices = choices;
        Lchoice = lchoice;
        Rchoice = rchoice;
    }

    /**
     * A function for printing the scenarios.
     */
    public void printScene(){
        System.out.println(this.getDescription());
        System.out.println(this.getChoices()[1]+" || "+this.getChoices()[2]);
    }

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

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public int[] getLchoice() {
        return Lchoice;
    }

    public void setLchoice(int[] lchoice) {
        Lchoice = lchoice;
    }

    public int[] getRchoice() {
        return Rchoice;
    }

    public void setRchoice(int[] rchoice) {
        Rchoice = rchoice;
    }
}
