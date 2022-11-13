import java.util.ArrayList;

public class test {
    public test() {
        ArrayList Scenarios = new ArrayList();
        ArrayList Endings = new ArrayList();
        // imi scene
        String graduation = "It's the fall after you graduated from your high school. Do you want to enroll in the college or take a gap year?";
        String[] enrollChoices = {"Enroll in the college.","Take a gap year."};
        int[] Lchoice = {20,20,20}; int[] Rchoice = {-50,-50,-50};
        Scenario enroll = new Scenario(
                "StartCollege",8,graduation,enrollChoices,
                Lchoice,Rchoice);
        Scenarios.add(enroll);

        //imi ending
        String endEnroll = "You have successfully enrolled in the college. A whole new life awaits!";
        Ending end1 = new Ending("Enrolled",8,endEnroll);
        String endGap = "You decided to have a gap year and comeback later to the school.";
        Ending end2 = new Ending("Gap",9,endGap);
        Ending ph = new Ending("PH",0,"This is a place holder scene");
        Endings.add(ph);Endings.add(ph);Endings.add(ph);
        Endings.add(ph);Endings.add(ph);Endings.add(ph);
        Endings.add(ph);Endings.add(ph);Endings.add(end1);
        Endings.add(end2);Endings.add(ph);

        // get a game
        MainGame gm = new MainGame(Scenarios,Endings);
    }

    public static void main(String[] args) {
        test run = new test();

        /* A game main file logic draft:

        while inGame is true:
        if fits one of the first priority ending requirements:
            return ending
            print ending description
            inGame = false
        else
            check stats category
            check block
                if block > 32:
                    return category & correspondent ending
                    print ending description
                else
                    return category
                    draw scenario from corresponding category pool
                    print scenario description
                    print choices and choices description
                    take user input for 1 or 2
                    print choice
                    // loop and modify the int array
                    for (i:3){
                        int[i] playerstats += int[i] choice
                    }
         //print stats after previous operations
         System.out.println(player.stats);

         */

    }
}